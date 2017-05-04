import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EditNewsMakerView extends JPanel implements ActionListener {

	private long serialVersionUID;
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
		this.newsDataBaseModel = newsDataBaseModel;
		// Making JList, adding it to JScrollPane, then adding that to a newly initialized JPanel
		jlNewsStoryList = new JList<String>(newsMakerModel.getNewsStoryListModel().getStoriesForJList());
		jspNewsStoryList = new JScrollPane(jlNewsStoryList);
		jpNewsStoryList = new JPanel();
		jpNewsStoryList.add(jspNewsStoryList);
		
		jlbName = new JLabel(newsMakerModel.getName());
		
		
	}

	public int[] getSelectedNewsStoryIndices() {
		return null;
	}


	public void actionPerformed(ActionEvent e) {

	}
}
