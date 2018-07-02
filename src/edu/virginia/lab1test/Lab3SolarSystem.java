package edu.virginia.lab1test;

//import java.awt.Color;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;

public class Lab3SolarSystem extends Game {
	/*Full System*/
	
	Sprite solarSystem = new Sprite("solar system");
	AnimatedSprite moons = new AnimatedSprite("moons","moons.png");
	
	/* Tier 1 - Sun & Planet Orbital Dummy Sprites */
	
	Sprite sun = new Sprite("sun","sun.png");
	Sprite stars = new Sprite("stars","stars.jpg");
	
	Sprite dmercury = new Sprite("dmercury");
	Sprite dvenus = new Sprite("dvenus");
	Sprite dearth = new Sprite("dearth");
	Sprite dmars = new Sprite("dmars");
	Sprite djupiter = new Sprite("djupiter");
	Sprite dsaturn = new Sprite("dsaturn");
	Sprite duranus = new Sprite("duranus");
	Sprite dneptune = new Sprite("dneptune");
	Sprite dpluto = new Sprite("dpluto");
	
	/* Tier 2 - Planet Rotation Gap */
	
	Sprite rmercury = new Sprite("rmercury");
	Sprite rvenus = new Sprite("rvenus");
	Sprite rearth = new Sprite("rearth");
	Sprite rmars = new Sprite("rmars");
	Sprite rjupiter = new Sprite("rjupiter");
	Sprite rsaturn = new Sprite("rsaturn");
	Sprite ruranus = new Sprite("ruranus");
	Sprite rneptune = new Sprite("rneptune");
	Sprite rpluto = new Sprite("rpluto");
	
	
	/* Tier 3 - Actual Sprites &  Moon Orbital Dummy Sprites */

	Sprite mercury = new Sprite("mercury");
	Sprite venus = new Sprite("venus");
	Sprite earth = new Sprite("earth");
	Sprite mars = new Sprite("mars");
	Sprite jupiter = new Sprite("jupiter");
	Sprite saturn = new Sprite("saturn");
	Sprite uranus = new Sprite("uranus");
	Sprite neptune = new Sprite("neptune");
	Sprite pluto = new Sprite("pluto");
	
	Sprite dluna = new Sprite("dluna");
	Sprite dphobos = new Sprite("dphobos");
	Sprite ddeimos = new Sprite("ddeimos");
	Sprite dio = new Sprite("dio");
	Sprite deuropa = new Sprite("deuropa");
	Sprite dganymede = new Sprite("dganymede");
	Sprite dcallisto = new Sprite("dcallisto");
	Sprite drhea = new Sprite("drhea");
	Sprite dtitan = new Sprite("dtitan");
	Sprite doberon = new Sprite("doberon");
	Sprite dtitania = new Sprite("dtitania");
	Sprite dtriton = new Sprite("dtriton");
	Sprite dnereid = new Sprite("dnereid");
	Sprite dcharon = new Sprite("dcharon");
	
	/*Moon Sprites*/
	
	AnimatedSprite luna = new AnimatedSprite("luna","luna.png");
	AnimatedSprite phobos = new AnimatedSprite("phobos");
	AnimatedSprite deimos = new AnimatedSprite("deimos");
	AnimatedSprite io = new AnimatedSprite("io");
	AnimatedSprite europa = new AnimatedSprite("europa");
	AnimatedSprite ganymede = new AnimatedSprite("ganymede");
	AnimatedSprite callisto = new AnimatedSprite("callisto");
	AnimatedSprite rhea = new AnimatedSprite("rhea");
	AnimatedSprite titan = new AnimatedSprite("titan");
	AnimatedSprite oberon = new AnimatedSprite("oberon");
	AnimatedSprite titania = new AnimatedSprite("titania");
	AnimatedSprite triton = new AnimatedSprite("triton");
	AnimatedSprite nereid = new AnimatedSprite("nereid");
	AnimatedSprite charon = new AnimatedSprite("charon");
	
	
	
