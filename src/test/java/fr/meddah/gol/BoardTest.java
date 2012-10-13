package fr.meddah.gol;

import org.junit.Test;

import static com.google.common.collect.Iterables.*;
import static fr.meddah.gol.Pattern.*;
import static org.fest.assertions.Assertions.*;

public class BoardTest {

	@Test
	public void board_is_displayed_properly() {
		Board board = new Board(BLINKER);
		assertThat(cleanToString(board)).isEqualTo("X\nX\nX\n\n");
		assertThat(cleanToString(board.next())).isEqualTo("XXX\n\n");
	}

	@Test
	public void block_is_immobile() {
		Board blockAfterOneStep = new Board(BLOCK).next();
		assertThat(blockAfterOneStep).containsOnly(toCellArray(BLOCK));
	}

	@Test
	public void blinker_has_period_of_two_and_is_stationary() {
		Board blinkerAfterTwoSteps = new Board(BLINKER).next().next();
		assertThat(blinkerAfterTwoSteps).containsOnly(toCellArray(BLINKER));
	}

	@Test
	public void glider_has_period_of_four_and_moves_diagonally() {
		Board gliderAfterFourSteps = new Board(GLIDER).next().next().next().next();
		assertThat(gliderAfterFourSteps).containsOnly(toCellArray(GLIDER.from(-1, 1)));
	}

	private static Object[] toCellArray(Iterable<Cell> pattern) {
		return toArray(pattern, Cell.class);
	}

	private static String cleanToString(Board board) {
		return board.toString().replaceAll("[-|]", "");
	}
}