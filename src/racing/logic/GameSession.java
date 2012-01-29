package racing.logic;

import java.io.Serializable;

/**
 * Holds data of a particular game session.
 * This object will be used by RacingLogic to process the game; it can also be
 * saved and loaded to the disk, to handle different sessions. This way
 * a) we don't need to "save" racinglogic
 * b) we can store multiple "saves" or sessions to be loaded by logic
 */
public class GameSession implements Serializable {

	private static final long serialVersionUID = 9082301846718371225L;

	private Configuration config;
	private Race race;

	public GameSession(Player m_Player, int keyLayout){
		config = new Configuration(keyLayout);
		
		race = new Race(m_Player, 5); // Default map to be loaded -> 5
	}
	
	public Race getRace() {
		return race;
	}
	
	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}
	
	public void finalize() throws Throwable {

	}

}