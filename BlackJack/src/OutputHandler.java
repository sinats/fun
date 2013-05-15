public class OutputHandler {
	boolean print;
	
	public OutputHandler(boolean print)
	{
		this.print = print;
	}
	
	public void print(String text)
	{
		if(this.print)
			System.out.println(text);
	}
}
