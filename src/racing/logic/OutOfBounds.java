package racing.logic;

/**
 * As the name implies, if the car touches this object then it is automatically destroyed:
 * simulation of pit fall.
 *
 */
public class OutOfBounds extends CollisionObject {

	public OutOfBounds(int i, int j) {
		super(i, j);
	}

	public OutOfBounds() {
	}

	public OutOfBounds(double x1, double y1, int width, int height) {
		super(x1, y1, width, height);
		this.solidness = true;
		this.drainPoints = 200;
		this.hitPoints = 1000;
		this.unbreakable = true;
	}
	
	public String write() {
		String str = null;
		str = "4 " + getCoordX() + " " + getCoordY() + " " + getSizeX() + " " + getSizeY();
		return str;
	}

}
