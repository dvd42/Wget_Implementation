package wget;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Html2AsciiInputStream extends FilterInputStream {

	public Html2AsciiInputStream(InputStream in) {
		super(in);
	}

	//Loop the web data skipping all the html tags
	public int read(){
		
		int c = 0;
		
		try {
			
			c = in.read();
			
			while (c == '<'){				
	
				while (c != '>') {
					c = in.read();
				}
				c = in.read();
				
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return c;
	}

}
