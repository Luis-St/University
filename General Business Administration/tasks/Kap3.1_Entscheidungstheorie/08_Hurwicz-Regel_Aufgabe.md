# Hurwicz-Regel (λ = 0,3)

> **Quelle:** Foliensatz 5, Seite 26–28 — `Script/Nr05_BWL_Kap_3_Grundl_Entscheidungstheorie_v8.pdf`
> **Thema:** Kapitel 3.1 Grundlagen der Entscheidungstheorie — Hurwicz-Regel (Entscheidung unter Unsicherheit)

## Aufgabenstellung
Gegeben ist die folgende Ergebnismatrix:

| Alternative | u1 | u2 | u3 |
|---|---|---|---|
| a1 | 18 | 35 | 5 |
| a2 | 20 | 14 | 25 |
| a3 | 12 | 15 | 30 |

Für welche Alternative entscheidet sich der Akteur nach der Hurwicz-Regel bei einem Optimismusparameter λ = 0,3? Nennen Sie zudem das zentrale Problem der Regel.

## Benötigte Formeln
**Hurwicz-Regel (Pessimismus-Optimismus-Regel):** mit Optimismusparameter λ (0 ≤ λ ≤ 1):

$$H(a_i) = \lambda \cdot \max_j E_{ij} + (1 - \lambda) \cdot \min_j E_{ij}$$

- λ = 0 → Maximin-Regel
- λ = 1 → Maximax-Regel

Wähle die Alternative mit der höchsten gewichteten Summe.

---
👉 Lösung: [`08_Hurwicz-Regel_Lösung.md`](08_Hurwicz-Regel_Lösung.md)
