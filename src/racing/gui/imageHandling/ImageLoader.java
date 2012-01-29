package racing.gui.imageHandling;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImageLoader extends JPanel{
	
	private String address = null;  //  @jve:decl-index=0:
	Image imageToPrint = null;  //  @jve:decl-index=0:
	
	public ImageLoader(String add) throws IOException
	{
		setAddress(add);
		
		this.setSize(512, 512);
		
		initializeGraphs();
	}
	
	public void initializeGraphs() throws IOException
	{
		this.imageToPrint = (new ImageIcon(this.getAddress())).getImage();
		Dimension size = new Dimension(imageToPrint.getWidth(null), imageToPrint.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void setAddress(String address) 
	{ 
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int x = (this.getWidth() - imageToPrint.getWidth(null))/2;
		int y = (this.getHeight() - imageToPrint.getHeight(null))/2;
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(imageToPrint, x, y, null);
	}
}