package wget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.rmi.CORBA.Util;

public class Thread extends java.lang.Thread implements ThreadInterface {

	private String line;
	private int count;
	private boolean asc;
	private boolean zip;
	private boolean gzip;

	public Thread(String l, int i, boolean asc, boolean zip, boolean gzip) {
		this.count = Wget.i;
		this.line = l;
		this.asc = asc;
		this.zip = zip;
		this.gzip = gzip;
	}

	public void run() {
		this.saveURL();
	}

	@Override
	@SuppressWarnings("resource")
	public void saveURL() {

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

			if (gzip) {
				fileExtension += ".gz";
			}

			// Save the file we fetch from the web with its original name plus
			// extension
			// If we dont fetch for a specific file the filename will be
			// index.html by default
			File f;
			if (web[web.length - 1].isEmpty()) {
				f = new File("index" + count + ".html" + fileExtension);
			} else {
				f = new File(nameExtension[0] + count + "." + nameExtension[1] + fileExtension);
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

			// TODO help!!
			if (gzip) {
				fos = new GZIPOutputStream(fos);
			}

			// Copy bytes to file
			int b = is.read();
			while (b != -1) {
				fos.write(b);
				b = is.read();
			}

			closeQuietly(fos);
			is.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void closeQuietly(OutputStream fos){
		
		try {
			
			if(fos != null){
				fos.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
