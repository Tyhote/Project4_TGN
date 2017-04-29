import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

class NewsMakerModel implements Comparable<NewsMakerModel>, Serializable {
	
	private long serialVersionUID;
	private ArrayList<ActionListener> actionListenerList;
	private String name;
	private NewsStoryListModel newsStoryListModel;
	public NewsMakerModel() {}
	public NewsMakerModel(String name) {}
	public String getName() {return null;}
	public NewsStoryListModel getNewsStoryListModel() {return null;}
	public void addNewsStory(NewsStory newsStory) {}
	public void setName(String name) {}
	public void setNewsStoryListModel(NewsStoryListModel newsStoryListModel) {}
	public void removeNewsStory(NewsStory newsStory) {}
	public boolean equals(Object o) {return true;}
	public int compareTo(NewsMakerModel newsMakerModel) {return 0;}
	public String toString() {return null;}
	public void addActionListener(ActionListener l) {}
	public void removeActionListener(ActionListener l) {}
	private void processEvent(ActionEvent e) {}

}
