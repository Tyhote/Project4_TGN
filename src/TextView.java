import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

/**
 * This class creates a text view, summary, title, and list of stories from a
 * model, media choices and sort criteria.
 * 
 * @author Clayton Glenn, Tristan Dow, and Nick Fox
 *
 */
public class TextView implements ActionListener {

	private NewsMakerModel newsMakerModel;
	private List<NewsMedia> newsMedia;
	private List<SortCriterion> sortCriteria;
	private String listOfStories;
	private String summaryLine;

	private JFrame jfText;
	private JTextArea jtaNewsStoryList;
	private JScrollPane jspNewsStoryList;
	private JTextArea jtaSummaryLine;

	/**
	 * Constructor that sets a text view of the output from a news maker's
	 * stories.
	 * 
	 * @param newsMakerModel
	 *            Model of a newsMaker used to create output
	 * @param newsMedia
	 *            User choices of news media for stories
	 * @param sortCriteria
	 *            User choices of sort criteria for sorting stories
	 */
	public TextView(NewsMakerModel newsMakerModel, List<NewsMedia> newsMedia, List<SortCriterion> sortCriteria) {

		this.newsMakerModel = newsMakerModel;
		this.newsMedia = newsMedia;
		this.sortCriteria = sortCriteria;

		jfText = new JFrame();
		jfText.setSize(500, 250);
		jfText.setLocation(600, 250);
		constructTitle();

		constructNewsStoriesAndSummary();
		jtaNewsStoryList = new JTextArea(this.listOfStories);
		jtaNewsStoryList.setEditable(false);
		jtaSummaryLine = new JTextArea(this.summaryLine);
		jtaSummaryLine.setEditable(false);
		jspNewsStoryList = new JScrollPane(jtaNewsStoryList);
		
		
		JPanel jpNewsStoryList = new JPanel();
		JPanel jpSummaryLine = new JPanel();
		jpNewsStoryList.add(jspNewsStoryList);
		
		
		jpSummaryLine.add(jtaSummaryLine);
		
		JPanel bigPanel = new JPanel(new BorderLayout());
		bigPanel.add(jspNewsStoryList, BorderLayout.CENTER);
		bigPanel.add(jtaSummaryLine, BorderLayout.SOUTH);
		
		jfText.add(bigPanel);
		
		jfText.pack();
		jfText.setVisible(true);
	}

