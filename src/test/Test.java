package test;

import java.io.InputStream;

public class Test {

	public static void main(String[] args) {
		
		int i = 0;
		
		while(i != '>' ){
			
			System.out.println((char)i + " " + i);
			
			if (i == '<'){
				
				System.out.println("found < " + i + "" + (char)i);
			}
			
			i++;
		}
		
		
	}

}
