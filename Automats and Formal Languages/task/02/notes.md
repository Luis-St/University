$${\mathcal{I}}^{\ast}\,\parallel\,{\mathcal{I}}^{\ast}$$
assertTrue(Pattern.matches(kennzeichen, "VS HH 1000"));
assertTrue(Pattern.matches(kennzeichen, "HH HH 1"));
assertTrue(Pattern.matches(kennzeichen, "H HH 1"));
assertTrue(Pattern.matches(kennzeichen, "HH HH 1 H"));
assertTrue(Pattern.matches(kennzeichen, "T� HH 1 H"));
assertFalse(Pattern.matches(kennzeichen, "HH HH 1 "));
assertFalse(Pattern.matches(kennzeichen, "VS VS 0"));
assertFalse(Pattern.matches(kennzeichen, "VS VS1 1"));
assertFalse(Pattern.matches(kennzeichen, "VS 1"));
assertFalse(Pattern.matches(kennzeichen, "VS ABC 1"));
assertFalse(Pattern.matches(kennzeichen, "VS HH 0"));
assertFalse(Pattern.matches(kennzeichen, "VS HH 10000"));
assertFalse(Pattern.matches(kennzeichen, "VS HH 99 EH"));
assertFalse(Pattern.matches(kennzeichen, "HVSA HH 1 "));
assertFalse(Pattern.matches(kennzeichen, "HH HH 01"));


