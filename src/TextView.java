import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import java.lang.reflect.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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

	public TextView(NewsMakerModel newsMakerModel, List<NewsMedia> newsMedia, List<SortCriterion> sortCriteria) {
		this.newsMakerModel = newsMakerModel;
		this.newsMedia = newsMedia;
		this.sortCriteria = sortCriteria;
		
		constructTitle();
		jfText = new JFrame(summaryLine);
		jfText.setSize(1000, 500);
		constructNewsStoriesAndSummary();
		jtaNewsStoryList = new JTextArea(listOfStories);
		jfText.add(jtaSummaryLine);
		jfText.add(jtaNewsStoryList);
		
	}

	private void constructNewsStoriesAndSummary() {
		
		//Create summaryLine
		//for tv
		//for newspaper
		//for online
		
		//Create list of stories
		listOfStories = "";
		Method m = null;
		try {
			m = UserInterface.class.getDeclaredMethod("convertToOutputFormat", NewsStory.class, String.class);
		} catch (NoSuchMethodException | SecurityException e) {

		}
		m.setAccessible(true);

		for (Object n : newsMakerModel.getNewsStoryListModel().getNewsStories().toArray()) {
			for (NewsMedia nm : newsMedia) {
				try {
					if(nm.toString().equals("TVNewsStory")){
						if(n instanceof TVNewsStory){
							listOfStories += m.invoke(new UserInterface(), (TVNewsStory) n, newsMedia.toString());
						}
					}
					else if(nm.toString().equals("OnlineNewsStory")){
						if(n instanceof OnlineNewsStory){
							listOfStories += m.invoke(new UserInterface(), (OnlineNewsStory) n, newsMedia.toString());
						}
					}
					else if(nm.toString().equals("NewspaperStory")){
						if(n instanceof NewspaperStory){
							listOfStories += m.invoke(new UserInterface(), (NewspaperStory) n, newsMedia.toString());
						}
					}
					else{
						throw new IllegalArgumentException();
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println("Reflection didnt work!!!!!");
				}
			}
		}
	}

	private void constructTitle() {
		summaryLine = "TextDisplay";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Text Display")) {
			jspNewsStoryList = new JScrollPane();
			jfText.add(jspNewsStoryList);
			jfText.pack();
			jfText.setVisible(true);
		}
	}
}
