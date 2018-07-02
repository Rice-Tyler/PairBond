package edu.virginia.engine.events;



public interface IEventDispatcher {

	public void addEventListener(IEventListener Listener, String eventType);
	public boolean removeEventListener(IEventListener Listener, String eventType);
	public void dispatchEvent(Event event);
	public boolean hasEventListener(IEventListener Listener, String eventType);
}
