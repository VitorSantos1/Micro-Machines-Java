package racing.logic;

/**
 * The speeder lacks in handling but has tons of speed.
 *
 */
public class Speeder extends Car {

	public Speeder(){
		super(1, 4, 100, 70);
		this.carID = 2;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Speeder(boolean carSpeed, boolean hitPoints, int coordY, int coordX){
		return;
	}

}