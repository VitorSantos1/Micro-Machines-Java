package racing.logic;

/**
 * Represents a specific "obstacle" that has the ability to move
 * (is non-stationary).
 * @deprecated since the only "moving" object is the car.
 *
 */
public abstract class DynamicObject extends GenericObject {

	public void finalize() throws Throwable {
		super.finalize();
	}

	public DynamicObject(){
		
	}


}