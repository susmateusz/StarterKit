
public interface BowlingGameCalc {
	/**
	 * 
	 * @param numberOfPins
	 */
	void roll(int numberOfPins);

	/**
	 * 
	 * @return
	 */
	int score();

	/**
	 * 
	 * @return
	 */
	boolean isFinished();
}
