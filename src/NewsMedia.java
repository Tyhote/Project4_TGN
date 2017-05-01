import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NewsMedia {

	NEWSPAPER, ONLINE, TV;
	// Turned this into a constant and populated it to save us headaches in the future. Possible stack overflow if someone accesses this program by automation? kek
	public static final List<NewsMedia> VALUES_LIST = new ArrayList<NewsMedia>(Arrays.asList(NEWSPAPER, ONLINE, TV));

	public String toString() {

		switch (this) {
		case NEWSPAPER:
			return "NewspaperStory";
		case ONLINE:
			return "OnlineNewsStory";
		case TV:
			return "TVNewsStory";
		default:
			throw new IllegalArgumentException();
		}
	}

	public List<NewsMedia> valuesAsList() {
		return VALUES_LIST;
	}
}
