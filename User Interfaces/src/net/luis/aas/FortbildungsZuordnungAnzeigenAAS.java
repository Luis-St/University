package net.luis.aas;

import net.luis.Auswaehlen;
import net.luis.Sachbearbeiter;
import net.luis.k.FortbildungsZuordnungAnzeigenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortbildungsZuordnungAnzeigenAAS extends JPanel {
	
	private static final String[] EMPTY_DATA = new String[0];
	public static final FortbildungsZuordnungAnzeigenAAS INSTANZ = new FortbildungsZuordnungAnzeigenAAS();
	
	private final FortbildungsZuordnungAnzeigenK kontrolle = new FortbildungsZuordnungAnzeigenK();
	
	private final JLabel sachbearbeiterNameLabel = new JLabel();
	private final ButtonGroup fortbildungsTypGruppe = new ButtonGroup();
	private final JList<String> fortbildungsListe = new JList<>();
	
	private JFrame fenster;
	private Sachbearbeiter sachbearbeiter;
	
	private FortbildungsZuordnungAnzeigenAAS() {
		this.setLayout(new GridLayout(4, 3));
		this.setSize(200, 200);
		
		JLabel sachbearbeiterLabel = new JLabel("Sachbearbeiter:");
		this.add(sachbearbeiterLabel);
		this.add(sachbearbeiterNameLabel);
		this.add(new JLabel());
		
		JLabel fortbildungsTypLabel = new JLabel("Fortbildungstyp:");
		this.add(fortbildungsTypLabel);
		
		JRadioButton belegtAuswahlKnopf = new JRadioButton("Belegt", false);
		JRadioButton bestandenAuswahlKnopf = new JRadioButton("Bestanden", false);
		this.add(belegtAuswahlKnopf);
		this.add(bestandenAuswahlKnopf);
		fortbildungsTypGruppe.add(belegtAuswahlKnopf);
		fortbildungsTypGruppe.add(bestandenAuswahlKnopf);
		
		this.add(new JLabel());
		this.add(fortbildungsListe);
		this.add(new JLabel());
		
		bestandenAuswahlKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fortbildungsListe.setListData(kontrolle.gibBestandeneFortbildung(sachbearbeiter.gibBenutzername()));
			}
		});
		belegtAuswahlKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fortbildungsListe.setListData(kontrolle.gibBelegteFortbildung(sachbearbeiter.gibBenutzername()));
			}
		});
		
		JButton zurueckKnopf = new JButton("Zurück");
		zurueckKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Zurück - FortbildungsZuordnungAnzeigenAAS");
				schliessen(fenster);
			}
		});
		this.add(new JLabel());
		this.add(zurueckKnopf);
		this.add(new JLabel());
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
	}
	
	public void ausfuehren(Sachbearbeiter sachbearbeiter) {
		this.sachbearbeiter = sachbearbeiter;
		sachbearbeiterNameLabel.setText(sachbearbeiter.gibBenutzername());
		fortbildungsTypGruppe.clearSelection();
		fortbildungsListe.setListData(EMPTY_DATA);
	}
	
	public void schliessen(JFrame fenster) {
		fenster.remove(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
