package net.luis.aas;

import net.luis.k.SachbearbeiterBearbeitenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SachbearbeiterBearbeitenAAS extends JPanel {
	
	public static final SachbearbeiterBearbeitenAAS INSTANZ = new SachbearbeiterBearbeitenAAS();
	
	private final SachbearbeiterBearbeitenK kontrolle = new SachbearbeiterBearbeitenK();
	private JFrame fenster;
	
	private SachbearbeiterBearbeitenAAS() {
		this.setLayout(new GridLayout(9, 2));
		this.setSize(200, 200);
		
		GridBagConstraints kontext = new GridBagConstraints();
		
		JLabel benutzerLabel = new JLabel("Benutzername:");
		kontext.weightx = 0.5;
		kontext.fill = GridBagConstraints.HORIZONTAL;
		kontext.gridx = 0;
		kontext.gridy = 0;
		this.add(benutzerLabel, kontext);
		
		JTextField benutzerTextFeld = new JTextField();
		kontext.gridx = 1;
		kontext.gridy = 0;
		this.add(benutzerTextFeld, kontext);
		
		kontext.gridy = 1;
		this.add(new JLabel(" "), kontext);
		
		JLabel passwortLabel = new JLabel("Passwort:");
		kontext.gridx = 0;
		kontext.gridy = 2;
		this.add(passwortLabel, kontext);
		
		JTextField passwortTextFeld = new JTextField();
		kontext.gridx = 1;
		kontext.gridy = 2;
		this.add(passwortTextFeld, kontext);
		
		kontext.gridy = 5;
		this.add(new JLabel(" "), kontext);
		
		JLabel berechtigungsLabel = new JLabel("Berechtigung:");
		kontext.gridx = 0;
		kontext.gridy = 6;
		this.add(berechtigungsLabel, kontext);
		
		JRadioButton normalAuswahlKnopf = new JRadioButton("Sachbearbeiter", true);
		kontext.gridx = 1;
		kontext.gridy = 6;
		this.add(normalAuswahlKnopf, kontext);
		
		JRadioButton adminAuswahlKnopf = new JRadioButton("Admin", false);
		kontext.gridx = 1;
		kontext.gridy = 7;
		this.add(adminAuswahlKnopf, kontext);
		
		ButtonGroup berechtigungsGruppe = new ButtonGroup();
		berechtigungsGruppe.add(normalAuswahlKnopf);
		berechtigungsGruppe.add(adminAuswahlKnopf);
		
		kontext.gridy = 7;
		this.add(new JLabel(" "), kontext);
		
		JButton abbruchKnopf = new JButton("Abbruch");
		abbruchKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				schliessen(fenster);
			}
		});
		kontext.gridx = 0;
		kontext.gridy = 8;
		this.add(abbruchKnopf, kontext);
		
		JLabel fehlerLabel = new JLabel(" ");
		fehlerLabel.setForeground(Color.RED);
		kontext.gridwidth = GridBagConstraints.REMAINDER;
		kontext.gridx = 0;
		kontext.gridy = 9;
		this.add(fehlerLabel, kontext);
		
		JButton speichernKnopf = new JButton("Speichern");
		speichernKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!benutzerTextFeld.getText().isBlank() && !passwortTextFeld.getText().isBlank()) {
					try {
						// ToDo: Sachbearbeiter speichern
						kontrolle.schreibeSachbearbeiter(null, benutzerTextFeld.getText(), passwortTextFeld.getText(), !normalAuswahlKnopf.isSelected());
						schliessen(fenster);
					} catch (IllegalArgumentException error) {
						fehlerLabel.setText(error.getMessage());
					}
				} else {
					fehlerLabel.setText("Benutzername oder Passwort leer");
				}
			}
		});
		kontext.gridx = 1;
		kontext.gridy = 8;
		this.add(speichernKnopf, kontext);
		
		normalAuswahlKnopf.setEnabled(false);
		adminAuswahlKnopf.setEnabled(false);
		benutzerTextFeld.setEnabled(false);
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
		
		// ToDo: Sachbearbeiter auswählen
		//Sachbearbeiter temp = LoginAAS.INSTANZ().sachbearbeiterToLogin;
		//benutzerTextFeld.setText(temp.gibBenutzername());
		//passwortTextFeld.setText(temp.gibPasswort());
		//berechtigungsGruppe.clearSelection();
		//normalAuswahlKnopf.setSelected(!temp.istAdmin());
		//adminAuswahlKnopf.setSelected(temp.istAdmin());
	}
	
	public void schliessen(JFrame fenster) {
		fenster.remove(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
