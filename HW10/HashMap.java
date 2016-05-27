//Quinn Schiller and Andrew Maris

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;

public class HashMap<K, V> implements MapADT<K, V>{
	private int size;
	private int INITIAL_SIZE = 101;
	private LinkedList<TableEntry<K,V>>[] table;
	
	/*
	*
	*	Initialize the hashMap
	*
	*/
	public HashMap(){	//initialize the hashMap
		table = (LinkedList<TableEntry<K,V>>[]) new LinkedList[INITIAL_SIZE]; //creating the table as an array of linked lists of table entrues
		for (int i = 0; i < INITIAL_SIZE; i++) {	//initialize each linked list in the hash map
			table[i] = new LinkedList<TableEntry<K,V>>();
		}
	}

	/*
	*
	*	hash the key and then make sure it fits into size of the list.	
	*
	*/
	private int hash(K key, int constraint){
		return Math.abs(key.hashCode()%constraint); //use modulo to make sure it fits in the table
	}


	/*
	*
	*	adds the value to the hashMap, overrides existing key/value pair if it exists
	*
	*/
	public V put(K key, V value) {
		if (!this.containsKey(key)){ //if the table doesn't have this key yet
			table[hash(key, INITIAL_SIZE)].addFirst(new TableEntry(key, value));	//add to correct sublist
			this.size++;	//increase size
			reSize();	//check to see if we need to increase the size of the table
			return value; 
		}
		else{	//if there is an entry with this key in the table, overwrite the old values
			this.remove(key);	//remove the old entry
			table[hash(key, INITIAL_SIZE)].addFirst(new TableEntry(key, value));	//add to correct sublist
			this.size++;	//increase size
			reSize();	//check to see if we need to resize the table
			return value;
		}
	}

	private void copy(K key, V value, LinkedList<TableEntry<K,V>>[] newTable, int newSize) {
		if (!this.containsKey(key)){ //if the table doesn't have this key yet
			newTable[hash(key, newSize)].addFirst(new TableEntry(key, value));	//add to correct sublist
		}
		else{	//if there is an entry with this key in the table, overwrite the old values
			this.remove(key);	//remove the old entry
			newTable[hash(key,newSize)].addFirst(new TableEntry(key, value));	//add to correct sublist
		}
	}

	/*
	*
	*	returns the value of the item held at the key inputed 
	*
	*/
	public V get(K key) {	//return the value held at the key
		LinkedList<TableEntry<K,V>> hashList = table[hash(key, INITIAL_SIZE)];	//gets correct sublist
		for (int i = 0; i < hashList.size(); i++) {	//loop through the sublist
			if (hashList.get(i).getKey().equals(key)) {	//if the loop finds the entry with the right key
				return hashList.get(i).getValue();	//return the value
			}
		}
		return null;	//if it loops all the way through the hashList without finding the key, return null since we couldn't find it
	}

	/*
	*
	*	removes the entry at that key and returns its value
	*
	*/
	public V remove(K key) {	//removes the item in the hashMap with that key and returns its value
		LinkedList<TableEntry<K,V>> hashList = table[hash(key, INITIAL_SIZE)];	//find the subList that has the key in it
		for (int i = 0; i < hashList.size(); i++) {	//loop through each element of that sublist
			if (hashList.get(i).getKey().equals(key)) {	//if there is a matching key
				V value = hashList.get(i).getValue();	//store the value held at that entry
				hashList.remove(i);	//remove the entry
				size--;	//update size
				return value;	//return the value held at the old entry
			}
		}
		return null;	//if the key isn't in the hashMap, return null
	}

	/*
	*
	*	search the entire hashMap for the key and return true if it is found
	*
	*/
	public boolean containsKey(K key){
		LinkedList<TableEntry<K,V>> hashList = table[hash(key, INITIAL_SIZE)];	//get the correct sublist
		for (int i = 0; i < hashList.size(); i++) {	//loop through the whole sublist
			if (hashList.get(i).getKey().equals(key)) {	//if it finds the key return true
				return true;
			}
		}
		return false;	//if it does not find the key return false
	}

	/*
	*
	*	search the entire hashMap for the value and return true if it is found
	*
	*/
	public boolean containsValue(V value){
		for (int k = 0; k < INITIAL_SIZE; k++) { //you have to check every value because there is no key
			LinkedList<TableEntry<K,V>> hashList = table[k];	//look through each sublist
			for (int i = 0; i < hashList.size(); i++) {	//look through each entry at each sublist
				if (hashList.get(i).getValue().equals(value)) {	//if it finds the value, return true
					return true;
				}
			}
		}
		return false;	//if it makes it all the way to the end without finding the value, return false
	}

	//NOTE: ONLY WORKS FOR ODD NUMBERS. Only odd numbers are passed into it though. 
	private boolean isPrime(int n){
		for (int i=3; i*i<n; i+=2){	//test to see if n is divisible by all odd numbers less than the square root of it
			if(n%i == 0){
				return false;	//if it is divisible by a lower number, it isn't prime
			}
		}
		return true;	//if it pasts all tests, return true
	}

	private int nextPrime(){
		int newSize = (INITIAL_SIZE*2)+1;	//start with a number one higher than double Initial size 
		while (!isPrime(newSize)){	//until it finds a prime number, increase newSize
			newSize+=2;	//increments  by two to skip even numbers
		}
		return newSize;
	}

	private void reSize(){
		if (this.size >= INITIAL_SIZE){ //if the size parameter is greater than or equal to the constant INITIAL_SIZE, we need to create a new table
			int newSize = nextPrime(); //Find next prime number to find new table size
			LinkedList<TableEntry<K,V>>[] holderTable = (LinkedList<TableEntry<K,V>>[]) new LinkedList[newSize]; //create a temp holder table with the new size
			for (int i = 0; i < newSize; i++) {	//initialize each linked list in the hash map
				holderTable[i] = new LinkedList<TableEntry<K,V>>();
			}


			for (int i = 0; i < INITIAL_SIZE; i++){	//loop through each list
				LinkedList<TableEntry<K,V>> hashList = table[i];
				for (int k = 0; k < hashList.size(); k++) {	//loop through the whole sublist
					this.copy(hashList.get(k).getKey(),hashList.get(k).getValue(), holderTable, newSize);	//copy each item to the new hashMap	
				}
			}
			this.table = holderTable; //set this table to the resized table
			INITIAL_SIZE = newSize;	//resize the current size variable	
		}
	}

	/*
	*
	*	output each key/value pair
	*
	*/
	public void out() {
		for (int i = 0; i < INITIAL_SIZE; i++){	//loop through each list
			LinkedList<TableEntry<K,V>> hashList = table[i];
			for (int k = 0; k < hashList.size(); k++) {	//loop through the whole sublist
				System.out.print(hashList.get(k).getKey()); //write the key of the entry
				System.out.print(": ");
				System.out.println(hashList.get(k).getValue());	//write the value of the entry
			}
		}
	}
} 