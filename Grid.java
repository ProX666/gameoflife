package nl.gameoflife;

public class Grid {

	protected static int GRID_SIZE = 100;
	private static int[][] pulsar = { { 5, 3 }, { 6, 3 }, { 7, 3 }, { 11, 3 }, { 12, 3 }, { 13, 3 }, { 3, 5 }, { 8, 5 }, { 10, 5 }, { 15, 5 }, { 3, 6 }, { 8, 6 }, { 10, 6 }, { 15, 6 }, { 3, 7 }, { 8, 7 }, { 10, 7 }, { 15, 7 }, { 5, 8 }, { 6, 8 }, { 7, 8 }, { 11, 8 }, { 12, 8 }, { 13, 8 }, { 5, 10 }, { 6, 10 }, { 7, 10 }, { 11, 10 }, { 12, 10 }, { 13, 10 }, { 3, 11 }, { 8, 11 }, { 10, 11 },
			{ 15, 11 }, { 3, 12 }, { 8, 12 }, { 10, 12 }, { 15, 12 }, { 3, 13 }, { 8, 13 }, { 10, 13 }, { 15, 13 }, { 5, 15 }, { 6, 15 }, { 7, 15 }, { 11, 15 }, { 12, 15 }, { 13, 15 } };

	protected Creatures[][] grid = new Creatures[GRID_SIZE][GRID_SIZE]; // Represents

	// the
	// board.
	// grid[r][c]
	// is true
	// if the
	// cell in
	// row r,
	// column c
	// is grid.

	Grid() {
		resetGrid();
	}

	public void resetGrid() {
		for (int w = 0; w != GRID_SIZE; w++) {
			for (int h = 0; h != GRID_SIZE; h++) {
				grid[w][h] = Creatures.EMPTY;
			}
		}
	}

	public void setCreature(int w, int h, Creatures creature) {		
		grid[w][h] = creature;
	}

	public void setDead(int w, int h) {
		grid[w][h] = Creatures.EMPTY;
	}

	public Creatures getCreature(int w, int h) {
		return grid[w][h];
	}

	public int getGridSize() {
		return GRID_SIZE;
	}

	public void setLive(Shapes shape, Creatures creature, int dx, int dy) {

		switch (shape) {
		case PULSAR:
			for (int coord[] : pulsar) {
				int w = (getGridSize() + dx + coord[0]) % getGridSize();
				int h = (getGridSize() + dy + coord[1]) % getGridSize();		
				
				setCreature(w, h, creature);
			}
			break;
		}

	}
}
