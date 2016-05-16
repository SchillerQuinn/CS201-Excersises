class SortedSet<T extends Comparable<T>> /*implements SortedSetADT*/{
	
	private BiNode head;
	private int size = 0;
	/*******
		* Adds the given item to the set, ignoring duplicates
		* @param item The item to add
		*/
	public void add(T item){
		if (size == 0){ //if it is an empty tree, set the head
			head = new BiNode(item, null, null);
			size++;	//increase the size
		}
		else{
			//do crazy shit
		}

	}

	 /*********
		* Removes the given item
		* @param item The item to remove
		* @return true if that item was removed, false otherwise
		*/
	public boolean remove(T item){
		if (!this.contains(item)){
			return false;
		}
		else{
			//do weird recursive shit
		}
	}

	private boolean recursiveRemove(T item, BiNode root){
		if
	}

	private void shiftTree{
		//fix trees8888
	}
	 /*********
		* Checks whether the given item is in the set
		* @param item The item to look for
		* @return true if that item is in the set, false otherwise
		*/
	public boolean contains(T item){
		if (head.getData().compareTo(item) == 0){
			return true;
		}
		return containsHelper(item, head); //recursivly look for item
	}

	private boolean containsHelper(T item, BiNode root){
		if(root.getData().compareTo(item)==0){
			return true;
		}
		boolean inLeft = false;
		boolean inRight = false;
		if(root.getLeft()!= null){
			inLeft = (containsHelper(item, root.getLeft()));
		}
		if(root.getRight()!= null){
			inRight = containsHelper(item, root.getRight());
		}
		if (inLeft||inRight){
			return true;
		} else{
			return false;
		}
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
		return this.size;
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
	//public ListADT<T> toList(){}
}