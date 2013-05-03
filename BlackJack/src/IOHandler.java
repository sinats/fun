import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class IOHandler {
	public String queryInput(String string) throws IOException{
		String input = null;
		System.out.println(string);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			input = br.readLine();
		} catch (IOException ioe) {
	         System.out.println("IO error trying to read input!");
	         System.exit(1);
	    }
		return(input);
	}
}
