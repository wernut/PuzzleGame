package puzzle.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import puzzle.Handler;

public class TileLevel extends Level {

	public int[] pixels;

	public int[] tiles = new int[64 * 64];

	private int width = handler.getWidth(), height = handler.getHeight();

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	private int[] pixelsRaster = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Random random = new Random();

	public TileLevel(Handler handler) {
		super(handler);
		pixels = new int[width * handler.getHeight()];
		
		for(int i = 0; i < 64 * 64; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	@Override
	public void tick() {

	}

	public void setPixelsRaster() {
		for (int i = 0; i < pixelsRaster.length; i++) {
			pixelsRaster[i] = this.pixels[i];
		}
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		this.setPixelsRaster();
		for (int y = 0; y < height; ++y) {
			if (y < 0 || y >= height)
				break;
			for (int x = 0; y < width; ++x) {
				if (x < 0 || x >= width)
					break;
				int tileIndex = (x >> 6) + (y >> 6) * 64;
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
		g.drawImage(image, 0, 0, width, height, null);
	}

}
