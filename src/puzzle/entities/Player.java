package puzzle.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import puzzle.Handler;
import puzzle.display.Assets;
import puzzle.display.Utils;
import puzzle.interactables.Button;
import puzzle.interactables.Platform;
import puzzle.interactables.PowerUp;
import puzzle.interactables.Wall;
import puzzle.levels.Level;

public class Player extends Entity {

	public int x, y, health;
	public float speed;
	public static final int WIDTH = 32, HEIGHT = 32;
	public float gravity = 7.5f;
	private boolean canJump = true;
	private boolean jumping = false;
	private float jumpVelocity = 12.5f;
	private float yVelocity;
	private float xVelocity;
	private boolean falling = false;
	private int jumpHeight = 250;
	private int ground = handler.getHeight() - 100;
	private boolean facingRight = true;
	public static int playerdeaths = 0;
	private ArrayList<Platform> platforms = new ArrayList<Platform>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
	private Wall wall;

	public static boolean hover = false;

	public Player(Handler handler, int spawnX, int spawnY) {
		super(handler);
		this.health = DEFUALT_HEATH;
		this.speed = 5.5f;
		this.x = spawnX;
		this.y = spawnY;
	}

	@Override
	public void tick() {
		if (!isOnGround(this.y, HEIGHT, ground)) {
			if (!this.collision()) {
				falling = true;
				if (hover) {
					falling = false;
					gravity = 3f;
					this.y += gravity;
				} else {
					gravity = 7.5f;
					this.y += gravity;
				}
			} else {
				falling = false;
				yVelocity = 0;
				canJump = true;
			}
			if (yVelocity >= jumpHeight) {
				this.canJump = false;
				jumping = false;
			}
		} else {
			falling = false;
			yVelocity = 0;
			canJump = true;
		}
		if (jumping) {
			yVelocity += jumpVelocity;
			this.y -= jumpVelocity;
		}
		handleInput();
	}

	private boolean respawnCool = true;

	public void handleInput() {
		if (hover) {
			if (handler.getPuzzle().getKeyManager().up) {
				this.y -= 10;
			}
		} else {
			if (handler.getPuzzle().getKeyManager().up && canJump && !falling) {
				jumping = true;
			}
		}
		if (handler.getPuzzle().getKeyManager().left && canMoveLeft()) {
			move(false);
			facingRight = false;
		}
		if (handler.getPuzzle().getKeyManager().right && canMoveRight()) {
			move(true);
			facingRight = true;
		}
		if (Utils.cooldown(59, 59)) {
			respawnCool = true;
		}
		if (handler.getPuzzle().getKeyManager().enter && respawnCool) {
			respawnCool = false;
			this.respawn(Level.spawnX, Level.spawnY);
		}
	}

	public boolean collision() {
		boolean colliding = false;
		for (Platform platform : platforms) {
			if (getBounds().intersectsLine(platform.getX(), platform.getY(), platform.getX() + platform.getWidth(),
					platform.getY())) {
				colliding = true;
				break;
			}
		}
		return colliding;
	}

	public void toggleButton(Button arg0) {
		for (Button button : buttons) {
			if (this.getBounds().intersects(button.getBounds())) {
				arg0.toggle = true;
			}
		}
	}

	public void togglePowerUp(PowerUp arg0) {
		for (PowerUp pwr : powerups) {
			if (this.getBounds().intersects(pwr.getBounds())) {
				arg0.toggle = true;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (this.facingRight) {
			g.drawImage(Assets.playerRight, x, y, WIDTH, HEIGHT, null);
			g.setColor(Color.RED);
		} else {
			g.drawImage(Assets.playerLeft, x, y, WIDTH, HEIGHT, null);
		}
	}

	public void move(boolean dir) {
		if (dir) {
			xVelocity = 0;
			xVelocity += speed;
			x += xVelocity;
		} else {
			xVelocity = 0;
			xVelocity += speed;
			x -= xVelocity;
		}
	}

	public boolean canMoveLeft() {
		boolean move = true;
		for (Wall wll : walls) {
			if (wll.getID() == 0 && this.getX() <= wll.getBounds().getX() + wll.getBounds().getWidth()) {
				move = false;
			}
		}
		return move;
	}

	public boolean canMoveRight() {
		boolean move = true;
		for (Wall wll : walls) {
			if (wll.getID() == 1 && this.getX() >= wll.getBounds().getX() - wll.getBounds().getWidth()
					&& this.getY() <= wll.getBounds().getY() + wll.getBounds().getHeight()) {
				move = false;
			}
		}
		return move;
	}

	public boolean isInPlatform() {
		boolean inside = false;
		for (Platform platform : platforms) {
			if (!getBounds().intersectsLine(platform.getX(), platform.getY(), platform.getX() + platform.getWidth(),
					platform.getY()) && this.collision()) {
				inside = true;
			} else {
				inside = false;
			}
		}
		return inside;
	}

	public void respawn(int spawnX, int spawnY) {
		for (Button butt : buttons) {
			butt.toggle = false;
		}
		disablePowerups();
		Level.player.x = spawnX;
		Level.player.y = spawnY;
		Player.playerdeaths += 1;
	}

	public void disablePowerups() {
		for (PowerUp pwr : powerups) {
			pwr.toggle = false;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void setX(int x) {
		x = this.x;
	}

	public void setY(int y) {
		y = this.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y - HEIGHT;
	}

	public void clearLists() {
		this.platforms.clear();
		this.buttons.clear();
		this.walls.clear();
		this.powerups.clear();
	}

	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	public ArrayList<Button> getButtons() {
		return buttons;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public ArrayList<PowerUp> getPowerups() {
		return powerups;
	}

}
