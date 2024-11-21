package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterBearbeitenK;

public class SachbearbeiterBearbeitenAAS {
	
	private static SachbearbeiterBearbeitenAAS OBJ = new SachbearbeiterBearbeitenAAS();
	SachbearbeiterBearbeitenK kontrolle;
	
	private SachbearbeiterBearbeitenAAS() {
		this.kontrolle = new SachbearbeiterBearbeitenK();
	}
	
	public static SachbearbeiterBearbeitenAAS getInstance() {
		return OBJ;
	}
	
	public void ausgefuehrt(String name) {
		Sachbearbeiter temp = Sachbearbeiter.gib(name);
		System.out.println("Neuer Benutzername ist: " + temp.gibPasswort());
	}
	
	public void modifiziereSachbearbeiter() {
		String name = LoginAAS.getInstance().sachbearbeiterToLogin.gibBenutzername();
		
		System.out.println("Sachbearbeiter " + name + " bearbeiten:");
		
		while (true) {
			try {
				String username = Eingabe.eingeben("Neuer Benutzername eingeben: (X to cancel)");
				if (username.equals("X")) {
					break;
				}
				Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(name);
				kontrolle.schreibeSachbearbeiter(name, username, sachbearbeiter.gibPasswort(), sachbearbeiter.istAdmin());
				ausgefuehrt(name);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
