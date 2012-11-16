package fr.meddah.gol;

import java.util.Iterator;

import static java.util.Arrays.*;

public enum Pattern implements Iterable<Cell> {

	CELL(cell(0, 0)),
	BLINKER(cell(0, 0), cell(0, 1), cell(0, 2)),
	BLOCK(cell(0, 0), cell(0, 1), cell(1, 0), cell(1, 1)),
	GLIDER(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 2), cell(2, 1)),
	SPACESHIP(cell(0, 0), cell(0, 2), cell(1, 3), cell(2, 3), cell(3, 0), cell(3, 3), cell(4, 1), cell(4, 2), cell(4, 3)); 

	public Iterable<Cell> from(int x, int y) {
		return cell(x, y).cellsFrom(relativeCells);
	}

	public Iterable<Cell> nextFrom(int x, int y) {
		return new Board(from(x, y)).nextCells();
	}

	@Override
	public Iterator<Cell> iterator() {
		return relativeCells.iterator();
	}

	private Pattern(Cell... cells) {
		this.relativeCells = asList(cells);
	}

	private final Iterable<Cell> relativeCells;

	private static Cell cell(int x, int y) {
		return new Cell(x, y);
	}
}