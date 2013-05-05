import java.util.List;

public class SimplePlayer extends Player {

	public SimplePlayer(String name) {
		super(name + "_sim");
	}

	public int decide(List<Card> drawnCards) {
		int myHand = this.sumHands();
		System.out.println(this.name + " has " + myHand);
		if(myHand < 17)
			return 1;
		else
			return 0;
	}

	public boolean stillIn() {
		if(this.getPocket() >= 2000 || this.getPocket() < this.minBet)
			return false;
		else 
			return true;
	}

	public void bet() {
		this.bet = (int)this.getPocket()/10;
		putBet();
	}
}
