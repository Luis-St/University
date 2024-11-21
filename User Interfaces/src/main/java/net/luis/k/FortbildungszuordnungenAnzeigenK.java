package net.luis.k;

public class FortbildungszuordnungenAnzeigenK {
	
	public String[] gibBestandeneFortbildungenNamen(String benutzername) {
		return new FortbildungszuordnungAuswaehlenK().gibBestandeneFortbildungenNamen(benutzername);
	}
	
	public String[] gibBelegteFortbildungenNamen(String benutzername) {
		return new FortbildungszuordnungAuswaehlenK().gibBelegteFortbildungenNamen(benutzername);
	}
}
