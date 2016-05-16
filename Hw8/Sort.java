/******************
 * A class that sorts file input
 *
 * java -s file.txt
 *   (for selection sort)
 *
 * java -m file.txt
 *   (for merge sort)
 *
 * java -q file.txt
 *   (for quick sort)
 *
 * java -r file.txt
 *   (for radix sort)
 *
	@original author Andy Exley


	@submitted by Quinn Schiller
 *****************/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collections;	//use built-in queue implimentation to save time 
import java.util.Queue;	//use built-in queue implimentation to save time
import java.util.Stack; //use built-in stack implimentation to save time
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

public class Sort {

	public static void main(String[] args) {
		if(args.length < 1) {
			usage();
		}
		
		char sorttype = 's';
		String filename = "";

		//determin sort type from user input
		if(args[0].equals("-m")) {  //-m for merge sort
			sorttype = 'm';
			if(args.length < 2) { usage(); }
			filename = args[1];
		} else if(args[0].equals("-s")) { //-s for selection sort
			sorttype = 's';
			if(args.length < 2) { usage(); }  
			filename = args[1];
		}
		else if(args[0].equals("-q")){
			sorttype = 'q';
			if(args.length < 2) { usage(); }  //-q for quck sort
			filename = args[1];
		}
		else if(args[0].equals("-r")){
			sorttype = 'r';
			if(args.length < 2) { usage(); }  //-r for radix sort
			filename = args[1];
		}
		else {
			System.err.println("No sort specified, using selection sort");
			filename = args[0];
		}

		String[] arr = null;  //initialize empty array to hold ints
		File tfile = new File(filename);  //create a file object for reading the data
		ArrayList<String> list = new ArrayList<String>(); //new list for holding the objects
		try { //fill up the array
			Scanner scan = new Scanner(tfile);  //scanner for reading the objects
			while(scan.hasNextLine()) {  //loop through the file
				list.add(scan.nextLine());  //enter that item to the list
			}
			arr = new String[list.size()];
			list.toArray(arr);  //set list to an array
		} catch(FileNotFoundException e) {
			System.err.println("Error, cannot find file: " + filename);
			System.exit(1);
		}

		switch (sorttype){
			case 'm': mergeSort(arr); break;
			case 's': selectionSort(arr); break;
			case 'q': quickSort(arr); break;
			case 'r': radixSort(arr); break;
		}

		for(String line : arr) {
			System.out.println(line);
		}
	}

	/************
	 * Prints out an error message explaining how to use this program
	 * Then quits
	 ****************/
	public static void usage() {
		System.err.println("Usage:\n\tjava Sort <sorttype> filename");
		System.err.println(" Where <sorttype> is:\n  -m Merge Sort\n -s Selection Sort \n -q Quick Sort \n -r Radix Sort");
		System.exit(1);
	}

	private static void quickSort(String[] arr){
		arr = rQuickSort(arr,0, arr.length-1); //call the recurvie quick sort
	}

	private static String[] rQuickSort(String[] arr, int lowerIndex, int higherIndex) {	 
		//copy the initial index limits
		int lBound = lowerIndex;
		int hBound = higherIndex;

		// Find point to compare all other items to
		String pivot = arr[lowerIndex+(higherIndex-lowerIndex)/2];
		
		while (lBound <= hBound) {
			while (arr[lBound].compareTo(pivot) < 0) { lBound++; } //move lower bound up until it finds an item after than the pivot value
			while (arr[hBound].compareTo(pivot) >0 ){ hBound--; } //move upper bound down until it finds and item before the pivot value
			if (lBound <= hBound) { // if these indicies are valid, switch them
				swap(arr, lBound, hBound);
				//let the bounds pass eachother
				lBound++; 
				hBound--;
			}
		}
		
		// call quickSort() method recursively
		if (lowerIndex < hBound){ arr = rQuickSort(arr, lowerIndex, hBound); } //sort the first half of the array
		if (lBound < higherIndex){ arr= rQuickSort(arr, lBound, higherIndex); } //sort the second half of the array
		return arr;
	}

	private static void swap(String[] arr, int i, int j) {
		String temp = arr[i]; //store the value in a temperary array
		arr[i] = arr[j]; 
		arr[j] = temp; //put it back in the other position
	}

