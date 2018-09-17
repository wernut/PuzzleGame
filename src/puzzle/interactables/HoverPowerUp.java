package puzzle.interactables;

import java.awt.Graphics;

import puzzle.Handler;
import puzzle.display.Assets;
import puzzle.entities.Player;

public class HoverPowerUp extends PowerUp {

	public HoverPowerUp(Handler handler, int x, int y) {
		super(handler, x, y, 32, 32);
	}

	@Override
	public void render(Graphics g) {
		if(!toggle ) {
			g.drawImage(Assets.hoverOff, x, y, width, height, null);
		} else {
			g.drawImage(Assets.hoverOn, x, y, width, height, null);	
		}
	}

	@Override
	public void tick() {
		toggle();
	}

	@Override
	public void toggle() {
		if(toggle) {
			Player.hover = true;
		} else {
			Player.hover = false;
		}
	}
	

}
