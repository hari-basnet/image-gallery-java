import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddImageUrl extends JFrame implements ActionListener,Serializable {
	private String imageName; 
	private String imageUrl;
	JLabel lblName; 
	JLabel lblUrl; 
	JTextField txtName, txtUrl;
	JButton add,cancel; 

	public static void main(String args[])
	{
		AddImageUrl image= new AddImageUrl(); 
		image.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		image.setTitle("Button action listner"); 
		image.setSize(400, 350); 
		image.setVisible(true); 
	}	
	public AddImageUrl()
	{
		JPanel container = new JPanel ();
		GridBagLayout gridbag = new GridBagLayout();  
		GridBagConstraints c = new GridBagConstraints();  
		container.setLayout(gridbag); 
		lblName = new JLabel("Image Name");
		lblUrl= new JLabel ("Image URL");
		txtName = new JTextField ("",20); 
		txtUrl = new JTextField ("",20); 
		add= new JButton ("Add");
		cancel = new JButton("Cancel"); 
		add.addActionListener(this); 
		cancel.addActionListener(this); 
		
		container.add(lblName,c);
		container.add(txtName,c);
		
		c.gridy=1;
		
		container.add(lblUrl,c); 		
		container.add(txtUrl,c);
		
		c.gridy=2;
		container.add(add,c );
		container.add(cancel,c ); 
		
		this.getContentPane().add(container,BorderLayout.CENTER); 
		
	}
	public AddImageUrl(String name, String url)
	{
		imageName= name; 
		imageUrl= url; 
	}
	public String getUrl()
	{
		return imageUrl; 
	}
	public String getName()
	{
		return imageName; 
	}
	public void serializeObject()
	{
		AddImageUrl addImage = new AddImageUrl(txtName.getText(),txtUrl.getText()); 
		try
		{
		FileOutputStream file = new FileOutputStream("imageSerial.txt");
		ObjectOutputStream object = new ObjectOutputStream(file);
		object.writeObject(addImage);
		object.flush();
		object.close();
		}
		catch(Exception e) 
		{
		System.out.println(e.getMessage());
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JButton)
		{
		
		if((JButton)arg0.getSource() == add)
		{
		serializeObject(); 
		txtName.setText(""); 
		txtUrl.setText(""); 
		}
		else if((JButton)arg0.getSource() == cancel)
		{
		this.dispose();  
		}
		}
		
	}
}
