package racing.logic;

/**
 * Is the parent class of Lines <-> Dynamic Objects
 *
 */
public abstract class StaticObject extends GenericObject {

	private boolean isJump;
	private boolean isObstacle;
	private boolean isWall;
	/**
	 * Measured in %, 0..1.
	 * 0 is invisible, 1 is full .
	 */
	private int opacity;
	/**
	 * Object radius or field of action, measured from the center
	 */
	protected int radius;
	/**
	 * Measured in 0...1.
	 * It is used by physics / collisions to measure the effect it is going to have in
	 * the car.
	 * If object has a solidness of 1, car will obviously stop , whereas with 30% or
	 * other, will only reduce speed
	 */
	protected boolean solidness;

	public StaticObject(){
		super();
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param solidness
	 * @param radius
	 * @param opacity
	 * @param isWall
	 * @param isObstacle
	 * @param isJump
	 * @param coordY
	 * @param coordX
	 */
	public StaticObject(int solidness, int radius, int opacity, boolean isWall, boolean isObstacle, boolean isJump, int coordY, int coordX){

	}

	public StaticObject(int i, int j) {
		super(i,j);
	}
	
	public StaticObject(double x1, double y1, int width, int height) {
		super(x1,y1,width,height);
	}


	public boolean isJump() {
		return isJump;
	}

	public void setJump(boolean isJump) {
		this.isJump = isJump;
	}

	public boolean isObstacle() {
		return isObstacle;
	}

	public void setObstacle(boolean isObstacle) {
		this.isObstacle = isObstacle;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean getSolidness() {
		return solidness;
	}

	public void setSolidness(boolean solidness) {
		this.solidness = solidness;
	}

}