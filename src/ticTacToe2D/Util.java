package ticTacToe2D;

import ticTacToe3D.GameState;

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
	 * Helper for adjustment of depth close to the game
	 * @param state
	 * @return
	 */
	private int checkWhichRound (GameState state){
		int status = 0;
		
		for (int i = 0; i < GameState.BOARD_SIZE; i++) {
			for (int j = 0; j < GameState.BOARD_SIZE; j++) {
				for (int k = 0; k < GameState.BOARD_SIZE; k++) {
					switch (state.at(i, j, k)) {
					case (1):
						status += 1;
						break;

					case (2):
						status += 1;
						break;

					default:
						break;
					}
				}
			}
		}
		if(status < 10){
			return 1;
		} else if(status < 20){
			return 2;
		} else if(status < 30){
			return 2;
		} else if(status < 40){
			return 4;
		} else if(status < 50){
			return 4;
		} else if (status < 60) {
			return 4;
		} else{
			return 5;
		}
	}
}