package edu.virginia.engine.display;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class AnimatedSprite extends Sprite {
	private Integer width = 0;
	private Integer height = 0;
	private Integer cols = 0;
	private Integer rows = 0;
	private Integer current = 0;
	private Integer begin = 0;
	private Integer end = 1;
	private Integer speed = 11;
	private Integer count = 1;
	private Integer currentAnimation = 0;
	private Integer trim =5;
	private boolean paused = true;
	
	private HashMap<String,Integer[]> animations = new HashMap<String,Integer[]>(); 
	private BufferedImage[] sprites = new BufferedImage[rows * cols];
	public AnimatedSprite(String id, String fileName) {
		super(id,fileName);
	}
	public AnimatedSprite(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public AnimatedSprite(String id, String Image_name, Integer width, Integer height,
			Integer cols, Integer rows) {
		super(id);
		this.setImage(Image_name);
		this.width = width;
		this.height = height;
		this.cols = cols;
		this.rows = rows;
		
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}

	public boolean isPaused() {
		return this.paused;
	}
	public void setPause(boolean b) {
		this.paused = b;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getCols() {
		return cols;
	}
	public void setCols(Integer cols) {
		this.cols = cols;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCurrentAnimation() {
		return currentAnimation;
	}
	public void setCurrentAnimation(Integer currentAnimation) {
		this.currentAnimation = currentAnimation;
	}
	public Integer getTrim() {
		return trim;
	}
	public void setTrim(Integer trim) {
		this.trim = trim;
	}
	public HashMap<String, Integer[]> getAnimations() {
		return animations;
	}
	public void setAnimations(HashMap<String, Integer[]> animations) {
		this.animations = animations;
	}
	public BufferedImage[] getSprites() {
		return sprites;
	}
	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}
	public void splitFromSpriteSheet(Integer width, Integer height,
			Integer cols, Integer rows, Integer offset) {
		BufferedImage img = this.getDisplayImage();
		BufferedImage[] tsp = new BufferedImage[(rows*cols)];
		int place = 0;
		while(place<this.sprites.length) {
			tsp[place] = sprites[place];
			place++;
		}
 		for(Integer x=0; x<rows; x++) {
			for(Integer y=0;y<cols;y++) {
//				System.out.println(count);
				tsp[place] = img.getSubimage(y*(width+offset), x*(height+offset), width, height);
				place++;
			}
		}
 		this.sprites = tsp;	
	}
	public void addFramesFromSpriteSheet(BufferedImage sheet, Integer width, Integer height,
			Integer cols, Integer rows, Integer offset) {
		BufferedImage img = sheet;
		BufferedImage[] tsp = new BufferedImage[(rows*cols + this.sprites.length)];
		int place = 0;
		while(place<this.sprites.length) {
			tsp[place] = sprites[place];
			place++;
		}
 		for(Integer x=0; x<3; x++) {
			for(Integer y=0;y<14;y++) {
//				System.out.println(count);
				tsp[place] = img.getSubimage(y*(width+offset), x*(height+offset), width, height);
				place++;
			}
		}
 		this.sprites = tsp;
 		
		
	}
	public void add_animation(String s, Integer b, Integer f) {
		Integer set[] = new Integer[] {b,f};
		this.animations.put(s,set);
		
	}
	public void set_animation(String s) {
		if(this.animations.containsKey(s)) {
			String[] keys = animations.keySet().toArray(new String[animations.keySet().size()]);
//			System.out.println(s);
//			System.out.println(currentAnimation);
			currentAnimation = Arrays.asList(keys).indexOf(s);
			Integer[] set = this.animations.get(s);
			this.begin = set[0];
			this.end = set[1];
			current = this.begin;
		}
		else {
			System.out.println("Error Animation Not Found");
		}	
	}
	public void cycle_animation() {
		String[] keys = animations.keySet().toArray(new String[animations.keySet().size()]);
		List<String> Ks = Arrays.asList(keys);
		Integer newAn = currentAnimation;
		newAn += 1;
//		System.out.println(Ks);
		newAn = newAn%Ks.size();
		
		this.set_animation(Ks.get(newAn));
		
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public void animate() {
		this.count++;
		this.count = count%speed;
		if(count==0) {
			this.current++;
			this.current = current%(end-begin);
			this.setImage(sprites[this.current+begin]);
		}
	}
	
	
}
