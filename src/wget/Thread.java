package wget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Thread extends java.lang.Thread{
	
	public Thread(String l){
		this.count = Wget.i;	
		this.line = l;
	}
	
	
	public void run(){
		this.saveURL(line, count, Wget.ascFilter, Wget.zipFilter, Wget.gzipFilter);
	}
	
	public void saveURL(String line, int i, boolean asc, boolean zip, boolean gzip){
		
		try {
			
		 	URL u = new URL(line);
            InputStream is = u.openStream();
           
            //Split the URL name 
            String[] web = u.getPath().split("/");
            String[] nameExtension = web[web.length -1].split("\\.");
            
            /*
             * Save the file we fetch from the web with its original name 
             * If we look for a domain instead of a specific file the 
             * file name will be "index.html" by default)
             */
            File f;
			if (web[web.length - 1].isEmpty()) {
				f = new File("index" + i + ".html");
			}
			
			else{
			
				f = new File(nameExtension[0] + i +"." + nameExtension[1]);
			}
			
			FileOutputStream fos = new FileOutputStream(f);
			
			if(asc){
				//Apply filter
				asc(is, fos); 
			}
		
			else{
				
				int b = is.read();
				
	            while (b != -1) {	
	            	fos.write(b);
	            	b = is.read();
	            }
	         
	            is.close();    
			}
			
		fos.close();
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	//If the -a paramater was called then we parse the file the remove all the html tags
	public void asc(InputStream is, FileOutputStream fos) throws IOException {
		
		Html2AsciiInputStream html = new Html2AsciiInputStream(is);
		int b = html.read();
		
		while (b != -1) {
			
			fos.write(b);
			b = html.read();
		}
		
		html.close();
	}
	
	private  String line;
	private  int count;	
}