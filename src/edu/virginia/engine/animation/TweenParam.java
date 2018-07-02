package edu.virginia.engine.animation;

import edu.virginia.engine.util.GameClock;

public class TweenParam {
	private TweenableParams.Tweenables param;
	private TweenTransitions.transitions transition;
	private Double startVal = 0.0;
	private Double endVal = 0.0;
	private Double time = 0.0;
	private GameClock clock;

	public TweenParam(TweenableParams.Tweenables param,Double startVal, Double endVal, Double time,TweenTransitions.transitions transition) {
		this.param = param;
		this.startVal = startVal;
		this.endVal = endVal;
		this.time = time;
		this.clock = new GameClock();
		this.transition = transition;
	}

	public TweenableParams.Tweenables getParam() {
		return param;
	}

	public void setParam(TweenableParams.Tweenables param) {
		this.param = param;
	}

	public Double getStartVal() {
		return startVal;
	}

	public void setStartVal(Double startVal) {
		this.startVal = startVal;
	}

	public Double getEndVal() {
		return endVal;
	}

	public void setEndVal(Double endVal) {
		this.endVal = endVal;
	}
	public Double getTweenTime() {
		return this.clock.getElapsedTime();
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public TweenTransitions.transitions getTransition() {
		return transition;
	}

	public void setTransition(TweenTransitions.transitions transition) {
		this.transition = transition;
	}
	

}
