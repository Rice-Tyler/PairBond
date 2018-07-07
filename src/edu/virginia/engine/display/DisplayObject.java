package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.util.Stack;
import javax.imageio.ImageIO;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.EventDispatcher;
import edu.virginia.engine.events.IEventListener;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject extends EventDispatcher implements IEventListener{

	/* All DisplayObject have a unique id */
	private String id;
	private Point position = new Point();
	private Point pivotPoint = new Point();
	private Double scaleX = 1.0;
	private Double scaleY = 1.0;
	private Double rotation = 0.0;
	private float alpha = 1.0f;
	private boolean Visible = true;
	private boolean flip = false;
	private Point center = new Point();
	private Integer xVelocity = 0;
	private Integer yVelocity = 0;
	private Integer xAcc = 0;
	private Integer yAcc = 0;
	private Integer xMax = 10;
	private Integer yMax = 10;
	private Double friction = .90;
	private Double mass = 1.0;
	private DisplayObjectContainer parent = null;
	/* The image that is displayed by this object */
	private BufferedImage displayImage;
	private boolean solid = false;
	private Stack<Point> forces = new Stack<Point>();
	private boolean NormalUp = false;
//	private boolean reverse = false;
	private String hitboxShape = "Rect";
	private Double radius = 0.0;
	private Integer height = 0;
	private Integer width = 0;

	public boolean isNormalUp() {
		return NormalUp;
	}
	public void setNormalUp(boolean normalUp) {
		NormalUp = normalUp;
	}
	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	
	public boolean isFlip() {
		return flip;
	}
	public void setFlip(boolean flip) {
		this.flip = flip;
	}
	
	public Double getRotation() {
		return rotation;
	}

	public boolean isVisible() {
		return Visible;
	}

	public void setVisible(boolean visible) {
		Visible = visible;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public DisplayObject(String id) {
		this.setId(id);
		
	}

	public Point getPosition() {
		return this.position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}

	public void setPosition(Integer x, Integer y) {
		this.position.setLocation(x,y);
	}
	
	public void setPosition(Double x, Double y) {
		this.position.setLocation(x,y);
	}

	public Point getPivotPoint() {
		return pivotPoint;
	}
	
	public void setPivotPoint(Point pivotPoint) {
		this.pivotPoint = pivotPoint;
	}

	public void setPivotPoint(Integer x, Integer y) {
		this.pivotPoint.setLocation(x,y);
	}
	
	public void setPivotPoint(Double x, Double y) {
		this.pivotPoint.setLocation(x,y);
	}

	public Double getScaleX() {
		return scaleX;
	}

	public void setScaleX(Double scaleX) {
		this.scaleX = scaleX;
	}

	public Double getScaleY() {
		return scaleY;
	}

	public void setScaleY(Double scaleY) {
		this.scaleY = scaleY;
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.height = this.displayImage.getHeight();
		this.width = this.displayImage.getWidth();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setScale(Double x) {
		this.scaleX=x;
		this.scaleY=x;
	}

	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		else if(this.width == 0) {
			this.width = displayImage.getWidth();
		}
		return this.width;
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		else if(this.height == 0) {
			this.height = displayImage.getHeight();
		}
		return this.height;
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	public DisplayObjectContainer getParent() {
		return parent;
	}
	public void setParent(DisplayObjectContainer parent) {
		this.parent = parent;
	}
	public Integer getxVelocity() {
		return xVelocity;
	}
	public void setxVelocity(Integer xVelocity) {
		this.xVelocity = xVelocity;
	}
	public Integer getyVelocity() {
		return yVelocity;
	}
	public void setyVelocity(Integer yVelocity) {
		this.yVelocity = yVelocity;
	}
	public Integer getxAcc() {
		return xAcc;
	}
	public void setxAcc(Integer xAcc) {
		this.xAcc = xAcc;
	}
	public Integer getyAcc() {
		return yAcc;
	}
	public void setyAcc(Integer yAcc) {
		this.yAcc = yAcc;
	}
	public Integer getxMax() {
		return xMax;
	}
	public void setxMax(Integer xMax) {
		this.xMax = xMax;
	}
	public Integer getyMax() {
		return yMax;
	}
	public void setyMax(Integer yMax) {
		this.yMax = yMax;
	}
	public Double getFriction() {
		return friction;
	}
	public void setFriction(Double friction) {
		this.friction = friction;
	}
	public Double getMass() {
		return mass;
	}
	public void setMass(Double mass) {
		this.mass = mass;
	}
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	public String getHitboxShape() {
		return hitboxShape;
	}
	public void setHitboxShape(String hitboxShape) {
		this.hitboxShape = hitboxShape;
	}
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d,this);
			float alpha = this.getAlpha();
			if(!this.Visible)alpha = 0.0f;
			Composite Com = g2d.getComposite();
			AlphaComposite Alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g2d.setComposite(Alcom);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, this.pivotPoint.x, this.pivotPoint.y,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			g2d.setComposite(Com);
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d, this);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d, DisplayObject d) {
		Point Pos = d.getPosition();
		Double theta = d.getRotation();
		Double scalex = d.getScaleX();
		Double scaley = d.getScaleY();
		boolean flip = d.isFlip();
		Point center = d.center;
		//Move to position
		g2d.translate(Pos.getX(), Pos.getY());
		//Rotate & Scale
		g2d.rotate(theta);
		g2d.scale(scalex, scaley);
		
		if(flip) {
			g2d.translate(center.x, center.y);
			g2d.scale(-1.0, 1.0);
			g2d.translate(-center.x, -center.y);
		}
		
		
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d, DisplayObject d) {
		Point Pos = d.getPosition();
		Double theta = d.getRotation();
		Double scalex = 1/d.getScaleX();
		Double scaley = 1/d.getScaleY();
		boolean flip = d.isFlip(); 
		//Un-Rotate & Scale
		if(flip) {
			g2d.translate(center.x, center.y);
			g2d.scale(-1.0, 1.0);
			g2d.translate(-center.x, -center.y);
		}
		g2d.scale(scalex, scaley);
		g2d.rotate(-theta);
		//Unset Position
		g2d.translate(-Pos.getX(), -Pos.getY());
		
	}
	public void findCenter() {
		Integer x = -this.getUnscaledWidth()/2;
		Integer y = -this.getUnscaledHeight()/2;
		this.pivotPoint = new Point(x,y);
	}
	
	public void centerSystem() {
		this.findCenter();
	}
	public boolean equals(Object o){
		if(o instanceof DisplayObject) {
			if (((DisplayObject)o).getId().equals(this.getId())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	public Point localToGlobal(Point pos, DisplayObject d) {
		if(d.getParent()!= null) {
			Point p = d.getPosition();
//			System.out.println(d.parent.getId());
			return localToGlobal(new Point(p.x+pos.x,p.y+pos.y),d.parent);
			
		}
		else {
			return pos;
		}
	}
	public AffineTransform getGlobalTransform(){
		AffineTransform at;
		if(this.parent == null) at = new AffineTransform();
		else at = this.getParent().getGlobalTransform();
		
		at.concatenate(getLocalTransform());
		return at;
	}
		
	public AffineTransform getLocalTransform(){
		AffineTransform at = new AffineTransform();
		at.translate(this.position.x, this.position.y);
		at.rotate(this.rotation);
		at.scale(this.scaleX, this.scaleY);
		at.translate(this.pivotPoint.x, this.pivotPoint.y);
		return at;
	}
	public Shape getGlobalHitbox(){
		if(this.hitboxShape == "Round")return getGlobalTransform().createTransformedShape(new Ellipse2D.Double(0.0,0.0,radius,radius));
		else return getGlobalTransform().createTransformedShape(new Rectangle(0, 0, getUnscaledWidth(), getUnscaledHeight()));
	}
		
	public Shape getLocalHitbox(){
		if(this.hitboxShape == "Round")return getLocalTransform().createTransformedShape(new Ellipse2D.Double(0.0,0.0,radius,radius));
		else return getLocalTransform().createTransformedShape(new Rectangle(0, 0, getUnscaledWidth(), getUnscaledHeight()));
	}
	public Stack<Point> getForces() {
		return forces;
	}
	public void setForces(Stack<Point> forces) {
		this.forces = forces;
	}
	public boolean collidesWith(DisplayObject other){
		Area a = new Area(getGlobalHitbox());
		a.intersect(new Area(other.getGlobalHitbox()));
		return !a.isEmpty();
	}
	public void xfriction() {
		int t_force = 0;
		if (xVelocity>0) {
			t_force =-(int)Math.ceil(xVelocity*friction);
			if(xVelocity+xAcc<0)t_force=-xVelocity;
		}
		if (xVelocity<0) {
			t_force=(int)Math.ceil(-xVelocity*friction);
			if(xAcc+xVelocity>0)t_force=-xVelocity;
		}
		this.push_force(t_force, 0);
	}
	public void yfriction() {
		if (yVelocity>0) {
			yAcc=-(int)Math.ceil(yVelocity*friction);
			if(yVelocity+yAcc<0)yAcc=-yVelocity;
		}
		if (yVelocity<0) {
			yAcc=(int)Math.ceil(yVelocity*friction);
			if(yAcc+yVelocity>0)yAcc=-yVelocity;
		}
	}
	public void move() {
		this.eval_force();
		this.xVelocity +=this.xAcc;
		this.yVelocity +=this.yAcc;
		if(Math.abs(xVelocity)>xMax) {
			if(xVelocity>0)xVelocity = xMax;
			if(xVelocity<0)xVelocity = -xMax;
//			System.out.printf("%s: setx \n",this.getId());
		}
		if(Math.abs(yVelocity)>yMax) {
			if(yVelocity>0)yVelocity = yMax;
			if(yVelocity<0)yVelocity = -yMax;
//			System.out.printf("%s: sety \n",this.getId());
		}
		this.position = new Point(this.getPosition().x+xVelocity,this.getPosition().y+yVelocity);
	}
	public boolean isSolid() {
		return this.solid;
	}
	public void eval_force() {
		Point total = new Point();
		while(!this.forces.empty()) {
			Point p = forces.pop();
			total = new Point(total.x+p.x,total.y+p.y);
		}
		
		this.xAcc = (int) (total.x/this.mass);
		this.yAcc = (int) (total.y/this.mass);
		if(NormalUp) {
//			System.out.printf("%s: Normal \n",this.getId());
			this.yVelocity=0;
			this.yAcc=0;
		}
	}
	public void gravity() {
		this.forces.push(new Point(0,(int)(1)));
	}
	public void push_force(Point p) {
		this.forces.push(p);
	}
	public Point pop_force() {
		return this.forces.pop();
	}
	public void push_force(int x, int y) {
		this.push_force(new Point(x,y));
	}
	@Override
	public void handleEvent(Event event) {
		
	}
	public boolean collide(DisplayObject d1, DisplayObject d2) {
		Point g1 = d1.localToGlobal(d1.getPivotPoint(), d1);
		Point g2 = d2.localToGlobal(d2.getPivotPoint(), d2);
		Point l1 = d1.getPosition();
//		System.out.printf("%s:: x: %d y: %d xV: %d yV: %d \n",d1.getId(),g1.x,g1.y,d1.getxVelocity(),d1.getyVelocity());
//		System.out.printf("%s:: x: %d y: %d \n",d2.getId(),g2.x,g2.y);
		if(d1.isSolid()&&d2.isSolid()) {
			if((g1.y<g2.y)&(d1.getyVelocity()>d2.getyVelocity())) {
				d1.setPosition(l1.x,l1.y-d1.getyVelocity());
				d1.setyVelocity(0);
				return true;
			}
			else if((g1.y>g2.y)&(d1.getyVelocity()<d2.getyVelocity())) {
				d1.setPosition(l1.x,l1.y-d1.getyVelocity());
				d1.setyVelocity(0);
			}
			else if((g1.x<g2.x)&(d1.getxVelocity()>d2.getxVelocity())) {
				d1.setPosition(l1.x-d1.getxVelocity(),l1.y);
				d1.setxVelocity(0);
			}
			else if((g1.x>g2.x)&(d1.getxVelocity()<d2.getxVelocity())) {
				d1.setPosition(l1.x-d1.getxVelocity(),l1.y);
				d1.setxVelocity(0);
			}
		}
		return false;
	}
	public void turn(double angle) {
		
		Double ang = 0.0;
		if(this.getxVelocity()==0.0) {
			if(this.yVelocity>0) {
				ang = Math.PI/2;
			}
			else {
				ang = - Math.PI/2;
			}
		}
		else {
			 ang = Math.tan(this.getyVelocity()/this.getxVelocity());
		}
		angle = Math.toRadians(angle);
		System.out.printf("init: %f,",ang);
		System.out.printf("turn by: %f ", angle);
		double mag = Math.sqrt(Math.pow(this.getxVelocity(),2) + Math.pow(this.getyVelocity(),2));
		System.out.printf("total V: %f \n", mag);
		ang += angle;
		if(this.xVelocity <0)this.xVelocity = -(int)(mag* Math.cos(ang)); 
		else this.xVelocity = (int)(mag* Math.cos(ang)); 
		this.yVelocity = (int)(mag* Math.sin(ang)); 
	}
}
