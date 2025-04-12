package ie.atu.sw;

// imports
import java.util.Map;
import java.io.*; // files

public class Encoding // converting words to numbers
{	
	// instance variables
	private Map<String, Integer> mappings;

	// constructor
	public Encoding(Map<String, Integer> mappings)
	{
		this.mappings = mappings;
	} // Encoding
	
	public void encode(String in, String out)
	{
		try (BufferedReader read = new BufferedReader(new FileReader(in));
				BufferedWriter write = new BufferedWriter(new FileWriter(out)))
		{
			String line;
			
			// reading file line by line
			while ((line = read.readLine()) != null)
			{
				// storing the encoded line
				StringBuilder encodedLine = new StringBuilder();
				String[] words = line.split(" ");
				
				for (String word : words)
				{
					Integer encodedVal = mappings.get(word.toLowerCase());
					
					if (encodedVal == null) // word not in map
					{
						for (String key : mappings.keySet()) // check for suffix
						{
							if (word.toLowerCase().endsWith(key))
							{
								encodedVal = mappings.get(key);
								break;
							} // if
						} // for(each)
						
						// if suffix match is found
						if (encodedVal != null) // word is in map
						{
							encodedLine.append(encodedVal).append(" ");
						} // if
						
						else // word not in map
						{
							encodedLine.append(" unknown ??? ");
						} // else
					} // if
					
					else // word is in map
					{
						encodedLine.append(encodedVal).append(" ");
					} // else
				} // for(each)
				
				// DEBUG
				//System.out.println("Encoded line: " + encodedLine.toString());
				
				write.write(encodedLine.toString().trim());
				write.newLine();
			} // while
			
			//write.flush(); // DEBUG
			System.out.println("Encoding complete, saved to " + out);
		} // try
		
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		} // catch
	} // encode
} // class