package Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;

public class Menu extends DisplayObjectContainer{
	private int numPlayers = 2;
	private int level = 1;
	private DisplayObject titleBackground;
	private boolean inStart = true;
	private boolean playerSelect = false;
	DisplayObjectContainer titleScreen1;
	


	public Menu(String id) {
		super("StartMenu");
		this.titleBackground = new DisplayObject("titleBackground", "StartScreen.png");
		this.titleBackground.setScale(0.7);
		//this.titleScreen1.addChild(titleBackground);//addChild(titleBackground);
	}
	
	public boolean getInStart() {
		return this.inStart;
	}
	public void setStart(boolean b) {
		this.inStart = b;
	}
	public boolean getPlayerSelect() {
		return this.playerSelect;
	}
	public void setPlayerSelect(boolean b) {
		this.playerSelect = b;
	}
	public int getNumPlayers() {
		return this.numPlayers;
	}
	public void setNumPlayers(int n) {
		this.numPlayers = n;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int n) {
		this.level = n;
	}
	
	public void setTitleBackground(String fileName) {
		this.removeChild(titleBackground);
		this.titleBackground = new DisplayObject("titleBackground", fileName);
		this.addChild(titleBackground);
	}
	public DisplayObject getTitleBackground() {
		return this.titleBackground;
	}
	
}
