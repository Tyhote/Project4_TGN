import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

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
		newsDataBaseModel = new NewsDataBaseModel();
		selectionView = new SelectionView();
		editNewsMakerView = new EditNewsMakerView(new NewsMakerModel(""), newsDataBaseModel);
		viewDialog = new JDialog();
		editedNewsStory = new NewspaperStory(LocalDate.of(2000, 1, 1), null, 0, null, null, null, null);
		addEditNewsStoryView = new AddEditNewsStoryView(newsDataBaseModel, editedNewsStory);
		mediaTypeSelectionView = new MediaTypeSelectionView();
		selectedMediaTypes = new ArrayList<NewsMedia>();
	}

	private class FileMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Load".equals(actionEvent.getActionCommand())) {
				loadNewsData();
			}
			if ("Save".equals(actionEvent.getActionCommand())) {
				saveNewsData();
			}
			if ("Import".equals(actionEvent.getActionCommand())) {
				importNoozStories();
			}
			if ("Export".equals(actionEvent.getActionCommand())) {
				exportNewsStories();
			}
		}
	}

	private class NewsMakerMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Add NewsMaker".equals(actionEvent.getActionCommand())) {
				addNewsMaker();
			}
			if ("Edit NewsMaker".equals(actionEvent.getActionCommand())) {
				editNewsMakers();
			}
			if ("Delete NewsMaker".equals(actionEvent.getActionCommand())) {
				deleteNewsMakers();
			}
			if ("Delete NewsMaker List".equals(actionEvent.getActionCommand())) {
				deleteNewsMakerList();
			}
		}
	}

	private class NewsStoryMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Add News Story".equals(actionEvent.getActionCommand())) {
				addNewsStory();
			}
			if ("Edit News Story".equals(actionEvent.getActionCommand())) {
				editNewsStories();
			}
			if ("Sort News Stories".equals(actionEvent.getActionCommand())) {
				sortNewsStories();
			}
			if ("Delete News Story".equals(actionEvent.getActionCommand())) {
				deleteNewsStories();
			}
			if ("Delete All News Stories".equals(actionEvent.getActionCommand())) {
				deleteAllNewsStories();
			}
		}
	}

	private class DisplayMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Pie Chart".equals(actionEvent.getActionCommand())) {
				displayPieCharts();
			}
			if ("Text".equals(actionEvent.getActionCommand())) {
				displayTextViews();
			}
			
		}
	}

	public class EditNewsMakerNameListener implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Pie Chart".equals(actionEvent.getActionCommand())) {
				displayPieCharts();
			}
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
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			selectedMediaTypes = new LinkedList<NewsMedia>();
			if ("OK".equals(actionEvent.getActionCommand())) {
				if (mediaTypeSelectionView.jcbNewspaper.isSelected()) {
					selectedMediaTypes.add(NewsMedia.NEWSPAPER);
				}
				if (mediaTypeSelectionView.jcbTVNews.isSelected()) {
					selectedMediaTypes.add(NewsMedia.TV);
				}
				if (mediaTypeSelectionView.jcbOnline.isSelected()) {
					selectedMediaTypes.add(NewsMedia.ONLINE);
				}
				if (null == selectedMediaTypes) {
					JOptionPane.showMessageDialog(selectionView, "No media type selected.", "Invalid Selection",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			viewDialog.dispose();
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
		// Get the indices of the news makers selected in the selection view.
		int[] indices = selectionView.getSelectedNewsMakers();

		// If there are no selected news makers, alert the user and return.
		if (0 == indices.length) {
			JOptionPane.showMessageDialog(selectionView, "No newsmaker selected.", "Invalid Selection",
					JOptionPane.WARNING_MESSAGE);
		} else {
			// If there are selected news makers, go through the process for
			// each.
			NewsMakerListModel newsMakerListModel = this.newsDataBaseModel.getNewsMakerListModel();
			for (int index : indices) {
				NewsMakerModel newsMakerModel = newsMakerListModel.get(index);
				String newsMakerName = newsMakerModel.getName();

				// Get media types using MediaTypeSelectionView.
				this.selectedMediaTypes = null;
				this.mediaTypeSelectionView = new MediaTypeSelectionView();
				MediaTypeSelectionListener mediaTypeSelectionListener = new MediaTypeSelectionListener();
				this.mediaTypeSelectionView.jbOkay.addActionListener(mediaTypeSelectionListener);
				this.mediaTypeSelectionView.jbCancel.addActionListener(mediaTypeSelectionListener);

				this.viewDialog = new JDialog(selectionView, newsMakerName, true);
				this.viewDialog.add(mediaTypeSelectionView);
				this.viewDialog.setResizable(false);
				this.viewDialog.pack();
				this.viewDialog.setVisible(true);

				// If no media types were selected, go on to next news maker.
				if (null == this.selectedMediaTypes) {
					continue;
				}

				// Get content type using JOptionPane.
				NewsContent selectedNewsContent = null;
				selectedNewsContent = (NewsContent) JOptionPane.showInputDialog(selectionView,
						"Graph news stories based on which content?", newsMakerName, JOptionPane.PLAIN_MESSAGE, null,
						NewsContent.values(), NewsContent.TOPIC);
				if (null == selectedNewsContent) {
					continue;
				}

				// Get metric type using JOPtionPane.
				NewsMetric selectedNewsMetric = null;
				selectedNewsMetric = (NewsMetric) JOptionPane.showInputDialog(selectionView,
						"Graph news stories based on which metric?", newsMakerName, JOptionPane.PLAIN_MESSAGE, null,
						NewsMetric.values(), NewsMetric.LENGTH);
				if (null == selectedNewsMetric) {
					continue;
				}

				// Create the pie chart.
				PieChartView pieChartView = new PieChartView(newsMakerModel, selectedMediaTypes,
						selectedNewsContent.toString(), selectedNewsMetric.toString());

				// Make sure the pie chart listens for model changes so that it
				// can update itself.
				newsMakerModel.addActionListener(pieChartView);
			}
		}
	}

	private void displayTextViews() {

	}
}
