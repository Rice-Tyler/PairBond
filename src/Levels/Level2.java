package Levels;

import java.awt.Point;

public class Level2 extends Level {
	public Level2(String id, String background, int players) {
		super(id, background, players);
		this.getBackground().setScale(0.8);
		this.addPlatform(250, 300, 1.0, "platform1", "Platform1.png");
		this.addPlatform(850, 300, 1.0, "platform2", "Platform1.png");
		this.addPlatform(0, 600, 2.5, "platform3", "Platform1.png");
		this.addPlatform(0, (int) (this.platforms.get(2).getPivotPoint().y 
				+ (this.platforms.get(2).getUnscaledHeight() * 2.5) + 600), 2.5, "Platform3", "BlackRectangle.png");
		this.addPlatform(800, 600, 2.5, "platform4", "Platform1.png");

		this.addPlatform(800, (int) (this.platforms.get(2).getPivotPoint().y 
				+ (this.platforms.get(2).getUnscaledHeight() * 2.5) + 600), 2.5, "Platform5", "BlackRectangle.png");
		
		Point p1 = new Point(290, 300);
		this.addSpawnPoint(p1);
		Point p2 = new Point(890, 300);
		this.addSpawnPoint(p2);
		Point p3 = new Point(250, 300);
		this.addSpawnPoint(p3);
		Point p4 = new Point(950, 300);
		this.addSpawnPoint(p4);
	}
//	public Level1(String id, String filename) {
//		super(id, filename);
//	}
}