	public static void radixSort(String[] arr){
		//turn every element in the array into an int
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i< arr.length; i++){
			list.add(Integer.valueOf(arr[i]));
		}
		ArrayList<Integer> foo = queify(list, 1);
		for (int i = 0; i< arr.length; i++){
			arr[i]=Integer.toString(foo.get(i));
		}
		//System.out.println(arr); //run the recursive radix Sort
	}

	private static ArrayList<Integer> queify(ArrayList<Integer> data, int ind){
		//initialize queues
		Queue<Integer> q0 = new LinkedList<Integer>();
		Queue<Integer> q1 = new LinkedList<Integer>();
		Queue<Integer> q2 = new LinkedList<Integer>();
		Queue<Integer> q3 = new LinkedList<Integer>();
		Queue<Integer> q4 = new LinkedList<Integer>();
		Queue<Integer> q5 = new LinkedList<Integer>();
		Queue<Integer> q6 = new LinkedList<Integer>();
		Queue<Integer> q7 = new LinkedList<Integer>();
		Queue<Integer> q8 = new LinkedList<Integer>();
		Queue<Integer> q9 = new LinkedList<Integer>();
		
		Queue[] slice = {q0,q1,q2,q3,q4,q5,q6,q7,q8,q9}; //hold the queues in an array


		System.out.println(ind);


		for(int i =0; i < data.size(); i++){	//itereate through the data array
			//System.out.print("Number :" );
			//System.out.print(data.get(i));
			//System.out.print(" sorted into :" );
			//System.out.println((int) ((data.get(i) / Math.pow(10, ind - 1)) % 10));
			int queInd = (int) ((data.get(i) / Math.pow(10, ind - 1)) % 10);	//find the index of the queue to add to 
			slice[queInd].add(data.get(i));	//add the data to the right queue
		}

		ArrayList<Integer> holder = new ArrayList<Integer>();	//create list to hold the new output
		for (int i =0; i < 10; i++){ //iterate over the array of queues
			while (!(slice[i]).isEmpty()){ //repeat until the list is empty
				holder.add((Integer) slice[i].poll()); //dequeue the element into the holder list
			}
		}

		//if the index is larger than the number of digits of the longest number in the list, stop the recursion
		if (ind > Math.floor(Math.log10(Collections.max(data)))){ 
			int[] foo = new int[holder.size()]; //create an array to return
			System.out.println(holder);
			//return holder;
			return holder;	//return the array
		}
		else{
			return queify(holder, ind+1); //recurse
		}
	}
	/*****
	 * Merge sort helper method
	 * @param arr The array to sort
	 ******/
	public static void mergeSort(String[] arr) {
		mergeSortRec(arr, 0, arr.length - 1);
	}

	/*********
	 * Merge sort recursive method
	 * @param arr The array to sort
	 * @param beg The beginning index to sort
	 * @param end The end index to sort
	 *********/
	private static void mergeSortRec(String[] arr, int beginIndex, int endIndex) {
		if(endIndex > beginIndex) {
			int a1length = (endIndex-beginIndex+1)/2;
			int a1end = beginIndex+a1length - 1;
			mergeSortRec(arr, beginIndex, beginIndex + (endIndex-beginIndex+1)/2 - 1);
			mergeSortRec(arr, a1end+1, endIndex);
			merge(arr,beginIndex,a1end,a1end+1,endIndex);
		} else {
			return;
		}
	}

	/****************
	 * Merge step of merge sort algorithm
	 * @param arr The array to sort
	 * @param a1start Where the first sub-array begins
	 * @param a1end Where the first sub-array ends
	 * @param a2start Where the second sub-array begins
	 * @param a2end Where the second sub-array ends
	 **************/
	private static void merge(String[] arr, int a1start, int a1end, int a2start, int a2end) {
		String[] tmp = new String[a2end - a1start + 1]; 
		int tmpindex = 0;
		int start = a1start;
		while(a1start <= a1end && a2start <= a2end) {
			if(arr[a1start].compareTo(arr[a2start]) < 0) {
				tmp[tmpindex] = arr[a1start];
				a1start++;
			} else {
				tmp[tmpindex] = arr[a2start];
				a2start++;
			}
			tmpindex++;
		}
		// copy remaining items in array 1
		while(a1start <= a1end) {
			tmp[tmpindex] = arr[a1start];
			a1start++;
			tmpindex++;
		}
		// copy remaining items in array 2
		while(a2start <= a2end) {
			tmp[tmpindex] = arr[a2start];
			a2start++;
			tmpindex++;
		}
		// copy from temp array back into arr
		for(int i = start; i <= a2end; i++) {
			arr[i] = tmp[i - start];
		}
	}

	/*********
	 * Selection sort method
	 * @param arr The array to sort
	 ******/
	public static void selectionSort(String[] arr) {
		for(int i = 0; i < arr.length; i++) {
			// find next smallest
			int smallestIndex = findNextSmallest(arr, i);
			
			// swap
			String tmp = arr[i];
			arr[i] = arr[smallestIndex];
			arr[smallestIndex] = tmp;
		}
	}

	/*************
	 * Finds the index of the smallest item in arr, from n to arr.length-1
	 * @param arr The array to search
	 * @param n The index to start looking
	 * @return The index of the smallest item
	 **********/
	private static int findNextSmallest(String[] arr, int n) {
		int minindex = n;
		for(int i = n+1; i < arr.length; i++) {
			if(arr[i].compareTo(arr[minindex]) < 0) {
				minindex = i;
			}
		}
		return minindex;
	}

}
