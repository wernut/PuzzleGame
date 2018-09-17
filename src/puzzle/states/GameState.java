package puzzle.states;


import java.awt.Graphics;
import puzzle.Handler;
import puzzle.entities.Player;
import puzzle.levels.Level;
import puzzle.levels.Level001;
import puzzle.levels.Level002;
import puzzle.levels.Level003;
import puzzle.levels.TileLevel;

public class GameState extends State {
	
	public Level001 level1;

	public GameState(Handler handler) {
		super(handler);
		init();
	}
	
	public void init() {
		level1 = new Level001(handler);
		Level.setLevel(level1);
	}

	@Override
	public void tick() {
		if(Level.getLevel() != null) {
			Level.getLevel().tick();
		}
	}

	@Override
	public void render(Graphics g) {
		if(Level.getLevel() != null) {
			Level.getLevel().render(g);
		}
	}

	public Level001 getLevel1() {
		return level1;
	}
	
	public Player getPlayer() {
		return Level.player;
	}
	
	

}
