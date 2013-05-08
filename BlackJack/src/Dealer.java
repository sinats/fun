import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


public class Dealer extends Player {
	private static final int minStand = 17;

	public Dealer() throws FileNotFoundException, UnsupportedEncodingException {
		super("Dealer");
	}

	public int decide() {
		if(this.sumHands() < minStand)
			return 1;
		else
			return 0;
	}

	@Override
	public boolean stillIn() {
		return true;
	}

	@Override
	public void bet() {}
}
