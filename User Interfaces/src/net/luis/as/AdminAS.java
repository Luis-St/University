package net.luis.as;

import net.luis.Eingabe;
import net.luis.aas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminAS extends JPanel {
	
	public static final AdminAS INSTANZ = new AdminAS();
	private JFrame fenster;
	
	private AdminAS() {
		this.setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Optionen");
		
		JMenuItem sachbearbeiterErfassenItem = new JMenuItem("Sachbearbeiter erfassen");
		sachbearbeiterErfassenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SachbearbeiterErfassenAAS.INSTANZ.oeffnen(fenster);
				SachbearbeiterErfassenAAS.INSTANZ.ausfuehren();
			}
		});
		
		JMenuItem sachbearbeiterBearbeitenItem = new JMenuItem("Sachbearbeiter bearbeiten");
		sachbearbeiterBearbeitenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SachbearbeiterAuswaehlenAAS.INSTANZ.oeffnen(fenster);
				SachbearbeiterAuswaehlenAAS.INSTANZ.ausfuehren(sachbearbeiter -> {
					AdminSachbearbeiterBearbeitenAAS.INSTANZ.oeffnen(fenster);
					AdminSachbearbeiterBearbeitenAAS.INSTANZ.ausfuehren(sachbearbeiter);
				});
			}
		});
		
		JMenuItem sachbearbeiterLoeschenItem = new JMenuItem("Sachbearbeiter löschen");
		sachbearbeiterLoeschenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SachbearbeiterAuswaehlenAAS.INSTANZ.oeffnen(fenster);
				SachbearbeiterAuswaehlenAAS.INSTANZ.ausfuehren(sachbearbeiter -> {
					SachbearbeiterLoeschenAAS.INSTANZ.oeffnen(fenster);
					SachbearbeiterLoeschenAAS.INSTANZ.ausfuehren(sachbearbeiter);
				});
			}
		});
		
		JMenuItem fortbildungZuordnenItem = new JMenuItem("Fortbildung zuordnen");
		fortbildungZuordnenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FortbildungZuordnenAAS.INSTANZ.oeffnen(fenster);
			}
		});
		
		JMenuItem fortbildungsZuordnungLoeschenItem = new JMenuItem("Fortbildungszuordnung löschen");
		fortbildungsZuordnungLoeschenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FortbildungsZuordnungLoeschenAAS.INSTANZ.oeffnen(fenster);
			}
		});
		
		JMenuItem fortbildungsZuordnungAnzeigenItem = new JMenuItem("Fortbildungszuordnung anzeigen");
		fortbildungsZuordnungAnzeigenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SachbearbeiterAuswaehlenAAS.INSTANZ.oeffnen(fenster);
				SachbearbeiterAuswaehlenAAS.INSTANZ.ausfuehren(sachbearbeiter -> {
					FortbildungsZuordnungAnzeigenAAS.INSTANZ.oeffnen(fenster);
					FortbildungsZuordnungAnzeigenAAS.INSTANZ.ausfuehren(sachbearbeiter);
				});
			}
		});
		
		menu.add(sachbearbeiterErfassenItem);
		menu.add(sachbearbeiterBearbeitenItem);
		menu.add(sachbearbeiterLoeschenItem);
		menu.add(fortbildungZuordnenItem);
		menu.add(fortbildungsZuordnungLoeschenItem);
		menu.add(fortbildungsZuordnungAnzeigenItem);
		
		menuBar.add(menu);
		this.add(menuBar, BorderLayout.NORTH);
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.setContentPane(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
