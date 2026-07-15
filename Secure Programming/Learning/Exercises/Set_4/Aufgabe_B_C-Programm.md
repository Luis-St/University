# Aufgabe B – C-Programm erstellen

Erstellen Sie eine C-Funktion mit der Signatur `char *getBlock(int fd)`, die einen Speicherblock fester Größe dynamisch reserviert, diesen mittels `read` von dem übergebenen Filedescriptor `fd` befüllt und den Block zurückgibt.

Tritt ein Fehler auf, soll die Funktion `NULL` zurückgeben. Achten Sie darauf, dass in keinem Ausführungspfad reservierter Speicher verloren geht.
