package racing.logic;

/**
 * Object that makes car's player jump and have a boost in speed.
 */
public class JumpingPlatform extends StaticObject {

	
	public JumpingPlatform(){
		super();
		this.pos.x = 100;
		this.pos.y = 400;
		this.radius = 200;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param coordY
	 * @param coordX
	 */
	public JumpingPlatform(int coordY, int coordX){

	}

	public JumpingPlatform(int x, int y, int width, int height) {
		super(x,y,width,height);
	}
	
	public JumpingPlatform(double x1, double y1, int x2, int y2) {
		super(x1,y1,x2,y2);
	}

	public String write() {
		String str = null;
		str = "3 " + getCoordX() + " " + getCoordY() + " " + getSizeX() + " " + getSizeY();
		return str;
	}

}