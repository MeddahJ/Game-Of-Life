package fr.meddah.gol;

import java.util.Iterator;

import static ch.lambdaj.Lambda.*;
import static fr.meddah.gol.Pattern.*;
import static fr.meddah.gol.Tools.*;
import static java.util.Arrays.*;

public class GameOfLife implements Iterator<GameOfLife> {

	public static void main(String[] args) {
		forEach(firstRound(GLIDER)).play();
	}

	static GameOfLife firstRound(Pattern... patterns) {
		return new GameOfLife(new Board(flatMap(asList(patterns), on(Pattern.class).getCells())));
	}

	void play() {
		System.out.println(board);
	}

	@Override
	public boolean hasNext() {
		return nextBoard.hasCells();
	}

	@Override
	public GameOfLife next() {
		board = nextBoard;
		nextBoard = nextBoard.next();
		return this;
	}

	@Override
	public void remove() {}

	private GameOfLife(Board nextBoard) {
		this.nextBoard = nextBoard;
	}

	private Board board, nextBoard;
}