	Double rate = 1.0; 
	boolean stoggle = true;
	boolean pause = false;
	
	
	public Lab3SolarSystem() {
		super("solar system", 2500, 2500);
		solarSystem.setPosition(1100,1100);
		this.setDHierarchy();
		this.systemImages();
		this.systemScale();
		this.centerSystem();
		this.systemPosition();
	}
	private void systemPosition() {
		Integer mercuryL = 100;
		Integer venusL = 100;
		Integer earthL = 110;
		Integer marsL = 150;
		Integer jupiterL = 300;
		Integer saturnL = 350;
		Integer neptuneL = 400;
		Integer uranusL = 430;
		Integer plutoL = 460;
		rmercury.setPosition(new Point((int)Math.floor(400+mercuryL*Math.sin(dmercury.getRotation())),0));
		rvenus.setPosition(new Point((int)Math.floor(600+venusL*Math.sin(dvenus.getRotation())),0));
		rearth.setPosition(new Point((int)Math.floor(800+earthL*Math.sin(dearth.getRotation())),0));
		rmars.setPosition(new Point((int)Math.floor(1000+marsL*Math.sin(dmars.getRotation())),0));
		rjupiter.setPosition(new Point((int)Math.floor(1200+jupiterL*Math.sin(djupiter.getRotation())),0));
		rsaturn.setPosition(new Point((int)Math.floor(1400+saturnL*Math.sin(dsaturn.getRotation())),0));
		ruranus.setPosition(new Point((int)Math.floor(1600+neptuneL*Math.sin(duranus.getRotation())),0));
		rneptune.setPosition(new Point((int)Math.floor(1800+uranusL*Math.sin(dneptune.getRotation())),0));
		rpluto.setPosition(new Point((int)Math.floor(2000+plutoL*Math.sin(dpluto.getRotation())),0));
		
		luna.setPosition(new Point(0,100));
		phobos.setPosition(new Point(0,100));
		deimos.setPosition(new Point(100,0));
		io.setPosition(new Point(100,0));
		europa.setPosition(new Point(0,100));
		ganymede.setPosition(new Point(-100,0));
		callisto.setPosition(new Point(0,-100));
		rhea.setPosition(new Point(0,100));
		titan.setPosition(new Point(100,0));
		oberon.setPosition(new Point(0,100));
		titania.setPosition(new Point(100,0));
		nereid.setPosition(new Point(0,100));
		charon.setPosition(new Point(100,0));
	}
	private void systemScale() {
		double r = 1.0;
		solarSystem.setScale(.80);
		stars.setScale(3.0);
		stars.setScaleY(4.0);
		mercury.setScale(.25*r);
		venus.setScale(.25*r);
		earth.setScale(.25*r);
		mars.setScale(.25*r);
		jupiter.setScale(.25*r);
		saturn.setScale(.25*r);
		neptune.setScale(.25*r);
		uranus.setScale(.25*r);
		pluto.setScale(.25*r);
		
		luna.setScale(.05);
		phobos.setScale(.5);
		deimos.setScale(.5);
		io.setScale(.5);
		europa.setScale(.5);
		ganymede.setScale(.5);
		callisto.setScale(.5);
		rhea.setScale(.5);
		titan.setScale(.5);
		oberon.setScale(.5);
		titania.setScale(.5);
		nereid.setScale(.5);
		charon.setScale(.5);
		
	}
	private void systemImages() {
		BufferedImage planets = readImage("planets.png");
		
		Integer mwidth = Math.floorDiv(moons.getUnscaledWidth(),8);
		Integer mheight = Math.floorDiv(moons.getUnscaledHeight(),8);
		moons.splitFromSpriteSheet(mwidth, mheight, 8, 8, 0);
		moons.add_animation("spin", 1, 64);
		moons.setBegin(1);
		moons.setEnd(3);
		moons.set_animation("spin");
		Integer pheight = Math.floorDiv(planets.getHeight(), 3);
		Integer pwidth  = Math.floorDiv(planets.getWidth(), 3);
		System.out.println(pheight);
		System.out.println(pwidth);
		mercury.setImage(planets.getSubimage(0, 0, pwidth-220, pheight-170));
		venus.setImage(planets.getSubimage(pwidth+35, 0, pwidth-210, pheight-170));
		earth.setImage(planets.getSubimage(pwidth*2,0,pwidth-130,pheight-170));
		mars.setImage(planets.getSubimage( 0,pheight,pwidth-220,pheight));
		jupiter.setImage(planets.getSubimage(pwidth,pheight,pwidth-40,pheight));
		saturn.setImage(planets.getSubimage(pwidth*2-47,pheight,pwidth+47,pheight));
		uranus.setImage(planets.getSubimage(0,pheight*2+40,pwidth-200,pheight-40));
		neptune.setImage(planets.getSubimage(pwidth,pheight*2+40,pwidth-170,pheight-40));
		pluto.setImage(planets.getSubimage(pwidth*2,pheight*2,pwidth,pheight));
		
		phobos.setSprites(moons.getSprites());
		deimos.setSprites(moons.getSprites());
		io.setSprites(moons.getSprites());
		europa.setSprites(moons.getSprites());
		ganymede.setSprites(moons.getSprites());
		callisto.setSprites(moons.getSprites());
		rhea.setSprites(moons.getSprites());
		titan.setSprites(moons.getSprites());
		oberon.setSprites(moons.getSprites());
		titania.setSprites(moons.getSprites());
		nereid.setSprites(moons.getSprites());
		charon.setSprites(moons.getSprites());
		
		phobos.add_animation("spin", 1, 64);
		deimos.add_animation("spin", 1, 64);
		io.add_animation("spin", 1, 64);
		europa.add_animation("spin", 1, 64);
		ganymede.add_animation("spin", 1, 64);
		callisto.add_animation("spin", 1, 64);
		rhea.add_animation("spin", 1, 64);
		titan.add_animation("spin", 1, 64);
		oberon.add_animation("spin", 1, 64);
		titania.add_animation("spin", 1, 64);
		nereid.add_animation("spin", 1, 64);
		charon.add_animation("spin", 1, 64);
		
		phobos.set_animation("spin");
		deimos.set_animation("spin");
		io.set_animation("spin");
		europa.set_animation("spin");
		ganymede.set_animation("spin");
		callisto.set_animation("spin");
		rhea.set_animation("spin");
		titan.set_animation("spin");
		oberon.set_animation("spin");
		titania.set_animation("spin");
		nereid.set_animation("spin");
		charon.set_animation("spin");
		
		phobos.setSpeed(3);
		deimos.setSpeed(3);
		io.setSpeed(3);
		europa.setSpeed(3);
		ganymede.setSpeed(3);
		callisto.setSpeed(3);
		rhea.setSpeed(3);
		titan.setSpeed(3);
		oberon.setSpeed(3);
		titania.setSpeed(3);
		nereid.setSpeed(3);
		charon.setSpeed(3);
		
		phobos.setImage(moons.getSprites()[0]);
		deimos.setImage(moons.getSprites()[20]);
		io.setImage(moons.getSprites()[15]);
		europa.setImage(moons.getSprites()[8]);
		ganymede.setImage(moons.getSprites()[30]);
		callisto.setImage(moons.getSprites()[1]);
		rhea.setImage(moons.getSprites()[6]);
		titan.setImage(moons.getSprites()[27]);
		oberon.setImage(moons.getSprites()[60]);
		titania.setImage(moons.getSprites()[3]);
		nereid.setImage(moons.getSprites()[10]);
		charon.setImage(moons.getSprites()[36]);
	}
	public void setDHierarchy() {
		/*set system */
		this.addChild(solarSystem);
		
		/*Tier 1*/
		solarSystem.addChild(stars);
		solarSystem.addChild(sun);
//		solarSystem.addChild(moons);
		solarSystem.addChild(dmercury);
		solarSystem.addChild(dvenus);
		solarSystem.addChild(dearth);
		solarSystem.addChild(dmars);
		solarSystem.addChild(djupiter);
		solarSystem.addChild(dsaturn);
		solarSystem.addChild(duranus);
		solarSystem.addChild(dneptune);
		solarSystem.addChild(dpluto);
		
		/*Tier 2*/
		dmercury.addChild(rmercury);
		dvenus.addChild(rvenus);
		dearth.addChild(rearth);
		dmars.addChild(rmars);
		djupiter.addChild(rjupiter);
		dsaturn.addChild(rsaturn);
		duranus.addChild(ruranus);
		dneptune.addChild(rneptune);
		dpluto.addChild(rpluto);
		
		/*Tier 3*/
		rearth.addChild(dluna);
		rmars.addChild(dphobos);
		rmars.addChild(ddeimos);
		rjupiter.addChild(dio);
		rjupiter.addChild(deuropa);
		rjupiter.addChild(dganymede);
		rjupiter.addChild(dcallisto);
		rsaturn.addChild(drhea);
		rsaturn.addChild(dtitan);
		ruranus.addChild(doberon);
		ruranus.addChild(dtitania);
		rneptune.addChild(dtriton);
		rneptune.addChild(dnereid);
		rpluto.addChild(dcharon);
		
		rmercury.addChild(mercury);
		rvenus.addChild(venus);
		rearth.addChild(earth);
		rmars.addChild(mars);
		rjupiter.addChild(jupiter);
		rsaturn.addChild(saturn);
		ruranus.addChild(uranus);
		rneptune.addChild(neptune);
		rpluto.addChild(pluto);
		
		/* Tier 4*/
		dluna.addChild(luna);
		dphobos.addChild(phobos);
		ddeimos.addChild(deimos);
		dio.addChild(io);
		deuropa.addChild(europa);
		dganymede.addChild(ganymede);
		dcallisto.addChild(callisto);
		drhea.addChild(rhea);
		dtitan.addChild(titan);
		doberon.addChild(oberon);
		dtitania.addChild(titania);
		dtriton.addChild(triton);
		dnereid.addChild(nereid);
		dcharon.addChild(charon);
	}
	
