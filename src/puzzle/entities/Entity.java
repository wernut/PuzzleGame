package puzzle.entities;

import java.awt.Graphics;

import puzzle.Handler;

public abstract class Entity {

	public static final int DEFUALT_HEATH = 10;
	public static final float DEFUALT_SPEED = 7.5f;

	protected Handler handler;

	public Entity(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public boolean isOnGround(int y, int height, int groundY) {
		if (y + height >= groundY) {
			return true;
		} else {
			return false;
		}
	}

}
