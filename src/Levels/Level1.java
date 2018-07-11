package Levels;

import java.awt.Point;

public class Level1 extends Level {
	public Level1(String id, String background, int players) {
		super(id, background, players);
		this.getBackground().setScale(0.8);
		this.addPlatform(0, 550, 1.0, "ground", "Ground1.png");
		this.getPlatform(0).setScaleX(1.44);
		
		int x = 100;
		Point p1 = new Point(0+x, 550);
		this.addSpawnPoint(p1);
		Point p2 = new Point(300+x, 550);
		this.addSpawnPoint(p2);
		Point p3 = new Point(600+x, 550);
		this.addSpawnPoint(p3);
		Point p4 = new Point(900+x, 550);
		this.addSpawnPoint(p4);
	}
//	public Level1(String id, String filename) {
//		super(id, filename);
//	}
}