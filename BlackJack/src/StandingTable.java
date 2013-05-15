import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class StandingTable {
	HashMap<String, ArrayList<Double>> table = new HashMap<String, ArrayList<Double>>();
	
	public StandingTable(List<String> players)
	{
		for(String name : players)
		{
			table.put(name, new ArrayList<Double>());
		}
	}
	
	public void append(String name, double point)
	{
		ArrayList<Double> array = table.get(name);
		array.add(point);
		table.put(name, array);
	}
	
	public String getResult()
	{
		int max = 0;
		Set<String> names = table.keySet();
		String ret = "";
		for(String h : names)
		{
			if(h == null)
				break;
			else
				ret += String.format("%1$10s", h);
		}
		ret += String.format("\n");	
		
		for(ArrayList<Double> v : table.values())
			if(v.size() > max) max = v.size();

		for(int i=0; i<max;i++)
		{
			for(String name : names)
			{
				if(table.get(name).size()>i)
					ret += String.format("%1$10s", Double.toString(table.get(name).get(i)));
				else
					ret += String.format("%1$10s", "");
			}
			ret += String.format("\n");	
		}
		return ret;
	}
}
