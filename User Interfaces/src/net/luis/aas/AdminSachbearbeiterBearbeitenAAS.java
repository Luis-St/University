package net.luis.aas;

import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterBearbeitenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminSachbearbeiterBearbeitenAAS extends JPanel {
	
	public static final AdminSachbearbeiterBearbeitenAAS INSTANZ = new AdminSachbearbeiterBearbeitenAAS();
	
	private final SachbearbeiterBearbeitenK kontrolle = new SachbearbeiterBearbeitenK();
	private JFrame fenster;
	
	private AdminSachbearbeiterBearbeitenAAS() {
		this.setLayout(new GridLayout(9, 2));
		this.setSize(200, 200);
		
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel labelBenutzerAuswaehlen = new JLabel("Sachbearbeiter:");
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		this.add(labelBenutzerAuswaehlen, c);
		
		// ToDo: Sachbearbeiter auswählen
		JComboBox<String> benutzerAuswahl = new JComboBox<>(Sachbearbeiter.gibAlleNamen());
		c.gridx = 1;
		c.gridy = 0;
		this.add(benutzerAuswahl, c);
		
		c.gridy = 1;
		this.add(new JLabel(" "), c);
		
		JLabel benutzerLabel = new JLabel("Benutzername:");
		c.gridx = 0;
		c.gridy = 2;
		this.add(benutzerLabel, c);
		
		JTextField benutzerTextFeld = new JTextField();
		c.gridx = 1;
		c.gridy = 2;
		this.add(benutzerTextFeld, c);
		
		c.gridy = 3;
		this.add(new JLabel(" "), c);
		
		JLabel passwortLabel = new JLabel("Passwort:");
		c.gridx = 0;
		c.gridy = 4;
		this.add(passwortLabel, c);
		
		JTextField passwortTextFeld = new JTextField();
		c.gridx = 1;
		c.gridy = 4;
		this.add(passwortTextFeld, c);
		
		c.gridy = 5;
		this.add(new JLabel(" "), c);
		
		JLabel berechtigungsLabel = new JLabel("Berechtigung:");
		c.gridx = 0;
		c.gridy = 6;
		this.add(berechtigungsLabel, c);
		
		JRadioButton normalAuswahlKnopf = new JRadioButton("Sachbearbeiter", true);
		c.gridx = 1;
		c.gridy = 6;
		this.add(normalAuswahlKnopf, c);
		
		JRadioButton adminAuswahlKnopf = new JRadioButton("Admin", false);
		c.gridx = 1;
		c.gridy = 7;
		this.add(adminAuswahlKnopf, c);
		
		ButtonGroup berechtigungsGruppe = new ButtonGroup();
		berechtigungsGruppe.add(normalAuswahlKnopf);
		berechtigungsGruppe.add(adminAuswahlKnopf);
		
		c.gridy = 7;
		this.add(new JLabel(" "), c);
		
		JButton abbruchKnopf = new JButton("Abbruch");
		abbruchKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				schliessen(fenster);
			}
		});
		c.gridx = 0;
		c.gridy = 8;
		this.add(abbruchKnopf, c);
		
		JLabel fehlerLabel = new JLabel(" ");
		fehlerLabel.setForeground(Color.RED);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 0;
		c.gridy = 9;
		this.add(fehlerLabel, c);
		
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
		c.gridx = 1;
		c.gridy = 8;
		this.add(speichernKnopf, c);
		
		benutzerAuswahl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ToDo: Sachbearbeiter auswählen
				//Sachbearbeiter temp = Sachbearbeiter.gib(boxBenutzer.getSelectedItem().toString());
				//tfBenutzer.setText(temp.gibBenutzername());
				//tfPasswort.setText(temp.gibPasswort());
				//groupBerechtigung.clearSelection();
				//radioNormal.setSelected(!temp.istAdmin());
				//radioAdmin.setSelected(temp.istAdmin());
			}
		});
		
		// ToDo: Sachbearbeiter auswählen
		//Sachbearbeiter temp = Sachbearbeiter.gib(Sachbearbeiter.gibAlleNamen()[0]);
		//tfBenutzer.setText(temp.gibBenutzername());
		//tfPasswort.setText(temp.gibPasswort());
		//groupBerechtigung.clearSelection();
		//radioNormal.setSelected(!temp.istAdmin());
		//radioAdmin.setSelected(temp.istAdmin());
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
