package nl.gameoflife;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOfLife {

	public static JLabel gensLabel = new JLabel();
	public static JButton resetBtn = new JButton();
	private static JPanel p;
	private static JFrame f;

	public static void main(String[] args) {
		f = new JFrame("Life");
		f.getContentPane().add(BorderLayout.NORTH, gensLabel);
		f.getContentPane().add(BorderLayout.SOUTH, resetBtn);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setLocation(500, 50);
		f.setSize(1000, 1000);
		f.setVisible(true);
		
		gensLabel.setText("Generation: ");
		resetBtn.setText("Start");

		resetBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				f = new JFrame("Life");
				p = new Life();
				Life.resetGens();
				f.getContentPane().add(BorderLayout.NORTH, gensLabel);
				f.getContentPane().add(BorderLayout.SOUTH, resetBtn);
				f.getContentPane().add(BorderLayout.CENTER, p);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.pack();
				f.setLocation(500, 50);
				f.setSize(1000, 1000);
				f.setVisible(true);
			}

		});

		
	}
}
