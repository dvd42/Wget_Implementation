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

public class Thread extends java.lang.Thread{
	
	private String line;
	private int count;
	
	public Thread(String l){
		this.count = Wget.i;	
		this.line = l;
	}
	
	
	public void run(){
		this.saveURL(line, count, Wget.ascFilter, Wget.zipFilter, Wget.gzipFilter);
	}
	
	//Download the file/URL applying the specified filters
	public void saveURL(String line, int i, boolean asc, boolean zip, boolean gzip){
		
		try {
			
		 	URL u = new URL(line);
            URLConnection url = u.openConnection();
            InputStream is = url.getInputStream();
            
            //Split the URL name getting the filename and its extension
            String[] web = u.getPath().split("/");
            String[] nameExtension = web[web.length -1].split("\\.");
            
            //Save the file we fetch from the web with its original name 
            File f;
            //If we dont fetch for a specific file the filename will be index.html by default
			if (web[web.length - 1].isEmpty()) {
				f = new File("index" + i + ".html");
			}
			
			else{				
				f = new File(nameExtension[0] + i + "." + nameExtension[1]);
			}
			
			OutputStream fos = null;
			
			int b = is.read();	
			
			//Apply ascii filter to every file except images
			if(asc && !url.getContentType().contains("image")){
				is = new Html2AsciiInputStream(is);
			}
			
			//Apply zip filter
			// TODO Find cleaner Way to do this?
			ZipOutputStream zos = null;
			if(zip){
				fos = new FileOutputStream(f.getName() + ".zip");
				ZipEntry entry = new ZipEntry(f.getName());
				zos = new ZipOutputStream(fos);
				zos.putNextEntry(entry);
				fos = zos;
			}
			
			if(!zip){
				fos = new FileOutputStream(f);
			}
			
            while (b != -1) {	
            	fos.write(b);
            	b = is.read();
            }
            // TODO Encapsulate this or let exceptions catch??
	        zos.closeEntry();
	        zos.close();
	        is.close();
			fos.close();
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
		
}