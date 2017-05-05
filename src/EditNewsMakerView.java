import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EditNewsMakerView extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7654922804023547570L;
	NewsMakerModel newsMakerModel;
	private NewsDataBaseModel newsDataBaseModel;
	private JList<String> jlNewsStoryList;
	private JScrollPane jspNewsStoryList;
	private JPanel jpNewsStoryList;
	JTextField jtfName;
	private JLabel jlbName;
	JButton jbtRemoveFromStory;
	private JPanel jplName;

	public EditNewsMakerView(NewsMakerModel newsMakerModel, NewsDataBaseModel newsDataBaseModel) {
		this.newsMakerModel = newsMakerModel;
		this.newsMakerModel.addActionListener(this);
		this.newsDataBaseModel = newsDataBaseModel;
		
		// Making JList, adding it to JScrollPane, then adding that to a newly initialized JPanel
		jlNewsStoryList = new JList<String>(newsMakerModel.getNewsStoryListModel().getStoriesForJList());
		jspNewsStoryList = new JScrollPane(jlNewsStoryList);
		jpNewsStoryList = new JPanel();
		jpNewsStoryList.add(jspNewsStoryList);
		
		// Making label and field for the News Maker name and adding to panel
		jlbName = new JLabel("Name: ");
		jtfName = new JTextField(newsMakerModel.getName());
		jplName = new JPanel(new BorderLayout());
		jplName.add(jlbName, BorderLayout.WEST);
		jplName.add(jtfName, BorderLayout.CENTER);
		newsDataBaseModel.sortNewsMakerListModel();
		
		// Create remove from story button
		jbtRemoveFromStory = new JButton("Remove from Story");
		jbtRemoveFromStory.setEnabled(false);
		
		// Add panels to this
		setLayout(new BorderLayout());
		add(jplName, BorderLayout.NORTH);
		add(jpNewsStoryList, BorderLayout.CENTER);
		add(jbtRemoveFromStory, BorderLayout.SOUTH);
	}

	public int[] getSelectedNewsStoryIndices() {
		return jlNewsStoryList.getSelectedIndices();
	}
	
	private void enableRemovalButton() {
		// Enables the removal button if there is at least one news story in the list and the news maker is not "None".
		if (!(this.jlNewsStoryList.getModel().getSize() == 0) && !jtfName.equals("None"))
		{
			jbtRemoveFromStory.setEnabled(true);
		}
		return;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Modified News Story List")){
			jlNewsStoryList = new JList<>(newsMakerModel.getNewsStoryListModel().getStoriesForJList());
		}
		
		int[] selectedStories = getSelectedNewsStoryIndices();
		NewsStoryListModel stories = newsMakerModel.getNewsStoryListModel();
		for(int i : selectedStories){
			NewsStory story = stories.get(i);
			if(story.getNewsMaker1().equals(newsMakerModel)){
				story.setNewsMaker1(newsDataBaseModel.none);
			}
			if(story.getNewsMaker2().equals(newsMakerModel)){
				story.setNewsMaker2(newsDataBaseModel.none);
			}
			newsMakerModel.removeNewsStory(story);
		}
		
		jtfName = new JTextField(newsMakerModel.getName());
		
		enableRemovalButton();
	}
}
