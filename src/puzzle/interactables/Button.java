package puzzle.interactables;

import java.awt.Graphics;
import java.awt.Rectangle;

import puzzle.display.Assets;

public class Button {

	public int x, y, width, height;

	public boolean toggle = false;

	public Button(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 32;
		this.height = 32;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (!toggle) {
			g.drawImage(Assets.buttonOn, x, y, width, height, null);
		} else {
			g.drawImage(Assets.buttonOff, x, y, width, height, null);
		}
	}
	
	public boolean getStatus() {
		if(toggle) {
			return true;
		} else {
			return false;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
