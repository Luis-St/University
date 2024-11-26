package net.luis;

import net.luis.aas.LoginAAS;

public class FortbildungsverwaltungHS {
	
	public static void main(String[] args) {
		while (true) {
			try {
				LoginAAS.INSTANZ.oeffnen();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
