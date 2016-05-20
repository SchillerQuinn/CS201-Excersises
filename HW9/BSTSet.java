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
		count--;
		return head.remove(item, fakeHead);	//use built-in binode remove method
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
		test.add("z");
		test.add("b");
		test.add("y");
		test.add("x");
		test.add("c");

		System.out.println(test.getNumNodes());

		System.out.println(test.contains("a"));
		System.out.println(test.contains("b"));
		System.out.println(test.contains("c"));
		System.out.println(test.contains("d"));
		System.out.println(test.contains("x"));
		System.out.println(test.contains("x"));
		System.out.println(test.contains("q"));
		System.out.println(test.contains("y"));
		System.out.println(test.contains("z"));

		System.out.println(test.remove("a"));
		System.out.println(test.remove("b"));
		System.out.println(test.remove("c"));
		System.out.println(test.remove("d"));

		System.out.println(test.getNumNodes());
		
		System.out.println(test.contains("a"));
		System.out.println(test.contains("b"));
		System.out.println(test.contains("c"));
		System.out.println(test.contains("d"));
		System.out.println(test.contains("x"));
		System.out.println(test.contains("x"));
		System.out.println(test.contains("q"));
		System.out.println(test.contains("y"));
		System.out.println(test.contains("z"));

		ArrayList<String> travResult = new ArrayList<String>();
		for(String i : travResult){
			System.out.println(i);
		}

		test.clear();
		System.out.println(test.getNumNodes());
		System.out.println(test.isEmpty());

	}


}