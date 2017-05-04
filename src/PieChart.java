import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.lang.Math;

/**
 * This class extends a JFrame and creates a piechart object added to the
 * JFrame.
 * 
 * @author Clayton Glenn, Tristan Dow, Nick Fox
 */
public class PieChart extends JFrame {

	private static final long serialVersionUID = 1L;
	private List<Wedge> wedges;
	private String title;

	/**
	 * Constructor method used to create a piechart with a title and wedges.
	 * 
	 * @param title
	 *            Name of the whole piechart
	 * @param wedges
	 *            List of wedges used to draw the pie chart
	 */
	public PieChart(String title, List<Wedge> wedges) {
		this.title = title;
		this.wedges = wedges;

		// Create a specialized light-weight container.
		JPanel panel = new PieChartPanel();

		// Create a general heavy-weight container.
		setTitle(this.title);
		// Set its size.
		setSize(1000, 500);
		// Add the specialized container to the general container.
		add(panel);
		// Make the GUI visible on the screen.
		setVisible(true);
	}

	/**
	 * Mutator method used to set the title of the piechart.
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		super.setTitle(title);
	}

	/**
	 * Mutator method used to set the wedges of the piechart.
	 * 
	 * @param wedges
	 */
	public void setWedges(List<Wedge> wedges) {
		this.wedges = wedges;
	}

	/**
	 * The Pie chart panel used to add to the JFrame
	 * 
	 * @author Clayton Glenn, Tristan Dow, Nick Fox, Dean Hougen
	 */
	private class PieChartPanel extends JPanel {

		// As a subclass of JPanel, PieChartPanel should be serializable.
		private static final long serialVersionUID = 1L;

		/**
		 * This overriding method paints the JPanel that is used for the
		 * Piechart view
		 */
		@Override
		public void paintComponent(Graphics g) {
			// Should almost always call parent's paintComponent method first,
			// to ensure that it draws itself first.
			super.paintComponent(g);

			// White is likely to work at least moderately well with most
			// colors.
			setBackground(Color.WHITE);

			/* We need font metrics to get our text spacing right. */
			FontMetrics metrics = g.getFontMetrics(this.getFont());

			/* In particular, we need the height of a line of text. */
			int textHeight = metrics.getHeight();

			/* We need the x and y midpoints to center the elements. */
			int xMid = getWidth() / 2;
			int yMid = getHeight() / 2;

			/* We want to size the pie to fit within the window. */
			int pieSize = Math.min(xMid, yMid);

			/*
			 * We need the origin of the pie wedges. Because fillArc uses the
			 * origin of a bounding box that encloses the oval enclosing the
			 * arc, the origin of the pie is not in the center of the window.
			 */
			int pieXOrig = xMid - pieSize / 2;
			int pieYOrig = yMid - pieSize / 2;

			/*
			 * The text for each wedge should be drawn around the pie with a bit
			 * of space between the edge of the pie and the text. The constant
			 * here determines how much space is left over. A value of 0.5 would
			 * result in no space. Greater gives more space. Less than 0.5 puts
			 * text at least partly within the pie.
			 */
			double labelScale = pieSize * 0.6;

			/* We need to start drawing somewhere. Initialize to zero. */
			double startRadians = 0.0;
			double startDegrees = startRadians;

			/*
			 * We need to determine the color of the pie wedges and associated
			 * text.
			 */
			int nextColor = 0;

			// Cycle through the wedges.
			for (Wedge wedge : wedges) {

				// Set color for wedge and associated text.
				g.setColor(new Color(nextColor));

				/*
				 * We need the width of this piece of text to get our spacing
				 * right for labels on the left side of the chart.
				 */
				int textWidth = metrics.stringWidth(wedge.getText());

				/*
				 * We need the size of each wedge in both radians (for the text)
				 * and degrees (for the wedges).
				 */
				double sizeRadians = wedge.getPercent() * 2 * Math.PI / 100.0;
				double sizeDegrees = wedge.getPercent() * 360.0 / 100.0;

				/* We want to center the text on its associated wedge. */
				double midRadians = startRadians + sizeRadians / 2.0;

				// Draw a wedge.
				g.fillArc(pieXOrig, pieYOrig, pieSize, pieSize, (int) startDegrees,
						(int) Math.round(sizeDegrees + 0.49));

				// Draw associated text. We should adjust how we position the
				// text by which quadrant we're in. For quadrants two and three,
				// we should worry about the width of the text, since drawString
				// draws text from left to right and we specify the starting x
				// location. For quadrants three and four, we should worry about
				// the height of the text, since the starting y location we
				// specify to drawString is interpreted as the baseline of the
				// text, meaning that drawString effectively draws above the
				// location we specify.
				if (midRadians < Math.PI / 2.0) { // Quadrant one
					g.drawString(wedge.getText(), xMid + (int) (Math.cos(midRadians) * labelScale),
							yMid - (int) (Math.sin(midRadians) * labelScale));
				} else if (midRadians >= Math.PI / 2.0 && midRadians < Math.PI) { // Quadrant
																					// two
					g.drawString(wedge.getText(), xMid + (int) (Math.cos(midRadians) * labelScale) - textWidth,
							yMid - (int) (Math.sin(midRadians) * labelScale));
				} else if (midRadians >= Math.PI && midRadians < Math.PI * 3.0 / 2.0) { // Quadrant
																						// three
					g.drawString(wedge.getText(), xMid + (int) (Math.cos(midRadians) * labelScale) - textWidth, yMid
							- (int) (Math.sin(midRadians) * labelScale) - (int) (Math.sin(midRadians) * textHeight));
				} else { // Otherwise, it must be quadrant four
					g.drawString(wedge.getText(), xMid + (int) (Math.cos(midRadians) * labelScale), yMid
							- (int) (Math.sin(midRadians) * labelScale) - (int) (Math.sin(midRadians) * textHeight));
				}

				// Move the start for the next wedge by the size of the wedge
				// just drawn.
				startRadians += sizeRadians;
				startDegrees += sizeDegrees;

				// This isn't a good way to choose color values.
				nextColor += 987654321 % Integer.MAX_VALUE;
			}
		}
	}
}
