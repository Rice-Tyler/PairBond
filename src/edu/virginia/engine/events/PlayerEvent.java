package edu.virginia.engine.events;

public class PlayerEvent extends Event {
	public static final String TURNEND = "TURNEND";
	
	public PlayerEvent(IEventDispatcher source, String eventType) {
		super(eventType, source);
	}
}