package edu.virginia.engine.events;

public class ExplosionEvent extends Event {
	Double duration = 0.0;
	public ExplosionEvent(String eventType, IEventDispatcher source, Double duration) {
		super(eventType, source);
		this.duration = duration;
		// TODO Auto-generated constructor stub
	}
	public static final String EXP_START = "EXP_START";
	public static final String EXP_END = "EXP_END";
}
