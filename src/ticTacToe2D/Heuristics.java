package ticTacToe2D;
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
				return 10000;
			} else if(state.isOWin()){
				return -10000;
			} else {
				return 0;
			}	
		}
		
		// evaluating rows
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			for(int j = 0; j < GameState.BOARD_SIZE; j++){
				switch(state.at(i, j)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}
		
		// evaluating columns
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			for(int j = 0; j < GameState.BOARD_SIZE; j++){
				switch(state.at(j, i)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
			}
			score += calculateScore(o, x);
			x = 0;
			o = 0;
		}
		
		// evaluating first diagonal
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			switch(state.at(i, i)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
		}
		score += calculateScore(o, x);
		x = 0;
		o = 0;
		
		// evaluating second diagonal
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			switch(state.at(i, GameState.BOARD_SIZE-i-1)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
		}
		score += calculateScore(o, x);
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
		if(x == 3){
			score += 1000;
		} else if(x == 2){
			score += 100;
		} else if(x == 1){
			score += 10;
		}else if(o == 3){
			score -= 1000;
		} else if(o == 2){
			score -= 100;
		} else if(o == 1){
			score -= 10;
		}
		return score;
	}
}
