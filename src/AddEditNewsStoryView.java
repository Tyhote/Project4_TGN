import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class AddEditNewsStoryView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2941348907773307271L;

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

	JButton jbtAddEditNewsStory;
	private JPanel jplAddEditNewsStory = new JPanel();

	public AddEditNewsStoryView(NewsDataBaseModel newsDataBaseModel, NewsStory newsStory) {
		
		// Add or Edit news story
		boolean addNotEdit = newsStory.equals(new NewspaperStory(LocalDate.of(2000, 1, 1), "", 0, "", "", new NewsMakerModel(""),
				new NewsMakerModel("")));
		
		if (addNotEdit) {
			jbtAddEditNewsStory = new JButton("Add News Story");
		} else {
			jbtAddEditNewsStory = new JButton("Edit News Story");
		}
		
		
		// Assign the arguments to the proper fields
		this.newsDataBaseModel = newsDataBaseModel;
		this.newsStory = newsStory;

		// Initialize the JComboBoxes for the year, month, day, and part of day

		// Array of Integer values for years (2000-2017 inclusive). (see Dr.
		// Hougen's javadoc)
		years = new Integer[18];
		for (int year = 2000; year <= 2017; ++year) // Fill the array
		{
			years[year - 2000] = year;
		}
		jcbNewsStoryYear = new JComboBox<Integer>(years);

		jcbNewsStoryMonth = new JComboBox<Month>(Month.values());

		// Array of Integer values for day of the month (1-31 inclusive). (see
		// Dr. Hougen's javadoc)
		days = new Integer[31];
		for (int day = 1; day <= 31; ++day) // Fill the array
		{
			days[day - 1] = day;
		}
		jcbNewsStoryDay = new JComboBox<Integer>(days);

		jcbNewsStoryPartOfDay = new JComboBox<PartOfDay>(PartOfDay.values());

		// Initialize jcbNewsStoryType and set the selected option to the proper
		// NewsMedia
		jcbNewsStoryType = new JComboBox<NewsMedia>(NewsMedia.values());
		if (this.newsStory instanceof NewspaperStory) {
			jcbNewsStoryType.setSelectedItem(NewsMedia.NEWSPAPER);
		} else if (this.newsStory instanceof TVNewsStory) {
			jcbNewsStoryType.setSelectedItem(NewsMedia.TV);
		} else // if (this.newsStory instanceof OnlineNewsStory)
		{
			jcbNewsStoryType.setSelectedItem(NewsMedia.ONLINE);
		}

		// Finish initializing the rest of the JComboBoxes using the arrays
		// passed by newsDataBaseModel's methods
		// and setting the selected values to the corresponding fields from the
		// newsStory. The NewsMaker JComboBoxes should be editable in case
		// the user wants to add their own.
		jcbNewsStorySource = new JComboBox<String>(this.newsDataBaseModel.getNewsSources());
		jcbNewsStorySource.setSelectedItem(newsStory.getSource());
		
		jcbNewsStoryTopic = new JComboBox<String>(this.newsDataBaseModel.getNewsTopics());
		jcbNewsStoryTopic.setSelectedItem(newsStory.getTopic());
		
		jcbNewsStorySubject = new JComboBox<String>(this.newsDataBaseModel.getNewsSubjects());
		jcbNewsStorySubject.setSelectedItem(newsStory.getSubject());
		
		jcbNewsStoryNewsMaker1 = new JComboBox<String>(this.newsDataBaseModel.getNewsMakerNames());
		jcbNewsStoryNewsMaker1.setSelectedItem(newsStory.getNewsMaker1().getName());
		jcbNewsStoryNewsMaker1.setEditable(true);
		
		jcbNewsStoryNewsMaker2 = new JComboBox<String>(this.newsDataBaseModel.getNewsMakerNames());
		jcbNewsStoryNewsMaker2.setSelectedItem(newsStory.getNewsMaker2().getName());
		jcbNewsStoryNewsMaker2.setEditable(true);
		
		// Set values and selected items for the rest of the newsStory's info
		jftfNewsStoryLength.setValue(newsStory.getLength());
		jftfNewsStoryLength.setColumns(10); // Makes the text field a certain
											// width for aesthetic
		jcbNewsStoryYear.setSelectedItem(newsStory.getDate().getYear());
		jcbNewsStoryMonth.setSelectedItem(Month.fromInt(newsStory.getDate().getMonthValue()));
		jcbNewsStoryDay.setSelectedItem(newsStory.getDate().getDayOfMonth());
		// The following code sets the selected item for the part of day
		// JComboBox based on the type of media of the NewsStory
		if (newsStory instanceof NewspaperStory) {
			// Newspapers are published in the morning
			jcbNewsStoryPartOfDay.setSelectedItem(PartOfDay.MORNING.toString());
		} else if (newsStory instanceof TVNewsStory){
			// Cast to TVNewsStory in order to use the getPartOfDay method,
			// which isn't defined for NewsStory
			TVNewsStory newsStoryClone = (TVNewsStory) newsStory;
			jcbNewsStoryPartOfDay.setSelectedItem(newsStoryClone.getPartOfDay().toString());
		} else {
			// Cast to OnlineNewsStory in order to use the getPartOfDay method,
			// which isn't defined for NewsStory
			OnlineNewsStory newsStoryClone = (OnlineNewsStory) newsStory;
			jcbNewsStoryPartOfDay.setSelectedItem(newsStoryClone.getPartOfDay().toString());
		}

		// Add the components to their corresponding JPanels
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

		// Add all of the "when" related panels to the jplNewsStoryWhen panel
		jplNewsStoryWhen.add(jplNewsStoryYear);
		jplNewsStoryWhen.add(jplNewsStoryMonth);
		jplNewsStoryWhen.add(jplNewsStoryDay);
		jplNewsStoryWhen.add(jplNewsStoryPartOfDay);

		jplAddEditNewsStory.add(jbtAddEditNewsStory);
		
		// Set the action command for the Add/Edit News Story button
		if (addNotEdit)	{
			jbtAddEditNewsStory.setActionCommand("Add News Story");
		} else {
			jbtAddEditNewsStory.setActionCommand("Edit News Story");
		}
		
		
		// Set the layout to a GridLayout with 1 column and add the panels to
		// this AddEditNewsStoryView
		this.setLayout(new GridLayout(0, 1));
		this.add(jpNewsStoryType);
		this.add(jpNewsStorySource);
		this.add(jpNewsStoryTopic);
		this.add(jpNewsStorySubject);
		this.add(jpNewsStoryNewsMaker1);
		this.add(jpNewsStoryNewsMaker2);
		this.add(jplNewsStoryLength);
		this.add(jplNewsStoryWhen);
		this.add(jplAddEditNewsStory);
	}
	
	public void registerAddEditNewsStoryListener(ActionListener AddEditNewsStoryListener) {
		jbtAddEditNewsStory.addActionListener(AddEditNewsStoryListener);
	}
}
