package wget;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class Wget {

	public static void main(String[] args) {
		
		readFile(args[1]);
		
	}

	public static void readFile(String path) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			int i = 0;
			
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

	
	public static void saveURL(String line, int i){
		
		try {
			
		 	URL u = new URL(line);
            BufferedReader bf = new BufferedReader(new InputStreamReader(u.openStream()));
            
            File f = new File("Web_data" + i);
            if (!f.exists()){
            	f.createNewFile();	
            }
            
            FileOutputStream fop = new FileOutputStream(f);
            
            
            //Write the content of the web to the file 
            while((line = bf.readLine()) != null){
            	
                byte[] contentInBytes = bf.readLine().getBytes();
				fop.write(contentInBytes);
            }
            
            bf.close();
            fop.flush(); //force out all the data from the stream
            fop.close();
	        	
	            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		
	}
}



	
 