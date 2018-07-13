
package PairBond;

import java.awt.Color;
import java.awt.Font;
//import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import Sprites.Coin;
import Sprites.Mario;
import Sprites.Platform;
import Sprites.Tank;
import Sprites.Tankgun;
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

public class WeaponEditor extends Game {
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
	ArrayList<String> ImageSelect = new ArrayList<String>();
	ArrayList<String> BlastSelect = new ArrayList<String>();
	boolean jump = false;
	boolean Switch = true;
	boolean fire = true;
	boolean pause_movement = false;
	public boolean onGround = false;
	int f_count = 181;
	double velocity = 25.0;
	double angle = 0.0;
	int player = 0;
	int weapon = 0;
	int Blast = 0;
	int Image = 0;
	int Switch_count = 19;
	/* Weapon Variables */
	String imgName = "";
	String BlastType = "";
	Double radius = 10.0;
	Integer damage = 1;
	Integer duration = 10;
	Integer height = 10;
	Integer width = 10;
	Integer fuse = 10;
	Double spread = 0.0;
	String sub = "none";
	Integer sub_num = 0;
	Double scalex = .10;
	Double scaley = .10;
	Integer Select = 0;
	
	ArrayList<DisplayObject> PlayerSelect = Tanks.getChildren();
	public WeaponEditor() {
		super("WeaponEditor", 1500, 1500);
		this.setDisplay();
		this.Scale();
		this.Position();
		this.addEventListener(this, ProjectileEvent.PROJECTILE_FIRED);
		this.addEventListener(this, ProjectileEvent.PROJECTILE_EXPLODE);
		this.addEventListener(this, PlayerEvent.FIRE);
		this.addEventListener(this, PlayerEvent.TURN_END);
		tank1.addEventListener(this, CollisionEvent.COLLISION);
		
		WeaponSelect.add("none");
		BlastSelect.add("Round");
		BlastSelect.add("Rect");
		SM.loadSoundEffect("fire","resources/firing2.wav");
		File dir = new File("weapons/");
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		        if(!WeaponSelect.contains(file)) {
		        	WeaponSelect.add(file.getName());
		        }
		    }
		}
		File Imgdir = new File("resources/weapons/");
		File[] ImgList = Imgdir.listFiles();
		for (File file : ImgList) {
		    if (file.isFile()) {
		        if(!ImageSelect.contains(file)) {
		        	ImageSelect.add(file.getName());
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
		File dir = new File("weapons/");
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
		    if (file.isFile()) {
		        if(!WeaponSelect.contains(file.getName())) {
		        	WeaponSelect.add(file.getName());
		        }
		    }
		}
		sub = WeaponSelect.get(weapon);
		imgName = ImageSelect.get(Image);
		BlastType = BlastSelect.get(Blast);
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
			if(pressedKeys.contains(KeyEvent.VK_Q) && currPlayer.angle < 270) {
				currPlayer.angle+=2;
				currPlayer.setGunRotation(currPlayer.getGunRotation()-Math.toRadians(2));
			}
			if(pressedKeys.contains(KeyEvent.VK_W) && currPlayer.angle > 90) {
				currPlayer.angle-=2;
				currPlayer.setGunRotation(currPlayer.getGunRotation()+Math.toRadians(2));
			}
			if(pressedKeys.contains(KeyEvent.VK_A) && velocity < 50)velocity+=.5;
			if(pressedKeys.contains(KeyEvent.VK_S) && velocity > 0  )velocity-=.5;
			if(pressedKeys.contains(KeyEvent.VK_ENTER) && Switch) {
				Select = (Select+1)%13;
				Switch = false;
				Switch_count = 0;
			}
			if(pressedKeys.contains(KeyEvent.VK_SHIFT) && Switch) {
				if(Select>0)Select = (Select-1)%13;
				else Select = 12;
				Switch = false;
				Switch_count = 0;
			}
		}
		if(Switch_count >4) {
			Switch = true;
		}
		else Switch_count++;
		
		/* Weapon Editor */
		if(pressedKeys.contains(KeyEvent.VK_UP)){
			if(Select == 0 && Switch) {
				Image++;
				Image%=ImageSelect.size();
				Switch = false;
				Switch_count = 0;
			}else if(Select == 1 && Switch) {
				Blast++;
				Blast%=BlastSelect.size();
				Switch = false;
				Switch_count = 0;
			} else if(Select == 2) {
				radius+=1;
			} else if(Select == 3 && Switch) {
				damage++;
				Switch_count = 2;
				Switch = false;
			} else if(Select == 4 && Switch) {
				duration++;
				Switch_count = 2;
				Switch = false;
			} else if(Select == 5) {
				height++;
			} else if(Select == 6) {
				width++;
			} else if(Select == 7) {
				fuse++;
			} else if(Select == 8) {
				spread+=.1;
			} else if (Select ==9 && Switch) {
				weapon= (weapon+1)%WeaponSelect.size();
				System.out.println(WeaponSelect.size());
				Switch_count = 0;
				Switch = false;
			} else if(Select == 10 && Switch) {
				sub_num++;
				Switch_count = 2;
				Switch = false;
			}else if(Select == 11 && Switch) {
				scalex+=0.01;
				Switch = false;
				Switch_count = 2;
			}else if(Select == 12 && Switch) {
				scaley+=0.01;
				Switch = false;
				Switch_count = 2;
			}
		}
		
		if(pressedKeys.contains(KeyEvent.VK_DOWN)){
			if(Select == 0 && Switch) {
				if(Image>0)Image--;
				Switch = false;
				Switch_count = 0;
			}else if(Select == 1 && Switch) {
				if(Blast>0)Blast--;
				Switch = false;
				Switch_count = 0;
			} else if(Select == 2) {
				if(radius>0)radius-=1;
			} else if(Select == 3 && Switch) {
				if(damage>0) {
					damage--;
					Switch = false;
					Switch_count = 2;
				}
			} else if(Select == 4 && Switch) {
				if(duration>0) {
					duration--;
					Switch = false;
					Switch_count = 2;
				}
			} else if(Select == 5) {
				if(height>0)height--;
			} else if(Select == 6) {
				if(width>0)width--;
			} else if(Select == 7) {
				if(fuse>0)fuse--;
			} else if(Select == 8) {
				if(spread>0)spread-=.1;
			} else if (Select ==9 && Switch) {
				weapon = (weapon-1)%WeaponSelect.size();
				Switch = false;
				Switch_count = 2;
			} else if(Select == 10 && Switch) {
				if(sub_num>0)sub_num--;
				Switch = false;
				Switch_count = 2;
			}else if(Select == 11 && Switch) {
				if(scalex>0.001)scalex-=0.01;
				Switch = false;
				Switch_count = 2;
			}else if(Select == 12 && Switch) {
				if(scaley>0.001)scaley-=0.01;
				Switch = false;
				Switch_count = 2;
			}
		}

		
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
				if(e.collidesWith(t) || e.collidesWith(t.getGun())) {
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
		}
		TJ.nextFrame();
//		System.out.println(c1.getParent().getId());
	}
	@Override 
	public void draw(Graphics g) {
		if(this!=null)super.draw(g);
		Graphics2D g2 = (Graphics2D)g;
		if(tank1!=null && tank2!=null) {
			String t1 = String.format("%d / 100", tank1.getHealth());
			String t2 = String.format("%d / 100", tank2.getHealth());
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g.drawString(t1,40,80);
			g.drawString(t2,1100,80);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			this.drawParams(g);
		}
		
		
		if(EXP!=null) {
			for(int i = 0; i<EXP.getChildren().size();i++) {
				g2.setColor(Color.RED);
				g2.draw(EXP.getChild(i).getGlobalHitbox());
				
			}
		}
		DisplayObject barrel = tank1.getChild(0);
		Point bp = barrel.getPivotPoint();
		bp = new Point((int)bp.getX()+10,(int)bp.getY()+60);
		Point Barrelpoint = barrel.localToGlobal(bp);
		g2.setColor(Color.GREEN);
		g2.draw(new Ellipse2D.Double(Barrelpoint.x,Barrelpoint.y,10.0,10.0));
		for(int x = 0; x<Proj.getChildren().size();x++) {
			g2.draw(Proj.getChild(x).getGlobalHitbox());
		}
		
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
			String ImgName = String.format("weapons/%s", imgName);
			Projectile f1 = new Projectile("f1", ImgName, BlastType, radius, damage, duration, height, width, fuse, spread,sub,scalex,scaley);
			for(int x = 0; x<sub_num;x++) {
				Projectile p = Projectile.loadProjectile(String.format("s%d",x),sub);
				f1.addSubmunition(p);
			}
			Tank t1 = (Tank)PlayerSelect.get(player);

			DisplayObject barrel = t1.getChild(0);
			
			for(int y = 0;y<t1.getChildren().size();y++) {
				System.out.println(t1.getChild(y).getId());
			}
			Point bp = barrel.getPivotPoint();
			bp = new Point(bp.x+10,bp.y+60);
			Point Barrelpoint = barrel.localToGlobal(bp);
			Barrelpoint = new Point(Barrelpoint.x,Barrelpoint.y);
			f1.setPosition(Barrelpoint);
			 
			double rad = Math.toRadians(t1.angle-90);
			int xv = (int)(velocity*Math.cos(rad));
			int yv = -(int)(velocity*Math.sin(rad));
//			System.out.println(xv);
//			System.out.println(yv);
			f1.push_force(xv,yv);
			this.addEventListener(f1, ProjectileEvent.PROJECTILE_EXPLODE);
			Proj.addChild(f1);
			SM.playSoundEffect("fire");
			this.dispatchEvent(new PlayerEvent(this,PlayerEvent.FIRE));
			
		}
		if(event.getEventType() == ProjectileEvent.PROJECTILE_EXPLODE) {
			ProjectileEvent e = (ProjectileEvent)event;
			System.out.println("explode"); 
			Projectile p = (Projectile)Proj.getChild(e.getId());
			if(p!=null) {
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
					temp.setPosition(temp.localToGlobal(temp.getPosition()));
//					temp.getExp().setPosition(localToGlobal(temp.getExp().getPosition(),temp.getExp()));
					Proj.addChild(temp);
					p.removeChild(temp);
				}
				Explosion exp = p.getExp();
				Point g = p.getPosition();
				g = new Point(g.x,g.y-(p.getUnscaledHeight()/2));
				exp.setPosition(p.localToGlobal(g));
//				exp.setRotation(p.getRotation());
				System.out.println(exp.getPosition());
				EXP.addChild(exp);
				Proj.removeChildById(e.getId());
				if(Proj.getChildren().size()<=0) {
					this.dispatchEvent(new PlayerEvent(this, PlayerEvent.TURN_END));
				}
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
		WeaponEditor game = new WeaponEditor();
		game.start();
	}
	
	public void drawParams(Graphics g) {
		int x = 1000;
		int y = 200;
		double sx = scalex*10;
		double sy = scaley*10;
		String v1 = String.format("1>Image: %s",imgName);
		String v2 = String.format("2>BlastType: %s",BlastType);
		String v3 = String.format("3>Blast Radius: %.2f", radius);
		String v4 = String.format("4>Damage: %d", damage);
		String v5 = String.format("5>Blast Duration: %d", duration);
		String v6 = String.format("6>Blast Height: %d", height);
		String v7 = String.format("7>Blast Width: %d", width);
		String v8 = String.format("8>Fuse: %d", fuse);
		String v9 = String.format("9>Spread Angle: %.2f", spread);
		String v10 = String.format("10>Submunition: %s", sub);
		String v11 = String.format("11># Submunition: %s", sub_num);
		String v12 = String.format("12>ScaleX: %.2f", sx);
		String v13 = String.format("13>ScaleY: %.2f", sy);
		if(Select == 0) {
			g.setColor(Color.GREEN);
			g.drawString(v1, x, y);
			g.setColor(Color.BLACK);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		}else if(Select == 1) {
			g.drawString(v1, x, y);
			g.setColor(Color.GREEN);
			g.drawString(v2, x, y+50);
			g.setColor(Color.BLACK);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		}else if(Select == 2) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.setColor(Color.GREEN);
			g.drawString(v3, x, y+100);
			g.setColor(Color.BLACK);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		}else if(Select == 3) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.setColor(Color.GREEN);
			g.drawString(v4, x, y+150);
			g.setColor(Color.BLACK);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		}else if(Select == 4) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.setColor(Color.GREEN);
			g.drawString(v5, x, y+200);
			g.setColor(Color.BLACK);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		}
		else if(Select == 5) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.setColor(Color.GREEN);
			g.drawString(v6, x, y+250);
			g.setColor(Color.BLACK);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		} else if(Select == 6) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.setColor(Color.GREEN);
			g.drawString(v7, x, y+300);
			g.setColor(Color.BLACK);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		}else if(Select == 7) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.setColor(Color.GREEN);
			g.drawString(v8, x, y+350);
			g.setColor(Color.BLACK);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		} else if(Select == 8) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.setColor(Color.GREEN);
			g.drawString(v9, x, y+400);
			g.setColor(Color.BLACK);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		} else if (Select ==9) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.setColor(Color.GREEN);
			g.drawString(v10, x, y+450);
			g.setColor(Color.BLACK);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		} else if(Select == 10) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.setColor(Color.GREEN);
			g.drawString(v11, x, y+500);
			g.setColor(Color.BLACK);
			g.drawString(v12, x, y+550);
			g.drawString(v13, x, y+600);
		} else if(Select == 11) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.setColor(Color.GREEN);
			g.drawString(v12, x, y+550);
			g.setColor(Color.BLACK);
			g.drawString(v13, x, y+600);
		} else if(Select == 12) {
			g.drawString(v1, x, y);
			g.drawString(v2, x, y+50);
			g.drawString(v3, x, y+100);
			g.drawString(v4, x, y+150);
			g.drawString(v5, x, y+200);
			g.drawString(v6, x, y+250);
			g.drawString(v7, x, y+300);
			g.drawString(v8, x, y+350);
			g.drawString(v9, x, y+400);
			g.drawString(v10, x, y+450);
			g.drawString(v11, x, y+500);
			g.drawString(v12, x, y+550);
			g.setColor(Color.GREEN);
			g.drawString(v13, x, y+600);
			g.setColor(Color.BLACK);
		}
	}
}


