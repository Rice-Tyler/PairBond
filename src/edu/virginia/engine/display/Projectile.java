package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.events.ProjectileEvent;
import edu.virginia.engine.util.GameClock;

public class Projectile extends Sprite implements IEventListener {
	private Double fuse = 1000.0;
	private GameClock clock;
	private Double spread = 0.0;
	private String Blast;
	private String BlastType = "Round";
	private Integer radius;
	
	private ArrayList<Projectile> submunition = new ArrayList<Projectile>();
	public Projectile(String id) {
		super(id,"proj.png");
//		Integer mwidth = Math.floorDiv(this.getUnscaledWidth(),8);
//		Integer mheight = Math.floorDiv(this.getUnscaledHeight(),8);
//		this.splitFromSpriteSheet(mwidth, mheight, 8, 8, 0);
//		this.add_animation("spin", 1, 64);
//		this.setBegin(1);
//		this.setEnd(3);
//		this.set_animation("spin");
//		this.animate();
		clock = new GameClock();
		this.setScale(.2);
		this.setSolid(false);
		this.setNormalUp(false);
		this.setMass(1.0);
		this.setyMax(100);
		this.setxMax(100);
		for(int x = 0; x<submunition.size();x++) {
			Projectile sub = submunition.get(x);
			sub.setSolid(false);
			this.addChild(sub);
		}
	}
	public Double getFuse() {
		return fuse;
	}
	public void setFuse(Double fuse) {
		this.fuse = fuse;
	}
	public GameClock getClock() {
		return clock;
	}
	public void setClock(GameClock clock) {
		this.clock = clock;
	}
	public ArrayList<Projectile> getSubmunition() {
		return submunition;
	}
	public void setSubmunition(ArrayList<Projectile> submunition) {
		this.submunition = submunition;
	}
	@Override
	public void handleEvent(Event event) {
		if(event.getEventType() == ProjectileEvent.PROJECTILE_EXPLODE) {
			ProjectileEvent e = (ProjectileEvent)event;
			if(e.getId() == this.getId())
				this.setSolid(false);
				this.setVisible(false);
				for(int x = 0; x<submunition.size();x++) {
					Projectile sub = submunition.get(x);
					sub.setSolid(true);
					sub.setVisible(true);
			}
		}
	}
	
}
