import java.io.IOException;

public class table {
	static int numOfPlayers = 0;
	static int numOfDecks = 0;
	
	public static void main(String[] args) throws IOException{
		numOfPlayers = getNumOfPlayers();
		numOfDecks = getNumOfDecks();
		Game game = new Game(numOfPlayers, numOfDecks);
		game.play();
		System.out.println("The game has finished!");
	}
	
	private static int getNumOfDecks() throws NumberFormatException, IOException {
		IOHandler ioh = new IOHandler();
		return (int) Double.parseDouble(ioh.queryInput("How many decks to start?"));
	}

	private static int getNumOfPlayers() throws IOException {
		IOHandler ioh = new IOHandler();
		return (int) Double.parseDouble(ioh.queryInput("How many players to start?"));
	}
}
