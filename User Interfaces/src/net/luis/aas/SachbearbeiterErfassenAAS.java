package net.luis.aas;

import net.luis.k.SachbearbeiterErfassenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SachbearbeiterErfassenAAS extends JPanel {
	
	public static final SachbearbeiterErfassenAAS INSTANZ = new SachbearbeiterErfassenAAS();
	
	private final SachbearbeiterErfassenK kontrolle = new SachbearbeiterErfassenK();
	private JFrame fenster;
	
	private SachbearbeiterErfassenAAS() {
		this.setLayout(new GridLayout(8, 2));
		this.setSize(200, 200);
		
		GridBagConstraints kontext = new GridBagConstraints();
		
		JLabel benutzerLabel = new JLabel("Benutzername:");
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 0;
		kontext.gridy = 0;
		this.add(benutzerLabel, kontext);
		
		JTextField benutzerTextFeld = new JTextField();
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 1;
		kontext.gridy = 0;
		this.add(benutzerTextFeld, kontext);
		
		kontext.gridy = 1;
		this.add(new JLabel(" "), kontext);
		
		JLabel passwortLabel = new JLabel("Passwort:");
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 0;
		kontext.gridy = 2;
		this.add(passwortLabel, kontext);
		
		JTextField passwortTextFeld = new JTextField();
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 1;
		kontext.gridy = 2;
		this.add(passwortTextFeld, kontext);
		
		kontext.gridy = 3;
		this.add(new JLabel(" "), kontext);
		
		JLabel berechtigungsLabel = new JLabel("Berechtigung:");
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 0;
		kontext.gridy = 4;
		this.add(berechtigungsLabel, kontext);
		
		JRadioButton normalAuswahlKnopf = new JRadioButton("Sachbearbeiter", true);
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 1;
		kontext.gridy = 4;
		this.add(normalAuswahlKnopf, kontext);
		
		JRadioButton adminAuswahlKnopf = new JRadioButton("Admin", false);
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 1;
		kontext.gridy = 5;
		this.add(adminAuswahlKnopf, kontext);
		
		ButtonGroup berechtigungsGruppe = new ButtonGroup();
		berechtigungsGruppe.add(normalAuswahlKnopf);
		berechtigungsGruppe.add(adminAuswahlKnopf);
		
		kontext.gridy = 6;
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
		
		JButton speichernKnopf = new JButton("Speichern");
		speichernKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!benutzerTextFeld.getText().isBlank() && !passwortTextFeld.getText().isBlank()) {
					try {
						kontrolle.erzeugeSachbearbeiter(benutzerTextFeld.getText(), passwortTextFeld.getText(), !normalAuswahlKnopf.isSelected());
						schliessen(fenster);
					} catch (IllegalArgumentException error) {
						fehlerLabel.setText(error.getMessage());
					}
				} else {
					fehlerLabel.setText("Benutzername oder Passwort leer");
				}
			}
		});
		
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 1;
		kontext.gridy = 7;
		this.add(speichernKnopf, kontext);
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
