package racing.logic;

import java.util.Vector;

/**
 * @deprecated since the only player is human.
 */
public class HumanPlayer extends Player {

	/**
	 * Cars associated with the player, which are unlocked throught the game.
	 */
	private Vector<Car> cars;

	public HumanPlayer(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

}