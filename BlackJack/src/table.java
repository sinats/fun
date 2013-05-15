import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class table {
	static int numOfPlayers = 0;
	static int numOfDecks = 0;
	static IOHandler ioh;
	
	public static void main(String[] args) throws IOException{
		ioh = IOHandler.getInstance(getPrintOrNot());
		numOfPlayers = getNumOfPlayers();
		numOfDecks = getNumOfDecks();
		Game game = new Game(numOfPlayers, numOfDecks);
		game.play();
		System.out.println("The game has finished!");
	}
	
	private static boolean getPrintOrNot() throws IOException {
		String input = null;
		System.out.println("Print output?");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			input = br.readLine();
		} catch (IOException ioe) {
	         System.out.println("IO error trying to read input!");
	         System.exit(1);
	    }
		return "Yes".equalsIgnoreCase(input);
	}

	private static int getNumOfDecks() throws NumberFormatException, IOException {
		return (int) Double.parseDouble(ioh.queryInput("How many decks to start?"));
	}

	private static int getNumOfPlayers() throws IOException {
		return (int) Double.parseDouble(ioh.queryInput("How many players to start?"));
	}
}
