package racing.logic;

import java.io.Serializable;

/**
 * Represents a player, holding name and car.
 *
 */
public class Player implements Serializable{

	private String name;
	private int number;
	private long points;
	protected int lives;
	public Car m_Car;

	public Player(String name, int number, int points, Car m_Car) {
		super();
		this.name = name;
		this.number = number;
		this.points = points;
		this.m_Car = m_Car;
		this.lives = 500;
	}

	public Player(){

	}
	
	

	public void finalize() throws Throwable {

	}

	public Player(int number, String name, Car car){
		return;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long l) {
		this.points = l;
	}

	public Car getM_Car() {
		return m_Car;
	}

	public void setM_Car(Car m_Car) {
		this.m_Car = m_Car;
	}
	
	public void decLives() {
		if (lives >= 1)
			lives--;
	}
	
	public int getLives() {
		return lives;
	}


}