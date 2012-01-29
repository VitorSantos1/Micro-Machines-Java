package racing.logic;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Serves as super to its superclasses.
 */
public class Line extends StaticObject {

	protected Line2D line;
	protected boolean isCrossed;
	protected int radius;

	public Line(){
		line = new Line2D.Double(200, 500, 800, 500);
		radius = 5;
	}
	
	public Line(double x1, double y1, double x2, double y2) {
		line = new Line2D.Double(x1, y1, x2, y2);
	}


	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param radiusY
	 * @param radiusX
	 * @param isCrossed
	 * @param coordY
	 * @param coordX
	 */
	public Line(int radiusY, int radiusX, boolean isCrossed, int coordY, int coordX){
		return;
	}



	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}

	@Override
	public double getX1() {
		return line.getX1();
	}

	@Override
	public double getX2() {
		return line.getX2();
	}

	@Override
	public double getY1() {
		return line.getY1();
	}

	@Override
	public double getY2() {
		return line.getY2();
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
		line = new Line2D.Double(x1, y1, x2, y2);
	}


	@Override
	public Point2D getP1() {
		return null;
	}


	@Override
	public Point2D getP2() {
		return null;
	}
	
	
	public double getSlope() {
		return ((getY2() - getY1()) / (getX2() -getX1()));
	}
	
	public double getYaw() {
		return Math.toDegrees(Math.atan(getSlope()));
	}
	
	@Override
	public void show() {
		System.out.println("Line");
		System.out.println("X1: " + getX1() + " Y1: " + getY1() );
		System.out.println("X2: " + getX2() + " Y2: " + getY2() );
		System.out.println("Slope: " + getSlope());
		System.out.println("Yaw: "   + getYaw());
	}
	
	
	@Override
	public double getCoordX() {
		return getMiddle().x;
	}

	@Override
	public double getCoordY() {
		return getMiddle().y;
	}
	
	/**
	 * 
	 * @return the point between the two edges
	 */
	public Point getMiddle() {
		Point middle = new Point();
		middle.x = ((this.getX2() + this.getX1())/2);
		middle.y = ((this.getY2() + this.getY1())/2);
		return middle;
	}

}