package edu.virginia.engine.events;

public class CoinEvent extends Event {
	public static final String COIN_PICKED_UP = "COIN_PICKED_UP";
	public static final String ALL_COINS_PICKED_UP = "ALL_COINS_PICKED_UP";

	public CoinEvent(String eventType, IEventDispatcher source) {
		super(eventType, source);
	}

}
