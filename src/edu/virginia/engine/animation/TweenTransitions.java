package edu.virginia.engine.animation;

public class TweenTransitions {
	public static enum transitions {LINEAR,QUAD,CUBE,QUART,QUINT,QUADINOUT}
	
	public static Double applyTransition(Double percentDone, TweenTransitions.transitions t) {
		if(t == TweenTransitions.transitions.LINEAR)return percentDone;
		else if(t == TweenTransitions.transitions.QUAD)return quad(percentDone);
		else if(t == TweenTransitions.transitions.CUBE)return cube(percentDone); 
		else if(t == TweenTransitions.transitions.QUART)return quart(percentDone);
		else if(t == TweenTransitions.transitions.QUINT)return quint(percentDone);
		else if(t == TweenTransitions.transitions.QUADINOUT)return easeInOutQuad(percentDone);
		else {
			System.out.println("Transition does not exist");
			return null;
		}
	}
	private static Double quad(double d) {
		return Math.pow(d, 2.0);
	}
	private static Double cube(double d) {
		return Math.pow(d, 3.0);
	}
	private static Double quart(double d) {
		return Math.pow(d, 4.0);
	}
	private static Double quint(double d) {
		return Math.pow(d, 5.0);
	}
	private static Double easeInOutQuad(double t) {
		double d = 1.0;
		double b = 0.0;
		double c = 1.0;
		t /= d/2;
		if (t < 1) return c/2*t*t + b;
		t--;
		return -c/2 * (t*(t-2) - 1) + b;
	}
}
