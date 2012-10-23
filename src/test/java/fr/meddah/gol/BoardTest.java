package fr.meddah.gol;

import org.junit.Test;

import static fr.meddah.gol.Pattern.*;
import static org.fest.assertions.api.Assertions.*;

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
		assertThat(blockAfterOneStep).containsAll(BLOCK);
	}

	@Test
	public void blinker_has_period_of_two_and_is_stationary() {
		Board blinkerAfterTwoSteps = new Board(BLINKER).next().next();
		assertThat(blinkerAfterTwoSteps).containsAll(BLINKER);
	}

	@Test
	public void glider_has_period_of_four_and_moves_diagonally() {
		Board gliderAfterFourSteps = new Board(GLIDER).next().next().next().next();
		assertThat(gliderAfterFourSteps).containsAll(GLIDER.from(-1, 1));
	}

	private static String cleanToString(Board board) {
		return board.toString().replaceAll("[-|]", "");
	}
}