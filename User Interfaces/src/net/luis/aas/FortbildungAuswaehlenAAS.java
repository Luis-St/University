package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungAuswaehlenK;

import javax.swing.*;

public class FortbildungAuswaehlenAAS extends JPanel {
	
	public static final FortbildungAuswaehlenAAS INSTANCE = new FortbildungAuswaehlenAAS();
	
	private final FortbildungAuswaehlenK kontrolle = new FortbildungAuswaehlenK();
	private JFrame fenster;
	
	private FortbildungAuswaehlenAAS() {}
	
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
