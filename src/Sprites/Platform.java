package Sprites;

import java.awt.image.BufferedImage;

import edu.virginia.engine.display.Sprite;

public class Platform extends Sprite {

	public Platform(String id) {
		super(id);
		BufferedImage img = readImage("plat.png");
		int woff = 40;
		int hoff = 240;
		this.setImage(img.getSubimage(woff, hoff, img.getWidth()-woff-100, img.getHeight()-hoff-110));
		this.setVisible(true);
		this.setSolid(true);
		this.setVisible(true);
		this.setScale(1.00);
		// TODO Auto-generated constructor stub
	}

}
