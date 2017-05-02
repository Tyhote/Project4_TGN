import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class EditNewsMakerView implements ActionListener {

	private long serialVersionUID; 
	NewsMakerModel newsMakerModel; 
	private NewsDataBaseModel newsDataBaseModel; 
	private DefaultListModel<String> newsStoryStringList; 
	private JList<String> jlNewsStoryList; 
	private JScrollPane jspNewsStoryList; 
	private JPanel jpNewsStoryList; 
	JTextField jtfName; 
	private JLabel jlbName; 
	JButton jbtRemoveFromStory; 
	private JPanel jplName; 
	
	
	public EditNewsMakerView(NewsMakerModel newsMakerModel , NewsDataBaseModel newsDataBaseModel) {
		
	}
	
	public int[] getSelectedNewsStoryIndices() {
		return null;
	}
	
	private void populateNewsStoryJList(){
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
