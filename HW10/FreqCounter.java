//Quinn Schiller and Andrew Maris

import java.io.*;
import java.util.Scanner;
public class FreqCounter{
	
	public static HashMap<String, Integer> words = new HashMap<String, Integer>();

	public static void main(String[] args) {
		Scanner s = null;	//initialize scanner
		
		Scanner userScan = new Scanner(System.in); //initialize a second scanner
		System.out.print("Enter the file name to count: "); //prompt user for input
		String fileName = userScan.nextLine();	//get file name

		System.out.println("Loading words..."); //verbose output because this takes longer than the average opperation

		try { //put in try-catch block to avoid filenotfound exceptions
			s = new Scanner(new File(fileName));	//scan the list of words
			while(s.hasNextLine()){	//loop through the list of words
				//check to see if if the hashtable already has this term.
				String foo = s.next();	//get then next word in the txt file
				foo = foo.replaceAll("[^a-zA-Z]", "").toLowerCase();	//remove non-letter characters and make everything lowercase
				Integer count = words.remove(foo);	//remove the old key and get the count;
				if (count != null){ //if there was a word removed
					words.put(foo, count + 1);	//put in the word with a new value  
				}
				else{ //if there was not a word removed
					words.put(foo,1);		//add scanned words to the list in the correct index
				}
			}
		} 
		catch(FileNotFoundException e) {			//if they don't have the wordlist
			System.out.println("Unable to find wordlist file.");	//raise warning and exit
			System.exit(0);
		}

		words.out();	//output
	}
}