package puzzle.entities;

import java.awt.Graphics;

public abstract class Entity {
	
	public static final int DEFUALT_HEATH = 10, DEFUALT_SPEED = 10;
	
	public Entity() {
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

}
