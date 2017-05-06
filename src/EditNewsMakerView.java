import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
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

		// Making JList, adding it to JScrollPane, then adding that to a newly
		// initialized JPanel
		jlNewsStoryList = new JList<String>(newsMakerModel.getNewsStoryListModel().getStoriesForJList());
		jspNewsStoryList = new JScrollPane(jlNewsStoryList);
		jpNewsStoryList = new JPanel();
		jpNewsStoryList.add(jspNewsStoryList);

		// Making label and field for the News Maker name and adding to panel
		jlbName = new JLabel("Name: ");
		jtfName = new JTextField(newsMakerModel.getName());
		jtfName.addActionListener(this);
		jplName = new JPanel(new BorderLayout());
		jplName.add(jlbName, BorderLayout.WEST);
		jplName.add(jtfName, BorderLayout.CENTER);
		newsDataBaseModel.sortNewsMakerListModel();

		// Create remove from story button
		jbtRemoveFromStory = new JButton("Remove from Story");
		jbtRemoveFromStory.addActionListener(this);
		jbtRemoveFromStory.setEnabled(false);
		enableRemovalButton();
		
		jtfName.setActionCommand("Edit Name");
		jbtRemoveFromStory.setActionCommand("Remove Story");
		this.newsDataBaseModel.addActionListener(this);
		this.newsMakerModel.addActionListener(this);
		
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
		// Enables the removal button if there is at least one news story in the
		// list and the news maker is not "None".
		if (!(this.jlNewsStoryList.getModel().getSize() == 0) && !jtfName.equals("None")) {
			jbtRemoveFromStory.setEnabled(true);
		} else {
			jbtRemoveFromStory.setEnabled(false);
		}
		return;
	}

	public void setModel(NewsMakerModel maker) {
		jtfName = new JTextField(newsMakerModel.getName());
		jlNewsStoryList = new JList<>(newsMakerModel.getNewsStoryListModel().getStoriesForJList());
	}

	public void registerEditNewsMakerNameListener(ActionListener EditNewsMakerNameListener) {
		jtfName.addActionListener(EditNewsMakerNameListener);
	}
	
	public void registerRemoveNewsMakerFromNewStoriesListener(ActionListener RemoveNewsMakerFromNewStoriesListener) {
		jbtRemoveFromStory.addActionListener(RemoveNewsMakerFromNewStoriesListener);
	}

	public void actionPerformed(ActionEvent e) {
		jtfName = new JTextField(newsMakerModel.getName());
		jlNewsStoryList = new JList<String>(newsMakerModel.getNewsStoryListModel().getStoriesForJList());
		enableRemovalButton();
	}
}
