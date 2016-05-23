import java.io.*;
import java.util.Scanner;
public class FreqCounter{
	
	public static HashMap<String, Integer> words = new HashMap<String, Integer>();

	public static void main(String[] args) {
		Scanner s = null;	//initialize scanner
		
		Scanner userScan = new Scanner(System.in);
		System.out.print("Enter the file name to count: ");
		String fileName = userScan.nextLine();	//get file name

		System.out.println("Loading words..."); //verbose output because this takes longer than the average opperation

		try { //put in try-catch block to avoid filenotfound exceptions
			s = new Scanner(new File(fileName));	//scan the list of words
			while(s.hasNextLine()){	//loop through the list of words 
				words.put(s.next(),0);		//add scanned words to the list in the right index
			}
		} 
		catch(FileNotFoundException e) {			//if they don't have the wordlist
			System.out.println("Unable to find wordlist file.");	//raise warning and exit
			System.exit(0);
		}
		
		System.out.println(words.toCount());
	}
}