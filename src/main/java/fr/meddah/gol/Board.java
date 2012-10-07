package fr.meddah.gol;

import java.util.Iterator;
import java.util.Set;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static com.google.common.base.Strings.*;
import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Sets.*;
import static fr.meddah.gol.Cell.*;
import static fr.meddah.gol.Tools.*;
import static java.lang.String.*;

public class Board implements Iterable<Cell> {

	Board next() {
		Iterable<Cell> withNeighbours = concat(collect(cells, on(Cell.class).cellsBy(WITH_NEIGHBOURS)));
		return new Board(select(withNeighbours, having(on(Cell.class).willBeAliveAround(cells))));
	}

	void print() {
		System.out.print(this);
	}

	@Override
	public String toString() {
		Set<Long> abscissae = range(minFrom(cells).getX(), maxFrom(cells).getX());
		Set<Long> ordinates = range(minFrom(cells).getY(), maxFrom(cells).getY());
		String delimiter = format("|%s|%n", repeat("-", abscissae.size()));
		return with(allInstances(Cell.class, abscissae, ordinates)).extract(on(Cell.class).isAliveAround(cells)).join("")
				.replace("true", "X").replace("false", " ").replaceAll(format(".{%d}", abscissae.size()), "|$0|\n") + delimiter;
	}

	@Override
	public Iterator<Cell> iterator() {
		return cells.iterator();
	}

	Board(Iterable<Cell> cells) {
		this.cells = newHashSet(cells);
	}

	private final Iterable<Cell> cells;
}