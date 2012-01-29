package racing.logic;

/**
 * Solid is the default car, being the most balanced.
 *
 */
public class Solid extends Car {

	public Solid(){
		super(0.7,7, 70, 100);
		this.carID = 4;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Solid(boolean carSpeed, boolean hitPoints, int coordY, int coordX){
		return;
	}

}