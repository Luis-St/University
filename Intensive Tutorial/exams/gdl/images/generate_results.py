#!/usr/bin/env python3
"""
Erzeugt die Zielbilder (Loesungsbilder) fuer die GdL-Probeklausuren.
Konvention wie in der Klausur: p[x][y], x horizontal, y vertikal, Ursprung links oben.
PIL nutzt dieselbe (x, y)-Reihenfolge in getpixel/putpixel.
"""
from PIL import Image
import os

HERE = os.path.dirname(os.path.abspath(__file__))
SRC = os.path.join(HERE, "MyPicture.jpg")
BLACK = (0, 0, 0)


def load():
    return Image.open(SRC).convert("RGB")


def save(img, name):
    img.save(os.path.join(HERE, name))
    print("wrote", name, img.size)


# ---------------------------------------------------------------
# Klausur 1: Schwarzer Rahmen der Dicke 15 Pixel
# Alle Pixel mit x < 15 oder x >= W-15 oder y < 15 oder y >= H-15 -> schwarz
# ---------------------------------------------------------------
def exam1():
    img = load()
    W, H = img.size
    px = img.load()
    d = 15
    for x in range(W):
        for y in range(H):
            if x < d or x >= W - d or y < d or y >= H - d:
                px[x, y] = BLACK
    save(img, "exam1_result.png")


# ---------------------------------------------------------------
# Klausur 2: Obere und untere Bildhaelfte vertauschen
# (Hoehe ist gerade). Fuer y in 0..H/2-1: p[x][y] <-> p[x][y + H/2]
# ---------------------------------------------------------------
def exam2():
    img = load()
    W, H = img.size
    px = img.load()
    half = H // 2
    for x in range(W):
        for y in range(half):
            a = px[x, y]
            b = px[x, y + half]
            px[x, y] = b
            px[x, y + half] = a
    save(img, "exam2_result.png")


# ---------------------------------------------------------------
# Klausur 3: Gefuellte Kreisscheibe schwarz faerben
# Mittelpunkt (171,161), Radius 60
# ---------------------------------------------------------------
def exam3():
    img = load()
    W, H = img.size
    px = img.load()
    cx, cy, r = 171, 161, 60
    r2 = r * r
    for x in range(W):
        for y in range(H):
            if (x - cx) ** 2 + (y - cy) ** 2 <= r2:
                px[x, y] = BLACK
    save(img, "exam3_result.png")


# ---------------------------------------------------------------
# Klausur 4: Oberen-linken Quadranten in unteren-rechten Quadranten kopieren
# halfW = W/2, halfH = H/2; p[x+halfW][y+halfH] = p[x][y]
# ---------------------------------------------------------------
def exam4():
    img = load()
    W, H = img.size
    src = img.copy().load()
    px = img.load()
    hw, hh = W // 2, H // 2
    for x in range(hw):
        for y in range(hh):
            px[x + hw, y + hh] = src[x, y]
    save(img, "exam4_result.png")


# ---------------------------------------------------------------
# Klausur 5: Alles ausserhalb eines Kreises schwarz faerben (Vignette)
# Mittelpunkt (171,161), Radius 130 bleibt erhalten, Rest schwarz
# ---------------------------------------------------------------
def exam5():
    img = load()
    W, H = img.size
    px = img.load()
    cx, cy, r = 171, 161, 130
    r2 = r * r
    for x in range(W):
        for y in range(H):
            if (x - cx) ** 2 + (y - cy) ** 2 > r2:
                px[x, y] = BLACK
    save(img, "exam5_result.png")


if __name__ == "__main__":
    exam1()
    exam2()
    exam3()
    exam4()
    exam5()
