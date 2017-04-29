import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;

public class NewsController {

	private NewsDataBaseModel newsDataBaseModel;
	private SelectionView selectionView;
	private EditNewsMakerView editNewsMakerView;
	private JDialog viewDialog;
	private AddEditNewsStoryView addEditNewsStoryView;
	private NewsStory editedNewsStory;
	private MediaTypeSelectionView mediaTypeSelectionView;
	private List<NewsMedia> selectedMediaTypes;

	public NewsController() {

	}
	
	private class FileMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	private class NewsMakerMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	private class NewsStoryMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	private class DisplayMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	public class EditNewsMakerNameListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	public class RemoveNewsMakerFromNewStoriesListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	public class AddEditNewsStoryListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	public class MediaTypeSelectionListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
		}
	}
	
	public void setNewsDataBaseModel(NewsDataBaseModel DBModel) {

	}

	public void setSelectionView(SelectionView SView) {

	}

	private void loadNewsData() {

	}

	private void saveNewsData() {

	}

	private void importNoozStories() {

	}

	private void exportNewsStories() {

	}

	private void addNewsMaker() {

	}

	private void editNewsMakers() {

	}

	private void deleteNewsMakers() {

	}

	private void deleteNewsMakerList() {

	}

	private void addNewsStory() {

	}

	private void editNewsStories() {

	}

	private void sortNewsStories() {

	}

	private void deleteNewsStories() {

	}

	private void deleteAllNewsStories() {

	}

	private void displayPieCharts() {

	}

	private void displayTextViews() {

	}
}
