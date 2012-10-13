package fr.meddah.gol;

import com.google.common.collect.UnmodifiableIterator;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Iterables.*;
import static fr.meddah.gol.Pattern.*;

public class GameOfLife extends UnmodifiableIterator<Board> {

	public static void main(String[] args) {
		forEach(game(CELL.from(0, 1), BLINKER.from(-2, 0), BLINKER.from(2, 0), BLINKER.next().from(0, 2), BLINKER.next().from(0, -2))).print();
	}

	static GameOfLife game(Pattern... patterns) {
		return new GameOfLife(concat(patterns));
	}

	@Override
	public boolean hasNext() {
		return futureCells.iterator().hasNext();
	}

	@Override
	public Board next() {
		Board cells = new Board(futureCells);
		futureCells = cells.next();
		return cells;
	}

	private GameOfLife(Iterable<Cell> cells) {
		this.futureCells = cells;
	}

	private Iterable<Cell> futureCells;
}