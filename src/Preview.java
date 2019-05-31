
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*; 
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Preview extends JFrame implements ActionListener{

	JLabel labelImage; 
	JPanel buttonPanel; 
	JLabel imgDesc; 
	JButton previous; 
	JButton next; 
	JMenu menuFile; 
	Connection conn;	     
	Statement stat ;
	ArrayList<Integer> id; 
	String path= "";
	String detail=""; 
	int index=0; 
	public static void main(String []args)
	{
		Preview preview= new Preview(); 
		preview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preview.setTitle("Image viewer");
		preview.setSize(1080, 720);
		preview.setVisible(true);
	}
	public Preview()
	{
		id= new ArrayList<Integer>();
		JMenuBar menubar = new JMenuBar();
		menuFile = new JMenu("File");
		JMenuItem addImg = new JMenuItem("Add Image");
		addImg.addActionListener( this);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener( this);
		menuFile.add(addImg);
		menuFile.add(exit);
		menubar.add(menuFile); 
		JPanel container = new JPanel(new BorderLayout() ); 
		labelImage = new JLabel();
		imgDesc= new JLabel(detail);
		previous = new JButton (new ImageIcon ("left-icon.png"));
		next = new JButton (new ImageIcon ("right-icon.png"));
		previous.addActionListener( this);
		next.addActionListener( this);		
		loadImageID();
		loadImage(index);
		this.setJMenuBar(menubar);
		container.add(labelImage); 
		this.getContentPane().add(container, BorderLayout.CENTER); 
		this.getContentPane().add(next,BorderLayout.EAST);
		this.getContentPane().add(previous,BorderLayout.WEST);
		this .getContentPane().add(imgDesc,BorderLayout.SOUTH); 
		
	}
	public void loadImageID()
	{
		try
		{	Class.forName("org.sqlite.JDBC"); 
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/harikrishnabasnet/Documents/java/Image gallery/myDB.sqlite"); 		     
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Galary");			
			while (rs.next()) { 
			id.add(rs.getInt("id"));
			}			
			rs.close();	    
			conn.close();		
		}    
		catch(Exception e)
		{
			System.out.println(e.getMessage()); 			
		}	
	}
	public void loadImage(int inx)
	{
		 
		
		try
		{
			Class.forName("org.sqlite.JDBC"); 
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/harikrishnabasnet/Documents/java/Image gallery/myDB.sqlite"); 		     
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Galary WHERE id = "+id.get(inx)+"");			
			while (rs.next()) { 
			path= rs.getString("image_url");
			detail = rs.getString("image_name") + "\t Description:"+ rs.getString("description"); 
			}
			
			
			imgDesc.setText(detail);
			try {	
				URL url = new URL(path);		 	
				Image image = ImageIO.read(url);
				Image resizedImg = image.getScaledInstance(1080, 720, 0);
				labelImage.setIcon(new ImageIcon(resizedImg));
				
			} catch (IOException e) 
			{
				JOptionPane.showMessageDialog(this,"Image not found");
			}
			
			rs.close();	    
			conn.close();		
		}    
		catch(Exception e)
		{
			System.out.println(e.getMessage()); 			
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {

			if ((JButton) arg0.getSource() == next) {
				
				if(index < id.size())
				{
					loadImageID();
					loadImage(++index); 
					System.out.println(id.get(index)); 
				}
				else
				{
					index = 0; 
					loadImage(index);
				}
			}
			else if ((JButton) arg0.getSource() == previous) {
				
				if(index >0)
				{
					loadImage(--index); 
				}
				else
				{
					index = id.size(); 
					loadImage(index);
				}
			}
			}
		 else if (arg0.getSource() instanceof JMenuItem) {
				if (arg0.getActionCommand().equals("Add Image")) {
					AddImage image= new AddImage(); 
					image.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
					image.setTitle("Add Image"); 
					image.setSize(400, 250); 
					image.setVisible(true); 
				}
				if (arg0.getActionCommand().equals("Exit")) {
					this.dispose(); 
				}
			}
		
	}
}
