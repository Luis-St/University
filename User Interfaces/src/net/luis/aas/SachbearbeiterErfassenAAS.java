package net.luis.aas;

import net.luis.k.SachbearbeiterErfassenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SachbearbeiterErfassenAAS extends JPanel {
	
	public static final SachbearbeiterErfassenAAS INSTANZ = new SachbearbeiterErfassenAAS();
	
	private final SachbearbeiterErfassenK kontrolle = new SachbearbeiterErfassenK();
	
	private final JLabel fehlerLabel = new JLabel();
	
	private JFrame fenster;
	
	private SachbearbeiterErfassenAAS() {
		this.setLayout(new GridLayout(9, 2));
		this.setSize(200, 200);
		
		JLabel benutzerLabel = new JLabel("Benutzername:");
		this.add(benutzerLabel);
		
		JTextField benutzerTextFeld = new JTextField();
		this.add(benutzerTextFeld);
		
		JLabel passwortLabel = new JLabel("Passwort:");
		this.add(passwortLabel);
		
		JTextField passwortTextFeld = new JTextField();
		this.add(passwortTextFeld);
		
		JLabel berechtigungsLabel = new JLabel("Berechtigung:");
		this.add(berechtigungsLabel);
		
		this.add(new JLabel());
		
		JRadioButton normalAuswahlKnopf = new JRadioButton("Sachbearbeiter", true);
		this.add(normalAuswahlKnopf);
		
		JRadioButton adminAuswahlKnopf = new JRadioButton("Admin", false);
		this.add(adminAuswahlKnopf);
		
		ButtonGroup berechtigungsGruppe = new ButtonGroup();
		berechtigungsGruppe.add(normalAuswahlKnopf);
		berechtigungsGruppe.add(adminAuswahlKnopf);
		
		JButton abbruchKnopf = new JButton("Abbruch");
		abbruchKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				schliessen(fenster);
			}
		});
		this.add(abbruchKnopf);
		
		JButton speichernKnopf = new JButton("Speichern");
		speichernKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!benutzerTextFeld.getText().isBlank() && !passwortTextFeld.getText().isBlank()) {
					try {
						kontrolle.erzeugeSachbearbeiter(benutzerTextFeld.getText(), passwortTextFeld.getText(), !normalAuswahlKnopf.isSelected());
						schliessen(fenster);
					} catch (Exception fehler) {
						fehlerLabel.setText(fehler.getMessage());
					}
				} else {
					fehlerLabel.setText("Benutzername oder Passwort leer");
				}
			}
		});
		this.add(speichernKnopf);
		
		fehlerLabel.setForeground(Color.RED);
		this.add(fehlerLabel);
		this.add(new JLabel());
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
	}
	
	public void ausfuehren() {
		fehlerLabel.setText("");
	}
	
	public void schliessen(JFrame fenster) {
		fenster.remove(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
