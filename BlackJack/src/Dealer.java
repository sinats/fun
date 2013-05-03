
public class Dealer extends Player {
	private static final int minStand = 17;

	public Dealer() {
		super("Dealer");
	}

	@Override
	public Integer decide() {
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
