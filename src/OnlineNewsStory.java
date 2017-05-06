import java.time.LocalDate;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class OnlineNewsStory extends NewsStory {

	private static final long serialVersionUID = 1L;
	private PartOfDay partOfDay;

	public OnlineNewsStory(LocalDate date, String source, int length, String topic, String subject, PartOfDay partOfDay,
			NewsMakerModel newsMaker1, NewsMakerModel newsMaker2) {
		super(date, source, length, topic, subject, newsMaker1, newsMaker2);

		// TODO: Check to ensure the part of day is valid.
		this.partOfDay = partOfDay;
	}

	@Override
	public int getLengthInWords() {
		return this.getLength();
	}

	public PartOfDay getPartOfDay() {
		return partOfDay;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof OnlineNewsStory) {
			if (super.equals(o)) {
				return this.partOfDay.equals(((OnlineNewsStory) o).partOfDay);
			} else {
				return false;
			}
		}
		// If it isn't an OnlineNewsStory, it's not equivalent.
		else {
			return false;
		}
	}
}
