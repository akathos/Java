/*
 * Author: Agatha Man
 * agatha.man@gmail.com
 * 
 * Implement a circular buffer of size N. Allow the caller to append, 
 * remove and list the contents of the buffer. Implement the buffer to 
 * achieve maximum performance for each of the operations.
 * The new items are appended to the end and the order is retained 
 * i.e elements are placed in increasing order of their insertion time. 
 * When the number of elements in the list elements exceeds the defined 
 * size, the older elements are overwritten.
 * 
 * There are four types of commands.
 * 
 * "A" n - Append the following n lines to the buffer. If the buffer is 
 * 		   full they replace the older entries.
 * "R" n - Remove first n elements of the buffer. These n elements are
 *		   the ones that were added earliest among the current elements.
 * "L" - List the elements of buffer in order of their inserting time.
 * "Q" - Quit.
 * 
 * Your task is to execute commands on circular buffer.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class CircularBuffer 
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
		BufferedWriter output = null;
		try
		{
			output = new BufferedWriter(new OutputStreamWriter(System.out));
			
			for (String s : strings)
			{
				//System.out.println(s);
				output.write(s+"\n");
			}   	
			
			output.flush();
			output.close();
			
		} 
		catch (Exception e) 
		{
		    System.err.println("Caught Exception: " + e.getMessage());
		}
		
	}

	public static void run()
	{
		CircularBuffer cbuffer = new CircularBuffer();
		BufferedReader input = null;
		
		try 
		{
			input = new BufferedReader(new InputStreamReader(System.in));
			
			String text = null;
			int n = 0; // keep count of numbers of elements to append;
			Command command = Command.NONE; // last command from input
			
			// Read the first line for buffer size
			text = input.readLine();
			cbuffer.size(Integer.parseInt(text));
			
			// Read each line until we see the Q command
			while(true)
			{
				text = input.readLine();
				
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
			
			input.close();
			
		} 
		catch (Exception e) 
		{
		    System.err.println("Caught Exception: " + e.getMessage());
		}
	}
	
	public static void main(String args[]) 
	{
		run();
	}
}
