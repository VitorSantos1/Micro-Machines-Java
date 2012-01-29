package racing.logic;

public class BonusObject extends StaticObject {

	private int bonusPoints;
	
	public BonusObject() {
		super();
		this.bonusPoints = 50;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
	}
}
