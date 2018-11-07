/**
* Course: csc130<br>
* Lab: 5<br>
* <b>Description: ConsumerThread Class</b><br>
*
*@author Kevin Perez, Chris
*/

public class ConsumerThread extends Thread {
	private Queue<String> queue; 
	private ProducerThread producer; 
	private int count;
	private PictPanel panel;
	private boolean running;
	
	// 		constructor
	// 		initialize the queue, producer thread, and the panel
	// 		using the 3 parameters

	public ConsumerThread( Queue<String> q, ProducerThread p, PictPanel pa){
		queue = q;
		producer = p;
		panel = pa;
	}
	
	public void run() {
	String item = null;
	// loop while the producer thread isAlive or the queue is not empty
		while(producer.isAlive() || !queue.isEmpty()){
		
		
		// Note: possible exceptions that can occur are
		// 		QueueEmptyException and InterruptedException 
		
		
			//  loop while the queue is empty
			while(queue.isEmpty()){
			}
			try {
			//  if the queue is not empty 
			while(!queue.isEmpty()){
				// take an item from the queue
				item = queue.dequeue();
				// call the panel's drawPict method and pass the item
				panel.drawPict(item);
				// Use Thread.currentThread().getName() to output
				// the name of this thread and the item it processed
				System.out.print(Thread.currentThread().getName() + " " + item);
				// add 1 to the count
				count++;
				// sleep for 1000 to 5000 milliseconds (random)
				sleep(((long)Math.random() * (4000)) + 1000);
				}
		} catch (QueueEmptyException ex) {
			//  Auto-generated catch block
			System.out.println("QueueEmptyException" + ex);
		} catch (InterruptedException ex) {
			//  Auto-generated catch block
			System.out.println("QueueEmptyException" + ex);
		}
		
		}
		//  When the thread stop's running,
		// 		display its name and the number of items processed
		System.out.println("The Consumer Thread, with " + count + " items, has stopped running.");
		}	
	
	
	} 
	
