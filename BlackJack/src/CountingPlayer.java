import java.util.List;


public class CountingPlayer extends Player{
	private int sum = 0;

	public CountingPlayer(String name) {
		super(name);
	}

	@Override
	public int decide(List<Card> drawnCards) {
		for(Card drawn : drawnCards)
		{
			if(drawn.num <= 7) sum++; else sum--;
		}
		int myHand = this.sumHands();
		System.out.println(this.name + " has " + myHand);
		if(myHand < 17)
			return 1;
		else
			return 0;
	}

	@Override
	public boolean stillIn() {
		if(this.getPocket() >= 5000 || this.getPocket() < this.minBet)
			return false;
		else 
			return true;
	}

	@Override
	public void bet() {
		if(this.sum > 8) this.bet = 5*this.minBet; else this.bet = this.minBet;
		putBet();
	}

}
