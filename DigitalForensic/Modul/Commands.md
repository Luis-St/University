Overall-Befehle:
----------------------------------------------------------------
*Dell3.E0** -> WINDOWS

1. Lade die 4n6.ova herunter und starte sie, stelle 4 kerne ein
2. Prüfe ob sf_tmp verfügbar ist mit den Dateien.
3. root console öffnen
4. apt purge xmount
5. apt install xmount
6. mkdir /ewf
7. cd /media/sf_tmp
8. xmount --in ewf dell3.E0* --cache /tmp/dell3.ovl --out raw /ewf
9. cd /ewf
10. mmls dell3.dd
11. losetup --partscan --find --show /ewf/dell3.dd
12. ll /dev/loop*
13. fsstat /dev/loop0p1 | head -> Zeigt Versionsnummer und FileSystem an
14. fls -pro 63 dell3.dd
15. icat -o 63 dell3.dd 3664 > /tmp/boot.ini
16. cd /tmp
17. cat boot.ini ->> Zeigt genaue Windows Version = XP Professional
18. cd /media/sf_tmp
19. istat -o /dev/loop0p1 dell3.E0? 3664 --> zeigt timestamps an

-------------------------------------------------------------------
*i9300.E01* --> Aktuelles Android System

1. cd /media/sf_tmp
2. mmls i9300.E01 (Prüfen ob speicherzugriffsfehler, falls zugriffsfehler, dann xmount)
   2.1 cd /~
   2.2 mkdir /ewf
3. xmount --in ewf i9300.E01 --cache /tmp/i9300.ovl --out raw /ewf (erstellt dd durch RAW)
   3.1 mmls /ewf/i9300.dd
4. losetup --partscan --find --show /ewf/i9300.E01.dd  (looped alle Partitionen autom.)
5. ll /dev/loop* -> Zeigt alle bestehenden Loops
6. fls /dev/loop1p12 --> Zeigt das Dateisystem der 12ten Partition und deren inodes
7. fls -pr /dev/loop1p12 | grep ’\.db$' --> Zeigt alle db(datenbanken) an
8. fsstat /dev/loop1p12 | head ---> Zeigt System Typ, und bearbeitungszeitstempel an
9. fls -r /dev/loop1p12 | grep ’*' | wc -l ---> Zeigt an wie viel unallocated dat. da sind

- edit: Mann kann auch einzelne Loops erzeugen, auf eine bestimmte partiition mit:
  -> losetup -o $((6586368*512)) /dev/loop0 /ewf/i9300.dd -_> die 6586368 ist der Startwert der zu loopenden Partition und die 512 ist die angegebene Sektorgröße(mmls)

10. mmls /ewf/i9300.dd -> Zeigt Partitionen an
11. xmount --in ewf /i9300.dd --cache /tmp/i9300.ovl --out raw /ewf
12. losetup --partscan --show --find /ewf/i9300.dd
13. fsstat /dev/loop0p5 | head --> Gibt informationen ob es ein FLS gibt oder ähnliches
    --> Possible encryption detected (High Entropy (7.71))
14. binwalk /dev/loop0p5 ---> Gibt uns mehr informationen über die Partition ohne FLS
    -> Hier Ist der Kernel vorhanden. Die Information über SIZE ist wichtig
15. dd if=/dev/loop0p5 of=/tmp/extracted_kernel bs=1 count=49000256
    -> Hierfür wichtig: count = SIZE aus Schritt 14
16. file /tmp/extracted_kernel --> IMMER den 'file'-befehl nach erstellen eines files!!

----------------------------------------------------------------
*Nexus.Nandump* -> altes Android-System

1. cd /media/sf_tmp
2. mmls nexus.nandump --> Da passiert nichts, also prüfen mit fsstat
3. fsstat nexus.nandump --> Zeigt uns, dass es ein YAFFS-FLS ist
4. fls -pr nexus.nandump | grep '\.db$' | grep -v '*'
   --> Zeigt uns alle Datenbanken(.db) und durch -v = alle nicht gelöschten
5. Suche in der Ausgabe nach einer intressanten Datenbank und deren inode, in unserem Fall Geolocation mot der inode 524
6. icat nexus.nandump 524 > /tmp/CachedPosition.db --> Erstellen der auslesbaren Dateien
7. file /tmp/CachedPosition.db -> immer prüfen, ob datei erstellt wurde
8. cd /~
9. apt install sqlitebrowser -> Browser den wir zum auslesen brauchen
10. cd /tmp
11. sqlitebrowser CachedPosition.db -> Öffnet den sqlitebrowser
12. Refreshen (2 grüne pfeile) -> GeoDATEN/Koordinaten werden angezeigt.

