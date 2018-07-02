package edu.virginia.engine.sound;

import javax.sound.sampled.Clip;

public class Sound {
	String id;
	Clip clip;
	public Sound(String id,Clip clip) {
		this.id=id;
		this.clip=clip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Clip getClip() {
		return clip;
	}
	public void setClip(Clip clip) {
		this.clip = clip;
	}

}
