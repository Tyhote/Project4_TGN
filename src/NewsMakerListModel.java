import javax.swing.DefaultListModel;

public class NewsMakerListModel {

	private long serialVersionUID;
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
