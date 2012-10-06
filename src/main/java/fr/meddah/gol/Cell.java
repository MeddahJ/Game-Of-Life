package fr.meddah.gol;

import java.util.Collection;

import com.google.common.base.Objects;

import static ch.lambdaj.Lambda.*;
import static com.google.common.base.Objects.*;
import static fr.meddah.gol.Tools.*;
import static java.lang.Long.*;
import static org.hamcrest.Matchers.*;

public class Cell {

	public boolean willBeAliveAmong(Collection<Cell> cells) {
		int aliveNeighbours = select(cellsBy(NEIGHBOURS), having(on(Cell.class).isAliveAmong(cells))).size();
		return (isAliveAmong(cells) && aliveNeighbours == 2) || aliveNeighbours == 3;
	}

	public boolean isAliveAmong(Collection<Cell> cells) {
		return cells.contains(this);
	}

	public Collection<Cell> cellsBy(Collection<Cell> cells) {
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

	static final Collection<Cell>
			NEIGHBOURS_AND_SELF = allCombinationsOf(Cell.class, range(-1, 1), range(-1, 1)),
			NEIGHBOURS = select(NEIGHBOURS_AND_SELF, not(equalTo(new Cell(0, 0))));
}