package wget;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
           
            FileWriter fop = new FileWriter(f);
            String s = bf.readLine();
            
            //Write the content of the web to the file 
            while(s != null){
            	
                fop.write(s);
                s = bf.readLine(); 
            }
            
            bf.close();
            fop.close();
	        	
	            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		
	}
}



	
 