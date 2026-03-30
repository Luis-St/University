import json
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
import numpy as np

# === LFSR Configuration ===
# g(x) = x^16 + x^10 + x^6 + x^5 + 1
# Taps at positions 16, 10, 6, 5 (1-indexed)
# Initial value: (1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1)

NUM_BITS = 20000
lfsr = [1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1]  # 1-indexed: lfsr[0]=pos1
bits = []

for i in range(NUM_BITS):
    # Output is the last bit (position 16)
    output = lfsr[15]
    bits.append(output)
    # Feedback: XOR of positions 16, 10, 6, 5
    fb = (lfsr[15] + lfsr[9] + lfsr[5] + lfsr[4]) % 2
    # Shift right: move everything one position to the right
    lfsr = [fb] + lfsr[:15]

bits = np.array(bits)

print(f"First 25 bits: {''.join(map(str, bits[:25]))}")
print(f"Last 25 bits:  {''.join(map(str, bits[-25:]))}")
print()

# === Monobit Test ===
X_monobit = int(np.sum(bits))
monobit_nist = (9725, 10275)
monobit_bsi = (9654, 10346)
monobit_pass_nist = monobit_nist[0] < X_monobit < monobit_nist[1]
monobit_pass_bsi = monobit_bsi[0] < X_monobit < monobit_bsi[1]

print(f"=== Monobit Test ===")
print(f"X = {X_monobit}")
print(f"NIST FIPS140-2: ({monobit_nist[0]}, {monobit_nist[1]}) -> {'PASS' if monobit_pass_nist else 'FAIL'}")
print(f"BSI AIS 31:     ({monobit_bsi[0]}, {monobit_bsi[1]}) -> {'PASS' if monobit_pass_bsi else 'FAIL'}")

# === Poker Test ===
# Group bits into nibbles (4 bits each) -> 5000 nibbles
nibbles = []
for i in range(0, NUM_BITS, 4):
    val = bits[i] * 8 + bits[i+1] * 4 + bits[i+2] * 2 + bits[i+3]
    nibbles.append(val)

nibbles = np.array(nibbles)
nibble_counts = np.zeros(16, dtype=int)
for k in range(16):
    nibble_counts[k] = np.sum(nibbles == k)

Y_poker = (16.0 / 5000.0) * np.sum(nibble_counts ** 2) - 5000.0
poker_nist = (2.16, 46.17)
poker_bsi = (1.03, 57.4)
poker_pass_nist = poker_nist[0] < Y_poker < poker_nist[1]
poker_pass_bsi = poker_bsi[0] < Y_poker < poker_bsi[1]

print(f"\n=== Poker Test ===")
print(f"Y = {Y_poker:.4f}")
print(f"Nibble frequencies: {nibble_counts.tolist()}")
print(f"NIST FIPS140-2: ({poker_nist[0]}, {poker_nist[1]}) -> {'PASS' if poker_pass_nist else 'FAIL'}")
print(f"BSI AIS 31:     ({poker_bsi[0]}, {poker_bsi[1]}) -> {'PASS' if poker_pass_bsi else 'FAIL'}")

# === Run Test ===
# Count runs of 0s and 1s
def count_runs(bits):
    """Count runs of 0s and 1s by length."""
    zero_runs = {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0}  # 6 means >=6
    one_runs = {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0}

    current_bit = bits[0]
    current_len = 1
    max_run = 0

    for i in range(1, len(bits)):
        if bits[i] == current_bit:
            current_len += 1
        else:
            # Record the run
            k = min(current_len, 6)
            if current_bit == 0:
                zero_runs[k] += 1
            else:
                one_runs[k] += 1
            if current_len > max_run:
                max_run = current_len
            current_bit = bits[i]
            current_len = 1

    # Don't forget the last run
    k = min(current_len, 6)
    if current_bit == 0:
        zero_runs[k] += 1
    else:
        one_runs[k] += 1
    if current_len > max_run:
        max_run = current_len

    return zero_runs, one_runs, max_run

zero_runs, one_runs, max_run = count_runs(bits)

# NIST FIPS140-2 acceptance ranges
run_nist = {
    1: (2315, 2685),
    2: (1114, 1386),
    3: (527, 723),
    4: (240, 384),
    5: (103, 209),
    6: (103, 209),
}
# BSI AIS 31 acceptance ranges
run_bsi = {
    1: (2267, 2733),
    2: (1079, 1421),
    3: (502, 748),
    4: (233, 402),
    5: (90, 223),
    6: (90, 223),
}

