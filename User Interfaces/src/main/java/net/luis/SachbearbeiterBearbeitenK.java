package net.luis;

public class SachbearbeiterBearbeitenK {
	
	void schreibeSachbearbeiter(String name, String passwort, boolean istAdmin) {
		
		Sachbearbeiter mitarbeiter;
		while (true) {
			try {
				mitarbeiter = Sachbearbeiter.gib(name);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if (!istAdmin) {
			if (Sachbearbeiter.gib(name).istAdmin()) {
				int adminCount = 0;
				for (int i = 0; i < Sachbearbeiter.gibAlleNamen().length; i++) {
					if (Sachbearbeiter.gib(Sachbearbeiter.gibAlleNamen()[i]).istAdmin()) {
						adminCount++;
						if (adminCount > 1) {
							break;
						}
					}
				}
				if (adminCount < 2) {
					throw new IllegalArgumentException("Mindestens 1 Admin wird benötigt!");
				}
			}
		}
		
		mitarbeiter.setzeBerechtigung(istAdmin);
		mitarbeiter.setzePasswort(passwort);
	}
}
