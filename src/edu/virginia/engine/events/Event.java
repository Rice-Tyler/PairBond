package edu.virginia.engine.events;

public class Event {
	protected String eventType;
	protected IEventDispatcher source;
	public Event(String eventType, IEventDispatcher source) {
		super();
		this.eventType = eventType;
		this.source = source;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public IEventDispatcher getSource() {
		return source;
	}
	public void setSource(IEventDispatcher source) {
		this.source = source;
	}
	
}
