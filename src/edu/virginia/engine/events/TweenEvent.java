package edu.virginia.engine.events;

import edu.virginia.engine.animation.Tween;

public class TweenEvent extends Event {
	public static final String TWEEN_COMPLETE = "TWEEN_COMPLETE";
	private Tween tween;

	public TweenEvent(String eventType, IEventDispatcher source,Tween tween) {
		super(eventType, source);
		this.tween = tween;
		// TODO Auto-generated constructor stub
	}

	public Tween getTween() {
		return tween;
	}

	public void setTween(Tween tween) {
		this.tween = tween;
	}
}
