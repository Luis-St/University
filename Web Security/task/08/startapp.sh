#!/bin/sh

/bin/echo "Setze route..."
/usr/bin/sudo /sbin/ip route add 0.0.0.0/0 via 90.0.255.254
/usr/bin/sudo /bin/sh -c 'echo "nameserver 8.8.8.8" > /etc/resolv.conf'

set -e
set -u

export HOME=/config

PIDS=

notify() {
    for N in $(ls /etc/logmonitor/targets.d/*/send)
    do
       "$N" "$1" "$2" "$3" &
       PIDS="$PIDS $!"
    done
}

if ! /usr/bin/membarrier_check 2>/dev/null; then
   notify "$APP_NAME requires the membarrier system call." "$APP_NAME is likely to crash because it requires the membarrier system call.  See the documentation of this Docker container to find out how this system call can be allowed." "WARNING"
fi

set +e
for PID in "$PIDS"; do
   wait $PID
done
set -e

/usr/bin/firefox --version
exec /usr/bin/firefox "$@" >> /config/log/firefox/output.log 2>> /config/log/firefox/error.log

