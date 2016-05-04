//I wrote my code before you published your code. Sorry if it isn't standard.


public class PrimeFinder{
	/********
	 * Main method
	 *********/
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);      //set the number you want to find primes under with the first arg
		boolean[] out = new boolean[n];
		PrimeFinder pf = new PrimeFinder();
		switch(args[1].charAt(0)) {             //second arg switches algorithm methods
			case 'A':
			case 'a':
				out = pf.sieve(n);
				break;
			case 'B':
			case 'b':
				out = pf.iterative(n);
				break;
			default:
				System.err.println("Error, <Algorithm> should be A (for sieve), or B (for iterative)");
				System.exit(1);
		}
		try{																		//if they don't enter in a third argument it will raise an exception error
			if (args[2].charAt(0) == 'v'){ 				//if they pass a verbose argument, print the output
				for (int i = 0; i < n; i++){
					System.out.print(i);
					System.out.print("\t");
					System.out.println(out[i]);
				}
			}
		}
		catch (Exception e){
		//do nothing if they don't enter in a third argument
		}
	}
		
	public boolean[] sieve(int n){
		boolean primes[] = new boolean[n];			//make a list the length of n saying true or false if the number is a prime or not
		for(int i = 2; i < n; i++) {						//set them all equal to true to start
			primes[i] = true;
		}		
		for (int i = 2; i*i < n; i++){					//start at 2, test every number less than the square root of n.
			if (primes[i]==true){ 								//only runs on new primes
				for(int s = i; s*i<n; s++){					//set all multiples of that number within the range to false
					primes[s*i]=false;
				}
			}
		}
		return primes;													//return the boolean list of primes
	}

	public boolean isPrime(int n){
		for (int i=2; i<n; i++){								//test to see if n is divisible by all numbers less than it
			if(n%i == 0){
				return false;												//if it is divisible, return that it isn't a prime
			}
		}
		return true;														//if it pasts all tests, return true
	}

	public boolean[] iterative(int n){
		boolean primes[] = new boolean[n];			//make a list the length of n saying true or false if the number is a prime or not
		for (int i = 0; i < n; i++){
			primes[i] = isPrime(i);								//test every number in the list to see if it is a prime or not.
		}
		return primes;													//return the boolean list of primes
	}
}