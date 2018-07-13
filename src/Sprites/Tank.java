package Sprites;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tank extends Sprite {
	private int health = 100;
	public boolean onGround = true; 
	public boolean move = false;
	public double angle = 180.0;
	private int power = 50;
	private double fuel = 100;
	public static final double maxFuel = 100;
	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	private Tankgun gun = null;
	private DisplayObject healthbar = null;
	private DisplayObject healthMeter = null;
	public int getPower() {
		return power;
	}

	public DisplayObject getHealthbar() {
		return healthbar;
	}

	public void setHealthbar(DisplayObject healthBar1) {
		this.healthbar = healthBar1;
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
			DisplayObject healthBar1 = new DisplayObject("healthbar1", "Healthbar.png");
			DisplayObject health1 = new DisplayObject("health", "Health.png");
			gun1.setScale(0.8);
			gun1.setScaleY(1.4);
			gun1.setPosition(x, y);
			gun1.setRotation(Math.toRadians(angle));
			gun1.setPivotPoint(tpx, tpy);
			gun = gun1;
			health1.setPosition(-60, 0);
			health1.setScale(0.3);
			healthBar1.setPosition(-60, 0);
			healthBar1.setScale(0.3);
			this.addChild(gun1);
			this.addChild(health1);
			this.addChild(healthBar1);
			this.setHealthbar(healthBar1);
			this.setHealthMeter(health1);
			//setImage(readImage("Tank1.png"));
			break;
		case 2:
			//this.readImage("Tank2.png");
			this.setPivotPoint(-50,-50);
			Tankgun gun2 = new Tankgun("gun2", "Gun2.png");
			DisplayObject healthBar2= new DisplayObject("healthba2r", "Healthbar.png");
			DisplayObject health2 = new DisplayObject("health", "Health.png");
			gun2.setScale(0.8);
			gun2.setScaleY(1.3);
			gun2.setPosition(x, y);
			gun2.setRotation(Math.toRadians(angle));
			gun2.setPivotPoint(tpx, tpy);
			gun = gun2;
			health2.setPosition(-60, 0);
			health2.setScale(0.3);
			healthBar2.setPosition(-60, 0);
			healthBar2.setScale(0.3);
			this.addChild(gun2);
			this.addChild(health2);
			this.addChild(healthBar2);
			this.setHealthbar(healthBar2);
			this.setHealthMeter(health2);
			break;
		case 3:
			//this.readImage("Tank3.png");
			this.setPivotPoint(-50,-50);
			Tankgun gun3 = new Tankgun("gun3", "Gun3.png");
			DisplayObject healthBar3= new DisplayObject("healthbar3", "Healthbar.png");
			DisplayObject health3 = new DisplayObject("health", "Health.png");
			gun3.setScale(0.8);
			gun3.setScaleY(1.3);
			gun3.setPosition(x, y);
			gun3.setRotation(Math.toRadians(angle));
			gun3.setPivotPoint(tpx, tpy);
			gun = gun3;
			health3.setPosition(-60, 0);
			health3.setScale(0.3);
			healthBar3.setPosition(-60, 0);
			healthBar3.setScale(0.3);
			this.addChild(gun3);
			this.addChild(health3);
			this.addChild(healthBar3);
			this.setHealthbar(healthBar3);
			this.setHealthMeter(health3);
			//setImage(readImage("Tank3.png"));
			break;
		case 4:
			//this.readImage("Tank4.png");
			this.setPivotPoint(-50,-50);
			Tankgun gun4 = new Tankgun("gun4", "Gun4.png");
			DisplayObject healthBar4= new DisplayObject("healthbar4", "Healthbar.png");
			DisplayObject health4 = new DisplayObject("health", "Health.png");
			gun4.setPosition(x, y);
			gun4.setRotation(Math.toRadians(angle));
			gun4.setPivotPoint(tpx, tpy);
			gun = gun4;
			health4.setPosition(-60, 0);
			health4.setScale(0.3);
			healthBar4.setPosition(-60, 0);
			healthBar4.setScale(0.3);
			this.addChild(gun4);
			this.addChild(health4);
			this.addChild(healthBar4);
			this.setHealthbar(healthBar4);
			this.setHealthMeter(health4);
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
//		System.out.println(this.getHealth());
		double ratio = this.getHealth()/100.0;
//		System.out.println(ratio);
		double x = this.getChild("health").getUnscaledWidth() * ratio;
//		System.out.println(x);
		int bleh = (int) x;
		if (this.getHealth()<0) {
			this.getHealthbar().setVisible(false);
			this.getHealthMeter().setVisible(false);
		}
//		System.out.println(bleh);
		if(bleh > 0) {
			BufferedImage newHealth = readImage("Health.png").getSubimage(0, 0, bleh, this.getChild("health").getUnscaledHeight());
			this.getChild("health").setImage(newHealth);
		}
	}

	public DisplayObject getHealthMeter() {
		return healthMeter;
	}

	public void setHealthMeter(DisplayObject healthMeter) {
		this.healthMeter = healthMeter;
	}
}