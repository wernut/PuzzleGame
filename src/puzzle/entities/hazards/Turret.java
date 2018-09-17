package puzzle.entities.hazards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import puzzle.Handler;
import puzzle.Puzzle;
import puzzle.display.Assets;
import puzzle.display.Utils;
import puzzle.entities.Player;
import puzzle.levels.Level;
import puzzle.levels.Level001;

public class Turret {

	public int x, y, width, height;

	public boolean canShoot = false;

	public boolean shooting;

	public int dir = 0;

	private BufferedImage tLoaded, tShot;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private Projectile projectile;

	private Handler handler;
	// MAX SPEED 59
	private int turretSpeed, turretSpeed2;

	public Turret(Handler handler, int x, int y, int dir, int speed, int turretSpeed2) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = 48;
		this.height = 48;
		this.dir = dir;
		this.turretSpeed = speed;
		this.turretSpeed2 = turretSpeed2;
		tLoaded = Assets.turretLoaded;
		tShot = Assets.turretShot;
		shooting = true;
	}

	public void tick() {
		tickProjectiles();
	}

	public int rotate(int dir) {
		if (dir == 0)
			return 0;
		else if (dir == 1)
			return 90;
		else if (dir == 2)
			return 180;
		else if (dir == 3)
			return 270;
		else
			return 0;
	}

	public void render(Graphics g) {
		AffineTransform at = AffineTransform.getTranslateInstance(x * 3, y);
		Graphics2D g2d = (Graphics2D) g;
		at.rotate(Math.toRadians(rotate(dir)));
		at.scale(3, 3);
		if (shooting) {
			if (dir == 0 && canShoot) {
				Projectile p = new Projectile(x, y);
				p.setDirection(0);
				if (Utils.cooldown(turretSpeed, turretSpeed2)) {
					projectiles.add(p);
				}
			} else if (dir == 1) {
				Projectile p = new Projectile(x + 25, y + 14);
				p.setDirection(1);
				if (Utils.cooldown(turretSpeed, turretSpeed2)) {
					projectiles.add(p);
				}
			} else if (dir == 2) {
				Projectile p = new Projectile(x, y);
				p.setDirection(2);
				if (Utils.cooldown(turretSpeed, turretSpeed2)) {
					projectiles.add(p);
				}
			} else if (dir == 3) {
				Projectile p = new Projectile(x * 3, y - 34);
				p.setDirection(3);
				if (Utils.cooldown(turretSpeed, turretSpeed2)) {
					projectiles.add(p);
				}
			}
		}
		renderProjectiles(g);
		if (!canShoot) {
			g2d.drawImage(tShot, at, null);
		} else {
			g2d.drawImage(tLoaded, at, null);
		}

	}

	public void tickProjectiles() {
		for (int i = 0; i < projectiles.size(); i++) {
			projectile = projectiles.get(i);
			if (projectile.isVisible()) {
				projectile.tick();
			} else {
				projectiles.remove(i);
			}
		}
	}

	public void renderProjectiles(Graphics g) {
		for (int i = 0; i < projectiles.size(); i++) {
			projectile = projectiles.get(i);
			g.setColor(Color.RED);
			g.fillOval(projectile.getX(), projectile.getY(), 15, 15);
			if (this.getProjectileBounds()
					.intersects(Level.player.getBounds())) {
				handler.getPuzzle().getGameState().getPlayer().respawn(Level.spawnX, Level.spawnY);
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Rectangle getProjectileBounds() {
		return new Rectangle(projectile.getX(), projectile.getY(), 15, 15);
	}

}
