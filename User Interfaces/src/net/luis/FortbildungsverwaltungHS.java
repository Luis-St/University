package net.luis;

import net.luis.aas.LoginAAS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class FortbildungsverwaltungHS extends JFrame {
	
	public static void main(String[] args) { // aaAA11&&aa
		Sachbearbeiter.einlesen();
		Fortbildung mathematik1 = new Fortbildung("Mathematik 1");
		Fortbildung allgemeineBwl = new Fortbildung("Allgemeine BWL");
		Fortbildung mathematik2 = new Fortbildung("Mathematik 2", mathematik1);
		new Fortbildung("Kostenrechnung", mathematik2, allgemeineBwl);
		new FortbildungsverwaltungHS();
		Sachbearbeiter.abspeichern();
	}
	
	public FortbildungsverwaltungHS() {
		this.setTitle("Fortbildungsverwaltung");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(this.createMenuBar(this));
		this.setVisible(true);
	}
	
	private JMenuBar createMenuBar(JFrame fenster) {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Menü");
		menuBar.add(menu);
		
		JMenuItem loginItem = new JMenuItem("Login");
		loginItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginAAS.INSTANZ.oeffnen(fenster);
			}
		});
		menu.add(loginItem);
		
		JMenuItem exitItem = new JMenuItem("Beenden");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(exitItem);
		return menuBar;
	}
}
