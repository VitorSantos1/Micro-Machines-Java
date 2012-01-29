package racing.logic;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Super class that represents all objects in-game.
 *
 */
public class GenericObject extends Line2D {

	protected Point pos;
	protected int sizeX;
	protected int sizeY;
	protected boolean isStatic;

	public GenericObject(){
		pos = new Point(500, 700);
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	
	public void show() {
		System.out.println("GenericObject");
		System.out.println("X: " + pos.x);
		System.out.println("Y: " + pos.y);
	}

	public void finalize() throws Throwable {

	}
	
	public GenericObject(double i, double j) {
		pos = new Point(i, j);
	}

	public GenericObject(double x1, double y1, int width, int height) {
		pos = new Point(x1,y1);
		sizeX = width;
		sizeY = height;
	}

	public double getCoordX() {
		return pos.getX();
	}

	public void setCoordX(int coordX) {
		pos.setX(coordX);
	}

	public double getCoordY() {
		return pos.getY();
	}

	public void setCoordY(int coordY) {
		pos.setY(coordY);
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
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

	public String write() {
		return null;
	}


}