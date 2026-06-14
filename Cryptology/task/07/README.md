# Aufgabe 7 – Toy-Blockchain (SHA256 Proof of Work)

Ziel: 3 verkettete SHA256-Blöcke, deren Hashes je **k=10** führende Null-Nibbles haben → **10 Punkte**.

## Dateien
| Datei | Zweck |
|-------|-------|
| `miner.cu` | CUDA-GPU-Miner (findet die Nonces). **Hauptprogramm.** |
| `reference.c` | CPU-Referenz/Verifier, identische Nachrichten-Konstruktion |
| `Dokumentation.tex` | Abgabe-Dokument (→ PDF) |
| `k10_result.txt` | Ergebnis des k=10-Laufs (IV/TX/NONCE/HASH je Block) |

## Bauen & Laufen (GPU, z.B. RTX 4090)
```bash
nvcc -O3 -arch=sm_89 miner.cu -o miner   # sm_89 = Ada / RTX 40xx
./miner            # nutzt K=10 aus der Config
./miner 8          # optional: anderes K per Argument (Test)
```
Konfiguration oben in `miner.cu`: `MATRIKELNUMMER`, `NAME`, `AMOUNTS`, `K_NIBBLES`.

## Wie es funktioniert (Kurz)
- Gehashte Nachricht: `IV(64hex) + TX + "(8)" + NONCE(32hex)`, alles ASCII.
- `TX = (1)TxNr (2)SHA1(Name) (3)Unixzeit (4)SHA1(Empfänger) (5)Betrag (6)Länge (7)`
- Kette: `IV` von Block 1 = ALLZERO, danach `IV_n = SHA256(Block_{n-1})`.
- Mining = Nonce variieren bis `k` führende Hex-Nullen (~`16^k` Versuche/Block).
- Optimierung: konstante Blöcke per **Midstate** auf der CPU vorgehasht → GPU rechnet nur den letzten Block.

## Verifikation
- Der Miner rechnet jeden Hash auf der CPU (volles SHA256) nach.
- Zusätzlich geprüft mit `python3 -c "import hashlib; ..."` und in **CrypTool2** (identisch).

## „Schummeln"?
SHA256 lässt sich nicht brechen – es gibt keine Abkürzung zu führenden Nullen.
Der einzige (legitime) Hebel ist **roher Durchsatz**: offline auf der GPU minen statt in CrypTool2,
mit Midstate-Trick. So sind statt k=6 (Musterbeispiel) problemlos k=10+ drin.
