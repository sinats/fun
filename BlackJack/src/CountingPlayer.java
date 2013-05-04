import java.util.List;


public class CountingPlayer extends Player{
	private int sum = 0;

	public CountingPlayer(String name) {
		super(name);
	}

	@Override
	public int decide(List<Card> drawnCards) {
		return sum;

	}

	@Override
	public boolean stillIn() {
		if(this.getPocket() >= 200 || this.getPocket() < 5)
			return false;
		else 
			return true;
	}

	@Override
	public void bet() {
		// TODO Auto-generated method stub
		
	}

}
