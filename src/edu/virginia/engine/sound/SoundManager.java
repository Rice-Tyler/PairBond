package edu.virginia.engine.sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.*;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.EventDispatcher;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.events.SoundEvent;

public class SoundManager extends EventDispatcher implements IEventListener {
	ArrayList<Sound> SoundEffects = new ArrayList<Sound>();
	ArrayList<Sound> Music = new ArrayList<Sound>();
	public SoundManager() {
		// TODO Auto-generated constructor stub
	}

	public boolean loadSoundEffect(String id, String Filename) {
		File soundFile = new File(Filename);
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			Sound s = new Sound(id,clip);
			this.SoundEffects.add(s);
			return true;
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	    } catch (LineUnavailableException e) {
	    	e.printStackTrace();
	    }
		return false;
	}
	public boolean loadMusic(String id, String Filename) {
		File soundFile = new File(Filename);
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			Sound s = new Sound(id,clip);
			this.Music.add(s);
			return true;
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
	    } catch (LineUnavailableException e) {
	    	e.printStackTrace();
	    }
		return false;
	}
	public void playSoundEffect(String id) {
		Sound s = null;
		for(int x = 0; x< SoundEffects.size();x++) {
			Sound t = SoundEffects.get(x);
			if(t.getId()==id) {
				s = t;
			}
		}
		if(s!=null) {
			s.getClip().setFramePosition(0);
			s.getClip().start();
		}
		else {
			System.out.println("No Effect with that Id exists");
		}
	}
	public void playMusic(String id) {
		Sound s = null;
		for(int x = 0; x< Music.size();x++) {
			Sound t = Music.get(x);
			if(t.getId()==id) {
				s = t;
			}
		}
		if(s!=null) {
			s.getClip().loop(Clip.LOOP_CONTINUOUSLY);
		}
		else {
			System.out.println("No Music with that Id exists");
		}
	}
	public void stopMusic(String id) {
		Sound s = null;
		for(int x = 0; x< Music.size();x++) {
			Sound t = Music.get(x);
			if(t.getId()==id) {
				s = t;
			}
		}
		if(s!=null) {
			s.getClip().stop();
		}
		else {
			System.out.println("No Music with that Id exists");
		}
	}
	@Override
	public void handleEvent(Event event) {
		if(event.getEventType() == SoundEvent.TRIGGER_MUSIC) {
			SoundEvent s = (SoundEvent)event;
			String id = s.getId();
			this.playMusic(id);
		}
		else if(event.getEventType() == SoundEvent.TRIGGER_SOUND_EFFECT) {
			SoundEvent s = (SoundEvent)event;
			String id = s.getId();
			this.playSoundEffect(id);
		}
		else if(event.getEventType() == SoundEvent.STOP_MUSIC) {
			SoundEvent s = (SoundEvent)event;
			String id = s.getId();
			this.stopMusic(id);
		}
	}
}
