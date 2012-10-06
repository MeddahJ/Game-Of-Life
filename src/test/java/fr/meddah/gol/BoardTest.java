package fr.meddah.gol;

import java.util.Collections;

import org.junit.Test;

import static fr.meddah.gol.Pattern.*;
import static org.fest.assertions.Assertions.*;

public class BoardTest {

	@Test
	public void empty_board_remains_empty() {
		Board board = new Board(Collections.<Cell> emptyList());
		assertThat(board.hasCells()).isFalse();
		assertThat(board.next().hasCells()).isFalse();
	}

	@Test
	public void board_is_displayed_properly() {
		Board board = new Board(BLINKER.getCells());
		assertThat(board.toString()).startsWith("X\nX\nX\n");
		assertThat(board.next().toString()).startsWith("XXX\n");
	}

	@Test
	public void block_is_immobile() {
		Board blockAfterOneStep = new Board(BLOCK.getCells()).next();
		assertThat(blockAfterOneStep).containsOnly(BLOCK.getCells().toArray());
	}

	@Test
	public void blinker_has_period_of_two_and_is_stationary() {
		Board blinkerAfterTwoSteps = new Board(BLINKER.getCells()).next().next();
		assertThat(blinkerAfterTwoSteps).containsOnly(BLINKER.getCells().toArray());
	}

	@Test
	public void glider_has_period_of_four_and_moves_diagonally() {
		Board gliderAfterFourSteps = new Board(GLIDER.getCells()).next().next().next().next();
		assertThat(gliderAfterFourSteps).containsOnly(GLIDER.by(-1, 1).getCells().toArray());
	}
}