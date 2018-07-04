package edu.virginia.engine.events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher implements IEventDispatcher {
	private HashMap<String, ArrayList<IEventListener>> dispatchList = new HashMap<String, ArrayList<IEventListener>>();

	public HashMap<String, ArrayList<IEventListener>> getDispatchList() {
		return dispatchList;
	}

	public void setDispatchList(HashMap<String, ArrayList<IEventListener>> dispatchList) {
		this.dispatchList = dispatchList;
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
