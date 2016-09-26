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
	public static int countSymbols(GameState state){
   		int x = 0; 
		int o = 0; 
		int score = 0;
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			for(int j = 0; j < GameState.BOARD_SIZE; j++){
				switch(state.at(i, j)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
			}
			if(x == 3 && o == 0){
				score += 100;
			} else if(x == 2 && o == 0){
				score += 10;
			} else if(x == 1 && o == 0){
				score += 1;
			}else if(o == 3 && x == 0){
				score -= 100;
			} else if(o == 2 && x == 0){
				score -= 10;
			} else if(o == 1 && x == 0){
				score -= 1;
			}
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
			for(int j = 0; j < GameState.BOARD_SIZE; j++){
				switch(state.at(j, i)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
			}
			if(x == 3 && o == 0){
				score += 100;
			} else if(x == 2 && o == 0){
				score += 10;
			} else if(x == 1 && o == 0){
				score += 1;
			}else if(o == 3 && x == 0){
				score -= 100;
			} else if(o == 2 && x == 0){
				score -= 10;
			} else if(o == 1 && x == 0){
				score -= 1;
			}
			x = 0;
			o = 0;
		}
		
		for(int i = 0; i < GameState.BOARD_SIZE; i++){
				switch(state.at(i, i)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
			}
			if(x == 3 && o == 0){
				score += 100;
			} else if(x == 2 && o == 0){
				score += 10;
			} else if(x == 1 && o == 0){
				score += 1;
			}else if(o == 3 && x == 0){
				score -= 100;
			} else if(o == 2 && x == 0){
				score -= 10;
			} else if(o == 1 && x == 0){
				score -= 1;
			}
			x = 0;
			o = 0;
			
			for(int i = 0; i < GameState.BOARD_SIZE; i++){
				switch(state.at(GameState.BOARD_SIZE - i,GameState.BOARD_SIZE - i)){
				case(1): x += 1;
				break;
				
				case(2): o += 1;
				break;
				}
			}
			if(x == 3 && o == 0){
				score += 100;
			} else if(x == 2 && o == 0){
				score += 10;
			} else if(x == 1 && o == 0){
				score += 1;
			}else if(o == 3 && x == 0){
				score -= 100;
			} else if(o == 2 && x == 0){
				score -= 10;
			} else if(o == 1 && x == 0){
				score -= 1;
			}

			return score;
    	}
	}
