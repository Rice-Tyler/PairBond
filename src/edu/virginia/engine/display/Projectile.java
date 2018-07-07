package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.events.ProjectileEvent;
import edu.virginia.engine.util.GameClock;

public class Projectile extends Sprite implements IEventListener {
	private int fuse = 100;
	private int countdown = 0;
	private Double spread = 0.0;
	private Explosion exp;
	private String BlastType = "Round";
	
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
//		clock = new GameClock();
		this.exp=new Explosion(this.getId(),3,100,BlastType,60.0);
		this.setScale(.1);
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
		this.addChild(exp);
	}
	public Double getSpread() {
		return spread;
	}
	public void setSpread(Double spread) {
		this.spread = spread;
	}
	public Explosion getExp() {
		return exp;
	}
	public void setExp(Explosion exp) {
		this.exp = exp;
	}
	public String getBlastType() {
		return BlastType;
	}
	public void setBlastType(String blastType) {
		BlastType = blastType;
	}
	public int getFuse() {
		return fuse;
	}
	public void setFuse(int fuse) {
		this.fuse = fuse;
	}
	public int getCountdown() {
		return countdown;
	}
	public void incCountdown() {
		this.countdown++;
	}
	public ArrayList<Projectile> getSubmunition() {
		return submunition;
	}
	public void setSubmunition(ArrayList<Projectile> submunition) {
		this.submunition = submunition;
	}
	public void addSubmunition(Projectile p) {
		p.setFuse(p.fuse+this.fuse);
		this.submunition.add(p);
		this.addChild(p);
	}
	@Override
	public void handleEvent(Event event) {
		if(event.getEventType() == ProjectileEvent.PROJECTILE_EXPLODE) {
			ProjectileEvent e = (ProjectileEvent)event;
			if(e.getId() == this.getId()) {
				this.setSolid(false);
				this.setVisible(false);
			}
		}
	}
	
}
