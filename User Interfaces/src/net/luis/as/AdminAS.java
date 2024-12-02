package net.luis.as;

import net.luis.Auswaehlen;
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
				SachbearbeiterErfassenAAS.INSTANZ.oeffnen(AdminAS.this.fenster);
			}
		});
		
		JMenuItem sachbearbeiterBearbeitenItem = new JMenuItem("Sachbearbeiter bearbeiten");
		sachbearbeiterBearbeitenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminSachbearbeiterBearbeitenAAS.INSTANZ.oeffnen(AdminAS.this.fenster);
			}
		});
		
		JMenuItem sachbearbeiterLoeschenItem = new JMenuItem("Sachbearbeiter löschen");
		sachbearbeiterLoeschenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SachbearbeiterLoeschenAAS.INSTANZ.oeffnen(AdminAS.this.fenster);
			}
		});
		
		JMenuItem fortbildungZuordnenItem = new JMenuItem("Fortbildung zuordnen");
		fortbildungZuordnenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FortbildungZuordnenAAS.INSTANZ.oeffnen(AdminAS.this.fenster);
			}
		});
		
		JMenuItem fortbildungsZuordnungLoeschenItem = new JMenuItem("Fortbildungszuordnung löschen");
		fortbildungsZuordnungLoeschenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FortbildungsZuordnungLoeschenAAS.INSTANZ.oeffnen(AdminAS.this.fenster);
			}
		});
		
		JMenuItem fortbildungsZuordnungAnzeigenItem = new JMenuItem("Fortbildungszuordnung anzeigen");
		fortbildungsZuordnungAnzeigenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FortbildungsZuordnungAnzeigenAAS.INSTANZ.oeffnen(AdminAS.this.fenster);
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
