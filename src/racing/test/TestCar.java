package racing.test;

import static org.junit.Assert.*;
import static racing.logic.keyNotation.DECELERATE;

import javax.swing.Timer;

import org.junit.Before;
import org.junit.Test;

import racing.gui.RacingGUI;
import racing.logic.RacingLogic;
import racing.logic.keyNotation;


public class TestCar {
	
	private RacingLogic logic;
	public TestCar(){
		logic = new RacingLogic();
	}

	public void finalize() throws Throwable {

	}

	/**
	 * Car is destroyed (animation takes place) and is warped to the last good spot.
	 */
	@Test
	public void destroyWarp(){

	}

	@Test
	public void moveBackward()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		Timer testTimer = new Timer(0, null);
		testTimer.start();
		
		while(testTimer.getDelay() < 2000)
		{
			testGUI.getRacingPane().getKeysPressed()[keyNotation.DECELERATE] = 1;	
		}
		
		testTimer.stop();
		
		assertEquals(testGUI.m_RacingLogic.m_Car.getYaw(), initialYaw);
	}

	@Test
	public void moveBackwardAndBrake()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		double initialY = testGUI.m_RacingLogic.m_Car.getCoordY();
		
		Timer testTimer = new Timer(0, null);
		testTimer.start();
		
		while(testTimer.getDelay() < 2000)
		{
			testGUI.getRacingPane().getKeysPressed()[keyNotation.ACCELERATE] = 1;	
		}
		
		testGUI.getRacingPane().getKeysPressed()[keyNotation.ACCELERATE] = 0;
		testTimer.restart();
		
		while(testTimer.getDelay() < 1000)
		{
			testGUI.getRacingPane().getKeysPressed()[keyNotation.DECELERATE] = 1;
		}
		
		testTimer.stop();
		
		assertTrue(testGUI.m_RacingLogic.m_Car.getYaw() < initialY);
	}

	@Test
	public void moveForward()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		double initialY = testGUI.m_RacingLogic.m_Car.getCoordY();
		
		Timer testTimer = new Timer(0, null);
		testTimer.start();
		
		while(testTimer.getDelay() < 2000)
		{
			testGUI.getRacingPane().getKeysPressed()[keyNotation.ACCELERATE] = 1;	
		}
		
		testTimer.stop();
		
		assertTrue(testGUI.m_RacingLogic.m_Car.getYaw() < initialY);
	}

	@Test
	public void moveForwardAndJumping()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		double initialY = testGUI.m_RacingLogic.m_Car.getCoordY();
		
		Timer testTimer = new Timer(0, null);
		testTimer.start();
		
		while(testTimer.getDelay() < 2000)
		{
			testGUI.getRacingPane().getKeysPressed()[keyNotation.ACCELERATE] = 1;	
		}
		
		testTimer.stop();
		
		assertTrue(testGUI.m_RacingLogic.m_Car.getYaw() < initialY);
		
	}

	@Test
	public void orientationE()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		testGUI.m_RacingLogic.m_Car.incThrottle(270);
		initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(270, initialYaw);
	}

	/**
	 * Moves the car so that it makes 90� with x axis.
	 * Principle applies to the following _orientation% operations
	 */
	@Test
	public void orientationN()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(0, initialYaw);
	}

	@Test
	public void orientationNE()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		testGUI.m_RacingLogic.m_Car.incThrottle(315);
		initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(315, initialYaw);
	}

	@Test
	public void orientationNW()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		testGUI.m_RacingLogic.m_Car.incThrottle(45);
		initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(45, initialYaw);
	}

	@Test
	public void orientationS()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		testGUI.m_RacingLogic.m_Car.incThrottle(180);
		initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(180, initialYaw);
	}

	@Test
	public void orientationSE()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		testGUI.m_RacingLogic.m_Car.incThrottle(225);
		initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(225, initialYaw);
	}

	@Test
	public void orientationSW()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		testGUI.m_RacingLogic.m_Car.incThrottle(135);
		initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(135, initialYaw);
	}

	@Test
	public void orientationW()
	{
		RacingGUI testGUI = new RacingGUI(logic);
		int initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		testGUI.m_RacingLogic.m_Car.incThrottle(90);
		initialYaw = testGUI.m_RacingLogic.m_Car.getYaw();
		
		assertEquals(90, initialYaw);
	}

	/**
	 * Car goes out of bounds (leaves screen, jumps too high or falls) and is warped
	 * to the last good position.
	 */
	@Test
	public void outOfBoundsWarp(){

	}

	/**
	 * Reaches finish line, game finishes
	 */
	@Test
	public void reachFinishLine()
	{

	}

	/**
	 * Reaches full throttle.
	 */
	@Test
	public void reachFullSpeed()
	{

	}

	
	@Test
	public void reachFullStop()
	{

	}

}