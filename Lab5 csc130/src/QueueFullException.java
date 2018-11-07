/**
* Course: csc130
* Lab: 5
* Description: QueueFullException
*
*@author Kevin Perez, Chris
*/

public class QueueFullException extends Exception {

	public QueueFullException(){
		super("Queue Full exception");
	}
	public QueueFullException(String msg){
		super(msg);
	}
}
