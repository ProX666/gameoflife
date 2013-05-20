package nl.gameoflife;

import java.util.ArrayList;


public class Settings {
	private int worldSize;	// how many pixels is the frame
	private int gridSize;	// how many squares are on the field
	private int sq;			// how big is each square	
	private int speed;
	private int windowWidth, windowHeight;
	ArrayList<Creature> creatureList = new ArrayList<Creature>(); 
	
	
	public int getWorldSize() {
		return worldSize;
	}
	
	public void setWorldSize(int worldSize) {
		this.worldSize = worldSize;
	}
	
	public int getGridSize() {		
		return gridSize;
	}
	
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
		
	public int getSq() {
		return sq;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * 
	 * @param worldSize	size in pixels
	 * @param lifeSize	how many pixels for 1 life
	 */
	public Settings(int worldSize, int lifeSize, int speed) {
		
		this.worldSize = worldSize;
		this.sq = lifeSize;
		this.gridSize = this.worldSize / this.sq;	
		this.speed = speed;
	}
	
	// default constructor
	public Settings() {
		this.worldSize = 800;
		this.sq = 10;
		this.gridSize = this.worldSize / this.sq;	
		this.speed = 50;
	}
	
	public void setDimensions(int w, int h) {
		this.windowWidth = w;
		this.windowHeight = h;
	}
	
	public int getWindowWidth() {
		return windowWidth;
	}
	
	public int getWindowHeight() {
		return windowHeight;
	}
	
	
	public void setCreature(Creature creature) {
		creatureList.add(creature);
	}

}
