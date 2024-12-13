package net.luis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis-St
 *
 */

public class ZustandsmodellInSwing {
	
	public static void main(String[] args) {
		JFrame fenster = new JFrame("Synchronisierte JScrollBar und JSlider");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setSize(400, 200);
		fenster.setLayout(new GridLayout(2, 1));
		
		BoundedRangeModel model = new DefaultBoundedRangeModel(50, 0, 0, 100);
		
		JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
		scrollBar.setModel(model);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL);
		slider.setModel(model);
		
		fenster.add(scrollBar);
		fenster.add(slider);
		
		fenster.setVisible(true);
	}
}
