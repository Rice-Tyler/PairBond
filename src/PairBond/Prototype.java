
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

//import Levels.Background;
//import Levels.Ground;
import Menu.Menu;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Sprites.Coin;
//import Sprites.Mario;
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
//import edu.virginia.engine.display.Sprite;
//import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.CollisionEvent;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.GameEvent;
import edu.virginia.engine.events.PlayerEvent;
import edu.virginia.engine.events.ProjectileEvent;
//import edu.virginia.engine.events.SoundEvent;
//import edu.virginia.engine.events.TweenEvent;
import edu.virginia.engine.sound.SoundManager;

public class Prototype extends Game {
	Tank tank1 = new Tank("tank1", "Tank3.png", 3);
	Tank tank2 = new Tank("tank2","Tank2.png",2);
	Menu startMenu = new Menu("menu");
	//Level1 lv = new Level1("lv1", "Background_field.png", 2);
	Level2 lv = new Level2("lv2", "Background_desert.png", 2);
	//Level3 lv = new Level3("lv3", "Background_winternight.png", 2);
	SoundManager SM = new SoundManager();
	TweenJuggler TJ = new TweenJuggler();
	Platform p1 = new Platform("p1");
	DisplayObjectContainer Plats = new DisplayObjectContainer("Plats");
	DisplayObjectContainer Proj = new DisplayObjectContainer("Proj");
	DisplayObjectContainer Tanks = new DisplayObjectContainer("Tanks");
	/* Container to hold Explosion Hitboxes*/
	DisplayObjectContainer EXP = new DisplayObjectContainer("EXP");
	ArrayList<String> WeaponSelect = new ArrayList<String>();
	boolean gameover = false;
	boolean jump = false;
	boolean Switch = true;
	boolean fire = true;
	boolean pause_movement = false;
	public boolean onGround = false;
	int f_count = 181;
	double velocity = 25.0;
	double angle = 90.0;
	int player = 0;
	int weapon = 1;
	int Switch_count = 19;
	
