package fr.meddah.gol;

import org.junit.Test;

import static com.google.common.collect.Lists.*;

import static java.util.Collections.*;
import static org.fest.assertions.Assertions.*;

public class CellTest {

	@Test
	public void should_game_coverage_stats() {
		Cell cell = cell(2, 0);
		assertThat(cell).isEqualTo(cell);
		assertThat(cell).isEqualTo(cell(2, 0));
		assertThat(cell(2, 0)).isEqualTo(cell);
		assertThat(cell).isEqualTo(cell("2", "0"));
		assertThat(cell).isEqualTo(cell(2, "0"));
		assertThat(cell).isNotEqualTo(cell(-2, 0));
		assertThat(cell).isNotEqualTo("2 0");
		assertThat(cell).isNotEqualTo(null);
		assertThat(cell.toString()).isEqualTo("Cell{x=2, y=0}");
	}

	@Test
	public void shoud_translate_by_another_cell() {
		assertThat(cell(2, 0).moveBy(cell(1, 2))).isEqualTo(cell(3, 2));
		assertThat(cell(2, 0).moveBy(cell(1, 2))).isEqualTo(cell(1, 2).moveBy(cell(2, 0)));
	}

	@Test
	public void should_be_alive() {
		assertThat(cell(0, 1).isAliveAmong(newArrayList(cell(0, 1), cell(0, 2)))).isTrue();
	}

	@Test
	public void should_be_dead() {
		assertThat(cell(0, 0).isAliveAmong(newArrayList(cell(0, 1), cell(0, 2)))).isFalse();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void should_die_if_less_than_two_cells_around() {
		assertThat(cell(0, 0).willBeAliveAmong(EMPTY_LIST)).isFalse();
		assertThat(cell(0, 0).willBeAliveAmong(newArrayList(cell(0, 0)))).isFalse();
	}

	@Test
	public void should_live_if_two_or_three_cells_around() {
		assertThat(cell(1, 1).willBeAliveAmong(newArrayList(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 1)))).isTrue();

		assertThat(cell(-1, 0).willBeAliveAmong(newArrayList(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 1)))).isFalse();
	}

	@Test
	public void should_die_if_more_than_three_cells_around() {
		assertThat(cell(1, 1).willBeAliveAmong(newArrayList(cell(0, 0), cell(0, 1), cell(0, 2), cell(1, 0), cell(1, 1)))).isFalse();
	}

	@Test
	public void should_animate_if_three_cells_around() {
		assertThat(cell(1, 1).willBeAliveAmong(newArrayList(cell(0, 0), cell(0, 1), cell(0, 2)))).isTrue();
	}

	private static Cell cell(Object x, Object y) {
		return new Cell(x, y);
	}
}
