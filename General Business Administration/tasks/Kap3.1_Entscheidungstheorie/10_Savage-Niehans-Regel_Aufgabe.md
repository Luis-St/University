# Savage-Niehans-Regel (Minimax-Regret)

> **Quelle:** Foliensatz 5, Seite 31–33 — `Script/Nr05_BWL_Kap_3_Grundl_Entscheidungstheorie_v8.pdf`
> **Thema:** Kapitel 3.1 Grundlagen der Entscheidungstheorie — Savage-Niehans-Regel (Entscheidung unter Unsicherheit)

## Aufgabenstellung
Gegeben ist die folgende Ergebnismatrix:

| Alternative | u1 | u2 | u3 |
|---|---|---|---|
| a1 | 18 | 35 | 5 |
| a2 | 20 | 14 | 25 |
| a3 | 12 | 15 | 30 |

Für welche Alternative entscheidet sich der Akteur nach der Savage-Niehans-Regel?

## Benötigte Formeln
**Savage-Niehans-Regel (Minimax-Regret):** Minimiere den maximal möglichen Opportunitätsverlust (Regret). Schritte:

1. Spaltenmaxima bestimmen.
2. Regret-Matrix bilden: Regret = Spaltenmaximum − Ergebniswert.
3. Je Alternative das Zeilenmaximum der Regret-Matrix bestimmen (= maximales Risiko).
4. Wähle die Alternative mit dem kleinsten maximalen Risiko.

---
👉 Lösung: [`10_Savage-Niehans-Regel_Lösung.md`](10_Savage-Niehans-Regel_Lösung.md)
