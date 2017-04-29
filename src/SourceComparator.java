import java.util.Comparator;

public class SourceComparator implements Comparator<NewsStory> {

	public static final SourceComparator SOURCE_COMPARATOR = new SourceComparator();

	@Override
	public int compare(NewsStory newsStory1, NewsStory newsStory2) {
		return newsStory1.getSource().compareTo(newsStory2.getSource());
	}

}