package fr.meddah.gol;

import java.util.Iterator;

import com.google.common.collect.ContiguousSet;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static com.google.common.base.Strings.*;
import static com.google.common.collect.Iterables.*;
import static fr.meddah.gol.Cell.*;
import static fr.meddah.gol.Tools.*;
import static java.lang.String.*;

public class Board implements Iterable<Cell> {

	Board nextCells() {
		Iterable<Cell> cellsAndDeadNeighbours = concat(extract(boardCells, on(Cell.class).cellsFrom(NEIGHBOURHOOD)));
		return new Board(with(cellsAndDeadNeighbours).distinct().retain(having(on(Cell.class).willBeAliveAround(boardCells))));
	}

	void printBoard() {
		System.out.print(this);
	}

	@Override
	public String toString() {
		ContiguousSet<Long> abscissae = range(minFrom(boardCells).getX(), maxFrom(boardCells).getX());
		ContiguousSet<Long> ordinates = range(minFrom(boardCells).getY(), maxFrom(boardCells).getY());
		String boardDelimiter = format("|%s|%n", repeat("-", abscissae.size()));
		return with(allInstances(Cell.class, abscissae, ordinates)).extract(on(Cell.class).isAliveAround(boardCells)).join("")
			.replace("true", "X").replace("false", " ").replaceAll(format(".{%d}", abscissae.size()), "|$0|\n") + boardDelimiter;
	}

	@Override
	public Iterator<Cell> iterator() {
		return boardCells.iterator();
	}

	Board(Iterable<Cell> cells) {
		this.boardCells = cells;
	}

	private final Iterable<Cell> boardCells;
}