package Sprites;

import java.awt.Point;
import java.awt.image.BufferedImage;

import edu.virginia.engine.display.AnimatedSprite;

public class Mario extends AnimatedSprite {
	public Mario(String id) {
		super(id);
//		this.setImage("mario_008.png");
		this.setWidth(34);
		this.setHeight(49);
		this.setCols(14);
		this.setRows(4);
		this.setBegin(1);
		this.setEnd(100);
		this.setFriction(.9);
		this.setxMax(20);
		this.setyMax(20);
//		this.setSprites(new BufferedImage[this.getRows()*this.getCols()]);
		Integer offset = 42;
		this.setCenter(new Point(17,0));
		Integer h2 = 26;
		BufferedImage img = this.readImage("mario_008.png");
		this.setSolid(true);
		
		System.out.printf("w: %s \n", img.getWidth());
		System.out.printf("h: %s \n", img.getHeight());
		BufferedImage img2 = img.getSubimage(1, 1, 489, 81); 
		this.addFramesFromSpriteSheet(img2, this.getWidth(), h2, 14, 3, 1);
//		for(Integer x=0; x<3; x++) {
//			for(Integer y=0;y<14;y++) {
////				System.out.println(count);
//				sprites[count] = img2.getSubimage(y*(1+this.getWidth()), x*(1+h2), this.getWidth(), h2);
//				count++;
//			}
//		}
		BufferedImage img3 = img.getSubimage(1, 82, 489, 199);
		this.addFramesFromSpriteSheet(img3, this.getWidth(), this.getHeight(), 14, 4, 1);
//		for(Integer x=0; x<this.getRows(); x++) {
//			for(Integer y=0;y<this.getCols();y++) {
////				System.out.println(count);
//				sprites[count] = img3.getSubimage(y*(1+this.getWidth()), x*(1+h2), this.getWidth(), h2);
//				count++;
//			}
//		}
		
		this.setImage(this.getSprites()[this.getBegin()]);
		this.add_animation("Lrun", 1, 7);
		this.add_animation("Lswim", 29, 35);
		this.add_animation("run", 43, 49);
		this.add_animation("swim", 29+offset, 35+offset);
		this.add_animation("climb", 35+offset, 37+offset);
		this.add_animation("aaah", 85, 88);
		this.set_animation("run");
	}
	public void move(int x, int y) {
		Point p = this.getPosition();
		this.setPosition(p.x+x, p.y+y);
	}
	

}
