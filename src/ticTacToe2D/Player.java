package ticTacToe2D;
import java.util.*;

public class Player {
    /**
     * Performs a move
     *
     * @param gameState the current state of the board
     * @param deadline time before which we must have returned
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
        	values[i] = minimax(nextStates.elementAt(i), true, Integer.MIN_VALUE, Integer.MAX_VALUE, 3, deadline);
        }
        // return next best move -> move with max heuristic value
        return nextStates.elementAt(Util.getMaxIndex(values));
    }    
    
    /**
     * Mix / Max algorithm for optimal path over given gamestate. Evaluates all future gameStates and rates
     * them with a value, bigger meaning more favorable.
     * @param state current game state
     * @param player indicates whether to check min or max (max our turn) 
     * @param alpha current alpha value for alpha beta pruning
     * @param beta current beta value for alpha beta pruning
     * @param depth max recursion depth
     * @param dead time deadline for evaluation
     * @return evaluation for current state
     */
	private int minimax (GameState state, boolean player, int alpha, int beta, int depth, Deadline dead){
		//recursion termination
		if(depth == 0 && !(state.isEOG())){ // guard clause for depth
			return Heuristics.countSymbols(state);
		}
		if(state.isEOG()){ // guard clause for win
			if(state.isXWin()){
				return 10000;
			} else if(state.isOWin()){
				return -10000;
			} else {
				return 500;
			}	
		}
		//find beste possible next move
		Vector<GameState> nextStates = new Vector<GameState>();
		state.findPossibleMoves(nextStates);
		int bestPossible = player ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for(GameState child : nextStates) {
			if(player){ //our turn (max)
				bestPossible = Math.max(bestPossible, minimax(child, false, alpha, beta, depth - 1, dead));
				alpha = Math.max(alpha, bestPossible);				
			} else { // opponents turn (min)
				bestPossible = Math.min(bestPossible, minimax(child, true, alpha, beta, depth - 1, dead));
				beta = Math.min(beta, bestPossible);
			}
			if(beta <= alpha){
				break;
			}
		}
		return bestPossible;
	}
}