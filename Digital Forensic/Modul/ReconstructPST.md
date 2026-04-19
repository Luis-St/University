# Thu Mar 26 - PST aus Volume Shadow Copies rekonstruieren

## 1. EWF Image mounten (wie gewohnt)
```shell
mkdir -p /tmp/ewf /tmp/vss

# EWF -> RAW konvertieren
xmount --in ewf vss.E0* --cache /tmp/vss.ovl --out raw /tmp/ewf

# Partitionstabelle anzeigen
mmls /tmp/ewf/vss.dd

# Loop Device erstellen
sudo losetup --partscan --find --show /tmp/ewf/vss.dd
# -> /dev/loopX (merken!)
```

## 2. Volume Shadow Copies auflisten
```shell
# Infos ueber vorhandene Shadow Copies anzeigen
sudo vshadowinfo /dev/loopXp2

# Ausgabe zeigt: Anzahl Stores, Timestamps, GUIDs
```

## 3. Shadow Copies mounten (vshadowmount)
```shell
mkdir -p /tmp/vshadow

# Alle Shadow Copy Stores mounten
sudo vshadowmount /dev/loopXp2 /tmp/vshadow

# Verfuegbare Stores anzeigen
ls /tmp/vshadow/
# -> vss1, vss2, vss3, ... (jeder Store = ein Zeitpunkt)
```

## 4. Shadow Copies mit fls durchsuchen
```shell
# PST in jedem Store suchen (fls -pr = rekursiv mit Pfad)
fls -pr /tmp/vshadow/vss1 | grep -i '\.pst'
fls -pr /tmp/vshadow/vss2 | grep -i '\.pst'
# ... wiederholen bis Treffer
# -> PST gefunden in vss5, INODE merken
```

## 5. PST extrahieren
```shell
# PST per INODE aus dem Shadow Copy Store extrahieren
icat /tmp/vshadow/vss5 <INODE> > /tmp/recovered.pst

# Datei verifizieren
file /tmp/recovered.pst

# Pruefsumme erstellen
md5sum /tmp/recovered.pst
```

## 6. PST oeffnen / analysieren
```shell
# Option A: readpst (libpst-utils) - konvertiert PST zu mbox/einzelne Mails
sudo apt install pst-utils -y
readpst -o /tmp/pst_output /tmp/<name>.pst

# Option B: pffinfo/pffexport (libpff-utils) - detailliertere Analyse
sudo apt install libpff-utils -y
pffinfo /tmp/<name>.pst
pffexport /tmp/<name>.pst
```

## 7. Aufraeumen (LiFo)
```shell
sudo umount /tmp/vshadow
sudo losetup -d /dev/loopX
sudo umount /tmp/ewf
```
