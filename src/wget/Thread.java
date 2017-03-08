package wget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Thread extends java.lang.Thread implements ThreadInterface {

	private String line;
	private int count;

	public Thread(String l) {
		this.count = Wget.i;
		this.line = l;
	}

	public void run() {
		this.saveURL(line, count, Wget.ascFilter, Wget.zipFilter, Wget.gzipFilter);
	}

	@Override
	@SuppressWarnings("resource")
	public void saveURL(String line, int i, boolean asc, boolean zip, boolean gzip) {

		try {

			URL u = new URL(line);
			URLConnection url = u.openConnection();
			InputStream is = url.getInputStream();

			// Split the URL name getting the filename and its extension
			String[] web = u.getPath().split("/");
			String[] nameExtension = web[web.length - 1].split("\\.");
			String fileExtension = "";

			// Add extensions to file names
			if (asc && url.getContentType().contains("text/html")) {
				fileExtension += ".asc";
			}
			if (zip) {
				fileExtension += ".zip";
			}

			// Save the file we fetch from the web with its original name plus
			// extension
			// If we dont fetch for a specific file the filename will be
			// index.html by default
			File f;
			if (web[web.length - 1].isEmpty()) {
				f = new File("index" + i + ".html" + fileExtension);
			} else {
				f = new File(nameExtension[0] + i + "." + nameExtension[1] + fileExtension);
			}

			OutputStream fos = new FileOutputStream(f);

			// Apply ascii filter to text file
			if (asc && url.getContentType().contains("text/html")) {
				is = new Html2AsciiInputStream(is);
			}

			// Apply zip filter
			ZipOutputStream zos = null;
			if (zip) {
				ZipEntry entry = new ZipEntry(f.getName().replace(".zip", ""));
				zos = new ZipOutputStream(fos);
				zos.putNextEntry(entry);
				fos = zos;
			}

			// TODO gzip

			// Copy bytes to file
			int b = is.read();
			while (b != -1) {
				fos.write(b);
				b = is.read();
			}

			// Close zip
			if (zip) {
				zos.closeEntry();
				zos.close();
			}
			is.close();
			fos.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}