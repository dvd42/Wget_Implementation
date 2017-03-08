package wget;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Wget {

	public static int i = 0;
	public static boolean fileFilter = false;
	public static boolean ascFilter = false;
	public static boolean zipFilter = false;
	public static boolean gzipFilter = false;

	public void readFile(String path) {

		try {

			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();

			// Read the whole file
			while (line != null) {

				// Create and start a thread for each line in the file
				Thread t = new Thread(line);
				t.start();
				line = br.readLine();
				i++;
			}
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Checks the arguments with which the program was executed
	public void readArgs(String[] args) {

		for (int i = 0; i < args.length; i++) {

			switch (args[i]) {
			case "-f":
				fileFilter = true;
				break;
			case "-a":
				ascFilter = true;
				break;
			case "-z":
				zipFilter = true;
				break;
			case "-gz":
				gzipFilter = true;
				break;
			}
		}
	}

	public static void main(String[] args) {

		Wget wg = new Wget();
		wg.readArgs(args);

		if (!fileFilter) {
			System.out.println("You must especify the file from which to read URLs");
			System.exit(1);

		}
		wg.readFile(args[1]);
	}
}
