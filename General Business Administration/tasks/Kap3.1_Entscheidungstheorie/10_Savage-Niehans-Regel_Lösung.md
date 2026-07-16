# Savage-Niehans-Regel (Minimax-Regret) — Lösung

> **Quelle:** Foliensatz 5, Seite 31–33 — `Script/Nr05_BWL_Kap_3_Grundl_Entscheidungstheorie_v8.pdf`

## Lösungsweg
**Schritt 1 — Spaltenmaxima bestimmen:**

| | u1 | u2 | u3 |
|---|---|---|---|
| Spaltenmaximum | 20 | 35 | 30 |

**Schritt 2 & 3 — Regret-Matrix (Spaltenmaximum − Wert) und maximales Risiko je Zeile:**

| Alt. | u1 | u2 | u3 | max. Risiko |
|---|---|---|---|---|
| a1 | 20−18 = 2 | 35−35 = 0 | 30−5 = 25 | 25 |
| a2 | 20−20 = 0 | 35−14 = 21 | 30−25 = 5 | 21 |
| a3 | 20−12 = 8 | 35−15 = 20 | 30−30 = 0 | 20 |

**Schritt 4 — kleinstes maximales Risiko:** min(25; 21; 20) = 20 → a3.

## Ergebnis
Nach der Savage-Niehans-Regel wird **a3** gewählt (kleinstes maximales Risiko = 20).
