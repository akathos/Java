import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CircularBuffer 
{
	private static final String CMD_APPEND = "A ";
	private static final String CMD_REMOVE = "R ";
	private static final String CMD_LIST = "L";
	private static final String CMD_QUIT = "Q";
	private static final int MIN_SIZE = 0;
	private static final int MAX_SIZE = 10000;
	
	public enum Command 
	{
		NONE, APPEND, REMOVE, LIST, QUIT
	}
	
	ArrayList<String> strings = new ArrayList<String>();
	int bufferSize; 		// max strings the buffer can have 
	
	/*
	 * Set  the size of the circular buffer
	 */
	public void size(int size)
	{
		if(size >= MIN_SIZE && size <= MAX_SIZE)
			bufferSize = size;
	}
	
	/*
	 * Append the new string to the buffer. 
	 * If the buffer is full remove the first element.
	 */
	public void append(String string)
	{
		// The buffer has size 0 and will not have items
		if(bufferSize == 0)
			return;

		if(strings.size() == bufferSize)
			strings.remove(0);
		
		strings.add(string);
	}
	
	/*
	 * Remove the first n elements
	 */
	public void remove(int n)
	{
		while(n > 0)
		{
			strings.remove(0);
			--n;
		}
	}
	
	/*
	 * List all elements in the buffer starting 
	 * with the element first entered.
	 */
	public void list()
	{
		for (String s : strings)
		{
			System.out.println(s);
		}   	
	}

	public static void run()
	{
		CircularBuffer cbuffer = new CircularBuffer();
		String fileName = "Input1.txt"; 
		
		// Open file for reading
		File file = new File(fileName);
		BufferedReader reader = null;
		
		try 
		{
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			int n = 0; // keep count of numbers of elements to append;
			Command command = Command.NONE; // last command from input
			
			// Read the first line for buffer size
			text = reader.readLine();
			cbuffer.size(Integer.parseInt(text));
			
			// Read each line until we see the Q command
			while((text = reader.readLine()) != null)
			{
				if(text.length() == 0) continue;
				if(text.startsWith(CMD_APPEND))
				{
					command = Command.APPEND;
					n = Integer.parseInt(text.substring(2));
					continue;
				}
				if(text.startsWith(CMD_REMOVE))
				{
					command = Command.REMOVE;
					cbuffer.remove(Integer.parseInt(text.substring(2)));
					continue;
				}
				if(text.startsWith(CMD_LIST))
				{
					command = Command.LIST;
					cbuffer.list();
					continue;
				}
				if(text.startsWith(CMD_QUIT))
					break;
				
				// This line is not a command
				if(command == Command.APPEND && n > 0)
				{
					cbuffer.append(text);
					--n;
				}
			}
			
			reader.close();
			
		} catch (Exception e) {
		    System.err.println("Caught Exception: " + e.getMessage());
		}
	}
	
	public static void main(String args[]) 
	{
		run();
	}
}
