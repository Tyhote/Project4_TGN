
/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public enum NewsMetric {

	LENGTH, COUNT;

	public String toString() {

		switch (this) {
		case LENGTH: {
			return "Length";
		}
		case COUNT: {
			return "Count";
		}
		default: {
			throw new IllegalArgumentException();
		}
		}
	}
}
