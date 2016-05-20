import java.util.*;
import java.util.ArrayList; //use built-in list implimentation

class BSTSet<T extends Comparable<T>> implements BSTInterface<T> {
	
	private BiNode head = new BiNode(null);
	private int count = 0;
	
	public BSTSet(){

	}

	/*********
	 * Checks whether the given item is in the BST
	 * @param item The item to look for
	 * @return true if that item is in the BST, false otherwise
	 */
	public boolean contains(T item){
		return head.contains(item);
	}

	/*******
	 * Adds the given item to the BST
	 * @param item The item to add
	 */
	public void add(T newitem){
		head.add(newitem);
		count++;
	}

	/*********
	 * Removes the given item
	 * @param item The item to remove
	 * @return true if that item was removed, false otherwise
	 */
	public boolean remove(T item){
		BiNode fakeHead = new BiNode(head.getData(),head,null); //make a fake new head so the real head of tree can have a parent;
		boolean retVal = head.remove(item, fakeHead);	//use built-in binode remove method
		if(retVal){
			count--; //decrement count if something was removed
		}
		return retVal;
	}

	/*********
	 * Gets the number of items in the BST
	 * @return the number of items in the BST
	 */
	public int getNumNodes(){
		return count;
	}

	/********
	 * Removes all items from the BST
	 */
	public void clear(){
		count = 0;
		head = null;
	}

	/*********
	 * Checks whether the BST is empty
	 * @return true if the BST is empty
	 */
	public boolean isEmpty(){
		if (count == 0){
			return true;
		}
		else{
			return false;
		}
	}

	/**********
	 * Return an array that contains all the items in this BST in an in-order traversal
	 * @return the array
	 */
	public ListADT<T> inOrderTraversal(){
		LinkedUList<T> traverse = new LinkedUList<T>();
		return this.listBuilder(head, traverse);
	}

	private LinkedUList<T> listBuilder(BiNode root, LinkedUList<T> list){
		if (root.getLeft()!= null){ //check the left side
			list = listBuilder(root.getLeft(), list); //add the left side of the tree to the list
		}
		list.addToEnd((T)(root.getData())); //after the left side has been added to the list, add the parent
		if(root.getRight()!=null){	//check the right side
			list = listBuilder(root.getRight(),list);	//add the right side of the tree to the list
		}
		return list; //return the list
	}

	public static void main (String[] args){
		BSTSet<String> test = new BSTSet<String>();
		
		/*
		* Testing BSTSet
		* Should output:
		*
		* 5
		* false
		* ------
		* false
		* true
		* true
		* false
		* true
		* true
		* false
		* true
		* true
		* ------
		* false
		* true
		* false
		* true
		* false
		* ---
		* 3
		* ---
		* false
		* false
		* false
		* false
		* true
		* true
		* false
		* true
		* true
		* ------
		* b
		* c
		* m
		* x
		* y
		* z
		* ------
		* 0
		* true
		*/


		//Testing add method
		test.add("z");
		test.add("b");
		test.add("y");
		test.add("x");
		test.add("c");
		System.out.println(test.getNumNodes()); // prints 5
		System.out.println(test.isEmpty()); //print false
		System.out.println("------");

		//Testing contain methos
		System.out.println(test.contains("a")); // prints false
		System.out.println(test.contains("b")); // prints true
		System.out.println(test.contains("c")); // prints true
		System.out.println(test.contains("d")); // prints false
		System.out.println(test.contains("x")); // prints true
		System.out.println(test.contains("x")); // prints true
		System.out.println(test.contains("q")); // prints false
		System.out.println(test.contains("y")); // prints true
		System.out.println(test.contains("z")); // prints true
		System.out.println("------");

		//Testing rmove method
		System.out.println(test.remove("a")); // prints false
		System.out.println(test.remove("b")); // prints true
		System.out.println(test.remove("b")); // prints false
		System.out.println(test.remove("c")); // prints true
		System.out.println(test.remove("d")); // prints false
		System.out.println("---");
		System.out.println(test.getNumNodes());  // prints 3
		System.out.println("---");
		System.out.println(test.contains("a")); // prints false
		System.out.println(test.contains("b")); // prints false
		System.out.println(test.contains("c")); // prints false
		System.out.println(test.contains("d")); // prints false
		System.out.println(test.contains("x")); // prints true
		System.out.println(test.contains("x")); // prints true
		System.out.println(test.contains("q")); // prints false
		System.out.println(test.contains("y")); // prints true
		System.out.println(test.contains("z")); // prints true
		System.out.println("------");

		//Testing traversal
		test.add("b");
		test.add("m");
		test.add("c");
		LinkedUList<String> travResult = ((LinkedUList<String>)(test.inOrderTraversal()));

		int num = travResult.size();
		for(int i = 0; i<num; i++){
			System.out.println( travResult.get(i)); //prints xyz
		}
		System.out.println("------");

		//Testing clear
		test.clear();
		System.out.println(test.getNumNodes()); //prints 0
		System.out.println(test.isEmpty()); //print true

	}


}