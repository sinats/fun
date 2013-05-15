import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class SimplePlayer extends Player {
	

	public SimplePlayer(String name) throws FileNotFoundException, UnsupportedEncodingException {
		super(name + "_sim");
		super.setPocket(100);
	}

	public int decide() {
		int myHand = this.sumHands();
		if(myHand < 17)
			return 1;
		else
			return 0;
	}

	public boolean stillIn() {
		if(this.getPocket() >= 200000 || this.getPocket() < this.minBet)
			return false;
		else 
			return true;
	}

	public void bet() {
		this.bet = (int)this.getPocket()/1000;
		putBet();
	}
}
