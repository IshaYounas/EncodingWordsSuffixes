package ie.atu.sw;

// imports
import java.util.Map;
import java.util.TreeMap;
import java.io.*; // file imports

public class LoadFile 
{
	// instance variable
	private Map<String, Integer> m = new TreeMap<>(); // type string & integer
	
	// custom methods
	public void parse(String file)
	{
		try (var in = new BufferedInputStream(new FileInputStream(new File (file))))
		{
			var line = "";
			
			// read text using buffered reader
			BufferedReader read = new  BufferedReader(new InputStreamReader(in)); 
			
			while ((line = read.readLine()) != null)
			{
				String[] pair = line.split(","); // splitting by comma
				m.put(pair[0], Integer.parseInt(pair[1])); // storing in a tree map
			} // while
		} // try
		
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		} // catch
	} // parse
	
	public Map<String,Integer> getMappings() 
	{
		return m; // returning the map
	} // getMappings
} // class