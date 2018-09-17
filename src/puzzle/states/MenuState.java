package puzzle.states;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import puzzle.Handler;
import puzzle.display.Assets;
import puzzle.display.ImageLoader;
import puzzle.display.ui.UIImageButton;
import puzzle.display.ui.UIManager;
import puzzle.input.ClickListener;

public class MenuState extends State {
	
	private BufferedImage menu;
	
	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		menu = ImageLoader.loadImage("res/imgs/menu/background.png");
		uiManager = new UIManager(handler);
		handler.getPuzzle().getMouseManager().setUiManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 128, handler.getHeight() / 2, 256, 128, Assets.buttonStart, new ClickListener() {
			@Override
			public void onClick() {
				handler.getPuzzle().getMouseManager().setUiManager(null);
				State.setState(handler.getPuzzle().gameState);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(menu, 0, 0, handler.getWidth(), handler.getHeight(), null);
		uiManager.render(g);
	}

}
