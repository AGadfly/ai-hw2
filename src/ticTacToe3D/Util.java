package ticTacToe3D;
import java.util.Vector;

public class Util {
	
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
}