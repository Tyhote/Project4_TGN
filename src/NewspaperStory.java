import java.time.LocalDate;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class NewspaperStory extends NewsStory {

	private static final long serialVersionUID = 1L;

	public NewspaperStory(LocalDate date, String source, int length, String topic, String subject,
			NewsMakerModel newsMaker1, NewsMakerModel newsMaker2) {
		super(date, source, length, topic, subject, newsMaker1, newsMaker2);
	}

	@Override
	public int getLengthInWords() {
		return this.getLength();
	}
}
