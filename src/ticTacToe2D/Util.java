package ticTacToe2D;

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
}