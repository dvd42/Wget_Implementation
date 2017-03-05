package wget;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Wget {

	public static void main(String[] args) {
		
		Wget wg = new Wget();
		
		wg.readArgs(args);
		
		if(fileFilter){
			
			File f = new File(args[1]);
			
			if(!f.exists()){
				System.out.println("File doesnt exists");
				System.exit(1);
			}
		}
		wg.readFile(args[1]);
	}
	
	
	public  void readFile(String path) {
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
		
			//Read the whole file
			while(line != null){
		
				Thread t = new Thread(line);
				t.start();
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
	
	public void readArgs(String[] args){

		if(args.length < 2){
			System.out.println("You need to indicate a file or URL to download (-f filename )");
			System.exit(1);
		}
		
		for(int i = 0; i < args.length; i++){
			
			if(args[i].equals("-f")) {fileFilter = true;}
			
			if(args[i].equals("-a")) {ascFilter = true;}
		
			if(args[i].equals("-z")) {zipFilter = true;}
				
			if(args[i].equals("-gz")) {gzipFilter = true;}
		}
			
	}	
	public static int i = 0;
	public static boolean fileFilter = false;
	public static boolean ascFilter = false;
	public static boolean zipFilter = false;
	public static boolean gzipFilter = false;
}



	
 