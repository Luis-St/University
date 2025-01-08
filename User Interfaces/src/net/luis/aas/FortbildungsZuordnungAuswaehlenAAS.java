package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungsZuordnungAuswaehlenK;

import javax.swing.*;

public class FortbildungsZuordnungAuswaehlenAAS extends JPanel {
	
	public static final FortbildungsZuordnungAuswaehlenAAS INSTANCE = new FortbildungsZuordnungAuswaehlenAAS();

	private FortbildungsZuordnungAuswaehlenK kontrolle = new FortbildungsZuordnungAuswaehlenK();
	private JFrame fenster;
	
	private FortbildungsZuordnungAuswaehlenAAS() {}
	
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
