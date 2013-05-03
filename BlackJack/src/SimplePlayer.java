


public class SimplePlayer extends Player {

	public SimplePlayer(String name) {
		super(name);
	}

	public Integer decide() {
		int myHand = this.sumHands();
		System.out.println(this.name + " has " + myHand);
		if(myHand < 17)
			return 1;
		else
			return 0;
	}

	public boolean stillIn() {
		if(this.getPocket() >= 200 || this.getPocket() < 5)
			return false;
		else 
			return true;
	}

	public void bet() {
		this.bet = 10;
		System.out.println( this.name+ " bets 10!");
		this.setPocket(this.getPocket()-10);
	}
	
	
}
