package fr.meddah.gol;

import java.util.Iterator;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Iterables.*;
import static fr.meddah.gol.Pattern.*;

public class GameOfLife implements Iterator<Board> {

	public static void main(String[] args) {
		GameOfLife game = startGameWith(CELL.by(0, 1),
				BLINKER.by(-2, 0), BLINKER.by(2, 0),
				BLINKER.next().by(0, 2), BLINKER.next().by(0, -2));
		forEach(game).print();
	}

	static GameOfLife startGameWith(Pattern... patterns) {
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

	@Override
	public void remove() {}

	private GameOfLife(Iterable<Cell> cells) {
		this.futureCells = cells;
	}

	private Iterable<Cell> futureCells;
}