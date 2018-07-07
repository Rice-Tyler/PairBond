package edu.virginia.engine.events;

import edu.virginia.engine.display.Projectile;

public class ProjectileEvent extends Event {
	public static final String PROJECTILE_FIRED = "PROJECTILE_FIRED";
	public static final String PROJECTILE_EXPLODE = "PROJECTILE_EXPLODE";
	String projectileType;
	public ProjectileEvent(String id, IEventDispatcher source, String eventType, String projectileType){
		super(id, source);
		this.eventType = eventType;
		this.projectileType = projectileType;
	}
	

}
