//Quinn Schiller and Andrew Maris

class SortedSet<T extends Comparable<T>> implements SortedSetADT<T>{
	
	public BiNode head = new BiNode(null);
	public int count = 0;

	/*******
		* Adds the given item to the set, ignoring duplicates
		* @param item The item to add
	*/

	public void add(T item){
		head.add(item); //use Binode's add method
		count++;
	}

	 /*********
		* Removes the given item
		* @param item The item to remove
		* @return true if that item was removed, false otherwise
		*/
	public boolean remove(T item){
		
		//BiNode fakeHead = new BiNode(head.getData(),head,null); //make a fake new head so the real head of tree can have a parent;
		boolean retVal = head.remove(item, null);
		if(retVal){
			count--;
		}
		return retVal;//use built-in binode remove method
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
		this.head = new BiNode(null);
		this.count = 0;
	}

	/*********
		* Gets the number of items in the set
		* @return the number of items in the set
		*/
	public int size(){
		return count;
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

	public static void main (String[] args){
		SortedSet<String> test1 = new SortedSet<String>();
		
		/*******************************************************************
		* Testing SortedSet
		* Should output:
		*
		* First we will test to see if it works as a BST
		* 5
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
		* true
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
		* e
		* x
		* y
		* ------
		* 0
		* 
		* Now we will test it's SortedSet functionality
		* b
		* b
		* c
		* m
		* n
		* o
		* x
		* y
		* z
		* ------
		* b
		* b
		* c
		* m
		* n
		* o
		* x
		* y
		* z
		* ------
		* ------
		* b
		* x
		* ------
		* b
		* x
		********************************************************************/

		System.out.println("First we will test to see if it works as a BST");
		//Testing add method
		test1.add("z");
		test1.add("b");
		test1.add("y");
		test1.add("x");
		test1.add("c");
		
		System.out.println(test1.size()); // prints 5
		System.out.println("------");

		//Testing contain methos
		System.out.println(test1.contains("a")); // prints false
		System.out.println(test1.contains("b")); // prints true
		System.out.println(test1.contains("c")); // prints true
		System.out.println(test1.contains("d")); // prints false
		System.out.println(test1.contains("x")); // prints true
		System.out.println(test1.contains("x")); // prints true
		System.out.println(test1.contains("q")); // prints false
		System.out.println(test1.contains("y")); // prints true
		System.out.println(test1.contains("z")); // prints true
		System.out.println("------");

		//Testing remove method
		System.out.println(test1.remove("a")); // prints false
		System.out.println(test1.remove("b")); // prints true
		System.out.println(test1.remove("b")); // prints false
		System.out.println(test1.remove("z")); // prints true
		System.out.println(test1.remove("d")); // prints false
		System.out.println("---");
		System.out.println(test1.size());  // prints 3
		System.out.println("---");
		System.out.println(test1.contains("a")); // prints false
		System.out.println(test1.contains("b")); // prints false
		System.out.println(test1.contains("c")); // prints true
		System.out.println(test1.contains("d")); // prints false
		System.out.println(test1.contains("x")); // prints true
		System.out.println(test1.contains("x")); // prints true
		System.out.println(test1.contains("q")); // prints false
		System.out.println(test1.contains("y")); // prints true
		System.out.println(test1.contains("z")); // prints false
		System.out.println("------");
		
		//Testing traversal
		test1.add("b");
		test1.add("m");
		test1.add("e");
		LinkedUList<String> travResult = ((LinkedUList<String>)(test1.toList())); // created a linked list to store the result of the traversal
		int num = travResult.size(); //num represents the size of the traverse
		for(int i = 0; i<num; i++){
			System.out.println( travResult.get(i)); //prints b c m e x y on different lines
		}
		System.out.println("------");

		//Testing clear
		test1.clear();
		System.out.println(test1.size()); //prints 0



		SortedSet<String> test2 = new SortedSet<String>();
		System.out.println("\nNow we will test its SortedSet functionality");

		//Creating two test lists to union and intersect
		test1.add("z");
		test1.add("b");
		test1.add("y");
		test1.add("x");
		test1.add("c");

		test2.add("m");
		test2.add("b");
		test2.add("o");
		test2.add("x");
		test2.add("n");

		//Test union
		//We run this twice to make sure there is no difference between test1.union(test2) and test2.union(test1)
		SortedSetADT<String> testUnion1 = test1.union(test2); //Creates the union set
		LinkedUList<String> travResult1 = ((LinkedUList<String>)(testUnion1.toList())); //puts the union set into a linked list
		int tU1size = travResult1.size(); //saves number of elements in list for the loop
		for(int i = 0; i<tU1size; i++){ //prints out the elements in the list, which represent elements in the union
			System.out.println(travResult1.get(i));
		}
		
		System.out.println("------");

		SortedSetADT<String> testUnion2 = test2.union(test1);//Creates the union set
		LinkedUList<String> travResult2 = ((LinkedUList<String>)(testUnion2.toList())); //puts the union set into a linked list
		int tU2size = travResult2.size(); //saves number of elements in list for the loop
		for(int i = 0; i<tU2size; i++){ //prints out the elements in the list, which represent elements in the union
			System.out.println(travResult2.get(i));
		}
		System.out.println("------");
		System.out.println("------");

		//Test intersect
		//We run this twice to make sure there is no difference between test1.intersect(test1) and test1.intersect(test2)
		SortedSetADT<String> testInt1 = test1.intersect(test2); //Creates the intersection
		LinkedUList<String> travResult21 = ((LinkedUList<String>)(testInt1.toList())); //puts the intersection into a linked list
		tU1size = travResult21.size(); //saves the number of elements in the list for the loop
		for(int i = 0; i<tU1size; i++){ //prints out the elements in the list, which represent elements in the intersection
			System.out.println(travResult21.get(i));
		}
		System.out.println("------");

		SortedSetADT<String> testInt2 = test2.intersect(test1);//Creates the intersection
		LinkedUList<String> travResult22 = ((LinkedUList<String>)(testInt2.toList())); //puts the intersection into a linked list
		tU2size = travResult22.size(); //saves the number of elements in the list for the loop
		for(int i = 0; i<tU2size; i++){ //prints out the elements in the list, which represent elements in the intersection
			System.out.println(travResult22.get(i));
		}
	}
}