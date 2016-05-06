import java.util.EmptyStackException;
import java.util.*;
import java.lang.IndexOutOfBoundsException;

/*
*TODO
*Make main file for testing
*String comparator is giving problems still
*/


public class OrderedAList<T> implements OrderedListADT<T>{
	
	private T[] data = (T[]) new Object[10];//creates and initial array of size 10
	private int length = 0; //initializes the number of units in the array to be 0
	private Comparator<T> check; //create an instance of the comparator

	public OrderedAList(Comparator<T> input){
		check = input; //takes a Comparator to use throughout the meme
	}

	public void add(T item){
		//when the array cannot take on another value, we double the size
		if(this.length == data.length){
            T[] foo = (T[]) new Object[2*data.length];
            for(int i = 0; i < this.length; i++){
                foo[i] = data[i];
            }
            data = foo;
        }

        int insertIndex = 0; 
        while(insertIndex < this.length && check.compare(item, data[insertIndex]) > 0){
            insertIndex++;
        }

        for(int i = this.length; i > insertIndex ; i--){ //slides all the entries down that are after insertIndex
        	data[i] = data[i-1];
        }

        data[insertIndex] = item; //after we opened up a slot at insertIndex by sliding everythin past it down, we now place item into that spot
        this.length++; //we increase the length by 1 to reflect the fact that we added a new item 
	}


	/*******************
	* Get the item at the given index, starting at 0
	* @return the item at that index
	*******************/
	public T get(int index){
		if (this.length > index && 0 <= index){	//only return valid items between length and 
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
		int mid = 0;
		while (lo <= hi) { //loop until the whole array has been searched
			mid = (hi + lo) / 2; // update midpint
			if (check.compare(item, data[mid])==0) {
				return true; //if it is right just exit the loop
			}
			else if (check.compare(item, data[mid])>0) {
				lo = mid +1; //if it was too low, update the lo point of the search
			}
			else if (check.compare(item, data[mid])<0) {
				hi = mid -1; //if it was too high, update the hi point of the search
			}
		}
		//System.out.println("yo " + data[mid]);
		//System.out.println(mid);
		//if(check.compare(item, data[lo]) == 0 || check.compare(item, data[hi]) == 0){// checks to see if the item is at the final hi or lo
		//	return true;
		//}

		return false; //if we couldn't find it, return false

	}

	/******************
	* Test whether the list is empty
	* @return Whether the list is empty
	********************/
	public boolean isEmpty(){
		if (this.length == 0){ //if the size is 0, it will return true
			return true;
		}
		else { //otherwise return false
			return false;
		}
	}

	/*********************
	* Get the number of items in the list
	* @return The number of items
	*********************/
	public int size(){
		return this.length; //returns number of items in the array
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
		T[] foo = (T[]) new Object[this.length];
		int fooInd = 0;
		for (int i = 0; i<this.length; i++){ //fill the first half before the insterted item
			if (check.compare(item, data[i])!=0){ //before the index where the item is added, just put it in where it should go
				foo[fooInd] = this.data[i];
				fooInd++;
			}
		}
		this.data = foo;//copy the holder array to the data array
		this.length--;	//update length of list
		
		//return the removed value
		return item;
	}

	/**********************
	* Clear the list
	******************/
	public void clear(){
		this.length = 0; //reduces count to zero
		this.data = (T[]) new Object[10]; //resets the array
	}

	public static void main(String[] args){
		StringComparator sComp = new StringComparator();
		OrderedAList<String> test = new OrderedAList<String>(sComp);
		/*******************************
		Testing the add, contains, get, remove method
		Should print unless commented out:
		
		//IndexOutOfBoundsException
		//IndexOutOfBoundsException
		//IndexOutOfBoundsException
		-1
		5
		Andrew
		Andy
		Quinn
		dog
		house
		Andrew
		Quinn
		house
		//IndexOutOfBoundsException
		//NoSuchElementException
		false
		false
		false
		false
		true
		true
		2
		Andy
		dog
		0
		true

		**********************************/
		

		//Testing properties of List when it has no elements
		//test.remove("meme");
		//test.contains("meme");
		//test.get(1);
		System.out.println(test.isEmpty());

		//Testing comparator
	    System.out.println(sComp.compare("a","b"));

	    //Testing add and size method
		test.add("Andy");
		test.add("dog");
		test.add("house");
		test.add("Quinn");
		test.add("Andrew");
		System.out.println(test.size());

		//Testing get methos
		System.out.println(test.get(0)); 
		System.out.println(test.get(1)); 
		System.out.println(test.get(2)); 
		System.out.println(test.get(3)); 
		System.out.println(test.get(4)); 
		//System.out.println(test.get(10)); 
		
		//Testing remove method
		System.out.println(test.remove("Andrew"));
		System.out.println(test.remove("Quinn"));
		System.out.println(test.remove("house"));
		//System.out.println(test.remove("foo"));

		//Testing contains method
		System.out.println(test.contains("foo"));
		System.out.println(test.contains("Andrew")); 
		System.out.println(test.contains("dat boi")); 
		System.out.println(test.contains("Quinn")); 
		System.out.println(test.contains("Andy")); 
		System.out.println(test.contains("dog")); 
		System.out.println(test.size());

		//Testing get method after removals
		System.out.println(test.get(0));
		System.out.println(test.get(1));

		//Testing clear and isEmpty methods
		test.clear();
		System.out.println(test.size());
		System.out.println(test.isEmpty());
	  }

}
