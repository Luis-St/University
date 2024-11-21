package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterBearbeitenK;

public class AdminSachbearbeiterBearbeitenAAS {
	
	private static AdminSachbearbeiterBearbeitenAAS OBJ = new AdminSachbearbeiterBearbeitenAAS();
	private SachbearbeiterBearbeitenK kontrolle;
	
	private AdminSachbearbeiterBearbeitenAAS() {
		this.kontrolle = new SachbearbeiterBearbeitenK();
	}
	
	public static AdminSachbearbeiterBearbeitenAAS getInstance() {
		return OBJ;
	}
	
	public void oeffnen() {
		System.out.println("Sachbearbeiter bearbeiten:");
		String name = Eingabe.eingeben("Namen des zu bearbeitenden Nutzers eingeben:");
		while (true) {
			try {
				Sachbearbeiter.gib(name);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				name = Eingabe.eingeben("Namen des zu bearbeitenden Nutzers eingeben:");
			}
		}
		String admin = Eingabe.eingeben("Admin? Y/N (X to leave it as it was)");
		Boolean isAdmin = Sachbearbeiter.gib(name).istAdmin();
		
		while (!admin.equals("Y") && !admin.equals("N") && !admin.equals("X")) {
			if (admin.equals("X")) {
				break;
			}
			admin = Eingabe.eingeben("Admin? Y / N / X");
		}
		if (admin == "Y") {
			isAdmin = true;
		}
		
		while (true) {
			try {
				String userName = Eingabe.eingeben("Neuer Benutzername eingeben: (X to cancel)");
				if (userName.equals("X")) {
					break;
				}
				Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(userName);
				kontrolle.schreibeSachbearbeiter(userName, sachbearbeiter.gibBenutzername(), sachbearbeiter.gibPasswort(), isAdmin);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void praesentiereSachbearbeiter() {}
}
