package wget;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
		
			//Create File output Stream to write the URL data to Web_data.txt
			File file = new File("Web_data.txt");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream fop = new FileOutputStream(file);
			
			//Read the whole file
			while(line != null){
				saveURL(line, fop);
				line = br.readLine();
			}
			br.close();
			fop.flush();
			fop.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void saveURL(String line, FileOutputStream fop){
		
		try {
			
			InputStream is = null;
			URL url = new URL(line);
		
			//open connection to URL to get data
			is = url.openStream();

			// get the content in bytes
			byte[] contentInBytes = is.toString().getBytes();
			
			System.out.println(is.toString());
			
			fop.write(contentInBytes);
			fop.write("\n".getBytes());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
		
	}
}



	
 