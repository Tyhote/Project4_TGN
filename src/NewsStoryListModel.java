import java.io.Serializable;

import javax.swing.DefaultListModel;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
class NewsStoryListModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private DefaultListModel<NewsStory> newsStories;

	public NewsStoryListModel() {
		newsStories = new DefaultListModel<NewsStory>();
	}

	public NewsStoryListModel(NewsStoryListModel newsStoryListModel) {
		this.newsStories = newsStoryListModel.getNewsStories();
	}

	public boolean isEmpty() {
		if (newsStories.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return this.newsStories.size();
	}

	public boolean contains(NewsStory newsStory) {
		return newsStories.contains(newsStory);
	}

	public NewsStory get(int index) {
		if (index >= 0 && index < this.newsStories.size()) {
			return this.newsStories.get(index);
		} else {
			throw new IllegalArgumentException("Index out of bounds: " + index);
		}
	}

	public DefaultListModel<NewsStory> getNewsStories() {
		return newsStories;
	}

	public void add(NewsStory newsStory) {
		if (!newsStories.contains(newsStory)) {
			this.newsStories.addElement(newsStory);
			return;
		}return;
		//throw new IllegalArgumentException("Story already in list");
	}

	public void remove(NewsStory newsStory) {
		newsStories.removeElement(newsStory);
	}

	public void removeListOfNewsStories(DefaultListModel<NewsStory> newsStories) {
		for(int i = 0; i < newsStories.size(); ++i){
			remove(newsStories.getElementAt(i));
		}
	}

	public void setNewsStories(NewsStoryListModel newsStoryListModel) {
		this.newsStories = newsStoryListModel.getNewsStories();
	}

	public void setNewsStoriesFromArray(NewsStory[] newsStoryArray) {
		newsStories.removeAllElements();
		for (NewsStory newsStory : newsStoryArray) {
			newsStories.addElement(newsStory);
		}
	}
	
	public DefaultListModel<String> getStoriesForJList(){
		DefaultListModel<String> result = new DefaultListModel<String>();
		for(int i = 0; i < newsStories.size(); ++i){
			result.addElement(newsStories.get(i).toString());
		}
		return result;
	}
}
