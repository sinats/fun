import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class CountingPlayer extends Player{
	private int sum = 0;

	public CountingPlayer(String name) throws FileNotFoundException, UnsupportedEncodingException {
		super(name);
	}

	@Override
	public int decide() {
		int myHand = this.sumHands();
		System.out.println(this.name + " has " + myHand);
		if(myHand < 15)
			return 1;
		else
			return 0;
	}

	@Override
	public boolean stillIn() {
		if(this.getPocket() >= 20000 || this.getPocket() < this.minBet)
			return false;
		else 
			return true;
	}

	@Override
	public void bet() {
		pw.println(this.sum);
		if(this.sum > 10) 
			this.bet = (int)this.getPocket()/5; 
		else if (this.sum>5) 
			this.bet = (int)this.getPocket()/20;
		else 
			this.bet = this.minBet;
		putBet();
	}
	public void watch(List<Card> drawnCards)
	{
		for(Card drawn : drawnCards)
		{
			if(drawn.num <= 6) this.sum++; else if(drawn.num >=10) this.sum--;
		}
	}
}
