package Levels;

import java.awt.Point;

public class Level3 extends Level {
	public Level3(String id, String background, int players) {
		super(id, background, players);
		this.getBackground().setScale(0.8);

		this.addPlatform(190, 600, 0.32, "bridge1", "Platform_IceBridge.png");
		this.addPlatform(540, 600, 0.32, "bridge2", "Platform_IceBridge.png");
		this.addPlatform(890, 600, 0.32, "bridge3", "Platform_IceBridge.png");
		
		this.addPlatform(0, 600, 0.5, "platform1", "Platform_Ice1.png");
		this.addPlatform(350, 600, 0.5, "platform2", "Platform_Ice1.png");
		this.addPlatform(700, 600, 0.5, "platform3", "Platform_Ice1.png");
		this.addPlatform(1050, 600, 0.5, "platform4", "Platform_Ice1.png");
		
		int xOffset = 0;
		for(int i = 0; i < 10; i++) {
			this.addDestructable(xOffset, 0, 1, "iceBlock", "Platform_IceBlock.png");
			this.addDestructable(xOffset, 130, 1, "iceBlock", "Platform_IceBlock.png");
			this.addDestructable(xOffset, 260, 1, "iceBlock", "Platform_IceBlock.png");
			xOffset = xOffset + 130;
		}

		Point p1 = new Point(50, 600);
		this.addSpawnPoint(p1);
		Point p2 = new Point(400, 600);
		this.addSpawnPoint(p2);
		Point p3 = new Point(750, 600);
		this.addSpawnPoint(p3);
		Point p4 = new Point(1100, 600);
		this.addSpawnPoint(p4);
	}
//	public Level1(String id, String filename) {
//		super(id, filename);
//	}
}