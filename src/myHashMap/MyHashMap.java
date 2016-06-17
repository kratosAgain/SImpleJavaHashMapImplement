package myHashMap;

/* Author masterChiefBITS */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

//this is a genereic class
public class MyHashMap<K,V> {
    //this array of Linkedlist will store the nodes (nodes is nothing but key value pair in form of object)
	private LinkedList<Node>[] table  = null;  
	//store number of keys
	private int no_of_keys  = 0;
	//size of current hashtable
	private int size = 0;
	
	private boolean contracting = false;
	//constructor
	public MyHashMap(){
		//initializing the initial size to 16 (java HashMap also initialize it to 16)
		this.table = new LinkedList[16];
		this.size = 16;
		//initialize the table
		init(this.table); 
	}
	
	//function to initialize the linkedLists
	public void init(LinkedList<Node>[] table){
		for(int i =0 ;i<table.length;i++)
			table[i] = new LinkedList();
	}
	
	//function which return the array index for a hashCode "I just took the mod with size" you could introduce a more efficient hash Function
	public int index(int hashCode){
		return hashCode%(this.size);
	}
	
	//get a value from hashMap
	public V get(K key) {
		int hashCode = Math.abs(key.hashCode());
		int index = this.index(hashCode);
		for(Node<K,V> node:this.table[index]){
			if(node.hashValue == hashCode){
		    	return  node.value;
		    }
		}
		System.out.println(key+" is not found");
		return null;
	}
	
	//check whether key is present or not
	public boolean containsKey(K key){
		int hashCode = Math.abs(key.hashCode());
		int index = this.index(hashCode);
		for(Node<K,V> node:this.table[index]){
			if(node.hashValue == hashCode){
		    	return  true;
		    }
		}
		return false;
	}
	
	
	
	//putting values in hashmap
	public void put(K key, V val){
		Node<K,V> toPut = null;
		//first checking whether key is present or not
		if(this.containsKey(key)){
			//if present just replace the values
			this.putInAlreadyKey(key,val);
			return ;
		}
		
		int hashCode = Math.abs(key.hashCode());
		toPut = new Node(key,val,hashCode);	
		int index = this.index(hashCode);
		this.table[index].add(toPut);
		this.no_of_keys++;
		//if number of items is greater than equal to array size-- double the size-- standard practice
		if(this.no_of_keys >= this.size && !this.contracting){
			expand();
		}
	}
	
	//if key is already present, just replace the values
	public void putInAlreadyKey(K key,V val){
			if(!this.containsKey(key)){
				return ;
			}
			int hashCode = Math.abs(key.hashCode());
			int index = this.index(hashCode);
			for(Node<K,V> node:this.table[index]){
				if(node.key.equals(key)){
					node.value = val;
				}
			}
			//System.out.println("Values replaced");
			
		}
	
    //function to double the size of the hashMap
	public void expand(){
		LinkedList<Node>[] newTable = Arrays.copyOf(this.table, this.table.length);
		this.table = new LinkedList[this.size*2];
		this.size = this.size*2;
		this.init(this.table);
		for(LinkedList<Node> ll:newTable){
			for(Node<K,V> node:ll){
				this.put(node.key, node.value);
				
			}
		}
		System.out.println("size increased to "+this.size+"   "+this.table.length );
		newTable = null;
		//System.gc();		
	}
	
	
	//function to remove a key-value pair from map
	public void removeKey(K key){
		int hashCode = Math.abs(key.hashCode());
		int index = this.index(hashCode);
		for(Node<K,V> node:this.table[index]){
			if(node.hashValue == hashCode){
		    	this.table[index].remove(node);
		    	break;
		    }
		}
		//if number of items is smaller than equal to one fourth of array size--half the size-- standard practice
		if(this.no_of_keys <= 0.25*this.size){
			contract();
		}
	}
	
	//function to contract the size of table by half
	public void contract(){
		this.contracting = true;
		LinkedList<Node>[] newTable = Arrays.copyOf(this.table, this.table.length);
		this.table = new LinkedList[this.size/2];
		//System.out.println(this.size);
		this.size = this.size/2;
		this.init(this.table);
		for(LinkedList<Node> ll:newTable){
			for(Node<K,V> node:ll){
				this.put(node.key, node.value);
			}
		}
		System.out.println("size decreased to "+this.size+"   "+this.table.length );
		newTable = null;
		this.contracting = false;
		System.gc();		
	}
	
	
	//function to return all keys in the Hashmap
	public ArrayList<K> getKeys(){
		ArrayList<K> keys = new ArrayList();
		for(LinkedList<Node> ll:this.table){
			for(Node<K,V> node:ll){
				keys.add(node.key);
			}
		}
		return keys;
	}
	
	//print the map in pairs
	public String toString(){
		String s = "";
		for(LinkedList<Node> ll:this.table){
			for(Node<K,V> node:ll){
				s = s+"["+node.key+":"+node.value+"]\n";
			}
		}
		return s;
	}
}
