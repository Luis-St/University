# APL BFE – Juni 2025 · Forensik-Kommandos

> **Tools:** Sleuthkit (`mmls`, `fls`, `icat`, `fsstat`), `xmount`, `regripper`, `log2timeline`/`plaso`, `vshadowmount`, `binwalk`, `readpst`/`pffexport`, `exiftool`, `strings`, u. Ä.  

---

## Setup – Images mounten

```bash
# Arbeitsverzeichnis
mkdir -p /tmp/ewf /tmp/phone

# ── Windows 11 Image ──────────────────────────────────────────
xmount --in ewf w11_20250601.E0* --cache /tmp/w11.ovl --out raw /tmp/ewf
mmls /tmp/ewf/w11_20250601.dd
losetup --partscan --find --show /tmp/ewf/w11_20250601.dd
# Ergebnis merken, z. B. /dev/loop0
ll /dev/loop0*

# ── Android Image ─────────────────────────────────────────────
mmls mmcblk0p28.E01
# Falls Fehler → xmount:
xmount --in ewf mmcblk0p28.E01 --cache /tmp/phone.ovl --out raw /tmp/phone
mmls /tmp/phone/mmcblk0p28.E01.dd
losetup --partscan --find --show /tmp/phone/mmcblk0p28.E01.dd
# Ergebnis merken, z. B. /dev/loop1
```

---

## Aufgabe 1 – MD5-Hashwerte prüfen (5 Pkt.)

```bash
# Hashwerte berechnen und mit Aufgabenstellung vergleichen
md5sum mmcblk0p28.E01
md5sum w11_20250601.E01
md5sum w11_20250601.E02
md5sum w11_20250601.E03
md5sum w11_20250601.E04
md5sum w11_20250601.E05

# Alternativ mit ewfverify (prüft intern gespeicherte Hashes)
ewfverify w11_20250601.E01

# Tool-Versionen dokumentieren
xmount --version
mmls --version          # The Sleuth Kit Version
fls --version
regripper --version 2>&1 | head -1
log2timeline.py --version
vshadowinfo --version
exiftool -ver
binwalk --version
```

> **Dokumentieren:** Ausgaben Screenshot + Versionsnummern in PDF aufnehmen.

---

## Aufgabe 2 – USB Devices (10 Pkt.)

```bash
# SYSTEM-Hive extrahieren
# Größte Partition wählen (aus mmls – hier p2 als Beispiel)
fls -pr /dev/loop35p3 | grep -i 'SYSTEM$'
# INODE merken, z. B. 59959
icat /dev/loop35p3 <INODE_SYSTEM> > /tmp/SYSTEM
file /tmp/SYSTEM

# USB-Auswertung mit regripper
apt install regripper -y
cd /tmp

# Angeschlossene USB-Geräte (Name, VID/PID)
regripper -p usbdevices -r SYSTEM

# Geräteklassen (inkl. Hersteller)
regripper -p devclass -r SYSTEM

# Timestamps: wann zuerst/zuletzt angeschlossen
regripper -p usbstor -r SYSTEM
regripper -p usbstor2 -r SYSTEM

# Laufwerksbuchstabe des USB-Sticks
regripper -p mountdev -r SYSTEM
```

---

## Aufgabe 3 – Volume Shadow Copies (10 Pkt.)

```bash
mkdir -p /tmp/vshadow

# Vorhandene Shadow Copies anzeigen
vshadowinfo /dev/loop35p3

# Alle Stores mounten
vshadowmount /dev/loop35p3 /tmp/vshadow
ls /tmp/vshadow/
# → vss1, vss2, vss3, … (je Store = ein Wiederherstellungszeitpunkt)

# Timestamps der einzelnen Stores
vshadowinfo /dev/loop35p3 | grep -E 'Store|Creation'

# Beispiel: Inhalte eines Stores auflisten
fls -pr /tmp/vshadow/vss1 | head -30
```

> **Für Aufgabe 5 (E-Mail):** PST-Dateien in den Shadow Copies suchen:
> ```bash
> for i in /tmp/vshadow/vss*; do
>   echo "=== $i ==="; fls -pr "$i" | grep -i '\.pst'
> done
> ```

---

## Aufgabe 4 – Browser-Verlauf mit log2timeline (10 Pkt.)

