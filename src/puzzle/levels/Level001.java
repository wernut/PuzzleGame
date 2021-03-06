package puzzle.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import puzzle.Handler;
import puzzle.display.Assets;
import puzzle.display.ImageLoader;
import puzzle.entities.Player;
import puzzle.entities.hazards.Turret;
import puzzle.interactables.Button;
import puzzle.interactables.Platform;
import puzzle.interactables.Wall;

public class Level001 extends Level {

	private BufferedImage background;

	private Platform platform, platform1;

	private Button button;

	private Wall wall1, wall2;

	private Turret turret, turret2;

	private int doorPos = handler.getHeight();
	
	private Level002 level2;

	public Level001(Handler handler) {
		super(handler);
		background = ImageLoader.loadImage("res/imgs/levels/level001.png");
		spawnX = 30;
		spawnY = 60;
		player = new Player(handler, spawnX, spawnY);
		initLevel();
	}

	public void initLevel() {
		player.clearLists();
		wall1 = new Wall(handler, 0, 0, handler.getHeight() - 100, 0);
		wall2 = new Wall(handler, handler.getWidth() - 30, 0, handler.getHeight() - 100, 1);
		player.getWalls().add(wall1);
		player.getWalls().add(wall2);
		platform = new Platform(handler, 30, 120, 160, 20);
		platform1 = new Platform(handler, handler.getWidth() - 190, 350, 160, 20);
		player.getPlatforms().add(platform);
		player.getPlatforms().add(platform1);
		button = new Button(handler.getWidth() - 125, 280);
		player.getButtons().add(button);
		initTurrets();
	}

	public void initTurrets() {
		turret = new Turret(handler, 25, 142, 1, 30, 59);
		turret2 = new Turret(handler, 208, 420, 3, 30, 59);
	}

	@Override
	public void tick() {
		player.tick();
		turret.tick();
		turret2.tick();
		player.toggleButton(button);
		if(button.toggle) {
			wall2.toggleDoor(button.toggle, 2);	
		} else {
			wall2.height = handler.getHeight();
		}
		if(button.toggle && player.getY() > 500 && player.getX() > handler.getWidth() - 60) {
			button.toggle = false;
			player.clearLists();
			level2 = new Level002(handler);
			Level.setLevel(this.level2);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, 700, 700, null);
		renderLevel(g);
		player.render(g);
	}

	public void renderLevel(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setColor(Color.LIGHT_GRAY);
		// shadow
		g.fillRect(0, 0, 700, handler.getHeight() - 100);
		g.setColor(Color.black);
		platform.render(g, Color.GREEN);
		platform1.render(g, Color.RED);
		wall1.render(g);
		wall2.render(g);
		button.render(g);
		turret.render(g);
		turret2.render(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Level 1", 40, 30);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("" + Player.playerdeaths, handler.getWidth() - 100, 50);
		g.drawImage(Assets.playerRight, handler.getWidth() - 145, 17, 32, 32, null);
	}

	public Player getPlayer() {
		return player;
	}

}
