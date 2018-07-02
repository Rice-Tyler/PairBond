package edu.virginia.lab4;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
//import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Sprites.Coin;
import Sprites.Mario;
//import Sprites.Platform;
import edu.virginia.engine.display.DisplayObject;
//import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.CollisionEvent;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
//import edu.virginia.lab1test.LabOneGame;

public class Lab4Test extends Game implements IEventListener {
	Coin c1 = new Coin("c1");
	Coin c2 = new Coin("c2");
	Coin c3 = new Coin("c3");
	Coin c4 = new Coin("c4");
	
	Mario mario = new Mario("mario");
	QuestManager quest = new QuestManager("quest");
	CoinManager Coins = new CoinManager("Coins");
	

	public Lab4Test() {
		super("Lab Four Game Test", 1000, 1000);
		this.setDisplay();
		this.Scale();
		this.Position();
		this.findCenter();
		Coins.addEventListener(quest, CoinEvent.COIN_PICKED_UP);
		quest.setTotal_coins(Coins.getChildren().size());
		quest.addEventListener(this, CoinEvent.ALL_COINS_PICKED_UP);
		mario.addEventListener(this, CollisionEvent.COLLISION);
//		p1.setSolid(true);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		mario.animate();
		if(pressedKeys.contains(KeyEvent.VK_LEFT))mario.move(-4,0);
		if(pressedKeys.contains(KeyEvent.VK_RIGHT))mario.move(4,0);
		if(pressedKeys.contains(KeyEvent.VK_UP))mario.move(0,-4);
		if(pressedKeys.contains(KeyEvent.VK_DOWN))mario.move(0,4);
		Double theta = mario.getRotation();
		if(pressedKeys.contains(KeyEvent.VK_Q))theta+=.3;
		if(pressedKeys.contains(KeyEvent.VK_W))theta-=.3;
		mario.setRotation(theta);
		Point Piv = mario.getPivotPoint();
		Double xPiv = Piv.getX();
		Double yPiv = Piv.getY();
		Integer Pspeed = 3;
		if(pressedKeys.contains(KeyEvent.VK_J)) xPiv+=Pspeed;
		if(pressedKeys.contains(KeyEvent.VK_I)) yPiv+=Pspeed;
		if(pressedKeys.contains(KeyEvent.VK_L)) xPiv-=Pspeed;
		if(pressedKeys.contains(KeyEvent.VK_K)) yPiv-=Pspeed;
		mario.setPivotPoint(xPiv, yPiv);
		
		Double scalex = mario.getScaleX();
		Double scaley = mario.getScaleY();
		if(pressedKeys.contains(KeyEvent.VK_A)) {
			scalex += .1;
			scaley += .1;
		}
		if(pressedKeys.contains(KeyEvent.VK_S)) {
			scalex -= .1;
			scaley -= .1;
		}
		if(scalex<1.0)scalex = 1.0;
		if(scaley<1.0)scaley = 1.0;
		
		mario.setScaleX(scalex);
		mario.setScaleY(scaley);
//		System.out.println(c1.getParent().getId());
		for(int x = 0; x<Coins.getChildren().size();x++) {
			DisplayObject currCoin = Coins.getChild(x);
			if(mario.collidesWith(currCoin)) {
//				System.out.println("intersect");
				if(currCoin.isVisible()) {
//					System.out.println("pickup");
					Coins.dispatchEvent(new CoinEvent(CoinEvent.COIN_PICKED_UP,Coins));
					currCoin.setVisible(false);
				}
			}
		}
		
		
	}
	@Override 
	public void draw(Graphics g) {
		if(this!=null)super.draw(g);
		String tally = String.format("%d/%d", quest.getNum_coins(), quest.getTotal_coins());
		g.setFont(new Font("TimesRoman", Font.PLAIN, 56));
		g.drawString(tally, 100, 100);
		Graphics2D g2d = (Graphics2D)g;
//		AffineTransform at  = mario.getGlobalTransform();
		g2d.draw(mario.getGlobalHitbox());
		
	}
	@Override
	public void setDisplay() {
		this.addChild(Coins);
		Coins.addChild(c1);
		Coins.addChild(c2);
		Coins.addChild(c3);
		Coins.addChild(c4);
		this.addChild(mario);
		this.addChild(quest);
//		this.addChild(p1);
		
	}
	public void Scale() {
		mario.setScale(3.0);
		c1.setScale(.125);
		c2.setScale(.125);
		c3.setScale(.125);
		c4.setScale(.125);
	}
	public void Position() {
		mario.setPosition(100,500);
		c1.setPosition(600,500);
		c2.setPosition(0,0);
		c3.setPosition(800, 800);
		c4.setPosition(0,800);
//		p1.setPosition(500,500);
	}
	public static void main(String[] args) {
		Lab4Test game = new Lab4Test();
		game.start();

	}

	@Override
	public void handleEvent(Event event) {
		if(event.getEventType() == CoinEvent.ALL_COINS_PICKED_UP) {
			this.pause();
		}
		if(event.getEventType() == CollisionEvent.COLLISION) {
			DisplayObject D1 =((CollisionEvent)event).getD1();
			if(D1.getClass() == Coin.class ) {
				
			}
		}
		
	}

}
