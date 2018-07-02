package edu.virginia.engine.events;

public class SoundEvent extends Event {
	public static final String TRIGGER_SOUND_EFFECT ="TRIGGER_SOUND_EFFECT";
	public static final String TRIGGER_MUSIC = "TRIGGER_MUSIC";
	public static final String STOP_MUSIC = "STOP_MUSIC";
	private String id;
	public SoundEvent(String eventType, IEventDispatcher source, String id) {
		super(eventType, source);
		this.id = id;
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
