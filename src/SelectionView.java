import java.awt.GridLayout;
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

public class SelectionView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private NewsDataBaseModel newsDataBaseModel;
	
	private JMenuBar jmb = new JMenuBar();
	
	private JMenu jmFile = new JMenu("File");
	private JMenuItem jmiLoad = new JMenuItem("Load");
	private JMenuItem jmiSave = new JMenuItem("Save");
	private JMenuItem jmiImport = new JMenuItem("Import");
	private JMenuItem jmiExport = new JMenuItem("Export");
	
	private JMenu jmNewsMaker = new JMenu("NewsMaker");
	private JMenuItem jmiAddNewsMaker = new JMenuItem("Add NewsMaker");
	private JMenuItem jmiEditNewsMaker = new JMenuItem("Edit NewsMaker");
	private JMenuItem jmiDeleteNewsMaker = new JMenuItem("Delete NewsMaker");
	private JMenuItem jmiDeleteNewsMakerList = new JMenuItem("Delete NewsMaker List");
	
	private JMenu jmNewsStory = new JMenu("News Story");
	private JMenuItem jmiAddNewsStory = new JMenuItem("Add News Story");
	private JMenuItem jmiEditNewsStory = new JMenuItem("Edit News Story");
	private JMenuItem jmiSortNewsStories = new JMenuItem("Sort News Stories");
	private JMenuItem jmiDeleteNewsStory = new JMenuItem("Delete News Story");
	private JMenuItem jmiDeleteAllNewsStories = new JMenuItem("Delete All News Stories");
	
	private JMenu jmDisplay = new JMenu("Display");
	private JMenuItem jmiPieChart = new JMenuItem("Pie Chart");
	private JMenuItem jmiText = new JMenuItem("Text");
	
	private JList<NewsMakerModel> jlNewsMakerList;
	private JScrollPane jspNewsMakerList;
	private JPanel jpNewsMakerList;
	
	private JList<NewsStory> jlNewsStoryList;
	private JScrollPane jspNewsStoryList;
	private JPanel jpNewsStoryList = new JPanel();
	
	private JSplitPane splitPane = new JSplitPane();

	public SelectionView() {
		
		newsDataBaseModel = new NewsDataBaseModel();
		
		setTitle("Nooz");
		
		jmFile.add(jmiLoad);
		jmFile.add(jmiSave);
		jmFile.add(jmiImport);
		jmFile.add(jmiExport);
		
		jmNewsMaker.add(jmiAddNewsMaker);
		jmNewsMaker.add(jmiEditNewsMaker);
		jmNewsMaker.add(jmiDeleteNewsMaker);
		jmNewsMaker.add(jmiDeleteNewsMakerList);
		
		jmNewsStory.add(jmiAddNewsStory);
		jmNewsStory.add(jmiEditNewsStory);
		jmNewsStory.add(jmiSortNewsStories);
		jmNewsStory.add(jmiDeleteNewsStory);
		jmNewsStory.add(jmiDeleteAllNewsStories);
		
		jmDisplay.add(jmiPieChart);
		jmDisplay.add(jmiText);
		
		jmb.add(jmFile);
		jmb.add(jmNewsMaker);
		jmb.add(jmNewsStory);
		jmb.add(jmDisplay);
		
		jlNewsMakerList = new JList<NewsMakerModel>(newsDataBaseModel.getNewsMakers());
		jspNewsMakerList = new JScrollPane(jlNewsMakerList);
		jpNewsMakerList.add(jlNewsMakerList);
		jpNewsMakerList.add(jspNewsMakerList);
		
		jlNewsStoryList = new JList<NewsStory>(newsDataBaseModel.getNewsStories());
		jspNewsStoryList = new JScrollPane(jlNewsStoryList);
		jpNewsStoryList.add(jlNewsStoryList);
		jpNewsStoryList.add(jspNewsStoryList);
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.add(jpNewsMakerList);
		splitPane.add(jpNewsStoryList);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 1, 5, 5));
		add(jmb);
		add(splitPane);
		pack();
		setVisible(true);
	}

	public void registerFileMenuListener(ActionListener fileMenuListener) {
		
		jmiLoad.addActionListener(fileMenuListener);
		jmiSave.addActionListener(fileMenuListener);
		jmiImport.addActionListener(fileMenuListener);
		jmiExport.addActionListener(fileMenuListener);
	}

	public void registerNewsMakerMenuListener(ActionListener newsMakerMenuListener) {
	
		jmiAddNewsMaker.addActionListener(newsMakerMenuListener);
		jmiEditNewsMaker.addActionListener(newsMakerMenuListener);
		jmiDeleteNewsMaker.addActionListener(newsMakerMenuListener);
		jmiDeleteNewsMakerList.addActionListener(newsMakerMenuListener);
	}

	public void registerNewsStoryMenuListener(ActionListener newsStoryMenuListener) {
	
		jmiAddNewsStory.addActionListener(newsStoryMenuListener);
		jmiEditNewsStory.addActionListener(newsStoryMenuListener);
		jmiSortNewsStories.addActionListener(newsStoryMenuListener);
		jmiDeleteNewsStory.addActionListener(newsStoryMenuListener);
		jmiDeleteAllNewsStories.addActionListener(newsStoryMenuListener);
	}

	public void registerDisplayMenuListener(ActionListener displayMenuListener) {
	
		jmiPieChart.addActionListener(displayMenuListener);
		jmiText.addActionListener(displayMenuListener);
	}

	public void setNewsDataBaseModel(NewsDataBaseModel newsDataBaseModel) {
	
		this.newsDataBaseModel = newsDataBaseModel;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	
		jlNewsMakerList = new JList<NewsMakerModel>(newsDataBaseModel.getNewsMakers());
		jlNewsStoryList = new JList<NewsStory>(newsDataBaseModel.getNewsStories());
	}

	public int[] getSelectedNewsMakers() {
		
		return jlNewsMakerList.getSelectedIndices();
	}

	public int[] getSelectedNewsStories() {
		
		return jlNewsStoryList.getSelectedIndices();
	}

}
