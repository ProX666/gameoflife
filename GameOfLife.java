package nl.gameoflife;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOfLife extends Applet {

	public static JLabel gensLabel = new JLabel();
	public static JButton resetBtn = new JButton();
	public static JPanel world, j;
	private static JFrame f;
	public static Settings settings;

	public static int gridSize() {
		return GameOfLife.j.getWidth();
	}

	private void startGame() {
		j = new JPanel(new BorderLayout());
		f.setContentPane(j);

		world = new Life();
		Life.resetGens();

		f.getContentPane().add(BorderLayout.NORTH, gensLabel);
		f.getContentPane().add(BorderLayout.SOUTH, resetBtn);
		f.getContentPane().add(BorderLayout.CENTER, world);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gensLabel.setText("Generation: ");
		resetBtn.setText("Start");
		f.pack();	// set the right size
		f.setLocation(500, 50);
		f.setVisible(true);
	}

	private void setup() {
		settings = new Settings(1000, 10, 50);
		settings.setCreature(new Creature(Creatures.RABBIT, 6, 1, 100));
		settings.setCreature(new Creature(Creatures.FOX, 12, 30, 100));
		settings.setCreature(new Creature(Creatures.HUNTER, 12, 20, 60));

		startGame();

		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});

	}

	public void init() {
		f = new JFrame("Life");
		setup();
	}
}
