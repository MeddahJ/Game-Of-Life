package fr.meddah.gol;

import com.google.common.collect.UnmodifiableIterator;

public class GameOfLife extends UnmodifiableIterator<Board> {

	@Override
	public boolean hasNext() {
		return futureCells.iterator().hasNext();
	}

	@Override
	public Board next() {
		Board board = new Board(futureCells);
		futureCells = board.nextCells();
		return board;
	}

	GameOfLife(Iterable<Cell> cells) {
		this.futureCells = cells;
	}

	private Iterable<Cell> futureCells;
}