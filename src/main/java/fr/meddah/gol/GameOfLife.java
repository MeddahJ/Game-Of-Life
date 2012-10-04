package fr.meddah.gol;

import java.util.Iterator;
import java.util.Set;

import static ch.lambdaj.Lambda.*;
import static fr.meddah.gol.Pattern.*;
import static fr.meddah.gol.Tools.*;
import static java.util.Arrays.*;

public class GameOfLife implements Iterable<GameOfLife> {

	public static void main(String[] args) {
		forEach(firstRound(SPACESHIP, GLIDER, BLOCK)).play();
	}

	static GameOfLife firstRound(Pattern... patterns) {
		Set<Cell> cells = flatMap(asList(patterns), on(Pattern.class).getCells());
		return new GameOfLife(null, new Board(cells));
	}

	void play() {
		System.out.println(board);
	}

	boolean canContinue() {
		return nextBoard.hasCells();
	}

	GameOfLife continues() {
		return new GameOfLife(nextBoard, nextBoard.next());
	}

	@Override
	public Iterator<GameOfLife> iterator() {
		return new GameIterator(this);
	}

	private GameOfLife(Board board, Board nextBoard) {
		this.board = board;
		this.nextBoard = nextBoard;
	}

	private final Board board, nextBoard;
}

class GameIterator implements Iterator<GameOfLife> {

	@Override
	public boolean hasNext() {
		return game.canContinue();
	}

	@Override
	public GameOfLife next() {
		game = game.continues();
		return game;
	}

	@Override
	public void remove() {}

	GameIterator(GameOfLife game) {
		this.game = game;
	}

	private GameOfLife game;
}