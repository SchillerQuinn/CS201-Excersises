import java.io.*;
import java.util.Scanner;
//My Partner is Andrew Marris

public class SpellCheck{
	
	public static void main(String[] args) {
		UnorderedListADT<String> wordList = new LinkedUList<String>();	//create a list to hold the words
		Scanner s = null;	//initialize scanner
		System.out.println("Loading wordlist..."); //verbose output because this takes longer than the average opperation
		try { //put in try-catch block to avoid filenotfound exceptions
			s = new Scanner(new File("wordlist"));	//scan the list of words
			while(s.hasNextLine()){	//loop through the list of words 
				wordList.add(s.nextLine(),0);		//add scanned words to the list in the right index
			}
		} 
		catch(FileNotFoundException e) {			//if they don't have the wordlist
			System.out.println("Unable to find wordlist file.");	//raise warning and exit
			System.exit(0);
		}

		System.out.println("Successfully loaded");

		Scanner userScan = new Scanner(System.in);	//start scanning for user input

		boolean run = true;
		while(run){
			System.out.print("Enter word to spell check: ");	//prompt for input
			String userInput = userScan.nextLine();	//get input
			if(userInput.equals("")){	//check for empty input
				run = false;	//exit loop
			}
			else{ //check the word list for user entered word and give the correct output
				if(wordList.contains(userInput)){
					System.out.println(userInput + " is spelled correctly");	
				}
				else{
					System.out.println(userInput + " is not spelled correctly");
				}
			}
		}
		System.out.println("Goodbye!"); //show the program has ended
	}
}