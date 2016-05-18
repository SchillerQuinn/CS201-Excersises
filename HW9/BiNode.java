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
			return 1 + size(t.left) + size(t.right)  //if there is node here, it adds 1 and then checks size of left and then right subtree
		}
	}

	public void add(T item){ //adds an item to the BST in the right order
		if (this.data == null){ //if there is no data at this node, it sets the data equal to the item
			this.setData(item); 
		}
		else{
			if (this.getData().compareTo(item)>0){ //if the item is greater than then this, recurse down the right side
				if (this.getRight() == null){ //if there is no data on this side, put a new value there.
					this.right = new BiNode(item);
				}
				else{ //if there is data, recurse down that side 
					this.right.add(item);
				}
			}
			else{ //if the item is less than or equal to, check the left side
				if (this.getLeft() == null){ //if there is no data on this side, put a new value there.
				this.left = new BiNode(item);
				}
				else{ //if there is data, recurse down that side 
					this.left.add(item);
				}
			}
		}
	}

	public boolean contains(T item){
		if(this.getData().compareTo(item)==0){ //if we find what we are looking for, return true
			return true;
		}
		if (this.getData().compareTo(item)>0){ //if the item is greater than then this, recurse down the right side
			if (this.getRight() == null){ //if there is no data on this side, put a new value there.
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

	public boolean remove(T item){
		if(this.getData().compareTo(item)==0){
			this.getData() == null;
			return true;
		}
	}
}