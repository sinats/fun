import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public abstract class Player {
	private double pocket = 100;
	private Stack<String[]> hands = new Stack<String[]>();
	String name;
	double bet;
	private double blackJack = 21;
	private static final String ACE = "ACE";
	
	public Player(String name) {
		this.name = name;
	}
	
	public abstract Integer decide();
	public abstract boolean stillIn();
	public abstract void bet();
	
	public boolean isBankrupt()
	{
		if(getPocket() < .01){ 
			return true;
		} else {
			return false;
		}
	}
	
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
		for(String[] s : hands)
			ret.add(s[1]);
		return ret;
	}

	public double getPocket() {
		return pocket;
	}

	public void setPocket(double pocket) {
		this.pocket = pocket;
		System.out.println( this.name + "'s pocket is now " + this.pocket);
	}

	public Stack<String[]> getHands() {
		return hands;
	}
	
	public void clearHands()
	{
		this.hands.clear();
	}

	public void setHands(String[] cards) {
		System.out.println( this.name + " draws " + Arrays.toString(cards));
		this.hands.addElement(cards);
	}
}
