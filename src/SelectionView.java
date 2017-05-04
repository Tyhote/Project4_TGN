import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * This class creates a selection view UI for project 4 with many components
 * associated with java.
 * 
 * @author Clayton Glenn, Tristan Dow, Nick Fox
 *
 */
public class SelectionView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private NewsDataBaseModel newsDataBaseModel;

	// Menu bar for selection view
	private JMenuBar jmb = new JMenuBar();

	// Components for menu bar.
	private JMenu jmFile = new JMenu("File");
	private JMenuItem jmiLoad = new JMenuItem("Load");
	private JMenuItem jmiSave = new JMenuItem("Save");
	private JMenuItem jmiImport = new JMenuItem("Import");
	private JMenuItem jmiExport = new JMenuItem("Export");

	// All newsMaker components
	private JMenu jmNewsMaker = new JMenu("NewsMaker");
	private JMenuItem jmiAddNewsMaker = new JMenuItem("Add NewsMaker");
	private JMenuItem jmiEditNewsMaker = new JMenuItem("Edit NewsMaker");
	private JMenuItem jmiDeleteNewsMaker = new JMenuItem("Delete NewsMaker");
	private JMenuItem jmiDeleteNewsMakerList = new JMenuItem("Delete NewsMaker List");

	// All news story components
	private JMenu jmNewsStory = new JMenu("News Story");
	private JMenuItem jmiAddNewsStory = new JMenuItem("Add News Story");
	private JMenuItem jmiEditNewsStory = new JMenuItem("Edit News Story");
	private JMenuItem jmiSortNewsStories = new JMenuItem("Sort News Stories");
	private JMenuItem jmiDeleteNewsStory = new JMenuItem("Delete News Story");
	private JMenuItem jmiDeleteAllNewsStories = new JMenuItem("Delete All News Stories");

	// All display components
	private JMenu jmDisplay = new JMenu("Display");
	private JMenuItem jmiPieChart = new JMenuItem("Pie Chart");
	private JMenuItem jmiText = new JMenuItem("Text");

	// All components for news maker list
	private JList<NewsMakerModel> jlNewsMakerList;
	private JScrollPane jspNewsMakerList;
	private JPanel jpNewsMakerList = new JPanel(new BorderLayout());

	// All components for news story list
	private JList<NewsStory> jlNewsStoryList;
	private JScrollPane jspNewsStoryList;
	private JPanel jpNewsStoryList = new JPanel(new BorderLayout());

	// Split pane component for news story and news maker lists
	private JSplitPane splitPane = new JSplitPane();

	/**
	 * Constructor for selection view that sets a frame for the UI
	 */
	public SelectionView() {
		
		// Create new news database model
		newsDataBaseModel = new NewsDataBaseModel();

		// Set title of the frame
		setTitle("Nooz");

		// Add item components to file menu
		jmFile.add(jmiLoad);
		jmFile.add(jmiSave);
		jmFile.add(jmiImport);
		jmFile.add(jmiExport);

		// Add item components to newsMaker menu
		jmNewsMaker.add(jmiAddNewsMaker);
		jmNewsMaker.add(jmiEditNewsMaker);
		jmNewsMaker.add(jmiDeleteNewsMaker);
		jmNewsMaker.add(jmiDeleteNewsMakerList);

		// Add items components to news story menu
		jmNewsStory.add(jmiAddNewsStory);
		jmNewsStory.add(jmiEditNewsStory);
		jmNewsStory.add(jmiSortNewsStories);
		jmNewsStory.add(jmiDeleteNewsStory);
		jmNewsStory.add(jmiDeleteAllNewsStories);

		// Add item components to display menu
		jmDisplay.add(jmiPieChart);
		jmDisplay.add(jmiText);

		// Add menus to menu bar
		jmb.add(jmFile);
		jmb.add(jmNewsMaker);
		jmb.add(jmNewsStory);
		jmb.add(jmDisplay);

		// Create new jlists and add Newsmakers to it
		jlNewsMakerList = new JList<NewsMakerModel>(newsDataBaseModel.getNewsMakers());
		jspNewsMakerList = new JScrollPane(jlNewsMakerList);
		jpNewsMakerList.add(new JLabel("Newsmakers               "), BorderLayout.NORTH);
		jpNewsMakerList.add(jspNewsMakerList, BorderLayout.CENTER);

		// Create new Jlist and add new stories to it
		jlNewsStoryList = new JList<NewsStory>(newsDataBaseModel.getNewsStories());
		jspNewsStoryList = new JScrollPane(jlNewsStoryList);
		jpNewsStoryList.add(new JLabel("News Stories"), BorderLayout.NORTH);
		jpNewsStoryList.add(jspNewsStoryList, BorderLayout.CENTER);

		// Add the lists to the split pane
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.add(jpNewsMakerList);
		splitPane.add(jpNewsStoryList);
		splitPane.setLeftComponent(jpNewsMakerList);
		splitPane.setRightComponent(jpNewsStoryList);

		// Add components to frame, pack, and set visible
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		
		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		
        add(jmb, BorderLayout.NORTH);
		
		// Add the lists to the split pane
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.add(jpNewsMakerList);
		splitPane.add(jpNewsStoryList);
		splitPane.setTopComponent(jpNewsMakerList);
		splitPane.setBottomComponent(jpNewsStoryList);
		
		add(splitPane, BorderLayout.CENTER);
		pack();
        
		setVisible(true);
	}

	/**
	 * This method registers the file menu with listeners
	 * 
	 * @param fileMenuListener
	 *            Listener for file menu
	 */
	public void registerFileMenuListener(ActionListener fileMenuListener) {

		jmiLoad.addActionListener(fileMenuListener);
		jmiSave.addActionListener(fileMenuListener);
		jmiImport.addActionListener(fileMenuListener);
		jmiExport.addActionListener(fileMenuListener);
	}

	/**
	 * This method registers the news maker menu with listeners
	 * 
	 * @param newsMakerMenuListener
	 *            Listener for news maker menu
	 */
	public void registerNewsMakerMenuListener(ActionListener newsMakerMenuListener) {

		jmiAddNewsMaker.addActionListener(newsMakerMenuListener);
		jmiEditNewsMaker.addActionListener(newsMakerMenuListener);
		jmiDeleteNewsMaker.addActionListener(newsMakerMenuListener);
		jmiDeleteNewsMakerList.addActionListener(newsMakerMenuListener);
	}

	/**
	 * This method registers the news story menu with listeners
	 * 
	 * @param newsStoryMenuListener
	 *            Listener for news story menu
	 */
	public void registerNewsStoryMenuListener(ActionListener newsStoryMenuListener) {

		jmiAddNewsStory.addActionListener(newsStoryMenuListener);
		jmiEditNewsStory.addActionListener(newsStoryMenuListener);
		jmiSortNewsStories.addActionListener(newsStoryMenuListener);
		jmiDeleteNewsStory.addActionListener(newsStoryMenuListener);
		jmiDeleteAllNewsStories.addActionListener(newsStoryMenuListener);
	}

	/**
	 * This method registers the display menu with listeners
	 * 
	 * @param displayMenuListener
	 *            Listener for display menu
	 */
	public void registerDisplayMenuListener(ActionListener displayMenuListener) {

		jmiPieChart.addActionListener(displayMenuListener);
		jmiText.addActionListener(displayMenuListener);
	}

	/**
	 * This method sets the news database
	 * 
	 * @param newsDataBaseModel
	 *            Data base passed from a higher method
	 */
	public void setNewsDataBaseModel(NewsDataBaseModel newsDataBaseModel) {

		this.newsDataBaseModel = newsDataBaseModel;
		actionPerformed(new ActionEvent(this, 0, "Set the NewsDataBaseModel"));
	}

	/**
	 * This method sets new j lists with an override
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		jlNewsMakerList = new JList<NewsMakerModel>(this.newsDataBaseModel.getNewsMakers());
		jlNewsStoryList = new JList<NewsStory>(this.newsDataBaseModel.getNewsStories());
	}

	/**
	 * This method gets all indices of chosen news makers
	 * 
	 * @return integer array
	 */
	public int[] getSelectedNewsMakers() {

		return jlNewsMakerList.getSelectedIndices();
	}

	/**
	 * This method gets all indices of chosen news stories
	 * 
	 * @return integer array
	 */
	public int[] getSelectedNewsStories() {

		return jlNewsStoryList.getSelectedIndices();
	}
}
