package fr.meddah.gol;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.ContiguousSet;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static com.google.common.base.Strings.*;
import static fr.meddah.gol.Cell.*;
import static fr.meddah.gol.Tools.*;
import static java.lang.String.*;

public class Board implements Iterable<Cell> {

	public Board next() {
		return new Board(select(aliveCellsAndNeighbours(), having(on(Cell.class).willBeAliveAmong(aliveCells))));
	}

	private Set<Cell> aliveCellsAndNeighbours() {
		return flatMap(aliveCells, on(Cell.class).cellsBy(NEIGHBOURS_AND_SELF));
	}

	public boolean hasCells() {
		return iterator().hasNext();
	}

	@Override
	public String toString() {
		ContiguousSet<Long> abscissae = range(minFrom(aliveCells).getX(), maxFrom(aliveCells).getX());
		ContiguousSet<Long> ordinates = range(minFrom(aliveCells).getY(), maxFrom(aliveCells).getY());
		return with(allCombinationsOf(Cell.class, abscissae, ordinates)).extract(on(Cell.class).isAliveAmong(aliveCells)).join("")
				.replace("true", "X").replace("false", " ").replaceAll(format(".{%d}", abscissae.size()), "$0\n")
				+ repeat("-", abscissae.size());
	}

	@Override
	public Iterator<Cell> iterator() {
		return aliveCells.iterator();
	}

	public Board(Collection<Cell> cells) {
		this.aliveCells = cells;
	}

	private final Collection<Cell> aliveCells;
}