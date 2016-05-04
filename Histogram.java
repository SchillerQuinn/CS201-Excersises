public class Histogram{
	/*
	Instance variables
	*/

	//array for holding scores
	private int[] scores = new int[10];

	/********
	Methods
	*********/


	/*
	Method for adding a score to the histogram. 
	*/
	public void addScore(int score){
		//make sure the inputted value is legal
		if(score <= 50 && score >= 1){
			//find what bin the score would be in
			int bin = ((score-1)/5);
			//increase the value of that bin by one
			scores[bin] ++;
		}
		else{
			//if it is an invalid entry, raise an error.
			System.out.println("Invalid response, please enter a new number");
		}
	}
	
	/*
	Method for printing the histogram
	*/
	public void print(){
		//print stuff
		for (int bin = 0; bin<10; bin++){
			
			//print the low and high ends of the bin
			System.out.print((5*bin)+1); //1,6,11,16... for example
			System.out.print("-");
			System.out.print(5*(bin+1)); //5,10,15,20... for example

			//print the bar to separate the dots of the histogram
			System.out.print("\t| ");
			
			//print the number of dots equal to the number of scores in that bin. 
			//if there are 0 scores in that bin it will not print anything.
			for(int i= 0; i<scores[bin]; i++){
				System.out.print("*");
			}

			//go to the next line
			System.out.println("");
		}
	}
	
	/*
	Sample MAIN method
	*/
	public static void main(String[] args) {
        Histogram h = new Histogram();
        h.addScore(24);
        h.addScore(37);
        h.addScore(13);
        h.addScore(36);
        h.addScore(3);
        h.addScore(22);
        h.addScore(50);
        h.addScore(50);
        h.print();
	}
}