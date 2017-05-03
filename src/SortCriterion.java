
/**
 * This class creates sort criterion enumerations for source, topic, subject,
 * length, and date.
 * 
 * @author Clayton Glenn, Tristan Dow, Nick Fox
 *
 */
public enum SortCriterion {

	// Enum Constants
	SOURCE, TOPIC, SUBJECT, LENGTH, DATE_TIME;

	/**
	 * This method creates a string from the enums.
	 * 
	 * @return String
	 */
	public String toString() {

		switch (this) {
		case SOURCE:
			return "Source";
		case TOPIC:
			return "Topic";
		case SUBJECT:
			return "Subject";
		case LENGTH:
			return "Length";
		case DATE_TIME:
			return "Date/Time";
		default:
			throw new IllegalArgumentException();
		}
	}
}
