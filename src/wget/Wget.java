package wget;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Wget {

	public static void main(String[] args) {
		
		readFile(args[1]);
		
	}

	public static void readFile(String args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(args));
			
			String s = br.readLine();
			
			while(s != null){
				System.out.println(s);
				saveURL(s);
				s = br.readLine();
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void saveURL(String s){
		
		try {
			URL url = new URL(s);
			
			System.out.println(url.getFile());
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		
	}
}



	
 