import javax.swing.DefaultListModel;

public class NewsMakerListModel {

	private long serialVersionUID;
	private DefaultListModel<NewsMakerModel> newsMakerDefaultListModel;

	public NewsMakerListModel() {
	}

	public NewsMakerListModel(NewsMakerListModel newsMakerListModel) {
	}

	public boolean isEmpty() {
		return true;
	}

	public int size() {
		return 0;
	}

	public boolean contains(NewsMakerModel newsMakerModel) {
		return true;
	}

	public NewsMakerModel get(NewsMakerModel newsMakerModel) {
		return null;
	}

	public NewsMakerModel getExactMatch(String newsMakerName) {
		return null;
	}

	public NewsMakerModel getPartialMatch(String newsMakerName) {
		return null;
	}

	public DefaultListModel<NewsMakerModel> getNewsMakers() {
		return null;
	}

	public NewsMakerModel get(int index) {
		return null;
	}

	public String[] getNewsMakerNames() {
		return null;
	}

	public void add(NewsMakerModel newsMakerModel) {
	}

	public void replace(NewsMakerModel newsMakerModel) {
	}

	public void remove(NewsMakerModel newsMakerModel) {
	}

	public void removeListOfNewsMakers(DefaultListModel<NewsMakerModel> newsMakers) {
	}

	public void removeAllNewsMakers() {
	}

	public void setNewsMakersFromNewsMakerList(NewsMakerListModel newsMakerListModel) {
	}

	public void sort() {
	}
}
