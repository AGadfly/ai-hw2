package kth.ai16.hw2.main;

import java.util.*;

public class MyAgent {
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
        	values[i] = minimax(nextStates.elementAt(i), true);
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
    
    	private int minimax (GameState state, boolean player){
    		//recursion termination
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
    				int value = 0;
    		        Vector<GameState> nextStates = new Vector<GameState>();
    		        state.findPossibleMoves(nextStates);
    				for (GameState child : nextStates){
    					value = minimax(child, false);
    					if(value > bestPossible){
    						bestPossible = value;
    					}
    				}
    				return bestPossible;
    			//opponents turn	
    			} else {
    				int bestPossible = Integer.MAX_VALUE;
    				int value = 0;
    		        Vector<GameState> nextStates = new Vector<GameState>();
    		        state.findPossibleMoves(nextStates);
    				for (GameState child : nextStates){
    					value = minimax(child, true);
    					if(value < bestPossible){
    						bestPossible = value;
    					}
    				}
    				return bestPossible;
    			}
    		}
    	}
    }