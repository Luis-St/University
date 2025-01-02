package net.luis.aas;

import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterLoeschenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SachbearbeiterLoeschenAAS extends JPanel {
	
	public static final SachbearbeiterLoeschenAAS INSTANZ = new SachbearbeiterLoeschenAAS();
	
	private final SachbearbeiterLoeschenK kontrolle = new SachbearbeiterLoeschenK();
	private JFrame fenster;
	
	private SachbearbeiterLoeschenAAS() {
		this.setLayout(new GridLayout(8, 2));
		this.setSize(200, 200);
		
		GridBagConstraints kontext = new GridBagConstraints();
		
		JLabel labelBenutzer = new JLabel("Sachbearbeiter:");
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 0;
		kontext.gridy = 0;
		this.add(labelBenutzer, kontext);
		
		// ToDo: Sachbearbeiter auswählen
		JComboBox<String> boxBenutzer = new JComboBox<>(Sachbearbeiter.gibAlleNamen());
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 1;
		kontext.gridy = 0;
		this.add(boxBenutzer, kontext);
		
		kontext.gridy = 1;
		this.add(new JLabel(" "), kontext);
		
		JButton abbruchKnopf = new JButton("Abbruch");
		abbruchKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				schliessen(fenster);
			}
		});
		
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 0;
		kontext.gridy = 7;
		this.add(abbruchKnopf, kontext);
		
		JLabel fehlerLabel = new JLabel(" ");
		fehlerLabel.setForeground(Color.RED);
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridwidth = GridBagConstraints.REMAINDER;
		kontext.gridx = 0;
		kontext.gridy = 8;
		this.add(fehlerLabel, kontext);
		
		JButton loeschenKnopf = new JButton("Löschen");
		loeschenKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					kontrolle.loescheSachbearbeiter(boxBenutzer.getSelectedItem().toString());
					schliessen(fenster);
				} catch (IllegalArgumentException error) {
					fehlerLabel.setText(error.getMessage());
				}
				
			}
		});
		
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 1;
		kontext.gridy = 7;
		this.add(loeschenKnopf, kontext);
	}
	
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
