import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import org.junit.Test;

public class EditNewsMakerViewTest {
	// 1
	@Test
	public void getSelectedNewsStoryIndicesTest() {
		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();

		NewsMakerModel newsMakerModel = new NewsMakerModel("Interesting Newsmaker");
		for (int i = 0; i < 10; i++) {
			NewsStory newsStory = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", i + 20,
					"Interesting Topic", "Interesting Subject", newsMakerModel, newsDataBaseModel.none);
			newsMakerModel.addNewsStory(newsStory);
		}

		newsDataBaseModel.addNewsMakerModel(newsMakerModel);

		EditNewsMakerView editNewsMakerView = new EditNewsMakerView(newsMakerModel, newsDataBaseModel);
		int[] indices = { 1, 4, 5 };

		Field field = null;
		try {
			field = EditNewsMakerView.class.getDeclaredField("jlNewsStoryList");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		field.setAccessible(true);
		try {
			JList<NewsStory> jList = (JList<NewsStory>) field.get(editNewsMakerView);
			jList.setSelectedIndices(indices);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		int[] selectedIndices = editNewsMakerView.getSelectedNewsStoryIndices();
		if (selectedIndices.length != indices.length) {
			fail("Not Equal: Different Sizes");
		} else {
			for (int i = 0; i < indices.length; i++) {
				if (selectedIndices[i] != indices[i]) {
					fail("Not Equal: Different at Index " + i);
				}
			}
		}
		// return true;
	}

	// 2
	@Test
	public void enableRemovalButtonTestNullNewsStoryListModel() {
		NewsMakerModel newsMakerModel = new NewsMakerModel("Interesting Newsmaker");
		newsMakerModel.setNewsStoryListModel(null);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.addNewsMakerModel(newsMakerModel);

		EditNewsMakerView editNewsMakerView = new EditNewsMakerView(newsMakerModel, newsDataBaseModel);

		Field field = null;
		try {
			field = EditNewsMakerView.class.getDeclaredField("jbtRemoveFromStory");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		field.setAccessible(true);

		JButton jButton = null;
		try {
			jButton = (JButton) field.get(editNewsMakerView);
			jButton.setEnabled(true);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		Method method = null;
		try {
			method = EditNewsMakerView.class.getDeclaredMethod("enableRemovalButton", null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		method.setAccessible(true);
		try {
			method.invoke(editNewsMakerView, (Object[]) null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		if (jButton.isEnabled()) {
			fail("Button Enabled When News Story List is Null");
		}
		// return true;
	}

	// 3
	@Test
	public void enableRemovalButtonTestEmptyNewsStoryListModel() {
		NewsMakerModel newsMakerModel = new NewsMakerModel("Interesting Newsmaker");
		newsMakerModel.setNewsStoryListModel(new NewsStoryListModel());

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.addNewsMakerModel(newsMakerModel);

		EditNewsMakerView editNewsMakerView = new EditNewsMakerView(newsMakerModel, newsDataBaseModel);

		Field field = null;
		try {
			field = EditNewsMakerView.class.getDeclaredField("jbtRemoveFromStory");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		field.setAccessible(true);

		JButton jButton = null;
		try {
			jButton = (JButton) field.get(editNewsMakerView);
			jButton.setEnabled(true);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		Method method = null;
		try {
			method = EditNewsMakerView.class.getDeclaredMethod("enableRemovalButton", null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		method.setAccessible(true);
		try {
			method.invoke(editNewsMakerView, (Object[]) null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		if (jButton.isEnabled()) {
			fail("Button Enabled When News Story List is Empty");
		}
		// return true;
	}

	// 4
	@Test
	public void enableRemovalButtonTestNonEmptyNewsStoryListModel() {
		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		NewsMakerModel newsMakerModel = new NewsMakerModel("Interesting Newsmaker");
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();
		NewsStory newsStory = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20, "Interesting Topic",
				"Interesting Subject", newsMakerModel, newsDataBaseModel.none);
		newsStoryListModel.add(newsStory);
		newsMakerModel.setNewsStoryListModel(newsStoryListModel);
		newsDataBaseModel.addNewsMakerModel(newsMakerModel);

		EditNewsMakerView editNewsMakerView = new EditNewsMakerView(newsMakerModel, newsDataBaseModel);

		Field field = null;
		try {
			field = EditNewsMakerView.class.getDeclaredField("jbtRemoveFromStory");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		field.setAccessible(true);

		JButton jButton = null;
		try {
			jButton = (JButton) field.get(editNewsMakerView);
			jButton.setEnabled(false);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		Method method = null;
		try {
			method = EditNewsMakerView.class.getDeclaredMethod("enableRemovalButton", null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		method.setAccessible(true);
		try {
			method.invoke(editNewsMakerView, (Object[]) null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		if (!jButton.isEnabled()) {
			fail("Button Not Enabled When News Story List is Not Empty");
		}
		// return true;
	}

	// 5
	@Test
	public void actionPerformedTestNullNewsStoryListModel() {
		NewsMakerModel newsMakerModel = new NewsMakerModel("Interesting Newsmaker");
		newsMakerModel.setNewsStoryListModel(null);

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.addNewsMakerModel(newsMakerModel);

		EditNewsMakerView editNewsMakerView = new EditNewsMakerView(newsMakerModel, newsDataBaseModel);

		Field field = null;
		try {
			field = EditNewsMakerView.class.getDeclaredField("jbtRemoveFromStory");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		field.setAccessible(true);

		JButton jButton = null;
		try {
			jButton = (JButton) field.get(editNewsMakerView);
			jButton.setEnabled(true);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		editNewsMakerView.actionPerformed(new ActionEvent(0, 0, "Modified News Story List"));

		if (jButton.isEnabled()) {
			fail("Button Enabled When News Story List is Null");
		}
		// return true;
	}

	// 6
	@Test
	public void actionPerformedTestEmptyNewsStoryListModel() {
		NewsMakerModel newsMakerModel = new NewsMakerModel("Interesting Newsmaker");
		newsMakerModel.setNewsStoryListModel(new NewsStoryListModel());

		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		newsDataBaseModel.addNewsMakerModel(newsMakerModel);

		EditNewsMakerView editNewsMakerView = new EditNewsMakerView(newsMakerModel, newsDataBaseModel);

		Field field = null;
		try {
			field = EditNewsMakerView.class.getDeclaredField("jbtRemoveFromStory");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		field.setAccessible(true);

		JButton jButton = null;
		try {
			jButton = (JButton) field.get(editNewsMakerView);
			jButton.setEnabled(true);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		editNewsMakerView.actionPerformed(new ActionEvent(0, 0, "Modified News Story List"));

		if (jButton.isEnabled()) {
			fail("Button Enabled When News Story List is Empty");
		}
		// return true;
	}

	// 7
	@Test
	public void actionPerformedTestNonEmptyNewsStoryListModel() {
		NewsDataBaseModel newsDataBaseModel = new NewsDataBaseModel();
		NewsMakerModel newsMakerModel = new NewsMakerModel("Interesting Newsmaker");
		NewsStoryListModel newsStoryListModel = new NewsStoryListModel();
		NewsStory newsStory = new NewspaperStory(LocalDate.of(2000, 12, 31), "Times of London", 20, "Interesting Topic",
				"Interesting Subject", newsMakerModel, newsDataBaseModel.none);
		newsStoryListModel.add(newsStory);
		newsMakerModel.setNewsStoryListModel(newsStoryListModel);
		newsDataBaseModel.addNewsMakerModel(newsMakerModel);

		EditNewsMakerView editNewsMakerView = new EditNewsMakerView(newsMakerModel, newsDataBaseModel);

		Field field = null;
		try {
			field = EditNewsMakerView.class.getDeclaredField("jbtRemoveFromStory");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		field.setAccessible(true);

		JButton jButton = null;
		try {
			jButton = (JButton) field.get(editNewsMakerView);
			jButton.setEnabled(false);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		editNewsMakerView.actionPerformed(new ActionEvent(0, 0, "Modified News Story List"));

		if (!jButton.isEnabled()) {
			fail("Button Not Enabled When News Story List is Not Empty");
		}
		// return true;
	}
}