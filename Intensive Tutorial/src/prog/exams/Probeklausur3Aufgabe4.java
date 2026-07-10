package prog.exams;

import fachwerk.Fachwerk;
import fachwerk.Fachwerkstab;

import java.util.Scanner;

public class Probeklausur3Aufgabe4 {
	
	public static void main(String[] args) {
		
		System.out.println("Welches Fachwerk wollen Sie rechnen (0-4)?");
		Scanner sc = new Scanner(System.in);
		int wahl = sc.nextInt();
		
		Fachwerk fw = new Fachwerk(wahl);
		fw.berechneGroessen();
		
		double maxBetrag = -1.0;
		int maxNr = -1;
		
		for (int i = 0; i < fw.gibAnzahlStaebe(); i++) {
			
			Fachwerkstab stab = fw.gibStab(i);
			double kraft = stab.gibStabkraft();
			
			if (Math.abs(kraft) > maxBetrag) {
				maxBetrag = Math.abs(kraft);
				maxNr = i;
			}
		}
		
		Fachwerkstab maxStab = fw.gibStab(maxNr);
		double maxKraft = maxStab.gibStabkraft();
		
		String art;
		
		if (maxKraft > 0) {
			art = "Zugkraft";
		} else {
			art = "Druckkraft";
		}
		
		System.out.println("Hoechstbelasteter Stab: Stab " + maxNr + " (" + art + "), Kraft = " + maxKraft);
		
		int a = maxStab.gibKnotenNrA();
		int e = maxStab.gibKnotenNrE();
		
		double xm = (fw.gibKnoten(a).gibX() + fw.gibKnoten(e).gibX()) / 2.0;
		double ym = (fw.gibKnoten(a).gibY() + fw.gibKnoten(e).gibY()) / 2.0;
		
		System.out.println("Mittelpunkt: x = " + xm + "   y = " + ym);
		
		sc.close();
	}
}
