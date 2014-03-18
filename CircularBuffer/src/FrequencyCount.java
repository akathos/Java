import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * Author: Agatha Man
 * agatha.man@gmail.com
 * 
 * Frequency Counting of Words / Top N words in a document.
 * Given N terms, your task is to find the k most frequent terms from given N terms.
 * 
 * Input format
 * 
 * First line of input contains N, denoting the number of terms to add.
 * In each of the next N lines, each contains a term.
 * Next line contains k, most frequent terms.
 * 
 * Output format
 * 
 * Print the k most frequent terms in descending order of their frequency. 
 * If two terms have same frequency print them in lexicographical order.
 */ 
public class FrequencyCount 
{
	private static final int MIN_N = 0;
	private static final int MAX_N = 300000;
	
	int n,	// number of terms 
		k;	// number of frequent terms
	String[] terms;
	
	
	private void frequentTerms()
	{
		// Sort terms
		// 
		
	}
	
	public void loadFile(String filename)
	{
		// Open file for reading
		File file = new File(filename);
		BufferedReader reader = null;
		
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			
			// Get number of terms to add
			text = reader.readLine();
			n = Integer.parseInt(text);
			
			// n is not in not in valid range 
			if(n <= MIN_N || n >= MAX_N)
			{
				reader.close();
				throw new Exception("Invalid number of terms entered.");
			}
			
			terms = new String[n];
			
			// Read in each term
			for(int i = 0; i < n; ++i)
			{
				terms[i] = reader.readLine();				
			}
			
			// Get the k value
			text = reader.readLine();
			k = Integer.parseInt(text);
			
			// Find the k frequent terms
		} catch (Exception e) {
			System.err.println("Caught Exception: " + e.getMessage());
		}
		
	}
	
	/*public static void main(String args[]) 
	{
		FrequencyCount fc = new FrequencyCount();
		fc.loadFile("Input2.txt");
		fc.frequentTerms();
	}*/
}
