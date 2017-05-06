
/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public enum PartOfDay {

	MORNING, AFTERNOON, EVENING, LATE_NIGHT;

	@Override
	public String toString() {

		switch (this) {
		case MORNING:
			return "Morning";
		case AFTERNOON:
			return "Afternoon";
		case EVENING:
			return "Evening";
		case LATE_NIGHT:
			return "Late Night";
		default:
			throw new IllegalArgumentException();

		}
	}
}
