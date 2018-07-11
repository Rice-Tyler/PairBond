package Levels;

import java.util.ArrayList;
import java.awt.Point;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import java.util.Random;

public class Level extends DisplayObjectContainer {
	ArrayList<DisplayObject> platforms = new ArrayList<DisplayObject>();
	ArrayList<DisplayObject> destructables = new ArrayList<DisplayObject>();
	ArrayList<Point> spawnPoints = new ArrayList<Point>();
	private DisplayObject background;
	private int numPlatforms = 0;
	private int numDestructables = 0;
	private int numPlayers;
	private int numSpawnPoints = 0;
	public Level(String id, String backgroundFile, int numPlayers) {
		super(id);
		this.background = new DisplayObject("background", backgroundFile);
		this.numPlayers = numPlayers;
	}
	

	public void addPlatform(int x, int y, double scale, String id, String filename) {
		DisplayObject platform = new DisplayObject(id, filename);
		platform.setPosition(x, y);
		platform.setScale(scale);
		platforms.add(platform);
		numPlatforms++;
	}
	
	public void addPlatform(int x, int y, double scale, double rotation, String id, String filename) {
		DisplayObject platform = new DisplayObject(id, filename);
		platform.setPosition(x, y);
		platform.setScale(scale);
		platform.setRotation(rotation);
		platforms.add(platform);
		numPlatforms++;
	}
	
	public void addDestructable(int x, int y, double scale, String id, String filename) {
		DisplayObject destructable = new DisplayObject(id, filename);
		destructable.setPosition(x, y);
		destructable.setScale(scale);
		destructables.add(destructable);
		numDestructables++;
	}
	
	public DisplayObject getBackground() {
		return this.background;
	}
	
	public int getNumPlatforms() {
		return this.numPlatforms;
	}

	public int getNumDestructables() {
		return this.numDestructables;
	}
	public DisplayObject getPlatform(int i) {
		return this.platforms.get(i);
	}
	public DisplayObject getDestructable(int i) {
		return this.destructables.get(i);
	}
	public int getNumPlayers() {
		return this.numPlayers;
	}
	public void setNumPlayers(int n) {
		this.numPlayers = n;
	}
	public void addSpawnPoint(Point p) {
		this.spawnPoints.add(p);
		this.numSpawnPoints++;
	}

	
	private int getRandomSpawn() {
		Random r = new Random();
		return r.nextInt(this.numSpawnPoints);
	}
	
	public Point getSpawnPoint() {
		int index = this.getRandomSpawn();
		Point spawn = this.spawnPoints.get(index);
		this.spawnPoints.remove(index);
		this.numSpawnPoints--;
		return spawn;
	}
}
