/**
 * @author sunceli
 * @email sunceli@brandeis.edu

 * @brief Implementation of Data structure Queue
 * @param generic <T>
 */
public class Queue<T> {
	private T[] queue;
	private int front;
	private int rear;
	private int len;
	
	/**
	 * Constructor 
	 * @param size
	 * @RunningTime: constant c
	 */
	public Queue(int size){
		assert size > 0 : size;			// have to be positive size 
		this.queue = (T[])new Object[size];
		this.front = 0;
		this.rear = 0;
		this.len = size;
		
	}
	
	
	/**
	 * Check if the queue is full
	 * @return boolean
	 * @RunningTime: O(1)
	 */
	public boolean isFull() {
		return (front%this.len == rear%this.len) 
				&& this.queue[this.rear] != null;
	}
	
	
	/**
	 * Check if the queue is empty
	 * @return true if empty, otherwise false
	 * @RunningTime O(1)
	 */
	public boolean isEmpty() {
		return (front%this.len == rear%this.len) && (!isFull());
	}
	
	
	/**
	 * Get size of the queue
	 * @return size of the queue
	 * @RunningTime O(1)
	 */
	public int getSize() {
		if (isFull()) {
			return this.len;
		}else{
			return (this.len-this.front+this.rear)%this.len;
		}
	}
	
	
	/**
	 * Enqueue data at rear of the queue
	 * @param data
	 *  @Runningtime O(1)
	 */
	public void enqueue (T data){
		if ( getSize() == this.len) {			// check if full queue 
			throw new Error("failed: queue full, unable to enqueue");
		}else{
			this.queue[this.rear] = data;
			this.rear = (this.rear+1) % this.len;
		}
	}
	
	
	/**
	 * dequeue first item in queue and return it
	 * @return the item at front of queue that has been removed 
	 * @Runningtime O(1)
	 */
	public T dequeue (){
		if (isEmpty()){							// check if empty queue 
			throw new Error("empty queue");
		}else{
			T x = this.queue[this.front];
			this.front = this.front%this.len + 1; 
			return x;
		}
	}
	
	public String toString(){
		return this.queue.toString();
	}
	
	
}
