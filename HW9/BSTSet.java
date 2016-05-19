import java.util.*;
import java.util.ArrayList; //use built-in list implimentation

class BSTSet<T extends Comparable<T>> implements BSTInterface{
	private BiNode head = new BiNode(null);
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
	}

	/*********
	 * Removes the given item
	 * @param item The item to remove
	 * @return true if that item was removed, false otherwise
	 */
	private boolean remove( T item){
		BiNode fakeHead = new BiNode(head.getData(),head,null); //make a fake new head so the real head of tree can have a parent;
		return head.remove(item, fakeHead);	//use built-in binode remove method
	}

	/*********
	 * Gets the number of items in the BST
	 * @return the number of items in the BST
	 */
	public int getNumNodes(){
		return head.size();
	}

	/********
	 * Removes all items from the BST
	 */
	public void clear(){
		head = null;
	}

	/*********
	 * Checks whether the BST is empty
	 * @return true if the BST is empty
	 */
	public boolean isEmpty(){
		if (head.size() == 0){
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
	public ArrayList<T> inOrderTraversal(){
		ArrayList<T> traverse = new ArrayList<T>();
		return this.listBuilder(head, traverse);
	}

	private ArrayList<T> listBuilder(BiNode root, ArrayList<T> list){
		if (root.getLeft()!= null){ //check the left side
			list = listBuilder(root.getLeft(), list); //add the left side of the tree to the list
		}
		list.add(root.getData); //after the left side has been added to the list, add the parent
		if(root.getRight()!=null){	//check the right side
			list = listBuilder(root.getRight(),list);	//add the right side of the tree to the list
		}
		return list; //return the list
	}
}