package ticTacToe2D;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Player {
	private HashMap<GameState, Integer> stateCache;
	
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
        stateCache = new HashMap<>();
        for (int i = 0; i < nextStates.size(); i++){
        	values[i] = minimax(nextStates.elementAt(i), Integer.MIN_VALUE, Integer.MAX_VALUE,  6, deadline);
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
	private int minimax (GameState state, int alpha, int beta, int depth, Deadline dead){
		System.out.println(TimeUnit.NANOSECONDS.toMillis(dead.timeUntil()));
		// current player
		boolean isMax = state.getNextPlayer()==2 ? true : false;
		//recursion termination: recursion depth | end of game (leaf) 
		if(depth == 0 || state.isEOG()){ 
			return Heuristics.evaluate(state);
		}

		//find beste possible next best move
		Vector<GameState> nextStates = new Vector<GameState>();
		state.findPossibleMoves(nextStates);
		int bestPossible = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		Vector<GameState> orderedMoves = orderMoves(nextStates, isMax); // move ordering for earlier pruning
		for(GameState child : orderedMoves) {
			if(isMax){ //our turn (max)
				bestPossible = Math.max(bestPossible, minimax(child, alpha, beta, depth - 1, dead));
				alpha = Math.max(alpha, bestPossible);				
			} else { // opponents turn (min)
				bestPossible = Math.min(bestPossible, minimax(child, alpha, beta, depth - 1, dead));
				beta = Math.min(beta, bestPossible);
			}
			if(beta <= alpha){ // alpha beta pruning
				break;
			}
		}
		return bestPossible;
	}
	
	/**
	 * Sorts states on their heuristic value
	 * @param nextStates states to sort
	 * @param descending descending or ascending
	 * @return sorted states
	 */
	private Vector<GameState> orderMoves(Vector<GameState> nextStates,  boolean descending){
		Collections.sort(nextStates, new Comparator<GameState>() {
			@Override
			public int compare(GameState o1, GameState o2) {
				return Integer.compare(Heuristics.evaluate(o1), Heuristics.evaluate(o2));
			}
		});
		if(descending){
			Collections.reverse(nextStates);
		}
		return nextStates;
	}
}