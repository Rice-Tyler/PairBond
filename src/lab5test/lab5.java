package lab5test;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Sprites.Coin;
import Sprites.Mario;
import Sprites.Platform;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.CollisionEvent;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.events.SoundEvent;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.lab4.CoinManager;
import edu.virginia.lab4.QuestManager;

public class lab5 extends Game implements IEventListener {
	Mario mario = new Mario("mario");
	QuestManager quest = new QuestManager("quest");
	CoinManager Coins = new CoinManager("Coins");
	SoundManager SM = new SoundManager();
	Coin c1 = new Coin("c1");
	Coin c2 = new Coin("c2");
	Coin c3 = new Coin("c3");
	Coin c4 = new Coin("c4");
	DisplayObjectContainer Plats = new DisplayObjectContainer("id");
	Platform p1 = new Platform("p1");
	Platform p2 = new Platform("p2");
	Platform p3 = new Platform("p3");
	Platform p4 = new Platform("p4");
	
	boolean jump = false;
	
	public boolean onGround = false;

	public lab5() {
		super("Lab Four Game Test", 1000, 1000);
		this.setDisplay();
		this.Scale();
		this.Position();
		this.findCenter();
		Coins.addEventListener(quest, CoinEvent.COIN_PICKED_UP);
		quest.setTotal_coins(Coins.getChildren().size());
		quest.addEventListener(this, CoinEvent.ALL_COINS_PICKED_UP);
		mario.addEventListener(this, CollisionEvent.COLLISION);
		this.addEventListener(SM, SoundEvent.TRIGGER_MUSIC);
		this.addEventListener(SM, SoundEvent.TRIGGER_SOUND_EFFECT);
		this.addEventListener(SM, SoundEvent.STOP_MUSIC);
		
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
		if(jump) {
			if(pressedKeys.contains(KeyEvent.VK_SPACE)) {
				mario.setNormalUp(false);
				mario.push_force(0,-50);
				this.dispatchEvent(new SoundEvent(SoundEvent.TRIGGER_SOUND_EFFECT,this,"jump"));
				jump = false;
			}	
		}
		mario.move();
		for(int x = 0;x<Plats.getChildren().size();x++) {
			DisplayObject c = Plats.getChild(x);
			if(mario.collidesWith(c)) {
			mario.dispatchEvent(new CollisionEvent(CollisionEvent.COLLISION,mario,c));
			}
		}
		for(int x = 0;x<Coins.getChildren().size();x++) {
			DisplayObject c = Coins.getChild(x);
			if(mario.collidesWith(c)) {
			mario.dispatchEvent(new CollisionEvent(CollisionEvent.COLLISION,mario,c));
			}
		}
//		System.out.println(c1.getParent().getId());
	}
	@Override 
	public void draw(Graphics g) {
		if(this!=null)super.draw(g);
//		g2.draw(p1.getGlobalHitbox());
//		g2.draw(p2.getGlobalHitbox());
//		g2.draw(p3.getGlobalHitbox());
		
	}
	@Override
	public void setDisplay() {
		this.addChild(mario);
		this.addChild(Coins);
		Coins.addChild(c1);
		Coins.addChild(c2);
		Coins.addChild(c3);
		Coins.addChild(c4);
		this.addChild(quest);
		this.addChild(Plats);
		Plats.addChild(p1);
		Plats.addChild(p2);
		Plats.addChild(p3);
//		Plats.addChild(p4);
	}
	public void Scale() {
		mario.setScale(3.0);
		c1.setScale(.1);
		c2.setScale(.1);
		c3.setScale(.1);
		c4.setScale(.1);
		p1.setScale(1.0);
	}
	public void Position() {
		mario.setPosition(400,0);
		c1.setPosition(40,800);
		c2.setPosition(10,200);
		c3.setPosition(600,620);
		c4.setPosition(700,220);
		p1.setPosition(30, 850);
		p2.setPosition(30, 500);
		p3.setPosition(700, 700);
		p4.setPosition(30, 900);
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
					if(currCoin.isVisible()) {
//						System.out.println("pickup");
						Coins.dispatchEvent(new CoinEvent(CoinEvent.COIN_PICKED_UP,Coins));
						currCoin.setVisible(false);
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
	}
	public static void main(String[] args) {
		lab5 game = new lab5();
		game.start();
	}
}
