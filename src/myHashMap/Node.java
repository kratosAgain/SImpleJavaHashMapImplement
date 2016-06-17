package myHashMap;
/* Author masterChiefBITS */

//generic node class
public class Node<K,V> {
	K key = null;
	V value = null;
	int hashValue;
	public Node(K key,V value,int hashValue){
		
		this.key = key;
		this.value = value;
		this.hashValue = hashValue;
	}
	
}
