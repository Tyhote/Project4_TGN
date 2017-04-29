
public class Wedge {

	private double percent;
	private String text;

	public Wedge(double percent, String text) {
		if (percent >= 0 && percent <= 100) {
			this.percent = percent;
		} else {
			throw new IllegalArgumentException();
		}
		this.setText(text);
	}

	public double getPercent() {
		return this.percent;
	}

	public void setPercent(double percent) {
		if (percent >= 0 && percent <= 100) {
			this.percent = percent;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
