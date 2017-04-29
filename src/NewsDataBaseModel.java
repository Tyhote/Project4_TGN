import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultListModel;

public class NewsDataBaseModel {
	private long serialVersionUID;
	private ArrayList<ActionListener> actionListenerList;
	private Map<String, String> newsSourceMap;
	private Map<String, String> newsTopicMap;
	private Map<String, String> newsSubjectMap;
	NewsMakerModel none;
	private NewsMakerListModel newsMakerListModel;
	private NewsStoryListModel newsStoryListModel;

	public void NewsDataBaseModel() {

	}

	public void NewsDataBaseModel(NewsMakerListModel newsMakerListModel, NewsStoryListModel newsStoryListModel) {

	}

	public Map<String, String> getNewsSourceMap() {
		return null;
	}

	public String[] getNewsSources() {
		return null;
	}

	public void setNewsSourceMap(Map<String, String> newsSourceMap) {
	}

	public Map<String, String> getNewsTopicMap() {
		return null;
	}

	public String[] getNewsTopics() {
		return null;
	}

	public void setNewsTopicMap(Map<String, String> newsTopicMap) {
	}

	public Map<String, String> getNewsSubjectMap() {
		return null;
	}

	public String[] getNewsSubjects() {
		return null;
	}

	public void setNewsSubjectMap(Map<String, String> newsSubjectMap) {
	}

	public boolean newsMakerListIsEmpty() {
		return true;
	}

	public boolean containsNewsMakerModel(NewsMakerModel newsMakerModel) {
		return true;
	}

	public NewsMakerListModel getNewsMakerListModel() {
		return null;
	}

	public String[] getNewsMakerNames() {
		return null;
	}

	public DefaultListModel<NewsMakerModel> getNewsMakers() {
		return null;
	}

	public void setNewsMakerListModel(NewsMakerListModel newsMakerListModel) {
	}

	public void addNewsMakerModel(NewsMakerModel newsMakerModel) {
	}

	public void replaceNewsMakerModel(NewsMakerModel newsMakerModel) {
	}

	public void removeNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
	}

	public void removeAllNewsMakers() {
	}

	public void sortNewsMakerListModel() {
	}

	public boolean newsStoryListIsEmpty() {
		return true;
	}

	public boolean containsNewsStory(NewsStory newsStory) {
		return true;
	}

	public NewsStoryListModel getNewsStoryListModel() {
		return null;
	}

	public DefaultListModel<NewsStory> getNewsStories() {
		return null;
	}

	public void setNewsStoryListModel(NewsStoryListModel newsStoryListModel) {
	}

	public void setNewsStoryListModelFromArray(NewsStory[] newsStoryArray) {
	}

	public void addNewsStory(NewsStory newsStory) {
	}

	public void removeNewsStories(DefaultListModel<NewsStory> newsStories) {
	}

	public void removeAllNewsStories() {
	}

	public void addActionListener(ActionListener l) {
	}

	public void removeActionListener(ActionListener l) {
	}

	private void processEvent(ActionEvent e) {
	}

}
