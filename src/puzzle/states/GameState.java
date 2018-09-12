package puzzle.states;


import java.awt.Graphics;

import puzzle.Handler;
import puzzle.entities.Player;
import puzzle.levels.Level;
import puzzle.levels.Level001;

public class GameState extends State {
	
	private Level001 level1;
	private Player player;

	public GameState(Handler handler) {
		super(handler);
		init();
	}
	
	public void init() {
		level1 = new Level001(handler);
		player = new Player();
		Level.setLevel(level1);
	}

	@Override
	public void tick() {
		if(Level.getLevel() != null) {
			Level.getLevel().tick();
			player.tick();
			
		}
	}

	@Override
	public void render(Graphics g) {
		if(Level.getLevel() != null) {
			Level.getLevel().render(g);
			player.render(g);
		}
	}

}
