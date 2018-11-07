/**
* Course: csc130<br>
* Lab: 5<br>
* <b>Description: ProducerThread Class</b><br>
* </p>
*
*@author Kevin Perez, Chris
*/

public class ProducerThread extends Thread { 
	
	private Queue<String> queue;
	private String[] picture;

	// TODO: constructor
	public ProducerThread(Queue<String> q, String[] p){
	// initialize the queue and the array
	// using the 2 parameters
		queue = q;
		picture = p;
	
	}
	
	public void run() {
		// loop for each picture and the picture is not null 
		for(int i = 0; i < picture.length && picture[i] != null; i++){
		// Note: possible exceptions that can occur are
		// QueueFullException and InterruptedException

	// loop while the queue is full
			while(queue.isFull()){
			}
			try {
	// TODO add an item from the array to the queue 
				
					queue.enqueue(picture[i]);
	// display the item added to the queue
				System.out.println(picture[i]);
	// sleep for 100 - 1000 milliseconds
				sleep(((long)Math.random() * (1000)) + 100);
			} catch (QueueFullException ex) {
				//  Auto-generated catch block
				System.out.println("QueueFullException " + ex);
			} catch (InterruptedException ex) {
				//  Auto-generated catch block
				System.out.println("QueueEmptyException " + ex);
			}

//  When the thread stop's running,
// display a message indicating the thread has stopped
			
		} 
		System.out.println("The Producer Thread has stopped running");
	}
}