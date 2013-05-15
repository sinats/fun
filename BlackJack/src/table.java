import java.io.IOException;

public class table {
	static int numOfPlayers = 0;
	static int numOfDecks = 0;
	static boolean print = true;
	static IOHandler ioh;
	
	public static void main(String[] args) throws IOException{
		numOfPlayers = getNumOfPlayers();
		numOfDecks = getNumOfDecks();
		print = getPrintOrNot();
		ioh = IOHandler.getInstance(print);
		Game game = new Game(numOfPlayers, numOfDecks, print);
		game.play();
		System.out.println("The game has finished!");
	}
	
	private static boolean getPrintOrNot() throws IOException {		
		return "Yes".equalsIgnoreCase(ioh.queryInput("Print output?"));
	}

	private static int getNumOfDecks() throws NumberFormatException, IOException {
		return (int) Double.parseDouble(ioh.queryInput("How many decks to start?"));
	}

	private static int getNumOfPlayers() throws IOException {
		return (int) Double.parseDouble(ioh.queryInput("How many players to start?"));
	}
}
