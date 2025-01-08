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
	
	private final JLabel benutzerNameLabel = new JLabel();
	
	private JFrame fenster;
	private Sachbearbeiter sachbearbeiter;
	
	private SachbearbeiterLoeschenAAS() {
		this.setLayout(new GridLayout(8, 2));
		this.setSize(200, 200);
		
		JLabel benutzerLabel = new JLabel("Sachbearbeiter:");
		this.add(benutzerLabel);
		this.add(benutzerNameLabel);
		
		JButton abbruchKnopf = new JButton("Abbruch");
		abbruchKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abbruch - SachbearbeiterLoeschenAAS");
				schliessen(fenster);
			}
		});
		this.add(abbruchKnopf);
		
		JLabel fehlerLabel = new JLabel(" ");
		fehlerLabel.setForeground(Color.RED);
		
		JButton loeschenKnopf = new JButton("Löschen");
		loeschenKnopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Löschen - SachbearbeiterLoeschenAAS");
				try {
					kontrolle.loescheSachbearbeiter(sachbearbeiter.gibBenutzername());
					schliessen(fenster);
				} catch (IllegalArgumentException error) {
					fehlerLabel.setText(error.getMessage());
				}
			}
		});
		this.add(loeschenKnopf);
		
		this.add(fehlerLabel);
		this.add(new JLabel());
	}
	
	public void oeffnen(JFrame fenster) {
		this.fenster = fenster;
		fenster.add(this);
		fenster.revalidate();
		fenster.repaint();
	}
	
	public void ausfuehren(Sachbearbeiter sachbearbeiter) {
		this.sachbearbeiter = sachbearbeiter;
		benutzerNameLabel.setText(sachbearbeiter.gibBenutzername());
	}
	
	public void schliessen(JFrame fenster) {
		fenster.remove(this);
		fenster.revalidate();
		fenster.repaint();
	}
}
