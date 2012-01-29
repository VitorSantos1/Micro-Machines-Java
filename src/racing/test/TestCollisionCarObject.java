package racing.test;

import org.junit.Test;

import racing.logic.*;

public class TestCollisionCarObject {

	public TestCollisionCarObject(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Car collides with an object and changes some of its attributes: Speed,
	 * Acceleration, Hit points.
	 * e.g: speed boost, heal pack
	 */
	@Test
	public void collisionObject_ChangeAttribute(){
		Car testCar = new Car(300,300);
	}

	/**
	 * Car collides with an object and changes its steering.
	 */
	@Test
	public void collisionObject_ChangeDirection(){

	}

	/**
	 * Car collides with an object and gains / reduces speed.
	 */
	@Test
	public void collisionObject_ChangeSpeed(){

	}

	/**
	 * Car collides with an object that represents a pit / hole.
	 * Car is destroyed.
	 */
	@Test
	public void collisionObject_FallDestroy(){

	}

	/**
	 * Car collides with a wall and fully drains its hit points. Car wrecks
	 */
	@Test
	public void collisionWall_DestroyCar(){

	}

	/**
	 * Car collides with a wall and fully stops
	 */
	@Test
	public void collisionWall_FullStop(){

	}

	/**
	 * Car collides with a wall and reduces its hit points for about a half.
	 */
	@Test
	public void collisionWall_ReduceHitPoints(){

	}

	/**
	 * Car collides with a wall and reduces speed for half.
	 * If the car vector aims to the wall, car's speed progressively reduces; else car
	 * is not aiming towards wall anymore and regains speed.
	 */
	@Test
	public void collisionWall_ReduceSpeed(){

	}

}