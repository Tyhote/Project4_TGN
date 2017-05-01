
/**
 * This Class creates a wedge object with a percentage and text.
 * 
 * @author Clayton Glenn, Tristan Dow, Nick Fox, and Dean Hougen
 *
 */
public class Wedge {

	// Variables local to the wedge object
	private double percent;
	private String text;

	/**
	 * This method is a constructor that sets the initial percentage and text of
	 * the object.
	 * 
	 * @param percent
	 *            Percent of unit circle
	 * 
	 * @param text
	 *            Name of certain media
	 */
	public Wedge(double percent, String text) {

		// Construct the object
		this.setPercent(percent);
		this.setText(text);
	}

	/**
	 * This method returns the percent of the wedge to a higher class as an
	 * accessor.
	 * 
	 * @return percent
	 */
	public double getPercent() {

		// Return the percentage of the wedge
		return this.percent;
	}

	/**
	 * This method sets the percent of the wedge as a mutator.
	 * 
	 * @param percent
	 */
	public void setPercent(double percent) {

		// If percent is valid, set the percent
		if (percent >= 0 && percent <= 100) {
			this.percent = percent;
		} else {
			// If not, throw an exception
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This method is an accessor that returns text to a higher class.
	 * 
	 * @return text
	 */
	public String getText() {

		// Return text
		return this.text;
	}

	/**
	 * This method is a mutator that sets the text of the wedge
	 * 
	 * @param text
	 */
	public void setText(String text) {

		// Set text
		this.text = text;
	}
}
