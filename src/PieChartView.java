import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PieChartView implements ActionListener {

	private PieChart pieChart;
	private NewsMakerModel newsMakerModel;
	private List<NewsMedia> media;
	private String content;
	private String measure;

	public PieChartView(NewsMakerModel newsMakerModel, List<NewsMedia> media, String content, String measure) {
		
		this.newsMakerModel = newsMakerModel;
		this.media = media;
		this.content = content;
		this.measure = measure;
		
		// Create the actual pie chart.
		try {
			pieChart = new PieChart(constructTitle(), constructWedges());
		} catch (IOException e) {
			System.err.println("Illegal Input found in pie chart");
		}
	}

	private String constructTitle() {
		
		// The title always starts with the news maker's name.
		String titleString = newsMakerModel.getName() + " - ";

		// If not all media types are selected, specify those that are.
		// Fixed to represent new data type for media
		if (media.size() < 3) {
			if (media.contains(NewsMedia.NEWSPAPER)) {
				titleString += "Newspaper";
				if (media.size() > 1) {
					titleString += "/";
				} else {
					titleString += " ";
				}
			}
			if (media.contains(NewsMedia.TV)) {
				titleString += "TV News";
				if (media.size() > 2) {
					titleString += "/";
				} else {
					titleString += " ";
				}
			}
			if (media.contains(NewsMedia.ONLINE)) {
				titleString += "Online ";
			}
		}

		// Specify the content selected.
		if ("s".equals(content)) {
			titleString += "Sources ";
		} else if ("t".equals(content)) {
			titleString += "Topics ";
		} else if ("b".equals(content)) {
			titleString += "Subjects ";
		}

		// Specify the measure selected.
		if (measure.equals("l")) {
			titleString += "by Length";
		} else if (measure.equals("c")) {
			titleString += "by Count";
		}
		return titleString;
	}

	private List<Wedge> constructWedges() throws IOException {
		
		NewsStoryListModel newsStoryListModel = newsMakerModel.getNewsStoryListModel();
		
		/* List to hold a copy of the relevant data. */
		List<NewsStory> selectedNewsStories = new ArrayList<NewsStory>();

		// Select the news stories of the media type(s) requested.
		for (int i = 0; i < newsStoryListModel.size(); i++) {
			NewsStory newsStory = newsStoryListModel.get(i);
			if ((media.contains(NewsMedia.NEWSPAPER) && newsStory instanceof NewspaperStory)
					|| (media.contains(NewsMedia.NEWSPAPER) && newsStory instanceof TVNewsStory)
					|| (media.contains(NewsMedia.NEWSPAPER) && newsStory instanceof OnlineNewsStory)) {
				selectedNewsStories.add(newsStory);
			}
		}
		
		/*
		 * Map to keep track of the items found and the quantity for each (for
		 * the pie chart wedges). Note that the items could be sources, topics,
		 * or subjects and the quantity could be measured by length or count.
		 */
		Map<String, Integer> itemNameQuantityMap = new TreeMap<String, Integer>();

		/* Need total to determine percentage. */
		int totalQuantity = 0;

		// Run through all the selected stories to add up values.
		for (NewsStory newsStory : selectedNewsStories) {

			/* Get items of the correct content type. */
			String itemName = null;
			if ("s".equals(content)) {
				itemName = newsStory.getSource();
			} else if ("t".equals(content)) {
				itemName = newsStory.getTopic();
			} else if ("b".equals(content)) {
				itemName = newsStory.getSubject();
			} else {throw new IOException();}

			/*
			 * Need variable to hold quantity of item. If this item has not been
			 * added previously, we'll want to add it to the map with a starting
			 * quantity. If it is already in the map, we'll want to add to its
			 * previous quantity.
			 */
			Integer itemQuantity = itemNameQuantityMap.get(itemName);

			// If the measure is count, we'll just add one.
			if ("c".equals(measure)) {
				if (itemQuantity == null) {
					itemNameQuantityMap.put(itemName, 1);
				} else {
					itemNameQuantityMap.put(itemName, itemQuantity + 1);
				}
				totalQuantity++;
			}
			// If the measure is length, we'll need to add the length.
			else if ("l".equals(measure)) {
				int addedQuantity = newsStory.getLengthInWords();
				if (itemQuantity == null) {
					itemNameQuantityMap.put(itemName, addedQuantity);
				} else {
					itemNameQuantityMap.put(itemName, itemQuantity + addedQuantity);
				}
				totalQuantity += addedQuantity;
			} else {throw new IOException();}
		}

		/* List of pie wedges to put in the chart. */
		List<Wedge> wedges = new ArrayList<Wedge>();

		/*
		 * The scaling factor takes into account the total quantity and the fact
		 * that wedges require percentages.
		 */
		double scale = totalQuantity / 100.0;

		// Take the data from the map and put it into the list of wedges.
		for (Map.Entry<String, Integer> entry : itemNameQuantityMap.entrySet()) {
			wedges.add(new Wedge(entry.getValue() / scale, entry.getKey()));
		}
		
		return wedges;
	}

	public void actionPerformed(ActionEvent e) {
		//TODO
	}
}













