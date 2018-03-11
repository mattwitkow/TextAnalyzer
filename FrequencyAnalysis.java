import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;


public class FrequencyAnalysis {
	public static void main (String[] args){
//Commented out is how to use with url. can uncomment if url is not broken	
//		Scanner input = null;
//		try{
//			URL url = new URL("http://www.gutenberg.org/cache/epub/11/pg11.txt");
//			input = new Scanner(url.openStream());
//			}
//			catch(Exception e){
//				
//			}
//	int[]counts = new int [26];

//	String readMe = input.nextLine();
//	while(input.hasNextLine())
//	{
//	readMe += input.nextLine();
//	}
//	System.out.println(readMe);
//	count(readMe, counts);
//	System.out.println(Arrays.toString(counts));
    int[] aliceCounts = new int[26];
	Scanner fileScanner = null;
	try {
	fileScanner = new Scanner(new File("Alice.txt"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	String alice = fileScanner.nextLine();
	while(fileScanner.hasNextLine())
	{
	alice += fileScanner.nextLine();
	}
	
	
	count(alice, aliceCounts);
	System.out.println(Arrays.toString(aliceCounts));
	
	double[]p = new double[26];
	displayCounts(alice, p);
	System.out.println(Arrays.toString(p));
	System.out.print("a                     b                     c                     d                     e                     f                     g                     h                     i                     j                     k                     l                     m                     n                     o                     p                     q                     r                     s                     t                     u                     v                     w                     x                     y                     z ");
	
	}
	
	public static void count(String line, int[] counts)
	{
		String lower = line.toLowerCase();
		for(int i = 0; i < lower.length(); i++)
		{
			if(lower.charAt(i) < 123 && lower.charAt(i) >= 97 )
			{
				char current = lower.charAt(i);
				counts[current - 'a'] +=1;
			}
		}
	}
	public static void displayCounts(String line, double[] counts)
	{
		String lower = line.toLowerCase();
		double letterCount = 0;
		for(int i = 0; i < lower.length(); i++)
		{
			if(lower.charAt(i) <= 123 && lower.charAt(i) >= 97 )
			{
				char current = lower.charAt(i);
				counts[current - 'a'] +=1;
				letterCount++;
			}
		}
		for(int i = 0; i < counts.length;i++)
		{
			
			counts[i] = (counts[i]/letterCount);
		}
	}
	
	
}
