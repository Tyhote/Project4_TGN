import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
class NewsMakerModel implements Comparable<NewsMakerModel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7042093619925230030L;
	private ArrayList<ActionListener> actionListenerList;
	private String name;
	private NewsStoryListModel newsStoryListModel;

	public NewsMakerModel() {

		actionListenerList = new ArrayList<ActionListener>();
		newsStoryListModel = new NewsStoryListModel();
		name = "None";
	}

	public NewsMakerModel(String name) {

		this.name = name;
		actionListenerList = new ArrayList<ActionListener>();
		newsStoryListModel = new NewsStoryListModel();
	}

	public String getName() {

		return name;
	}

	public NewsStoryListModel getNewsStoryListModel() {
		if(newsStoryListModel != null){
			return newsStoryListModel;
		}
		newsStoryListModel = new NewsStoryListModel();
		return newsStoryListModel;
	}

	public void addNewsStory(NewsStory newsStory) {
		if (newsStory.getNewsMaker1().equals(this) || newsStory.getNewsMaker2().equals(this)) {
			newsStoryListModel.add(newsStory);
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Modified News Maker"));
		} else {
			throw new IllegalArgumentException("Story Not Relevant");
		}

	}

	public void setName(String name) {
		this.name = name;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Modified News Maker"));
	}

	public void setNewsStoryListModel(NewsStoryListModel newsStoryListModel) {
		this.newsStoryListModel = newsStoryListModel;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Modified News Story List"));
	}

	public void removeNewsStory(NewsStory newsStory) {
		newsStoryListModel.remove(newsStory);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Modified News Maker"));
	}

	public boolean equals(Object o) {

		NewsMakerModel m = (NewsMakerModel) o;
		if (!(this.getName() == null) && !(m.getName() == null)) {
			if (name.equals(m.getName())) {
				return true;
			}
		}
		return false;
	}

	public int compareTo(NewsMakerModel newsMakerModel) {
		return this.getName().compareTo(newsMakerModel.getName());
	}

	public String toString() {
		return this.name;
	}

	public void addActionListener(ActionListener l) {
		actionListenerList.add(l);
	}

	public void removeActionListener(ActionListener l) {
		actionListenerList.remove(l);
	}

	private void processEvent(ActionEvent e) {
		for (ActionListener actionListener : actionListenerList) {
			actionListener.actionPerformed(e);
		}
	}

}
