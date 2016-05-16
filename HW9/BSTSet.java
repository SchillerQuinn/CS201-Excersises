class BSTSet<T extends Comparable<T>> implements BSTInterface{

	/*********
	 * Checks whether the given item is in the BST
	 * @param item The item to look for
	 * @return true if that item is in the BST, false otherwise
	 */
	public boolean contains(T item);

	/*******
	 * Adds the given item to the BST
	 * @param item The item to add
	 */
	public void add(T newitem);

	/*********
	 * Removes the given item
	 * @param item The item to remove
	 * @return true if that item was removed, false otherwise
	 */
	public boolean remove(T item);

	/*********
	 * Gets the number of items in the BST
	 * @return the number of items in the BST
	 */
	public int getNumNodes();

	/********
	 * Removes all items from the BST
	 */
	public void clear();

	/*********
	 * Checks whether the BST is empty
	 * @return true if the BST is empty
	 */
	public boolean isEmpty();

	/**********
	 * Return an array that contains all the items in this BST in an in-order traversal
	 * @return the array
	 */
	public ListADT<T> inOrderTraversal();

}