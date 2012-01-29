package racing.logic;

import racing.physics.Collision;

public class Obstacle extends CollisionObject {

	public Collision m_Collision;
	
	public Obstacle() {
		super();
		
		this.sizeX = 50;
		this.sizeY = 50;
		this.solidness = true;
		this.radius = 50;
		
		this.pos.x = 0;
		this.pos.y = 0;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Obstacle(int coordX, int coordY){

	}


}