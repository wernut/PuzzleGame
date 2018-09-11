package puzzle.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import puzzle.Handler;
import puzzle.display.ImageLoader;

public class Level001 extends Level {

	private BufferedImage background;

	public Level001(Handler handler) {
		super(handler);
		background = ImageLoader.loadImage("res/imgs/levels/level001.png");
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, 700, 700, null);
	}

}
