import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Clayton Glenn, Nick Fox, Tristan Dow
 *
 */
public class CodeFileProcessor {
	private static Map<String, String> codeMap;

	public static Map<String, String> readCodeFile(String fileName) throws IOException {
		// Need to clear map of old data before reading new.
		codeMap = new TreeMap<String, String>();

		FileReader fr;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException f) {
			throw new FileNotFoundException();
		}
		BufferedReader br = new BufferedReader(fr);
		String nextLine = br.readLine();
		while (nextLine != null) {
			String parts[] = nextLine.split(",");
			if (parts.length == 2) {
				codeMap.put(parts[0], parts[1].replaceAll("\"", ""));
			} else {
				System.err.println("Wrong number of components in line: " + parts.length);
				System.err.println("Skipping bad line in " + fileName + ": " + nextLine);
			}
			nextLine = br.readLine();
		}
		br.close();

		return codeMap;
	}
}
