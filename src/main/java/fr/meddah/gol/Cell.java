package fr.meddah.gol;

import com.google.common.base.Objects;

import static ch.lambdaj.Lambda.*;
import static com.google.common.base.Objects.*;
import static com.google.common.collect.Iterables.*;
import static fr.meddah.gol.Tools.*;
import static java.lang.Long.*;
import static org.hamcrest.Matchers.*;

public class Cell {

	boolean willBeAliveAround(Iterable<Cell> cells) {
		int neighbours = intersect(cells, cellsFrom(MOORE_NEIGHBOURHOOD)).size();
		return (isAliveAround(cells) && neighbours == 2) || neighbours == 3;
	}

	boolean isAliveAround(Iterable<Cell> cells) {
		return contains(cells, this);
	}

	Iterable<Cell> cellsFrom(Iterable<Cell> cells) {
		return extract(cells, on(Cell.class).translateBy(this));
	}

	Cell translateBy(Cell cell) {
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
			NEIGHBOURHOOD = allInstances(Cell.class, range(-1, 1), range(-1, 1)),
			MOORE_NEIGHBOURHOOD = select(NEIGHBOURHOOD, not(equalTo(new Cell(0, 0))));
}