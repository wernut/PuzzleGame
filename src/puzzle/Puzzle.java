package puzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import puzzle.display.Assets;
import puzzle.display.Display;
import puzzle.input.KeyManager;
import puzzle.input.MouseManager;
import puzzle.states.GameState;
import puzzle.states.MenuState;
import puzzle.states.State;

public class Puzzle implements Runnable {

	public int width = 700, height = 700;
	public int scale = 8;
	private String title = "Puzz Puzz";
	private boolean running = false;
	private Thread thread;
	private BufferStrategy bs;
	private Display display;
	private Graphics g;
	public GameState gameState;
	public MenuState menuState;
	private Handler handler;
	private KeyManager keyManager;
	private MouseManager mouseManager;

	public Puzzle() {
		this.start();
	}

	public static void main(String[] args0) {
		Puzzle p = new Puzzle();
	}

	public static long ticks = 0;

	@Override
	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	public void init() {
		display = new Display(width, height, title);
		handler = new Handler(this);
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}

	public void tick() {
		keyManager.tick();
		if (State.getState() != null) {
			State.getState().tick();
		}
	}

	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!
		if (State.getState() != null) {
			State.getState().render(g);
		}
		// End Drawing!
		bs.show();
		g.dispose();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager() {
		return this.keyManager;
	}

	public GameState getGameState() {
		return gameState;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}
}
