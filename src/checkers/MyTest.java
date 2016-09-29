package checkers;

public class MyTest {
	
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

	public static void main(String[] args) {
		//GameState state8 = new GameState("44444333322442133124422333344444");
		GameState state = new GameState("rrrrrrrrrrrr........wwwwwwwwwwww -1 r 50");
		//GameState state2 = new GameState("rrrrrrrr..rr......rrwwwwwwwrrrrr -1 r 50");
		//GameState state3 = new GameState("wwwww......ww.r..r.ww......wwwww -1 r 50");
		//System.out.println(Heuristics.evaluate(state) + " expected 0");
		//System.out.println(Heuristics.evaluate(state2) + " expected -30");
		//System.out.println(Heuristics.evaluate(state3) + " expeted 36");
		Player p = new Player();
		Deadline deadline = new Deadline(Deadline.getCpuTime() + (long) 1e9);
		System.out.println(p.play(state, deadline).toMessage());
	}
}
