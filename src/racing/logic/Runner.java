package racing.logic;

/**
 * Runner has tons of acceleration , medium speed and low hit points.
 */
public class Runner extends Car {

	public Runner(){
		super(5, 7, 50, 70);
		this.carID = 1;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Runner(boolean carSpeed, boolean hitPoints, int coordY, int coordX){
		return;
	}

}