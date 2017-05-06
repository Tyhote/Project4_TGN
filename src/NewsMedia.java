import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public enum NewsMedia {

	NEWSPAPER, ONLINE, TV;

	public static final List<NewsMedia> VALUES_LIST = new ArrayList<NewsMedia>(Arrays.asList(NEWSPAPER, ONLINE, TV));

	public String toString() {

		switch (this) {
			case NEWSPAPER: 	{return "Newspaper Story";}
			case ONLINE: 			{return "Online News Story";}
			case TV: 					{return "TV News Story";}
			default:					{throw new IllegalArgumentException();}
		}
	}

	public List<NewsMedia> valuesAsList() {
		return VALUES_LIST;
	}
}
