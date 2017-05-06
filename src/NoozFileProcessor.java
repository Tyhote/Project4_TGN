import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
class NoozFileProcessor {

	private static NewsMakerListModel newsMakers = new NewsMakerListModel();
	private static NewsStoryListModel newsStories = new NewsStoryListModel();
	private static NewsDataBaseModel newsDataBase = new NewsDataBaseModel();

	public static NewsDataBaseModel readNoozFile(String fileName, Map<String, String> sourceMap,
			Map<String, String> topicMap, Map<String, String> subjectMap) throws IOException {

		// Handle possible I/O errors
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException f) {
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(fr);
			String nextLine = br.readLine(); // First line is header info.
												// Ignore.
			nextLine = br.readLine();
			while (nextLine != null) {
				processLine(nextLine, sourceMap, topicMap, subjectMap);
				nextLine = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			return null;
		} catch (StringIndexOutOfBoundsException i) {
			throw new StringIndexOutOfBoundsException();
		}

		// Set, sort, and return the database
		newsDataBase.setNewsMakerListModel(newsMakers);
		newsDataBase.sortNewsMakerListModel();
		return newsDataBase;

	}

	public static void writeNewsTextFile(String outputFileName, String listOfStories) throws IOException {
		FileWriter outfile = new FileWriter(outputFileName);
		BufferedWriter bw = new BufferedWriter(outfile);
		bw.write(listOfStories);
		bw.newLine();
		bw.close();
	}

	private static void processLine(String line, Map<String, String> sourceMap, Map<String, String> topicMap,
			Map<String, String> subjectMap) throws IOException {

		/* The parts the line created by splitting the line at each comma. */
		String[] parts = line.split(",");

		/* The local date from part zero of the line. */
		LocalDate date = decodeDate(parts[0]);

		/* The source from part one of the line. */
		String sourceCode = parts[1];
		String source = sourceMap.get(sourceCode);
		if (source == null) {
			System.err.println("No matching source map entry for " + sourceCode + ". Skipping line.");
			return;
		}

		/* The word count from part two of the line. */
		int wordCount = decodeLength(parts[2]);

		/* The subject from part three of the line. */
		String subject = subjectMap.get(parts[3]);
		if (subject == null) {
			System.err.println("No matching subject map entry for " + parts[3] + ". Skipping line.");
			return;
		}

		/* The topic from part four of the line. */
		String topic = topicMap.get(parts[4]);
		if (topic == null) {
			System.err.println("No matching topic map entry for " + parts[4] + ". Skipping line.");
			return;
		}

		/*
		 * The first news maker name, which might come from just part four or
		 * from parts four and five, depending on whether it contains a comma.
		 */
		String newsMakerName1 = decodeNewsmakerName(parts, 5);

		/*
		 * The second news maker name, which might start with part five or part
		 * six, depending on the first news maker name.
		 */
		String newsMakerName2;
		if (newsMakerName1.contains(",")) {
			newsMakerName2 = decodeNewsmakerName(parts, 7);
		} else {
			newsMakerName2 = decodeNewsmakerName(parts, 6);
		}

		/*
		 * The first news maker is constructed based on the first news maker
		 * name read.
		 */
		NewsMakerModel newsMaker1 = new NewsMakerModel(newsMakerName1);
		// If the news maker is on the list, use the copy already on the list
		if (newsMakers.contains(newsMaker1)) {
			newsMaker1 = newsMakers.get(newsMaker1);
		}
		// Otherwise, add the new news maker to the list
		else {
			newsMakers.add(newsMaker1);
		}

		/*
		 * The second news maker is constructed based on the second news maker
		 * name read.
		 */
		NewsMakerModel newsMaker2 = new NewsMakerModel(newsMakerName2);
		// If the news maker is on the list, use the copy already on the list
		if (newsMakers.contains(newsMaker2)) {
			newsMaker2 = newsMakers.get(newsMaker2);
		}
		// Otherwise, add the new news maker to the list
		else {
			newsMakers.add(newsMaker2);
		}

		/*
		 * The news story, which is constructed from the relevant data.
		 */
		int sourceNum = 0;
		try {
			sourceNum = Integer.parseInt(sourceCode);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Non-integer as source code: " + sourceCode);
		}

		NewsStory newsStory = null;

		// Below 200 is newspaper.
		if (sourceNum < 200) {
			newsStory = new NewspaperStory(date, source, wordCount, topic, subject, newsMaker1, newsMaker2);
		}
		// Between 200 and 400 is online news.
		else if (sourceNum < 400) {
			// The part of day from the last field (only for TV news stories)
			PartOfDay partOfDay = decodePartOfDay(parts[parts.length - 1]);
			newsStory = new OnlineNewsStory(date, source, wordCount, topic, subject, partOfDay, newsMaker1, newsMaker2);
		}
		// Between 400 and 600 is TV news
		else if (sourceNum < 600) {
			// The part of day from the last field (only for TV news stories)
			PartOfDay partOfDay = decodePartOfDay(parts[parts.length - 1]);
			newsStory = new TVNewsStory(date, source, wordCount, topic, subject, partOfDay, newsMaker1, newsMaker2);
		} else {
			throw new IllegalArgumentException();
		}

		// Get newsMakers stories and add story to list
		newsStories = newsMaker1.getNewsStoryListModel();
		newsStories.add(newsStory);
		newsMaker1.setNewsStoryListModel(newsStories);

		newsStories = newsMaker2.getNewsStoryListModel();
		newsStories.add(newsStory);
		newsMaker2.setNewsStoryListModel(newsStories);

		newsDataBase.addNewsStory(newsStory);

		// Add newsMakers to list
		newsMakers.add(newsMaker1);
		newsMakers.add(newsMaker2);

	}