	public void systemAnimate() {
		phobos.animate();
		deimos.animate();
		io.animate();
		europa.animate();
		ganymede.animate();
		callisto.animate();
		rhea.animate();
		titan.animate();
		oberon.animate();
		titania.animate();
		nereid.animate();
		charon.animate();
	}
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		
		if(pressedKeys.contains(KeyEvent.VK_A)){
			rate = -Math.abs(rate);
		}
		
		if(pressedKeys.contains(KeyEvent.VK_S)) {
			rate = Math.abs(rate);
		}
		
		if(pressedKeys.contains(KeyEvent.VK_A) ^ pressedKeys.contains(KeyEvent.VK_S)) {
			sun.setRotation(sun.getRotation()+Math.toRadians(360/24*rate));
			dmercury.setRotation(dmercury.getRotation()+Math.toRadians(((360/87.97))*rate));
			dvenus.setRotation(dvenus.getRotation()+Math.toRadians(((360/224.70))*rate));
			dearth.setRotation(dearth.getRotation()+Math.toRadians(((360/365.26))*rate));
			dmars.setRotation(dmars.getRotation()+Math.toRadians(((360/686.98))*rate));
			djupiter.setRotation(djupiter.getRotation()+Math.toRadians(((360/4332.82))*rate));
			dsaturn.setRotation(dsaturn.getRotation()+Math.toRadians(((360/10755.70))*rate));
			duranus.setRotation(duranus.getRotation()+Math.toRadians(((360/30687.15))*rate));
			dneptune.setRotation(dneptune.getRotation()+Math.toRadians(((360/60190.03))*rate));
			dpluto.setRotation(dpluto.getRotation()+Math.toRadians(((360/90553.017))*rate));
			
			dluna.setRotation(dluna.getRotation()+Math.toRadians(360/27*rate));
			dphobos.setRotation(dphobos.getRotation()+Math.toRadians(360/.333*rate));
			ddeimos.setRotation(ddeimos.getRotation()+Math.toRadians(360/1.125*rate));
			dio.setRotation(dio.getRotation()+Math.toRadians(360/1.75*rate));
			deuropa.setRotation(deuropa.getRotation()+Math.toRadians(360/3.541*rate));
			dganymede.setRotation(dganymede.getRotation()+Math.toRadians(360/7.1666*rate));
			dcallisto.setRotation(dcallisto.getRotation()+Math.toRadians(360/17*rate));
			drhea.setRotation(drhea.getRotation()+Math.toRadians(360/4.5*rate));
			dtitan.setRotation(dtitan.getRotation()+Math.toRadians(360/16*rate));
			doberon.setRotation(doberon.getRotation()+Math.toRadians(360/13*rate));
			dtitania.setRotation(dtitania.getRotation()+Math.toRadians(360/8.708*rate));
			dnereid.setRotation(dnereid.getRotation()+Math.toRadians(360/360*rate));
			dcharon.setRotation(dcharon.getRotation()+Math.toRadians(360/6.375*rate));
			this.systemAnimate();
			this.systemPosition();
		}
		/* Pause - Unpause */
//		if(pressedKeys.contains(KeyEvent.VK_SPACE)) {
//			System.out.println("space");
//			if(stoggle && !pause) {
//				System.out.println("pause");
//				pause = true;
//				stoggle = false;
//			}
//			if(stoggle && pause) {
//				System.out.println("unpause");
//				pause = false;
//				stoggle = false;
//			}	
//		}
//		if(!pressedKeys.contains(KeyEvent.VK_SPACE)) stoggle = true;
		/* Change Rate */
		if(pressedKeys.contains(KeyEvent.VK_X))rate*=1.1;
		if(pressedKeys.contains(KeyEvent.VK_Z ))rate /=1.1;
		if(rate>25.0)rate=25.0;
		if(pressedKeys.contains(KeyEvent.VK_Q)) {
			solarSystem.setScale(solarSystem.getScaleX()/1.1);
			stars.setScale(stars.getScaleX()*1.1);
		}
		if(pressedKeys.contains(KeyEvent.VK_W)) {
			solarSystem.setScale(solarSystem.getScaleX()*1.1);
			stars.setScale(stars.getScaleX()/1.1);
		}
		Integer mspeed = 10;
		Point sp = solarSystem.getPosition();
		if(pressedKeys.contains(KeyEvent.VK_RIGHT))solarSystem.setPosition(sp.x-mspeed, sp.y);
		if(pressedKeys.contains(KeyEvent.VK_LEFT))solarSystem.setPosition(sp.x+mspeed, sp.y);
		if(pressedKeys.contains(KeyEvent.VK_UP))solarSystem.setPosition(sp.x, sp.y+mspeed);
		if(pressedKeys.contains(KeyEvent.VK_DOWN))solarSystem.setPosition(sp.x, sp.y-mspeed);
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){	
		super.draw(g);
		solarSystem.draw(g);
//		if(mercury!=null)mercury.draw(g);
	}
	
	public static void main(String[] args) {
		Lab3SolarSystem game = new Lab3SolarSystem();
		game.start();

	}


}
