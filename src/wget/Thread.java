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
			String filterExtensionZipEntry = "";
			String filterExtensionFile = "";

			// Create file names
			if (asc) {
				if (zip) {
					filterExtensionZipEntry += ".asc";
				} else if(url.getContentType().contains("text")){
					filterExtensionFile += ".asc";
				}
			}
			
			System.out.println(url.getContentType());
			
			if (zip) {
				filterExtensionFile += ".zip";
			}
			
			// if(gzip){
			// filterExtension += ".gz";
			// }

			String entryName = "";

			// Save the file we fetch from the web with its original name
			// If we dont fetch for a specific file the filename will be
			// index.html by default
			File f;
			if (web[web.length - 1].isEmpty()) {
				f = new File("index" +i + ".html" + filterExtensionFile); 
				entryName = "index" + i + ".html" + filterExtensionZipEntry;
			} 
			else {
				f = new File(nameExtension[0] + i + "." + nameExtension[1] + filterExtensionFile);
				entryName = nameExtension[0] + i + "." + nameExtension[1] + filterExtensionZipEntry;
			}

			OutputStream fos = new FileOutputStream(f);
			int b = is.read();

			// Apply ascii filter to every file except images
			if (asc && url.getContentType().contains("text")) {
				is = new Html2AsciiInputStream(is);
			}

			// Apply zip filter
			ZipOutputStream zos = null;
			if (zip) {
				ZipEntry entry = new ZipEntry(entryName);
				zos = new ZipOutputStream(fos);
				zos.putNextEntry(entry);
				fos = zos;
			}

			// Copy bytes
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