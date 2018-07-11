package edu.virginia.engine.events;

import Sprites.Tank;

public class TankEvent extends Event {
	public static final String EXPLODE = "EXPLODE";
	private Tank tank;

	public TankEvent(String eventType, IEventDispatcher source, Tank tank) {
		super(eventType, source);
		this.tank = tank;
		// TODO Auto-generated constructor stub
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}
	
	
}
