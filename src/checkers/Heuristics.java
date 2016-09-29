package checkers;
import java.util.Arrays;

/*
 *    col 0  1  2  3  4  5  6  7
 * row  -------------------------
 *  0  |     0     1     2     3 |  0
 *  1  |  4     5     6     7    |  1
 *  2  |     8     9    10    11 |  2
 *  3  | 12    13    14    15    |  3
 *  4  |    16    17    18    19 |  4
 *  5  | 20    21    22    23    |  5
 *  6  |    24    25    26    27 |  6
 *  7  | 28    29    30    31    |  7
 *      -------------------------
 *        0  1  2  3  4  5  6  7
 */
public class Heuristics {
	private final static int [] KING_POSITIONS = new int[]{0,1,2,3,4,12,20,11,19,27,28,29,30,31};
	private final static int [] POSITION_3 = new int[]{24,16,8,5,6,7,15,23};
	private final static int [] POSITION_2 = new int[]{25,21,13,9,14,18};
	private final static int [] POSITION_1 = new int[]{22,17,14};
	/**
	 * Simple heuristic function for 2DTicTacToe which counts the
	 * number of different symbols on the board and favors the player
	 * having more advantagous symbols.
	 * 
	 * @param state Current gameState
	 * @return value representing the evaluation of that state
	 */
	public static int evaluate(GameState state){
		int score = 0;
		
		// check end of game
		if(state.isEOG()){
			if(state.isRedWin()){
				return 1000;
			} else if(state.isWhiteWin()){
				return -1000;
			} else {
				return 0;
			}
		}
		score += countPieces(state);
		return score;
	}
	
	// just counts number of pieces
	private static int countPieces(GameState state){
		int score = 0;

		// evaluate number of pieces
		int red = 0;
		int white = 0;
		for(int i=0; i<GameState.NUMBER_OF_SQUARES; i++){
			int current = state.get(i);
			if(current == Constants.CELL_RED){
				red += 1;
				if(current == Constants.CELL_KING){
					red += 2;
				}
				// evaluate position
				red += checkPosition(i);
			}else if(current == Constants.CELL_WHITE){
				white += 1;
				if(current == Constants.CELL_KING){
					white += 2;
				}
				// evaluate positions
				white += checkPosition(i);
			}
		}
		score = red - white;
		return score;
	}
	
	private static int checkPosition(int piece){
		int score = 0;
		if(Arrays.asList(KING_POSITIONS).contains(piece)){
			score = 4;
		} else if(Arrays.asList(POSITION_3).contains(piece)){
			score = 3;
		} else if(Arrays.asList(POSITION_2).contains(piece)){
			score = 2;
		} else if(Arrays.asList(POSITION_1).contains(piece)){
			score = 1;
		}
		return score;
	}
}
