package Sprites;

import edu.virginia.engine.display.AnimatedSprite;

public class ExplosionSprite extends AnimatedSprite {
	public ExplosionSprite(String id,  String Image_name, Integer width, Integer height,
		Integer cols, Integer rows) {
		super(id, Image_name, width, height, cols, rows);
		

		Integer mwidth = Math.floorDiv(this.getUnscaledWidth(), cols);
		Integer mheight = Math.floorDiv(this.getUnscaledHeight(), rows);
		this.splitFromSpriteSheet(mwidth, mheight, cols, rows, 0);
		this.add_animation("explode", 1, 15);
		this.setBegin(1);
		this.setEnd(15);
		this.set_animation("explode");
		this.setSpeed(2);
		this.setCount(this.getSpeed());
		this.animate();
	}
}
