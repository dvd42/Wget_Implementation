package wget;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Html2AsciiInputStream extends FilterInputStream {

	public Html2AsciiInputStream(InputStream in) {
		super(in);
	}

	public int read(){
		
		int c = 0;
		
		try {
			
			c = in.read();
			
			do {
				if (c == '<') {

					while (c != '>') {
						c = in.read();
					}
					c = in.read();
				} 
			} while (c == '<');
			
		if (c == '<' || c == '>'){
			System.out.println(c);
		}
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return c;
	}

}
