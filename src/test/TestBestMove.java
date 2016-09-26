package test;

import static org.junit.Assert.*;
import org.junit.Test;

import ticTacToe2D.Deadline;
import ticTacToe2D.GameState;
import ticTacToe2D.Player;

public class TestBestMove {
	
	private Deadline deadline = new Deadline(Deadline.getCpuTime() + (long) 1e9);

	@Test
	public void testMinMax(){
		Player testPlayer = new Player();
		
		// stop other player from winning
		String init = "...xxo....o....o 0_5_2 o";
		GameState testState = new GameState(init);
		GameState nextState = testPlayer.play(testState, deadline);
		String expected = "x..xxo....o....o 0_0_1 x";
		String actual = nextState.toMessage();
		assertTrue(expected.equals(actual));
		
		
		// try to win
		init = ".oo..xo...x...ox 0_2_2 o";
		//String init = "o..o..o......xxx 0_0_2 o;";
		testState = new GameState(init);
		nextState = testPlayer.play(testState, deadline);
		expected = "xoo..xo...x...ox -2_0_1 x";
		actual = nextState.toMessage();
		assertTrue(expected.equals(actual));
	}
}
