import java.util.*;

class SortedSet<T extends Comparable<T>> implements SortedSetADT<T>{
	
	public BiNode head = new BiNode(null);

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
		ListADT<T> otherItems = new LinkedUList<T>();
		items = this.toList(); //fill the list full of items in this binary tree
		otherItems = otherSet.toList(); //fill the second list full of items in the other binary tree
		SortedSet foo = new SortedSet(); //create a sortedSet to fll the union of
		
		while (items.isEmpty()!=true){ //add all of the items in the other binary tree; 
			foo.add((T)items.get(0)); 
			items.remove(items.get(0));
		}
		while (otherItems.isEmpty()!=true){
			foo.add((T)otherItems.get(0));
			otherItems.remove(otherItems.get(0));
		}
		return foo; //return the union set
	}

	/************
		* Creates a new SortedSet and returns an intersections of this set and otherSet
		* @param otherSet A SortedSet to combine with this set
		* @return The intersection of the two sets
		*/
	public SortedSetADT<T> intersect(SortedSetADT<T> otherSet){
		ListADT<T> ourItems = new LinkedUList<T>();
		ListADT<T> otherItems = new LinkedUList<T>();
		ourItems = this.toList(); //fill the list full of items in this binary tree
		otherItems = otherSet.toList(); //add all of the items in the other binary tree;

		SortedSet foo = new SortedSet(); //create a sortedSet to fll the union of
		while (ourItems.isEmpty()!=true){
			T holder = ourItems.get(0);
			if(otherItems.contains(holder)){
				foo.add(holder);
			}
			ourItems.remove(holder);
		}
		return foo; //return the intersection set	
	}

	/************
		* Returns a List of the items in this set, in order.
		* @return A list of the items in this set.
		*/
	public ListADT<T> toList(){
		ListADT<T> traverse = new LinkedUList<T>();
		return this.head.inOrderTraversal();
	}
}