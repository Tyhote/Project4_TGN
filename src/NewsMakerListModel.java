import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class NewsMakerListModel implements Serializable {

	private static final long serialVersionUID = -7876369843726837770L;
	private DefaultListModel<NewsMakerModel> newsMakerDefaultListModel;

	public NewsMakerListModel() {

		this.newsMakerDefaultListModel = new DefaultListModel<NewsMakerModel>();
	}

	public NewsMakerListModel(NewsMakerListModel newsMakerListModel) {

		this.newsMakerDefaultListModel = newsMakerListModel.getNewsMakers();
	}

	public boolean isEmpty() {

		if (newsMakerDefaultListModel.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return newsMakerDefaultListModel.size();
	}

	public boolean contains(NewsMakerModel newsMakerModel) {
		if (newsMakerDefaultListModel.contains(newsMakerModel)) {
			return true;
		} else {
			return false;
		}
	}

	public NewsMakerModel get(NewsMakerModel newsMakerModel) {
		int index = newsMakerDefaultListModel.indexOf(newsMakerModel);
		if (index > -1) {
			return newsMakerDefaultListModel.get(index);
		} else {
			return null;
		}
	}

	public NewsMakerModel getExactMatch(String newsMakerName) {
		NewsMakerModel newsMakerModel = new NewsMakerModel(newsMakerName);
		int index = newsMakerDefaultListModel.indexOf(newsMakerModel);
		if (index > -1) {
			return newsMakerDefaultListModel.get(index);
		} else {
			return null;
		}
	}

	public NewsMakerModel getPartialMatch(String newsMakerName) {
		for (int i = 0; i < newsMakerDefaultListModel.size(); ++i) {
			NewsMakerModel newsMakerModel = newsMakerDefaultListModel.get(i);
			if (newsMakerModel.getName().contains(newsMakerName)) {
				return newsMakerModel;
			}
		}
		return null;
	}

	public DefaultListModel<NewsMakerModel> getNewsMakers() {
		return newsMakerDefaultListModel;
	}

	public NewsMakerModel get(int index) {
		if (index > -1 && index < newsMakerDefaultListModel.size()) {
			return newsMakerDefaultListModel.get(index);
		} else {
			throw new IllegalArgumentException("You entered the wrong value into the get method");
		}
	}

	public String[] getNewsMakerNames() {
		int size = newsMakerDefaultListModel.size();
		String[] result = new String[size];
		for (int i = 0; i < size; ++i) {
			result[i] = newsMakerDefaultListModel.get(i).getName();
		}
		return result;
	}

	public void add(NewsMakerModel newsMakerModel) {
		// illegal arg if in list
		if (!newsMakerDefaultListModel.contains(newsMakerModel)) {
			//throw new IllegalArgumentException();
			newsMakerDefaultListModel.addElement(newsMakerModel);
		}
		
	}

	public void replace(NewsMakerModel newsMakerModel) {
		// illegal arg if none or not in list
		if (!(newsMakerDefaultListModel.contains(newsMakerModel))
				|| newsMakerModel.getName().toLowerCase().equals("none")) {
			throw new IllegalArgumentException();
		}
		// using the remove function to make sure correct removal
		remove(newsMakerModel);
		newsMakerDefaultListModel.addElement(newsMakerModel);
	}

	public void remove(NewsMakerModel newsMakerModel) {
		// Illegal arg if none or not in list
		if (newsMakerModel.getName().toLowerCase().equals("none")
				|| !newsMakerDefaultListModel.contains(newsMakerModel)) {
			throw new IllegalArgumentException();
		}

		NewsMakerModel targetNewsMakerModel = newsMakerDefaultListModel
				.getElementAt(newsMakerDefaultListModel.indexOf(newsMakerModel));
		NewsStoryListModel targetNewsStoryListModel = targetNewsMakerModel.getNewsStoryListModel();
		String newsMakerModelName = newsMakerModel.getName();

		// Setting all references to this NewsMakerModel to None
		NewsMakerModel none = get(new NewsMakerModel("None"));
		for (int i = 0; i < targetNewsStoryListModel.size(); ++i) {
			NewsStory targetStory = targetNewsStoryListModel.get(i);
			if (targetStory.getNewsMaker1().getName().equals(newsMakerModelName)) {
				targetStory.setNewsMaker1(none);
			}
			if (targetStory.getNewsMaker2().getName().equals(newsMakerModelName)) {
				targetStory.setNewsMaker2(none);
			}
			if (!none.getNewsStoryListModel().contains(targetStory)) {
				none.addNewsStory(targetStory);
			}
		}
		newsMakerDefaultListModel.removeElement(targetNewsMakerModel);

	}

	public void removeListOfNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
		for (int i = 0; i < newsMakerDefaultListModel.size(); ++i) {
			remove(newsMakers.get(i));
		}
	}

	public void removeAllNewsMakers() {
		int i = 0;
		while(newsMakerDefaultListModel.size() > 1 ){
			NewsMakerModel aux = newsMakerDefaultListModel.get(i);
			if(aux.getName().equals("None")){
				if(i == newsMakerDefaultListModel.size()-1){
					i = 0;
				}else{
					++i;
				}
				continue;
			}
			remove(aux);
		}
	}

	public void setNewsMakersFromNewsMakerList(NewsMakerListModel newsMakerListModel) {
		this.newsMakerDefaultListModel = newsMakerListModel.newsMakerDefaultListModel;
	}

	public void sort() {
		ArrayList<NewsMakerModel> aux = new ArrayList<NewsMakerModel>();
		for(int i = 0; i < newsMakerDefaultListModel.size(); ++i){
			aux.add(newsMakerDefaultListModel.get(i));
		}
		Collections.sort(aux);
		newsMakerDefaultListModel.clear();
		for(NewsMakerModel newsMaker : aux){
			newsMakerDefaultListModel.addElement(newsMaker);
		}
	}
}

