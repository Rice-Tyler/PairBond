package Sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.CoinEvent;
import edu.virginia.engine.events.IEventListener;

public class Coin extends Sprite {
	HashMap<String, ArrayList<IEventListener>> dispatchList = new HashMap<String, ArrayList<IEventListener>>();
	private boolean pickedUp = false;
	public Coin(String id, String fileName) {
		super(id, fileName);
		dispatchList.put(CoinEvent.COIN_PICKED_UP,new ArrayList<IEventListener>());
	}
	

	public Coin(String id) {
		super(id);
		BufferedImage img = readImage("Coin.png");
		int woff = 60;
		int hoff = 40;
		this.setImage(img.getSubimage(woff, hoff, img.getWidth()-woff-55, img.getHeight()-hoff-45));
		dispatchList.put(CoinEvent.COIN_PICKED_UP,new ArrayList<IEventListener>());
	}


	public boolean isPickedUp() {
		return pickedUp;
	}


	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}


	

}
