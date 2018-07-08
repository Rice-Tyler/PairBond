package edu.virginia.engine.events;

public class GameEvent extends Event {
	public static final String GAME_OVER = "GAME_OVER";
	public GameEvent(String eventType, IEventDispatcher source) {
		super(eventType, source);
		// TODO Auto-generated constructor stub
	}
	
}
