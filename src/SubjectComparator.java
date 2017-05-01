import java.util.Comparator;

/**
 * This class is a comparator that compares two news stories subjects.
 * 
 * @author Clayton Glenn, Tristan Dow, Nick Fox, and Dean Hougen
 *
 */
public class SubjectComparator implements Comparator<NewsStory> {

	public static final SubjectComparator SUBJECT_COMPARATOR = new SubjectComparator();

	/**
	 * This method compares the subjects of 2 news stories
	 * 
	 * @return compareTo integer
	 */
	@Override
	public int compare(NewsStory newsStory1, NewsStory newsStory2) {
		return newsStory1.getSubject().compareTo(newsStory2.getSubject());
	}

}