	private static LocalDate decodeDate(String dateString) {

		try {
			/* The year portion of the date string. */
			String yearString = dateString.substring(0, 4);

			/* The month portion of the date string. */
			String monthString = dateString.substring(4, 6);

			/* The day portion of the date string. */
			String dayOfMonthString = dateString.substring(6, 8);

			/* The year as an integer (hopefully). */
			int year = 0;
			try {
				year = Integer.parseInt(yearString);
			} catch (NumberFormatException e) {
				System.err.println("Wrong argument provided. Argument (" + year + ") is not an integer.");
				return null;
			}

			/* The month as an integer (hopefully). */
			int month = 0;
			try {
				month = Integer.parseInt(monthString);
			} catch (NumberFormatException e) {
				System.err.println("Wrong argument provided. Argument (" + month + ") is not an integer.");
				return null;
			}

			/* The month as an integer (hopefully). */
			int dayOfMonth = 0;
			try {
				dayOfMonth = Integer.parseInt(dayOfMonthString);
			} catch (NumberFormatException e) {
				System.err.println("Wrong argument provided. Argument (" + dayOfMonth + ") is not an integer.");
				return null;
			}

			/*
			 * The date constructed from the year, month, and dayOfMonth
			 * integers.
			 */
			LocalDate date = LocalDate.of(year, month, dayOfMonth);
			return date;
		} catch (StringIndexOutOfBoundsException e) {
			throw new StringIndexOutOfBoundsException();
		}
	}

	private static int decodeLength(String lengthString) {
		int length = 0;

		try {
			length = Integer.parseInt(lengthString);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Non-integer as length: " + lengthString);
		}

		return length;
	}

	private static String decodeNewsmakerName(String[] parts, int startingIndex) {
		String nameString = "";

		// Check for special code 99
		if ("99".equals(parts[startingIndex])) {
			nameString = "None";
		}
		// If the starting part of the name ends with a quotation mark, then the
		// name takes up only one part
		else if (parts[startingIndex].endsWith("\"")) {
			nameString = parts[startingIndex].replaceAll("\"", "");
		}
		// The other option is that the name takes up two parts, which must be
		// put together.
		else {
			nameString = (parts[startingIndex] + "," + parts[startingIndex + 1]).replaceAll("\"", "");
		}

		return nameString;
	}

	private static PartOfDay decodePartOfDay(String partOfDayCode) {
		switch (partOfDayCode) {
		case "1":
			return PartOfDay.MORNING;
		case "2":
			return PartOfDay.AFTERNOON;
		case "4":
			return PartOfDay.EVENING;
		case "6":
			return PartOfDay.LATE_NIGHT;
		default:
			throw new IllegalArgumentException("Invalid part of day code: " + partOfDayCode);
		}
	}
}
