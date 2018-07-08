package Levels;

import edu.virginia.engine.display.Sprite;

public class Level1 extends Sprite {
	public Level1(String id, int n) {
		super(id);
		switch (n) {
		case 1:
			Background background = new Background("background", "Background_field.png");
			Ground ground = new Ground("ground", "Ground1.png");
			background.setScale(0.7);
			ground.setScale(1.5);
			ground.setPosition(-50, 490);
			this.addChild(background);
			this.addChild(ground);
			break;
		}
	}
//	public Level1(String id, String filename) {
//		super(id, filename);
//	}
}