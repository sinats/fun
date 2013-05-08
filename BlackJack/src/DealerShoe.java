import java.util.HashMap;
import java.util.Random;
import java.util.Stack;


public class DealerShoe {
	Stack<Card> cards = new Stack<Card>();
	Stack<Card> usedCards = new Stack<Card>();
	private int minShuffle = 200;
	
	public DealerShoe(int numOfDecks)
	{
		Random rand = new Random();
		int shuffle = rand.nextInt(200);
		for(int i=0; i<numOfDecks; i++)
		{
			Deck deck = new Deck();
			cards.addAll(deck.get());
		}
		for(int i=0; i<shuffle+minShuffle; i++)
			shuffle();
	}
	
	public Card draw()
	{
		return cards.pop();
	}
	
	public void shuffle()
	{
		Stack<Card> temp = new Stack<Card>();
		temp.addAll(cards);
		Random rand = new Random(System.currentTimeMillis());
		this.cards.clear();
		while(!temp.isEmpty())
		{
			int pos = rand.nextInt(temp.size());
			this.cards.add(temp.get(pos));
			temp.remove(pos);
		}
		validate();
	}

	private void validate() {
		HashMap<String, Integer> validate = new HashMap<String, Integer>();
		Stack<Card> temp = new Stack<Card>();
		temp.addAll(cards);
		while(!temp.isEmpty())
		{
			Card pop = temp.pop();
			if(validate.containsKey(pop.number))
			{
				int i = validate.get(pop.number);
				validate.put(pop.number, ++i);
			}
			else
			{
				validate.put(pop.number, 1);
			}
		}
	}
}
