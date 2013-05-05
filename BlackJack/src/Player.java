import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public abstract class Player {
	private double pocket = 1000;
	private Stack<Card> hands = new Stack<Card>();
	String name;
	double bet;
	double minBet;
	private double blackJack = 21;
	private static final String ACE = "ACE";
	
	public Player(String name) {
		this.name = name;
	}
	
	public abstract int decide(List<Card> drawnCards);
	public abstract boolean stillIn();
	public abstract void bet();
		
	protected int sumHands()
	{
		ArrayList<String> numbers = getNumberFromHands();
		return addMyNumbers(numbers);		
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
		System.out.println( this.name + "'s pocket is now " + this.pocket);
	}

	public Stack<Card> getHands() {
		return hands;
	}
	
	public void clearHands()
	{
		this.hands.clear();
	}

	public void setHands(Card drawnCard) {
		System.out.println( this.name + " draws " + drawnCard.print());
		this.hands.addElement(drawnCard);
	}
	public void putBet()
	{
		if(this.bet < this.minBet) this.bet = this.minBet;
		if(this.bet > this.pocket) this.bet = this.pocket;
		System.out.println( this.name+ " bets " + this.bet + "!");
		this.setPocket(this.getPocket()-this.bet);
	}
}
