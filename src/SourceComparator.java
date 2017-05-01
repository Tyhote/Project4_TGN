import java.util.Comparator;

/**
 * This class is a comparator that compares two news stories sources.
 * 
 * @author Clayton Glenn, Tristan Dow, Nick Fox, and Dean Hougen
 *
 */
public class SourceComparator implements Comparator<NewsStory> {

	public static final SourceComparator SOURCE_COMPARATOR = new SourceComparator();

	/**
	 * This method compares the sources of 2 news stories
	 * 
	 * @return compareTo integer
	 */
	@Override
	public int compare(NewsStory newsStory1, NewsStory newsStory2) {
		return newsStory1.getSource().compareTo(newsStory2.getSource());
	}

}