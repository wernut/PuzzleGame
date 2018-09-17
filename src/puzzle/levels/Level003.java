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
import puzzle.interactables.HoverPowerUp;
import puzzle.interactables.Platform;
import puzzle.interactables.Wall;

public class Level003 extends Level {

	private BufferedImage background;

	private Platform platform;

	private Button button1;

	private Wall wall1, wall2, wall3;

	private Turret turret, turret2;
	
	private HoverPowerUp hover;

	private int doorPos = handler.getHeight();

	private boolean loading = true;

	public Level003(Handler handler) {
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
		player.getWalls().add(wall3);
		platform = new Platform(handler, 30, 120, 160, 20);
		player.getPlatforms().add(platform);
		button1 = new Button(handler.getWidth() / 2 - 16, 540);
		player.getButtons().add(button1);
		hover = new HoverPowerUp(handler, handler.getWidth() / 2 - 16, handler.getHeight() / 2 - 16);
		player.getPowerups().add(hover);
		initTurrets();
	}

	public void initTurrets() {
		turret = new Turret(handler, 25, 142, 1, 30, 59);
		turret2 = new Turret(handler, 208, 480, 3, 30, 59);
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
			hover.tick();
			player.toggleButton(button1);
			player.togglePowerUp(hover);
			if(button1.toggle) {
				int doorHeight = 285;
				doorHeight--;
				wall3.height = doorHeight;
			} else {
				wall3.height = 350;
			}
			if(button1.toggle && player.getY() > 500 && player.getX() > handler.getWidth() - 60) {
				//button1.toggle = false;
				//player.disablePowerups();
				//player.clearLists();
				//Level.setLevel(this);
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
		wall1.render(g);
		wall2.render(g);
		wall3.render(g);
		button1.render(g);
		turret.render(g);
		turret2.render(g);
		hover.render(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Level 3", 40, 30);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("" + Player.playerdeaths, handler.getWidth() - 100, 50);
		g.drawImage(Assets.playerRight, handler.getWidth() - 145, 17, 32, 32, null);
	}

	public Player getPlayer() {
		return player;
	}

}
