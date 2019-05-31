
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*; 

public class AddImage extends JFrame implements ActionListener{
	JLabel lblName; 
	JLabel lblUrl; 
	JLabel lblDesc;
	JTextField txtName, txtUrl, txtDesc;
	JButton add,cancel; 

	public static void main(String args[])
	{
		AddImage image= new AddImage(); 
		image.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		image.setTitle("Add Image"); 
		image.setSize(400, 350); 
		image.setVisible(true); 
	}	
	public AddImage()
	{
		JPanel container = new JPanel ();
		GridBagLayout gridbag = new GridBagLayout();  
		GridBagConstraints c = new GridBagConstraints();  
		container.setLayout(gridbag); 
		lblName = new JLabel("Image Name");
		lblUrl= new JLabel ("Image URL");
		lblDesc= new JLabel("Description"); 
		txtName = new JTextField ("",20); 
		txtUrl = new JTextField ("",20); 
		txtDesc= new JTextField("",20); 
		add= new JButton ("Save");
		cancel = new JButton("Cancel"); 
		add.addActionListener(this); 
		cancel.addActionListener(this); 
		
		container.add(lblName,c);
		container.add(txtName,c);
		
		c.gridy=1;
		
		container.add(lblUrl,c); 		
		container.add(txtUrl,c);
		
		c.gridy=2;
		
		container.add(lblDesc,c); 		
		container.add(txtDesc,c);
		
		c.gridy=3;
		container.add(add,c );
		container.add(cancel,c ); 
		
		this.getContentPane().add(container,BorderLayout.CENTER); 
		
	}
	public void addImage(String name, String url, String desc)
	{
		try
		{
		//loads the driver
		Class.forName("org.sqlite.JDBC"); 
		Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/harikrishnabasnet/Documents/java/Image gallery/myDB.sqlite"); 		     
	    Statement stat = conn.createStatement(); 	 
	    stat.executeUpdate("INSERT INTO Galary(description, image_name, image_url)VALUES ('"+desc+"','"+name+"','"+url+"')");
	    JOptionPane.showMessageDialog(this,"Image saved"); 
	    conn.close();		
		}    
		catch(Exception e)
		{
			System.out.println(e.getMessage()); 
			 JOptionPane.showMessageDialog(this,"Failed to save Image"); 
		}	
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JButton)
		{
		
		if((JButton)arg0.getSource() == add)
		{
		addImage(txtName.getText(), txtUrl.getText(),txtDesc.getText()); 
		txtName.setText(""); 
		txtUrl.setText(""); 
		txtDesc.setText(""); 
		}
		else if((JButton)arg0.getSource() == cancel)
		{
		this.dispose();  
		}
		}
		
	}
}
