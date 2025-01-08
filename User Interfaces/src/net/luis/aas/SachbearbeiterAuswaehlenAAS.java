package net.luis.aas;

import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterAuswaehlenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class SachbearbeiterAuswaehlenAAS extends JPanel {
	
	public static final SachbearbeiterAuswaehlenAAS INSTANZ = new SachbearbeiterAuswaehlenAAS();
	
	private final SachbearbeiterAuswaehlenK kontrolle = new SachbearbeiterAuswaehlenK();
	private JFrame fenster;
	private Consumer<Sachbearbeiter> naechstesPanelDynamischOeffnen;
	
	public SachbearbeiterAuswaehlenAAS() {
		this.setLayout(new GridLayout(2, 2));
		this.setSize(200, 200);
		
		JLabel labelBenutzerAuswaehlen = new JLabel("Sachbearbeiter:");
		this.add(labelBenutzerAuswaehlen);
		
		JComboBox<String> benutzerAuswahl = new JComboBox<>(this.kontrolle.gibSachbearbeiterNamen());
		this.add(benutzerAuswahl);
		
		JButton abbruchKnopf = new JButton("Abbruch");
		abbruchKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abbruch - SachbearbeiterAuswaehlenAAS");
				schliessen(fenster);
			}
		});
		this.add(abbruchKnopf);
		
		JButton weiterKnopf = new JButton("Weiter");
		weiterKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Weiter - SachbearbeiterAuswaehlenAAS");
				schliessen(fenster);
				Sachbearbeiter sachbearbeiter = kontrolle.gibSachbearbeiter(String.valueOf(benutzerAuswahl.getSelectedItem()));
				naechstesPanelDynamischOeffnen.accept(sachbearbeiter);
			}
		});
		this.add(weiterKnopf);
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
	}
	
	public void ausfuehren(Consumer<Sachbearbeiter> naechstesPanelDynamischOeffnen) {
		this.naechstesPanelDynamischOeffnen = naechstesPanelDynamischOeffnen;
	}
	
	public void schliessen(JFrame fenster) {
		fenster.remove(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
