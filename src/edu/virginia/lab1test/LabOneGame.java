package edu.virginia.lab1test;

import java.awt.Graphics;
import java.util.ArrayList;

import Sprites.Mario;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
//import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
//import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game implements MouseListener{
	Integer hit_count = 0;
	Integer speed = 3;
	Integer Pspeed = 3;
	Integer health = 10;
	Integer Aspeed = 10;
	Integer tBound,dBound,rBound,lBound = 0;
	boolean toggle = true;
	boolean stoggle = true;
	Integer offset = 42;
	
	boolean Up = true;
	GameClock clock = new GameClock();
	/* Create a sprite object for our game. We'll use mario */
	Mario mario = new Mario("Mario");
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 1000, 1000);
		this.getMainFrame().addMouseListener(this);
		mario.setPosition(500,500);
		mario.setScaleX(3.0);
		mario.setScaleY(3.0);
		tBound = 24;
		dBound = 60;
		lBound = 7;
		rBound = 27;
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		mario.animate();
		Point Pos = mario.getPosition();
		Double xPos = Pos.getX();
		Double yPos = Pos.getY();
		Double theta = mario.getRotation();
		Double scalex = mario.getScaleX();
		Double scaley = mario.getScaleY();
				
		/*Rotation*/
		if(pressedKeys.contains(KeyEvent.VK_Q))theta+=.3;
		if(pressedKeys.contains(KeyEvent.VK_W))theta-=.3;
		mario.setRotation(theta);
		/*Scale*/
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
		
		/*Movement*/
		if(pressedKeys.contains(KeyEvent.VK_SPACE)) {
			
			if(stoggle) {
				System.out.println("switch!");
				mario.cycle_animation();
			}
			stoggle = false;
		}
		if(!pressedKeys.contains(KeyEvent.VK_SPACE)) stoggle = true;
		if(pressedKeys.contains(37)) {
			xPos-=speed;
			if(!pressedKeys.contains(38))mario.setFlip(true);
		}
		if(pressedKeys.contains(38)) yPos-=speed;
		if(pressedKeys.contains(39)) {
			xPos+=speed;
			if(!pressedKeys.contains(37))mario.setFlip(false);
		}
		if(pressedKeys.contains(40)) yPos+=speed;
		if((xPos + (rBound*scalex)) > (1000))xPos = Math.floor(1000-(rBound*scalex));
		if((xPos+(lBound*scalex) < 0)) xPos = Math.ceil(-lBound*scalex);
		
		if((yPos + (dBound*scaley)) > 970)yPos = Math.floor(970-(dBound*scaley));
		if((yPos+(tBound*scaley) < 0)) yPos = -(tBound*scaley);

		mario.setPosition(xPos,yPos);
		
		/*Move Pivot*/
		Point Piv = mario.getPivotPoint();
		Double xPiv = Piv.getX();
		Double yPiv = Piv.getY();
		if(pressedKeys.contains(KeyEvent.VK_J)) xPiv+=Pspeed;
		if(pressedKeys.contains(KeyEvent.VK_I)) yPiv+=Pspeed;
		if(pressedKeys.contains(KeyEvent.VK_L)) xPiv-=Pspeed;
		if(pressedKeys.contains(KeyEvent.VK_K)) yPiv-=Pspeed;
		mario.setPivotPoint(xPiv, yPiv);
		/*Visibility*/
		float alpha = mario.getAlpha();
		if(hit_count==1)mario.setVisible(false);
		if(pressedKeys.contains(KeyEvent.VK_Z)) alpha = Math.min(1.0f, alpha+.05f);
		if(pressedKeys.contains(KeyEvent.VK_X)) alpha = Math.max(0.0f, alpha-.05f);
		if(pressedKeys.contains(KeyEvent.VK_V)) {
			if(toggle) mario.setVisible(!mario.isVisible());
			toggle = false;
		}
		if(hit_count>0) {
			mario.setVisible(!mario.isVisible());
			hit_count--;
		}
		if(!pressedKeys.contains(KeyEvent.VK_V)) toggle = true;
		mario.setAlpha(alpha);
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null) mario.update(pressedKeys);
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		Integer fontSize = 30;
	    double ctime = this.clock.getElapsedTime()/1000;
	    String time = String.format("%.2f",ctime);
		super.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(mario != null) mario.draw(g);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		g.setColor(Color.GRAY);
		g.fillRect(10, 10, 160, 60);
		g.setColor(Color.red);
		g.fillRect(15, 15, (15*health), 50);
		g.drawString(time, 900, 50);
		g.setColor(Color.BLACK);
		g.drawString(health.toString(), 85, 50);
		g.setColor(Color.GREEN);
		
		if(ctime >=60) {
			g.drawString("mario Wins", 500, 500);
			this.pause();
		}
		if(health <= 0) {
			g.drawString("Clicker Wins", 500, 500);
			this.pause();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Integer xt = e.getX();
		Integer yt = e.getY();
		boolean xhit = false;
		boolean yhit = false;
		Integer xPos = mario.getPosition().x;
		Integer yPos = mario.getPosition().y;
		Double scaley = mario.getScaleX();
		Double scalex = mario.getScaleY();
		Integer xdist = xt-xPos;
		Integer ydist = yt-yPos;
		if(scalex>=0) {
			xhit = (xdist < (rBound*scalex) && xdist > (lBound*scalex));
		}
		else {
			xhit = (xdist > (rBound*scalex) && xdist < (lBound*scalex));
//			System.out.println("scale neg");
		}
		
		if(scaley>=0) {
			yhit = (ydist < (dBound*scaley) && ydist > (tBound*scaley));
		}
		else {
			yhit = (ydist > (dBound*scaley) && ydist < (tBound*scaley));
		}
//		System.out.printf("x: %s \n",xhit);
//		System.out.printf("y: %s \n",yhit);
		if(xhit && yhit) {
			health-=1;
			speed+=2;
			hit_count = 10;
			mario.setSpeed(mario.getSpeed()-1);
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
//		System.out.println('y');
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// 
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// 
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// 
		
	}
	


	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		game.start();

	}
}
