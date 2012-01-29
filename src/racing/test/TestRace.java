package racing.test;

import org.junit.Test;

import racing.logic.Player;

public class TestRace {

	public TestRace(){

	}

	public void finalize() throws Throwable {

	}

	public boolean checkOnTrack(){
		return false;
	}

	/**
	 * Checks the winner, having in consideration the players sum of points / times.
	 * 
	 * @param player2
	 * @param player1
	 */
	@Test
	public void checkWinners(Player player2, Player player1){

	}

	/**
	 * Returns number in miliseconds of time player was off the track.
	 */
	@Test
	public int countOffTime(){
		return 0;
	}

	/**
	 * Player crosses a check point. This one is flagged as reached and next one is
	 * activated.
	 */
	@Test
	public void crossCheckPoint(){

	}

	/**
	 * Player crosses finish line, add to laps. Only counts if :
	 * - all the checkpoints of the track were crossed
	 * After crossing the line, resets checkpoints.
	 */
	public void crossLine(){

	}

	/**
	 * Player has X defined number of vehicles.
	 * Decreasing has the effect in game and (on screen?)
	 */
	public void decreasePlayerCars(){

	}

	/**
	 * Player has X defined number of vehicles.
	 */
	public void increasePlayerCars(){

	}

	/**
	 * Reach finish line if player has:
	 * X number of laps
	 * Other
	 */
	public void reachFinishLine(){

	}

	/**
	 * Sums player total points based on:
	 * -Bonuses
	 * -Remaining lives
	 * -Finish time (converts time in to points)
	 * ...
	 */
	public void sumPoints(){

	}

	/**
	 * Sum total time based on:
	 * -Finish time
	 * -Penalties / Bonuses
	 * -On track / off track times
	 */
	public void sumTimes(){

	}

}