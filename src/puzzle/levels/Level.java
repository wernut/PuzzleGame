package puzzle.levels;

import java.awt.Graphics;

import puzzle.Handler;

public abstract class Level {

	public static Level currentLevel = null;

	protected Handler handler;

	public Level(Handler handler) {
		this.handler = handler;
	}

	public static void setLevel(Level level) {
		currentLevel = level;
	}

	public static Level getLevel() {
		return currentLevel;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
