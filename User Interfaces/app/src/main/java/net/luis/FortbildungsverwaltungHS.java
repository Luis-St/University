package net.luis;

import android.app.Application;

public class FortbildungsverwaltungHS extends Application { // aaAA11&&aa
	
	@Override
	public void onCreate() {
		super.onCreate();
		Sachbearbeiter.einlesen();
		Fortbildung administartion1 = new Fortbildung("Administration 1");
		Fortbildung administartion2 = new Fortbildung("Administration 2", administartion1);
		Fortbildung administartion3 = new Fortbildung("Administration 3", administartion1, administartion2);
		Fortbildung mathematik1 = new Fortbildung("Mathematik 1");
		Fortbildung allgemeineBwl = new Fortbildung("Allgemeine BWL");
		Fortbildung mathematik2 = new Fortbildung("Mathematik 2", mathematik1);
		
		Sachbearbeiter admin = Sachbearbeiter.gib("admin");
		admin.bestehe(administartion1);
		admin.bestehe(administartion2);
		admin.belege(administartion3);
		
		new Fortbildung("Kostenrechnung", mathematik2, allgemeineBwl);
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
		Sachbearbeiter.abspeichern();
	}
}
