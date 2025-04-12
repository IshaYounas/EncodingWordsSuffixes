package ie.atu.sw;

// imports
import java.util.Map;
import java.util.TreeMap;
import java.io.*; // files

public class Decoding // converting numbers to words
{
	// instance variables
	private Map<Integer, String> reverseMap;

	// constructor
	public Decoding(Map<Integer, String> mappings)
	{
		this.reverseMap= new TreeMap<>();
		
	} // Decoding
	
	public void decode(String in, String out)
	{
		try (BufferedReader read = new BufferedReader(new FileReader(in));
				BufferedWriter write = new BufferedWriter(new FileWriter(out)))
		{
			String line;
			
			// reading file line by line
			while ((line = read.readLine()) != null)
			{
				// storing the decoded line
				StringBuilder decodedLine = new StringBuilder();
				String[] codes = line.split(" "); 
				
				for (String code : codes)
				{
					try 
					{
						int number = Integer.parseInt(code);
						String word = reverseMap.get(number);
						
						if (word != null) // word found
						{
							decodedLine.append(word).append(" ");
						} // if
						
						else // word no found
						{
							decodedLine.append(" unknown ??? ").append(" ");
						} // else
					} // try 
					
					catch (NumberFormatException e) 
					{
						decodedLine.append(code).append(" ");
					}					
				} // for(each)
				
				// DEBUG
				//System.out.println("Decoded line: " + decodedLine.toString());
				
				write.write(decodedLine.toString().trim());
				write.newLine();
			} // while
			
			//write.flush(); // DEBUG
			System.out.println("Decoding complete, saved to " + out);
		} // try
		
		catch (Exception e) 
		{
			System.err.println("Error: " + e.getMessage());
		} // catch
	} // decode
} // class