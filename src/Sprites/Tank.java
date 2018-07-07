package Sprites;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import java.awt.Point;

public class Tank extends Sprite {//DisplayObject {
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
		default: 
			this.readImage("Tank1.png");
		}
	}
	
	public Double getGunRotation() {
		return this.getChild(0).getRotation();
	}
	
	public void setGunRotation(double rotation) {
		this.getChild(0).setRotation(rotation);
	}
}
