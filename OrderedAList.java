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
	private Comparator check; //create an instance of the comparator

	public OrderedAList(Comparator<T> input){
		check = input;
	}

	public void add(T item){
		System.out.println(item);
		System.out.print(" ");
		if (this.length ==0){
			this.data[0]=item;
		}
		else{
			T[] foo = (T[]) new Object[this.length+1]; //create holder array
			boolean placed = false;
			for (int i = 0; i<this.length; i++){ //fill the first half before the insterted item
				if (!placed){
					if (i <= this.length || check.compare(item, data[i])<0 ){ //before the index where the item is added, just put it in where it should go
						System.out.print(this.data[i]);
						foo[i] = this.data[i];
					}
					else{ //the first time it reaches something bigger than it, put the item in before the larger item
						foo[i] = item;
						placed = true;
					}
				}
				else{ //then copy the rest of the data
					foo[i] = this.data[i-1];
				}
			}
			this.data = foo;	//copy the holder onto the data
		}
		this.length++;
	}


	/*******************
	* Get the item at the given index, starting at 0
	* @return the item at that index
	*******************/
	public T get(int index){
		if (this.length >= index){	//only return valid items
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
			throw new IndexOutOfBoundsException();
		}
		int lo = 0;
		int hi = this.length - 1; //correct for off by one error
		while (lo <= hi) { //loop until the whole array has been searched
			int mid = (hi + lo) / 2; // update midpint
			if (check.compare(item, data[mid])>0) {
				hi = mid - 1; //if it was too high, update the hi point of the search
			}
			else if (check.compare(item, data[mid])<0) {
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
			throw new IndexOutOfBoundsException();
		}
		if (!this.contains(item)){
			throw new NoSuchElementException(); //you can't remove an item that doens't exist
		}
		T holder = null;	//initialize holder in this scope
		boolean placed = false;
		T[] foo = (T[]) new Object[this.length-1];
		for (int i = 0; i<this.length; i++){ //fill the first half before the insterted item
			if (check.compare(item, data[i])<0){ //before the index where the item is added, just put it in where it should go
				foo[i] = this.data[i];
			}
			else if(!placed){ //the first time it reaches something bigger than it, don't copy the item
				placed = true;
				holder = this.data[i]; //store the value to return
			}
			else{ //then copy the rest of the data
				foo[i-1] = this.data[i];
			}
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
