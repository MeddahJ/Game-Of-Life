package fr.meddah.gol;

import java.util.Iterator;

import static java.util.Arrays.*;

public class Pattern implements Iterable<Cell> {

	public static final Pattern
			CELL = pattern(cell(0, 0)),
			BLINKER = pattern(cell(0, -1), cell(0, 0), cell(0, 1)),
			BLOCK = pattern(cell(0, 0), cell(0, 1), cell(1, 0), cell(1, 1)),
			GLIDER = pattern(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 2), cell(2, 1)),
			SPACESHIP = pattern(cell(0, 0), cell(0, 2), cell(1, 3), cell(2, 3),
					cell(3, 0), cell(3, 3), cell(4, 1), cell(4, 2), cell(4, 3));

	public Pattern from(int x, int y) {
		return new Pattern(cell(x, y).cellsFrom(cells));
	}

	public Pattern next() {
		return new Pattern(new Board(cells).next());
	}

	private Pattern(Iterable<Cell> cells) {
		this.cells = cells;
	}

	@Override
	public Iterator<Cell> iterator() {
		return cells.iterator();
	}

	private final Iterable<Cell> cells;

	private static Pattern pattern(Cell... cells) {
		return new Pattern(asList(cells));
	}

	private static Cell cell(int x, int y) {
		return new Cell(x, y);
	}
}