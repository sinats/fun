import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public abstract class Player {
	private double pocket = 10000;
	private Stack<Card> hands = new Stack<Card>();
	private int sum = 0;
	String name;
	double bet;
	double minBet;
	private double blackJack = 21;
	private static final String ACE = "ACE";
	
	public Player(String name) throws FileNotFoundException, UnsupportedEncodingException {
		this.name = name;
	}
	
	public abstract int decide();
	public abstract boolean stillIn();
	public abstract void bet();
		
	public void watch(List<Card> drawnCards) {}
	
	protected int sumHands()
	{
		return this.sum;
	}

	private int addMyNumbers(ArrayList<String> numbers) {
		Deck d = new Deck();
		int myHand = 0;
		for(String num : numbers)
			myHand += d.valuesMap.get(num);

		if(myHand > blackJack && numbers.contains(ACE))
			myHand -= 10;
		return myHand;
	}

	private ArrayList<String> getNumberFromHands() {
		ArrayList<String> ret = new ArrayList<String>();
		for(Card s : hands)
			ret.add(s.number);
		return ret;
	}

	public double getPocket() {
		return pocket;
	}

	public void setPocket(double pocket) {
		this.pocket = pocket;
	}

	public Stack<Card> getHands() {
		return hands;
	}
	
	public void clearHands()
	{
		this.hands.clear();
	}

	public void setHands(Card drawnCard) {
		this.hands.addElement(drawnCard);
		this.sum = addMyNumbers(getNumberFromHands());	
	}
	
	public void putBet()
	{
		if(this.bet < this.minBet) this.bet = this.minBet;
		if(this.bet > this.pocket) this.bet = this.pocket;
		this.setPocket(this.getPocket()-this.bet);
	}
}
