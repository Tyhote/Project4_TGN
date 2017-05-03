import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
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
	JComboBox<Month> jcbNewsStoryMonth; 
	private JPanel jplNewsStoryMonth = new JPanel(); 
	
	private JLabel jlbNewsStoryDay = new JLabel("Day: "); 
	private Integer[] days; 
	JComboBox<Integer> jcbNewsStoryDay; 
	private JPanel jplNewsStoryDay = new JPanel();
	
	private JLabel jlbNewsStoryPartOfDay = new JLabel("Part of Day: "); 
	JComboBox<PartOfDay> jcbNewsStoryPartOfDay; 
	private JPanel jplNewsStoryPartOfDay = new JPanel(); 
	
	private JPanel jplNewsStoryWhen = new JPanel();
	
	JButton jbtAddEditNewsStory = new JButton("Edit News Story"); 
	private JPanel jplAddEditNewsStory = new JPanel(); 
	
	public AddEditNewsStoryView(NewsDataBaseModel newsDataBaseModel , NewsStory newsStory) {
		
		//Assign the arguments to the proper fields
		this.newsDataBaseModel = newsDataBaseModel;
		this.newsStory = newsStory;
		
		//Initialize the JComboBoxes for the year, month, day, and part of day
		
		//Array of Integer values for years (2000-2017 inclusive). (see Dr. Hougen's javadoc)
		years = new Integer[18];
		for (int year = 2000; year <= 2017; ++year) //Fill the array
		{
			years[year - 2000] = year;
		}
		jcbNewsStoryYear = new JComboBox<Integer>(years);
		
		jcbNewsStoryMonth = new JComboBox<Month>(Month.values());
		
		//Array of Integer values for day of the month (1-31 inclusive). (see Dr. Hougen's javadoc)
		days = new Integer[31];
		for (int day = 1; day <= 31; ++day) //Fill the array
		{
			days[day - 1] = day;
		}
		jcbNewsStoryDay = new JComboBox<Integer>(days);
		
		jcbNewsStoryPartOfDay = new JComboBox<PartOfDay>(PartOfDay.values()); 
		
		
		//Initialize jcbNewsStoryType and set the selected option to the proper NewsMedia
		jcbNewsStoryType = new JComboBox<NewsMedia>(NewsMedia.values());
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
		
		//Finish initializing the rest of the JComboBoxes using the arrays passed by newsDataBaseModel's methods
		//and setting the selected values to the corresponding fields from the newsStory
		jcbNewsStorySource = new JComboBox<String>(newsDataBaseModel.getNewsSources());
		jcbNewsStorySource.setSelectedItem(newsStory.getSource());
		
		jcbNewsStoryTopic = new JComboBox<String>(newsDataBaseModel.getNewsTopics());
		jcbNewsStoryTopic.setSelectedItem(newsStory.getTopic());
		
		jcbNewsStorySubject = new JComboBox<String>(newsDataBaseModel.getNewsSubjects());
		jcbNewsStorySubject.setSelectedItem(newsStory.getSubject());
		
		jcbNewsStoryNewsMaker1 = new JComboBox<String>(newsDataBaseModel.getNewsMakerNames());
		jcbNewsStoryNewsMaker1.setSelectedItem(newsStory.getNewsMaker1().getName());
		
		jcbNewsStoryNewsMaker2 = new JComboBox<String>(newsDataBaseModel.getNewsMakerNames());
		jcbNewsStoryNewsMaker2.setSelectedItem(newsStory.getNewsMaker2().getName());
		
		//Set values and selected items for the rest of the newsStory's info
		jftfNewsStoryLength.setValue(newsStory.getLength());
		jftfNewsStoryLength.setColumns(10); //Makes the text field a certain width for aesthetic
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
		jplNewsStoryYear.add(jcbNewsStoryYear);

		jplNewsStoryMonth.add(jlbNewsStoryMonth);
		jplNewsStoryMonth.add(jcbNewsStoryMonth);
		
		jplNewsStoryDay.add(jlbNewsStoryDay);
		jplNewsStoryDay.add(jcbNewsStoryDay);
		
		jplNewsStoryPartOfDay.add(jlbNewsStoryPartOfDay);
		jplNewsStoryPartOfDay.add(jcbNewsStoryPartOfDay);
		
		//Add all of the "when" related panels to the jplNewsStoryWhen panel
		jplNewsStoryWhen.add(jplNewsStoryYear);
		jplNewsStoryWhen.add(jplNewsStoryMonth);
		jplNewsStoryWhen.add(jplNewsStoryDay);
		jplNewsStoryWhen.add(jplNewsStoryPartOfDay);
		
		jplAddEditNewsStory.add(jbtAddEditNewsStory);
		
		
		//Add components to a box for formatting and add the box to this AddEditNewsStoryView
		Box box = Box.createVerticalBox();
		box.add(jpNewsStoryType);
		box.add(Box.createVerticalGlue());
		box.add(jpNewsStorySource);
		box.add(Box.createVerticalGlue());
		box.add(jpNewsStoryTopic);
		box.add(Box.createVerticalGlue());
		box.add(jpNewsStorySubject);
		box.add(Box.createVerticalGlue());
		box.add(jpNewsStoryNewsMaker1);
		box.add(Box.createVerticalGlue());
		box.add(jpNewsStoryNewsMaker2);
		box.add(Box.createVerticalGlue());
		box.add(jplNewsStoryLength);
		box.add(Box.createVerticalGlue());
		box.add(jplNewsStoryWhen);
		box.add(Box.createVerticalGlue());
		box.add(jplAddEditNewsStory);
		
		this.add(box);		
	}
}
