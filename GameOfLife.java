package nl.gameoflife;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOfLife {

	public static JLabel gensLabel = new JLabel();	
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Life");
		JPanel p = new Life();	
		
		
		gensLabel.setText("Generation: ");
		
		f.getContentPane().add(BorderLayout.NORTH, gensLabel);
		
		f.getContentPane().add(BorderLayout.CENTER, p);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocation(500,50);
		f.setSize(1000, 1000);
		f.setVisible(true);
	}
}
