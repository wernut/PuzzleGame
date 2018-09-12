package puzzle.display;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage playerLeft, playerRight;
	private static final int WIDTH = 16, HEIGHT = 16;
	
	public static void init() {
		SpriteSheet entities = new SpriteSheet(ImageLoader.loadImage("res/imgs/sprites.png"));	
		playerRight = entities.crop(0, 0, WIDTH, HEIGHT);
		playerLeft = entities.crop(WIDTH, 0, WIDTH, HEIGHT);
	}
}
