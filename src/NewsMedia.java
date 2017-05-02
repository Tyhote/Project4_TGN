import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NewsMedia {

	NEWSPAPER, ONLINE, TV;
	// Turned this into a constant and populated it to save us headaches in the future. Possible stack overflow if someone accesses this program by automation? kek
	public static final List<NewsMedia> VALUES_LIST = new ArrayList<NewsMedia>(Arrays.asList(NEWSPAPER, ONLINE, TV));

	public String toString() {

		switch (this) {
<<<<<<< HEAD
			case NEWSPAPER: 	return "Newspaper";
			case ONLINE: 			return "Online";
			case TV: 						return "TV";
			default:						throw new IllegalArgumentException();
=======
		case NEWSPAPER:
			return "NewspaperStory";
		case ONLINE:
			return "OnlineNewsStory";
		case TV:
			return "TVNewsStory";
		default:
			throw new IllegalArgumentException();
>>>>>>> branch 'master' of https://www.github.com/Tyhote/Project4_TGN
		}
	}

	public List<NewsMedia> valuesAsList() {
		return VALUES_LIST;
	}
}
