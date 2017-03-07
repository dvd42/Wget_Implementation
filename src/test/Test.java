package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test {

	
	public static void main(String[] args) {
		
		OutputStream fos;
		
		try {
			fos = new FileOutputStream("hello-world.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			FileInputStream fin = new FileInputStream("urls.txt"); //get data from urls.txt
	        zos.putNextEntry(new ZipEntry("urls.txt"));
	        fos = zos;
	        int b = fin.read();
	        
	        while(b != -1){
	        	fos.write(b);
	        	b = fin.read();
	        }
	        
	        zos.closeEntry();
			zos.close();
			fin.close();
			fos.close();

	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
