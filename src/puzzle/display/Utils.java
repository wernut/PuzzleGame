package puzzle.display;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Utils {
	
	public static void drawLine(Graphics g, int x, int y, int x1, int y1, float st) {
		Graphics g2d = (Graphics2D) g;
		Stroke stroke = new BasicStroke(st);
		((Graphics2D) g).setStroke(stroke);
		g2d.drawLine(x, x1, y, y1);
	}
}
