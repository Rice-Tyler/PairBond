
package PairBond;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import Sprites.Coin;
import Sprites.Mario;
import Sprites.Platform;
import Sprites.Tank;
import edu.virginia.engine.animation.Tween;
import edu.virginia.engine.animation.TweenJuggler;
//import edu.virginia.engine.animation.TweenTransitions;
import edu.virginia.engine.animation.TweenableParams;
import edu.virginia.engine.animation.TweenableParams.Tweenables;
import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Explosion;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Projectile;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.CollisionEvent;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.PlayerEvent;
import edu.virginia.engine.events.ProjectileEvent;
import edu.virginia.engine.events.SoundEvent;
import edu.virginia.engine.events.TweenEvent;
import edu.virginia.engine.sound.SoundManager;

public class Prototype extends Game {
	Tank tank1 = new Tank("tank1", "Tank2.png", 2);
	Tank tank2 = new Tank("tank2","Tank1.png",1);
	SoundManager SM = new SoundManager();
	TweenJuggler TJ = new TweenJuggler();
	Platform p1 = new Platform("p1");
	DisplayObjectContainer Plats = new DisplayObjectContainer("Plats");
	DisplayObjectContainer Proj = new DisplayObjectContainer("Proj");
	DisplayObjectContainer Tanks = new DisplayObjectContainer("Tanks");
	/* Container to hold Explosion Hitboxes*/
	DisplayObjectContainer EXP = new DisplayObjectContainer("EXP");
	ArrayList<String> WeaponSelect = new ArrayList<String>();
	boolean jump = false;
	boolean Switch = true;
	boolean fire = true;
	boolean pause_movement = false;
	public boolean onGround = false;
	int f_count = 181;
	double velocity = 25.0;
	double angle = 90.0;
	int player = 0;
	int weapon = 0;
	int Switch_count = 19;
	
	ArrayList<DisplayObject> PlayerSelect = Tanks.getChildren();
	public Prototype() {
		super("Prototype", 1500, 1500);
		this.setDisplay();
		this.Scale();
		this.Position();
		this.findCenter();
		this.addEventListener(this, ProjectileEvent.PROJECTILE_FIRED);
		this.addEventListener(this, ProjectileEvent.PROJECTILE_EXPLODE);
		this.addEventListener(this, PlayerEvent.FIRE);
		this.addEventListener(this, PlayerEvent.TURN_END);
		tank1.addEventListener(this, CollisionEvent.COLLISION);
		File dir = new File("weapons/");
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		        if(!WeaponSelect.contains(file)) {
		        	WeaponSelect.add(file.getName());
		        }
		    }
		}
		tank1.setyMax(100);
		
