
public class BowlingCalculator implements BowlingGameCalc {
	private int score = 0;
	private int bonus = 0;
	private int lastRoll = 0;
	private boolean isFirstRoll = true;
	private int countRounds = 0;

	private void wasStrike(int numberOfPins) {
		score += numberOfPins;
		--bonus;
	}
	
	@Override
	public void roll(int numberOfPins) {
		score += numberOfPins;
		
		if (bonus > 0) {
			wasStrike(numberOfPins);
		}
		if (bonus == 2) {
			wasStrike(numberOfPins);
		}
		if (numberOfPins == 10 && isFirstRoll) {
			bonus += 2;
			isFirstRoll = !isFirstRoll;
		}
		else if (lastRoll + numberOfPins == 10) {
			bonus = 1;
		}
		
		isFirstRoll = !isFirstRoll;
		
		if(isFirstRoll)
			++countRounds;

		lastRoll = numberOfPins;
	}

	@Override
	public int score() {
		return score;
	}

	@Override
	public boolean isFinished() {
		return countRounds == 10;
	}

}
