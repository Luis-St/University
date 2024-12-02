package net.luis.aas;

import net.luis.Eingabe;
import net.luis.Sachbearbeiter;
import net.luis.as.AdminAS;
import net.luis.as.NormalAS;
import net.luis.k.LoginK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAAS extends JPanel {
	
	public static final LoginAAS INSTANZ = new LoginAAS();
	
	private final JTextField benutzerFeld;
	private final JPasswordField passwortFeld;
	private final JCheckBox adminCheckBox;
	private final JLabel nachrichtLabel;
	private JFrame fenster;
	Sachbearbeiter sachbearbeiter;
	
	private LoginAAS() {
		this.setLayout(new GridLayout(5, 2));
		
		JLabel benutzerLabel = new JLabel("Benutzername:");
		this.benutzerFeld = new JTextField(20);
		JLabel passwortLabel = new JLabel("Passwort:");
		this.passwortFeld = new JPasswordField(20);
		this.adminCheckBox = new JCheckBox("Als Admin einloggen");
		JButton loginButton = new JButton("Login");
		this.nachrichtLabel = new JLabel("");
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Login Vorgang gestartet.");
				LoginAAS.this.verarbeiteLogin();
			}
		});
		
		this.add(benutzerLabel);
		this.add(this.benutzerFeld);
		this.add(passwortLabel);
		this.add(this.passwortFeld);
		this.add(this.adminCheckBox);
		this.add(loginButton);
		this.add(this.nachrichtLabel);
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
	}
	
	private void verarbeiteLogin() {
		String benutzername = this.benutzerFeld.getText();
		String passwort = new String(this.passwortFeld.getPassword());
		boolean istAdmin = this.adminCheckBox.isSelected();
		
		if (benutzername.isBlank() || passwort.isBlank()) {
			this.nachrichtLabel.setText("Benutzername und Passwort dürfen nicht leer sein.");
			return;
		}
		
		try {
			Sachbearbeiter sachbearbeiter = Sachbearbeiter.gib(benutzername);
			if (LoginK.passwortPasstZuBenutzername(sachbearbeiter.gibBenutzername(), passwort)) {
				if (LoginK.gewaehlteBerechtigungPasstZuSachbearbeiter(sachbearbeiter.gibBenutzername(), istAdmin)) {
					if (istAdmin) {
						if (LoginK.istAdmin(sachbearbeiter.gibBenutzername())) {
							this.nachrichtLabel.setText("Eingeloggt als Admin: " + sachbearbeiter.gibBenutzername());
							AdminAS.INSTANZ.oeffnen(this.fenster);
						} else {
							throw new IllegalStateException("Nicht berechtigt als Admin einzuloggen.");
						}
					} else {
						this.nachrichtLabel.setText("Eingeloggt als Benutzer: " + sachbearbeiter.gibBenutzername());
						NormalAS.INSTANZ.oeffnen(this.fenster);
					}
				} else {
					throw new IllegalStateException("Berechtigung passt nicht zum Benutzer.");
				}
			} else {
				this.nachrichtLabel.setText("Falsches Passwort!");
			}
		} catch (Exception e) {
			this.nachrichtLabel.setText(e.getMessage());
			e.printStackTrace();
		}
	}
}
