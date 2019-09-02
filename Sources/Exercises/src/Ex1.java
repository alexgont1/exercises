import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public class Ex1 {

	// Project directory path:
	public static String userDir = Paths.get(".").normalize().toAbsolutePath().toString();

	// file path:
	public static String path = userDir + File.separator + "Dictionary.txt";

	// input file stream:
	public static FileInputStream f;

	public static boolean doesFileExist(String path) {
		boolean exist = false;
		try {
			f = new FileInputStream(path);
			exist = true;
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public static void main(String[] args) throws IOException {

		if (doesFileExist(path)) {

			// using HashMap because here is 'Key:Value' structure
			HashMap<String, String> m = new HashMap<String, String>();

			// reading file
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String newLine = "";

			// fill HashMap and print key and values
			while ((newLine = reader.readLine()) != null) {
				String line[] = newLine.split(" – ");

				m.put(line[0], line[1]);

				System.out.println(line[0]);

				String[] values = line[1].split(", ");

				for (String value : values) {
					System.out.println(value);
				}
			}

			reader.close();

		}
	}
}
