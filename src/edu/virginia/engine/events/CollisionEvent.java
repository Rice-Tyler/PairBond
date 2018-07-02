package edu.virginia.engine.events;

import edu.virginia.engine.display.DisplayObject;

public class CollisionEvent extends Event {
	public static final String COLLISION = "COLLISION";
	private DisplayObject D1;
//	private DisplayObject D2;

	public CollisionEvent(String eventType, IEventDispatcher source, DisplayObject d1) {
		super(eventType, source);
		this.D1 = d1;
	}

	public DisplayObject getD1() {
		return D1;
	}

	public void setD1(DisplayObject d1) {
		D1 = d1;
	}

}
