import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class NewsDataBaseModel implements Serializable {

	private static final long serialVersionUID = 6305823792028405117L;
	private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	private Map<String, String> newsSourceMap = new HashMap<String, String>();
	private Map<String, String> newsTopicMap = new HashMap<String, String>();
	private Map<String, String> newsSubjectMap = new HashMap<String, String>();
	NewsMakerModel none = new NewsMakerModel("None");
	private NewsMakerListModel newsMakerListModel;
	private NewsStoryListModel newsStoryListModel;

	public NewsDataBaseModel() {
		newsStoryListModel = new NewsStoryListModel();
		newsMakerListModel = new NewsMakerListModel();
		newsMakerListModel.add(none);
		none.setNewsStoryListModel(new NewsStoryListModel());
		newsMakerListModel.add(none);
	}

	public NewsDataBaseModel(NewsMakerListModel newsMakerListModel, NewsStoryListModel newsStoryListModel) {
		this.newsStoryListModel = newsStoryListModel;
		this.newsMakerListModel = newsMakerListModel;
		newsMakerListModel.add(none);
	}

	public Map<String, String> getNewsSourceMap() {
		return new HashMap<String, String>(newsSourceMap);
	}

	public String[] getNewsSources() {
		//return newsSourceMap.values().toArray(new String[0]);
		ArrayList<String> sourceList = new ArrayList<String>(newsSourceMap.values());
		String[] result = new String[sourceList.size()];
		for(int i = 0; i < result.length; ++i){
			result[i] = sourceList.get(i);
		}
		return result;
	}

	public void setNewsSourceMap(Map<String, String> newsSourceMap) {
		this.newsSourceMap = newsSourceMap;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Set source map"));
	}

	public Map<String, String> getNewsTopicMap() {
		return new HashMap<String, String>(newsTopicMap);
	}

	public String[] getNewsTopics() {
		ArrayList<String> topicList = new ArrayList<String>(newsTopicMap.values());
		String[] result = new String[topicList.size()];
		for(int i = 0; i < result.length; ++i){
			result[i] = topicList.get(i);
		}
		return result;
	}

	public void setNewsTopicMap(Map<String, String> newsTopicMap) {
		this.newsTopicMap = newsTopicMap;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Set topic map"));
	}

	public Map<String, String> getNewsSubjectMap() {
		return new HashMap<String, String>(newsSubjectMap);
	}

	public String[] getNewsSubjects() {
		ArrayList<String> subjectList = new ArrayList<String>(newsSubjectMap.values());
		String[] result = new String[subjectList.size()];
		for(int i = 0; i < result.length; ++i){
			result[i] = subjectList.get(i);
		}
		return result;
	}

	public void setNewsSubjectMap(Map<String, String> newsSubjectMap) {
		this.newsSubjectMap = newsSubjectMap;
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Set subject map"));
	}

	public boolean newsMakerListIsEmpty() {
		return newsMakerListModel.isEmpty();
	}

	public boolean containsNewsMakerModel(NewsMakerModel newsMakerModel) {
		return this.newsMakerListModel.contains(newsMakerModel);
	}

	public NewsMakerListModel getNewsMakerListModel() {
		return newsMakerListModel;
	}

	public String[] getNewsMakerNames() {
		String[] result = new String[newsMakerListModel.size()];
		for (int i = 0; i < newsMakerListModel.size(); ++i) {
			result[i] = newsMakerListModel.get(i).getName();
		}
		return result;
	}

	public DefaultListModel<NewsMakerModel> getNewsMakers() {
		return newsMakerListModel.getNewsMakers();
	}

	public void setNewsMakerListModel(NewsMakerListModel newsMakerListModel) {
		this.newsMakerListModel = newsMakerListModel;
		processEvent(
				new ActionEvent(this.newsMakerListModel, ActionEvent.ACTION_PERFORMED, "Modified News Maker List"));
	}

	public void addNewsMakerModel(NewsMakerModel newsMakerModel) {
		newsMakerListModel.add(newsMakerModel);
		processEvent(
				new ActionEvent(this.newsMakerListModel, ActionEvent.ACTION_PERFORMED, "Modified News Maker List"));
	}

	public void replaceNewsMakerModel(NewsMakerModel newsMakerModel) {
		if (newsMakerListModel.contains(newsMakerModel)) {
			newsMakerListModel.remove(newsMakerModel);
			newsMakerListModel.add(newsMakerModel);
		}
		processEvent(
				new ActionEvent(this.newsMakerListModel, ActionEvent.ACTION_PERFORMED, "Modified News Maker List"));
	}

	public void removeNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
		for (int i = 0; i < newsMakers.getSize(); ++i) {
			newsMakerListModel.remove(newsMakers.get(i));
		}
		processEvent(
				new ActionEvent(this.newsMakerListModel, ActionEvent.ACTION_PERFORMED, "Modified News Maker List"));
	}

	public void removeAllNewsMakers() {
		newsMakerListModel.removeAllNewsMakers();
		processEvent(
				new ActionEvent(this.newsMakerListModel, ActionEvent.ACTION_PERFORMED, "Modified News Maker List"));
	}

	public void sortNewsMakerListModel() {
		newsMakerListModel.sort();
		processEvent(
				new ActionEvent(this.newsMakerListModel, ActionEvent.ACTION_PERFORMED, "Modified News Maker List"));
	}

	public boolean newsStoryListIsEmpty() {
		return newsStoryListModel.isEmpty();
	}

	public boolean containsNewsStory(NewsStory newsStory) {
		return newsStoryListModel.contains(newsStory);
	}

	public NewsStoryListModel getNewsStoryListModel() {
		NewsStoryListModel result = new NewsStoryListModel();
		for (int i = 0; i < newsStoryListModel.size(); ++i) {
			result.add(newsStoryListModel.get(i));
		}
		return result;
	}

	public DefaultListModel<NewsStory> getNewsStories() {
		return newsStoryListModel.getNewsStories();
	}

	public void setNewsStoryListModel(NewsStoryListModel newsStoryListModel) {
		this.newsStoryListModel = newsStoryListModel;
		processEvent(
				new ActionEvent(this.newsStoryListModel, ActionEvent.ACTION_PERFORMED, "Modified News Story List"));
	}

	public void setNewsStoryListModelFromArray(NewsStory[] newsStoryArray) {
		newsStoryListModel.setNewsStoriesFromArray(newsStoryArray);
		processEvent(
				new ActionEvent(this.newsStoryListModel, ActionEvent.ACTION_PERFORMED, "Modified News Story List"));
	}

	public void addNewsStory(NewsStory newsStory) {
		newsStoryListModel.add(newsStory);
		processEvent(
				new ActionEvent(this.newsStoryListModel, ActionEvent.ACTION_PERFORMED, "Modified News Story List"));
	}

	public void removeNewsStories(DefaultListModel<NewsStory> newsStories) {
		newsStoryListModel.removeListOfNewsStories(newsStories);
		processEvent(
				new ActionEvent(this.newsStoryListModel, ActionEvent.ACTION_PERFORMED, "Modified News Story List"));
	}

	public void removeAllNewsStories() {
		newsStoryListModel = new NewsStoryListModel();
		processEvent(
				new ActionEvent(this.newsStoryListModel, ActionEvent.ACTION_PERFORMED, "Modified News Story List"));
	}

	public void addActionListener(ActionListener l) {
		actionListenerList.add(l);
	}

	public void removeActionListener(ActionListener l) {
		actionListenerList.remove(l);
	}

	private void processEvent(ActionEvent e) {
		for (int i = 0; i < actionListenerList.size(); ++i) {
			actionListenerList.get(i).actionPerformed(e);
		}
	}

}
