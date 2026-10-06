#!/bin/bash
set -e
KC=http://127.0.0.1:8081
NC=https://127.0.0.1:8443

TOKEN=$(curl -s -d client_id=admin-cli -d username=admin -d password=admin -d grant_type=password \
  "$KC/realms/master/protocol/openid-connect/token" | python3 -c "import sys,json;print(json.load(sys.stdin)['access_token'])")
H="Authorization: Bearer $TOKEN"
curl -s -X POST "$KC/admin/realms" -H "$H" -H "Content-Type: application/json" -d '{"realm":"hfuBereich","enabled":true}'
for u in "userCM:Claudia:Müller:cm@hfuBereich.de" "userHM:Hans:Meier:hm@hfuBereich.de"; do
  IFS=: read un fn ln em <<< "$u"
  curl -s -X POST "$KC/admin/realms/hfuBereich/users" -H "$H" -H "Content-Type: application/json" \
    -d "{\"username\":\"$un\",\"firstName\":\"$fn\",\"lastName\":\"$ln\",\"email\":\"$em\",\"enabled\":true,\"emailVerified\":true,\"credentials\":[{\"type\":\"password\",\"value\":\"test1234\",\"temporary\":false}]}"
done
HM=$(curl -s "$KC/admin/realms/hfuBereich/users?username=userHM" -H "$H" | python3 -c "import sys,json;print(json.load(sys.stdin)[0]['id'])")
curl -s -X PUT "$KC/admin/realms/hfuBereich/users/$HM" -H "$H" -H "Content-Type: application/json" -d '{"requiredActions":["CONFIGURE_TOTP"]}'
curl -s -X POST "$KC/admin/realms/hfuBereich/clients" -H "$H" -H "Content-Type: application/json" -d "{
  \"clientId\":\"$NC/apps/user_saml/saml/metadata\",\"protocol\":\"saml\",\"enabled\":true,
  \"redirectUris\":[\"$NC/apps/user_saml/saml/acs\"],\"adminUrl\":\"$NC/apps/user_saml/saml/acs\",
  \"attributes\":{\"saml.assertion.signature\":\"true\",\"saml.client.signature\":\"false\",\"saml_name_id_format\":\"username\"}}"
CID=$(curl -s "$KC/admin/realms/hfuBereich/clients?clientId=$NC/apps/user_saml/saml/metadata" -H "$H" | python3 -c "import sys,json;print(json.load(sys.stdin)[0]['id'])")
for m in "username:username:username" "email:email:email"; do
  IFS=: read n a pr <<< "$m"
  curl -s -X POST "$KC/admin/realms/hfuBereich/clients/$CID/protocol-mappers/models" -H "$H" -H "Content-Type: application/json" \
    -d "{\"name\":\"$n\",\"protocol\":\"saml\",\"protocolMapper\":\"saml-user-property-mapper\",\"config\":{\"user.attribute\":\"$pr\",\"attribute.name\":\"$a\",\"attribute.nameformat\":\"Basic\"}}"
done
SC=$(curl -s "$KC/admin/realms/hfuBereich/client-scopes" -H "$H" | python3 -c "import sys,json;[print(s['id']) for s in json.load(sys.stdin) if s['name']=='role_list']")
curl -s -X DELETE "$KC/admin/realms/hfuBereich/clients/$CID/default-client-scopes/$SC" -H "$H"
IDP_CERT=$(curl -s "$KC/realms/hfuBereich/protocol/saml/descriptor" | grep -oP '(?<=<ds:X509Certificate>)[^<]+' | head -1)

occ() { docker exec -u www-data sp php occ "$@"; }
docker exec -u www-data sp sh -c 'cat > /var/www/html/config/zz-overwrite.config.php <<PHP
<?php
$CONFIG["overwritehost"]="127.0.0.1:8443";
$CONFIG["overwriteprotocol"]="https";
$CONFIG["overwrite.cli.url"]="https://127.0.0.1:8443";
PHP'
occ app:install user_saml; occ app:enable user_saml
occ config:app:set user_saml type --value=saml
PID=$(occ saml:config:create | grep -oE '[0-9]+')
occ saml:config:set "$PID" \
  --general-uid_mapping=username \
  --idp-entityId="$KC/realms/hfuBereich" \
  --idp-singleSignOnService.url="$KC/realms/hfuBereich/protocol/saml" \
  --idp-x509cert="$IDP_CERT" \
  --sp-entityId="$NC/apps/user_saml/saml/metadata" \
  --security-wantAssertionsSigned=1 --security-authnRequestsSigned=0 \
  --saml-attribute-mapping-email_mapping=email --saml-attribute-mapping-displayName_mapping=username
occ saml:config:validate
echo "Fertig. SSO-Login: $NC/apps/user_saml/saml/login  (userCM / test1234)"
