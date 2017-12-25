/**
 * @author sunceli
 * @email sunceli@brandeis.edu
 * 
 * @brief Implementation of object node used, building block of singly linked list.
 * @param generic <T>
 */
public class SinglyLinkedNode<T> {
	private T data;
	private SinglyLinkedNode<T> next;
	
	/**
	 * Constructor
	 * new node initialized with data and null "next"
	 * @param data
	 * @Runningtime 0(1)
	 */
	public SinglyLinkedNode(T data){
		this.data = data;
		this.next = null;
	}
	
	/**
	 * the data in node 
	 * @return data   
	 * @Runningtime 0(1)
	 */
	public T getData(){
		return this.data;
	}
	
	/**
	 * set the node as the next node in the list, returned by getNext() 
	 * @param nextNode
	 * @Runningtime 0(1)
	 */
	public void setNext(SinglyLinkedNode<T> nextNode){
		this.next = nextNode;
		
	}
	
	
	/**
	 * return the next node in the list
	 * @return next
	 * @Runningtime 0(1)
	 */
	public SinglyLinkedNode<T> getNext() {
		return this.next;
	}
	
	/**
	 * Get data in the node as string.
	 * @return data  
	 * @Runningtime 0(1)
	 */
	public String toString() {
		return this.data.toString();
	}
	
	
	
}
