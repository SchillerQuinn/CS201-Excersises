public class TableEntry<K, V> {
	private final K key;  //final because we will never change the key
	private V value;  //value the entry holds

	public TableEntry(K key, V value) {  //initialize the entry 
		this.key = key;
		this.value = value;
	}

	public K getKey() { //return the key
		return key;
	}

	public V getValue() { //return the data held in this entry
		return value;
	}

	public void setValue(V value) { //set the value of this entry
		this.value = value;
	}
} 
