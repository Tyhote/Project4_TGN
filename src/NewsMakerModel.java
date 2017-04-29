import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

class NewsMakerModel implements Comparable<NewsMakerModel>, Serializable {

	private long serialVersionUID;
	private ArrayList<ActionListener> actionListenerList;
	private String name;
	private NewsStoryListModel newsStoryListModel;

	
	
	public NewsMakerModel() {
		
		actionListenerList = new ArrayList<ActionListener>();
	}

	
	public NewsMakerModel(String name) {
		
		this.name = name;
		actionListenerList = new ArrayList<ActionListener>();
	}

	
	public String getName() {
		
		return name;
	}

	
	public NewsStoryListModel getNewsStoryListModel() {
		
		return newsStoryListModel;
	}

	
	public void addNewsStory(NewsStory newsStory) {
		
		newsStoryListModel.add(newsStory);
	}

	
	public void setName(String name) {
		
		this.name = name;
	}

	
	public void setNewsStoryListModel(NewsStoryListModel newsStoryListModel) {
		
		this.newsStoryListModel = newsStoryListModel;
	}

	
	public void removeNewsStory(NewsStory newsStory) {
		
		newsStoryListModel.remove(newsStory);
	}

	
	public boolean equals(Object o) {
		
		NewsMakerModel m = (NewsMakerModel) o;
		if (name.equals(m.getName())) {
			return true;
		} else {
			return false;
		}
	}

	
	public int compareTo(NewsMakerModel newsMakerModel) {
		
		return newsMakerModel.getName().compareTo(newsMakerModel.getName());
	}

	
	//TODO
	public String toString() {
		
		return null;
	}

	
	//TODO
	public void addActionListener(ActionListener l) {
	}

	
	//TODO
	public void removeActionListener(ActionListener l) {
	}

	
	//TODO
	private void processEvent(ActionEvent e) {
	}

}
