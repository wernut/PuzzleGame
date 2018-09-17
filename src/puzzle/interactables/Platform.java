package puzzle.interactables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import puzzle.Handler;
import puzzle.display.Utils;

public class Platform {

	public int x, y, width, height;
	

	public Platform(Handler handler, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		Utils.drawLine(g, x, x + width, y, y, 2f);
		Utils.drawLine(g, x, x, y, y + height, 2f);
		Utils.drawLine(g, x + width, x + width, y, y + height, 2f);
		Utils.drawLine(g, x, x + width, y + height, y + height, 2f);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
