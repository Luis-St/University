# Formelsammlung – Allgemeine BWL

## Einführung und Überblick (Kenngrößen)

**Produktivität** (mengenmäßig):
$$P = \dfrac{\text{Leistung [ME]}}{\text{Input [ME]}}$$

**Wirtschaftlichkeit** (wertmäßig):
$$W = \dfrac{\text{Erlöse [GE]}}{\text{Kosten [GE]}}$$

**Umsatzrentabilität:**
$$\text{Umsatzrentabilität} = \dfrac{\text{Gewinn}}{\text{Umsatzerlöse}} \cdot 100\,[\%]$$

**Eigenkapitalrentabilität:**
$$\text{Eigenkapitalrentabilität} = \dfrac{\text{Gewinn}}{\text{Eigenkapital}} \cdot 100\,[\%]$$

---

## Gründung von Unternehmen

### Center-of-Gravity-Modell (CoG) – gewichteter Schwerpunkt

$$a^{*} = \frac{\sum_{i=1}^{n} (a_i \cdot w_i)}{\sum_{i=1}^{n} w_i} \qquad b^{*} = \frac{\sum_{i=1}^{n} (b_i \cdot w_i)}{\sum_{i=1}^{n} w_i}$$

| Symbol | Bedeutung |
|---|---|
| $a_i,\ b_i$ | Koordinaten des Nachfragezentrums $i$ |
| $w_i$ | Gewicht von $i$ (Frachtmenge/Ladeeinheiten) |
| $n$ | Anzahl der Nachfragezentren |
| $a^{*},\ b^{*}$ | Koordinaten des gesuchten Standorts |

### OHG-Gewinnverteilung

$$V_i = 0{,}04 \cdot K_i \qquad G_{\mathrm{Rest}} = G - \sum_{i=1}^{n} V_i \qquad G_i = V_i + \dfrac{G_{\mathrm{Rest}}}{n}$$

| Symbol | Bedeutung |
|---|---|
| $G$ | Gesamtgewinn |
| $K_i$ | Kapitalanteil Gesellschafter $i$ |
| $V_i$ | Vorabverzinsung Gesellschafter $i$ |
| $n$ | Anzahl Gesellschafter |
| $G_i$ | Gewinnanteil Gesellschafter $i$ |

### KG-Gewinnverteilung

$$V_i = T_i + z_i \cdot K_i \qquad G_{\mathrm{Rest}} = G - \sum_{i=1}^{n} V_i \qquad G_i = V_i + G_{\mathrm{Rest}} \cdot \dfrac{q_i}{\sum_{j=1}^{n} q_j}$$

| Symbol | Bedeutung |
|---|---|
| $T_i$ | Tätigkeits-/Vorausvergütung Gesellschafter $i$ |
| $z_i$ | Verzinsungssatz |
| $K_i$ | Kapitalanteil Gesellschafter $i$ |
| $q_i$ | fester Verteilungsschlüssel Gesellschafter $i$ |

---

## Entscheidungstheorie

| Regel | Formel |
|---|---|
| **Bayes (Erwartungswert)** | $\mu = \sum (p_i \cdot E_i)$ |
| **Maximin (Wald)** | $\max_i(\min_j E_{ij})$ |
| **Maximax** | $\max_i(\max_j E_{ij})$ |
| **Minimin** (Schadensmatrix) | $\min_i(\min_j E_{ij})$ |
| **Hurwicz** | $H = \lambda \cdot \max + (1-\lambda) \cdot \min$ |
| **Laplace** | $\bar{E} = \frac{1}{n} \cdot \sum E_{ij}$ |
| **Savage-Niehans (Minimax-Regret)** | $\text{Regret}_{ij} = \text{Spaltenmax}_j - E_{ij}$; wähle $\min_i(\max_j \text{Regret}_{ij})$ |

---

## Personal und Organisation

### Nettopersonalbedarf

$$\text{Nettopersonalbedarf} = \text{Bruttopersonalbedarf} - \text{Personalbestand} + \text{Personalabgänge} - \text{feststehende Personalzugänge}$$

### Personalzuordnungsmodell (LP)

Maximiere die Eignungssumme:

$$\sum_{i=1}^{n} \sum_{j=1}^{n} e_{ij} \cdot z_{ij} \;\to\; \max$$

unter den Nebenbedingungen:

$$\sum_{i=1}^{n} z_{ij} = 1 \qquad \sum_{j=1}^{n} z_{ij} = 1 \qquad z_{ij} \in \{0;1\}$$

---

## Beschaffung

### ABC-Analyse

$$\text{Anteil}_i = \frac{\text{Volumen}_i}{\text{Gesamtvolumen}} \cdot 100\,\%, \qquad \text{Volumen}_i = \text{Menge}_i \times \text{Preis}_i$$

### Scoring-Methode / Nutzwertanalyse

$$\text{Gesamtnutzwert} = \sum (\text{Gewicht} \times \text{Bewertung})$$

### Kostenvergleichsrechnung (KVR)

$$K = B + \dfrac{A}{t} + \dfrac{A}{2} \cdot i \qquad\text{(ohne Restwert)}$$
$$K = B + \dfrac{A - R}{t} + i \cdot \dfrac{A + R}{2} \qquad\text{(mit Restwert)}$$
$$k = \dfrac{K}{x} \quad \text{(Stückkosten)}$$

