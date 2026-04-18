import csv
import matplotlib.pyplot as plt
import matplotlib
matplotlib.use('Agg')

with open('results.csv', 'r') as f:
    reader = csv.DictReader(f)
    rows = list(reader)

ciphers = [h for h in rows[0].keys() if h not in ('n', 'size_bytes')]

n_values = [int(r['n']) for r in rows]
sizes = [int(r['size_bytes']) for r in rows]

def format_size(b):
    if b < 1024: return f"{b} B"
    if b < 1024*1024: return f"{b//1024} KB"
    if b < 1024**3: return f"{b//(1024*1024)} MB"
    return f"{b//(1024**3)} GB"

size_labels = [format_size(s) for s in sizes]

colors = {
    'ChaCha20': '#1f77b4',
    'AES-256-CBC': '#ff7f0e',
    'ARC4': '#2ca02c',
    'Blowfish-CBC': '#d62728',
    'Serpent-CBC': '#9467bd',
}

# --- Combined bar chart ---
fig, ax = plt.subplots(figsize=(14, 7))
x_pos = range(len(n_values))
bar_width = 0.15

for i, cipher in enumerate(ciphers):
    times = [float(r[cipher]) for r in rows]
    offset = (i - 2) * bar_width
    bars = ax.bar([x + offset for x in x_pos], times, bar_width,
                  label=cipher, color=colors.get(cipher, None))

ax.set_xlabel('Plaintextgroesse', fontsize=12)
ax.set_ylabel('Verschluesselungsdauer (ms)', fontsize=12)
ax.set_title('Mittlere Verschluesselungsdauer bei steigender Plaintextgroesse', fontsize=13)
ax.set_xticks(list(x_pos))
ax.set_xticklabels(size_labels, rotation=45, ha='right', fontsize=8)
ax.legend(fontsize=10)
ax.set_yscale('log')
ax.grid(axis='y', alpha=0.3)
plt.tight_layout()
plt.savefig('comparison_histogram.png', dpi=200)
plt.close()

# --- Individual histograms ---
for cipher in ciphers:
    fig, ax = plt.subplots(figsize=(12, 5))
    times = [float(r[cipher]) for r in rows]
    ax.bar(x_pos, times, color=colors.get(cipher, '#333333'), width=0.6)
    ax.set_xlabel('Plaintextgroesse', fontsize=11)
    ax.set_ylabel('Verschluesselungsdauer (ms)', fontsize=11)
    ax.set_title(f'Mittlere Verschluesselungsdauer von {cipher}\nbei steigender Plaintextgroesse', fontsize=12)
    ax.set_xticks(list(x_pos))
    ax.set_xticklabels(size_labels, rotation=45, ha='right', fontsize=8)
    ax.grid(axis='y', alpha=0.3)
    plt.tight_layout()
    fname = cipher.replace('-', '_').replace(' ', '_').lower()
    plt.savefig(f'histogram_{fname}.png', dpi=200)
    plt.close()

# --- Throughput chart ---
fig, ax = plt.subplots(figsize=(14, 7))
for cipher in ciphers:
    times_ms = [float(r[cipher]) for r in rows]
    throughputs = [(s / (1024*1024)) / (t / 1000) for s, t in zip(sizes, times_ms)]
    ax.plot(n_values, throughputs, 'o-', label=cipher, color=colors.get(cipher, None), linewidth=2)

ax.set_xlabel('Plaintextgroesse (2^n Bytes)', fontsize=12)
ax.set_ylabel('Durchsatz (MB/s)', fontsize=12)
ax.set_title('Durchsatz der Verschluesselungsverfahren', fontsize=13)
ax.set_xticks(n_values)
ax.set_xticklabels(size_labels, rotation=45, ha='right', fontsize=8)
ax.legend(fontsize=10)
ax.grid(alpha=0.3)
plt.tight_layout()
plt.savefig('throughput.png', dpi=200)
plt.close()

print("Charts generated.")
