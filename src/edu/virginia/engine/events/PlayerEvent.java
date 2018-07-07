package edu.virginia.engine.events;

public class PlayerEvent extends Event {
	public static final String FIRE = "FIRE";
	public static final String TURN_END = "TURN_END";
	
	public PlayerEvent(IEventDispatcher source, String eventType) {
		super(eventType, source);
	}
}