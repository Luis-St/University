package net.luis;

import net.luis.aas.LoginAAS;
import net.luis.k.SachbearbeiterErfassenK;

import java.util.Arrays;

public class FortbildungsverwaltungHS {
	
	public static void main(String[] args) {
		while (true) {
			try {
				LoginAAS.getInstance().oeffnen();
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
