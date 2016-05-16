class BiNode<T extends Comparable<T>> {
	private T data;
	private BiNode<T> left;
	private BiNode<T> right;

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
}	