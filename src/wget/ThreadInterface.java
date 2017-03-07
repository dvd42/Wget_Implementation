package wget;

public interface ThreadInterface {
	/**
	 * Download the file/URL applying the specified filters
	 * @param line  
	 * @param i
	 * @param asc
	 * @param zip
	 * @param gzip
	 */
	void saveURL(String line, int i, boolean asc, boolean zip, boolean gzip);

}