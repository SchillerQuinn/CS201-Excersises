/****************
 * ListADT defines the interface to a general list collection
 *******/
public interface ListADT<T> {

  /*******************
   * Get the item at the given index, starting at 0
   * @return the item at that index
   *******************/
  public T get(int index);

  /***************
   * Test whether the list contains the given item
   * @return true if the item is in the list
   **********/
  public boolean contains(T item);

  /******************
   * Test whether the list is empty
   * @return Whether the list is empty
   ********************/
  public boolean isEmpty();

  /*********************
   * Get the number of items in the list
   * @return The number of items
   *********************/
  public int size();

  /***********************
   * Remove the given item from the list
   * @return The item that was removed
   *************************/
  public T remove(T item);

  /**********************
   * Clear the list
   ******************/
  public void clear();

}
