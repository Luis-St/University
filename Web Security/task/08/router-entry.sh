#!/bin/bash

/usr/sbin/iptables -t nat -A POSTROUTING -o eth1 -j MASQUERADE
/usr/sbin/iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE


sleep infinity
