import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum NewsMedia {

	NEWSPAPER, ONLINE, TV;
	
	public static final List<NewsMedia> VALUES_LIST = new ArrayList<NewsMedia>(Arrays.asList(NEWSPAPER, ONLINE, TV));

	public String toString() {

		switch (this) {
			case NEWSPAPER: 	{return "Newspaper";}
			case ONLINE: 			{return "Online";}
			case TV: 					{return "TV";}
			default:					{throw new IllegalArgumentException();}
		}
	}

	public List<NewsMedia> valuesAsList() {
		return VALUES_LIST;
	}
}
