package racing.logic;

import racing.physics.Collision;

/**
 * Represents a Wall object ( thin line, unbreakable ).
 *
 */
public class Wall extends Line {

	public Collision m_Collision;

	public Wall(){
		super();
		this.radius = 7;
	}
	
	public Wall(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
		this.radius = 5;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param coordY
	 * @param coordX
	 */
	public Wall(int coordY, int coordX){

	}



	public Collision getM_Collision() {
		return m_Collision;
	}

	public void setM_Collision(Collision m_Collision) {
		this.m_Collision = m_Collision;
	}
	
	public String write() {
		String str = null;
		str = "1 " + getX1() + " " + getY1() + " " + getX2() + " " + getY2();
		return str;
	}

}