package racing.physics;

import racing.logic.Car;

/**
 * @deprecated since the only calcs needed are simply done once in Car->update
 */
public class Physics {

	public Collision m_Collision;

	public Physics(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param indFormula
	 */
	public Physics(int indFormula){
		
	}

	/**
	 * 
	 * @param car
	 */
	public void calculateOrientation(Car car){

	}

	/**
	 * Computes next position based on car speed
	 * 
	 * @param car
	 */
	public void calculatePosition(Car car){

	}

	/**
	 * 
	 * @param car
	 */
	public void calculateSpeed(Car car){

	}

}