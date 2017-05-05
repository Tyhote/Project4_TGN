import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
	}

	private class FileMenuListener implements ActionListener {
		@Override
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
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Add NewsMaker".equals(actionEvent.getActionCommand())) {
				addNewsMaker();
			}
			if ("Edit NewsMaker".equals(actionEvent.getActionCommand())) {
				viewDialog = new JDialog();
				editNewsMakerView = new EditNewsMakerView(new NewsMakerModel(""), newsDataBaseModel);
				viewDialog.add(editNewsMakerView);
				viewDialog.pack();
				viewDialog.setVisible(true);
				// editNewsMakers();
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
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Add News Story".equals(actionEvent.getActionCommand())) {
				viewDialog = new JDialog();
				viewDialog.setTitle("Add News Story");
				addEditNewsStoryView = new AddEditNewsStoryView(newsDataBaseModel, editedNewsStory);
				viewDialog.add(addEditNewsStoryView);
				viewDialog.pack();
				viewDialog.setVisible(true);
			}
			if ("Edit News Story".equals(actionEvent.getActionCommand())) {

				// Get the indices of the news stories selected in the selection
				// view.
				int[] indices = selectionView.getSelectedNewsStories();
				if (0 == indices.length) {
					JOptionPane.showMessageDialog(selectionView, "No news stories selected.", "Invalid Selection",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// If there are selected news stories, go through the
					// process
					// for each.
					for (int index : indices) {
						editedNewsStory = newsDataBaseModel.getNewsStoryListModel().get(index);
						viewDialog = new JDialog();
						viewDialog.setTitle("Edit News Story");
						addEditNewsStoryView = new AddEditNewsStoryView(newsDataBaseModel, editedNewsStory);
						viewDialog.add(addEditNewsStoryView);
						viewDialog.pack();
						viewDialog.setVisible(true);
					}
				}

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
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Pie Chart".equals(actionEvent.getActionCommand())) {
				displayPieCharts();
			}
			if ("Text".equals(actionEvent.getActionCommand())) {
				displayTextViews();
			}
		}
	}

	// TODO
	public class EditNewsMakerNameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			int[] indices = selectionView.getSelectedNewsMakers();
			if (0 == indices.length) {
				JOptionPane.showMessageDialog(selectionView, "No news makers selected.", "Invalid Selection",
						JOptionPane.WARNING_MESSAGE);
			} else {
				// If there are selected news stories, go through the
				// process
				// for each.
				for (int index : indices) {
					viewDialog = new JDialog();
					viewDialog.setTitle("Edit " + newsDataBaseModel.getNewsMakerListModel().get(index).getName());
					editNewsMakerView = new EditNewsMakerView(newsDataBaseModel.getNewsMakerListModel().get(index),
							newsDataBaseModel);
					viewDialog.add(editNewsMakerView);
					viewDialog.pack();
					viewDialog.setVisible(true);
				}
			}

		}
	}

	// TODO
	public class RemoveNewsMakerFromNewStoriesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {

		}
	}

	public class AddEditNewsStoryListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if ("Add/Edit News Story".equals(actionEvent.getActionCommand())) {
				addNewsStory();
			}
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
		this.newsDataBaseModel = DBModel;

	}

	public void setSelectionView(SelectionView SView) {
		this.selectionView = SView;
		selectionView.registerFileMenuListener(new FileMenuListener());
		selectionView.registerNewsMakerMenuListener(new NewsMakerMenuListener());
		selectionView.registerNewsStoryMenuListener(new NewsStoryMenuListener());
		selectionView.registerDisplayMenuListener(new DisplayMenuListener());
		editedNewsStory = new NewspaperStory(LocalDate.of(2000, 1, 1), "", 0, "", "", new NewsMakerModel(""),
				new NewsMakerModel(""));
		selectedMediaTypes = new ArrayList<NewsMedia>();
	}

	private void loadNewsData() {
		JFileChooser fileChooser = new JFileChooser(".");
		int returnVal = fileChooser.showOpenDialog(selectionView);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fileName = null;
			try {
				fileName = fileChooser.getSelectedFile().getCanonicalPath();
				FileInputStream fileInputStream = new FileInputStream(fileName);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				newsDataBaseModel.none = (NewsMakerModel) objectInputStream.readObject();
				newsDataBaseModel.setNewsMakerListModel((NewsMakerListModel) objectInputStream.readObject());
				NewsStoryListModel stories = new NewsStoryListModel();
				for (int i = 0; i < newsDataBaseModel.getNewsMakerListModel().size(); i++) {
					for (int j = 0; j < newsDataBaseModel.getNewsMakerListModel().get(i).getNewsStoryListModel()
							.size(); j++) {
						stories.add(newsDataBaseModel.getNewsMakerListModel().get(i).getNewsStoryListModel().get(j));
					}
				}
				newsDataBaseModel.setNewsStoryListModel(stories);
				objectInputStream.close();
			} catch (ClassNotFoundException | IOException i) {
				JOptionPane.showMessageDialog(selectionView, "File not found", "Invalid Selection",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void saveNewsData() {
		JFileChooser fileChooser = new JFileChooser(".");
		int returnVal = fileChooser.showOpenDialog(selectionView);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fileName = null;
			try {
				fileName = fileChooser.getSelectedFile().getCanonicalPath();
				FileOutputStream fileOutputStream = new FileOutputStream(fileName);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(newsDataBaseModel.none);
				objectOutputStream.writeObject(newsDataBaseModel.getNewsMakerListModel());
				objectOutputStream.close();
			} catch (IOException i) {
				JOptionPane.showMessageDialog(selectionView, "Problem occurred Loading NewsData", "System Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void importNoozStories() {

		String sourceFile = null, topicFile = null, subjectFile = null, dataFile = null;
		List<String> possibilities = new ArrayList<String>();
		possibilities.add("Data File");
		possibilities.add("Source File");
		possibilities.add("Topic File");
		possibilities.add("Subject File");
		for (int i = 0; i < 4; i++) {

			JFileChooser fileChooser = new JFileChooser(".");
			int returnVal = fileChooser.showOpenDialog(selectionView);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String fileName = null;
				try {
					fileName = fileChooser.getSelectedFile().getCanonicalPath();
					String s = (String) JOptionPane.showInputDialog(viewDialog, "File Type", "File Chooser",
							JOptionPane.PLAIN_MESSAGE, null, possibilities.toArray(), "Data File");

					// Find Which file
					if ("Data File".equals(s)) {
						dataFile = fileName;
						possibilities.remove("Data File");
					} else if ("Source File".equals(s)) {
						sourceFile = fileName;
						possibilities.remove("Source File");
					} else if ("Topic File".equals(s)) {
						topicFile = fileName;
						possibilities.remove("Topic File");
					} else if ("Subject File".equals(s)) {
						subjectFile = fileName;
						possibilities.remove("Subject File");
					} else {
						return;
					}

				} catch (IOException | StringIndexOutOfBoundsException s) {
					JOptionPane.showMessageDialog(selectionView, "Wrong file Selection", "Invalid Selection",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		try {
			Map<String, String> sourceMap = CodeFileProcessor.readCodeFile(sourceFile);
			Map<String, String> topicMap = CodeFileProcessor.readCodeFile(topicFile);
			Map<String, String> subjectMap = CodeFileProcessor.readCodeFile(subjectFile);
			newsDataBaseModel = NoozFileProcessor.readNoozFile(dataFile, sourceMap, topicMap, subjectMap);
			selectionView.setNewsDataBaseModel(newsDataBaseModel);
			selectionView.enableAllMenus();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(selectionView, "Invalid File Choices", "Invalid File",
					JOptionPane.WARNING_MESSAGE);
		} catch (NullPointerException n) {
			JOptionPane.showMessageDialog(selectionView, "Invalid File Choices", "Invalid File",
					JOptionPane.WARNING_MESSAGE);
		} catch (StringIndexOutOfBoundsException i) {
			JOptionPane.showMessageDialog(selectionView, "Invalid File Choices", "Invalid File",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void exportNewsStories() {
		JFileChooser fileChooser = new JFileChooser(".");
		int returnVal = fileChooser.showOpenDialog(selectionView);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String fileName = null;
			try {
				fileName = fileChooser.getSelectedFile().getCanonicalPath();
				FileWriter outfile = new FileWriter(fileName);
				BufferedWriter bw = new BufferedWriter(outfile);
				String listOfStories = "";
				for (int i : selectionView.getSelectedNewsMakers()) {
					NewsStoryListModel stories = newsDataBaseModel.getNewsMakerListModel().get(i)
							.getNewsStoryListModel();
					listOfStories += newsDataBaseModel.getNewsMakerListModel().get(i).getName() + "\n";
					for (int j = 0; j < stories.size(); j++) {
						listOfStories += UserInterface.convertToOutputFormat(stories.get(j), NewsMedia.VALUES_LIST)
								+ "\n";
					}
					listOfStories += "\n";
				}
				bw.write(listOfStories);
				bw.newLine();
				bw.close();
			} catch (IOException i) {
				JOptionPane.showMessageDialog(selectionView, "Error Occured in writing file", "System Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void addNewsMaker() {

		viewDialog = new JDialog();
		String name = (String) JOptionPane.showInputDialog(selectionView, "News Maker's name?", "Add News Maker",
				JOptionPane.PLAIN_MESSAGE, null, null, "");

		// If a string was returned, say so.
		if ((name != null) && (name.length() > 0)) {
			if (!newsDataBaseModel.containsNewsMakerModel(new NewsMakerModel(name))) {
				newsDataBaseModel.addNewsMakerModel(new NewsMakerModel(name));
			} else {
				int n = JOptionPane.showConfirmDialog(selectionView, "News Maker already exists.\n Replace News Maker?",
						"Replace News Maker", JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					NewsMakerModel none = newsDataBaseModel.getNewsMakerListModel().get(new NewsMakerModel("None"));
					NewsMakerModel aux = newsDataBaseModel.getNewsMakerListModel().get(new NewsMakerModel(name));
					for (int i = 0; i < aux.getNewsStoryListModel().size(); i++) {
						NewsStory story = aux.getNewsStoryListModel().get(i);
						if (story.getNewsMaker1().equals(aux)) {
							story.setNewsMaker1(none);
						}
						if (story.getNewsMaker2().equals(aux)) {
							story.setNewsMaker2(none);
						}
						none.addNewsStory(story);
					}
					newsDataBaseModel.getNewsMakerListModel()
							.remove(newsDataBaseModel.getNewsMakerListModel().get(new NewsMakerModel(name)));
					newsDataBaseModel.addNewsMakerModel(new NewsMakerModel(name));
				} else {
					return;
				}
			}
		}
	}

	private void editNewsMakers() {
		newsDataBaseModel.getNewsMakerListModel().get(editNewsMakerView.newsMakerModel);
		newsDataBaseModel.addNewsMakerModel(new NewsMakerModel(editNewsMakerView.jtfName.getText()));
	}

	private void deleteNewsMakers() {
		int[] indices = selectionView.getSelectedNewsMakers();
		//Remove newsMakers from list
		for (int i : indices) {
			newsDataBaseModel.getNewsMakerListModel().remove(newsDataBaseModel.getNewsMakerListModel().get(i));
		}
		
		
	}

	private void deleteNewsMakerList() {
		
		newsDataBaseModel.removeAllNewsMakers();
		//Set all stories to none
		for (int i = 0; i < newsDataBaseModel.getNewsStoryListModel().size(); i++) {
			NewsMakerModel none = newsDataBaseModel.getNewsMakerListModel().get(new NewsMakerModel("None"));
			NewsStory story = newsDataBaseModel.getNewsStoryListModel().get(i);
			if (story.getNewsMaker1() != none) {
				story.setNewsMaker1(new NewsMakerModel("None"));
			}
			
			if (story.getNewsMaker2() != none) {
				story.setNewsMaker2(new NewsMakerModel("None"));
			}
			if (!newsDataBaseModel.getNewsMakerListModel().get(none).getNewsStoryListModel().contains(story)){
				newsDataBaseModel.getNewsMakerListModel().get(none).getNewsStoryListModel().add(story);
			}
		}
	}

	private void addNewsStory() {// TODO maybe a bug in the project
		int day = (int) addEditNewsStoryView.jcbNewsStoryDay.getSelectedItem();
		Month month = (Month) addEditNewsStoryView.jcbNewsStoryMonth.getSelectedItem();
		int monthInt = month.toInt();
		int year = (int) addEditNewsStoryView.jcbNewsStoryYear.getSelectedItem();

		String source = addEditNewsStoryView.jcbNewsStorySource.getSelectedItem().toString();
		String topic = addEditNewsStoryView.jcbNewsStoryTopic.getSelectedItem().toString();
		String subject = addEditNewsStoryView.jcbNewsStorySubject.getSelectedItem().toString();
		int length = Integer.parseInt(addEditNewsStoryView.jftfNewsStoryLength.getText());
		NewsMakerModel maker1 = new NewsMakerModel(
				addEditNewsStoryView.jcbNewsStoryNewsMaker1.getSelectedItem().toString());
		NewsMakerModel maker2 = new NewsMakerModel(
				addEditNewsStoryView.jcbNewsStoryNewsMaker2.getSelectedItem().toString());
		NewsMedia media = (NewsMedia) addEditNewsStoryView.jcbNewsStoryType.getSelectedItem();

		if (media == NewsMedia.NEWSPAPER) {
			editedNewsStory = new NewspaperStory(LocalDate.of(year, monthInt, day), source, length, topic, subject,
					maker1, maker2);
		} else if (media == NewsMedia.TV) {
			PartOfDay pod = (PartOfDay) addEditNewsStoryView.jcbNewsStoryPartOfDay.getSelectedItem();
			editedNewsStory = new TVNewsStory(LocalDate.of(year, monthInt, day), source, length, topic, subject, pod,
					maker1, maker2);
		} else if (media == NewsMedia.ONLINE) {
			PartOfDay pod = (PartOfDay) addEditNewsStoryView.jcbNewsStoryPartOfDay.getSelectedItem();
			editedNewsStory = new OnlineNewsStory(LocalDate.of(year, monthInt, day), source, length, topic, subject,
					pod, maker1, maker2);
		} else {
			System.err.println("There was an error with the media portion of add news story");
			System.exit(0);
		}
	}

	private void editNewsStories() {// TODO
		int day = (int) addEditNewsStoryView.jcbNewsStoryDay.getSelectedItem();
		Month month = (Month) addEditNewsStoryView.jcbNewsStoryMonth.getSelectedItem();
		int monthInt = month.toInt();
		int year = (int) addEditNewsStoryView.jcbNewsStoryYear.getSelectedItem();

		String source = addEditNewsStoryView.jcbNewsStorySource.getSelectedItem().toString();
		String topic = addEditNewsStoryView.jcbNewsStoryTopic.getSelectedItem().toString();
		String subject = addEditNewsStoryView.jcbNewsStorySubject.getSelectedItem().toString();
		int length = Integer.parseInt(addEditNewsStoryView.jftfNewsStoryLength.getText());
		NewsMakerModel maker1 = new NewsMakerModel(
				addEditNewsStoryView.jcbNewsStoryNewsMaker1.getSelectedItem().toString());
		NewsMakerModel maker2 = new NewsMakerModel(
				addEditNewsStoryView.jcbNewsStoryNewsMaker2.getSelectedItem().toString());
		NewsMedia media = (NewsMedia) addEditNewsStoryView.jcbNewsStoryType.getSelectedItem();

		if (media == NewsMedia.NEWSPAPER) {
			editedNewsStory = new NewspaperStory(LocalDate.of(year, monthInt, day), source, length, topic, subject,
					maker1, maker2);
		} else if (media == NewsMedia.TV) {
			PartOfDay pod = (PartOfDay) addEditNewsStoryView.jcbNewsStoryPartOfDay.getSelectedItem();
			editedNewsStory = new TVNewsStory(LocalDate.of(year, monthInt, day), source, length, topic, subject, pod,
					maker1, maker2);
		} else if (media == NewsMedia.ONLINE) {
			PartOfDay pod = (PartOfDay) addEditNewsStoryView.jcbNewsStoryPartOfDay.getSelectedItem();
			editedNewsStory = new OnlineNewsStory(LocalDate.of(year, monthInt, day), source, length, topic, subject,
					pod, maker1, maker2);
		} else {
			System.err.println("There was an error with the media portion of add news story");
			System.exit(0);
		}
	}

	private void sortNewsStories() {
		NewsStoryListModel model = newsDataBaseModel.getNewsStoryListModel();
		NewsStory[] stories = new NewsStory[model.size()];
		for (int i = 0; i < stories.length; i++) {
			stories[i] = model.get(i);
		}

		viewDialog = new JDialog();
		Object[] possibilities = SortCriterion.values();
		SortCriterion s = (SortCriterion) JOptionPane.showInputDialog(viewDialog,
				"Which sort criteria would you like to choose?", "Sort criteria", JOptionPane.PLAIN_MESSAGE, null,
				possibilities, SortCriterion.SOURCE);

		if (s == SortCriterion.SOURCE) {
			Arrays.sort(stories, SourceComparator.SOURCE_COMPARATOR);
		} else if (s == SortCriterion.TOPIC) {
			Arrays.sort(stories);
		} else if (s == SortCriterion.LENGTH) {
			Arrays.sort(stories, LengthComparator.LENGTH_COMPARATOR);
		} else if (s == SortCriterion.DATE_TIME) {
			Arrays.sort(stories, DateComparator.DATE_COMPARATOR);
		} else if (s == SortCriterion.SUBJECT) {
			Arrays.sort(stories, SubjectComparator.SUBJECT_COMPARATOR);
		} else {
			return;
		}

		newsDataBaseModel.setNewsStoryListModelFromArray(stories);
	}

	private void deleteNewsStories() {
		int[] indices = selectionView.getSelectedNewsMakers();
		for (int i : indices) {
			NewsMakerModel model = newsDataBaseModel.getNewsMakerListModel().get(i);
			for (int j = 0; j < model.getNewsStoryListModel().size(); j++) {
				model.removeNewsStory(model.getNewsStoryListModel().get(j));
			}
		}
	}

	private void deleteAllNewsStories() {
		for (int i = 0; i < newsDataBaseModel.getNewsMakerListModel().size(); i++) {
			NewsMakerModel model = newsDataBaseModel.getNewsMakerListModel().get(i);
			for (int j = 0; j < model.getNewsStoryListModel().size(); j++) {
				model.removeNewsStory(model.getNewsStoryListModel().get(j));
			}
		}
		newsDataBaseModel.getNewsStoryListModel().removeListOfNewsStories(newsDataBaseModel.getNewsStories());
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
						"Display news stories based on which content?", newsMakerName, JOptionPane.PLAIN_MESSAGE, null,
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

				List<SortCriterion> sortCriteria = new ArrayList<SortCriterion>();
				List<SortCriterion> sortCriteriaOptions = new ArrayList<SortCriterion>();
				
				// Add all of the SortCriterion options to the sortCriteriaOptions
				for (SortCriterion sc : SortCriterion.values())
				{
					sortCriteriaOptions.add(sc);
				}

				for (int sortCriterionIndex = 0; sortCriterionIndex <= 3; ++sortCriterionIndex) {
					String fancyWord = "";
					switch (sortCriterionIndex) {
					case 0: {
						fancyWord = "Primary ";
						break;
					}
					case 1: {
						fancyWord = "Secondary ";
						break;
					}
					case 2: {
						fancyWord = "Tertiary ";
						break;
					}
					case 3: {
						fancyWord = "Quaternary ";
						break;
					}
					}

					// Get sort criterion using JOptionPane.
					SortCriterion sortCriterion = null;
					sortCriterion = (SortCriterion) JOptionPane.showInputDialog(selectionView,
							fancyWord + "criterion to sort news stories?", newsMakerName, JOptionPane.PLAIN_MESSAGE,
							null, sortCriteriaOptions.toArray(), SortCriterion.SOURCE);
					
					sortCriteriaOptions.remove(sortCriterion);

					if (null == sortCriterion) {
						continue;
					}
				}

				for (SortCriterion sortCriterion : SortCriterion.values()) {
					if (!sortCriteria.contains(sortCriterion)) {
						sortCriteria.add(sortCriterion);
						break;
					}
				}

				// Create the text view.
				TextView textView = new TextView(newsMakerModel, selectedMediaTypes, sortCriteria);

				// Make sure the text view listens for model changes so that it
				// can update itself.
				newsMakerModel.addActionListener(textView);
			}
		}
	}
}
