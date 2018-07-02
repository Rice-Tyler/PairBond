package edu.virginia.lab4;

import java.util.ArrayList;
import java.util.HashMap;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;
import edu.virginia.engine.events.IEventListener;

public class CoinManager extends DisplayObjectContainer implements IEventDispatcher {
	HashMap<String, ArrayList<IEventListener>> dispatchList = new HashMap<String, ArrayList<IEventListener>>();
	public CoinManager(String id) {
		super(id);
		dispatchList.put(CoinEvent.COIN_PICKED_UP,new ArrayList<IEventListener>());
	}
	
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

}
