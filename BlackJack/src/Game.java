import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

	public Game(int numOfPlayers, int numOfDecks) throws IOException 
	{
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
		{
			ret.add(p.name);
		}
		return ret;
	}

	public void play() 
	{
		/* 
		 * The algorithm here is:
		 * 1. Ask everyone to put bets
		 * 2. Give everyone two cards
		 * 3. Ask each player if they want more 
		 */		
		while(playersAvailable())
		{
			placeBets();
			playOneRound();		
			whoWins();		
			finish();
		}
		table.print();
	}
	

	private void playOneRound() 
	{
		List<Card> drawnCards = new ArrayList<Card>();
		
		for(int i=0; i<initialHand;i++)
		{
			for(Player p : players)
			{
				Card drawnCard = this.dealerShoe.draw();
				p.setHands(drawnCard);
				drawnCards.add(drawnCard);
			}
			Card drawnCard = this.dealerShoe.draw();
			dealer.setHands(drawnCard);
			drawnCards.add(drawnCard);
		}
		
		for(Player p : players)
		{
			while(p.decide(drawnCards) != 0)
			{
				Card drawnCard = this.dealerShoe.draw();
				p.setHands(drawnCard);
				drawnCards.add(drawnCard);
			}
		}
			
		while(dealer.decide(drawnCards) != 0)
		{
			Card drawnCard = this.dealerShoe.draw();
			dealer.setHands(drawnCard);
			drawnCards.add(drawnCard);
		}
	}

	private void whoWins() {
		int dealerHand = dealer.sumHands();
		
		for(Player p : players)
		{
			int playerHand = p.sumHands();
			if(playerHand > maxTotal)
			{
				System.out.println(p.name + " has " + playerHand + " and loses.");
			}
			else if(dealerHand > maxTotal)
			{
				System.out.println("Dealer is over 21. " + p.name + " has " + playerHand + " and wins.");
				p.setPocket(p.getPocket() + (2 * p.bet));
			}
			else if(playerHand > dealerHand)
			{
				System.out.println(p.name + " has " + playerHand + " and Dealer has "
						+ dealerHand + ". " + p.name + " wins." );
				p.setPocket(p.getPocket() + (2 * p.bet));
			}
			else if(playerHand == dealerHand)
			{
				System.out.println(p.name + " and Dealer pushes. Both have " + playerHand + ".");
				p.setPocket(p.getPocket() + (1 * p.bet));
			}
			else
			{
				System.out.println(p.name + " loses. Dealer has " + dealerHand + ".");
			}
		}	
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
			p.bet();
	}

	private boolean playersAvailable() 
	{
		int playersIn = players.size();
		System.out.println("We start the round with " + playersIn + " people.");
		return playersIn > 0;
	}


	private List<Player> initiatePlayers() throws IOException
	{
		List<Player> players = new ArrayList<Player>();
				
		// initiate i+1 number of players to include the dealer
		for(int i=0; i<numOfPlayers; i++)
		{
			int type = i;
			Player p;
			String name;
			IOHandler ioh = new IOHandler();
			name = ioh.queryInput("What's the name of player " + (i+1) + "?");
			
			switch (type) {
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
