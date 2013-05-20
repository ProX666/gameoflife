package nl.gameoflife;

public class Creature {
	private Creatures creature;
	private int density;	
	private int min, max;
	
	public void setCreature(Creatures creature) {
		this.creature = creature;
	}
	
	public Creatures getCreature() {
		return creature;
	}
	
	public void setDensity(int density) {
		this.density = density;
	}
	
	public int getDensity() {
		return density;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	/**
	 * 
	 * @param creature
	 * @param density
	 * @param min		: min percentage in het veld om te beginnen
	 * @param max		: max percentage in het veld om te beginnen
	 */
	public Creature(Creatures creature, int density, int min, int max) {
		this.creature = creature;
		this.density = density;	
		this.min = min;
		this.max = max;
	}
}
