package puzzle.tiles;

import puzzle.display.Assets;

public class FloorTile extends Tile{

	public FloorTile(int id) {
		super(Assets.floorTile, 0);
	}
	
	public boolean isSolid() {
		return true;
	}

}
