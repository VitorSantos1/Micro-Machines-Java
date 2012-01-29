package racing.physics;

import java.util.Iterator;

import racing.logic.*;

/**
 * Handles all collisions between car and different objects.
 *
 */
public class Collision {

	public Collision(){

	}

	public void finalize() throws Throwable {

	}
	
	/**
	 * Parses the binary tree of objects in Map. 
	 * If there is any collision we call the appropriate method.
	 * The method immediately returns on any collision.
	 * @param carRef
	 * @param mapRef
	 * @param timeElapsed
	 * @return true if there was any collision
	 */
	public boolean collisionDetectionAll(Race race, Car carRef, Map mapRef, long timeElapsed) {

		if (checkPointHandle(race))
			return true;

		Iterator<GenericObject> treeIterator = mapRef.getTree().iterator();
		GenericObject go;

		while(treeIterator.hasNext()) 
		{
			go = treeIterator.next();

			// Always return (stops procedure) if there was a valid collision
			// since there won't be anymore this instance.
			if (go instanceof Wall) { 
				if(collisionHandle(carRef, (Wall)go))
					return true;
			}
			if (go instanceof JumpingPlatform)
				if(collisionHandle(carRef, (JumpingPlatform)go))
					return true;
			if (go instanceof CollisionObject) { // All other objects are of type CollisionObject
				if(collisionHandle(carRef, (CollisionObject)go))
					return true;
			}

		}

		carRef.setOnAir(false);
		return false;
	}
	
	/**
	 * If player is inside a checkpoint and has crossed the previous ones:
	 * @param race
	 * @return true and sets the checkpoint as crossed
	 */
	private boolean checkPointHandle(Race race) {
		int checkPointN = insideCheckPoint(race.getM_Player().getM_Car(), race.getRaceMap());

		if (checkPointN == -1)
			return false;

		CheckPoint check = race.getRaceMap().getCheckpoints().get(checkPointN);
		if (checkPointN == 0) {
			check.setCrossed(true);
		}
		else {
			if (race.getRaceMap().getCheckpoints().get(checkPointN - 1).getCrossed()) 
				// Check if previous checkpoint was crossed
				check.setCrossed(true);
		}

		return true;
	}
	
	/**
	 * Methods to deal with collisions, overloadad to all object types.
	 * @param car
	 * @param obs
	 * @return true if there was any collision.
	 */
	private boolean collisionHandle(Car car, CollisionObject obs) {
		if (insideObject(car, obs)) {
			if (car.getThrottle() >= 0)
				if (obs.getSolidness()) // Object is solid -> collision
					car.decThrottle(-10, car.getThrottle() + 10);
				else 
					car.decThrottle(-5,2);
			else						// Car is moving backwards
				if (obs.getSolidness()) 
					car.incThrottle(10);
				else 
					car.decThrottle(0, 5); 
			
			car.decHitPoints(obs.getDrainPoints());
			if (!obs.isUnbreakable())
				obs.decHitPoints(5);
			
			car.setOnAir(false);
			System.out.println("HitPoints: " + car.getHitPoints());
			return true;
		}
		
		return false;
	}

	/**
	 * Collision between Car and Wall
	 * @param car
	 * @param go
	 * @return
	 */
	private boolean collisionHandle(Car car, Wall go) {

		if (insideObject(car, go)) {
			if (car.getThrottle() >= 0)
					car.decThrottle(-10, car.getThrottle() + 10);
			else // Moving backwards
				car.incThrottle(10);
			
			car.setOnAir(false);
			car.decHitPoints(5);
			return true;
		}
		return false;
	}

	/**
	 * Collision between car and jumping platform.
	 * @param car
	 * @param jump
	 * @return
	 */
	private boolean collisionHandle(Car car, JumpingPlatform jump) {
		if(insideObject(car, jump)) {
			car.incThrottle(3);
			car.setOnAir(true);
			return true;
		}
		return false;
	}
	
	/**
	 * Series of methods to check boundaries.
	 * Deals with StaticObject and its childs.
	 * @param car
	 * @param obs
	 * @return true if is inside obstacle; calls appropriate handler
	 */
	public boolean insideObject(Car car, StaticObject obs) {
		if ((car.getCoordX() >= obs.getCoordX()) && (car.getCoordX() <=  (obs.getCoordX() + obs.getSizeX() + obs.getRadius())))
			if ((car.getCoordY() >= obs.getCoordY()) && (car.getCoordY() <=  (obs.getCoordY() + obs.getSizeY() + obs.getRadius())))
				return true;
		return false;

	}
	
	/**
	 * Deals with Line and it's childs.
	 * @param car
	 * @param line
	 * @return
	 */
	public boolean insideObject(Car car, Line line) {
		if (line.ptSegDist(car.getCoordX(), car.getCoordY()) < line.getRadius()) {
			return true;
			}
		
		return false;
	}
	
	/**
	 * Checks if is inside a checkpoint and returns its id.
	 * @param car
	 * @param check
	 * @return
	 */
	private int insideCheckPoint(Car car, Map raceMap) {

        int i = 0;
        for (CheckPoint check: raceMap.getCheckpoints()) {
                if (insideObject(car, check)) {
                        return i;
                }
                i++;
        }
                
        return -1;
}


}