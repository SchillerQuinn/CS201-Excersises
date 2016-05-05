import java.util.EmptyStackException;

/*
*TODO
*Make main file for testing
*String comparator is giving problems still
*/


public class OrderedAList<T> implements OrderedListADT<T>{
	
	private T[] data = (T[]) new Object[1];
	private int length;

	public void add(T item){
		if (this.length == 0){ //you can't search an empty list
			throw new EmptyStackException();
		}
		StringComparator check = new Comparator<String>(); //create an instance of the comparator
		int lo = 0;
		int hi = this.length - 1; //correct for off by one error
		boolean found = false;
		int mid;
		while (lo <= hi && !found) { //find the place to insert the new item
			mid = (hi + lo) / 2; // update midpint
			if (check.compareTo(item, data[mid])<0) {
				hi = mid - 1; //if it was too high, update the hi point of the search
			}
			else if (check.compareTo(item, data[mid])>0) {
				lo = mid + 1; //if it was too low, update the lowpoint of the search
			}
			else {
				found = true; //exit the loop
			}
		}
		if (check.compareTo(data[mid],item)>0){ //if this midpoint is larger than the item 
			mid --;
		}
		//start filling holder array
		T[] foo = (T[]) new Object[this.length+1];
		for (int i = 0; i < mid; i++){ //fill the first half before the insterted item
			foo[i] = this.data[i];
		}
		foo[mid] = item; //insert the new item into aray
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
			return null;
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
		StringComparator check = new Comparator<String>(); //create an instance of the comparator
		int lo = 0;
		int hi = this.length - 1; //correct for off by one error
		while (lo <= hi) { //loop until the whole array has been searched
			int mid = (hi + lo) / 2; // update midpint
			if (check.compareTo(item, data[mid])<0) {
				hi = mid - 1; //if it was too high, update the hi point of the search
			}
			else if (check.compareTo(item, data[mid])>0) {
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
		StringComparator check = new Comparator<String>(); //create an instance of the comparator
		int lo = 0;
		int hi = this.length - 1; //correct for off by one error
		boolean found = false;
		int mid;
		T holder;
		while (!found) { //search until it finds the item
			mid = (hi + lo) / 2; // update midpint
			if (check.compareTo(item, data[mid])<0) {
				hi = mid - 1; //if it was too high, update the hi point of the search
			}
			else if (check.compareTo(item, data[mid])>0) {
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
