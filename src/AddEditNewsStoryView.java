import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AddEditNewsStoryView {
	public AddEditNewsStoryView() {
		
	}
	
	
	private long serialVersionUID;
	private NewsDataBaseModel newsDataBaseModel; 
	private NewsStory newsStory; 
	private JLabel jlbNewsStoryType; 
	JComboBox<NewsMedia> jcbNewsStoryType; 
	
	private JPanel jpNewsStoryType; 
	private JLabel jlbNewsStorySource; 
	JComboBox<String> jcbNewsStorySource; 
	
	private JPanel jpNewsStorySource; 
	private JLabel jlbNewsStoryTopic; 
	JComboBox<String> jcbNewsStoryTopic; 
	
	private JPanel jpNewsStoryTopic; 
	private JLabel jlbNewsStorySubject; 
	JComboBox<String> jcbNewsStorySubject; 
	
	private JPanel jpNewsStorySubject; 
	private JLabel jlbNewsStoryNewsMaker1; 
	JComboBox<String> jcbNewsStoryNewsMaker1; 
	
	private JPanel jpNewsStoryNewsMaker1; 
	private JLabel jlbNewsStoryNewsMaker2; 
	JComboBox<String> jcbNewsStoryNewsMaker2; 
	
	private JPanel jpNewsStoryNewsMaker2; 
	private JLabel jlbNewsStoryLength; 
	private NumberFormat integerFieldFormatter; 
	JFormattedTextField jftfNewsStoryLength; 
	
	private JPanel jplNewsStoryLength; 
	private JLabel jlbNewsStoryYear; 
	private Integer[] years; 
	JComboBox<Integer> jcbNewsStoryYear; 
	
	private JPanel jplNewsStoryYear; 
	private JLabel jlbNewsStoryMonth; 
	JComboBox<Month> jcbNewsStoryMonth; 
	
	private JPanel jplNewsStoryMonth; 
	private JLabel jlbNewsStoryDay; 
	private Integer[] days; 
	JComboBox<Integer> jcbNewsStoryDay; 
	
	private JPanel jplNewsStoryDay; 
	private JLabel jlbNewsStoryPartOfDay; 
	JComboBox<PartOfDay> jcbNewsStoryPartOfDay; 
	
	private JPanel jplNewsStoryPartOfDay; 
	
	private JPanel jplNewsStoryWhen; 
	JButton jbtAddEditNewsStory; 
	
	private JPanel jplAddEditNewsStory; 
	
	public AddEditNewsStoryView(NewsDataBaseModel newsDataBaseModel , NewsStory newsStory) {
		
	}
}
