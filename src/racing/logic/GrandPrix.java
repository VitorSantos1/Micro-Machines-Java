package racing.logic;

import java.io.Serializable;
import java.util.Vector;

/**
 * Set of races.
 * @deprecated not able to provide more maps and do a set of races
 */
public class GrandPrix implements Serializable {


	private Race gpRaces;
	
	public GrandPrix(Player m_Player){
	}

	public Race getGpRaces() {
		return gpRaces;
	}

	public void setGpRaces(Race gpRaces) {
		this.gpRaces = gpRaces;
	}

	public void finalize() throws Throwable {

	}

	public GrandPrix(Vector<Map> gpMaps){
		return;
	}

}