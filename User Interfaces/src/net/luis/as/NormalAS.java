package net.luis.as;

import net.luis.aas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NormalAS extends JPanel {
	
	public static final NormalAS INSTANZ = new NormalAS();
	private JFrame fenster;
	
	private NormalAS() {
		this.setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Optionen");
		
		JMenuItem sachbearbeiterBearbeitenItem = new JMenuItem("Sachbearbeiter bearbeiten");
		sachbearbeiterBearbeitenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SachbearbeiterAuswaehlenAAS.INSTANZ.oeffnen(fenster);
				SachbearbeiterAuswaehlenAAS.INSTANZ.ausfuehren(sachbearbeiter -> {
					SachbearbeiterBearbeitenAAS.INSTANZ.oeffnen(fenster);
					SachbearbeiterBearbeitenAAS.INSTANZ.ausfuehren(sachbearbeiter);
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
		
		menu.add(sachbearbeiterBearbeitenItem);
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