```bash
# Partition mounten (read-only)
mount -o ro /dev/loop35p3 /mnt

# log2timeline / plaso installieren
apt install plaso -y

# Timeline aus dem kompletten Image erstellen (dauert)
log2timeline.py /tmp/w11.plaso /mnt

# Nur Browser-Artefakte extrahieren (schneller)
log2timeline.py --parsers "chrome_history,firefox_history,msedge_history,ie_webcache" \
    /tmp/w11_browser.plaso /mnt

# Ausgabe als lesbare CSV
psort.py -o l2tcsv -w /tmp/browser_timeline.csv /tmp/w11_browser.plaso

# Nach URLs/Domains filtern
grep -i "http" /tmp/browser_timeline.csv | less

# Alternativ: Chrome History direkt per SQLite
fls -pr /dev/loop35p3 | grep -i 'History$' | grep -i chrome
# INODE merken
icat /dev/loop35p3 <INODE_HISTORY> > /tmp/chrome_history.db
file /tmp/chrome_history.db
apt install sqlite3 -y
sqlite3 /tmp/chrome_history.db "SELECT url, title, visit_count, last_visit_time FROM urls ORDER BY last_visit_time DESC LIMIT 50;"

# Firefox places.sqlite
fls -pr /dev/loop35p3 | grep -i 'places.sqlite'
icat /dev/loop35p3 <INODE_PLACES> > /tmp/places.sqlite
sqlite3 /tmp/places.sqlite "SELECT url, title, visit_count, last_visit_date FROM moz_places ORDER BY last_visit_date DESC LIMIT 50;"

# Edge WebCacheV01.dat (ESE-Datenbank)
fls -pr /dev/loop35p3 | grep -i 'WebCacheV01'
icat /dev/loop35p3 <INODE_EDGE> > /tmp/WebCacheV01.dat
apt install libesedb-utils -y
esedbexport -t /tmp/edge_cache /tmp/WebCacheV01.dat
```

---

## Aufgabe 5 – E-Mail-Kommunikation (10 Pkt.)

```bash
# ── A) PST aus aktiver Partition ──────────────────────────────
fls -pr /dev/loop35p3 | grep -i '\.pst'
icat /dev/loop35p3 <INODE_PST> > /tmp/outlook.pst
file /tmp/outlook.pst

# ── B) PST aus Volume Shadow Copies (Aufgabe 3 beachten!) ─────
for i in 1 2 3 4 5; do
  echo "=== vss$i ==="; fls -pr /tmp/vshadow/vss$i | grep -i '\.pst'
done
# Gefundene PST extrahieren:
icat /tmp/vshadow/vss<N> <INODE_PST> > /tmp/recovered.pst

# ── C) PST analysieren ────────────────────────────────────────
apt install pst-utils -y
mkdir -p /tmp/pst_out
readpst -o /tmp/pst_out /tmp/outlook.pst
ls /tmp/pst_out/
cat /tmp/pst_out/*.mbox | less

# Alternativ: pffexport (ausführlicher)
apt install libpff-utils -y
pffinfo /tmp/outlook.pst
mkdir -p /tmp/pff_out
pffexport -t /tmp/pff_out /tmp/outlook.pst

# Mails durchsuchen
grep -ri "subject\|from\|to\|date" /tmp/pst_out/
```

---

## Aufgabe 6 – Darknet-Aktivitäten (10 Pkt.)

```bash
# ── Tor Browser Artefakte suchen ──────────────────────────────
fls -pr /dev/loop35p3 | grep -i 'tor'
fls -pr /dev/loop35p3 | grep -i 'onion'

# Tor Browser History (Firefox-basiert, places.sqlite)
fls -pr /dev/loop35p3 | grep -i 'places.sqlite' | grep -i tor
icat /dev/loop35p3 <INODE> > /tmp/tor_places.sqlite
sqlite3 /tmp/tor_places.sqlite "SELECT url FROM moz_places WHERE url LIKE '%.onion%';"

# Onion-URLs in Prefetch/Registry suchen
regripper -p recentdocs -r /tmp/NTUSER.DAT | grep -i onion

# Strings-Suche im Image nach .onion-Adressen
# (direkt im gemounteten Volume)
grep -r "\.onion" /mnt/Users/ 2>/dev/null

# Alle Onion-URLs aus Browser-History extrahieren
sqlite3 /tmp/chrome_history.db "SELECT url FROM urls WHERE url LIKE '%.onion%';"
sqlite3 /tmp/places.sqlite    "SELECT url FROM moz_places WHERE url LIKE '%.onion%';"

# ── Onion-URLs auf Aktivität prüfen ───────────────────────────
# Für jeden gefundenen Link: Tor-Browser + Screenshot mit Zeitstempel
# Screenshot-Dateiname: onion_check_YYYY-MM-DD_HH-MM.png
# Torbrowser nutzen und Seite aufrufen → Screenshot + "date" Ausgabe dokumentieren
date
```

