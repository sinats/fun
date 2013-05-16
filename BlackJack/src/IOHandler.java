import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class IOHandler {
	private static IOHandler instance;
	boolean print;
	
	private IOHandler() throws IOException {
		this.print = "Yes".equalsIgnoreCase(queryInput("Print output?"));
	}

	public static synchronized IOHandler getInstance() throws IOException {
		if(instance == null)
			instance = new IOHandler();
		return instance;
	}
	
	public void print(String text) {
		if(this.print)
			System.out.println(text);
	}
	
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
