package PairBond;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Sprites.Coin;
import Sprites.Mario;
import Sprites.Platform;
import edu.virginia.engine.animation.Tween;
import edu.virginia.engine.animation.TweenJuggler;
import edu.virginia.engine.animation.TweenTransitions;
import edu.virginia.engine.animation.TweenableParams;
import edu.virginia.engine.animation.TweenableParams.Tweenables;
import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Projectile;
import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.CollisionEvent;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.ProjectileEvent;
import edu.virginia.engine.events.SoundEvent;
import edu.virginia.engine.events.TweenEvent;
import edu.virginia.engine.sound.SoundManager;

public class Prototype extends Game {
	Mario mario = new Mario("mario");
	Mario m2 = new Mario("m2");
	SoundManager SM = new SoundManager();
	TweenJuggler TJ = new TweenJuggler();
	Platform p1 = new Platform("p1");
	DisplayObjectContainer Plats = new DisplayObjectContainer("Plats");
	DisplayObjectContainer Proj = new DisplayObjectContainer("Proj");
	boolean jump = false;
	
	public boolean onGround = false;

	public Prototype() {
		super("Prototype", 1000, 1000);
		this.setDisplay();
		this.Scale();
		this.Position();
		this.findCenter();
		this.addEventListener(this, ProjectileEvent.PROJECTILE_FIRED);
		mario.addEventListener(this, CollisionEvent.COLLISION);
		mario.setyMax(100);
		SM.loadSoundEffect("jump", "resources/jump.wav");
		SM.loadMusic("music","resources/mario_music.wav");
		this.dispatchEvent(new SoundEvent(SoundEvent.TRIGGER_MUSIC,this,"music"));
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		mario.animate();
		mario.setxAcc(0);
		Point p = mario.getPosition();
		onGround = false;
		boolean move = false;
		mario.gravity();
		for(int x = 0; x<Proj.getChildren().size();x++) {
//			System.out.println("p");
			DisplayObject P = Proj.getChild(x);
			P.gravity();
			System.out.printf("x: %d, %d \n",P.getxVelocity(),P.getxAcc());
			System.out.printf("y: %d, %d \n",P.getyVelocity(),P.getyAcc());
			P.move();
			if(P instanceof AnimatedSprite) {
				((AnimatedSprite)P).animate();
			}
		}
		if(p.y>=(int)Math.floor(930-((mario.getUnscaledHeight()*mario.getScaleY()))))onGround = true;
		if(pressedKeys.contains(KeyEvent.VK_LEFT)) {
			move = true;
			mario.push_force(-2,0);
		}
		if(pressedKeys.contains(KeyEvent.VK_RIGHT)) {
			move = true;
			mario.push_force(2,0);
		}
		Double theta = p1.getRotation();
		if(pressedKeys.contains(KeyEvent.VK_Q))theta+=.3;
		if(pressedKeys.contains(KeyEvent.VK_W))theta-=.3;
		p1.setRotation(theta);
//		mario.eval_force();
		if(!move)mario.xfriction();
		if(onGround) {
			mario.setPosition(new Point(p.x,(int)Math.floor((930-(mario.getUnscaledHeight()*mario.getScaleY())))));
			mario.setNormalUp(true);
			jump = true;
		}
		
		if(pressedKeys.contains(KeyEvent.VK_SPACE)) {
			this.dispatchEvent(new ProjectileEvent("Fire",this,ProjectileEvent.PROJECTILE_FIRED, "s"));
//			mario.push_force(0,-50);
//				this.dispatchEvent(new SoundEvent(SoundEvent.TRIGGER_SOUND_EFFECT,this,"jump"));
		}	
		for(int x = 0; x<Proj.getChildren().size();x++) {
//			System.out.println("p");
			DisplayObject P = Proj.getChild(x);
			P.gravity();
			System.out.printf("x: %d, %d \n",P.getxVelocity(),P.getxAcc());
			System.out.printf("y: %d, %d \n",P.getyVelocity(),P.getyAcc());
			P.move();
			if(P instanceof AnimatedSprite) {
				((AnimatedSprite)P).animate();
			}
		}
		mario.move();
		for(int x = 0;x<Plats.getChildren().size();x++) {
			DisplayObject c = Plats.getChild(x);
			if(mario.collidesWith(c)) {
			mario.dispatchEvent(new CollisionEvent(CollisionEvent.COLLISION,mario,c));
			}
		}
		TJ.nextFrame();
//		System.out.println(c1.getParent().getId());
	}
	@Override 
	public void draw(Graphics g) {
		if(this!=null)super.draw(g);
		
	}
	@Override
	public void setDisplay() {
		this.addChild(mario);
//		this.addChild(Plats);
//		Plats.addChild(p1);
		this.addChild(Proj);
//		Plats.addChild(p4);
	}
	public void Scale() {
		mario.setScale(3.0);
		p1.setScaleX(5.0);
	}
	public void Position() {
		mario.setPosition(400,500);
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
			Projectile f1 = new Projectile("f1");
			f1.setPosition(new Point(mario.getPosition().x-25,mario.getPosition().y));
			double velocity = 50.0;
			double angle = 90.0;
			angle = Math.toRadians(angle);
			int xv = (int)(velocity*Math.cos(angle));
			int yv = -(int)(velocity*Math.sin(angle));
			System.out.println(xv);
			System.out.println(yv);
			f1.push_force(xv,yv);
			Proj.addChild(f1);
		}
	}
	public static void main(String[] args) {
		Prototype game = new Prototype();
		game.start();
	}
}

