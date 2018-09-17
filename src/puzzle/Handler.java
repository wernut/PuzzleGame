package puzzle;

import puzzle.display.Display;
import puzzle.interactables.Platform;
import puzzle.levels.Level001;

public class Handler {

	private Puzzle puzzle;
	private Display display;
	public Handler(Puzzle p) {
		this.puzzle = p;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public Display getDisplay() {
		return display;
	}

	public int getWidth() {
		return puzzle.width;
	}

	public int getHeight() {
		return puzzle.height;
	}
}
