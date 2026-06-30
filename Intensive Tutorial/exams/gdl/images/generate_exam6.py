#!/usr/bin/env python3
"""
Zielbild fuer Probeklausur 6, Aufgabe 1 (Kombination aus drei Schritten):
  a) kreisfoermigen Ausschnitt (Mittelpunkt (90,230), Radius 45) um 165 nach rechts kopieren
  b) Kreislinie/Ring um (171,95), innerer Radius 38, aeusserer Radius 55, schwarz
  c) Rahmen der Dicke 18 schwarz
Konvention: p[x][y], x horizontal, y vertikal, Ursprung links oben.
"""
from PIL import Image
import os

HERE = os.path.dirname(os.path.abspath(__file__))
SRC = os.path.join(HERE, "MyPicture.jpg")
BLACK = (0, 0, 0)


def exam6():
    img = Image.open(SRC).convert("RGB")
    W, H = img.size
    src = img.copy().load()
    px = img.load()

    # a) kreisfoermigen Ausschnitt um 165 nach rechts kopieren
    cx, cy, r = 90, 230, 45
    for x in range(W):
        for y in range(H):
            if (x - cx) ** 2 + (y - cy) ** 2 <= r * r:
                nx, ny = x + 165, y
                if 0 <= nx < W and 0 <= ny < H:
                    px[nx, ny] = src[x, y]

    # b) Kreislinie (Ring) schwarz: 38 <= Abstand <= 55
    icx, icy, ri, ro = 171, 95, 38, 55
    for x in range(W):
        for y in range(H):
            d2 = (x - icx) ** 2 + (y - icy) ** 2
            if ri * ri <= d2 <= ro * ro:
                px[x, y] = BLACK

    # c) Rahmen der Dicke 18 schwarz
    d = 18
    for x in range(W):
        for y in range(H):
            if x < d or x >= W - d or y < d or y >= H - d:
                px[x, y] = BLACK

    out = os.path.join(HERE, "exam6_result.png")
    img.save(out)
    print("wrote", out, img.size)


if __name__ == "__main__":
    exam6()
