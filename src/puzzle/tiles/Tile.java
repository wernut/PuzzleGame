package puzzle.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile tiles[] = new Tile[256];
	public static Tile floorTile = new FloorTile(0);
	
	protected BufferedImage texture;
	protected final int id;
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public void tick() {}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getID() {
		return id;
	}
	

}
