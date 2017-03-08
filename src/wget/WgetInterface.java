package wget;

public interface WgetInterface {

	/**
	 * Reads the file containing the list of URLs to download
	 * 
	 * @param path
	 *            Path of the file containing the list or URLS
	 */
	void readFile(String path);

	/**
	 * Checks the arguments with which the program was executed -f to indicate
	 * the file to read the list of URLS to download -zip to download the file
	 * in zip format -a to remove all the html tags and comments -gzip to
	 * download the file in gzip format
	 * 
	 * @param args
	 *            Parameters with which we want the program to be executed
	 */
	void readArgs(String[] args);

}