	/**
	 * Helper method to create a list of news stories and a summary line
	 */
	private void constructNewsStoriesAndSummary() {

		// Create a list of news stories that will be printed and exclude non
		// essential news stories
		List<NewsStory> newsStories = new ArrayList<NewsStory>();
		for (Object o : newsMakerModel.getNewsStoryListModel().getNewsStories().toArray()) {
			NewsStory n = (NewsStory) o;
			if (newsMedia.contains(NewsMedia.TV)) {
				if (n instanceof TVNewsStory) {
					newsStories.add(n);
				}
			}
			if (newsMedia.contains(NewsMedia.ONLINE)) {
				if (n instanceof OnlineNewsStory) {
					newsStories.add(n);
				}
			}
			if (newsMedia.contains(NewsMedia.NEWSPAPER)) {
				if (n instanceof NewspaperStory) {
					newsStories.add(n);
				}
			}
			if (newsMedia.size() == 0) {
				throw new IllegalArgumentException();
			}
		}

		// Sort the newsStories
		for (SortCriterion sc : sortCriteria) {
			if (sc == SortCriterion.TOPIC) {
				Collections.sort(newsStories);
			} else if (sc == SortCriterion.SUBJECT) {
				Collections.sort(newsStories, SubjectComparator.SUBJECT_COMPARATOR);
			} else if (sc == SortCriterion.SOURCE) {
				Collections.sort(newsStories, SourceComparator.SOURCE_COMPARATOR);
			} else if (sc == SortCriterion.LENGTH) {
				Collections.sort(newsStories, LengthComparator.LENGTH_COMPARATOR);
			} else if (sc == SortCriterion.DATE_TIME) {
				Collections.sort(newsStories, DateComparator.DATE_COMPARATOR);
			} else {
				throw new IllegalArgumentException();
			}
		}

		// Create SUMMARY LINE
		// Step through each selected news story to find distinct sources,
		// topics, subjects, and total length
		List<String> distinctNewsSourceNames = new ArrayList<String>();
		int totalLength = 0;
		List<String> distinctTopics = new ArrayList<String>();
		List<String> distinctSubjects = new ArrayList<String>();
		listOfStories = "";
		for (NewsStory story : newsStories) {

			// Find distinct sources
			if (!distinctNewsSourceNames.contains(story.getSource())) {
				distinctNewsSourceNames.add(story.getSource());
			}

			// Find distinct topics
			if (!distinctTopics.contains(story.getTopic())) {
				distinctTopics.add(story.getTopic());
			}

			// Find distinct subjects
			if (!distinctSubjects.contains(story.getSubject())) {
				distinctSubjects.add(story.getSubject());
			}

			// Find total length
			if (newsMedia.contains(NewsMedia.TV) && newsMedia.size() == 1) {
				totalLength += story.getLength();
			} else {
				totalLength += story.getLengthInWords();
			}

			// Create list of stories
			listOfStories += UserInterface.convertToOutputFormat(story, newsMedia) + "\n";
		}

		summaryLine = "";

		if (!newsMedia.contains(NewsMedia.TV)) {
			summaryLine += "Number of Stories: " + newsStories.size() + "; Number of Sources: "
					+ distinctNewsSourceNames.size() + "; Number of Words: " + totalLength + "; Number of Topics: "
					+ distinctTopics.size() + "; Number of Subjects: " + distinctSubjects.size();
		}
		// If the type is TV news, use seconds (from length)
		else if (newsMedia.contains(NewsMedia.TV) && newsMedia.size() == 1) {
			summaryLine += "Number of Stories: " + newsStories.size() + "; Number of Sources: "
					+ distinctNewsSourceNames.size() + "; Seconds: " + totalLength + "; Number of Topics: "
					+ distinctTopics.size() + "; Number of Subjects: " + distinctSubjects.size();
		}
		// If the type is mixed, use words as common unit
		else {
			summaryLine += "Number of Stories: " + newsStories.size() + "; Number of Sources: "
					+ distinctNewsSourceNames.size() + "; Number of Word Equivalents: " + totalLength
					+ "; Number of Topics: " + distinctTopics.size() + "; Number of Subjects: "
					+ distinctSubjects.size();
		}

	}

	/**
	 * Helper method to construct a title to add to the text view.
	 */
	private void constructTitle() {

		// Add news maker name
		String title = newsMakerModel.getName() + " - ";

		// Add all newsMedia unless all three are chosen
		if (newsMedia.size() < 3) {
			for (int i = 0; i < newsMedia.size(); i++) {
				if (i == newsMedia.size() - 1) {
					if(newsMedia.get(i).equals(NewsMedia.TV)){
						title += "TV News ";
					}
					else if(newsMedia.get(i).equals(NewsMedia.ONLINE)){
						title += "Online ";
					}
					else{
						title += "Newspaper ";
					}
				} else {
					if(newsMedia.get(i).equals(NewsMedia.TV)){
						title += "TV News/";
					}
					else if(newsMedia.get(i).equals(NewsMedia.ONLINE)){
						title += "Online/";
					}
					else{
						title += "Newspaper/";
					}
				}
			}
		}
		title += " Stories sorted by ";

		// Add all sort criteria.
		for (int i = 0; i < sortCriteria.size(); i++) {
			if (i == sortCriteria.size() - 1) {
				title += sortCriteria.get(i).toString();
			} else {
				title += sortCriteria.get(i).toString() + ", ";
			}
		}

		// Set title
		jfText.setTitle(title);
	}

	/**
	 * This method acts on an action event and with code
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// Check to see if the action event is for text display
		if (e.getActionCommand().equals("Text Display")) {
			
			constructTitle();
			constructNewsStoriesAndSummary();
			
			// Create a new scroll pane for the text display and add
			jspNewsStoryList = new JScrollPane();
			jfText.add(jspNewsStoryList);

			// Pack the text display
			jfText.pack();
			
			// Set the frame to visible
			jfText.setVisible(true);
		}
	}
}
