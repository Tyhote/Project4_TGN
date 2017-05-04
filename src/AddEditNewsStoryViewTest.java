
import java.time.LocalDate;

import javax.swing.JFrame;

import org.junit.Test;

//JUnit TestCase for testing AddEditNewsStoryView
public class AddEditNewsStoryViewTest {

	@Test
	public void test() {
		System.out.println("Begin test of AddEditNewsStoryView");

		NewspaperStory testStory = new NewspaperStory(LocalDate.of(19, 8, 20), "TMZ", 485, "Stuff", "Things",
				new NewsMakerModel("Rodney Bates"), new NewsMakerModel("Norman Bates"));

		NewsDataBaseModel testDataBase = new NewsDataBaseModel();

		AddEditNewsStoryView testView = new AddEditNewsStoryView(testDataBase, testStory);

		JFrame frame = new JFrame();
		frame.add(testView);
		frame.setVisible(true);

		// fail("Not yet implemented");
	}

	public static void main(String[] args) {
		// NOTE: This will fail unless you comment out the parts of the code in
		// AddEdit... that refers to the database's
		// arrays of sources, topics, subjects, and newsmakers
		System.out.println("Begin test of AddEditNewsStoryView");

		NewspaperStory testStory = new NewspaperStory(LocalDate.of(19, 8, 20), "TMZ", 485, "Stuff", "Things",
				new NewsMakerModel("Rodney Bates"), new NewsMakerModel("Norman Bates"));

		NewsDataBaseModel testDataBase = new NewsDataBaseModel();

		AddEditNewsStoryView testView = new AddEditNewsStoryView(testDataBase, testStory);

		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.add(testView);
		frame.pack();
		frame.setVisible(true);
	}
}
