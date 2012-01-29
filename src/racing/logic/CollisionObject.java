package racing.logic;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * Class that represents a specific type of object that will
 * be colliding with the players car.
 */
public class CollisionObject extends StaticObject {

	/** How much points should drain from a car that collides */
	protected int drainPoints;
	/** "Life" of the object */
	protected int hitPoints;
	protected boolean unbreakable;

	public CollisionObject(int i, int j){
		super(i,j);
		drainPoints = 5;
		unbreakable = true;
		
		this.sizeX = 50;
		this.sizeY = 50;
		this.solidness = true;
		this.radius = 50;
	}

	public CollisionObject() {
		super();
	}

	public CollisionObject(double x1, double y1, int width, int height) {
		super(x1,y1,width,height);
		drainPoints = 5;
		unbreakable = true;
		this.solidness = true;
		this.radius = 20;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public int getDrainPoints() {
		return drainPoints;
	}

	public boolean isUnbreakable() {
		return unbreakable;
	}

	public void setUnbreakable(boolean unbreakable) {
		this.unbreakable = unbreakable;
	}

	public void setDrainPoints(int drainPoints) {
		this.drainPoints = drainPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}

	@Override
	public Point2D getP1() {
		return null;
	}

	@Override
	public Point2D getP2() {
		return null;
	}

	@Override
	public double getX1() {
		return 0;
	}

	@Override
	public double getX2() {
		return 0;
	}

	@Override
	public double getY1() {
		return 0;
	}

	@Override
	public double getY2() {
		return 0;
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
	}
	
	@Override
	public void show() {
		System.out.println("CollisionObject");
		System.out.println("X: " + pos.x);
		System.out.println("Y: " + pos.y);
	}

	public void decHitPoints(int i) {
		hitPoints = hitPoints - i;
	}
	
	public String write() {
		String str = null;
		str = "6 " + getCoordX() + " " + getCoordY() + " " + getSizeX() + " " + getSizeY();
		return str;
	}

}