	ArrayList<DisplayObject> PlayerSelect = Tanks.getChildren();
	public Prototype() {
		//super("Prototype", 1500, 1100);
		super("Prototype", 1500, 700);
		this.setDisplay();
		this.Scale();
		this.Position();
		this.findCenter();
		this.addEventListener(this, ProjectileEvent.PROJECTILE_FIRED);
		this.addEventListener(this, ProjectileEvent.PROJECTILE_EXPLODE);
		this.addEventListener(this, PlayerEvent.FIRE);
		this.addEventListener(this, PlayerEvent.TURN_END);
		this.addEventListener(this, GameEvent.GAME_OVER);
		for(int x = 0; x<Tanks.getChildren().size();x++) {
			Tanks.getChild(x).addEventListener(this, CollisionEvent.COLLISION);
		}
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
		if(!this.startMenu.getInStart()) {
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
					currPlayer.angle+=.8;
					currPlayer.setGunRotation(currPlayer.getGunRotation()-Math.toRadians(.8));
				}
				if(pressedKeys.contains(KeyEvent.VK_W) && currPlayer.angle > 0  ) {
					currPlayer.angle-=.8;
					currPlayer.setGunRotation(currPlayer.getGunRotation()+Math.toRadians(.8));
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
			for(int r = 0; r<this.Tanks.num_children; r++) {
				for(int q = 0; q<lv.getNumPlatforms(); q++) {
					//Tanks.getChild(r).setNormalUp(false);
					//boolean check = false;
					boolean noCollisions = true;
					if(Tanks.getChild(r).collidesWith(lv.getPlatform(q))) {
						Point positionPlatform = lv.getPlatform(q).getPosition();
						Point positionTank = Tanks.getChild(r).getPosition();
						noCollisions = false;
						if(positionPlatform.y <= positionTank.y) {
							Tanks.getChild(r).setNormalUp(true);
							//check = true;
							Tanks.getChild(r).setPosition(Tanks.getChild(r).getPosition().x, lv.getPlatform(q).getPosition().y);
							//System.out.println("Collision");
							//System.out.println("NormalUp: " + check);
						}
					}
					if(noCollisions) {
						Tanks.getChild(r).setNormalUp(false);
					}
				}
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
				boolean explode = false;
				if(yvel>0)thetap+=Math.PI;
				P.findCenter();
				P.setRotation(thetap);
				for(int y = 0; y<Tanks.getChildren().size();y++) {
					if(P.collidesWith(Tanks.getChild(y)) && !explode) {
						this.dispatchEvent(new ProjectileEvent(ProjectileEvent.PROJECTILE_EXPLODE,P,P.getId(),"s"));
						explode = true;
						x--;
					}
				}
				for(int m = 0; m < lv.getNumPlatforms(); m++) {
					if(P.collidesWith(lv.getPlatform(m)) && !explode) {
						this.dispatchEvent(new ProjectileEvent(ProjectileEvent.PROJECTILE_EXPLODE,P,P.getId(),"s"));
						explode = true;
						x--;
					}
				}
				for(int n = 0; n < lv.getNumDestructables(); n++) {
					if(P.collidesWith(lv.getDestructable(n)) && !explode && lv.getDestructable(n).getVisible() == true) {
						this.dispatchEvent(new ProjectileEvent(ProjectileEvent.PROJECTILE_EXPLODE,P,P.getId(),"s"));
						explode = true;
						lv.getDestructable(n).setVisible(false);
						x--;
					}
				}
				P.incCountdown();
				if(P.getCountdown()>P.getFuse() & !explode) {
					this.dispatchEvent(new ProjectileEvent(ProjectileEvent.PROJECTILE_EXPLODE,P,P.getId(),"s"));
					explode = true;
					x--;
				}
			}
			for(int j =0;j<PlayerSelect.size();j++) {
				Tank t1 = (Tank)PlayerSelect.get(j);
				//t1.gravity();
				t1.onGround = false;
				t1.move = false;
				if(!t1.move)t1.xfriction();
				if(t1.onGround) {
					t1.setPosition(new Point(p.x,(int)Math.floor((930-(t1.getUnscaledHeight()*t1.getScaleY())))));
					t1.setNormalUp(true);
					jump = true;
				}
				t1.move();
				for(int x = 0;x<lv.getChildren().size();x++) {
					DisplayObject c = lv.getChild(x);
					if(t1.collidesWith(c)) {
	//				t1.dispatchEvent(new CollisionEvent(CollisionEvent.COLLISION,t1,c));
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
						if(t.getHealth()<0) {
							this.dispatchEvent(new GameEvent(GameEvent.GAME_OVER,this));
						}
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
		else {
			if(!this.startMenu.getPlayerSelect()) {
				if(pressedKeys.contains(KeyEvent.VK_SPACE)) {
					this.startMenu.setPlayerSelect(true);
					this.startMenu.setTitleBackground("BlackSquare.png");
					this.startMenu.getTitleBackground().setScale(5.0);
				}
			}
			if(pressedKeys.contains(KeyEvent.VK_2) && this.startMenu.getPlayerSelect()) {
				this.startMenu.setNumPlayers(2);
			}
			if(pressedKeys.contains(KeyEvent.VK_3) && this.startMenu.getPlayerSelect()) {
				this.startMenu.setNumPlayers(3);
			}
			if(pressedKeys.contains(KeyEvent.VK_4) && this.startMenu.getPlayerSelect()) {
				this.startMenu.setNumPlayers(4);
			}
			if(pressedKeys.contains(KeyEvent.VK_F) && this.startMenu.getPlayerSelect()) {
				this.startMenu.setLevel(1);
			}
			if(pressedKeys.contains(KeyEvent.VK_D) && this.startMenu.getPlayerSelect()) {
				this.startMenu.setLevel(2);
			}
			if(pressedKeys.contains(KeyEvent.VK_W) && this.startMenu.getPlayerSelect()) {
				this.startMenu.setLevel(3);
			}
			if(pressedKeys.contains(KeyEvent.VK_ENTER) && this.startMenu.getPlayerSelect()) {
				
			}
		}
	}
	@Override 
	public void draw(Graphics g) {
		if(this!=null)super.draw(g);
		if(!this.startMenu.getInStart()) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			if(tank1!=null) {
				g.setColor(Color.BLACK);
				String t1 = String.format("%d / 100", tank1.getHealth());
				if(tank1.getHealth()<50)g.setColor(Color.YELLOW);
				if(tank1.getHealth()<25)g.setColor(Color.RED);
				g.drawString(t1,40,80);
			}
			if(tank2!=null) {
				g.setColor(Color.BLACK);
				if(tank2.getHealth()<50)g.setColor(Color.YELLOW);
				if(tank2.getHealth()<25)g.setColor(Color.RED);
				String t2 = String.format("%d / 100", tank2.getHealth());
				
				g.drawString(t2,1000,80);
			}
			Graphics2D g2 = (Graphics2D)g;
			for(int i = 0;i<Proj.getChildren().size();i++) {
				g2.draw(((Projectile)Proj.getChild(i)).getExp().getGlobalHitbox());
			}
			g.setColor(Color.BLACK);
			
			g.setColor(Color.GREEN);
			if(WeaponSelect!=null)g.drawString(WeaponSelect.get(weapon), 500, 800);
			
			if(EXP!=null) {
				for(int i = 0; i<EXP.getChildren().size();i++) {
					g2.setColor(Color.RED);
					g2.draw(EXP.getChild(i).getGlobalHitbox());
					
				}
				
			}
	//		g2.draw(tank1.getGlobalHitbox());
	//		Tank currTank = (Tank) Tanks.getChild((player+1)%2);
	//		if(currTank.getHealth() < 0) {
	//			g2.drawString("Game Over", 300, 200);
	//			this.pause();
	//		}
		}
		else {
			if(!this.startMenu.getPlayerSelect()) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
				g.setColor(Color.BLACK);
				String t1 = String.format("Tank Battle");
				g.drawString(t1, 500, 200);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
				g.drawString("--Press space to continue--", 475, 300);
			}
			else {
				g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
				g.setColor(Color.WHITE);
				String t1 = String.format("Tank Battle");
				g.drawString(t1, 500, 200);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				if(this.startMenu.getNumPlayers() == 2) g.setColor(Color.YELLOW);
				g.drawString("Press 2 for two Players", 100, 300);
				g.setColor(Color.WHITE);
				if(this.startMenu.getNumPlayers() == 3) g.setColor(Color.YELLOW);
				g.drawString("Press 3 for three Players", 550, 300);
				g.setColor(Color.WHITE);
				if(this.startMenu.getNumPlayers() == 4) g.setColor(Color.YELLOW);
				g.drawString("Press 4 for four Players", 900, 300);
				
				g.setColor(Color.WHITE);
				
				if(this.startMenu.getLevel() == 1) g.setColor(Color.YELLOW);
				g.drawString("Press f for the Field level", 100, 450);
				g.setColor(Color.WHITE);
				if(this.startMenu.getLevel() == 2) g.setColor(Color.YELLOW);
				g.drawString("Press d for the Desert level", 520, 450);
				g.setColor(Color.WHITE);
				if(this.startMenu.getLevel() == 3) g.setColor(Color.YELLOW);
				g.drawString("Press w for the Winter level", 900, 450);
				

				g.setColor(Color.WHITE);
				
				g.drawString("Press Enter to start the game", 525, 600);
			}
		}
	}
	@Override
	public void setDisplay() {
		if(!this.startMenu.getInStart()) {
			this.addChild(lv.getBackground());
			for(int i = 0; i < lv.getNumPlatforms(); i++) {
				this.addChild(lv.getPlatform(i));
			}
			for(int i = 0; i < lv.getNumDestructables(); i++) {
				this.addChild(lv.getDestructable(i));
			}
			this.addChild(Tanks);
			Tanks.addChild(tank1);
			Tanks.addChild(tank2);
	//		this.addChild(Plats);
	//		Plats.addChild(p1);
			this.addChild(Proj);
	//		Plats.addChild(p4);
	//		this.addChild(EXP);
		}
		else {
			this.addChild(startMenu.getTitleBackground());
			this.addChild(startMenu);
		}
	}
	public void Scale() {
		if(!this.startMenu.getInStart()) {
			tank1.setScale(0.6);
			tank2.setScale(0.6);
			p1.setScaleX(5.0);
		}
	}
	public void Position() {
		if(!this.startMenu.getInStart()) {
			tank1.setPosition(lv.getSpawnPoint());
			tank2.setPosition(lv.getSpawnPoint());
			p1.setPosition(0, 750);
		}
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
				jump = this.collide(d1, d2);
			}
		}
		if(event.getEventType() == ProjectileEvent.PROJECTILE_FIRED) {
			System.out.println("fire"); 
			Projectile f1 = Projectile.loadProjectile("id",WeaponSelect.get(weapon));
			Tank t1 = (Tank)PlayerSelect.get(player);

			DisplayObject barrel = t1.getChild(0);
			Point Barrelpoint = barrel.localToGlobal(barrel.getPosition());
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
	//			Point pexp = localToGlobal(exp.getPosition,exp)
				if(exp.getPosition().x==0 && exp.getPosition().y==0)exp.setPosition(p.localToGlobal(p.getPosition()));
				else exp.setPosition(exp.localToGlobal(exp.getPosition()));
				System.out.println(exp.getPosition());
//				exp.setRotation(p.getRotation());
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
		if(event.getEventType() == GameEvent.GAME_OVER) {
			if(tank1.getHealth() < 0) {
				
			}
			this.pause();
		}
	}
	
	public static void main(String[] args) {
		Prototype game = new Prototype();
		game.start();
	}
}


