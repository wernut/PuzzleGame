package puzzle.entities;

import java.awt.Color;
import java.awt.Graphics;

import puzzle.display.Assets;

public class Player extends Entity {

	public int x, y, speed, health;

	public float gravity = 5f;

	public Player() {
		this.health = DEFUALT_HEATH;
		this.speed = DEFUALT_SPEED;
		this.x = 150;
		this.y = 150;
	}

	public boolean isOnGround() {
		if (this.y >= 350 - 30) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void tick() {
		if(!isOnGround()) {
			this.y += gravity;
		}
	}

	@Override
	public void render(Graphics g) {
		// player image not drawing
		// g.drawImage(Assets.playerRight, x, y, 64, 64, null);
		g.setColor(Color.white);
		g.fillRect(x, y, 30, 30);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 30, 30);

	}

}
