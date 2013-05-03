import java.util.Random;
import java.util.Stack;


public class DealerShoe {
	Stack<String[]> cards = new Stack<String[]>();
	Stack<String[]> usedCards = new Stack<String[]>();
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
	
	public String[] draw()
	{
		if(!cards.isEmpty())
			return cards.pop();
		else
		{
			cards.addAll(usedCards);
			usedCards.clear();
			for(int i=0; i<100; i++)
				shuffle();
			return cards.pop();
		}
	}
	
	public void shuffle()
	{
		Stack<String[]> stack1 = new Stack<String[]>();
		Stack<String[]> stack2 = new Stack<String[]>();
		int size = cards.size();
		int i = 0;
		while(i<size/2)
		{
			stack1.push(cards.pop());
			i++;
		}
		while(!cards.isEmpty())
			stack2.push(cards.pop());
		while(!stack1.isEmpty() || !stack2.isEmpty())
		{
			Random rand = new Random();
			int split = rand.nextInt(1);
			for(i=0;i<=split;i++)
			{
				if(!stack1.isEmpty())
					cards.push(stack1.pop());
			}
				
			if(!stack2.isEmpty())
				cards.push(stack2.pop());
		}	
	}
}
