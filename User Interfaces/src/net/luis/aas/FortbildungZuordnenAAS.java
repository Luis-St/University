package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungZuordnenK;

import javax.swing.*;

public class FortbildungZuordnenAAS extends JPanel {
	
	public static final FortbildungZuordnenAAS INSTANZ = new FortbildungZuordnenAAS();
	
	private final FortbildungZuordnenK kontrolle = new FortbildungZuordnenK();
	private JFrame fenster;
	
	private FortbildungZuordnenAAS() {}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
	}
	
	public void schliessen(JFrame fenster) {
		fenster.remove(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
