package Levels;

import edu.virginia.engine.display.Sprite;

public class Level1 extends Sprite {
	public Level1(String id, int n) {
		super(id);
		switch (n) {
		case 1:
			Background background = new Background("background");
			Ground ground = new Ground("ground");
			background.setScale(0.7);
			background.setScaleX(1.2);
			ground.setScale(1.5);
			ground.setScaleX(2.5);
			ground.setPosition(-100, 490);
			this.addChild(background);
			this.addChild(ground);
			break;
		}
	}
//	public Level1(String id, String filename) {
//		super(id, filename);
//	}
}