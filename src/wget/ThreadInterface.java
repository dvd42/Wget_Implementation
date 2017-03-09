package wget;

import java.io.OutputStream;

public interface ThreadInterface {
	/**
	 * Download the file/URL applying the specified filters
	 */
	void saveURL();

	/**
	 * Closes OutputStream safely preventing resource leakage
	 * 
	 * @param fos
	 *            OutputStream to be closed
	 */
	public void closeQuietly(OutputStream fos);

}
