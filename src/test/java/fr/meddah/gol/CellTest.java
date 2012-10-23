package fr.meddah.gol;

import java.util.List;

import org.junit.Test;

import static com.google.common.collect.Lists.*;

import static java.util.Arrays.*;
import static org.fest.assertions.api.Assertions.*;
import static org.junit.Assert.*;

public class CellTest {

	@Test
	public void should_game_coverage_metrics() {
		Cell cell = cell(2, 1);
		assertThat(cell.toString()).isEqualTo("Cell{x=2, y=1}");
		assertTrue(cell.equals(cell));
		assertTrue(cell.equals(cell(2, 1)));
		assertTrue(cell.equals(cell("2", 1)));
		assertFalse(cell.equals(cell(2, -1)));
		assertFalse(cell.equals("2 1"));
		assertFalse(cell.equals(null));
	}

	@Test
	public void shoud_translate_by_another_cell() {
		assertThat(cell(2, 0).translateBy(cell(1, 2))).isEqualTo(cell(1, 2).translateBy(cell(2, 0)));
	}

	@Test
	public void should_be_alive() {
		assertThat(cell(0, 1).isAliveAround(asList(cell(0, 1), cell(0, 2)))).isTrue();
	}

	@Test
	public void should_be_dead() {
		assertThat(cell(0, 0).isAliveAround(asList(cell(0, 1), cell(0, 2)))).isFalse();
	}

	@Test
	public void should_die_if_zero_cells_around() {
		Iterable<Cell> noCells = newArrayList();
		assertThat(cell(0, 0).willBeAliveAround(noCells)).isFalse();
	}

	@Test
	public void should_die_if_one_cell_around() {
		Iterable<Cell> oneCell = newArrayList(cell(0, 0));
		assertThat(cell(0, 0).willBeAliveAround(oneCell)).isFalse();
	}

	@Test
	public void should_live_if_two_cells_around() {
		List<Cell> expectedCells = asList(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 1));
		assertThat(cell(0, 2).willBeAliveAround(expectedCells)).isTrue();
	}

	@Test
	public void should_live_if_three_cells_around() {
		List<Cell> expectedCells = asList(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 1));
		assertThat(cell(1, 1).willBeAliveAround(expectedCells)).isTrue();
	}

	@Test
	public void should_die_if_more_than_three_cells_around() {
		List<Cell> expectedCells = asList(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 0), cell(1, 1));
		assertThat(cell(1, 1).willBeAliveAround(expectedCells)).isFalse();
	}

	@Test
	public void should_animate_if_three_cells_around() {
		List<Cell> expectedCells = asList(cell(0, 0), cell(0, 1), cell(0, 2));
		assertThat(cell(1, 1).willBeAliveAround(expectedCells)).isTrue();
	}

	private static Cell cell(Object x, Object y) {
		return new Cell(x, y);
	}
}