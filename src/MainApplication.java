import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;





public class MainApplication extends JFrame {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainApplication myWindow = new MainApplication();
		myWindow.setSize(900,600);
		myWindow.setTitle("My Image Gallery");
		myWindow.setVisible(true);
		myWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

	}
	
	public MainApplication()
	{
		// creating the container to the window and adding the master layout 
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		
		/*LEFT PANEL*/
		JPanel leftArea = new JPanel();
		container.add(leftArea,BorderLayout.WEST);
		leftArea.setBorder(new EmptyBorder(10,10,10,10));
		
		
		// adding list
		leftArea.setLayout(new BoxLayout(leftArea,BoxLayout.PAGE_AXIS));
	    JLabel l1 = new JLabel("List of Image");	// adding title
	    //l1.setBorder(new EmptyBorder(10,10,10,10));
	    l1.setAlignmentX((float) 0.5); 
		leftArea.add(l1);
		// 
		String[] selections = {"Selection 1", "Selection 2", "Selection 3"}; 
		//
		leftArea.add(Box.createRigidArea(new Dimension(0,20)));
		JComboBox myBox =new JComboBox(selections);
		leftArea.add(myBox);
		 
		 
		/*LEFT PANEL ENDS*/
		
		/*RIGHT PANEL*/
		JPanel rightArea = new JPanel();
		container.add(rightArea,BorderLayout.EAST);
		rightArea.setBorder(new EmptyBorder(10,10,10,10));
		rightArea.setLayout(new BorderLayout());
		//setting title
		String url = "left-icon.png"; 	
		JLabel header = new JLabel();
		
		
		Font f1 = new Font("Arial",Font.BOLD, 20 );
		rightArea.add(header,BorderLayout.NORTH);
		header.setAlignmentX((float) 0.5);
		header.setFont(f1);
		
		//adding image label
		JLabel myLabel = new JLabel(); // THIS IS FOR IMAGE DISPLAY
		//Image resizedImg = image.getScaledInstance(1080, 720, 0);
		ImageIcon icon = new ImageIcon ("funny.jpg"); 
		myLabel.setIcon(icon); 
		//myLabel.setIcon(new ImageIcon(url));
		rightArea.add(myLabel);
		
		// creating a Panel for the Icon
		JPanel bottom = new JPanel();
		JButton b1 = new JButton ("Previous");
		JButton b2 = new JButton ("Next");
		bottom.add(b1);
		bottom.add(b2);
		
		rightArea.add(bottom,BorderLayout.SOUTH);
		
		/*RIGHT PANEL ENDS*/
		
		this.getContentPane().add(container);
	}
}
