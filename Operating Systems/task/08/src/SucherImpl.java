class SucherImpl extends Thread implements Sucher {
	
	private final Territorium territorium;
	private int[] aktuellePosition;
	private Richtung gehRichtung;
	
	SucherImpl(int[] neuePosition, Richtung richtung, Territorium territorium) {
		this.aktuellePosition = new int[] { neuePosition[0], neuePosition[1] };
		this.territorium = territorium;
		this.gehRichtung = richtung;
	}
	
	@Override
	public void setzeGehrichtung(Richtung r) {
		this.gehRichtung = r;
	}
	
	@Override
	public Richtung gibGehRichtung() {
		return this.gehRichtung;
	}
	
	@Override
	public Territorium gibTerritorium() {
		return this.territorium;
	}
	
	private void bewege(Richtung r) {
		this.aktuellePosition = this.gibTerritorium().gibNeuePosition(r, this.aktuellePosition);
		this.gehRichtung = r;
	}
	
	@Override
	public int[] gibPosition() {
		return new int[] { this.aktuellePosition[0], this.aktuellePosition[1] };
	}
	
	@Override
	public boolean setzePosition(int[] position) {
		if (this.territorium.istKachel(position)) {
			this.aktuellePosition = new int[] { position[0], position[1] };
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public boolean bewege() {
		int[] moeglichePosition = this.gibTerritorium().
			gibNeuePosition(this.gibGehRichtung(), this.gibPosition());
		
		if (this.territorium.istKachel(moeglichePosition)) {
			this.bewege(this.gibGehRichtung());
			return true;
		} else {
			return false;
		}
	}
	
}
