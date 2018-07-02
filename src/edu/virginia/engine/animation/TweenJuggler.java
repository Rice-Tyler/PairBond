package edu.virginia.engine.animation;

import java.util.ArrayList;

import edu.virginia.engine.events.EventDispatcher;
import edu.virginia.engine.events.TweenEvent;

public class TweenJuggler extends EventDispatcher {
	private static TweenJuggler instance;
	private ArrayList<Tween> Tweens = new ArrayList<Tween>();
	public TweenJuggler() {
		if(instance==null)instance=this;
		else System.out.println("Cannot Instantiate Second Singleton Class");
	}
	public TweenJuggler getInstance() {
		return instance;
	}
	public void add(Tween t) {
		this.Tweens.add(t);
	}
	public void nextFrame() {
		for(int x = 0; x<this.Tweens.size();x++) {
			Tween temp = this.Tweens.get(x);
			temp.update();
			if(temp.isComplete()) {
				this.Tweens.remove(x);
				this.dispatchEvent(new TweenEvent(TweenEvent.TWEEN_COMPLETE,this,temp));
				x--;
			}
			
		}
	}
}
