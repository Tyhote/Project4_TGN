import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddEditNewsStoryView extends JPanel {
	public AddEditNewsStoryView() {
		
	}
	
	
	private static long serialVersionUID;
	private NewsDataBaseModel newsDataBaseModel; 
	private NewsStory newsStory; 
	
	private JLabel jlbNewsStoryType = new JLabel("Type: "); 
	JComboBox<NewsMedia> jcbNewsStoryType; 
	private JPanel jpNewsStoryType = new JPanel();
	
	private JLabel jlbNewsStorySource = new JLabel("Source: "); 
	JComboBox<String> jcbNewsStorySource; 
	private JPanel jpNewsStorySource = new JPanel();
	
	private JLabel jlbNewsStoryTopic = new JLabel("Topic: ");
	JComboBox<String> jcbNewsStoryTopic; 
	private JPanel jpNewsStoryTopic = new JPanel();
	
	private JLabel jlbNewsStorySubject = new JLabel("Subject: "); 
	JComboBox<String> jcbNewsStorySubject; 
	private JPanel jpNewsStorySubject = new JPanel();
	
	private JLabel jlbNewsStoryNewsMaker1 = new JLabel("News Maker 1: "); 
	JComboBox<String> jcbNewsStoryNewsMaker1; 
	private JPanel jpNewsStoryNewsMaker1 = new JPanel(); 
	
	private JLabel jlbNewsStoryNewsMaker2 = new JLabel("News Maker 2: ");  
	JComboBox<String> jcbNewsStoryNewsMaker2; 
	private JPanel jpNewsStoryNewsMaker2 = new JPanel();
	
	private JLabel jlbNewsStoryLength = new JLabel("Length: "); 
	private NumberFormat integerFieldFormatter = NumberFormat.getIntegerInstance(); 
	JFormattedTextField jftfNewsStoryLength = new JFormattedTextField(integerFieldFormatter);
	private JPanel jplNewsStoryLength = new JPanel(); 
	
	private JLabel jlbNewsStoryYear = new JLabel("Year: ");
	private Integer[] years; 
	JComboBox<Integer> jcbNewsStoryYear; 
	private JPanel jplNewsStoryYear = new JPanel(); 
	
	private JLabel jlbNewsStoryMonth = new JLabel("Month: "); 
	JComboBox<Month> jcbNewsStoryMonth = new JComboBox<Month>(Month.values()); 
	private JPanel jplNewsStoryMonth; 
	
	private JLabel jlbNewsStoryDay = new JLabel("Day: "); 
	private Integer[] days; 
	JComboBox<Integer> jcbNewsStoryDay; 
	private JPanel jplNewsStoryDay = new JPanel();
	
	private JLabel jlbNewsStoryPartOfDay = new JLabel("Part of Day: "); 
	JComboBox<PartOfDay> jcbNewsStoryPartOfDay = new JComboBox<PartOfDay>(PartOfDay.values()); 
	private JPanel jplNewsStoryPartOfDay; 
	
	private JPanel jplNewsStoryWhen = new JPanel();
	
	JButton jbtAddEditNewsStory = new JButton("Edit News Story"); 
	private JPanel jplAddEditNewsStory = new JPanel(); 
	
	public AddEditNewsStoryView(NewsDataBaseModel newsDataBaseModel , NewsStory newsStory) {
		
		//Assign the arguments to the proper fields
		this.newsDataBaseModel = newsDataBaseModel;
		this.newsStory = newsStory;
		
		//Initialize the JComboBoxes for the year and day fields with the proper arrays
		
		//Array of Integer values for years (2000-2017 inclusive). (see Dr. Hougen's javadoc)
		years = new Integer[18];
		for (int year = 2000; year <= 2017; ++year) //Fill the array
		{
			years[year - 2000] = year;
		}
		jcbNewsStoryYear = new JComboBox<Integer>(years);
		
		//Array of Integer values for day of the month (1-31 inclusive). (see Dr. Hougen's javadoc)
		days = new Integer[31];
		for (int day = 1; day <= 31; ++day) //Fill the array
		{
			days[day - 1] = day;
		}
		jcbNewsStoryDay = new JComboBox<Integer>(days);
		
		
		//Use the newsStory's values to initialize or set the proper fields
		
		//Initialize jcbNewsStoryType and set the selected option to the proper NewsMedia
		//Create an array to hold the NewsMedia to be used to make the JComboBox for holding NewsMedia options
		NewsMedia[] newsMediaArray = new NewsMedia[3];
		jcbNewsStoryType = new JComboBox<NewsMedia>(NewsMedia.VALUES_LIST.toArray(newsMediaArray));
		//Set the selected option to the proper NewsMedia
		if (this.newsStory instanceof NewspaperStory)
		{
			jcbNewsStoryType.setSelectedItem(NewsMedia.NEWSPAPER);
		}
		else if (this.newsStory instanceof TVNewsStory)
		{
			jcbNewsStoryType.setSelectedItem(NewsMedia.TV);
		}
		else //if (this.newsStory instanceof OnlineNewsStory)
		{
			jcbNewsStoryType.setSelectedItem(NewsMedia.ONLINE);
		}
		
		//TODO: Somehow do the same for jcbNewsStorySource, jcbNewsStoryTopic, and jcbNewsStorySubject,
		//		as well as for jcbNewsStoryNewsMaker1 and jcbNewsStoryNewsMaker2.
		//		It seems that the JComboBoxes for those will have to be initialized with master list arrays of all the different
		//		sources, topics, subjects, and newsmakers.
		
		//Set values and selected items for the rest of the newsStory's info
		jftfNewsStoryLength.setValue(newsStory.getLength());
		jcbNewsStoryYear.setSelectedItem(newsStory.getDate().getYear());
		jcbNewsStoryMonth.setSelectedItem(newsStory.getDate().getMonth());
		jcbNewsStoryDay.setSelectedItem(newsStory.getDate().getDayOfMonth());
		//The following code sets the selected item for the part of day JComboBox based on the type of media of the NewsStory
		if (newsStory instanceof NewspaperStory)
		{
			//Newspapers are published in the morning
			jcbNewsStoryPartOfDay.setSelectedItem(PartOfDay.MORNING.toString());
		}
		else
		{
			//Cast to TVNewsStory in order to use the getPartOfDay method, which isn't defined for NewsStory
			TVNewsStory newsStoryClone = (TVNewsStory)newsStory;
			jcbNewsStoryPartOfDay.setSelectedItem(newsStoryClone.getPartOfDay().toString());
		}
		
		
		//Add the components to their corresponding JPanels
		jpNewsStoryType.add(jlbNewsStoryType);
		jpNewsStoryType.add(jcbNewsStoryType);
		
		jpNewsStorySource.add(jlbNewsStorySource);
		jpNewsStorySource.add(jcbNewsStorySource);
		
		jpNewsStoryTopic.add(jlbNewsStoryTopic);
		jpNewsStoryTopic.add(jcbNewsStoryTopic);
		
		jpNewsStorySubject.add(jlbNewsStorySubject);
		jpNewsStorySubject.add(jcbNewsStorySubject);
		
		jpNewsStoryNewsMaker1.add(jlbNewsStoryNewsMaker1);
		jpNewsStoryNewsMaker1.add(jcbNewsStoryNewsMaker1);
		
		jpNewsStoryNewsMaker2.add(jlbNewsStoryNewsMaker2);
		jpNewsStoryNewsMaker2.add(jcbNewsStoryNewsMaker2);
		
		jplNewsStoryLength.add(jlbNewsStoryLength);
		jplNewsStoryLength.add(jftfNewsStoryLength);
		
		jplNewsStoryYear.add(jlbNewsStoryYear);
		jplNewsStoryLength.add(jcbNewsStoryYear);

		jplNewsStoryMonth.add(jlbNewsStoryMonth);
		jplNewsStoryMonth.add(jcbNewsStoryMonth);
		
		jplNewsStoryDay.add(jlbNewsStoryDay);
		jplNewsStoryDay.add(jcbNewsStoryDay);
		
		jlbNewsStoryPartOfDay.add(jlbNewsStoryPartOfDay);
		jlbNewsStoryPartOfDay.add(jcbNewsStoryPartOfDay);
		
		//Add all of the "when" related panels to the jplNewsStoryWhen panel
		jplNewsStoryWhen.add(jplNewsStoryYear);
		jplNewsStoryWhen.add(jplNewsStoryMonth);
		jplNewsStoryWhen.add(jplNewsStoryDay);
		jplNewsStoryWhen.add(jplNewsStoryPartOfDay);
		
		jplAddEditNewsStory.add(jbtAddEditNewsStory);
		
		
		//Add all of the panels to this container
		this.add(jpNewsStoryType);
		this.add(jpNewsStorySource);
		this.add(jpNewsStorySubject);
		this.add(jpNewsStoryNewsMaker1);
		this.add(jpNewsStoryNewsMaker2);
		this.add(jplNewsStoryLength);
		this.add(jplNewsStoryWhen);
		this.add(jplAddEditNewsStory);
		
		//TODO: Make proper formatting for this panel (FlowLayout, BorderLayout, Box, etc.)
		
	}
}
