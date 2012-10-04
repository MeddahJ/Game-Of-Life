package fr.meddah.gol;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.ContiguousSet;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static com.google.common.base.Strings.*;
import static fr.meddah.gol.Tools.*;
import static java.lang.String.*;

public class Board implements Iterable<Cell> {

	public Board next() {
		return new Board(select(livingCellsAndNeighbours(), having(on(Cell.class).willBeAliveAmong(livingCells))));
	}

	private Set<Cell> livingCellsAndNeighbours() {
		return flatMap(livingCells, on(Cell.class).cellsBy(NEIGHBOURS_AND_SELF));
	}

	public boolean hasCells() {
		return iterator().hasNext();
	}

	@Override
	public String toString() {
		ContiguousSet<Long> abscissae = range(minFrom(livingCells).getX(), maxFrom(livingCells).getX());
		ContiguousSet<Long> ordinates = range(minFrom(livingCells).getY(), maxFrom(livingCells).getY());
		return with(allCombinationsOf(Cell.class, abscissae, ordinates)).extract(on(Cell.class).isAliveAmong(livingCells)).join("")
				.replace("true", "X").replace("false", " ").replaceAll(format(".{%d}", abscissae.size()), "$0\n")
				+ repeat("-", abscissae.size());
	}

	@Override
	public Iterator<Cell> iterator() {
		return livingCells.iterator();
	}

	public Board(Collection<Cell> livingCells) {
		this.livingCells = livingCells;
	}

	private final Collection<Cell> livingCells;

	private static final Collection<Cell> NEIGHBOURS_AND_SELF = allCombinationsOf(Cell.class, range(-1, 1), range(-1, 1));
}