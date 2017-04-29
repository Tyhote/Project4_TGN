import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

class NewsStoryListModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<NewsStory> newsStories = new ArrayList<NewsStory>();

	public NewsStoryListModel() {
	}

	public NewsStoryListModel(NewsStoryListModel newsStoryListModel) {
	}

	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return this.newsStories.size();
	}

	public boolean contains(NewsStory newsStory) {
		return true;
	}

	public NewsStory get(int index) {
		if (index >= 0 && index < this.newsStories.size()) {
			return this.newsStories.get(index);
		} else {
			throw new IllegalArgumentException("Index out of bounds: " + index);
		}
	}

	public DefaultListModel<NewsStory> getNewsStories() {
		return null;
	}

	public void add(NewsStory newsStory) {
		if (!newsStories.contains(newsStory)) {
			this.newsStories.add(newsStory);
		}
	}

	public void remove(NewsStory newsStory) {
	}

	public void removeListOfNewsStories(DefaultListModel<NewsStory> newsStories) {
	}

	public void setNewsStories(NewsStoryListModel newsStoryListModel) {
	}

	public void setNewsStoriesFromArray(NewsStory[] newsStoryArray) {
	}
}
