import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;

import java.util.TreeMap;

import org.junit.Test;

public class NewsDataBaseModelTest {

	// 1
	@Test
	public void noArgConstructorTest() {
		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		if (newsDataBaseModel.getNewsMakerListModel() == null) {
			fail("NewsMakerListModel is null.");
		}
		if (newsDataBaseModel.getNewsStoryListModel() == null) {
			fail("NewsStoryListModel is null.");
		}
		if (newsDataBaseModel.none == null) {
			fail("Newsmaker \"none\" is null.");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsDataBaseModel.none)) {
			fail("NewsDataBaseModel does not contain \"none\".");
		}
		if (newsDataBaseModel.getNewsMakerListModel().size() != 1) {
			fail("NewsDataBaseModel has wrong size.");
		}
		// return true;
	}

	// 2
	@Test
	public void parameterizedConstructorTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);

		NewsStory newsStory = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20, "Interesting Topic",
				"Interesting Subject", newsMakerModel1, newsMakerModel2);
		newsStoryListModel.add(newsStory);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (newsDataBaseModel.getNewsMakerListModel() == null) {
			fail("NewsMakerListModel is null.");
		}
		if (newsDataBaseModel.getNewsStoryListModel() == null) {
			fail("NewsStoryListModel is null.");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel1)) {
			fail("NewsDataBaseModel does not contain first newsmaker added.");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel2)) {
			fail("NewsDataBaseModel does not contain second newsmaker added.");
		}
		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory)) {
			fail("NewsDataBaseModel does not contain news story added.");
		}
		// return true;
	}

	// 3
	@Test
	public void setGetNewsSourceMapTest() {
		Map<String, String> map1 = new TreeMap<String, String>();
		map1.put("k1", "v1");
		map1.put("k2", "v2");

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsSourceMap(map1);

		Map<String, String> map2 = newsDataBaseModel.getNewsSourceMap();
		if (map1.size() != map2.size()) {
			fail("Set map and returned map of different sizes.");
		}
		for (Entry<String, String> entry : map1.entrySet()) {
			if (!map2.entrySet().contains(entry)) {
				fail("Returned map missing entry from set map.");
			}
		}
	}

	// 4
	@Test
	public void setGetNewsTopicMapTest() {
		Map<String, String> map1 = new TreeMap<String, String>();
		map1.put("k1", "v1");
		map1.put("k2", "v2");

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsTopicMap(map1);

		Map<String, String> map2 = newsDataBaseModel.getNewsTopicMap();
		if (map1.size() != map2.size()) {
			fail("Set map and returned map of different sizes.");
		}
		for (Entry<String, String> entry : map1.entrySet()) {
			if (!map2.entrySet().contains(entry)) {
				fail("Returned map missing entry from set map.");
			}
		}
		// return true;
	}

	// 5
	@Test
	public void setGetNewsSubjectMapTest() {
		Map<String, String> map1 = new TreeMap<String, String>();
		map1.put("k1", "v1");
		map1.put("k2", "v2");

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsSubjectMap(map1);

		Map<String, String> map2 = newsDataBaseModel.getNewsSubjectMap();
		if (map1.size() != map2.size()) {
			fail("Set map and returned map of different sizes.");
		}
		for (Entry<String, String> entry : map1.entrySet()) {
			if (!map2.entrySet().contains(entry)) {
				fail("Returned map missing entry from set map.");
			}
		}
		// return true;
	}

	// 6
	@Test
	public void setGetNewsSourceArrayTest() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("k1", "v1");
		map.put("k2", "v2");

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsSourceMap(map);

		String[] array = newsDataBaseModel.getNewsSources();
		if (map.size() != array.length) {
			fail("Set map and returned array of different sizes.");
		}
		for (String string : map.values()) {
			if (!Arrays.asList(array).contains(string)) {
				fail("Returned array missing value from map.");
			}
		}
		// return true;
	}

	// 7
	@Test
	public void setGetNewsTopicArrayTest() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("k1", "v1");
		map.put("k2", "v2");

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsTopicMap(map);

		String[] array = newsDataBaseModel.getNewsTopics();
		if (map.size() != array.length) {
			fail("Set map and returned array of different sizes.");
		}
		for (String string : map.values()) {
			if (!Arrays.asList(array).contains(string)) {
				fail("Returned array missing value from map.");
			}
		}
		// return true;
	}

	// 8
	@Test
	public void setGetNewsSubjectArrayTest() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("k1", "v1");
		map.put("k2", "v2");

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsSubjectMap(map);

		String[] array = newsDataBaseModel.getNewsSubjects();
		if (map.size() != array.length) {
			fail("Set map and returned array of different sizes.");
		}
		for (String string : map.values()) {
			if (!Arrays.asList(array).contains(string)) {
				fail("Returned array missing value from map.");
			}
		}
		// return true;
	}

	// 9
	@Test
	public void newsMakerListIsEmptyTest() {
		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsMakerListModel(new NewsMakerListModel());
		if (!newsDataBaseModel.newsMakerListIsEmpty()) {
			fail("Empty NewsMakerList not considered empty.");
		}
		newsDataBaseModel.addNewsMakerModel(new NewsMakerModel("Interesting Newsmaker"));
		if (newsDataBaseModel.newsMakerListIsEmpty()) {
			fail("Non-empty NewsMakerList considered empty.");
		}
		// return true;
	}

	// 10
	@Test
	public void containsNewsMakerModelTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		newsMakerListModel.add(newsMakerModel1);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (!newsDataBaseModel.containsNewsMakerModel(newsMakerModel1)) {
			fail("Added NewsMakerModel claimed not found by containsNewsMakerModel.");
		}
		if (newsDataBaseModel.containsNewsMakerModel(newsMakerModel2)) {
			fail("Missing NewsMakerModel claimed found by containsNewsMakerModel.");
		}
		// return true;
	}

	// 11
	@Test
	public void getNewsMakerNamesTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		String[] newsMakerNames = newsDataBaseModel.getNewsMakerNames();

		if (newsMakerNames == null) {
			fail("Array of names returned is null.");
		}
		if (newsMakerNames.length != 2 && newsMakerNames.length != 3) {
			fail("Array returned wrong size: " + newsMakerNames.length + ".");
		}

		boolean found = false;
		for (int i = 0; i < newsMakerNames.length; i++) {
			if (newsMakerNames[i].equals(newsMakerModel1.getName())) {
				found = true;
				break;
			}
		}
		if (!found) {
			fail("Added NewsMakerModel not in returned array.");
		}

		found = false;
		for (int i = 0; i < newsMakerNames.length; i++) {
			if (newsMakerNames[i].equals(newsMakerModel2.getName())) {
				found = true;
				break;
			}
		}
		if (!found) {
			fail("Added NewsMakerModel not in returned array.");
		}

		if (newsMakerNames.length == 3) {
			found = false;
			for (int i = 0; i < newsMakerNames.length; i++) {
				if (newsMakerNames[i].equals("None")) {
					found = true;
					break;
				}
			}
			if (!found) {
				fail("Extraneous NewsMakerModel in returned array.");
			}
		}
		// return true;
	}

	// 12
	@Test
	public void getNewsMakersTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		DefaultListModel<NewsMakerModel> newsMakers = newsDataBaseModel.getNewsMakers();

		if (newsMakers == null) {
			fail("DefaultListModel returned is null.");
		}
		if (newsMakers.size() != 2 && newsMakers.size() != 3) {
			fail("DefaultListModel returned wrong size: " + newsMakers.size() + ".");
		}

		if (!newsMakers.contains(newsMakerModel1)) {
			fail("Added NewsMakerModel not in returned DefaultListModel.");
		}

		if (!newsMakers.contains(newsMakerModel2)) {
			fail("Added NewsMakerModel not in returned DefaultListModel.");
		}

		if (newsMakers.size() == 3) {
			if (!newsMakers.contains(new NewsMakerModel())) {
				fail("Extraneous NewsMakerModel in returned DefaultListModel.");
			}
		}
		// return true;
	}

	// 13
	@Test
	public void replaceNewsMakerModelTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);

		NewsStory newsStory = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20, "Interesting Topic",
				"Interesting Subject", newsMakerModel1, newsMakerModel2);
		newsMakerModel1.addNewsStory(newsStory);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (!newsDataBaseModel.getNewsMakerListModel().get(newsMakerModel1).getNewsStoryListModel()
				.contains(newsStory)) {
			fail("News story missing from news maker.");
		}

		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 1");
		newsDataBaseModel.replaceNewsMakerModel(newsMakerModel3);

		if (newsDataBaseModel.getNewsMakerListModel().get(newsMakerModel1).getNewsStoryListModel()
				.contains(newsStory)) {
			fail("News maker not replaced.");
		}
		// return true;
	}

	// 14
	@Test
	public void removeNewsMakersBasicTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 3");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);
		newsMakerListModel.add(newsMakerModel3);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel1)) {
			fail("News maker not found in list.");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel2)) {
			fail("News maker not found in list.");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel2)) {
			fail("News maker not found in list.");
		}

		DefaultListModel<NewsMakerModel> removalListModel1 = new DefaultListModel<NewsMakerModel>();
		removalListModel1.addElement(newsMakerModel2);

		newsDataBaseModel.removeNewsMakers(removalListModel1);
		if (newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel2)) {
			fail("News maker not removed from list.");
		}

		DefaultListModel<NewsMakerModel> removalListModel2 = new DefaultListModel<NewsMakerModel>();
		removalListModel2.addElement(newsMakerModel1);
		removalListModel2.addElement(newsMakerModel3);

		newsDataBaseModel.removeNewsMakers(removalListModel2);
		if (newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel1)) {
			fail("News maker not removed from list.");
		}
		if (newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel3)) {
			fail("News maker not removed from list.");
		}
		// return true;
	}

	// 15
	@Test
	public void removeNewsMakersAdvancedTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 3");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);
		newsMakerListModel.add(newsMakerModel3);

		NewsStory newsStory = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20, "Interesting Topic",
				"Interesting Subject", newsMakerModel1, newsMakerModel3);
		newsMakerModel1.addNewsStory(newsStory);
		newsMakerModel3.addNewsStory(newsStory);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (!newsStory.getNewsMaker1().equals(newsMakerModel1)) {
			fail("News maker not properly added to story");
		}
		if (!newsStory.getNewsMaker2().equals(newsMakerModel3)) {
			fail("News maker not properly added to story");
		}

		DefaultListModel<NewsMakerModel> removalListModel = new DefaultListModel<NewsMakerModel>();
		removalListModel.addElement(newsMakerModel1);
		removalListModel.addElement(newsMakerModel3);

		newsDataBaseModel.removeNewsMakers(removalListModel);

		if (!newsStory.getNewsMaker1().equals(new NewsMakerModel())) {
			fail("News maker not properly removed from story");
		}
		if (!newsStory.getNewsMaker2().equals(new NewsMakerModel())) {
			fail("News maker not properly removed from story");
		}
		// return true;
	}

	// 16
	@Test
	public void removeAllNewsMakersBasicTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 3");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);
		newsMakerListModel.add(newsMakerModel3);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel1)) {
			fail("News maker not found in list.");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel2)) {
			fail("News maker not found in list.");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel2)) {
			fail("News maker not found in list.");
		}

		newsDataBaseModel.removeAllNewsMakers();
		if (newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel1)) {
			fail("News maker not removed from list.");
		}
		if (newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel2)) {
			fail("News maker not removed from list.");
		}
		if (newsDataBaseModel.getNewsMakerListModel().contains(newsMakerModel3)) {
			fail("News maker not removed from list.");
		}
		// return true;
	}

	// 17
	@Test
	public void removeAllNewsMakersAdvancedTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 3");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);
		newsMakerListModel.add(newsMakerModel3);

		NewsStory newsStory1 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel3);
		newsMakerModel1.addNewsStory(newsStory1);
		newsMakerModel3.addNewsStory(newsStory1);

		NewsStory newsStory2 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		newsMakerModel1.addNewsStory(newsStory2);
		newsMakerModel2.addNewsStory(newsStory2);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (!newsStory1.getNewsMaker1().equals(newsMakerModel1)) {
			fail("News maker not properly added to story");
		}
		if (!newsStory1.getNewsMaker2().equals(newsMakerModel3)) {
			fail("News maker not properly added to story");
		}

		if (!newsStory2.getNewsMaker1().equals(newsMakerModel1)) {
			fail("News maker not properly added to story");
		}
		if (!newsStory2.getNewsMaker2().equals(newsMakerModel2)) {
			fail("News maker not properly added to story");
		}

		newsDataBaseModel.removeAllNewsMakers();

		if (!newsStory1.getNewsMaker1().equals(new NewsMakerModel())) {
			fail("News maker not properly removed from story");
		}
		if (!newsStory1.getNewsMaker2().equals(new NewsMakerModel())) {
			fail("News maker not properly removed from story");
		}

		if (!newsStory2.getNewsMaker1().equals(new NewsMakerModel())) {
			fail("News maker not properly removed from story");
		}
		if (!newsStory2.getNewsMaker2().equals(new NewsMakerModel())) {
			fail("News maker not properly removed from story");
		}
		// return true;
	}

	// 18
	@Test
	public void sortNewsMakerListModelTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 3");
		newsMakerListModel.add(newsMakerModel3);
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		newsDataBaseModel.sortNewsMakerListModel();

		System.out.println();
		
		if (!newsDataBaseModel.getNewsMakerListModel().get(0).equals(newsMakerModel1)) {
			fail("Sort newsmaker list failure");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().get(1).equals(newsMakerModel2)) {
			fail("Sort newsmaker list failure");
		}
		if (!newsDataBaseModel.getNewsMakerListModel().get(2).equals(newsMakerModel3)) {
			fail("Sort newsmaker list failure");
		}
	}

	// 19
	@Test
	public void newsMakerStoryIsEmptyTest() {
		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.setNewsStoryListModel(new NewsStoryListModel());
		if (!newsDataBaseModel.newsStoryListIsEmpty()) {
			fail("Empty NewsStoryList not considered empty.");
		}
		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		newsDataBaseModel.addNewsStory(new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2));
		if (newsDataBaseModel.newsStoryListIsEmpty()) {
			fail("Non-empty NewsStoryList considered empty.");
		}
		// return true;
	}

	// 20
	@Test
	public void containsNewsStoryTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		NewsStory newsStory1 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		newsDataBaseModel.addNewsStory(newsStory1);

		NewsStory newsStory2 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 200,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);

		if (!newsDataBaseModel.containsNewsStory(newsStory1)) {
			fail("Added NewsStory claimed not found by containsNewsStory.");
		}
		if (newsDataBaseModel.containsNewsStory(newsStory2)) {
			fail("Missing NewsStory claimed found by containsNewsStory.");
		}
		// return true;
	}

	// 21
	@Test
	public void getNewsStoriesTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);

		NewsStory newsStory1 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		NewsStory newsStory2 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 200,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		newsStoryListModel.add(newsStory1);
		newsStoryListModel.add(newsStory2);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		DefaultListModel<NewsStory> newsStories = newsDataBaseModel.getNewsStories();

		if (newsStories == null) {
			fail("DefaultListModel returned is null.");
		}
		if (newsStories.size() != 2) {
			fail("DefaultListModel returned wrong size: " + newsStories.size() + ".");
		}

		if (!newsStories.contains(newsStory1)) {
			fail("Added NewsStory not in returned DefaultListModel.");
		}

		if (!newsStories.contains(newsStory2)) {
			fail("Added NewsStory not in returned DefaultListModel.");
		}

		// return true;
	}

	// 22
	@Test
	public void setNewsStoryListModelFromArrayTest() {
		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();

		NewsStory[] newsStories1 = new NewsStory[0];
		newsDataBaseModel.setNewsStoryListModelFromArray(newsStories1);
		if (!newsDataBaseModel.newsStoryListIsEmpty()) {
			fail("Empty NewsStoryList not considered empty.");
		}

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsStory newsStory1 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		NewsStory newsStory2 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 200,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		NewsStory[] newsStories2 = new NewsStory[2];
		newsStories2[0] = newsStory1;
		newsStories2[1] = newsStory2;
		newsDataBaseModel.setNewsStoryListModelFromArray(newsStories2);
		if (newsDataBaseModel.newsStoryListIsEmpty()) {
			fail("Non-empty NewsStoryList considered empty.");
		}
		// return true;
	}

	// 23
	@Test
	public void removeNewsStoriesTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 3");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);
		newsMakerListModel.add(newsMakerModel3);

		NewsStory newsStory1 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		NewsStory newsStory2 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 200,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel3);
		NewsStory newsStory3 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 200,
				"Interesting Topic", "Interesting Subject", newsMakerModel2, newsMakerModel3);
		newsStoryListModel.add(newsStory1);
		newsStoryListModel.add(newsStory2);
		newsStoryListModel.add(newsStory3);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory1)) {
			fail("News story not found in list.");
		}
		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory2)) {
			fail("News story not found in list.");
		}
		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory3)) {
			fail("News story not found in list.");
		}

		DefaultListModel<NewsStory> removalListModel1 = new DefaultListModel<NewsStory>();
		removalListModel1.addElement(newsStory2);

		newsDataBaseModel.removeNewsStories(removalListModel1);
		if (newsDataBaseModel.getNewsStoryListModel().contains(newsStory2)) {
			fail("News story not removed from list.");
		}
		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory1)) {
			fail("News story improperly removed from list.");
		}
		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory3)) {
			fail("News story improperly removed from list.");
		}

		DefaultListModel<NewsStory> removalListModel2 = new DefaultListModel<NewsStory>();
		removalListModel2.addElement(newsStory1);
		removalListModel2.addElement(newsStory3);

		newsDataBaseModel.removeNewsStories(removalListModel2);
		if (newsDataBaseModel.getNewsStoryListModel().contains(newsStory1)) {
			fail("News maker not removed from list.");
		}
		if (newsDataBaseModel.getNewsStoryListModel().contains(newsStory3)) {
			fail("News maker not removed from list.");
		}
		// return true;
	}

	// 24
	@Test
	public void removeAllNewsStoriesTest() {
		NewsMakerListModel newsMakerListModel = new NewsMakerListModel();
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();

		NewsMakerModel newsMakerModel1 = new NewsMakerModel("Interesting Newsmaker 1");
		NewsMakerModel newsMakerModel2 = new NewsMakerModel("Interesting Newsmaker 2");
		NewsMakerModel newsMakerModel3 = new NewsMakerModel("Interesting Newsmaker 3");
		newsMakerListModel.add(newsMakerModel1);
		newsMakerListModel.add(newsMakerModel2);
		newsMakerListModel.add(newsMakerModel3);

		NewsStory newsStory1 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel2);
		NewsStory newsStory2 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 200,
				"Interesting Topic", "Interesting Subject", newsMakerModel1, newsMakerModel3);
		NewsStory newsStory3 = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 200,
				"Interesting Topic", "Interesting Subject", newsMakerModel2, newsMakerModel3);
		newsStoryListModel.add(newsStory1);
		newsStoryListModel.add(newsStory2);
		newsStoryListModel.add(newsStory3);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel(newsMakerListModel, newsStoryListModel);

		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory1)) {
			fail("News story not found in list.");
		}
		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory2)) {
			fail("News story not found in list.");
		}
		if (!newsDataBaseModel.getNewsStoryListModel().contains(newsStory3)) {
			fail("News story not found in list.");
		}

		newsDataBaseModel.removeAllNewsStories();
		if (newsDataBaseModel.getNewsStoryListModel().contains(newsStory2)) {
			fail("News story not removed from list.");
		}
		if (newsDataBaseModel.getNewsStoryListModel().contains(newsStory1)) {
			fail("News maker not removed from list.");
		}
		if (newsDataBaseModel.getNewsStoryListModel().contains(newsStory3)) {
			fail("News maker not removed from list.");
		}
		// return true;
	}
}