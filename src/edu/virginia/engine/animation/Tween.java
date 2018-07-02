package edu.virginia.engine.animation;

import java.awt.Point;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.EventDispatcher;
//import edu.virginia.engine.events.TweenEvent;

public class Tween extends EventDispatcher {
	private String id;
	private DisplayObject object;
	private ArrayList<TweenParam> tweenParams = new ArrayList<TweenParam>();
	private TweenTransitions.transitions def;
	private boolean complete = false;
	
	public Tween(String id,DisplayObject d) {
		this.id = id;
		this.object = d;
		this.def = TweenTransitions.transitions.LINEAR; 
	}
	public Tween(String id, DisplayObject d,TweenTransitions.transitions transition) {
		this.id = id;
		this.object = d;
		this.def = transition;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DisplayObject getObject() {
		return object;
	}
	public void setObject(DisplayObject object) {
		this.object = object;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public void animate(TweenableParams.Tweenables fieldToAnimate, Double startVal,Double endVal, Double time, TweenTransitions.transitions transition) {
		TweenParam t = new TweenParam(fieldToAnimate,startVal,endVal,time,transition);
		this.tweenParams.add(t);
		}
	public void animate(TweenableParams.Tweenables fieldToAnimate, Double startVal,Double endVal, Double time) {
		TweenParam t = new TweenParam(fieldToAnimate,startVal,endVal,time,this.def);
		this.tweenParams.add(t);
		}
	public void update() {
//		System.out.println("update");
		TweenParam param;
		Double val = 0.0;
		Double percent = 0.0;
		for(int x = 0; x<tweenParams.size();x++) {
			param = this.tweenParams.get(x);
			percent = param.getTweenTime()/param.getTime();
			double percentT = TweenTransitions.applyTransition(percent,param.getTransition());
			val  = percentT*(param.getEndVal()-param.getStartVal())+param.getStartVal();
			if(percent>=1.0) {
				val = param.getEndVal();
				this.tweenParams.remove(x);
				if(tweenParams.size()==0)this.complete = true;
				x--;
			}
			this.setValue(param.getParam(), val);
		}
		
	}
	public boolean isComplete(){
		return this.complete;
	}
	public void setValue(TweenableParams.Tweenables param, Double value) {
		if(param == TweenableParams.Tweenables.Alpha) {
			float f = (float)((double)value);
			object.setAlpha(f);
		}
		else if(param == TweenableParams.Tweenables.PositionX) {
			Point p = object.getPosition();
//			System.out.println(p.x+value);
			object.setPosition(new Point((int)Math.ceil((value)),p.y));
		}
		else if(param == TweenableParams.Tweenables.PositionY) {
			Point p = object.getPosition();
			object.setPosition(new Point(p.x,(int)Math.ceil((value))));
		}
		else if(param == TweenableParams.Tweenables.ScaleX) object.setScaleX(value);
		else if(param == TweenableParams.Tweenables.ScaleY) object.setScaleY(value);
		else if(param == TweenableParams.Tweenables.Scale) object.setScale(value);
	}
}
