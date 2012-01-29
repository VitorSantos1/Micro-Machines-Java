package racing.logic;

import static racing.logic.keyNotation.ACCELERATE;
import static racing.logic.keyNotation.DECELERATE;
import static racing.logic.keyNotation.LEFT;
import static racing.logic.keyNotation.RIGHT;

import racing.physics.Collision;

public class GameEngine {

	private Car carRef;
	private Map map;
	private Collision collisions;
	private long timeElapsed;
	private int counterIdle;
	private int counterAcc;
	private int counterDir;
	private int idleMax;
	private int idleInc;
	private int airCounter;
	
	public GameEngine () {
		
		carRef     = null;
		map   	   = null;
		collisions = new Collision();
		timeElapsed = 0;
		counterIdle = 0;
		counterAcc  = 0;
		counterDir  = 0;
		idleMax 	= 7; // Timer loops before decreasing speed for idle 
		idleInc 	= 1;
	}
	
	/**
	 * Core function of the game.
	 * Manages in-game aspects like updating car position and handling
	 * collisions.
	 * @param curTime
	 * @param keysPressed
	 */
	public void gameEngine(long curTime, int[] keysPressed,Race race) {

		carRef = race.getM_Player().getM_Car();
		map    = race.getRaceMap();
				
		if (race.getLapStart() == 0) { 
			// First time game engine is run
			race.setLapStart(curTime);
		}
		
		// Finish procedure
		if (race.getCurLap() == race.getMaxLaps()) {
			if (race.getFinishTime() == 0) {
				race.setFinish();
				race.endRace(true);
				carRef.setOnAir(true); // Animation
			}
		}
	
		if (carRef.getDestroyed()) {
			if (race.getM_Player().getLives() == 0) {
				race.endRace(false);
			}
			else {
				carRef.resetHitPoints();
				carRef.resetCarPosition(lastCheckpoint(map));
			}
		}
		
		
		if (race.getEndRace()) {
			idleMax = 1;
			idleInc = 4;
			
			if (airCounter >= 10) { // Animation purpose
				airCounter = 0;
				carRef.setOnAir(!carRef.getOnAir());
			}
			airCounter++;
		}
		
		// Steadly decreases throttle until reaches a minimum value.
		if (counterIdle >= idleMax) {
			counterIdle = 0;
			carRef.decThrottleIdle(0, idleInc);
		}
		
		// Only enables to "really" accelerate after counterAcc iterations
		if (counterAcc >= carRef.getAcceleration()) {
			counterAcc = 0;
			if (keysPressed[ACCELERATE]!=0)
				carRef.incThrottle(1);
			if (keysPressed[DECELERATE]!=0)
				carRef.decThrottle(-5,2);
		}
		
		if (counterDir >= 2) {
			counterDir = 0;
			if (race.getRaceCompleted()) { 
				carRef.decYaw(5); // Animation purpose
			}
			else {
			if (keysPressed[LEFT]!=0) {
					if(keysPressed[ACCELERATE]!=0)      // Player is accelerating, the car will turn less
						carRef.decYaw(2);
					else if(keysPressed[DECELERATE]!=0) 
						carRef.decYaw(7);			    // Player is decelerating, car will turn more
					else 
						carRef.decYaw(5);				// Default
					carRef.decThrottle((int)(carRef.getMaxThrottle()/(1.5)),1);
			}
			if (keysPressed[RIGHT]!=0) {
					if(keysPressed[ACCELERATE]!=0)
						carRef.incYaw(2);
					else if(keysPressed[DECELERATE]!=0)
						carRef.incYaw(7);
					else 
						carRef.incYaw(5);
					carRef.decThrottle((int)(carRef.getMaxThrottle()/(1.5)),1);
			}
			}
			
		}
		
		// Update car position
		carRef.updateCar(curTime - timeElapsed, false, 0);
		
		if (!race.getEndRace()) {
			// Handles all collisions
			collisions.collisionDetectionAll(race, carRef, map, curTime);
			
			if (race.getRaceMap().getCheckpoints().lastElement().getCrossed()) {
				System.out.println("Adding new lap");
				race.getRaceMap().resetCheckpoints();
				race.newLap(System.currentTimeMillis() - race.getLapStart());
			}
		}

		/** Counter use. This is an alternative to threads : since we're using a swing timer
		 *  that loops this method over and over.
		 */
	    timeElapsed = curTime;
	    counterIdle++;
	    counterAcc++;
	    counterDir++;
		
	}
	
    private Point lastCheckpoint(Map map) {
        int i = 0;
        for (CheckPoint check: map.getCheckpoints()) {
                if (check.getCrossed())
                        i++;
                else
                        break;
        }
        
        if (i == 0) // 0 Means all checkpoints were reseted, aka player crossed the last one.
        	return map.getCheckpoints().get(map.getnumCheckpoints() - 1).getMiddle();
        
        return map.getCheckpoints().get(i-1).getMiddle();
}

	private void resetGameEngine() {
		counterIdle = 0;
		counterAcc  = 0;
		counterDir  = 0;
		timeElapsed = 0;
	}
}
