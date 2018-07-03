package edu.virginia.engine.display;

public class Projectile extends AnimatedSprite {
	private Double fuse = 0.0;
	public Projectile(String id) {
		super(id,"moons.png");
		Integer mwidth = Math.floorDiv(this.getUnscaledWidth(),8);
		Integer mheight = Math.floorDiv(this.getUnscaledHeight(),8);
		this.splitFromSpriteSheet(mwidth, mheight, 8, 8, 0);
		this.add_animation("spin", 1, 64);
		this.setBegin(1);
		this.setEnd(3);
		this.set_animation("spin");
		this.animate();
		this.setScale(1.0);
		this.setSolid(false);
	}
}
