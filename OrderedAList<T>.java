public class OrderedAList<T> implements OrderedListADT<T>{
	
	public void add(T item){
		StringComparator check = new Comparator<String>(); 

		if(count==0){
			throw new EmptyStackException();
		}

		int low = 0;
		int high = count;
		int mid;
		boolean placed = false;
		T midItem;
		while(!placed){
			mid = (high+low)/ 2;
			midItem = get(mid);
			if(check.compare(midItem,item)){ //if first parameter comes after the second, you willget positive
				add(item,mid);
				placed = true;
			}
			else if(check.compare(midItem,item) < 0){ // get(mid) comes first
				low = mid + 1;
			}
			else{
				high = mid -1;
			}

			if(high == low){
				add(item,mid);
				placed = true;
			} 

		}
	}

}
