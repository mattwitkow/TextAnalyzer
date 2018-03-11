
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
public class Dictionary {
	public static void main(String[] args)
	{
		Scanner fileScanner = null;
		try {
		fileScanner = new Scanner(new File("Alice.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		while(fileScanner.hasNextLine())
		{
		line += fileScanner.nextLine();
		}
		line = line.replaceAll("[^a-zA-Z]", " ");
		line = line.toLowerCase();
		System.out.print(line);
		Dictionary dictionary = new Dictionary();
		ArrayList<String> lineList = new ArrayList<String>();
		for(String word : line.split(" ")) 
		{
		    if(word.length()> 0 && !word.contains(" "))
		    {
		    	lineList.add(word);
		    }
		}
		
		for(int i = 0; i < lineList.size() ; i++)
		{
			dictionary.put(lineList.get(i));
		}
		
      System.out.println(Arrays.toString(dictionary.getTopWords(100)));	
	}
	private ArrayList<ArrayList<Entry>> dictionary;
	private int total;
	private Entry entry;
	public Dictionary()
	{
		total = 0;
		dictionary = new ArrayList<ArrayList<Entry>>(26);
		for(int i = 0; i < 26; i++) 
		{
	        dictionary.add(new ArrayList<Entry>());
	    }
	}
	
	public void put(String word)
	{
		word = word.toLowerCase();
		Entry entry  = new Entry(word);
		boolean place= true;
		if(word.length() > 0 && !word.contains(" "))
		{
			for(int i = 0; i < dictionary.get(word.charAt(0)-'a').size() ; i++ )
			{
				place = true;
				if(dictionary.get(word.charAt(0)-'a').get(i).equals(entry));
				{
					dictionary.get(word.charAt(0) - 'a').get(i).count++;
					place = false;
				}	
			}
			if(place)
			{
				if(word.length() > 0 && !word.contains(" "))
				{
					dictionary.get(word.charAt(0) - 'a').add(0,entry);
					dictionary.get(word.charAt(0)- 'a').get(0).count++;
					total++;
				}
			}
			
				
		}

		
		}
	
	public double getOcc(String word )
	{
		int counter = 0;
		char start = word.charAt(0);
		Iterator<Entry> itr = dictionary.get(word.charAt(0) - 'a').iterator();
		Entry com = new Entry(word);
		while (itr.hasNext())
			if(itr.next().equals(com))
			{
				counter ++;
			}
		return counter;
	}
	public double remove(String word)
	{
		Iterator<Entry> itr = dictionary.get(word.charAt(0) - 'a').iterator();
		Entry com = new Entry(word);
		double timesRemoved = 0;
		while (itr.hasNext())
			if(itr.next().equals(word))
			{
				dictionary.get(word.charAt(0) - 'a').remove(word);
				timesRemoved = 0;
			}
		return timesRemoved;
	}
	public double getAverageLength()
	{
		double totalLength = 0;
		for(int i = 97; i <= 122; i++)
		{
			Iterator<Entry> itr = dictionary.get( i - 'a').iterator();
			while (itr.hasNext())
			{
				totalLength += itr.next().getLength();
			}
		}
		if( total != 0)
		{
			return totalLength / total;
		}
		else return 0;

	}
	public double getAverageFreq()
	{
		double totalOccur = 0;
	
		for(int i = 97; i <= 122; i++)
		{
			Iterator<Entry> itr = dictionary.get( i - 'a').iterator();
			while (itr.hasNext())
			{
				totalOccur += itr.next().getCount();
				
			}
		}
		return totalOccur/total;
	}
	public String[] getTopWords(int top)
	{
		ArrayList<Integer> freq = new ArrayList<Integer>();
		String[] topWords = new String[top];
			
				for(int i = 97; i <= 122; i++)
				{
					Iterator<Entry> itr = dictionary.get( i - 'a').iterator();
					while (itr.hasNext())
					{
						freq.add(itr.next().count);
					}
				}
			Collections.sort(freq, Collections.reverseOrder());
			for(int k = 0; k < top ; k++)
			{
				for(int i = 97; i <= 122; i++)
				{
					int h = 1;
					Iterator<Entry> it = dictionary.get( i - 'a').iterator();
					while (it.hasNext() && k < top)
					{
						if( h == 1 )
						{
							topWords[k] = it.next().word;
							k++;
						}
								
					}
				}
			}
			return topWords;
	}

public class Entry {
	private String word;
	private int count;
	public Entry(String word)
	{
		this.word = word;
		count = 0;
		
	}
	public int getCount()
	{
		return count;
	}
	public void count()
	{
		count++;
	}
	public boolean equals(Entry com)
	{
		if(this.word.equals(com.getWord()))
			return true;
		else {
			return false;
		}
	}
	public String getWord()
	{
		return word;
	}
	public int getLength()
	{
		return word.length();
	}

	}
}