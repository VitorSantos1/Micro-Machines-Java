package racing.logic;

import java.io.Serializable;

import racing.physics.Collision;

/**
 * Logic that implements the game. Calls in cascade the remaing classes .
 *
 */
public class RacingLogic implements Serializable{

	private GameSession game;
	public Collision m_Collision;
	public GameSession m_GameSession;
	public Player m_Player;
	public Car m_Car;
	
	public RacingLogic() {
		m_Car = new Speeder();
		m_Player = new Player("Player", 0, 0, m_Car);
		m_GameSession = new GameSession(m_Player, 2);
	}

	public void finalize() throws Throwable {

	}

	public void setGame(GameSession game) {
		this.game = game;
	}

	public GameSession getGame() {
		return m_GameSession;
	}

	public void pauseGame()
	{
		
	}

	public Player getPlayer() {
		return m_Player;
	}
}