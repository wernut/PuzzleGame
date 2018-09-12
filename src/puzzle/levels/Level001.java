package puzzle.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import puzzle.Handler;
import puzzle.display.ImageLoader;
import puzzle.display.Utils;
import puzzle.entities.Player;

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
		//g.drawImage(background, 0, 0, 700, 700, null);
		renderLevel(g);
	}
	
	//Temp level hard code
	public void renderLevel(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 700, 350);
		g.setColor(Color.black);
		
		Utils.drawLine(g, 0, 150, 125, 125, 5f);
	}

}
