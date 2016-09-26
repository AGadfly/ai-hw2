package ticTacToe2D;
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
	public static int countSymbols(GameState state){
   		int x = 0; 
		int o = 0; 
		int score = 0;
		
		// look ahead one step
	    Vector<GameState> nextStates = new Vector<GameState>();
	    Vector<GameState> secondStates = new Vector<GameState>();
	    state.findPossibleMoves(nextStates);
	    for(GameState child: nextStates){
			if(child.isEOG()){ // guard clause for win
				if(child.isXWin()){
					return 10000;
				} else if(child.isOWin()){
					return -10000;
				} else {
					return 500;
				}	
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
		
		// evaluating diagonal
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			switch(state.at(i, i)){
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
