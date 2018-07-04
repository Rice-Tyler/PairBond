package edu.virginia.engine.events;

import edu.virginia.engine.display.Projectile;

public class ProjectileEvent extends Event {
	public static final String PROJECTILE_FIRED = "PROJECTILE_FIRED";
	public static final String PROJECTILE_EXPLODE = "PROJECTILE_EXPLODE";
	private String id;
	private String projectileType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectileType() {
		return projectileType;
	}
	public void setProjectileType(String projectileType) {
		this.projectileType = projectileType;
	}
	public ProjectileEvent(String eventType, IEventDispatcher source, String id, String projectileType){
		super(id, source);
		this.eventType = eventType;
		this.projectileType = projectileType;
		this.id = id;
	}
	

}