> **Hinweis:** Onion-URL-Erreichbarkeit nur über Tor-Netzwerk prüfbar. Zeitpunkt der Prüfung (Screenshot + `date`-Ausgabe) dokumentieren.

---

## Aufgabe 7 – Malware-Hinweise (10 Pkt.)

```bash
# Prefetch analysieren (zeigt ausgeführte Programme)
fls -pr /dev/loop35p3 | grep -i prefetch
mount -o ro /dev/loop35p3 /mnt

find /mnt -type f -iname "*.pf" -print0 | xargs -0 ls -la
# Verdächtige .pf-Dateien mit sccainfo analysieren:
sccainfo /mnt/Windows/Prefetch/<SUSPICIOUS>.pf

# Autostart-Einträge (Run-Keys)
regripper -p run -r /tmp/SOFTWARE
regripper -p run -r /tmp/NTUSER.DAT

# Geplante Tasks
fls -pr /dev/loop35p3 | grep -i 'Tasks'
icat /dev/loop35p3 <INODE_TASK_XML> > /tmp/task.xml
cat /tmp/task.xml

# Installierte Programme auf Anomalien prüfen
regripper -p uninstall -r /tmp/SOFTWARE | less

# Hosts-Datei (DNS-Hijacking)
fls -pr /dev/loop35p3 | grep -i 'hosts$'
icat /dev/loop35p3 <INODE_HOSTS> > /tmp/hosts
cat /tmp/hosts

# strings auf verdächtige Binaries
fls -pr /dev/loop35p3 | grep -i '\.exe$' | grep -v Windows
# Verdächtige EXE extrahieren und mit strings analysieren:
icat /dev/loop35p3 <INODE_EXE> > /tmp/suspicious.exe
strings /tmp/suspicious.exe | grep -Ei "http|cmd|powershell|download|exec|bitcoin|wallet"

# Ereignislog (EVTX) – Security & System
fls -pr /dev/loop35p3 | grep -i '\.evtx$'
icat /dev/loop35p3 <INODE_EVTX> > /tmp/security.evtx
apt install python3-evtx -y
evtx_dump.py /tmp/security.evtx | grep -i "malware\|virus\|threat\|defender"
```

---

## Aufgabe 8 – Geschäftsmodell (10 Pkt.)

```bash
# Kombination aus vorherigen Ergebnissen:
# - Onion-URLs (Aufgabe 6) → Darknet-Marktplatz?
# - E-Mails (Aufgabe 5) → Kommunikation mit Kunden/Lieferanten?
# - Browser-Verlauf (Aufgabe 4) → Bitcoin/Krypto-Börsen, Shops?

# Kryptowährungs-Wallets suchen
grep -r "bitcoin\|monero\|wallet\|BTC\|XMR" /mnt/Users/ 2>/dev/null | head -30
strings /tmp/chrome_history.db | grep -i "bitcoin\|crypto\|wallet\|coinbase"

# Recent Documents / Shell-Bags (welche Dateien/Ordner geöffnet)
regripper -p recentdocs -r /tmp/NTUSER.DAT
regripper -p shellbags -r /tmp/NTUSER.DAT

# Jumplists (zuletzt geöffnete Dateien pro App)
fls -pr /dev/loop35p3 | grep -i 'AutomaticDestinations\|CustomDestinations'

# Dokumente mit Preislisten, Kundendaten etc.
find /mnt/Users/ -type f \( -iname "*.xlsx" -o -iname "*.csv" -o -iname "*.txt" \) 2>/dev/null
```

---

## Aufgabe 9 – Verschlüsselter Container (10 Pkt.)

