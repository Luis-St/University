import csv
from collections import defaultdict

import matplotlib
matplotlib.use('Agg')
import matplotlib.pyplot as plt

# --- Daten einlesen (Langformat) ---
data = defaultdict(list)          # (type, k) -> [times_us]
bitlen = {}                       # (type, k) -> bitlen
ks = []
with open('results.csv') as f:
    for row in csv.DictReader(f):
        k = int(row['k'])
        t = row['type']
        data[(t, k)].append(float(row['time_us']))
        bitlen[(t, k)] = int(row['bitlen'])
        if k not in ks:
            ks.append(k)
ks.sort()

EC_COLOR = '#1f77b4'
GF_COLOR = '#d62728'


def boxplot(kind, color, xlabel, title, fname):
    fig, ax = plt.subplots(figsize=(11, 6))
    series = [data[(kind, k)] for k in ks]
    labels = [f"{bitlen[(kind, k)]}\n(k={k})" for k in ks]
    bp = ax.boxplot(series, showfliers=True, patch_artist=True,
                    flierprops=dict(marker='.', markersize=3, alpha=0.4))
    for box in bp['boxes']:
        box.set(facecolor=color, alpha=0.5)
    for med in bp['medians']:
        med.set(color='black', linewidth=1.5)
    ax.set_yscale('log')
    ax.set_xticklabels(labels, fontsize=9)
    ax.set_xlabel(xlabel, fontsize=12)
    ax.set_ylabel('Zeit pro Operation in us (logarithmisch)', fontsize=12)
    ax.set_title(title, fontsize=13)
    ax.grid(axis='y', alpha=0.3, which='both')
    plt.tight_layout()
    plt.savefig(fname, dpi=200)
    plt.close()


boxplot('EC', EC_COLOR,
        'Bitlaenge n des Koerpers (Sicherheitsniveau k)',
        'Laufzeiten der skalaren Multiplikation [a]G auf einer ell. Kurve ueber GF(p)',
        'boxplot_ec.png')

boxplot('GF', GF_COLOR,
        'Bitlaenge N der Primzahl (Sicherheitsniveau k)',
        'Laufzeiten der modularen Exponentiation G^a mod p im endlichen Koerper',
        'boxplot_gf.png')


# --- Vergleich der Mediane ueber das Sicherheitsniveau ---
def median(xs):
    s = sorted(xs)
    m = len(s) // 2
    return (s[m - 1] + s[m]) / 2 if len(s) % 2 == 0 else s[m]


ec_med = [median(data[('EC', k)]) for k in ks]
gf_med = [median(data[('GF', k)]) for k in ks]

fig, ax = plt.subplots(figsize=(11, 6))
ax.plot(ks, ec_med, 'o-', color=EC_COLOR, linewidth=2, label='ECDLP: [a]G  (n Bit)')
ax.plot(ks, gf_med, 's-', color=GF_COLOR, linewidth=2, label='DLP GF(p): G^a mod p  (N Bit)')
ax.set_yscale('log')
ax.set_xlabel('Sicherheitsniveau k in Bit (BSI TR-02102-1)', fontsize=12)
ax.set_ylabel('Median-Laufzeit pro Operation in us (logarithmisch)', fontsize=12)
ax.set_title('Performance-Vergleich DLP - ECDLP bei gleichem Sicherheitsniveau', fontsize=13)
ax.set_xticks(ks)
ax.grid(alpha=0.3, which='both')
ax.legend(fontsize=11)
plt.tight_layout()
plt.savefig('comparison_median.png', dpi=200)
plt.close()


# --- Speedup-Faktor GF(p) / EC ---
speedup = [g / e for g, e in zip(gf_med, ec_med)]

fig, ax = plt.subplots(figsize=(11, 6))
bars = ax.bar([str(k) for k in ks], speedup, color=EC_COLOR, alpha=0.7)
ax.axhline(1.0, color='black', linewidth=1, linestyle='--')
ax.set_yscale('log')
ax.set_xlabel('Sicherheitsniveau k in Bit', fontsize=12)
ax.set_ylabel('Faktor  t(GF(p)) / t(EC)  (logarithmisch)', fontsize=12)
ax.set_title('Geschwindigkeitsvorteil der EC-Arithmetik (>1 = EC schneller)', fontsize=13)
for bar, s in zip(bars, speedup):
    ax.text(bar.get_x() + bar.get_width() / 2, bar.get_height(),
            f'{s:.1f}x', ha='center', va='bottom', fontsize=8)
ax.grid(axis='y', alpha=0.3, which='both')
plt.tight_layout()
plt.savefig('speedup.png', dpi=200)
plt.close()


# --- Median-Tabelle fuer den LaTeX-Bericht ausgeben ---
print(f"{'k':>5} {'n':>5} {'N':>6} {'EC us':>12} {'GF us':>14} {'Faktor':>10}")
for i, k in enumerate(ks):
    print(f"{k:>5} {bitlen[('EC', k)]:>5} {bitlen[('GF', k)]:>6} "
          f"{ec_med[i]:>12.2f} {gf_med[i]:>14.2f} {speedup[i]:>9.1f}x")

print("\nCharts: boxplot_ec.png, boxplot_gf.png, comparison_median.png, speedup.png")
