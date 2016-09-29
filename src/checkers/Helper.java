//package checkers;
import java.util.Vector;

public class Helper {
	
	/**
	 * Returns index of max value in int array
	 * @param values int values
	 * @return max index
	 */
	public static int getMaxIndex(int [] values){
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
	
	/**
	 * Debug function for printing heuristics
	 * @param states
	 */
	public static void printHeuristics(Vector<GameState> states){
		for(GameState current: states){
			System.out.println(Heuristics.evaluate(current));
		}
		System.out.println();
		System.out.println();
	}
	
	public static int getDepth(GameState state){
		int pieces = 0;
		for(int i=0; i<GameState.NUMBER_OF_SQUARES; i++){
			int current = state.get(i);
			if(current == Constants.CELL_RED || current == Constants.CELL_WHITE){
				pieces += 1;
			}
		}
		if(pieces > 22){
			return 3;
		} else if(pieces > 18){
			return 5;
		}else if(pieces > 12){
			return 7;
		}else if (pieces > 6){
			return 8;
		}
		return 9;
	}
	
}