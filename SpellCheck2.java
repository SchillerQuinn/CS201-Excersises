import java.io.*;
import java.util.Scanner;

public class SpellCheck2{
	
	public static void main(String[] args) {
		UnorderedListADT<String> wordList1 = new LinkedUList<String>();	//create a list to hold the words
		StringComparator comp = new StringComparator();	//create comparator for ordered list
		OrderedListADT<String> wordList2 = new OrderedAList<String>(comp);	//create orderedlist
		Scanner s = null;	//initialize scanner
		System.out.println("Loading wordlist into unsorted list..."); //verbose output because this takes longer than the average opperation
		long startTime = System.currentTimeMillis();
		try { //put in try-catch block to avoid filenotfound exceptions
			s = new Scanner(new File("wordlist"));	//scan the list of words
			while(s.hasNextLine()){	//loop through the list of words 
				wordList1.add(s.nextLine(),0);		//add scanned words to the list in the right index
			}
		} 
		catch(FileNotFoundException e) {			//if they don't have the wordlist
			System.out.println("Unable to find wordlist file.");	//raise warning and exit
			System.exit(0);
		}
		long endTime = System.currentTimeMillis();	
		long elTime = endTime-startTime;//find difference in start and end times
		System.out.println("Loaded in "+ elTime + "miliseconds");	//say how long it took

		System.out.println("Loading wordlist into sorted list..."); //verbose output because this takes longer than the average opperation
		startTime = System.currentTimeMillis();
		try { //put in try-catch block to avoid filenotfound exceptions
			s = new Scanner(new File("wordlist"));	//scan the list of words
			while(s.hasNextLine()){	//loop through the list of words 
				wordList2.add(s.nextLine());		//add scanned words to the list in the right index
			}
		} 
		catch(FileNotFoundException e) {			//if they don't have the wordlist
			System.out.println("Unable to find wordlist file.");	//raise warning and exit
			System.exit(0);
		}
		endTime = System.currentTimeMillis();	//find difference in start and end times
		elTime = endTime-startTime;//find difference in start and end times
		System.out.println("Loaded in "+ elTime + " miliseconds"); //say how long it took

		Scanner userScan = new Scanner(System.in);	//start scanning for user input

		boolean run = true;
		while(run){
			System.out.print("Enter word to spell check: ");	//prompt for input
			String userInput = userScan.nextLine();	//get input
			if(userInput.equals("")){	//check for empty input
				run = false;	//exit loop
			}
			else{ //check the word list for user entered word and give the correct output
				startTime = System.nanoTime();
				if(wordList1.contains(userInput)){ //check unordered list
					endTime = ystem.nanoTime();
					elTime = endTime-startTime;//find difference in start and end times
					System.out.print(userInput + " is spelled correctly ");	
					System.out.println("(Took "+ elTime + " nanoseconds for unordered search)");	
				}
				else{
					endTime = System.nanoTime();
					elTime = endTime-startTime;//find difference in start and end times
					System.out.print(userInput + " is not spelled correctly ");
					System.out.println("(Took "+ elTime + " nanoseconds for unordered search)");	
				}
				startTime = System.nanoTime();
				
				if(wordList2.contains(userInput)){ //check ordered list
					endTime = System.nanoTime();
					elTime = endTime-startTime;//find difference in start and end times
					System.out.print(userInput + " is spelled correctly ");	
					System.out.println("(Took "+ elTime + " nanoseconds for ordered search)");	
				}
				else{
					endTime = System.nanoTime();
					elTime = endTime-startTime;//find difference in start and end times
					System.out.print(userInput + " is not spelled correctly ");
					System.out.println("(Took "+ elTime + " nanoseconds for ordered search)");	
				}
			}
		}
		System.out.println("Goodbye!"); //show the program has ended
	}
}