import java.util.ArrayList;
import java.util.List;

public class HelloWorld_jpeg {

	public static void main(String[] args) {
		
		NewsMakerModel model = new NewsMakerModel("Barry");
		NewsStoryListModel list = new NewsStoryListModel();
		model.setNewsStoryListModel(list);
		NewsStory ps = new NewspaperStory(NoozFileProcessor.decodeDate("20120102"), "source1", 3, "bottom", "sub", model, new NewsMakerModel("Jim"));
		model.addNewsStory(ps);
		ps = new TVNewsStory(NoozFileProcessor.decodeDate("20120102"), "source2", 1, "top", "Hoagie" , PartOfDay.MORNING, model, new NewsMakerModel("Jim"));
		model.addNewsStory(ps);
		ps = new OnlineNewsStory(NoozFileProcessor.decodeDate("20120102"), "source3", 2, "top", "Wheat Bread" , PartOfDay.AFTERNOON, model, new NewsMakerModel("Jim"));
		model.addNewsStory(ps);
		
		List<NewsMedia> newsMedia = new ArrayList<NewsMedia>();
		newsMedia.add(NewsMedia.NEWSPAPER);
		newsMedia.add(NewsMedia.TV);
		newsMedia.add(NewsMedia.ONLINE);
		
		List<SortCriterion> sortCriteria = new ArrayList<SortCriterion>();
		sortCriteria.add(SortCriterion.SOURCE);
		sortCriteria.add(SortCriterion.TOPIC);
		sortCriteria.add(SortCriterion.SUBJECT);
		sortCriteria.add(SortCriterion.LENGTH);
		
		TextView tv = new TextView(model, newsMedia, sortCriteria);
	}

}
