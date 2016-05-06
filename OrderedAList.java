import java.util.EmptyStackException;
import java.util.*;
import java.lang.IndexOutOfBoundsException;

/*
*TODO
*Make main file for testing
*String comparator is giving problems still
*/


public class OrderedAList<T> implements OrderedListADT<T>{
	
	private T[] data = (T[]) new Object[1];
	private int length = 0;
	private Comparator<T> check; //create an instance of the comparator

	public OrderedAList(Comparator<T> input){
		check = input;
	}

	public void add(T item){
		
		int lo = 0;
		int hi = this.length - 1; //correct for off by one error
		boolean found = false;
		int mid = (hi + lo) / 2;
		while (lo <= hi && !found) { //find the place to insert the new item
			mid = (hi + lo) / 2; // update midpint
			if (check.compare(item, data[mid])<0) {
				hi = mid - 1; //if it was too high, update the hi point of the search
			}
			else if (check.compare(item, data[mid])>0) {
				lo = mid + 1; //if it was too low, update the lowpoint of the search
			}
			else {
				found = true; //exit the loop
			}
		}
		if (length > 0 && check.compare(data[mid],item)>0){ //first checks to see if there are data in the array, then if this midpoint is larger than the item 
			mid --;
		}
		//start filling holder array
		T[] foo = (T[]) new Object[this.length+1];
		for (int i = 0; i < mid; i++){ //fill the first half before the insterted item
			foo[i] = this.data[i];
		}
		if(mid<0){
			mid=0;
		}
		foo[mid] = item; //insert the new item into array  ********
		for (int i = mid; i < this.length; i++){ //copy the second half of the list
			foo[i+1] = this.data[i];
		}
		this.length ++;	//update size of list
		this.data = foo;	//copy the holder onto the data
	}


	/*******************
	* Get the item at the given index, starting at 0
	* @return the item at that index
	*******************/
	public T get(int index){
		if (this.length >= index){ //only return valid items
			return this.data[index];
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}

	/***************
	* Test whether the list contains the given item
	* @return true if the item is in the list
	**********/
	public boolean contains(T item){
		if (this.length == 0){ //you can't search an empty list
			throw new EmptyStackException();
		}
		int lo = 0;
		int hi = this.length - 1; //correct for off by one error
		while (lo <= hi) { //loop until the whole array has been searched
			int mid = (hi + lo) / 2; // update midpint
			if (check.compare(item, data[mid])<0) {
				hi = mid - 1; //if it was too high, update the hi point of the search
			}
			else if (check.compare(item, data[mid])>0) {
				lo = mid + 1; //if it was too low, update the lowpoint of the search
			}
			else {
				return true; //exit the loop
			}
		}
		return false;

	}

	/******************
	* Test whether the list is empty
	* @return Whether the list is empty
	********************/
	public boolean isEmpty(){
		if (this.length == 0){ //if the size is 0 it is empty
			return true;
		}
		else {
			return false;
		}
	}

	/*********************
	* Get the number of items in the list
	* @return The number of items
	*********************/
	public int size(){
		return this.length;
	}

	/***********************
	* Remove the given item from the list
	* @return The item that was removed
	*************************/
	public T remove(T item){
		if (this.length == 0){ //you can't search an empty list
			throw new EmptyStackException();
		}
		if (!this.contains(item)){
			return null; //you can't remove an item that doens't exist
		}
		int lo = 0;
		int hi = this.length - 1; //correct for off by one error
		boolean found = false;
		int mid = (hi + lo) / 2;
		T holder = null;
		while (!found) { //search until it finds the item
			mid = (hi + lo) / 2; // update midpint
			if (check.compare(item, data[mid])<0) {
				hi = mid - 1; //if it was too high, update the hi point of the search
			}
			else if (check.compare(item, data[mid])>0) {
				lo = mid + 1; //if it was too low, update the lowpoint of the search
			}
			else {
				holder = this.data[mid]; //hold the item we will return
				found = true; //exit the loop
			}
		}
		//create holder array
		T[] foo = (T[]) new Object[this.length-1];
		for(int i = 0; i < mid; i++){//copy the first half
			foo[i] = this.data[i];
		}
		for (int i = mid+1; i < this.length; i++){ //copy the second half of the list skipping the index of the removed item
			foo[i-1] = this.data[i];
		}
		this.data = foo;//copy the holder array to the data array
		this.length --;	//update length of list
		//return the removed value
		return holder;
	}

	/**********************
	* Clear the list
	******************/
	public void clear(){
		this.length = 0;
	}

}
