package puzzle.display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import puzzle.Puzzle;

public class Utils {
	
	private static int x, y, width, height;
	
	public static void drawLine(Graphics g, int x, int x1, int y, int y1, float st) {
		Graphics g2d = (Graphics2D) g;
		Stroke stroke = new BasicStroke(st);
		((Graphics2D) g).setStroke(stroke);
		g2d.drawLine(x, y, x1, y1);
	}
	
	public static void drawWall(Graphics g, int x0, int y0, int width0, int height0) {
		x = x0;
		y = y0;
		width = width0;
		height = height0;
		Graphics g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(x0, y0, width0, height0);
	}
	
	public static Rectangle getWallBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public static boolean cooldown(int speed, int speed2) {
		if(Puzzle.ticks == speed || Puzzle.ticks == speed2) {
			return true;
		} else {
			return false;
		}
	}
}
