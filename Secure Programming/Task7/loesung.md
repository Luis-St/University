# Praktikumsaufgabe 07 – Lösungsdokumentation

## 1. `abc_term` – Programmablauf mit gdb ermittelt

Disassemblierung von `main` mit `gdb -batch -ex "disass main" abc_term`:

```
movb $0x41, -0x1(%rbp)   ; a = 'A'  (ASCII 65)
add  $0x3,  %eax          ; b = a + 3 = 'D'  (ASCII 68)
add  $0xf,  %eax          ; c = b + 15 = 'S' (ASCII 83)
call printf               ; printf("c=%c\n", c)
```

Das Programm setzt `a = 'A'`, berechnet `b = a + 3 = 'D'` und `c = b + 15 = 'S'` und gibt `c=S` aus.
Es demonstriert Zeichenarithmetik: Buchstaben werden als ASCII-Ganzzahlen behandelt und addiert.

---

## 2. `ausgabe_vektor` – Fehleranalyse

### Fehler

**Fehler 1 – Schleife startet bei `i = 1` statt `i = 0`**

In der Disassemblierung:
```
movl $0x1, -0x14(%rbp)   ; i = 1  ← sollte 0 sein
cmpl $0x3, -0x14(%rbp)   ; i <= 3
```
Dadurch wird `v[0]` nie ausgegeben.
Bei `i = 3` wird außerdem eine lokale Variable außerhalb von `v` gelesen (undefined behaviour).

**Fehler 2 – Formatstring mit hardcodiertem Index**

```
(gdb) x/s 0x5555555552b8
"v[0]=%d\n"
```
Der Index steht fest als `0` im Formatstring — der Schleifenzähler `i` wird nicht als Indexargument übergeben.
Alle drei Zeilen drucken dadurch `v[0]=...`.

### Korrektur

- Schleife von `i = 0` bis `i < n`
- Formatstring `"v[%d]=%d\n"` mit `i` als zusätzlichem Argument

---

## 3. `findmax` – Fehleranalyse

### Fehler

**Fehler 1 – `find_max` wird in `main` nie aufgerufen**

Disassemblierung von `main`:
```
mov $0x7fffffff, %esi   ; INT_MAX direkt als Argument
call printf             ; printf("Max: %d\n", INT_MAX)
ret
```
`main` ruft `find_max` nicht auf, sondern übergibt `INT_MAX` (0x7fffffff) fix an `printf`.

**Fehler 2 – `max` in `find_max` nicht initialisiert**

```asm
xor %edx, %edx          ; i = 0
; eax (max) nie gesetzt!
mov (%rdi,%rdx,4), %esi ; esi = v[i]
cmp %eax, %esi
cmovg %esi, %eax        ; if v[i] > max → max = v[i]
```
`eax` enthält vor der Schleife einen zufälligen Wert — der erste Vergleich ist undefiniert.

### Korrektur

- `find_max`: `max = v[0]` als Startwert, Schleife ab `i = 1`
- `main`: Vektor definieren, `find_max(v, n)` aufrufen, Ergebnis ausgeben
