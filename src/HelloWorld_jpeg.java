import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class HelloWorld_jpeg {

	public static void main(String[] args) {

		NewsDataBaseModel db = getData();
		NewsMakerListModel ml = db.getNewsMakerListModel();
		NewsMakerModel mm = ml.getExactMatch("ThisThingy");
		NewsStory ns = mm.getNewsStoryListModel().get(0);

		List<NewsMedia> newsMedia = new ArrayList<NewsMedia>();
		newsMedia.add(NewsMedia.NEWSPAPER);
		newsMedia.add(NewsMedia.TV);
		newsMedia.add(NewsMedia.ONLINE);

		List<SortCriterion> sortCriteria = new ArrayList<SortCriterion>();
		sortCriteria.add(SortCriterion.SOURCE);
		sortCriteria.add(SortCriterion.TOPIC);
		sortCriteria.add(SortCriterion.SUBJECT);
		sortCriteria.add(SortCriterion.LENGTH);

		String content = NewsContent.SOURCE.toString();

		String measure = NewsMetric.COUNT.toString();

		TextView tv = new TextView(mm, newsMedia, sortCriteria);
		SelectionView sv = new SelectionView();
		sv.setNewsDataBaseModel(db);

		JFrame mtsf = new JFrame("MEDIA TYPE SELECTION VIEW");
		MediaTypeSelectionView mt = new MediaTypeSelectionView();
		mtsf.add(mt);
		mtsf.pack();
		mtsf.setVisible(true);

		JFrame aensf = new JFrame();
		AddEditNewsStoryView aens = new AddEditNewsStoryView(db, ns);
		aensf.add(aens);
		aensf.pack();
		aensf.setVisible(true);

		JFrame enmf = new JFrame();
		EditNewsMakerView enm = new EditNewsMakerView(mm, db);
		enmf.add(enm);
		enmf.pack();
		enmf.setVisible(true);

		PieChartView pc = new PieChartView(mm, newsMedia, content, measure);
	}

	private static NewsDataBaseModel getData() {

		char letter1 = 'A', letter2 = 'A', letter3 = 'A', letter4 = 'A', letter5 = 'A', letter6 = 'A', letter7 = 'A',
				letter8 = 'A', letter9 = 'A', letter10 = 'a', letter11 = 'a', letter12 = 'a';
		int length = 0;

		NewsDataBaseModel model = new NewsDataBaseModel();
		NewsMakerListModel makerList = new NewsMakerListModel();
		NewsStoryListModel storyList = new NewsStoryListModel();
		NewsMakerModel one = new NewsMakerModel("ThisThingy");

		for (int i = 0; i < 50; i++) {

			StringBuilder newsMaker = new StringBuilder("");

			// NewsMakerModel
			newsMaker.append(letter1 + "" + letter2 + "" + letter3 + "" + letter4 + "" + letter5 + "" + letter6 + ""
					+ letter7 + "" + letter8 + "" + letter9);
			if (letter1 != 'H')
				letter1++;
			else {
				if (letter2 != 'H')
					letter2++;
				else {
					if (letter3 != 'H')
						letter3++;
					else {
						if (letter4 != 'H')
							letter4++;
						else {
							if (letter5 != 'H')
								letter5++;
							else {
								if (letter6 != 'H')
									letter6++;
								else {
									if (letter7 != 'H')
										letter7++;
									else {
										if (letter8 != 'H')
											letter8++;
										else {
											if (letter9 != 'H')
												letter9++;
											else {
												throw new IllegalArgumentException();
											}
										}
									}
								}
							}
						}
					}
				}
			}
			NewsMakerModel two = new NewsMakerModel(newsMaker.toString());
			makerList.add(two);

			int year = 1950;
			int month = 1;
			int day = 1;
			for (int j = 0; j < 50; j++) {

				// Date
				LocalDate date = LocalDate.of(year, month, day);
				year++;
				if (month == 12)
					month = 0;
				month++;
				if (day == 25)
					day = 0;
				day++;

				// Source
				if (j % 10 != 0)
					letter10++;
				StringBuilder source = new StringBuilder("" + letter10 + "");

				// Length
				length++;

				// Topic
				if (j % 10 != 0)
					letter11++;
				StringBuilder topic = new StringBuilder("" + letter11 + "");

				// Subject
				if (j % 10 != 0)
					letter12++;
				StringBuilder subject = new StringBuilder("" + letter12 + "");

				// Add story to model
				if (j % 3 == 0) {
					storyList.add(new NewspaperStory(date, source.toString(), length, topic.toString(),
							subject.toString(), one, two));
				} else if (j % 3 == 1) {
					storyList.add(new TVNewsStory(date, source.toString(), length, topic.toString(), subject.toString(),
							PartOfDay.AFTERNOON, one, two));
				} else {
					storyList.add(new OnlineNewsStory(date, source.toString(), length, topic.toString(),
							subject.toString(), PartOfDay.AFTERNOON, one, two));
				}
				try {
					one.addNewsStory(storyList.get(j));
				} catch (IllegalArgumentException e) {
					System.out.println(".");
					//e.printStackTrace();
				}
				try {
					two.addNewsStory(storyList.get(j));
				} catch (IllegalArgumentException e) {
					System.out.println(".");
					//e.printStackTrace();
				}
			}
			try {
				makerList.add(two);
			} catch (IllegalArgumentException e) {
				System.out.println(".");
				//e.printStackTrace();
			}
		}
		letter10 = 'a';
		letter11 = 'a';
		letter12 = 'a';
		makerList.add(one);
		model.setNewsMakerListModel(makerList);
		model.setNewsStoryListModel(storyList);
		
		one.addNewsStory(new NewspaperStory(LocalDate.of(1, 1, 1), "TMZZZZZZZ", 12345, "GOOD STUFF AND MORE", "QUALITY THINGS HERE", 
				one, one));
		
		return model;
	}
}
