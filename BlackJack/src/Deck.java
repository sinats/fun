import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;


public class Deck 
{
	// {HEARTS, TWO}, {SPADES, TEN}
	Stack<Card> deck = new Stack<Card>();
	HashMap <String, Integer> valuesMap = new HashMap<String, Integer>(13);
	
	public Deck()
	{
		String[] suits = {"HEARTS", "SPADES", "CLUBS", "DIAMONDS"};
		valuesMap.put("TWO", 2);
		valuesMap.put("THREE", 3);
		valuesMap.put("FOUR", 4);
		valuesMap.put("FIVE", 5);
		valuesMap.put("SIX", 6);
		valuesMap.put("SEVEN", 7);
		valuesMap.put("EIGHT", 8);
		valuesMap.put("NINE", 9);
		valuesMap.put("TEN", 10);
		valuesMap.put("JACK", 10);
		valuesMap.put("QUEEN", 10);
		valuesMap.put("KING", 10);
		valuesMap.put("ACE", 11);
		for(int i=0; i<suits.length; i++)
		{
			for(Entry<String, Integer> j : valuesMap.entrySet())
			{
				deck.push(new Card(suits[i],j.getKey(),j.getValue()));
			}
		}
	}
	
	public Stack<Card> get()
	{
		return deck;		
	}
}
