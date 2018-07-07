package Sprites;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import java.awt.Point;

public class Tank extends Sprite {
	private int health = 100;
	public boolean onGround = true; 
	public boolean move = false;
	public double angle = 90.0;
	public Tank(String id, String filename, int n) {
		super(id, filename);
		switch (n) {
		case 1:
			//this.readImage("Tank1.png");
			Tankgun gun1 = new Tankgun("gun1", "Gun1.png");
			gun1.setScale(0.8);
			gun1.setScaleY(1.3);
			gun1.setPosition(60, 10);
			gun1.setPivotPoint(-5, -50);
			this.addChild(gun1);
			//setImage(readImage("Tank1.png"));
			break;
		case 2:
			//this.readImage("Tank2.png");
			Tankgun gun2 = new Tankgun("gun2", "Gun2.png");
			gun2.setScale(0.8);
			gun2.setScaleY(1.3);
			gun2.setPosition(60, 10);
			gun2.setPivotPoint(-5, -50);
			this.addChild(gun2);
			break;
		case 3:
			//this.readImage("Tank3.png");
			Tankgun gun3 = new Tankgun("gun3", "Gun3.png");
			gun3.setScale(0.8);
			gun3.setScaleY(1.3);
			gun3.setPosition(60, 10);
			gun3.setPivotPoint(-5, -50);
			this.addChild(gun3);
			//setImage(readImage("Tank3.png"));
			break;
		case 4:
			//this.readImage("Tank4.png");
			Tankgun gun4 = new Tankgun("gun4", "Gun4.png");
			gun4.setScale(0.8);
			gun4.setScaleY(1.3);
			gun4.setPosition(60, 10);
			gun4.setPivotPoint(-5, -50);
			this.addChild(gun4);
			break;
		default: 
			this.readImage("Tank1.png");
		}
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