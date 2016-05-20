class BiNode<T extends Comparable<T>>{
	private T data;
	private BiNode<T> left;
	private BiNode<T> right;

	public BiNode(T d){ //basic constructor for making a new node without setting roots
		this( d , null, null ); //call full binary Node method
	}

	public BiNode(T d, BiNode<T> l, BiNode<T> r) { //full constructor for making a new node with specified children
		data = d;
		left = l;
		right = r;
	}

	public T getData() { return data; } //returns data of the node
	public BiNode<T> getLeft() { return left; } //returns left child of node
	public BiNode<T> getRight() { return right; } //returns right child of node

	public void setData(T d) { data = d; } //sets data of the node
	public void setLeft(BiNode<T> l) { left = l; } //sets left child of node
	public void setRight(BiNode<T> r) { right = r; } //sets right child of node

	public int size( BiNode t ){ //returns number of nodes in tree
		if( t == null ){ //if there is not a node here, it returns 0
			return 0;
		}
		else{
			return 1 + size(t.left) + size(t.right);  //if there is node here, it adds 1 and then checks size of left and then right subtree
		}
	}

	public void add(T item){ //adds an item to the BST in the right order
		if (this.data == null){ //if there is no data at this node, it sets the data equal to the item
			this.setData(item); 
		}
		else{
			if (this.getData().compareTo(item)<0){ //if the item is greater than then this, recurse down the right side
				if (this.getRight() == null){ //if there is no data on this side, put a new data there.
					this.right = new BiNode(item);
				}
				else{ //if there is data, recurse down that side 
					this.right.add(item);
				}
			}
			else{ //if the item is less than or equal to, check the left side
				if (this.getLeft() == null){ //if there is no data on this side, put a new data there.
				this.left = new BiNode(item);
				}
				else{ //if there is data, recurse down that side 
					this.left.add(item);
				}
			}
		}
	}


	public boolean remove(T item, BiNode parent) {
		if (this.data.compareTo(item)>0) { //if item is less than  this value
			if (left != null){ //if there is another item less than this value it can check
				return left.remove(item, this); //recurse
			}
			else{ //the item is not in the tree
				return false;
			}
		} 
		else if (this.data.compareTo(item)<0) {//if item is more than this value
			if (right != null){ //if there is another item greater than this value it can check
				return right.remove(item, this); //recurse
			}
			else{//the item is not in the tree
				return false;
			}
		} 
		else {
			if(parent == null){
				
			}
			if (left != null && right != null) {	//if it has two children
				this.data = right.minValue();	//set it equal to the value of the smallest item on the right hand of the tree
				right.remove(this.data, this);	//remove that smallest item from the tree;
			} else if (parent.getLeft() == this) {	//if this is the left child of the parent and we only have one child
				if(left!= null){	//replace this node with the correct one of its children
					parent.setLeft(this.left); 
				}
				else{
					parent.setLeft(this.right);
				}
			} else if (parent.getRight() == this) {//if this is the right child of the parent and we only have one child
				if(left!= null){ //replace this node with the correct one of its children
					parent.setRight(this.left);
				}
				else{
					parent.setRight(this.right);
				}
			}
			return true; //return that the item was removed
		}
	}

	public T minValue() {
		if (left == null){ //if the node has no left children
			return this.data; //this is the lowest item
		}
		else{
			return left.minValue();
		}
	}
	
	public boolean contains(T item){
		if(this.getData().compareTo(item)==0){ //if we find what we are looking for, return true
			return true;
		}
		if (this.getData().compareTo(item)<0){ //if the item is greater than then this, recurse down the right side
			if (this.getRight() == null){ //if there is no value on this side, put a new value there.
				return false;
			}
			else{ //if there is data, recurse down that side 
				return this.right.contains(item);
			}
		}
		else{ //if the item is less than or equal to, check the left side
			if (this.getLeft() == null){ //if there is no data on this side, put a new value there.
				return false;
			}
			else{ //if there is data, recurse down that side 
				return this.left.contains(item);
			}
		}
	}

	public ListADT<T> inOrderTraversal(){
		LinkedUList<T> holder = new LinkedUList<T>();
		return traverseBuilder(holder);
	}

	public LinkedUList<T> traverseBuilder(LinkedUList<T> list){
		if (this.getLeft()!= null){ //check the left side
			list = this.left.traverseBuilder(list); //add the left side of the tree to the list
		}
		list.addToEnd((T)(this.getData())); //after the left side has been added to the list, add the parent
		if(this.getRight()!=null){	//check the right side
			list = this.right.traverseBuilder(list);	//add the right side of the tree to the list
		}
		return list; //return the list
	}
}