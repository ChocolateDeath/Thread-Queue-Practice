/**
* Course: csc130<br>
* Lab: 5<br>
* <b>Description: Queue Class</b><br>
*
*@author Kevin Perez, Chris
*/

public class Queue<T> implements QueueADT<T> {
	/**
	 * data - a reference to a generic array; the array contains the Queue items
	 */
	private T[] data;
	private int size;
	/**
	 * front - index in the array where an item will be removed from the Queue
	 * rear - an index in the array where an item will be added to the Queue
	 */
	private int front, rear;
	/**
	 * MAX_SIZE - a constant used to define the default capacity of the Queue
	 */
	public static final int MAX_SIZE = 100;

	/**
	 * default constructor - creates an empty Queue capable of storing 100 items
	 */
	public Queue() {
		this(MAX_SIZE);
	}

	/**
	 * parameterized constructor - creates an empty Queue capable of storing
	 * 'size' items. If the size is negative or 0 a Queue is created that can
	 * store 100 items. * @param size - the maximum size of the Queue as
	 * specified by the user
	 */
	public Queue(int size) {
		if (size <= 0)
			size = MAX_SIZE;
		data = (T[]) new Object[size];
		front = rear = 0;
	}

	/**
	 * enqueue method - adds a new item at the rear of the Queue * @param x a
	 * reference to the item to be added at the rear of the Queue
	 */
	public synchronized void enqueue(T d) throws QueueFullException {
		if (isFull())
			throw new QueueFullException("Queue is full");
		rear = (rear + 1) % data.length;
		data[rear] = d;
	}

	/**
	 * dequeue method -- removes and returns the item at the front of the Queue 
	 * @return the item at the front of the Queue * @throws
	 * StackEmptyException if the Queue is empty
	 */
	public synchronized T dequeue() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Queue is empty");
		front = (front + 1) % data.length;
		return data[front];
	}

	/**
	 * * front method -- returns the item at the front of the Queue
	 * 
	 * @return the item at the front of the Queue *
	 * @throws StackEmptyException if the Queue is empty
	 */
	public synchronized T front() throws QueueEmptyException {
		if (isEmpty())
			throw new QueueEmptyException("Queue is empty");
		return data[(front + 1) % data.length];
	}

	/**
	 * isEmpty method -- this method indicates whether the Queue is empty or not 
	 * @return true if the Queue is empty; false otherwise.
	 */
	public synchronized boolean isEmpty() {
		return (rear == front);
	}

	/**
	 * * isFull method -- this method indicates whether the Queue is full or not
	 * *
	 * 
	 * @return true if the Queue is full; false otherwise.
	 */
	public synchronized boolean isFull() {
		return ((rear + 1) % data.length) == front;
	}

	/**
	 * * toString method -- this method returns the state of the Queue *
	 * 
	 * @return a reference to a String object representing the Queue
	 */
	public synchronized String toString() {
		String str = "";
		int start = front + 1;
		while (start != (rear + 1) % data.length) {
			str = str + data[start] + " ";
			start = (start + 1) % data.length;
		}
		return str;
	}
	public synchronized int getSize(){
		return size;
	}
}