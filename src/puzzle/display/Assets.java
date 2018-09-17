package puzzle.display;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage playerLeft, playerRight, buttonOn, buttonOff, turretLoaded, turretShot;	
	
	public static BufferedImage hoverOn, hoverOff;
	
	public static BufferedImage[] buttonStart;
	//converting into tilebased
	public static BufferedImage floorTile;
	
	
	public static final int WIDTH = 16, HEIGHT = 16;
	
	public static void init() {
		SpriteSheet entities = new SpriteSheet(ImageLoader.loadImage("res/imgs/sprites.png"));	
		playerRight = entities.crop(0, 0, WIDTH, HEIGHT);
		playerLeft = entities.crop(WIDTH, 0, WIDTH, HEIGHT);
		buttonOn = entities.crop(0, HEIGHT, WIDTH, HEIGHT);
		buttonOff = entities.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
		turretLoaded = entities.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
		turretShot = entities.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
		hoverOn = entities.crop(0, HEIGHT * 3, WIDTH, HEIGHT);
		hoverOff = entities.crop(WIDTH, HEIGHT * 3, WIDTH, HEIGHT);
		SpriteSheet world = new SpriteSheet(ImageLoader.loadImage("res/imgs/world.png"));
		floorTile = world.crop(0, 0, WIDTH * 2, HEIGHT);
		
		SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("res/imgs/menu/buttons.png"));
		
		buttonStart = new BufferedImage[2];
		buttonStart[0] = buttons.crop(0, 0, 256, 128);
		buttonStart[1] = buttons.crop(0, 128, 256, 128);
	}
}
