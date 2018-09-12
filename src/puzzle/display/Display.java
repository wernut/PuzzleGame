package puzzle.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6181722819867291929L;
	private JFrame frame;
	private Canvas canvas;
	public int w, h;
	public String n;

	public Display(int w, int h, String n) {
		this.n = n;
		this.w = w;
		this.h = h;
		drawDisplay();
	}

	private void drawDisplay() {
		frame = new JFrame(n);
		frame.setSize(w, h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(w, h));
		canvas.setMaximumSize(new Dimension(w, h));
		canvas.setMinimumSize(new Dimension(w, h));
		canvas.setFocusable(false);
		frame.add(canvas);
		frame.pack();
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
