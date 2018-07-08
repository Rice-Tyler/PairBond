package PairBond;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import Sprites.Platform;
import Sprites.Tank;
//import Sprites.healthbarSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.GameClock;
import edu.virginia.lab4.QuestManager;
import edu.virginia.engine.sound.SoundManager;
import edu.virginia.engine.animation.Tween;
import edu.virginia.engine.animation.TweenJuggler;
import edu.virginia.engine.animation.TweenTransitions;
import edu.virginia.engine.animation.TweenParam;

class LevelOne extends Game {
	
	public static DisplayObjectContainer levelOne = new DisplayObjectContainer("LevelOne", "health.png");
	
	private JPanel panel;
	Rectangle battleGrounds = new Rectangle(150, 200, 1200, 800);
	
	SoundManager soundManager;
//	healthbarSprite healthBar = new healthbarSprite("healthBar");
	int healthWidth = 0;
	
	boolean tankDestroyed = false;
	int tankRemaining = 4;
	
	Tank tank1 = new Tank("tank1", "Tank1.png", 1);
	Tank tank2 = new Tank("tank2", "Tank2.png", 2);
	private DisplayObjectContainer camera = new DisplayObjectContainer("Camera");
	
	//initialize some platforms for our game
	
	Platform platform1 = new Platform("plat1");
	Platform platform2 = new Platform("plat2");
	Platform platform3 = new Platform("plat3");
	
	Sprite gameOver = new Sprite("gameOver", "gameOver.png");
	Sprite youWin = new Sprite("youWin", "gameWon.png");
	Sprite backGround = new Sprite("battleGround", "fieldOne.png");
	
	TweenJuggler juggler = new TweenJuggler();
	
	GameClock clock;
	
	QuestManager myQuestManager = new QuestManager("mainquest");
	
	int dx = 5;
	
	boolean collisionOccured = false;
	boolean stopRight = false;
	boolean stopLeft = false;
	

	public LevelOne(String gameId, int width, int height) {
		super("levelOne", 1200, 800);
		
		clock = new GameClock();
		
		this.getScenePanel().setBackground(Color.WHITE);
		
//		healthBar.setPosition(10, 10);
//		healthBar.setScaleX(0.004);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		
		//healthBar.setPosition((tank1.getPosition() - VIEWPORT_SIZE_X / 2));
		//healthBar.setPosition((tank2.getPosition() - VIEWPORT_SIZE_X / 2));
		
		if (tank1 != null && tank2 != null) {
			
			juggler.nextFrame();
			
			if(collisionOccured == false) {
				stopLeft = false;
				stopRight = false;
				
			}
			
			if(collisionOccured == true) {
				collisionOccured = false;
			}
			
			if (pressedKeys.contains("L")) {
				System.exit(0);
			}
			
			//if (pressedKeys.contains(KeyEvent.getKeyText(39))) {
				//if (stopRight == false) {
					//tank2.setXPos(tank2.getPosition() + dx);
					//System.out.println("right is: " + ghost.getXPos());
					//}
			}

			//if (pressedKeys.contains(KeyEvent.getKeyText(37))) {
				//if ( stopLeft==false)
					//{tank2.setXPos(tank2.getPosition() - dx);
					//System.out.println("left is: " + ghost.getXPos());
					//}
			
	}
		
	
	public void LevelOneGame() {
		panel = new JPanel();
		this.getScenePanel().setBackground(Color.DARK_GRAY);
	}
	
	
	@Override
	public void draw(Graphics g) {
		//g.translate(int)-camera.getPosition(), (int)-camera.getPosition());
		super.draw(g);
		
		if (tank1 != null && tank2 != null) {
			tank1.draw(g);
			tank2.draw(g);
			
		}
		if (platform1 != null && platform2 != null && platform3 != null) {
			platform1.draw(g);
			platform2.draw(g);
			platform3.draw(g);
			
		
		}
		if (youWin != null) {
			youWin.draw(g);
		}
		
		if (gameOver != null) {
			gameOver.draw(g);
		}
		
		//public void switchLevels(){
			
			//System.out.println("level 1 entered");
			//Alpha.currentGame.exitGame();
			//Alpha.atLevelThree = true;
			//Alpha.atLevelTwo = false;
			//Game game = new LevelOne("LevelOne", 1200, 800);
			//Game game = new Beta();
			//Alpha.currentGame = game;
			//Alpha.currentGame.start();
			
		
	}
	public static void main(String[] args) {
		//Game game = new Alpha();
		//LevelOne Level1 = new LevelOne("testOne", 1200, 800);
		//Alpha.currentGame = Level1;
		
		//Alpha.currentGame.start();
	}
		
		
		
		// TODO Auto-generated constructor stub
	}
	
	


