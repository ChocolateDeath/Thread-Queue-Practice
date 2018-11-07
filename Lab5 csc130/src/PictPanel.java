import javax.swing.*; 
import java.awt.*; 
import java.net.*;

/**
* Course: csc130<br>
* Lab: 5<br>
* <b>Description: PictPanel Class</b><br>
* </p>
*
*@author Kevin Perez, Chris
*/

public class PictPanel extends JPanel {
	
private static final long serialVersionUID = 1L; 
private Image pict;
private JFrame parent;

public PictPanel(JFrame frame) { 
	parent = frame;
	try {
		ImageIcon imgIcon = new ImageIcon(new URL("http://www.google.com/images/logo.gif")); 
		pict = imgIcon.getImage();
		parent.setVisible(false);
		this.setSize(pict.getWidth(this), pict.getHeight(this)); 
		parent.setSize(pict.getWidth(this), pict.getHeight(this));
		parent.setVisible(true);
		repaint(); 
	}
	catch(java.net.MalformedURLException e){} 
}

public void paintComponent(Graphics g) { 
	super.paintComponent(g);
	if(pict != null)
		g.drawImage(pict,0,0,getWidth(), getHeight(),this);
}

public synchronized void drawPict(String fileName) { 
	try{
		System.out.println(fileName); 
		// FileName is a local file
		//ImageIcon imgIcon = new ImageIcon(this.getClass().getResource(fileName));
		
		// FileName is an URL
		ImageIcon imgIcon = new ImageIcon(new URL(fileName)); 
		pict = imgIcon.getImage();
		if(pict != null) {
			MediaTracker imageTracker = new MediaTracker(this); 
			imageTracker.addImage(pict, 0);
			
			try {
				imageTracker.waitForAll();
			}
			catch(InterruptedException e){ }
			
			parent.setVisible(false); 
			this.setSize(pict.getWidth(this), pict.getHeight(this)); 
			parent.setSize(pict.getWidth(this), pict.getHeight(this));
			
			parent.setVisible(true);
			repaint(); 
		}
}catch(MalformedURLException mue){ 
	System.out.println("Oh oh, something bad happened");
}
}
}