package net.luis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis-St
 *
 */

public class ZustandsmodellInSwing {
	
	public static void main(String[] args) {
		JFrame fenster = new JFrame("Beispiel für zustandsbehaftete Benachrichtigung");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setSize(400, 200);
		
		JButton knopf = new JButton("Klicken Sie mich!");
		knopf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ereignis) {
				String befehl = ereignis.getActionCommand();
				long zeitpunkt = ereignis.getWhen();
				Object quelle = ereignis.getSource();
				
				System.out.println("Ereignisinformationen:");
				System.out.println("Befehl: " + befehl);
				System.out.println("Zeitpunkt: " + zeitpunkt);
				System.out.println("Quelle: " + quelle);
				
				// Nachricht im Fenster anzeigen
				JOptionPane.showMessageDialog(
					fenster,
					"Knopf wurde geklickt!\nBefehl: " + befehl + "\nZeitpunkt: " + zeitpunkt,
					"Ereignisinformationen",
					JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		
		fenster.getContentPane().add(knopf);
		fenster.setVisible(true);
	}
}