//		 SM.loadSoundEffect("jump", "resources/jump.wav");
//		SM.loadMusic("music","resources/mario_music.wav");
//		this.dispatchEvent(new SoundEvent(SoundEvent.TRIGGER_MUSIC,this,"music"));
//		System.out.println(this.getDispatchList().keySet());
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		PlayerSelect = Tanks.getChildren();
//		tank1.animate();
		tank1.setxAcc(0);
		tank2.setxAcc(0);
		Point p = tank1.getPosition();
		
		for(int x = 0; x<Proj.getChildren().size();x++) {
//			System.out.println("p");
			DisplayObject P = Proj.getChild(x);
//			P.gravity();
//			System.out.printf("x: %d, %d \n",P.getPosition().x,P.getxVelocity());
//			System.out.printf("y: %d, %d \n",P.getPosition().y,P.getyVelocity());
			P.move();
			if(P instanceof AnimatedSprite) {
				((AnimatedSprite)P).animate();
			}
		}
		Tank currPlayer = (Tank)PlayerSelect.get(player);
		if(p.y>=(int)Math.floor(930-((tank1.getUnscaledHeight()*tank1.getScaleY()))))onGround = true;
		if(!pause_movement) {
			if(pressedKeys.contains(KeyEvent.VK_LEFT)) {
				currPlayer.move = true;
				currPlayer.push_force(-2,0);
			}
			if(pressedKeys.contains(KeyEvent.VK_RIGHT)) {
				currPlayer.move = true;
				currPlayer.push_force(2,0);
			}
			if(pressedKeys.contains(KeyEvent.VK_Q) && currPlayer.angle < 180) {
				currPlayer.angle+=.5;
				currPlayer.setGunRotation(currPlayer.getGunRotation()-Math.toRadians(.5));
			}
			if(pressedKeys.contains(KeyEvent.VK_W) && currPlayer.angle > 0  ) {
				currPlayer.angle-=.5;
				currPlayer.setGunRotation(currPlayer.getGunRotation()+Math.toRadians(.5));
			}
			if(pressedKeys.contains(KeyEvent.VK_A) && velocity < 50)velocity+=.5;
			if(pressedKeys.contains(KeyEvent.VK_S) && velocity > 0  )velocity-=.5;
			if(pressedKeys.contains(KeyEvent.VK_ENTER) && Switch) {
				weapon = (weapon+1)%WeaponSelect.size();
				Switch = false;
				Switch_count = 0;
			}
		}
		if(Switch_count >18) {
			Switch = true;
		}
		else Switch_count++;

		
		if(pressedKeys.contains(KeyEvent.VK_SPACE) && fire) {
			fire = false;
			f_count = 0;
//			System.out.println("f");
			this.dispatchEvent(new ProjectileEvent(ProjectileEvent.PROJECTILE_FIRED,this,"Fire", "s"));
//			tank1.push_force(0,-50);
//				this.dispatchEvent(new SoundEvent(SoundEvent.TRIGGER_SOUND_EFFECT,this,"jump"));
		}	
		if(f_count<=50) {
			f_count++;
		}
		else{
			fire = true;
//			System.out.println("f");
		}
		for(int x = 0; x<Proj.getChildren().size();x++) {
//			System.out.println("p");
			Projectile P = (Projectile)Proj.getChild(x);
			P.gravity();
//			System.out.printf("%s: %s \n", P.getId(), P.getPosition());
//			System.out.printf("%s:: x: %d, %d  ",P.getId(), P.getxVelocity(),P.getxAcc());
//			System.out.printf("y: %d, %d \n",P.getyVelocity(),P.getyAcc());
			P.move();
			double xvel = P.getxVelocity();
			double yvel = P.getyVelocity();
			double thetap = -Math.atan(xvel/yvel)+(Math.PI/2);
			if(yvel>0)thetap+=Math.PI;
			P.findCenter();
			P.setRotation(thetap);
			for(int y = 0; y<Tanks.getChildren().size();y++) {
				if(P.collidesWith(Tanks.getChild(y))) {
					this.dispatchEvent(new ProjectileEvent(ProjectileEvent.PROJECTILE_EXPLODE,P,P.getId(),"s"));
					x--;
				}
			}
			P.incCountdown();
			if(P.getCountdown()>P.getFuse()) {
				this.dispatchEvent(new ProjectileEvent(ProjectileEvent.PROJECTILE_EXPLODE,P,P.getId(),"s"));
				x--;
			}
		}
		for(int j =0;j<PlayerSelect.size();j++) {
			Tank t1 = (Tank)PlayerSelect.get(j);
//			t1.gravity();
			t1.onGround = false;
			t1.move = false;
			if(!t1.move)t1.xfriction();
			if(t1.onGround) {
				t1.setPosition(new Point(p.x,(int)Math.floor((930-(t1.getUnscaledHeight()*t1.getScaleY())))));
				t1.setNormalUp(true);
				jump = true;
			}
			t1.move();
			for(int x = 0;x<Plats.getChildren().size();x++) {
				DisplayObject c = Plats.getChild(x);
				if(t1.collidesWith(c)) {
				t1.dispatchEvent(new CollisionEvent(CollisionEvent.COLLISION,t1,c));
				}
			}
		}
		
		for(int x = 0; x<EXP.getChildren().size();x++) {
			Explosion e = (Explosion)EXP.getChild(x);
			for(int y = 0; y<PlayerSelect.size();y++) {
				Tank t = (Tank) PlayerSelect.get(y);
				if(e.collidesWith(t)) {
					System.out.println("hit");
//					System.out.println(e.getDamage());
					t.decreaseHealth(e.getDamage());
//					System.out.printf("%s:%d \n",t.getId(),t.getHealth());
				}
			}
			e.incCount();
			if(e.getCount()>=e.getDuration()) {
				EXP.removeChild(e);
				x--;
			}
			System.out.println(x);
		}
		TJ.nextFrame();
