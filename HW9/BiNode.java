class BiNode<T extends Comparable<T>>{
	private T data;
	private BiNode<T> left;
	private BiNode<T> right;


	public BiNode(T d){ //fast insert method for making a new node without setting roots
		this( d , null, null ); //call full binary Node method
	}

	public BiNode(T d, BiNode<T> l, BiNode<T> r) {
		data = d;
		left = l;
		right = r;
	}

	public T getData() { return data; }

	public BiNode<T> getLeft() { return left; }
	public BiNode<T> getRight() { return right; }

	public void setData(T d) { data = d; }
	public void setLeft(BiNode<T> l) { left = l; }
	public void setRight(BiNode<T> r) { right = r; }



	public int size( BiNode t ){
		if( t == null ){
			return 0;
		}
		else{
			return 1 + size(t.left) + size( t.right );
		}
	}

	public void add(T item){
		if (this.data == null){
			this.setData(item); //if there is no data at this node, set the data equil to this 
		}
		else{
			if (this.getData().compareTo(item)>0){ //if the item is greater than the(this, recurse down the right side
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
		if(this.getData().compareTo(item)==0){
			return true;
		}
		boolean inLeft = false;
		boolean inRight = false;
		if(this.left!= null){
			inLeft = this.left.contains(item);
		}
		if(this.right!= null){
			inRight = this.right.contains(item);
		}
		if (inLeft||inRight){
			return true;
		} else{
			return false;
		}
	}

	public boolean remove(T item){

	}
}