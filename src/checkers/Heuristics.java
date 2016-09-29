//package checkers;
import java.util.Arrays;
import java.util.List;

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
	private final static List<Integer> KING_POSITIONS = Arrays.asList(new Integer[]{0,1,2,3,4,12,20,11,19,27,28,29,30,31});
	private final static List<Integer> POSITION_3 = Arrays.asList(new Integer[]{24,16,8,5,6,7,15,23});
	private final static List<Integer> POSITION_2 = Arrays.asList(new Integer[]{25,26,21,13,9,10,18});
	private final static List<Integer> POSITION_1 = Arrays.asList(new Integer[]{22,17,14});
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
				return -1000;
			} else if(state.isWhiteWin()){
				return 1000;
			} else {
				return 50;
			}
		}
		score += countPieces(state);
		score += checkLastMove(state);
		return score;
	}
	
	// just counts number of pieces
	public static int countPieces(GameState state){
		int score = 0;

		// evaluate number of pieces
		int red = 0;
		int white = 0;
		for(int i=0; i<GameState.NUMBER_OF_SQUARES; i++){
			int current = state.get(i);
			if(current == Constants.CELL_RED){
				red += 2;
				if(current == Constants.CELL_KING){
					red += 5;
				}
				//red += checkPosition(i);
			}else if(current == Constants.CELL_WHITE){
				white += 2;
				if(current == Constants.CELL_KING){
					white += 5;
				}
				//white += checkPosition(i);
			}
		}
		score = white - red;
		return score;
	}
	
	public static int checkPosition(int piece){
		int score = 0;
		
		if(KING_POSITIONS.contains(piece)){
			score = 4;
		} else if(POSITION_3.contains(piece)){
			score = 3;
		} else if(POSITION_2.contains(piece)){
			score = 2;
		} else if(POSITION_1.contains(piece)){
			score = 1;
		}
		return score;
	}
	
	public static int checkLastMove(GameState state){
		int score = 0;
		if(state.getMove().getType() == 1){
			score += 5;
		}else if(state.getMove().getType() == 2){
			score += 20;
		}else if(state.getMove().getType() == 3){
			score += 40;
		}else if(state.getMove().getType() == 4){
			score += 70;
		}else if(state.getMove().getType() == 5){
			score += 100;
		}else if(state.getMove().getType() > 5){
			score += 200;
		}
		return score;
	}
}


