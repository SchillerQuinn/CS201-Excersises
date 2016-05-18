class BSTSet<T extends Comparable<T>> implements BSTInterface{
	private BiNode head = new BiNode();
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
	public boolean remove(T item){
		return head.remove(item);
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
	public ListADT<T> inOrderTraversal(){
		ArrayList<T> traverse = new ArrayList<T>();
		return this.listBuilder(head, traverse);
	}

	private ListADT<T> listBuilder(BiNode root, ListADT list){
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