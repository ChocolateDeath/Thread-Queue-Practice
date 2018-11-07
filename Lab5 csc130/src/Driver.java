import java.awt.*; 
import java.awt.event.*;

import javax.lang.model.util.Elements;
import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.parser.Element;

import org.jsoup.Jsoup;

import java.net.*; 
import java.applet.*; 
import java.io.*;

/**
* Course: csc130<br>
* Lab: 5<br>
* <b>Description: Driver Class</b><br>
* </p>
*
*@author Kevin Perez, Chris
*/

public class Driver {
//  define an integer to store the number of consumer threads
   // variable name - numThreads
	private int numThreads;
	
//  define a reference to a Queue<String> 
	// variable name - queue
	private Queue<String> queue = new Queue<String>();
   
	// define a reference to a ThreadGroup
	private ThreadGroup tg = new ThreadGroup("consumers");

//  define a reference to an array of Threads(data type isThread)
// variable name - consumer
	private Thread[] consumer;

// TODO: define a reference to a Producer Thread
// variable name - producer
	private ProducerThread producer;

// TODO: define a reference to an array of PictPanel objects (to display each image)
// variable name - panel 
	private PictPanel[] panel;

// TODO: define a reference to an array of JFrame objects (to place each Panel)
// variable name - pictureFrame 
	private JFrame[] pictureFrame;

// TODO: define a reference to an array of Strings (to store the names of the images) 
// variable name - picture
	private String[] picture;

public static void main(String[] args) {
// TODO: create an instance of this class
	Driver driver = new Driver();
//and call its constructor
	
	
// exit the program
System.exit(0); 
}

// constructor for the class
public Driver() {
// permit the user to input the number of consumer threads (3 - 5)
// Use JOptionPane.showInputDialog
	String numThreadsString;
	numThreadsString = JOptionPane.showInputDialog("Input the number of consumer threads (3 - 5)");
	int numThreads = Integer.parseInt(numThreadsString);

	//  initialize all arrays to the size of the number of consumer threads
	consumer = new Thread[numThreads];
	panel = new PictPanel[numThreads];
	pictureFrame = new JFrame[numThreads];
	picture = new String[numThreads];

	//  call the appropriate method to load the pictures
	loadImages();

	//  create a loop based on the number of consumer threads
	//		for each JFrame and PictPanel object
	for(int i = 0; i < numThreads; i++){
	
//  create an instance of JFrame with the title
// 	Thread and the number of the thread example: (Thread 1)
// and store it in the array
		pictureFrame[i] = new JFrame("Thread " + (i + 1));
		
	// use the setSize method to set the JFrameÕs size to 200 by 200
		pictureFrame[i].setSize(200,200);

// Set X,Y coordinate of upper left corner of the frame
pictureFrame[i].setLocation(80 * i, 80 * i);

// create an instance of PictPanel and store it in the panel
// array
panel[i] = new PictPanel(pictureFrame[i]);

// add the panel to the frame
pictureFrame[i].getContentPane().add(panel[i]);

// Use the setVisible method to show the frame
pictureFrame[i].setVisible(true); 
}

// create an instance of ProducerThread 
// store it in the variable created
// the ProducerThread constructor needs a Queue and an array of Strings
	producer = new ProducerThread(queue, picture);

// call the start method of the producer thread
	producer.start();

// producer.setPriority(Thread.MAX_PRIORITY); ?

// create the consumer threads and add them to the ThreadGroup
for (int i = 0; i < numThreads; i++) {
	consumer[i] = new Thread(tg, new ConsumerThread(queue, producer,
         panel[i]), "thread " + i);
   consumer[i].start();
}


//  loop until the producer thread is no longer alive
// use the isAlive() method of the producer thread
// the loop pauses the program until the producer thread ends
while(producer.isAlive()){
}
//  after the loop ends, display a message using ShowMessageDialog 
// indicating the producer thread has finished
JOptionPane.showMessageDialog(null, "Producer thread has finished running.");

//  pause the program until the consumer threads end 
// Loop while ThreadGroup's activeCount > 0
while(tg.activeCount() > 0){
	
}

//  after the loop ends, display a message using ShowMessageDialog 
// indicating that the consumer threads have finished
JOptionPane.showMessageDialog(null, "Consumer thread has finished running.");

// TODO: display an end of program message using ShowMessageDialog
JOptionPane.showMessageDialog(null, "End of Program");
}
/**
 *  Method loads the pictures from URL
 */
public void loadImages() {
org.jsoup.nodes.Document doc;
String url = "http://matcmp.ncc.edu/grahamf/csc130/images/"; 
int j=0;
	try {
		System.out.println("Reading URL directory"); 
		doc = Jsoup.connect(url).get();
		org.jsoup.select.Elements files = doc.getElementsByTag("a");

     // initialize the array size for the file names
	picture = new String[files.size()];
	
for (org.jsoup.nodes.Element file : files){
	String filename = file.attr("href");
	if(filename.indexOf(".jpg") >= 0 || filename.indexOf(".gif") >= 0){
		System.out.println(filename);
           picture[j++] = url + filename;
        }
}
} catch (IOException ex) {
     System.out.println("IOException " + ex);   }
}

/**
    *  Method loads the pictures from a local folder
    */
public void loadPictures() {
	// create a reference to an array of Files (type is File) 
	File[] pictures;
	
     // create a reference String for the image directory
     String directory = "images/";
     
     // create a reference to the directory
     File imgDirectory = new File(directory);
     
     // store the directory listing in the File array
     pictures = imgDirectory.listFiles();
     
     // initialize the array size for the file names
     picture = new String[pictures.length];
     
     // add each image file to the image file array
     for (int i = 0, j = 0; i < pictures.length; i++) { 
    	 File file = pictures[i];
    	 
    	 // add .jpg and .gif file names to the String array 
    	 // you can add other image types
     	if (file.isFile()
              && (file.getName().toLowerCase().endsWith(".jpg") ||
                file.getName().toLowerCase().endsWith(".gif"))) {
           // display the name of the image file
     		System.out.println((j + 1) + ": " + pictures[i].getName()); 
     		
     		// add the name to the array
           picture[j] = pictures[i].getName();
           
           // increment the number of images
           j++; 
           }
     	}
    }
}