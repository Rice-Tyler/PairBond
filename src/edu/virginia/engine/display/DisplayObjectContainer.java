package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {
	ArrayList<DisplayObject> children = new ArrayList<DisplayObject>();
	public Integer num_children = 0;

	public DisplayObjectContainer(String id, String fileName) {
		super(id, fileName);
		this.children = new ArrayList<DisplayObject>();
	}

	public DisplayObjectContainer(String id) {
		super(id);
		this.children = new ArrayList<DisplayObject>();
	}
	
	public DisplayObjectContainer(String id, ArrayList<DisplayObject> children) {
		super(id);
		this.children = children;
	}
	
	public DisplayObjectContainer(String id, String fileName, ArrayList<DisplayObject> children) {
		super(id, fileName);
		this.children = children;
	}
	
	public DisplayObjectContainer(String id, DisplayObjectContainer parent) {
		super(id);
		this.setParent(parent);
		parent.addChild(this);
	}
	
	public DisplayObjectContainer(String id, String fileName, DisplayObjectContainer parent) {
		super(id, fileName);
		this.setParent(parent);
		parent.addChild(this);
	}

	public DisplayObjectContainer(String id, DisplayObjectContainer parent, ArrayList<DisplayObject> children) {
		super(id);
		this.setParent(parent);
		parent.addChild(this);
		this.children = children;
	}
	
	public DisplayObjectContainer(String id, String fileName, DisplayObjectContainer parent, ArrayList<DisplayObject> children) {
		super(id, fileName);
		this.setParent(this);
		this.children = children;
	}

	public ArrayList<DisplayObject> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<DisplayObject> children) {
		this.children = children;
		for(int x=0;x<this.children.size();x++) {
			children.get(x).setParent(this);
		}
		this.num_children = children.size();
	}
	
	public void addChild(DisplayObject child) {
		this.children.add(child);
		child.setParent(this);
		this.num_children++;
	}
	public void addChildAtIndex(Integer index, DisplayObject child) {
		this.children.add(index, child);
		child.setParent(this);
		this.num_children++;
	}
	
	public boolean removeChild(DisplayObject o) {
		o.setParent(null);
		return this.children.remove(o);
		
	}
	
	public void removeChildAtIndex(int x) {
		this.children.remove(x);
	}
	public boolean removeChildById(String id) {
		DisplayObject d = new DisplayObject(id);
		return this.children.remove(d);
	}
	
	public void removeAll() {
		for(int x = 0; x < this.num_children;x++) {
			this.children.get(x).setParent(null);
		}
		this.children = new ArrayList<DisplayObject>();
	}
	public DisplayObject getChild(String id) {
		for(int z = 0; z < this.getChildren().size();z++) {
			//if(z<0)z=0;
			if(this.children.get(z).getId() == id) {
				return this.children.get(z);
			}
		}
		return null;
	}
	
	public DisplayObject getChild(int x) {
		if(x<this.getChildren().size()) {
			if(x<0)x=0;
			return this.children.get(x);
		}
		else {
			return null;
		}
	}
	
	public boolean contains(DisplayObject Do) {
		return this.children.contains(Do); 
	}
	
	@Override
	public void draw(Graphics g) {
		/*
		 * Get the graphics and apply this objects transformations
		 * (rotation, etc.)
		 */
		Graphics2D g2d = (Graphics2D) g;
		applyTransformations(g2d,this);
		float alpha = this.getAlpha();
		if(!this.isVisible())alpha = 0.0f;
		Composite Com = g2d.getComposite();
		AlphaComposite Alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2d.setComposite(Alcom);
		/* Actually draw the image, perform the pivot point translation here */
		if (this.getDisplayImage() != null) {
			g2d.drawImage(this.getDisplayImage(), this.getPivotPoint().x, this.getPivotPoint().y,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
		}
		if(this.isVisible()) {
			if(!this.children.equals(null)) {
				for(int x=0;x<this.children.size();x++) {
					this.children.get(x).draw(g2d);
				}
			}
		}
		
		/*undo the transformations so this doesn't affect other display objects*/
		g2d.setComposite(Com);
		reverseTransformations(g2d, this);
		
	}
	
	
	@Override
	public void centerSystem() {
		if(this!=null) {
//			System.out.println("centered");
			this.findCenter();
			if(this.children!=null) {
				for(int x=0;x<this.children.size();x++) {
					this.children.get(x).centerSystem();
				}
			}
		}
	}
	public Point GlobalToLocal(Point pos, DisplayObjectContainer d) {
		if(this.children.equals(null)) {
			return pos;
		}
		else {
			Point p = this.getPosition();
			return GlobalToLocal(new Point(pos.x-p.x,pos.y-p.y),this.getParent());
		}
	}
}


