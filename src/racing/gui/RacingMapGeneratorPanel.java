package racing.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollBar;


import racing.gui.imageHandling.ImageHandler;
import racing.logic.Car;
import racing.logic.CheckPoint;
import racing.logic.CollisionObject;
import racing.logic.Conversions;
import racing.logic.DelayObject;
import racing.logic.FinishLine;
import racing.logic.GenericObject;
import racing.logic.JumpingPlatform;
import racing.logic.Map;
import racing.logic.Obstacle;
import racing.logic.OutOfBounds;
import racing.logic.Wall;

public class RacingMapGeneratorPanel extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4842314332529175640L;
	/*private Vector<Point> points1 = new Vector<Point>();
    private Vector<Point> points2 = new Vector<Point>();*/
	private Point points1 = new Point(0,0);
	private Point points2 = new Point(0,0);
    Vector<Rectangle> rectanglesDrawn = new Vector<Rectangle>();
    Vector<Integer> types = new Vector<Integer>();
    private Point start = new Point();
    private Point end   = new Point();
    private Point temp  = new Point();
    Rectangle rect = new Rectangle();
    
   
    Vector<CheckPoint> checkpoints = new Vector<CheckPoint>();
    TreeSet<GenericObject> tree;
	
	private boolean isWall = false;
	private boolean isCar = false;
	private boolean isObstacle = false;
	private boolean isCheckpoint = false;
	private boolean isFinishLine = false;
	private boolean newRect = false;
    
	private Integer xInc;
	private Integer yInc;
	
	private Integer widthI;
	private Integer heightI;
	private int WidthHeight[];
	private int objectComponent[];
	private Image map = null;
	private int objectType;
	private boolean dragged;
	private boolean isJumpingPlatform;
	private boolean isOutOfBounds;
	private boolean isDelayObject;
	private Object type;
	
	public RacingMapGeneratorPanel(int mapID, Dimension mapDim, int array[], int objectComponent[])
	{
		/*
		xInc = widthInc;
		yInc = heightInc;*/
		tree = new TreeSet<GenericObject>(new PositionComparator());
		
		WidthHeight = array;
		
		System.out.println("Antes: " + WidthHeight[0] + "  " + WidthHeight[1]);
		
		
		
		ImageHandler imgHandler = new ImageHandler(false);
		/*if (mapID == 0)
			map = imgHandler.getBackground(this, "map2.png", 1440, 2880); // Select map according to the selected in logic*/
		
		Conversions conv = new Conversions();
		String file;
		file = conv.idToFilenames(mapID, 1);
			
		System.out.println("File: " + file);
		map = imgHandler.getBackground(this, file, mapDim.width, mapDim.height); // Select map according to the selected in logic
		
	
		
		
        addMouseMotionListener( new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent ev)
            {
                end = ev.getPoint();
                
                rect.setFrameFromDiagonal(start, end);
                dragged = true;
                repaint();
            }//end mouse drag
        });
        
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                start = e.getPoint();
                dragged = false;
            }

            public void mouseReleased(MouseEvent ev)
            {
                temp = ev.getPoint();
                newRect = true;
                dragged = false;
                repaint();
            }
        });
        
        addKeyListener(new KeyListener()
        {

			@Override
			public void keyPressed(KeyEvent arg0) 
			{
				if(arg0.getKeyCode() == KeyEvent.VK_UP)
				{
					if(yInc > 0)
						yInc--;
					System.out.println("OK_UP");
				}
				else if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
				{
					if(yInc < map.getHeight(null))
						yInc++;
					System.out.println("OK_DOWN");
				}
				else if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
				{
					if(xInc > 0)
						xInc--;
					System.out.println("OK_LEFT");
				}
				else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					if(xInc < map.getWidth(null))
						xInc++;
					System.out.println("OK_RIGHT");
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) 
			{
				
			}
        	
        });
	}
	
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.drawImage(map, (-1)*WidthHeight[0] /*+ this.getWidth()*/, (-1)*WidthHeight[1] /*+ this.getHeight()*/, this);
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
  
        if (dragged) {
        	g2.drawRect(rect.x  , rect.y , rect.width, rect.height); }
               
        if (newRect) {
        Rectangle r = new Rectangle();
        r.setFrameFromDiagonal(start, end);
    	System.out.println("Antes - rx: " + r.x + " ry: " + r.y);
    	r.x = r.x + WidthHeight[0];
    	r.y = r.y + WidthHeight[1];
    	
    	System.out.println("Depois - rx: " + r.x + " ry: " + r.y);

    	rectanglesDrawn.add(r);
    	types.add(boolToInt());
    	//treeAdd(r);

        newRect = false;
        dragged = false;
        }
        
        
        for (Rectangle r: rectanglesDrawn)
        	g2.drawRect(r.x - WidthHeight[0], r.y - WidthHeight[1], r.width, r.height);
        
		repaint();
    }
    

    
    int boolToInt () {
    	
    	if (isCheckpoint)
    		return 0;
    	if (isWall)
    		return 1;
    	if (isDelayObject)
    		return 2;
    	if (isJumpingPlatform())
    		return 3;
    	if (isOutOfBounds)
    		return 4;
    	if (isObstacle)
    		return 6;
    	
    	return 0;
    }
    
    void addAll () {
    	int i = 0;
    	for (Rectangle r: rectanglesDrawn) {
    		treeAdd(r, types.get(i));
    		i++;
    	}
    }

	private void treeAdd(Rectangle r, int type) {
		
		if (type == 1) {
			Wall wall = new Wall(r.x, r.y, r.x + r.width, r.y + r.height);
			tree.add(wall);
		}
		else if(type == 6) {
			CollisionObject co = new CollisionObject(r.x, r.y, r.width, r.height);
			tree.add(co);
		}
		else if (type == 0) {
			CheckPoint check = new CheckPoint(r.x, r.y, r.x + r.width, r.y + r.height);
			checkpoints.add(check);
		}
		else if (type == 3) {
			JumpingPlatform jp = new JumpingPlatform(r.x, r.y, r.width, r.height);
			tree.add(jp);
		}
		else if (type == 4) {
			OutOfBounds ob = new OutOfBounds(r.x, r.y, r.width, r.height);
			tree.add(ob);
		}
		else if (type == 2) {
			DelayObject doj = new DelayObject(r.x, r.y, r.width, r.height);
			tree.add(doj);
		}
	}

	public boolean isJumpingPlatform() {
		return isJumpingPlatform;
	}

	public void setJumpingPlatform(boolean isJumpingPlatform) {
		this.isJumpingPlatform = isJumpingPlatform;
	}

	public boolean isOutOfBounds() {
		return isOutOfBounds;
	}

	public void setOutOfBounds(boolean isOutOfBounds) {
		this.isOutOfBounds = isOutOfBounds;
	}

	public boolean isDelayObject() {
		return isDelayObject;
	}

	public void setDelayObject(boolean isDelayObject) {
		this.isDelayObject = isDelayObject;
	}

	public Vector<Rectangle> getRectanglesDrawn() {
		return rectanglesDrawn;
	}

	public void setRectanglesDrawn(Vector<Rectangle> rectanglesDraw) {
		this.rectanglesDrawn = rectanglesDraw;
	}

	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}

	public boolean isWall() {
		return isWall;
	}

	public void setCar(boolean isCar) {
		this.isCar = isCar;
	}

	public boolean isCar() {
		return isCar;
	}

	public void setObstacle(boolean isObstacle) {
		this.isObstacle = isObstacle;
	}

	public boolean isObstacle() {
		return isObstacle;
	}

	public void setCheckpoint(boolean isCheckpoint) {
		this.isCheckpoint = isCheckpoint;
	}

	public boolean isCheckpoint() {
		return isCheckpoint;
	}

	public void setFinishLine(boolean isFinishLine) {
		this.isFinishLine = isFinishLine;
	}

	public boolean isFinishLine() {
		return isFinishLine;
	}
	
	class PositionComparator implements Comparator<GenericObject>{
		   
	    public int compare(GenericObject obj1, GenericObject obj2){
	       
	        double obj1X = obj1.getCoordX();        
	        double obj2X = obj2.getCoordX();
	        
	        double obj1Y = obj1.getCoordY();        
	        double obj2Y = obj2.getCoordY();
	        
	        if(obj1X > obj2X)
	            return 1;
	        else if(obj1X < obj2X)
	            return -1;
	        else if(obj1Y > obj2Y)
	        	return 1;
	        else if(obj1Y < obj2Y)
	        	return -1;
	        else
	        	return 0; // Objects are in the same position
	            
	    }
	}

}