```bash
# Container-Dateien suchen (VeraCrypt, TrueCrypt, LUKS, BitLocker)
find /mnt -type f -size +1M ! -name "*.exe" ! -name "*.dll" ! -name "*.sys" 2>/dev/null | \
  xargs -I{} file {} | grep -i "data\|encrypted\|LUKS\|BitLocker"

# binwalk – Entropie-Analyse (hohe Entropie = verschlüsselt)
binwalk -E /tmp/suspicious_file

# VeraCrypt/TrueCrypt Container
fls -pr /dev/loop35p3 | grep -v '\.exe\|\.dll\|\.sys\|\.pf\|\.evtx' | \
  grep -v '\(dir\)' | grep -v 'Windows\|ProgramData' | head -50

# Passwort aus Prefetch / History / Dokumenten rekonstruieren
regripper -p recentdocs -r /tmp/NTUSER.DAT | grep -iv "\.docx\|\.xlsx\|\.pdf\|\.png"

# Passworte aus Browser gespeichert
fls -pr /dev/loop35p3 | grep -i 'Login Data'
icat /dev/loop35p3 <INODE_LOGINDATA> > /tmp/login_data.db
sqlite3 /tmp/login_data.db "SELECT origin_url, username_value FROM logins;"

# Container öffnen (falls Passwort bekannt):
veracrypt --text --mount /tmp/container.vc /mnt/container \
  --password="<PASSWORT>" --non-interactive

# LUKS:
cryptsetup open /tmp/container.img mycontainer --type luks
mount /dev/mapper/mycontainer /mnt/container
ls /mnt/container
```

---

## Aufgabe 10 – Handyauswertung: Fahrtroute (15 Pkt.)

```bash
# ── Android Image vorbereiten ─────────────────────────────────
# loop0 = Android Device (mmcblk0p28.E01), Partition wählen (userdata o. ä.)
fsstat /dev/loop1p<N> | head

# ── Bilder mit GPS-EXIF-Daten extrahieren ─────────────────────
apt install exiftool -y

# Alle Bilder finden und extrahieren
fls -pr /dev/loop1p<N> | grep -iE '\.(jpg|jpeg|png|heic)$' | grep -v '\*'

# Bulk-Extraktion aller Bilder in ein Verzeichnis
mkdir -p /tmp/photos
# Für jedes Bild: icat <partition> <inode> > /tmp/photos/imgXXXX.jpg

# Alternativ: Partition mounten (read-only) und Bilder kopieren
mount -o ro /dev/loop1p<N> /mnt
find /mnt -type f -iname "*.jpg" -o -iname "*.jpeg" | xargs -I{} cp {} /tmp/photos/

# GPS-Koordinaten aus allen Bildern auslesen
exiftool -csv -GPSLatitude -GPSLongitude -GPSLatitudeRef -GPSLongitudeRef \
  -DateTimeOriginal /tmp/photos/*.jpg > /tmp/gps_data.csv

cat /tmp/gps_data.csv

# Einzelbild prüfen
exiftool /tmp/photos/IMG_0001.jpg | grep -E "GPS|Date|Time"

# Koordinaten-Format konvertieren (DMS → Dezimalgrad)
# Beispiel: 48 deg 4' 35.00" N = 48 + 4/60 + 35/3600 = 48.0764°
# Ausgabe nach Zeitstempel sortieren
exiftool -csv -DateTimeOriginal -GPSLatitude -GPSLongitude /tmp/photos/*.jpg | \
  sort -t',' -k2
```

> **Fahrtroute rekonstruieren:**
> 1. `gps_data.csv` nach `DateTimeOriginal` sortieren
> 2. Koordinaten in Dezimalgrad umrechnen
> 3. Route chronologisch in Karte einzeichnen (z. B. https://maps.google.com oder `gpxpy`)
> 4. Screenshots der Route in PDF aufnehmen

```bash
# Optional: GPX-Track aus EXIF erzeugen (für Google Maps Import)
exiftool -p gpx.fmt /tmp/photos/*.jpg > /tmp/track.gpx
# gpx.fmt: https://exiftool.org/geotag.html#GPX
```

---

## Aufräumen (LIFO)

```bash
umount /mnt 2>/dev/null
umount /tmp/vshadow 2>/dev/null
umount /tmp/container 2>/dev/null
losetup -D
umount /tmp/ewf 2>/dev/null
umount /tmp/phone 2>/dev/null
```
