package puzzle.interactables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import puzzle.Handler;

public class Wall {

	public int x, y, width, height;
	private Handler handler;
	private final int oldHeight = height;
	private int id;

	public Wall(Handler handler, int x, int y, int height, int id) {
		this.x = x;
		this.y = y;
		this.width = 30;
		this.height = height;
		this.handler = handler;
		this.id = id;
		//0 = left, 1 = right
	}

	public void toggleDoor(boolean toggle, int doorSpeed) {
		if (toggle) {
			int doorHeight = 500;
			doorHeight -= doorSpeed;
			height = doorHeight;
			if (doorHeight == 0) {
				toggle = false;
			}
		}
	}
	
	public int getID() {
		return id;
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
