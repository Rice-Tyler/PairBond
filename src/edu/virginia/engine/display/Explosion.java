package edu.virginia.engine.display;

public class Explosion extends DisplayObject {
	int damage = 0;
	int duration = 0;
	public Explosion(String id, String fileName, int damage, int duration,String blastType, double radius) {
		super(id, fileName);
		this.damage = damage;
		this.duration = duration;
		this.setHitboxShape(blastType);
		this.setRadius(radius);
	}
	public Explosion(String id, int damage, int duration, String blastType, double radius) {
		super(id);
		this.damage = damage;
		this.duration = duration;
		this.setHitboxShape(blastType);
		this.setRadius(radius);
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
