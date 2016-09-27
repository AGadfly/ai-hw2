//package ticTacToe3D;
import java.util.Vector;

public class Heuristics {

	/**
	 * Simple heuristic function fpr 2DTicTacToe which counts the
	 * number of different symbols and the board and favors the player
	 * having more advantagous symbols.
	 * 
	 * @param state Current gameState
	 * @return value representing the evaluation of that state
	 */
	public static int evaluate(GameState state){
   		int x = 0; 
		int o = 0; 
		int score = 0;
		
		// check for win
		if(state.isEOG()){
			if(state.isXWin()){
				return 100000;
			} else if(state.isOWin()){
				return -100000;
			} else {
				return 0;
			}	
		}

		// look ahead one step
	    Vector<GameState> nextStates = new Vector<GameState>();
	    state.findPossibleMoves(nextStates);
	    for(GameState child: nextStates){
			if(child.isEOG()){ // guard clause for win
				if(child.isXWin()){
					return 100000;
				} else if(child.isOWin()){
					return 100000;
				} else {
					return 0;
				}	
			}
		}

	    // evaluate in y direction
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int j = 0; j < GameState.BOARD_SIZE; j++) {
				for (int k = 0; k < GameState.BOARD_SIZE; k++) {
					switch (state.at(i, k, j)) {
					case (1):
						x += 1;
						break;

					case (2):
						o += 1;
						break;
					}
				}
				score += calculateScore(o, x);
				x = 0;
				o = 0;
			}
		}

		// evaluate z direction
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int j = 0; j < GameState.BOARD_SIZE; j++) {
				for (int k = 0; k < GameState.BOARD_SIZE; k++) {
					switch (state.at(i, j, k)) {
					case (1):
						x += 1;
						break;

					case (2):
						o += 1;
						break;
					}
				}
				score += calculateScore(o, x);
				x = 0;
				o = 0;
			}
		}
		
		// evaluate x direction
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int j = 0; j < GameState.BOARD_SIZE; j++) {
				for (int k = 0; k < GameState.BOARD_SIZE; k++) {
					switch (state.at(k, i, j)) {
					case (1):
						x += 1;
						break;

					case (2):
						o += 1;
						break;
					}
				}
				score += calculateScore(o, x);
				x = 0;
				o = 0;
			}
		}

		// evaluate diagionals in x plane
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int k = 0; k < GameState.BOARD_SIZE; k++) {
				switch (state.at(i, k, k)) {
				case (1):
					x += 1;
					break;

				case (2):
					o += 1;
					break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}
		
		// evaluate other diagionals in x plane
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int k = 0; k < GameState.BOARD_SIZE; k++) {
				switch (state.at(i, k, GameState.BOARD_SIZE - k)) {
				case (1):
					x += 1;
					break;

				case (2):
					o += 1;
					break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}

		// evaluate diagionals in x plane
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int k = 0; k < GameState.BOARD_SIZE; k++) {
				switch (state.at(GameState.BOARD_SIZE - k, i, k)) {
				case (1):
					x += 1;
					break;

				case (2):
					o += 1;
					break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}

		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int k = 0; k < GameState.BOARD_SIZE; k++) {
				switch (state.at(k, i, k)) {
				case (1):
					x += 1;
					break;

				case (2):
					o += 1;
					break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}

		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int k = 0; k < GameState.BOARD_SIZE; k++) {
				switch (state.at(k, GameState.BOARD_SIZE - k, i)) {
				case (1):
					x += 1;
					break;

				case (2):
					o += 1;
					break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}

		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int k = 0; k < GameState.BOARD_SIZE; k++) {
				switch (state.at(k, k, i)) {
				case (1):
					x += 1;
					break;

				case (2):
					o += 1;
					break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}

		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			switch (state.at(i, i, i)) {
			case (1):
				x += 1;
				break;

			case (2):
				o += 1;
				break;
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			switch (state.at(GameState.BOARD_SIZE - i, i, i)) {
			case (1):
				x += 1;
				break;

			case (2):
				o += 1;
				break;
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}

		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			switch (state.at(i, GameState.BOARD_SIZE - i, i)) {
			case (1):
				x += 1;
				break;

			case (2):
				o += 1;
				break;
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}

		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			switch (state.at(i, i, GameState.BOARD_SIZE - i)) {
			case (1):
				x += 1;
				break;

			case (2):
				o += 1;
				break;
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}
		return score;
	}
		
	/**
	 * Weight for scores
	 * @param o number of os
	 * @param x number of xs
	 * @return
	 */
	private static int calculateScore(int o, int x){
		int score = 0;
		if(x == 3 && o == 0){
			score += 1000;
		} else if(x == 2 && o == 0){
			score += 100;
		} else if(x == 1 && o == 0){
			score += 10;
		}else if(o == 3 && x == 0){
			score -= 1000;
		} else if(o == 2 && x == 0){
			score -= 100;
		} else if(o == 1 && x == 0){
			score -= 10;
		}
		return score;
	}
}
