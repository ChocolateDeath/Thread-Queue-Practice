/**
* Course: csc130
* Lab: 5
* Description: QueueEmptyException
*
*@author Kevin Perez, Chris
*/

public class QueueEmptyException extends Exception {

	public QueueEmptyException(){
		super("Queue Empty exception");
	}
	public QueueEmptyException(String msg){
		super(msg);
	}
}
