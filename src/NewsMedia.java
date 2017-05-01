import java.util.ArrayList;
import java.util.List;

public enum NewsMedia {

	NEWSPAPER, ONLINE, TV;

	public List<NewsMedia> VALUES_LIST;

	public String toString() {
		
		switch (this) {
			case NEWSPAPER: 	return "Newspaper";
			case ONLINE: 			return "Online";
			case TV: 						return "TV";
			default:						throw new IllegalArgumentException();
		}
	}

	public List<NewsMedia> valuesAsList() {
		
		VALUES_LIST = new ArrayList<NewsMedia>();
		VALUES_LIST.add(NEWSPAPER);
		VALUES_LIST.add(ONLINE);
		VALUES_LIST.add(TV);
		
		return VALUES_LIST;
	}
}
