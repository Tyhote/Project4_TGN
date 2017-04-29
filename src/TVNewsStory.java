import java.time.LocalDate;

public class TVNewsStory extends NewsStory {

	private static final long serialVersionUID = 1L;
	private PartOfDay partOfDay;

	public TVNewsStory(LocalDate date, String source, int length, String topic, String subject, PartOfDay partOfDay,
			NewsMakerModel newsMaker1, NewsMakerModel newsMaker2) {
		super(date, source, length, topic, subject, newsMaker1, newsMaker2);
		this.partOfDay = partOfDay;
	}

	@Override
	public int getLengthInWords() {
		return this.getLength() * 150 / 60;
	}

	public PartOfDay getPartOfDay() {
		return partOfDay;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof TVNewsStory) {
			if (super.equals(o)) {
				return this.partOfDay.equals(((TVNewsStory) o).partOfDay);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
