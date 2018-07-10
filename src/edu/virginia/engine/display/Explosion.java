package edu.virginia.engine.display;

public class Explosion extends DisplayObject {
	int damage = 5;
	int duration = 5;
	int count = 0;
	public Explosion(String id, String fileName, int damage, int duration,String blastType, double diameter) {
		super(id, fileName);
		this.damage = damage;
		this.duration = duration;
		this.setHitboxShape(blastType);
		this.setdiameter(diameter);
	}
	public Explosion(String id, int damage, int duration, String blastType, double diameter, int height, int width) {
		super(id);
		this.setHeight(height);
		this.setWidth(width);
		this.damage = damage;
		this.duration = duration;
		this.setHitboxShape(blastType);
		this.setdiameter(diameter);
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void incCount() {
		this.count++;
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