| Symbol | Bedeutung |
|---|---|
| $B$ | Betriebskosten |
| $A$ | Anschaffungskosten |
| $t$ | Nutzungsdauer |
| $i$ | Kalkulationszins |
| $R$ | Restwert |
| $x$ | Mengenoutput |

### Optimale Bestellmenge – Andler-Formel (EOQ)

$$h = \alpha \cdot c$$

$$K_{\mathrm{L}} = \dfrac{q}{2} \cdot h \qquad K_{\mathrm{B}} = \dfrac{r}{q} \cdot s \qquad K_{\mathrm{ges}} = \dfrac{q}{2} \cdot h + \dfrac{r}{q} \cdot s$$

**Optimum** (Lagerkosten = Bestellkosten):

$$q^{*} = \sqrt{\dfrac{2 \cdot r \cdot s}{h}}$$

$$T = \dfrac{q}{r} \qquad T^{*} = \dfrac{q^{*}}{r} \quad \text{(Reichweite)}$$

$$\text{Bestellmengenwert} = c \cdot q^{*} \qquad \text{Jahresumsatz } U = c \cdot r$$

| Symbol | Bedeutung |
|---|---|
| $r$ | Bedarf [ME/Jahr] |
| $h$ | spez. Lagerkosten [EUR/(ME·Jahr)], $h = \alpha \cdot c$ |
| $s$ | spez. Bestellkosten [EUR/Bestellung] |
| $q,\ q^{*}$ | Bestellmenge, optimale Bestellmenge |
| $c$ | Einkaufspreis [EUR/ME] |
| $\alpha$ | Lagerzins [%/Jahr] |

### Lagerbestandsformeln

$$\varnothing\,\text{Arbeitsbestand} = \dfrac{q}{2}$$
$$\varnothing\,\text{Gesamtbestand} = \varnothing\,\text{Arbeitsbestand} + \varnothing\,\text{Sicherheitsbestand}$$
$$\varnothing\,\text{Bestand (Prozess)} = \varnothing\,\text{Flussstärke} \cdot \Delta t$$
$$\text{Buchbestand} = \text{Inventurbestand} + \text{Zugänge} - \text{Abgänge}$$
$$\text{Verfügbarer Bestand} = \text{physischer Bestand} + \text{Bestellbestand} - \text{reservierter Bestand}$$

---

## Produktionswirtschaft

### Leontief-Produktionsfunktion (limitational)

**Faktorfunktionen:**
$$r_i = a_i \cdot x \,;\quad a_i = \text{const.} > 0,\quad i = 1,\dots,I$$

**Produktfunktion:**
$$x = \dfrac{r_i}{a_i},\quad \text{mit}\ \dfrac{r_i}{a_i} = \dfrac{r_{\hat\imath}}{a_{\hat\imath}}\ \text{für alle } i,\hat\imath$$

**Maximal herstellbare Menge (Minimumfunktion) – Engpassfaktor:**
$$x = \min\left\{ \dfrac{\bar r_i}{a_i},\; i = 1,\dots,I \right\}$$

**Grenzaufwand:**
$$\dfrac{\partial r_i}{\partial x} = a_i$$

**Grenzproduktivität** (nur wenn $i$ alleiniger Engpass, sonst 0):
$$\dfrac{1}{a_i} = \dfrac{x}{r_i}$$

**Steigung des Prozessstrahls:**
$$\dfrac{a_2}{a_1}$$

### Gleitender Mittelwert (Absatzprognose, PPS)

$$F_{t+1} = \dfrac{1}{n}\left(A_t + A_{t-1} + \dots + A_{t-n+1}\right)$$

mit  $$A^{*}_{t+1} = A^{*}_{t+2} = \dots = F_{t+1}$$

---

## Absatzwirtschaft

### Marktanteil

$$\text{Marktanteil} = \dfrac{\text{Absatzvolumen}}{\text{Marktvolumen}}$$

($\text{Marktpotential} \ge \text{Marktvolumen} \ge \text{Absatzvolumen}$)

### Gewinnschwellen-/Break-Even-Analyse

**Gewinnfunktion:**
$$G = E - K = p \cdot x - (K_{\mathrm{f}} + k_{\mathrm{v}} \cdot x) = (p - k_{\mathrm{v}}) \cdot x - K_{\mathrm{f}}$$

**Break-Even-Menge** (Gewinnschwelle, $G=0$):
$$x_{\mathrm{BEP}} = \dfrac{K_{\mathrm{f}}}{p - k_{\mathrm{v}}}$$

| Symbol | Bedeutung |
|---|---|
| $p$ | Preis |
| $k_{\mathrm{v}}$ | variable Stückkosten |
| $K_{\mathrm{f}}$ | Fixkosten |
| $x$ | Absatzmenge |
| $E$ | Erlös ($=p \cdot x$) |
| $(p-k_{\mathrm v})$ | Stückdeckungsbeitrag |

**Kritische Preisschwelle**:
$$\text{krit. Preis} = k_{\mathrm{v}} + \dfrac{K_{\mathrm{f}}}{x}$$

**Maximaler Preisnachlass**:
$$\text{Preisnachlass}_{\max} = \dfrac{G}{x}$$
