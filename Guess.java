import java.util.Scanner;

public class Guess {
	
	/*
	MAIN method
	*/
	public static void main(String[] args) {
		//initialize a scanner for users to input into
		Scanner foo = new Scanner(System.in);
		//pick a number between 1 and 100 and convert it into an int.
		int num = (int) (Math.random() * 100);
		//initialize the guess value with an incorrect value so the loop will repeat no matter what.
		int guess = -1; 
		
		//repeat this loop until they guess the number correctly
		while (guess != num){ 
			//ask the user to input a new number
			System.out.println("Please guess a number between 0 and 100:");
			//get input from user
			guess = foo.nextInt();	
			
			//test if the guess was correct
			if (guess == num){
				System.out.println("You got it!");
				//the program automatically exit after this because the while loop now evaluates to false because the guess is equal to num.
			}
			//if the guess wasn't correct, test if it was larger or smaller than num and print the appropriate response
			else if (guess > num){
				System.out.println("Smaller!");
			}
			else{
				System.out.println("Larger!");
			}
		}
	}
}