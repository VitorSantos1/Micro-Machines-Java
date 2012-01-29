package racing.logic;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * Car class, with default values. Sub-classes exist
 * that manipulate the core fields which have a huge impact
 * in the gameplay.
 *
 */
public class Car extends DynamicObject {

	/** Measured in degrees */
	protected int yaw;
	protected int yawIncrement;
	
	/** Current speed*/
	protected int throttle;
	protected int maxThrottle;
	protected double acceleration;
	
	
	/** Max hit points car can withstand. */
	
	protected int endurence;
	protected int hitPoints;
	protected boolean onAir;
	
	
	/** For further use in sprite manipulation */
	protected int carID;
	
	public Car(){
		super();
	}
	
	/**
	 * 
	 * @param coordY
	 * @param coordX
	 */
	public Car(int coordX, int coordY){

	}

	public Car(double d, int yawInc, int maxThrottle, int endurence) {
		super();
		
		this.acceleration = d;
		this.maxThrottle = maxThrottle;
		
		yawIncrement = yawInc;
		this.sizeX = 5;
		this.sizeY = 5;		
		
		this.pos.x = 100;
		this.pos.y = 160;

		this.yaw = 0;
		this.endurence = endurence;
		this.hitPoints = endurence;
		this.acceleration = 1;
		
	}

	public void decHitPoints(int dec) {
		hitPoints = hitPoints - dec;
	}

	public void decThrottle(int min, int inc) {
		if (throttle > min)
			this.throttle = throttle - inc;
	}

	/**
	 * Decreases throttle until reaches min.
	 * This is the result of the player not pressing any key.
	 * @param min
	 */
	public void decThrottleIdle(int min, int inc) {
		if (throttle > min)
			this.throttle = throttle - inc;
		if (throttle < 0)
			this.throttle++;
	}

	public void decYaw(int inc) {
		if (yaw < 0)
			yaw = 360 + yaw;
		yaw = yaw - yawIncrement - inc;
		}

	public void finalize() throws Throwable {
		super.finalize();
	}



	public boolean getDestroyed() {
		if (hitPoints <= 0)
			return true;
		return false;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public long getHitPoints() {
		return hitPoints;
	}
	
	public int getMaxThrottle() {
		return maxThrottle;
	}
	
	public boolean getOnAir() {
		return onAir;
	}
	
	public int getThrottle() {
		return throttle;
	}
	
	public int getYaw() {
		return yaw;
	}
	
	/**
	 * Increases throttle by inc.
	 * @param inc
	 */
	public void incThrottle(int inc) {
		if (throttle < maxThrottle)
			this.throttle = throttle + inc;	
	}

	public void incYaw(int inc) {
		if (yaw >= 360)
			yaw = 0;
		yaw = yaw + yawIncrement + inc;
	}
	

	/**
	 * Car was destroyed or out of bounds,
	 * reset its position to last checkpoint.
	 * @param lastCheckpoint
	 */
	public void resetCarPosition(Point lastCheckpoint) {
		throttle = 0;
		pos.x = lastCheckpoint.x;
		pos.y = lastCheckpoint.y;
	}

	public void resetHitPoints() {
		hitPoints = endurence;
	}

	public void setMaxThrottle(int maxThrottle) {
		this.maxThrottle = maxThrottle;
	}

	public void setOnAir(boolean onAir) {
		this.onAir = onAir;
	}

	public void setThrottle(int throttle) {
		this.throttle = throttle;
	}

	public void setYaw(int yaw) {
		this.yaw = yaw;
	}

	public void show() {
		System.out.println("Car x: " + this.pos.x + " y: " + this.pos.y);
	}

	/**
	 * Updates car position, having in account throttle, yaw and acceleration.
	 * @param timeElapsed
	 * @param revert
	 * @param extraInc
	 */
	public void updateCar(long timeElapsed, boolean revert, int extraInc) {
		
		if (throttle != 0) {
			
			double xIncrement = throttle * Math.sin(Math.toRadians(yaw)) * timeElapsed * 0.01 * acceleration + extraInc;
			double yIncrement = (-1)*(throttle * Math.cos(Math.toRadians(yaw)) * timeElapsed * 0.01) * acceleration + extraInc;
			
			double max = Math.max(xIncrement, yIncrement);
			
			if(!revert) {
				pos.x +=  xIncrement;
				pos.y +=  yIncrement;
			}
			else {
				pos.x -=  xIncrement;
				pos.y -=  yIncrement;
			}
		}
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
	public void setLine(double arg0, double arg1, double arg2, double arg3) {
	}

	public int getID() {
		return carID;
	}


}