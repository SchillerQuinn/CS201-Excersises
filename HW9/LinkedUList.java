//My Partner is Andrew Maris

public class LinkedUList<T extends Comparable<T>> implements UnorderedListADT<T>{

	private int count;
	private Node<T> head;
	private Node<T> back; //pointer to last node in list

	/*******************
	 * Get the data at the given index, starting at 0
	 * @return the data at that index
	 *******************/
	
	LinkedUList(){
		this.count = 0;
		this.head = new Node<T>(null, null);
		this.back = head; //pointer to last node in list
	}

	public T get(int index){
		Node<T> check = head.getNext(); //initilize a node object to loop through the list
		if (index <= this.count){	//only allow the user to get valid items
			for (int i = 0; i < index; i ++){
				check=check.getNext(); //loop to the index'th item of the linked list
			}
			return check.getData(); //return the data at that node
		}
		else { //if it is out of range
			return null;
		}
	}
	/***************
	 * Test whether the list contains the given item
	 * @return true if the item is in the list
	 **********/
	public boolean contains(T item){
		for (Node<T> check = this.head.getNext(); check!=null; check = check.getNext()){ //loop through all of the array
			if (check.getData().equals(item)){
				return true; //if the checked index holds the item, return true.
			}
		}
		return false; //if none of the items match with it, the list does not contain the item.
	}
	/******************
	 * Test whether the list is empty
	 * @return Whether the list is empty
	 ********************/
	public boolean isEmpty(){
		if (this.count ==0){ //if there are not nodes in the list
			return true; //then it is empty
		}
		else{
			return false; //else it is not empty
		}
	}

	/*********************
	 * Get the number of items in the list
	 * @return The number of items
	 *********************/
	public int size(){
		return this.count; //return the number of items in the list
	}

	/***********************
	 * Remove the given item from the list
	 * @return The item that was removed
	 *************************/
	public T remove(T item){
		Node<T> prev = this.head; //initilize a node to walk through the list
		T foo = null; //holder for return value
		int indexAt = 0; // tells us which index we are at
		for (Node<T> check = this.head.getNext(); check!=null; check =check.getNext()){ //loop through all of the array
			foo = check.getData(); //hold the value of the node
			if (foo.equals(item)){ //if there is a match
				if(indexAt == count){ // if you are at the back, it sets the previous node as the back node
					this.back = prev; 
				}
				prev.setNext(check.getNext()); //remove the item from the linked list
				this.count--; //make the size smaller
				return foo; //if the check index holds the item, return true.
			}
			prev = check;
			indexAt++;
		}
		return null; //if it doesn't find the item return null
	}

	/**********************
	 * Clear the list
	 ******************/
	public void clear(){
		this.count = 0; //update the size
		this.head.setNext(null); //cut the rest of the linked list off, clearing it
		this.back = head;
	}

	public void add(T item, int index){
		Node<T> check = this.head;
		if (this.count <= index){ //make sure the index is legal
			for (int i = 0; i < index; i ++){ //set check to the right index in the 
				check=check.getNext(); //set check to one higher
			}
			check.setNext( new Node<T>(item, check.getNext()) ); //insert the item into the list
			this.count++; //update the size
		}
		
	}

	public void addToEnd(T item){ //using a pointer to back node to make O(1) opperation
		Node<T> foo = new Node<T>(item, null);
		this.back.setNext(foo); //insert the item into the list
		this.back=foo;
		this.count++; //update the size
		
	}


}