----------------------------------------------------------------
*Windows: vss.E0** Registry und Hives (Registry stehen in Configs meistens)
-> Es gibt 2 Rubriken, einmal HKLM mit SAM;SYSTEM;SOFTWARE und HKCU mit NTUSER.DAT

1. cd /media/sf_tmp xmount --in ewf vss.E0* --cache /tmp/vss.ovl --out raw /ewf
2. mmls /ewf/vss.dd -> Zeigt auch Größe der Partitionen
3. losetup --partscan --find --show /ewf/vss.dd
4. ll /dev/loop1* -> Wir schauen welche Partitionen es gibt und starten mit größten
5. fls /dev/loop1p2
6. fls -pr /dev/loop1p2 | grep $SAM --> Zeigt uns alles aus SAM_Registry -> CONFIG suchen und dessen INODE(hier 59953) nutzen
7. cd /ewf
8. icat /dev/loop1p2 59953 > /tmp/SAM
9. file /tmp/SAM
10. fls -pr /dev/loop1p2 | grep SYSTEM$
11. icat /dev/loop1p2 59959 > /tmp/SYSTEM
12. file /tmp/SYSTEM
13. fls -pr /dev/loop1p2 | grep SOFTWARE$
14. icat /dev/loop1p2 59957 > /tmp/SOFTWARE
15. file /tmp/SOFTWARE
16. fls -pr /dev/loop1p2 | grep NTUSER.DAT$ --> Hier suchen wir nach Usern(z.B. agumba)
17. icat /dev/loop1p2 472 > /tmp/NTUSER.DAT
18. file /tmp/NTUSER.DAT

-----
*REGRIPPER* -> wird benötigt zum auslesen von Registry. Hierfür sind winver, USB und NIC

19. cd /~
20. apt install regripper -y
21. cd /tmp --> in das Verzeichnis, in welches wir die files geschrieben haben
22. regripper -l | grep -A winver -r SOFTWARE -> Zeigt HIVE+ID zu winver an
23. regripper -p winver -r SOFTWARE --> Genaue Windows-version-Infos
    -->  (bei 11 steht auch 10 da windows nicht mehr die versionsnummer updated)
24. regripper -l | grep -B 1 USB --> Zeigt USB-Plugins mit deren HIVE+ID
25. regripper -p usbdevices -r SYSTEM
    25.1 regripper -p devclass -r SYSTEM
    25.2 regripper -p usbtor -r SYSTEM --> Zeigt uns TIMESTAMPS und NAME
26. regripper -l | grep -B 1 NIC --> Suche nach Router(NIC = NETWORK INTERFACE), HIVE
27. regripper -p nic2 -r SYSTEM

-----
*PREFETCH* -> Spurensuche in Programmen die Prefetch haben

28. fls -pr /dev/loop1p2 | grep -i prefetch
    ->Liste mit Programmen die Prefetch haben, wir wollen OUTLOOK.EXE = ID in der Liste
29. mount /dev/loop1p2 /mnt --> Zeigt dass es definitiv Win SYS ist
30. cd /mnt
31. ll
32. find . -type f -iname "*.pf" -print0 | xargs -0 ls --> Zeigt uns alle .pf dateien
    32.1 find . -type f -iname "*.pf" -print0 | xargs -0 md5sum -> Zeigt Prüfsummen
    32.2 find . -type f -iname "*.pf" -print0 | xargs -0 cp --target-directory=/tmp/prefetch --parents (bester befehl zum kopieren mit absoluter FileStruktur)
    33 find . -type f -iname "*.jpg" -print0 | xargs -0 ls -> Zeigt uns Bilder
34. sccainfo OUTLOOK.EXE-6C287DCC.pf -> Infos über: Run Count, Last-Runtime etc.
    --> Hier kann man die verschiedenen .pf dateien auslesen.

----------------------------------------------
*MBR_Bootloader* auslesen - bash.history finden!

1. cd /media/sf_tmp
2. mmls mbr.E01 --> Hier schauen wir auf den Startwert der Partition (hier 996030)
3. fls -ro 996030 mbr.E01 | grep bash_history
   --> Zeigt uns inode zu der gesuchten bash.history, hier 2424837
4. icat -o 996030 mbr.E01 2424837 > /tmp/mbr.EO1 ----> erstellt kopie
5. file /tmp/mbr.E01
6. xmount --in ewf mbr.E01 --cache /tmp/mbr.ovl --out raw /ewf
7. losetup --partscan --find --show /ewf/mbr.dd
8. ll /dev/loop* (nutze den loop den es gesetzt hat)

----------------------------------------------------------------
Aufräumen des PC nach LiFo-Prinzip, (last in First out):

1. umount /mnt
2. losetup -D
3. umount /ewf
