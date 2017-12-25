/**
 * @author sunceli
 * @email sunceli@brandeis.edu
 * 
 * @brief Implementation of data structure singly linked list. List has two pointers head and tail.
 * @param generic <T>
 */

import java.util.Arrays;
import java.util.Random;

public class SinglyLinkedList<T> {
	private int size;   //size of list
	private SinglyLinkedNode<T> head;    //head pointer 
	private SinglyLinkedNode<T> tail;    //tail pointer
	
	/**
	 * Constructor
	 * @Runningtime O(1)
	 */
	public SinglyLinkedList(){
		this.head = null;		// head is nil for empty list
		this.tail = null;
		this.size = 0;
	}
	
	
	/**
	 * get the first node in the list
	 * @return head
	 * @Runningtime O(1)
	 */
	public SinglyLinkedNode<T> getHead(){
		return this.head;
	}
	
	public void setHead(SinglyLinkedNode<T> next){
		this.head = next;
	}
	
	/**
	 * Insert data at the end 
	 * @param data
	 * @Runningtime O(1)
	 */
	public void regularInsert(T data){
		SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(data);	//construct node from data to insert 
		
		if (this.size==0){		// if this is the fist element to add, set to head 
			this.head= newNode; 
			this.tail = newNode;
		}else{
			this.tail.setNext(newNode);		//old tail points to new node 
			this.tail = newNode; 			// reset tail pointer 
		}
		this.size ++;
		//System.out.print("[[" +this.tail.toString()+ " ("+this.size+ ") ]]");
	}
	
	
	/**
	 * Insert a node at a random location in the list, including before head and after tail.
	 * @param data
	 * @Runningtime O(n)
	 */
	public void randomInsert(T data){
		SinglyLinkedNode<T> newNode = new SinglyLinkedNode<T>(data);
		Random rand = new Random();
		int loc = rand.nextInt(this.size+1);    // generate random location to insert. For a list of size n, there are n+1 places to insert.
		
		if (loc == 0){		//insert at head 
			newNode.setNext(this.head);
			this.head = newNode;
		}else if (loc == this.size+1){		//insert at end
			this.tail.setNext(newNode);
			this.tail = newNode;
		}else{								// insert in the middle
			SinglyLinkedNode<T> curr = this.head;
			for (int i=0; i < loc-1; i++){
				curr = curr.getNext();
			}
			newNode.setNext(curr.getNext());
			curr.setNext(newNode);
		}
		this.size++;		
	}
	
	
	/**
	 * Delete the data node from the list
	 * @param data
	 * @Runningtime O(n)
	 */
	public void remove(T data){
		SinglyLinkedNode<T> curr = this.head;
		SinglyLinkedNode<T> prev = null;
		int pos = 0;
		while(curr != null){				// iterate through entire list to search for target node
			if (curr.getData() == data){
				if (pos==0){ 	//delete first
					this.head = curr.getNext();
					this.size --;
					break;
				}else if (pos == this.size-1){		//delete last 
					prev.setNext(null);
					this.tail = prev;
					this.size --;
					break;
				}else{			//delete middle node 
					prev.setNext(curr.getNext());
					this.size --;
					break;
				}
			}
			prev = curr;
			curr = curr.getNext();
			pos++;
		}
		if (pos >= this.size){		// not found, throw error. deletion failed
			throw new Error("failed: data not found or empty list.");
		}
	}
	
	
	/**
	 * Delete the item by index in list and return it
	 * @param i
	 * @return node
	 * @Runningtime O(n)
	 */
	public SinglyLinkedNode<T> remove(int i){
		assert i < size : i;     // index to delete must be in range [0,size)
		assert i >= 0: i;
		
		if (this.size == 0){     // error if empty list
			throw new Error ("failed: empty list");
		}
		
		int index = i;
		SinglyLinkedNode<T> curr = this.head;
		SinglyLinkedNode<T> prev = null;
		while (curr != null && index != 0){ 	
			prev = curr;
			curr = curr.getNext();
			index --;
		}
		//System.out.println(curr.toString());
		//System.out.println(i + "/" + this.size);
		if (i == 0){
			this.head = this.head.getNext();
			this.size --;
		}else if (i == size-1){
			prev.setNext(null);
			this.tail = prev;
			this.size --;
		}else{
			prev.setNext(curr.getNext());
			this.size --;
		}
		
		return curr;
	}
	
	/**
	 * Get size of the list
	 * @return eize
	 * @Runningtime O(1)
	 */
	public int size() {
		return this.size;
	}
	
	
	/**
	 * Get all data of node in list as string
	 * @return data
	 * @Runningtime O(n)
	 */
	public String toString() {
		String[] data = new String[this.size];
		SinglyLinkedNode<T> curr = this.head;
		int i =0;
		while (curr != null){
			data[i] = curr.toString();
			curr = curr.getNext();
			i++;
		}
		return Arrays.toString(data);
	}
	
	
	/**
	 * Find the index of data in the list
	 * @param <String> data
	 * @return index
	 * @runningtime O(n)
	 */
	public int indexOf(String data){
		
		int i = 0;
		SinglyLinkedNode<T> curr = this.head;
		while (curr != null && !curr.getData().toString().equals(data)){ 	
			curr = curr.getNext();
			i ++;
		}
		if (i >= this.size){   		// not found in list
			throw new Error ("failed: data not found.");
		}
		
		return i;
	}

}
