package Sprites;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tank extends Sprite {
	private int health = 100;
	public boolean onGround = true; 
	public boolean move = false;
	public double angle = 180.0;
	private int power = 50;
	private Tankgun gun = null;
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Tank(String id, String filename, int n) {
		super(id, filename);
		this.setSolid(true);
		BufferedImage img = readImage("Gun1.png");
		int x = 5;
		int y = -46;
		int tpx = -8;
		int tpy = 0;
		switch (n) {
		case 1:
			//this.readImage("Tank1.png");
			this.setPivotPoint(-50,-50);
			Tankgun gun1 = new Tankgun("gun1", "Gun1.png");
			gun1.setScale(0.8);
			gun1.setScaleY(1.4);
			gun1.setPosition(x, y);
			gun1.setRotation(Math.toRadians(angle));
			gun1.setPivotPoint(tpx, tpy);
			gun = gun1;
			this.addChild(gun1);
			//setImage(readImage("Tank1.png"));
			break;
		case 2:
			//this.readImage("Tank2.png");
			this.setPivotPoint(-50,-50);
			Tankgun gun2 = new Tankgun("gun2", "Gun2.png");
			gun2.setScale(0.8);
			gun2.setScaleY(1.3);
			gun2.setPosition(x, y);
			gun2.setRotation(Math.toRadians(angle));
			gun2.setPivotPoint(tpx, tpy);
			gun = gun2;
			this.addChild(gun2);
			break;
		case 3:
			//this.readImage("Tank3.png");
			this.setPivotPoint(-50,-50);
			Tankgun gun3 = new Tankgun("gun3", "Gun3.png");
			gun3.setScale(0.8);
			gun3.setScaleY(1.3);
			gun3.setPosition(x, y);
			gun3.setRotation(Math.toRadians(angle));
			gun3.setPivotPoint(tpx, tpy);
			gun = gun3;
			this.addChild(gun3);
			//setImage(readImage("Tank3.png"));
			break;
		case 4:
			//this.readImage("Tank4.png");
			this.setPivotPoint(-50,-50);
			Tankgun gun4 = new Tankgun("gun4", "Gun4.png");
			gun4.setScale(0.8);
			gun4.setScaleY(1.3);
			gun4.setPosition(x, y);
			gun4.setRotation(Math.toRadians(angle));
			gun4.setPivotPoint(tpx, tpy);
			gun = gun4;
			this.addChild(gun4);
			break;
		default: 
			this.readImage("Tank1.png");
		}
	}
	
	public Tankgun getGun() {
		return gun;
	}

	public void setGun(Tankgun gun) {
		this.gun = gun;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Double getGunRotation() {
		return this.getChild(0).getRotation();
	}
	
	public void setGunRotation(double rotation) {
		this.getChild(0).setRotation(rotation);
	}
	public void decreaseHealth(int i) {
		this.setHealth(this.getHealth()-i);
	}
}