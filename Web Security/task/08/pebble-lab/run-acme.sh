#!/bin/bash
set -e
DOMAIN=immich.websec.lab
docker build -f Dockerfile.web -t ws-acme-web .
docker network create ws-acme-net 2>/dev/null || true
docker run -d --name pebble --network ws-acme-net -e PEBBLE_VA_NOSLEEP=1 ghcr.io/letsencrypt/pebble:latest
docker run -d --name ws-acme-web --network ws-acme-net --network-alias "$DOMAIN" ws-acme-web
sleep 3
docker exec ws-acme-web /root/.acme.sh/acme.sh --issue --standalone --httpport 5002 \
  -d "$DOMAIN" --server https://pebble:14000/dir --insecure --force
docker exec ws-acme-web sh -c "printf 'server { listen 443 ssl; ssl_certificate /root/.acme.sh/${DOMAIN}_ecc/fullchain.cer; ssl_certificate_key /root/.acme.sh/${DOMAIN}_ecc/${DOMAIN}.key; location / { return 200 \"HTTPS via ACME\\n\"; } }' > /etc/nginx/sites-available/default; service nginx restart"
docker cp ws-acme-web:/root/pebble-root.pem /tmp/pebble-root.pem
docker run --rm --network ws-acme-net -v /tmp/pebble-root.pem:/ca.pem:ro curlimages/curl \
  curl --cacert /ca.pem -s -w '\nssl_verify_result=%{ssl_verify_result}\n' "https://$DOMAIN/"
