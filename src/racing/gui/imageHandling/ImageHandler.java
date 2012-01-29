package racing.gui.imageHandling;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import racing.gui.RacingPanel;

public class ImageHandler {
	
	String carsPath = "Resources/Sprites/Cars/";
	String bgPath   = "Resources/Maps/Background/";
	String numPath  = "Resources/Sprites/Numbers/";
	String wordPath  = "Resources/Sprites/Words/";
	
	// Images for numbers
	private Image zero;
	private Image one;
	private Image two;
	private Image three;
	private Image four;
	private Image five;
	private Image six;
	private Image seven;
	private Image eight;
	private Image nine;
	private Image points;
	
	
	public ImageHandler (boolean loadImages) {
		if (loadImages) {
			zero = getNumber("0-35.png");
			one = getNumber("1-35.png");
			two = getNumber("2-35.png");
			three = getNumber("3-35.png");
			four = getNumber("4-35.png");
			five = getNumber("5-35.png");
			six = getNumber("6-35.png");
			seven = getNumber("7-35.png");
			eight = getNumber("8-35.png");
			nine = getNumber("9-35.png");	
			points = getNumber("pontos.png");
		}
			
	}
	
	public Image getCar(int i, int j) {
		String path = null;
		if (i == 0) {
			if (j == 0)
				path = "drifter.png";
			else
				path = "drifter2.png";
		}
		else if (i == 1) {
			if (j == 0)
				path = "runner.png";
			else
				path = "runner2.png";
		}
		else if (i == 2) {
			if (j == 0)
				path = "speeder.png";
			else
				path = "speeder2.png";
		}
		else if (i == 4) {
			if (j == 0)
				path = "solid.png";
			else
				path = "solid2.png";
		}
			
		
		return getImage(carsPath, path);
	}
	
	public Image getBackground(JPanel jPanel, String bgName, int width, int height) {
		Image bgImage = getImage(bgPath, bgName);
		
		bgImage = setScaledImage(jPanel, bgImage, width,  height, 2);
		return bgImage;
	}

	public Image setScaledImage(JPanel jPanel, Image bgImage, int width, int height, int zoom) {
	   if ( bgImage != null ) {
			
	        float iw = width;
	        float ih = height;
	        float pw = jPanel.getWidth();   //panel width
	        float ph = jPanel.getHeight();  //panel height
	
	        if ( pw < iw || ph < ih ) {
	
	            /* compare some ratios and then decide which side of image to anchor to panel
	               and scale the other side
	               (this is all based on empirical observations and not at all grounded in theory)*/
	
	            if ( (pw / ph) > (iw / ih) ) {
	                iw = -1;
	                ih = ph;
	            } else {
	                iw = pw;
	                ih = -1;
	            }
	
	            //prevent errors if panel is 0 wide or high
	            if (iw == 0) {
	                iw = -1;
	            }
	            if (ih == 0) {
	                ih = -1;
	            }
	
	            // Zoom in
	            bgImage = bgImage.getScaledInstance( (int)(width*zoom),
	                      (int)(height*zoom), Image.SCALE_DEFAULT);
	            
	            
	
	        }
	    }
	   return bgImage;
	}

	public Image getNumber(String spriteName) {
			return getImage(numPath, spriteName);
	}
	
	public Image getImage(String Path, String spriteName) {
		Image Sprite = null;
		try {
			System.out.println("File2: " + Path + spriteName);
			Sprite = ImageIO.read(new File(Path + spriteName));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return Sprite;
	}

	public Image getImage(String Path) {
		Image Sprite = null;
		try {
			Sprite = ImageIO.read(new File(Path));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return Sprite;
	}
	
	public Image getWord(String string) {
		return getImage(wordPath, string);
	}

	public void showLapTime(long l, Graphics g, RacingPanel racingPanel) {
		int width = 10;;
		int dig1, dig2;
		
		int sec = (int)(l / 1000);
		int min = sec / 60;
		//System.out.println("min: " + min);
		//System.out.println("sec: " + sec);
		/*if (min == sec) {
			dig1 = 0;
			dig2 = 0;
		}
		else {*/
			dig1 = min / 10;
			dig2 = min % 10;
		//}
		// min
		g.drawImage(convertToImg(dig1), width, 10, racingPanel);
		g.drawImage(convertToImg(dig2), width+=23, 10, racingPanel);
		g.drawImage(points, width+=23, 10, racingPanel);
		
		// sec
		sec = sec % 60;
		dig1 = sec / 10;
		dig2 = sec % 10;
		g.drawImage(convertToImg(dig1), width+=12, 10, racingPanel);
		g.drawImage(convertToImg(dig2), width+=23, 10, racingPanel);
		g.drawImage(points, width+=23, 10, racingPanel);
		
		int num = (int)(l % 1000);
		num = num / 10;
		dig1 = num / 10; 
		dig2 = num % 10;
		// ms
		g.drawImage(convertToImg(dig1), width+=12, 10, racingPanel);
		g.drawImage(convertToImg(dig2), width+=23, 10, racingPanel);
		}

	private Image convertToImg(int dig1) {
		switch (dig1) {
		case 0: return zero;
		case 1: return one;
		case 2: return two;
		case 3: return three;
		case 4: return four;
		case 5: return five;
		case 6: return six;
		case 7: return seven;
		case 8: return eight;
		case 9: return nine;
		}
		return null;
	}
	
	
	
}
