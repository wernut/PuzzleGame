package puzzle.interactables;

import java.awt.Graphics;
import java.awt.Rectangle;

import puzzle.Handler;

public abstract class PowerUp {

	protected Handler handler;
	protected int x, y, width, height;
	public boolean toggle = false;
	
	public PowerUp(Handler handler, int x, int y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void toggle();

	public abstract void render(Graphics g);

	public abstract void tick();
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