print(f"\n=== Run Test ===")
print(f"{'Length':<10} {'zero_k':<10} {'one_k':<10} {'NIST range':<18} {'NIST':<8} {'BSI range':<18} {'BSI':<8}")
all_run_pass_nist = True
all_run_pass_bsi = True
for k in range(1, 7):
    label = f">={k}" if k == 6 else str(k)
    z = zero_runs[k]
    o = one_runs[k]
    nist_lo, nist_hi = run_nist[k]
    bsi_lo, bsi_hi = run_bsi[k]
    z_nist = nist_lo <= z <= nist_hi
    o_nist = nist_lo <= o <= nist_hi
    z_bsi = bsi_lo <= z <= bsi_hi
    o_bsi = bsi_lo <= o <= bsi_hi
    pass_nist = z_nist and o_nist
    pass_bsi = z_bsi and o_bsi
    if not pass_nist:
        all_run_pass_nist = False
    if not pass_bsi:
        all_run_pass_bsi = False
    print(f"{label:<10} {z:<10} {o:<10} [{nist_lo}, {nist_hi}]{'':>4} {'PASS' if pass_nist else 'FAIL':<8} [{bsi_lo}, {bsi_hi}]{'':>4} {'PASS' if pass_bsi else 'FAIL':<8}")

# Long Run Test
longrun_nist = max_run < 34
longrun_bsi = max_run < 31
print(f"\n=== Long Run Test ===")
print(f"Longest run: {max_run}")
print(f"NIST FIPS140-2 (< 34): {'PASS' if longrun_nist else 'FAIL'}")
print(f"BSI AIS 31     (< 31): {'PASS' if longrun_bsi else 'FAIL'}")

# === Generate Plots ===

# 1. Nibble frequency histogram
fig, ax = plt.subplots(figsize=(10, 5))
bars = ax.bar(range(16), nibble_counts, color="#4682B4", edgecolor="black")
ax.set_xlabel("Nibble-Wert (hexadezimal)")
ax.set_ylabel("Häufigkeit")
ax.set_title("Häufigkeitsverteilung der Nibble-Werte (0-F)")
ax.set_xticks(range(16))
ax.set_xticklabels([f"{i:X}" for i in range(16)])
ax.axhline(y=5000/16, color="red", linestyle="--", label=f"Erwartungswert ({5000/16:.1f})")
ax.legend()
for bar, count in zip(bars, nibble_counts):
    ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 3, str(count),
            ha="center", va="bottom", fontsize=8)
plt.tight_layout()
plt.savefig("nibble_histogram.png", dpi=150)
plt.close()

# 2. Run length distribution
fig, ax = plt.subplots(figsize=(10, 5))
x_labels = ["1", "2", "3", "4", "5", ">=6"]
x_pos = np.arange(len(x_labels))
width = 0.35
bars_zero = ax.bar(x_pos - width/2, [zero_runs[k] for k in range(1, 7)], width, label="0-Runs", color="#4682B4", edgecolor="black")
bars_one = ax.bar(x_pos + width/2, [one_runs[k] for k in range(1, 7)], width, label="1-Runs", color="#CD5C5C", edgecolor="black")
ax.set_xlabel("Run-Länge")
ax.set_ylabel("Häufigkeit")
ax.set_title("Häufigkeitsverteilung der Runs")
ax.set_xticks(x_pos)
ax.set_xticklabels(x_labels)
ax.legend()
for bar in bars_zero:
    ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 3, str(int(bar.get_height())),
            ha="center", va="bottom", fontsize=8)
for bar in bars_one:
    ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 3, str(int(bar.get_height())),
            ha="center", va="bottom", fontsize=8)
plt.tight_layout()
plt.savefig("run_histogram.png", dpi=150)
plt.close()

# === Save results as JSON for LaTeX ===
results = {
    "monobit": {
        "X": X_monobit,
        "nist_pass": bool(monobit_pass_nist),
        "bsi_pass": bool(monobit_pass_bsi),
    },
    "poker": {
        "Y": round(float(Y_poker), 4),
        "nibble_counts": nibble_counts.tolist(),
        "nist_pass": bool(poker_pass_nist),
        "bsi_pass": bool(poker_pass_bsi),
    },
    "runs": {
        "zero_runs": {str(k): zero_runs[k] for k in range(1, 7)},
        "one_runs": {str(k): one_runs[k] for k in range(1, 7)},
        "all_nist_pass": bool(all_run_pass_nist),
        "all_bsi_pass": bool(all_run_pass_bsi),
    },
    "longrun": {
        "max_run": max_run,
        "nist_pass": bool(longrun_nist),
        "bsi_pass": bool(longrun_bsi),
    },
}

with open("results.json", "w") as f:
    json.dump(results, f, indent=2)

print("\nPlots saved: nibble_histogram.png, run_histogram.png")
print("Results saved: results.json")
