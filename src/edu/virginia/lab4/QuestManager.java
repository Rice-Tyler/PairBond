package edu.virginia.lab4;

import java.util.ArrayList;
import java.util.HashMap;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;
import edu.virginia.engine.events.IEventListener;

public class QuestManager extends DisplayObjectContainer implements IEventListener, IEventDispatcher {
	private int num_coins = 0;
	private int total_coins = 0;
	
	public QuestManager(String id) {
		super(id);
		this.setVisible(false);
		this.setImage("youwin.png");
	}

	public int getTotal_coins() {
		return total_coins;
	}

	public void setTotal_coins(int total_coins) {
		this.total_coins = total_coins;
	}

	public int getNum_coins() {
		return this.num_coins;
	}

	public void setNum_coins(int num_coins) {
		this.num_coins = num_coins;
	}

	@Override
	public void handleEvent(Event event) {
		if(event.getEventType()==CoinEvent.COIN_PICKED_UP) {
			setNum_coins(getNum_coins() + 1);
			if(getNum_coins() >= total_coins) {
					this.setVisible(true);
					this.dispatchEvent(new CoinEvent(CoinEvent.ALL_COINS_PICKED_UP,this));
			}
		}

	}
	
	private HashMap<String, ArrayList<IEventListener>> dispatchList = new HashMap<String, ArrayList<IEventListener>>();

	@Override
	public void addEventListener(IEventListener Listener, String eventType) {
		if (dispatchList.containsKey(eventType)){
			dispatchList.get(eventType).add(Listener);
		}
		else {
			dispatchList.put(eventType, new ArrayList<IEventListener>());
			dispatchList.get(eventType).add(Listener);
		}	
	}

	@Override
	public boolean removeEventListener(IEventListener Listener, String eventType) {
		if(dispatchList.containsKey(eventType)) {
			return dispatchList.get(eventType).remove(Listener);
		}
		else {
			System.out.println("Error: Event Type Does Not Exist");
			return false;
		}
	}

	@Override
	public boolean hasEventListener(IEventListener Listener, String eventType) {
		if(dispatchList.containsKey(eventType)) {
			return dispatchList.get(eventType).contains(Listener);
		}
		
		else {
			System.out.println("Error: Event Type Does Not Exist");
			return false;
		}
	}

	@Override
	public void dispatchEvent(Event event) {
		if(dispatchList.containsKey(event.getEventType())) {
			ArrayList<IEventListener> dList = dispatchList.get(event.getEventType());
			for(int x = 0; x<dList.size();x++) {
				dList.get(x).handleEvent(event);
			}
		}
		else {
			System.out.println("Error: Event Type Does Not Exist");
		}
	}


}
