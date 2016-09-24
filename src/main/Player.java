package main;

import java.util.*;

public class Player {
    /**
     * Performs a move
     *
     * @param gameState
     *            the current state of the board
     * @param deadline
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */
    public GameState play(final GameState gameState, final Deadline deadline) {
        Vector<GameState> nextStates = new Vector<GameState>();
        gameState.findPossibleMoves(nextStates);

        if (nextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(gameState, new Move());
        }
        
        int[] values = new int[nextStates.size()];
        for (int i = 0; i < nextStates.size(); i++){
        	values[i] = minimax(nextStates.elementAt(i), true, Integer.MIN_VALUE, Integer.MAX_VALUE, 4);
        }
        
        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        
        return nextStates.elementAt(getMaxIndex(values));
    }    
    
    	private int getMaxIndex(int[] values){
    		int bestValue = values[0];
    		int index = 0;
    		for (int i = 1; i< values.length; i++){
    			if (values[i] > bestValue){
    				bestValue = values[i];
    				index = i;
    			}
    		}
    		return index;
    	}
    	
    	
    
    	private int minimax (GameState state, boolean player, int alpha, int beta, int depth){
    		//recursion termination
    		
    		if(depth == 0 && !(state.isEOG())){
    			return eval(state);
    		}
    		if(state.isEOG()){
    			if(state.isXWin()){
    				return 1;
    			} else if(state.isOWin()){
    				return -1;
    			} else {
    				return 0;
    			}	
    		}
    		
    		//find beste possible next move
    		
    		else{
    			//our turn
    			if(player){
    				int bestPossible = Integer.MIN_VALUE;
    		        Vector<GameState> nextStates = new Vector<GameState>();
    		        state.findPossibleMoves(nextStates);
    				for (GameState child : nextStates){
    					bestPossible = Math.max(bestPossible, minimax(child, false, alpha, beta, depth - 1));
    					alpha = Math.max(alpha, bestPossible);
    					if(beta <= alpha){
    						break;
    					}
    				}
    				return bestPossible;
    				
    			//opponents turn	
    			} else {
    				int bestPossible = Integer.MAX_VALUE;
    		        Vector<GameState> nextStates = new Vector<GameState>();
    		        state.findPossibleMoves(nextStates);
    				for (GameState child : nextStates){
    					bestPossible = Math.min(bestPossible, minimax(child, true, alpha, beta, depth - 1));
    					beta = Math.min(beta, bestPossible);
    					if(beta<=alpha){
    						break;
    					}
    				}
    				return bestPossible;
    			}
    		}
    	}
    	
    	private int eval(GameState state) {
    		int x = 0; 
    		int o = 0; 
    		int n = 0; 
    		int score = 0;
    		
    		for(int i = 0; i < GameState.BOARD_SIZE; i++){
    			for(int j = 0; j < GameState.BOARD_SIZE; j++){
    				switch(state.at(i, j)){
					case(1): x += 1;
					break;
					
					case(2): o += 1;
					break;
					
					default: n += 1;
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
    			n = 0;
    		}
    		
    		for(int i = 0; i < GameState.BOARD_SIZE; i++){
    			for(int j = 0; j < GameState.BOARD_SIZE; j++){
    				switch(state.at(j, i)){
					case(1): x += 1;
					break;
					
					case(2): o += 1;
					break;
					
					default: n += 1;
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
    			n = 0;
    		}
    		
    		for(int i = 0; i < GameState.BOARD_SIZE; i++){
    				switch(state.at(i, i)){
					case(1): x += 1;
					break;
					
					case(2): o += 1;
					break;
					
					default: n += 1;
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
    			n = 0;
    			
    			for(int i = 0; i < GameState.BOARD_SIZE; i++){
    				switch(state.at(GameState.BOARD_SIZE - i,GameState.BOARD_SIZE - i)){
					case(1): x += 1;
					break;
					
					case(2): o += 1;
					break;
					
					default: n += 1;
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
    			
    				if(score > 0) {
    					return 1;
    				}
    				if(score < 0) {
    					return -1;
    				}
    				else {
    					return 0;
    				}
    			
        	}
    }