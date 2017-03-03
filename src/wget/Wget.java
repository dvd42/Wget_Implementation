package wget;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Wget {

	public static void main(String[] args) {
		
		Wget wg = new Wget();
		
		//args[1] contains the path of the file containing the names of the URLs
		wg.readFile(args[1]);
		
	}
	
	public  void readFile(String path) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
		
			//Read the whole file
			while(line != null){
				saveURL(line, i);
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

	//Save URL to a file 
	public void saveURL(String line, int i){
		
		try {
			
		 	URL u = new URL(line);
		 	Html2AsciiInputStream is = new Html2AsciiInputStream(u.openStream());
           
            //Split the URL name 
            String[] web = u.getPath().split("/");
            
            /*
             * Save the file we fetch from the web with its original name 
             * If we look for a domain instead of a specific file the 
             * file name will be "index.html" by default)
             */
            File f;
			if (web[web.length - 1].isEmpty()) {
				f = new File("index" + ".html" + i);
			}
			else{
				f = new File(web[web.length - 1] + i);
			}
	       
	        FileOutputStream fos = new FileOutputStream(f);
        
	        //Write every byte in the web to the file 
            
	        int c = is.read();
	        
	        while (c != -1){
	        	fos.write(c);
	        	c = is.read();
	        }
	        	
	        is.close();
            fos.close();
           
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public static int i = 0;
}



	
 