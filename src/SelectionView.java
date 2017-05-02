import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class SelectionView extends JFrame implements ActionListener {

	private long serialVersionUID;
	private NewsDataBaseModel newsDataBaseModel;
	private JMenuBar jmb;
	private JMenu jmFile;
	private JMenuItem jmiLoad;
	private JMenuItem jmiSave;
	private JMenuItem jmiImport;
	private JMenuItem jmiExport;
	private JMenu jmNewsMaker;
	private JMenuItem jmiAddNewsMaker;
	private JMenuItem jmiEditNewsMaker;
	private JMenuItem jmiDeleteNewsMaker;
	private JMenuItem jmiDeleteNewsMakerList;
	private JMenu jmNewsStory;
	private JMenuItem jmiAddNewsStory;
	private JMenuItem jmiEditNewsStory;
	private JMenuItem jmiSortNewsStories;
	private JMenuItem jmiDeleteNewsStory;
	private JMenuItem jmiDeleteAllNewsStories;
	private JMenu jmDisplay;
	private JMenuItem jmiPieChart;
	private JMenuItem jmiText;
	private JList<NewsMakerModel> jlNewsMakerList;
	private JScrollPane jspNewsMakerList;
	private JPanel jpNewsMakerList;
	private JList<NewsStory> jlNewsStoryList;
	private JScrollPane jspNewsStoryList;
	private JPanel jpNewsStoryList;
	private JSplitPane splitPane;

	public SelectionView() {
	}

	public void registerFileMenuListener(ActionListener fileMenuListener) {
	}

	public void registerNewsMakerMenuListener(ActionListener newsMakerMenuListener) {
	}

	public void registerNewsStoryMenuListener(ActionListener newsStoryMenuListener) {
	}

	public void registerDisplayMenuListener(ActionListener displayMenuListener) {
	}

	public void setNewsDataBaseModel(NewsDataBaseModel newsDataBaseModel) {
	}

	public void actionPerformed(ActionEvent actionEvent) {
	}

	public int[] getSelectedNewsMakers() {
		return null;
	}

	public int[] getSelectedNewsStories() {
		return null;
	}

}
