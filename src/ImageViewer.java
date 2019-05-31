import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
public class ImageViewer extends JFrame implements Serializable {
	JLabel labelName; 
	JLabel labelImage; 
	AddImageUrl add;
	public static void main(String []args)
	{
		ImageViewer viewer= new ImageViewer(); 
		viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewer.setTitle("Image viewer");
		viewer.setSize(1080, 720);
		viewer.setVisible(true);
	}
	public void setImage()
	{
		add= new AddImageUrl("","");
		try
		{
		FileInputStream fs = new FileInputStream("imageSerial.txt");
		ObjectInputStream os = new ObjectInputStream(fs);
		add = (AddImageUrl)os.readObject(); 
		os.close();
		}
		catch(Exception e) 
		{
		System.out.println(e.getMessage());
		} 		 
	}
	public ImageViewer()
	{
		labelName = new JLabel(); 
		labelImage = new JLabel();
		JPanel container = new JPanel(new BorderLayout() ); 
		setImage();
		try {	
			URL url = new URL(add.getUrl());			
			Image image = ImageIO.read(url);
			labelImage.setSize(150, 140);
			Image resizedImg = image.getScaledInstance(1080, 720, 0);
			labelImage.setIcon(new ImageIcon(resizedImg));
		} catch (IOException e) 
		{
			JOptionPane.showMessageDialog(this,"Unable to fetch the image!!");
		}
		labelName.setText(add.getName()); 
		container.add(labelName,BorderLayout.PAGE_START); 
		container.add(labelImage); 
		this.getContentPane().add(container, BorderLayout.CENTER); 
	}
}
