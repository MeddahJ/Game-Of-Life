package fr.meddah.gol;

import com.google.common.base.Objects;

import static ch.lambdaj.Lambda.*;
import static com.google.common.base.Objects.*;
import static com.google.common.collect.Iterables.*;
import static fr.meddah.gol.Tools.*;
import static java.lang.Long.*;
import static org.hamcrest.Matchers.*;

public class Cell {

	public boolean willBeAliveAround(Iterable<Cell> cells) {
		int aliveNeighbours = intersect(cellsBy(ONLY_NEIGHBOURS), cells).size();
		return (isAliveAround(cells) && aliveNeighbours == 2) || aliveNeighbours == 3;
	}

	public boolean isAliveAround(Iterable<Cell> cells) {
		return contains(cells, this);
	}

	public Iterable<Cell> cellsBy(Iterable<Cell> cells) {
		return collect(cells, on(Cell.class).moveBy(this));
	}

	public Cell moveBy(Cell cell) {
		return new Cell(cell.x + x, cell.y + y);
	}

	@Override
	public boolean equals(Object other) {//@formatter:off
		return (other == this) ? true
			 : (other == null) ? false
			 : (other instanceof Cell) ? equal(((Cell) other).x, x) && equal(((Cell) other).y, y)
			 : false;
	}//@formatter:on

	@Override
	public int hashCode() {
		return Objects.hashCode(x, y);
	}

	@Override
	public String toString() {
		return toStringHelper(Cell.class).add("x", x).add("y", y).toString();
	}

	public Cell(Object x, Object y) {
		this.x = parseLong(x.toString());
		this.y = parseLong(y.toString());
	}

	public long getX() {
		return x;
	}

	public long getY() {
		return y;
	}

	private final long x, y;

	static final Iterable<Cell>
			WITH_NEIGHBOURS = allInstances(Cell.class, range(-1, 1), range(-1, 1)),
			ONLY_NEIGHBOURS = select(WITH_NEIGHBOURS, not(equalTo(new Cell(0, 0))));
}