import java.io.Serializable;

import javax.swing.DefaultListModel;

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
		if (newsStories.contains(newsStory)) {
			return true;
		} else {
			return false;
		}
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
		}
	}

	public void remove(NewsStory newsStory) {
		newsStories.removeElement(newsStory);
	}

	public void removeListOfNewsStories(DefaultListModel<NewsStory> newsStories) {
		newsStories.removeAllElements();
	}

	public void setNewsStories(NewsStoryListModel newsStoryListModel) {
		this.newsStories = newsStoryListModel.getNewsStories();
	}

	public void setNewsStoriesFromArray(NewsStory[] newsStoryArray) {
		for (int i = 0; i < newsStoryArray.length; i++) {
			newsStories.addElement(newsStoryArray[i]);
		}
	}
}
