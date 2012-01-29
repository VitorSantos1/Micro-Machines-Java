package racing.gui.frameHandling;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import racing.gui.RacingPanel;
import racing.logic.Map;
import racing.logic.Point;

public class frameHandler {

	public frameHandler() {
		
	}
	
	static public int middleX(Container menuFrame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = menuFrame.getSize().width;
		int xCenter = (dim.width-width)/2;
		return xCenter;
	}
	
	static public int middleY(Container menuFrame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int height = menuFrame.getSize().height;
		int yCenter = (dim.height-height)/2;
		return yCenter;
	}

	public static boolean carInMapBorder(RacingPanel racingPanel,
			Map currentMap, Point carPosition,  int diffX, int diffY, Point lastCamera,int mapScaleW,int mapScaleH) {
		
		double dx = carPosition.getX() ;
		double dy = carPosition.getY() ;

		
		/*
		if (dx + diffX <= diffX) return true;
		if (mapScaleW - dx - diffX <= diffX) return true;
		if (dy + diffY <= diffY) return true;
		if (mapScaleH - dy - diffY <= diffY) return true;*/
		
		if ((dx + diffX <= diffX) && (dy + diffY <= diffY)) return true;
		if ((mapScaleW - dx - diffX <= diffX) && (dy + diffY <= diffY)) return true;
		if ((dx + diffX <= diffX) && (mapScaleH - dy - diffY <= diffY)) return true;
		if ((mapScaleW - dx - diffX <= diffX) && (mapScaleH - dy - diffY <= diffY)) return true;
		if ((dy + diffY <= diffY) && ((dx <= lastCamera.getX() + diffX ) && dx >= lastCamera.getX())) return false;
		if ((dy + diffY <= diffY) ) return true;

		
		return false;
	}

	
}
