import java.util.*;

class SortedSet<T extends Comparable<T>> implements SortedSetADT<T>{
	
	private BiNode head = new BiNode(null);

	/*******
		* Adds the given item to the set, ignoring duplicates
		* @param item The item to add
	*/

	public void add(T item){
		head.add(item); //use Binode's add method
	}

	 /*********
		* Removes the given item
		* @param item The item to remove
		* @return true if that item was removed, false otherwise
		*/
	public boolean remove(T item){
		BiNode<T> fakeHead = new BiNode<T>((T)(head.getData()),head,null); //make a fake new head so the real head of tree can have a parent;
		return head.remove(item, fakeHead);	//use built-in binode remove method
	}

	 /*********
		* Checks whether the given item is in the set
		* @param item The item to look for
		* @return true if that item is in the set, false otherwise
		*/
	public boolean contains(T item){
		return head.contains(item);	//use BiNode contains method
	}
	
	/********
	* Removes all items from the set
	*/
	public void clear(){
		this.head = null;
	}

	/*********
		* Gets the number of items in the set
		* @return the number of items in the set
		*/
	public int size(){
		return head.size(head);
	}

	/************
		* Creates a new SortedSet and returns a union of this set and otherSet
		* @param otherSet A SortedSet to combine with this set
		* @return The union of the two sets
		*/
	public SortedSetADT<T> union(SortedSetADT<T> otherSet){
		ListADT<T> items = new LinkedUList<T>();
		items = this.toList(); //fill the list full of items in this binary tree
		items.addAll(otherSet.toList()); //add all of the items in the other binary tree; 
		/* TODO
		WRITE ADD ALL METHOD
		*/
		SortedSet foo = new SortedSet(); //create a sortedSet to fll the union of
		while (items.isEmpty()!=true){
			foo.add(items.remove(items.size()-1)); //remove the last item in the list (so you don't have to shift down the entire array every time);
			/* TODO
			WRITE ADD TO INDEX METHOD
			*/
		}
		return foo; //return the union set
	}

	/************
		* Creates a new SortedSet and returns an intersections of this set and otherSet
		* @param otherSet A SortedSet to combine with this set
		* @return The intersection of the two sets
		*/
	public SortedSetADT<T> intersect(SortedSetADT<T> otherSet){
		LinkedUList<T> ourItems = new LinkedUList<T>();
		LinkedUList<T> otherItems = new LinkedUList<T>();
		ourItems = this.toList(); //fill the list full of items in this binary tree
		otherItems = otherSet.toList(); //add all of the items in the other binary tree;

		SortedSet foo = new SortedSet(); //create a sortedSet to fll the union of
		while (items.isEmpty()!=true){
			T holder = ourItems.remove(ourItems.size()-1);
			if(otherItems.contains(holder)){
				foo.add(holder);
			}
		}
		return foo; //return the intersection set	
	}

	/************
		* Returns a List of the items in this set, in order.
		* @return A list of the items in this set.
		*/
	public ListADT<T> toList(){
		ListADT<T> traverse = new LinkedUList<T>();
		return listBuilder(head, transverse);
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
}