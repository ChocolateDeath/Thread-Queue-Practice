/**
* Course: Csc130<br>
* Lab: 5<br>
* <b>Description: QueueADT Class</b><br>
*
*@author Kevin Perez, Chris
*/

public interface QueueADT<T> {

	public void enqueue(T d) throws QueueFullException;
	public T dequeue() throws QueueEmptyException;
	public T front() throws QueueEmptyException;
	public boolean isEmpty();
	public boolean isFull();
}
