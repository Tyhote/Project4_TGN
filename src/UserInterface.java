import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

class UserInterface {

	public String convertToOutputFormat(NewsStory newsStory, List<NewsMedia> newsMedia) {

		String storyString = "";
		LocalDate date = newsStory.getDate();

		// If the type doesn't include TV, use words
		if (!newsMedia.contains(NewsMedia.TV)) {
			if (newsStory instanceof NewspaperStory) {
				storyString += date.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + date.getDayOfMonth()
						+ ", " + date.getYear() + "; " + newsStory.getSource() + "; " + newsStory.getLength()
						+ " words; " + newsStory.getTopic() + "; " + newsStory.getSubject();
			} else if (newsStory instanceof OnlineNewsStory) {
				storyString += date.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + date.getDayOfMonth()
						+ ", " + date.getYear() + "; " + newsStory.getSource() + "; " + newsStory.getLength()
						+ " words; " + newsStory.getTopic() + "; " + newsStory.getSubject() + "; "
						+ ((OnlineNewsStory) newsStory).getPartOfDay().toString();
			}
		}
		// If the type is TV news, use seconds (from length)
		else if (newsMedia.contains(NewsMedia.TV) && newsMedia.size() == 1) {
			storyString += date.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + date.getDayOfMonth() + ", "
					+ date.getYear() + "; " + newsStory.getSource() + "; " + newsStory.getLength() + " seconds; "
					+ newsStory.getTopic() + "; " + newsStory.getSubject() + "; "
					+ ((TVNewsStory) newsStory).getPartOfDay().toString();
		}
		// If the type is mixed, use words as common unit
		else {
			if (newsStory instanceof NewspaperStory) {
				storyString += date.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + date.getDayOfMonth()
						+ ", " + date.getYear() + "; " + newsStory.getSource() + "; " + newsStory.getLengthInWords()
						+ " word equivalents; " + newsStory.getTopic() + "; " + newsStory.getSubject();
			} else if (newsStory instanceof TVNewsStory) {
				storyString += date.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + date.getDayOfMonth()
						+ ", " + date.getYear() + "; " + newsStory.getSource() + "; " + newsStory.getLengthInWords()
						+ " word equivalents; " + newsStory.getTopic() + "; " + newsStory.getSubject() + "; "
						+ ((TVNewsStory) newsStory).getPartOfDay().toString();
			} else if (newsStory instanceof OnlineNewsStory) {
				storyString += date.getMonth().getDisplayName(TextStyle.FULL, Locale.US) + " " + date.getDayOfMonth()
						+ ", " + date.getYear() + "; " + newsStory.getSource() + "; " + newsStory.getLength()
						+ " word equivalents; " + newsStory.getTopic() + "; " + newsStory.getSubject() + "; "
						+ ((OnlineNewsStory) newsStory).getPartOfDay().toString();
			}
		}
		return storyString;
	}
}
