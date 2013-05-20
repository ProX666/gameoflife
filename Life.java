package nl.gameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.Random;
// import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Life extends JPanel implements ActionListener {

	private Grid grid;
	private MosaicPanel display;
	boolean[][] eatGrid = new boolean[3][3];
	static int gens = 1;
	static int rabbitsBorn = 0;
	static int rabbitsDead = 0;
	static Grid nextGrid;
	static boolean isDiff = true;

	Life() {
		setLayout(new BorderLayout(3, 3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

		grid = new Grid();
		ListIterator<Creature> creatureIter = GameOfLife.settings.creatureList.listIterator();
		while (creatureIter.hasNext()) {
			Creature thisCreature = creatureIter.next();
			createRandomLife((int) (grid.getGridSize() * (thisCreature.getMin() / 100.0)), (int) (grid.getGridSize() * (thisCreature.getMax() / 100.0)), thisCreature.getCreature(), thisCreature.getDensity());
		}

		display = new MosaicPanel(grid);
		this.add(display, BorderLayout.CENTER);

		new Timer(GameOfLife.settings.getSpeed(), new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ListIterator<Creature> creatureIter = GameOfLife.settings.creatureList.listIterator();
				while (creatureIter.hasNext()) {
					Creature thisCreature = creatureIter.next();
					testForLife(thisCreature.getCreature());
				}

				testForEating(Creatures.FOX, Creatures.RABBIT);
				testForEating(Creatures.HUNTER, Creatures.FOX);
				testForEating(Creatures.HUNTER, Creatures.RABBIT);

				GameOfLife.gensLabel.setText("Generation: " + gens++);
			}

		}).start();

	}

	public static void resetGens() {
		gens = 0;
	}

	private void createRandomLife(int min, int max, Creatures creature, int density) {
		for (int w = min; w != max; w++) {
			for (int h = min; h != max; h++) {
				// we only can create creatures on empty spaces in grid
				if (getSomeLive(density) && grid.getCreature(w, h) == Creatures.EMPTY) {
					grid.setCreature(w, h, creature);
				}
			}
		}
	}

	private boolean getSomeLive(int density) {
		Random rand = new Random();
		return rand.nextInt(density) == 0 ? true : false;
	}

	/*
	 * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
	 * 
	 * Any live cell with two or three live neighbours lives on to the next generation.
	 * 
	 * Any live cell with more than three live neighbours dies, as if by overcrowding.
	 * 
	 * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 */

	// TODO: @random iteration instead of 0 to max!

	private void testForLife(Creatures creature) {
		nextGrid = new Grid();
		Creatures thisCreature;
		int neighbors;

		for (int w = 0; w != grid.getGridSize(); w++) {
			for (int h = 0; h != grid.getGridSize(); h++) {
				thisCreature = grid.getCreature(w, h);
				// find all same creatures around us
				neighbors = calculateNeighbors(w, h, thisCreature, creature);

				// if a shape is found, check if it is the creature we are looking for
				if (thisCreature == creature) {
					// if there are enough same creatures around us then stay alive
					if ((neighbors >= 2) && (neighbors <= 3)) {
						nextGrid.setCreature(w, h, creature);
					} // else nextGrid(w, h) stays empty
				} else if (thisCreature == Creatures.EMPTY && neighbors == 3) {
					// dead cell with 3 neighbours creatures? Get alive!
					nextGrid.setCreature(w, h, creature);
				} else {
					// keep exisiting shape in place
					nextGrid.setCreature(w, h, thisCreature);
				}
			}
		}

		copyGrid(grid, nextGrid);

	}

	/**
	 * Find neighbor. If we start with an empty place, what to do when there are more creatures around? If their is no Fox, all is well. If there are one or more Foxes then what?
	 * First eat?
	 * 
	 * @param x
	 * @param y
	 * @param thisShape
	 * @return
	 */
	private int calculateNeighbors(int x, int y, Creatures thisShape, Creatures creature) {
		Creatures neighborCreature;

		int total = (thisShape != Creatures.EMPTY && thisShape == creature) ? -1 : 0;

		for (int w = -1; w <= +1; w++) {
			for (int h = -1; h <= +1; h++) {
				neighborCreature = grid.getCreature((grid.getGridSize() + (x + w)) % grid.getGridSize(), (grid.getGridSize() + (y + h)) % grid.getGridSize());

				if (neighborCreature == creature) {
					total++;
				}
			}
		}

		return total;
	}

	/**
	 * Check all predators on neighbors. A predator can eat only one victim. So find all victim. If there is more then one victim, eat one @random => so dublicate (a new one is
	 * born) predator to predator place.
	 */
	private void testForEating(Creatures predator, Creatures victim) {

		for (int w = 0; w != grid.getGridSize(); w++) {
			for (int h = 0; h != grid.getGridSize(); h++) {

				if (grid.getCreature(w, h) == predator) {
					if (findCreature(w, h, victim)) {
						// found victim
						// eatGrid contains victims

						// loop through eatGrid
						for (int x = -1; x <= +1; x++) {
							for (int y = -1; y <= +1; y++) {

								// find first one that can be eaten
								if (eatGrid[x + 1][y + 1]) {
									// found, so replace victim by predator
									grid.setCreature((grid.getGridSize() + (x + w)) % grid.getGridSize(), (grid.getGridSize() + (y + h)) % grid.getGridSize(), predator);
									return; // just eat one
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean findCreature(int x, int y, Creatures shape) {
		Creatures neighborShape;
		boolean foundShape = false;

		for (int w = -1; w <= +1; w++) {
			for (int h = -1; h <= +1; h++) {
				neighborShape = grid.getCreature((grid.getGridSize() + (x + w)) % grid.getGridSize(), (grid.getGridSize() + (y + h)) % grid.getGridSize());

				if (neighborShape == shape) {
					foundShape = true;
					eatGrid[w + 1][h + 1] = true;
				} else {
					eatGrid[w + 1][h + 1] = false;
				}
			}
		}
		return foundShape;
	}

	public void copyGrid(Grid grid, Grid nextGrid) {
		for (int w = 0; w != grid.getGridSize(); w++) {
			for (int h = 0; h != grid.getGridSize(); h++) {
				grid.setCreature(w, h, nextGrid.getCreature(w, h));
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
