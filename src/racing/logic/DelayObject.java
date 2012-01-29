package racing.logic;

public class DelayObject extends CollisionObject {

	public DelayObject(int i, int j) {
		super(i, j);
	}

	public DelayObject() {
	}

	public DelayObject(double x1, double y1, int width, int height) {
		super(x1, y1, width, height);
		this.drainPoints = 0;
		this.hitPoints = 10;
		this.unbreakable = true;
		this.solidness = false;
	}
	
	public String write() {
		String str = null;
		str = "2 " + getCoordX() + " " + getCoordY() + " " + getSizeX() + " " + getSizeY();
		return str;
	}

}
