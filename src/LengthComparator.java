import java.util.Comparator;

public class LengthComparator implements Comparator<NewsStory> {
	
	public static final LengthComparator LENGTH_COMPARATOR = new LengthComparator();

	@Override
	public int compare(NewsStory newsStory1, NewsStory newsStory2) {
		return newsStory1.getLengthInWords() - newsStory2.getLengthInWords();
	}

}
