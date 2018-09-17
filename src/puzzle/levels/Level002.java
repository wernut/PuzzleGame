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

public class Level002 extends Level {

	private BufferedImage background;

	private Platform platform, platform1, platform2, platform3;

	private Button button1;

	private Wall wall1, wall2, wall3;

	private Turret turret, turret2, turret4;

	private int doorPos = handler.getHeight();

	private boolean loading = true;
	
	private Level003 level3;

	public Level002(Handler handler) {
		super(handler);
		background = ImageLoader.loadImage("res/imgs/levels/level001.png");
		spawnX = 30;
		spawnY = 60;
		player = new Player(handler, spawnX, spawnY);
		initLevel();
	}

	public void initLevel() {
		wall1 = new Wall(handler, 0, 0, handler.getHeight() - 100, 0);
		wall2 = new Wall(handler, handler.getWidth() - 30, 350, handler.getHeight() - 100, 1);
		wall3 = new Wall(handler, handler.getWidth() - 30, 0, 350, 1);
		player.getWalls().add(wall1);
		player.getWalls().add(wall2);
		platform = new Platform(handler, 30, 120, 160, 20);
		platform1 = new Platform(handler, handler.getWidth() - 190, 350, 160, 20);
		platform2 = new Platform(handler, 30, 520, 160, 20);
		platform3 = new Platform(handler, 230, 420, 160, 20);
		player.getPlatforms().add(platform);
		player.getPlatforms().add(platform1);
		player.getPlatforms().add(platform2);
		player.getPlatforms().add(platform3);
		button1 = new Button(handler.getWidth() - 125, 540);
		player.getButtons().add(button1);
		initTurrets();
	}

	public void initTurrets() {
		turret = new Turret(handler, 25, 142, 1, 30, 59);
		turret2 = new Turret(handler, 208, 280, 3, 30, 59);
		turret4 = new Turret(handler, 208, 220, 3, 30, 59);
	}

	@Override
	public void tick() {
		if (loading && player.getX() != 30 && player.getY() != 90) {
			player.x = spawnX;
			player.y = spawnY;
			loading = false;
		} else {
			player.tick();
			turret.tick();
			turret2.tick();
			turret4.tick();
			player.toggleButton(button1);
			if(button1.toggle) {
				int doorHeight = 285;
				doorHeight--;
				wall3.height = doorHeight;
			} else {
				wall3.height = 350;
			}
			if(button1.toggle && player.getX() > handler.getWidth() - 60 && player.getY() >= 285 && player.getY() <= 350) {
				button1.toggle = false;
				player.clearLists();
				level3 = new Level003(handler);
				Level.setLevel(this.level3);
			}
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
		platform2.render(g, Color.YELLOW);
		platform3.render(g, Color.MAGENTA);
		wall1.render(g);
		wall2.render(g);
		wall3.render(g);
		button1.render(g);
		turret.render(g);
		turret2.render(g);
		turret4.render(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Level 2", 40, 30);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("" + Player.playerdeaths, handler.getWidth() - 100, 50);
		g.drawImage(Assets.playerRight, handler.getWidth() - 145, 17, 32, 32, null);
	}

	public Player getPlayer() {
		return player;
	}

}
