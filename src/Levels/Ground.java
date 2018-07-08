package Levels;

import edu.virginia.engine.display.Sprite;

public class Ground extends Sprite {
	public Ground(String id) {
		super(id,"Ground1.png");
		this.setSolid(true);
	}
}