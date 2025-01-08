package net.luis.aas;

import net.luis.Sachbearbeiter;
import net.luis.k.SachbearbeiterBearbeitenK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SachbearbeiterBearbeitenAAS extends JPanel {
	
	public static final SachbearbeiterBearbeitenAAS INSTANZ = new SachbearbeiterBearbeitenAAS();
	
	private final SachbearbeiterBearbeitenK kontrolle = new SachbearbeiterBearbeitenK();
	
	private final JTextField benutzerTextFeld = new JTextField();
	private final JTextField passwortTextFeld = new JTextField();
	private final JRadioButton normalAuswahlKnopf = new JRadioButton("Sachbearbeiter", true);
	private final JRadioButton adminAuswahlKnopf = new JRadioButton("Admin", false);
	private final ButtonGroup berechtigungsGruppe = new ButtonGroup();
	
	private JFrame fenster;
	private Sachbearbeiter sachbearbeiter;
	
	private SachbearbeiterBearbeitenAAS() {
		this.setLayout(new GridLayout(9, 2));
		this.setSize(200, 200);
		
		JLabel benutzerLabel = new JLabel("Benutzername:");
		this.add(benutzerLabel);
		this.add(benutzerTextFeld);
		
		JLabel passwortLabel = new JLabel("Passwort:");
		this.add(passwortLabel);
		this.add(passwortTextFeld);
		
		JLabel berechtigungsLabel = new JLabel("Berechtigung:");
		this.add(berechtigungsLabel);
		this.add(new JLabel());
		this.add(normalAuswahlKnopf);
		this.add(adminAuswahlKnopf);
		berechtigungsGruppe.add(normalAuswahlKnopf);
		berechtigungsGruppe.add(adminAuswahlKnopf);
		
		JButton abbruchKnopf = new JButton("Abbruch");
		abbruchKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abbruch - SachbearbeiterBearbeitenAAS");
				schliessen(fenster);
			}
		});
		this.add(abbruchKnopf);
		
		JLabel fehlerLabel = new JLabel(" ");
		fehlerLabel.setForeground(Color.RED);
		
		
		JButton speichernKnopf = new JButton("Speichern");
		speichernKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Speichern - SachbearbeiterBearbeitenAAS");
				if (!benutzerTextFeld.getText().isBlank() && !passwortTextFeld.getText().isBlank()) {
					try {
						kontrolle.schreibeSachbearbeiter(sachbearbeiter.gibBenutzername(), benutzerTextFeld.getText(), passwortTextFeld.getText(), !normalAuswahlKnopf.isSelected());
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
		this.add(fehlerLabel);
		this.add(new JLabel());
		
		normalAuswahlKnopf.setEnabled(false);
		adminAuswahlKnopf.setEnabled(false);
		benutzerTextFeld.setEnabled(false);
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
	}
	
	public void ausfuehren(Sachbearbeiter sachbearbeiter) {
		this.sachbearbeiter = sachbearbeiter;
		benutzerTextFeld.setText(sachbearbeiter.gibBenutzername());
		passwortTextFeld.setText(sachbearbeiter.gibPasswort());
		berechtigungsGruppe.clearSelection();
		normalAuswahlKnopf.setSelected(!sachbearbeiter.istAdmin());
		adminAuswahlKnopf.setSelected(sachbearbeiter.istAdmin());
	}
	
	public void schliessen(JFrame fenster) {
		fenster.remove(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
