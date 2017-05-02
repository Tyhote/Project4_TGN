import java.time.LocalDate;

/**
 * This class creates a subclass of newsstory called tv news story. It is used
 * for the news database
 * 
 * @author Clayton Glenn, Tristan Dow, Nick Fox, and Dean Hougen
 *
 */
public class TVNewsStory extends NewsStory {

	// Class Variables
	private static final long serialVersionUID = 1L;
	private PartOfDay partOfDay;

	/**
	 * This constructor sets all the variables of the newsstory object
	 * 
	 * @param date
	 *            Date of the news story
	 * @param source
	 *            Source of the news story
	 * @param length
	 *            Length of the news story
	 * @param topic
	 *            topic of the news story
	 * @param subject
	 *            subject of the news story
	 * @param partOfDay
	 *            part of day of the news story
	 * @param newsMaker1
	 *            first news maker of the news story
	 * @param newsMaker2
	 *            second news maker of the news story
	 */
	public TVNewsStory(LocalDate date, String source, int length, String topic, String subject, PartOfDay partOfDay,
			NewsMakerModel newsMaker1, NewsMakerModel newsMaker2) {

		// Construct the abstract class
		super(date, source, length, topic, subject, newsMaker1, newsMaker2);

		// Set the partofday
		this.partOfDay = partOfDay;
	}

	/**
	 * Mutator method to get the length of words from the seconds of the news
	 * story.
	 * 
	 * @return Length
	 */
	@Override
	public int getLengthInWords() {

		// Return the length in words
		return this.getLength() * 150 / 60;
	}

	/**
	 * Mutator method to get the part of day from this object.
	 * 
	 * @return PartOfDay
	 */
	public PartOfDay getPartOfDay() {

		// Return the part of day of the object
		return partOfDay;
	}

	/**
	 * This method checks to see if two newsStories are equal
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof TVNewsStory) {
			if (super.equals(o)) {
				return this.partOfDay.equals(((TVNewsStory) o).partOfDay);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
