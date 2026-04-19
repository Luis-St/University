# Thu Mar 26

## 1. SPI Analyse - VMK extrahieren
```shell
# YAFFS Dateisystem in Firmware suchen
binwalk images/20260326/a2019-gh2-full.bin | grep -i yaffs

# VMK aus SPI-Dump extrahieren (ARNE Tool)
./arne -i digital.csv -k "1=SCLK,2=CS,3=MOSI,4=MISO" -o fuwa.vmk
xxd fuwa.vmk
```

## 2. EWF Image analysieren
```shell
# Partitionstabelle anzeigen
mmls hfu.E*

# EWF -> RAW konvertieren
mkdir -p /tmp/ewf
xmount --in ewf hfu.E* --cache /tmp/hfu.ovl --out raw /tmp/ewf

# Loop Device erstellen
sudo losetup --partscan --find --show /tmp/ewf/hfu.dd
# -> /dev/loop37

# Partitionen prüfen
sudo fsstat /dev/loop37p3
```

### mmls Ausgabe
```
      Slot      Start        End          Length       Description
004:  000       0000002048   0000206847   0000204800   EFI system partition
005:  001       0000206848   0000239615   0000032768   Microsoft reserved partition
006:  002       0000239616   0248655871   0248416256   Basic data partition (BitLocker)
007:  003       0248655872   0250066943   0001411072
```

## 3. BitLocker entschlüsseln
```shell
mkdir -p /tmp/bitlocker /tmp/windows

# BitLocker mit VMK-Key entschlüsseln (-K = Key-Datei)
sudo dislocker /dev/loop37p3 -K ../fuwa.vmk -- /tmp/bitlocker

# NTFS dirty flag beheben
sudo ntfsfix -d /tmp/bitlocker/dislocker-file

# Optional: Partition direkt mounten zum Prüfen
sudo mount /tmp/bitlocker/dislocker-file /tmp/windows
ls /tmp/windows
sudo umount /tmp/windows

# Decrypted Partition loopen
sudo losetup --partscan --find --show /tmp/bitlocker/dislocker-file
# -> /dev/loop38
```

## 4. dmsetup - Partitionen zusammenführen
```shell
# dmsetup.txt (loop Nummern anpassen!):
# 0 239616 linear /dev/loop37 0
# 239616 248416256 linear /dev/loop38 0
# 248655872 1411072 linear /dev/loop37 248655872

cat dmsetup.txt | sudo dmsetup create merged
```

## 5. QEMU Virtualisierung
```shell
sudo qemu-system-x86_64 -bios /usr/share/ovmf/OVMF.fd -cpu Skylake-Client-v3 -enable-kvm -usb -hda /dev/mapper/merged -smp 4 -m 4096
```

## 6. Windows Login umgehen (nach Boot)
```shell
# utilman.exe Trick anwenden
```

## 7. Aufräumen (LIFO)
```shell
sudo dmsetup remove merged
sudo losetup -d /dev/loop38      # dislocker-file
sudo umount /tmp/bitlocker
sudo losetup -d /dev/loop37      # hfu.dd
sudo umount /tmp/ewf
```

