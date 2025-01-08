package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.k.FortbildungsZuordnungLoeschenK;

import javax.swing.*;

public class FortbildungsZuordnungLoeschenAAS extends JPanel {
	
	public static final FortbildungsZuordnungLoeschenAAS INSTANZ = new FortbildungsZuordnungLoeschenAAS();
	
	private FortbildungsZuordnungLoeschenK kontrolle = new FortbildungsZuordnungLoeschenK();
	private JFrame fenster;
	
	private FortbildungsZuordnungLoeschenAAS() {}
	
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
