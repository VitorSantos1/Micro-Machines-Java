package racing.logic;

import java.util.Vector;

/**
 * Race holds a Map data and lap information.
 *
 */
public class Race {

	private Map raceMap;
	public Player m_Player;
	
	double totalPoints;
	long lapStart;
	long finishTime;	
	int curLap;
	int maxLaps;
	
	Vector<Long> lapTimes;
	private boolean endRace;
	/** User may have run out of lives without completing race */
	private boolean raceCompleted; 

	public Race(Player m_Player2, int laps) {
		
		raceMap = new Map(true, 5); // -> Default map
		m_Player = m_Player2;
		
		lapTimes = new Vector<Long>();
		
		maxLaps 	= laps;
		totalPoints = 1;
		lapStart 	= 0;
		finishTime  = 0;
		curLap 		= 0;
		endRace	      = false;
		raceCompleted = false;
		
		setCarPos(); 
	}

	public Map getRaceMap() {
		return raceMap;
	}

	public void setRaceMap(Map raceMap) {
		this.raceMap = raceMap;
		setCarPos();
	}
	
	public void setCarPos() {
		m_Player.getM_Car().setPos(this.raceMap.getCheckpoints().get(0).getMiddle());
	}

	public Player getM_Player() {
		return m_Player;
	}

	public void setM_Player(Player m_Player) {
		this.m_Player = m_Player;
	}

	public void finalize() throws Throwable {

	}
	
	/**
	 * Calculates points based on lives, hitpoints remaining and finishTime (the lesser, the better).
	 * @return
	 */
	public long calcPoints () {
		totalPoints = (100000/finishTime) ;
		
		totalPoints = totalPoints * m_Player.getM_Car().getHitPoints();
		totalPoints = totalPoints * m_Player.getLives();

		m_Player.setPoints(m_Player.getPoints() + (long)totalPoints);
		
		return (long)totalPoints;
	}
	
	public void addBonus (int bonus) {
		totalPoints = totalPoints + bonus;
	}

	public void endRace(boolean b) {
		this.endRace = true;
		if (b) {
			raceCompleted = true;
			calcPoints();
			show();
		}
	}

	private void show() {
		int i = 0;
		System.out.println("Finish Points: " + totalPoints);
		System.out.println("TotalTime: " + finishTime);
		for (Long times: lapTimes) {
			Conversions.lapTime(i, times);
			i++;
		}

	}

	public long getLapStart() {
		return lapStart;
	}

	public void newLap(long l) {
		lapTimes.add(l);
		curLap++;
	}

	public int getCurLap() {
		return curLap;
	}

	public int getMaxLaps() {
		return maxLaps;
	}

	public boolean getEndRace() {
		return endRace;
	}

	public void setFinish() {
		finishTime = lapTimes.lastElement();
	}

	public void setLapStart(long curTime) {
		lapStart = curTime;
	}

	public long getFinishTime() {
		return finishTime;
	}

	public boolean getRaceCompleted() {
		return raceCompleted;
	}

}