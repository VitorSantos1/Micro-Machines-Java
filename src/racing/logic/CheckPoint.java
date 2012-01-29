package racing.logic;

/**
 * 
 * Class that represents a checkpoint, which
 * is of type line.
 */
public class CheckPoint extends Line {

	boolean crossed;
	
	public CheckPoint(){
		super();
		this.setLine(100, 1000, 800, 1000);
		this.radius = 10;
		crossed = false;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}


	public CheckPoint(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
		crossed = false;
		this.radius = 10;
	}

	public void setCrossed(boolean b) {
		crossed = b;
	}

	public boolean getCrossed() {
		return crossed;
	}
	
	@Override
	public void show() {
		System.out.println("CheckPoint");
		System.out.println("Crossed?: " + crossed);
		System.out.println("X1: " + getX1() + " Y1: " + getY1() );
		System.out.println("X2: " + getX2() + " Y2: " + getY2() );
		System.out.println("Slope: " + getSlope());
		System.out.println("Yaw: "   + getYaw());
	}

	
	public String write() {
		String str = null;
		str = "0 " + getX1() + " " + getY1() + " " + getX2() + " " + getY2();
		return str;
	}

}