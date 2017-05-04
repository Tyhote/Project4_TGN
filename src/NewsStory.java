import java.io.Serializable;
import java.time.LocalDate;

abstract class NewsStory implements Comparable<NewsStory>, Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private String source;
	private int length;
	private String topic;
	private String subject;
	private NewsMakerModel newsMaker1;
	private NewsMakerModel newsMaker2;

	protected NewsStory(LocalDate date, String source, int length, String topic, String subject,
			NewsMakerModel newsMaker1, NewsMakerModel newsMaker2) {
		this.date = date;
		this.source = source;
		if (length >= 0) {
			this.length = length;
		} else {
			System.err.println("Invalid Length. Please use a positive length.");
		}
		this.topic = topic;
		this.subject = subject;
		this.newsMaker1 = newsMaker1;
		this.newsMaker2 = newsMaker2;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSource() {
		return source;
	}

	public int getLength() {
		return length;
	}

	public abstract int getLengthInWords();

	public String getTopic() {
		return topic;
	}

	public String getSubject() {
		return subject;
	}

	public NewsMakerModel getNewsMaker1() {
		NewsMakerModel clone = newsMaker1;
		return clone;
	}

	public NewsMakerModel getNewsMaker2() {
		NewsMakerModel clone = newsMaker2;
		return clone;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setNewsMaker1(NewsMakerModel newsMaker1) {
		this.newsMaker1 = newsMaker1;
	}

	public void setNewsMaker2(NewsMakerModel newsMaker2) {
		this.newsMaker2 = newsMaker2;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof NewsStory) {
			NewsStory newsStory = (NewsStory) o;
			boolean equivalent = this.date.equals(newsStory.date);
			if (equivalent) {
				equivalent = this.source.equals(newsStory.source);
			}
			if (equivalent) {
				equivalent = this.length == newsStory.length;
			}
			if (equivalent) {
				equivalent = this.topic.equals(newsStory.topic);
			}
			if (equivalent) {
				equivalent = this.subject.equals(newsStory.subject);
			}
			if (equivalent) {
				equivalent = this.newsMaker1.equals(newsStory.newsMaker1);
			}
			if (equivalent) {
				equivalent = this.newsMaker2.equals(newsStory.newsMaker2);
			}
			return equivalent;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(NewsStory newsStory) {
		return this.topic.compareTo(newsStory.topic);
	}
	
	/**
	 * This method doesn't really have to exist since we have convertToOutputFormat in UserInterface. So don't use it. It's just here so I don't get fined.
	 * 
	 * @return
	 * 		An empty String.
	 * @Override
	 * 		toString in class java.lang.Object
	 */
	@Override
	public String toString() {
		return "";
	}
	
}
