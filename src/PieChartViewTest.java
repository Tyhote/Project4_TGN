import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PieChartViewTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	public static void main(String[] args)
	{
		NewsMakerModel testNewsMaker = new NewsMakerModel("Bob");
		NewspaperStory testStory = new NewspaperStory(LocalDate.of(19, 8, 20), "TMZ", 485, "Stuff", "Things",
				new NewsMakerModel("Bob"), new NewsMakerModel("Norman Bates"));
		testNewsMaker.addNewsStory(testStory);
		List<NewsMedia> testMedia = new ArrayList<NewsMedia>();
		testMedia.add(NewsMedia.NEWSPAPER);
		String testContent = "Source";
		String testMeasure = "Length";
		
		PieChartView testPieChartView = new PieChartView(testNewsMaker, testMedia, testContent, testMeasure);
	}

}
