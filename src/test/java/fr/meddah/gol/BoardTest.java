package fr.meddah.gol;

import org.junit.Test;

import static fr.meddah.gol.Pattern.*;
import static java.util.Arrays.*;
import static org.fest.assertions.Assertions.*;

public class BoardTest {

	private Board board;

	@Test
	public void basic_board_tests() {
		board = board(new Cell(0, 0), new Cell(0, 1));
		assertThat(board.hasCells());
		assertThat(board).containsOnly(new Cell(0, 0), new Cell(0, 1));
	}

	@Test
	public void empty_board_remains_empty() {
		board = board();
		assertThat(board.hasCells()).isFalse();
		assertThat(board.next()).isEmpty();
	}

	@Test
	public void block_stabilizes() {
		board = new Board(BLOCK.getCells());
		assertThat(board.toString()).contains("XX\nXX");

		board = board.next();
		assertThat(board).containsOnly(BLOCK.getCells().toArray());
		assertThat(board.toString()).contains("XX\nXX");
	}

	@Test
	public void blinker_has_period_of_two_and_is_stationary() {
		board = new Board(BLINKER.getCells());
		assertThat(board.toString()).contains("X\nX\nX");

		board = board.next();
		assertThat(board).containsOnly(new Cell(-1, 1), new Cell(0, 1), new Cell(1, 1));
		assertThat(board.toString()).contains("XXX");

		board = board.next();
		assertThat(board).containsOnly(BLINKER.getCells().toArray());
		assertThat(new Board(BLINKER.getCells()).next().next()).containsOnly(BLINKER.getCells().toArray());
	}

	@Test
	public void glider_has_period_of_four_and_moves() {
		board = new Board(GLIDER.getCells()).next().next().next().next();
		assertThat(board).containsOnly(GLIDER.by(-1, 1).getCells().toArray());
	}

	private static Board board(Cell... cells) {
		return new Board(asList(cells));
	}
}