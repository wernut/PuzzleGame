package puzzle.entities.hazards;

import puzzle.Handler;

public class Projectile {

	public int x, y, speed, direction;
	private boolean visible;

	public Projectile(int startX, int startY) {
		this.x = startX;
		this.y = startY;
		speed = 7;
		visible = true;
	}

	public void tick() {
		if (direction == 0) {
			this.y += speed;
		} else if (direction == 1) {
			this.x += speed;
		} else if (direction == 2) {
			this.y -= speed;
		} else if (direction == 3) {
			this.x -= speed;
		}
		if (x < 30 || x > 640 || y < 0 || y > 700) {
			visible = false;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	

}
