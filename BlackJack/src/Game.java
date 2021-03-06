import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Game {
	private static final int initialHand = 2;
	private static final int maxTotal = 21;
	private static final double minBet = 10;
	List<Player> players;
	Dealer dealer;
	DealerShoe dealerShoe;
	int numOfDecks;
	int numOfPlayers;
	StandingTable table;
	IOHandler ioh;

	public Game(int numOfPlayers, int numOfDecks) throws IOException 
	{
		this.ioh = IOHandler.getInstance();
		this.numOfPlayers = numOfPlayers;
		this.numOfDecks = numOfDecks;
		this.dealer = new Dealer();
		this.players = initiatePlayers();
		this.dealerShoe = new DealerShoe(numOfDecks);
		
		//must be instantiated after the players on the table are set
		this.table =  new StandingTable(getPlayerNames());
	}

	private List<String> getPlayerNames() {
		List<String> ret = new ArrayList<String>();
		for(Player p : players)
			ret.add(p.name);
		return ret;
	}

	public void play() throws FileNotFoundException, UnsupportedEncodingException 
	{
		/* 
		 * The algorithm here is:
		 * 1. Ask everyone to put bets
		 * 2. Give everyone two cards
		 * 3. Ask each player if they want more 
		 */		
		while(playersAvailable())
		{
			checkDealerShoe();
			placeBets();
			playOneRound();		
			whoWins();		
			finish();
		}
		System.out.println(table.getResult());
	}
	

	private void checkDealerShoe() {
		if(this.dealerShoe.cards.size() < this.numOfDecks*52/5)
		{
			dealerShoe.cards.addAll(dealerShoe.usedCards);
			dealerShoe.usedCards.clear();
			for(int i=0; i<100; i++)
				dealerShoe.shuffle();
		}
	}

	private void playOneRound() 
	{
		List<Card> drawnCards = new ArrayList<Card>();
		
		for(int i=0; i<initialHand;i++)
		{
			for(Player p : players)
				handCard(p, this.dealerShoe.draw(), drawnCards);
			handCard(dealer, this.dealerShoe.draw(), drawnCards);
		}
		
		for(Player p : players)
			while(p.decide() != 0)
				handCard(p, this.dealerShoe.draw(), drawnCards);
			
		while(dealer.decide() != 0)
			handCard(dealer, this.dealerShoe.draw(), drawnCards);
		
		for(Player p : players)
			p.watch(drawnCards);
	}

	private void handCard(Player p, Card drawnCard, List<Card> drawnCards) {
		p.setHands(drawnCard);
		ioh.print(p.name + " draws " + drawnCard.print());
		ioh.print(p.name + " has " + p.sumHands());
		drawnCards.add(drawnCard);
	}

	private void whoWins() {
		int dealerHand = dealer.sumHands();
		
		for(Player p : players)
		{
			int playerHand = p.sumHands();
			if(playerHand > maxTotal)
				ioh.print(p.name + " has " + playerHand + " and loses.");
			else if(isBlackJack(p) && isBlackJack(dealer))
				setWinnings(p, 1, p.name + " and Dealer pushes. Both have BlackJack.");
			else if(isBlackJack(p))
				setWinnings(p, 2.5, p.name + " has BlackJack and wins.");
			else if(dealerHand > maxTotal)
				setWinnings(p, 2, "Dealer is over 21. " + p.name + " has " + playerHand + " and wins.");
			else if(playerHand > dealerHand)
				setWinnings(p, 2, p.name + " has " + playerHand + " and Dealer has " + dealerHand + ". " + p.name + " wins." );
			else if(playerHand == dealerHand)
				setWinnings(p, 1, p.name + " and Dealer pushes. Both have " + playerHand + ".");
			else
				ioh.print(p.name + " loses. Dealer has " + dealerHand + ".");
		}	
	}

	private void setWinnings(Player p, double win, String string) {
		p.setPocket(p.getPocket() + (win * p.bet));
		ioh.print(string);
		ioh.print(p.name + "'s pocket is now " + p.getPocket());
	}

	private boolean isBlackJack(Player p) {
		ArrayList<String> numbers = new ArrayList<String>();
		for(Card temp : p.getHands()) numbers.add(temp.number);
		if(p.getHands().size() > 2)
			return false;
		else if(numbers.contains("ACE") && !numbers.contains("TEN") && p.sumHands() == 21)
			return true;
		else 
			return false;
	}

	private void finish() {
		List<Integer> out = new ArrayList<Integer>();
		int i = 0;
		for(Player p : players)
		{
			Stack<Card> hands = p.getHands();
			while(!hands.isEmpty())
				dealerShoe.usedCards.push(hands.pop());
			if(!p.stillIn())
				out.add(i);
			table.append(p.name, p.getPocket());
			p.bet = 0;
			i++;
		}
		
		Collections.sort(out);
		Collections.reverse(out);
		
		for(int j : out)
			players.remove(j);
		
		Stack<Card> hands = dealer.getHands();
		while(!hands.isEmpty())
			dealerShoe.usedCards.push(hands.pop());
	}

	private void placeBets() 
	{
		for(Player p : players)
		{
			p.bet();
			ioh.print(p.name+ " bets " + p.bet + "!");
		}
	}

	private boolean playersAvailable() 
	{
		int playersIn = players.size();
		ioh.print("We start the round with " + playersIn + " people.");
		return playersIn > 0;
	}


	private List<Player> initiatePlayers() throws IOException
	{
		List<Player> players = new ArrayList<Player>();
				
		// initiate i+1 number of players to include the dealer
		for(int i=0; i<numOfPlayers; i++)
		{
			Player p;
			String name;
			name = ioh.queryInput("What's the name of player " + (i+1) + "?");
			
			switch (i) {
			case 1: 	p = new SimplePlayer(name);
						break;
			case 2: 	p = new CountingPlayer(name);
						break;
			default: 	p = new SimplePlayer(name);
						break;
			}
			p.minBet = minBet;
			players.add(p);
		}
		return players;
	}
}