//		System.out.println(c1.getParent().getId());
	}
	@Override 
	public void draw(Graphics g) {
		if(this!=null)super.draw(g);
		
		String t1 = String.format("%d / 100", tank1.getHealth());
		String t2 = String.format("%d / 100", tank2.getHealth());
		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		g.drawString(t1,40,80);
		g.drawString(t2,1100,80);
		g.drawString(WeaponSelect.get(weapon), 500, 1000);
		Graphics2D g2 = (Graphics2D)g;
		if(EXP!=null) {
			for(int i = 0; i<EXP.getChildren().size();i++) {
				g2.setColor(Color.RED);
				g2.draw(EXP.getChild(i).getGlobalHitbox());
				
			}
		}
		g2.draw(tank1.getGlobalHitbox());
	}
	@Override
	public void setDisplay() {
		this.addChild(Tanks);
		Tanks.addChild(tank1);
		Tanks.addChild(tank2);
//		this.addChild(Plats);
//		Plats.addChild(p1);
		this.addChild(Proj);
//		Plats.addChild(p4);
//		this.addChild(EXP);
	}
	public void Scale() {
		tank1.setScale(1.00);
		p1.setScaleX(5.0);
	}
	public void Position() {
		tank1.setPosition(400,500);
		tank2.setPosition(600, 500);
		p1.setPosition(0, 750);
	}
	@Override
	public void handleEvent(Event event) {
		if(event.getEventType() == CollisionEvent.COLLISION) {
			CollisionEvent e = (CollisionEvent)event;
			if(e.getSource() instanceof DisplayObject) {
				DisplayObject d1 = (DisplayObject)e.getSource();
				DisplayObject d2 = e.getD1();
				if(d2 instanceof Coin) {
					Coin currCoin = (Coin)d2;
					if(!currCoin.isPickedUp()) {
						currCoin.setPickedUp(true);
//						System.out.println("pickup");
						Tween swoop = new Tween("swoop",currCoin);
						Point c = currCoin.getPosition();
						swoop.animate(TweenableParams.Tweenables.PositionX,(double)c.x,0.0,500.0);
						swoop.animate(TweenableParams.Tweenables.PositionY,(double)c.y,0.0,500.0);
						swoop.animate(Tweenables.Scale, currCoin.getScaleX(), currCoin.getScaleX()*2, 500.0);
						TJ.add(swoop);
//						currCoin.setVisible(false);
					}
				}
				else
				
				jump = this.collide(d1, d2);
			}
		}
		if(event.getEventType() == CoinEvent.ALL_COINS_PICKED_UP) {
			this.pause();
			this.dispatchEvent(new SoundEvent(SoundEvent.STOP_MUSIC,this,"music"));
		}
		if(event.getEventType()==TweenEvent.TWEEN_COMPLETE) {
			TweenEvent e = (TweenEvent) event;
//			System.out.println(e.getTween().getId());
			if(e.getTween().getId()=="swoop") {
//				System.out.println("y");
				Tween fadeout = new Tween("fadeout",e.getTween().getObject());
				fadeout.animate(TweenableParams.Tweenables.Alpha, 1.0, 0.0, 300.0);
				TJ.add(fadeout);
			}
		}
		if(event.getEventType() == ProjectileEvent.PROJECTILE_FIRED) {
			System.out.println("fire"); 
			Projectile f1 = Projectile.loadProjectile("id",WeaponSelect.get(weapon));
			Tank t1 = (Tank)PlayerSelect.get(player);

			DisplayObject barrel = t1.getChild(0);
			Point Barrelpoint = localToGlobal(barrel.getPosition(),barrel);
			f1.setPosition(new Point(Barrelpoint.x,(int)(Barrelpoint.y-(barrel.getUnscaledHeight()*barrel.getScaleY()))));
			
			double rad = Math.toRadians(t1.angle);
			int xv = (int)(velocity*Math.cos(rad));
			int yv = -(int)(velocity*Math.sin(rad));
//			System.out.println(xv);
//			System.out.println(yv);
			f1.push_force(xv,yv);
			this.addEventListener(f1, ProjectileEvent.PROJECTILE_EXPLODE);
			Proj.addChild(f1);
			this.dispatchEvent(new PlayerEvent(this,PlayerEvent.FIRE));
			Projectile.loadProjectile("","");
		}
		if(event.getEventType() == ProjectileEvent.PROJECTILE_EXPLODE) {
			ProjectileEvent e = (ProjectileEvent)event;
			System.out.println("explode"); 
			Projectile p = (Projectile)Proj.getChild(e.getId());
			ArrayList<Projectile> psub = p.getSubmunition();
			double spread = p.getSpread();
			spread = spread/psub.size();
			Double spread_start = 0.0;
			if(psub.size()%2 ==1) {
				spread_start = -(psub.size()-1)/2 *spread;
			}
			else {
				spread_start = -(psub.size())/2 *spread;
			}
			for(int z = 0;z<psub.size();z++) {
				Projectile temp = psub.get(z);
				
				temp.setxVelocity(p.getxVelocity());
				temp.setyVelocity(p.getyVelocity());
				temp.turn(spread_start + (z*spread));
				temp.setSolid(true);
				temp.setVisible(true);
				temp.setPosition(localToGlobal(temp.getPosition(),temp));
				temp.getExp().setPosition(localToGlobal(temp.getExp().getPosition(),temp.getExp()));
				Proj.addChild(temp);
				p.removeChild(temp);
			}
			Explosion exp = p.getExp();
			exp.setPosition(localToGlobal(exp.getPosition(),exp));
			EXP.addChild(exp);
			Proj.removeChildById(e.getId());
			if(Proj.getChildren().size()<=0) {
				this.dispatchEvent(new PlayerEvent(this, PlayerEvent.TURN_END));
			}
		}
		if(event.getEventType() == PlayerEvent.FIRE) {
			pause_movement = true;
		}
		if(event.getEventType() == PlayerEvent.TURN_END) {
			player=(player+1)%2;
			pause_movement = false;
		}
	}
	
	public static void main(String[] args) {
		Prototype game = new Prototype();
		game.start();
	}
}


