import java.util.ArrayList //use built-in list implimentation

class SortedSet<T extends Comparable<T>> /*implements SortedSetADT*/{
	
	private BiNode head;
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
		return head.remove(item); //use BiNode remove method
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
		size = 0;
	}

	/*********
		* Gets the number of items in the set
		* @return the number of items in the set
		*/
	public int size(){
		return head.size();	//call BiNode size method
	}

	/************
		* Creates a new SortedSet and returns a union of this set and otherSet
		* @param otherSet A SortedSet to combine with this set
		* @return The union of the two sets
		*/
	//public SortedSetADT<T> union(SortedSetADT<T> otherSet);

	/************
		* Creates a new SortedSet and returns an intersections of this set and otherSet
		* @param otherSet A SortedSet to combine with this set
		* @return The intersection of the two sets
		*/
	//public SortedSetADT<T> intersect(SortedSetADT<T> otherSet);

	/************
		* Returns a List of the items in this set, in order.
		* @return A list of the items in this set.
		*/
	public ListADT<T> toList(){
		ArrayList<T> tranverse = new ArrayList<T>();
		return listBuilder(head)
	}
}