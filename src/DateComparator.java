import java.util.Comparator;


public class DateComparator implements Comparator<NewsStory> {
	
	public static final DateComparator DATE_COMPARATOR = new DateComparator();

	@Override
	public int compare(NewsStory newsStory1, NewsStory newsStory2) {
		// First, compare the dates themselves.
		int comparison = newsStory1.getDate().compareTo(newsStory2.getDate());

		// If the dates are the same, newspaper stories come before other types.
		if (comparison == 0) {
			if (newsStory1 instanceof NewspaperStory) {
				if (newsStory2 instanceof NewspaperStory) {
					return 0;
				} else {
					return -1;
				}
			} else if (newsStory2 instanceof NewspaperStory) {
				return 1;
			}

			// If neither is a newspaper story, look at part of day.
			PartOfDay partOfDay1;
			if (newsStory1 instanceof TVNewsStory) {
				partOfDay1 = ((TVNewsStory) newsStory1).getPartOfDay();
			} else {
				partOfDay1 = ((OnlineNewsStory) newsStory1).getPartOfDay();
			}

			PartOfDay partOfDay2;
			if (newsStory2 instanceof TVNewsStory) {
				partOfDay2 = ((TVNewsStory) newsStory2).getPartOfDay();
			} else {
				partOfDay2 = ((OnlineNewsStory) newsStory2).getPartOfDay();
			}

			comparison = partOfDay1.compareTo(partOfDay2);
		}
		return comparison;
	}
}