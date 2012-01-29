package racing.logic;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

import racing.fileIO.FileIO;


public class Map {

	private int mapID;
	protected int width;
	protected int height;
	private Vector<CheckPoint> checkpoints;
	private Vector<Obstacle> obstacles;
	private Vector<Line> lines;
	private int laps;
	private Vector<Player> players;
	private String bgImageFilename;
	
	/**
	 * TreeSet implementation. We store all the GenericObjects in this binary tree, ordered by:
	 * 1 - X coordinate
	 * 2 - Y coordinante
	 * By using a TreeSet, we also guarantee that there is no duplicate objects, as well as have 
	 * constant log(o) add and removal time complexity.
	 */
	private TreeSet<GenericObject> tree;

	public void finalize() throws Throwable {

	}
	
	public Map(boolean importM, int id) {
		tree = new TreeSet<GenericObject>(new PositionComparator());
		checkpoints = new Vector<CheckPoint>();

		FileIO fileImport = new FileIO(this, id);
		fileImport.importMap();
		
		Conversions conv = new Conversions();
		bgImageFilename = conv.idToFilenames(mapID, 1);
		// Get the filename based in the mapID
		
		this.width = conv.idToDimensions(mapID, 0);
		this.height = conv.idToDimensions(mapID, 1);
		System.out.println("Filename: " + bgImageFilename);
		System.out.println("Width: " + width);
		System.out.println("Height: " + height);
	}


	public TreeSet<GenericObject> getTree() {
		return tree;
	}

	public void setTree(TreeSet<GenericObject> tree) {
		this.tree = tree;
	}

	public Vector<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(Vector<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public Vector<CheckPoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(Vector<CheckPoint> checkpoints) {
		this.checkpoints = checkpoints;
	}


	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public Vector<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Vector<Player> players) {
		this.players = players;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Vector<Line> getLines() {
		return this.lines;
	}

	public String getFileName() {
		return bgImageFilename;
	}

	
	public void showTree() {
	    Iterator<GenericObject> treeIterator = tree.iterator();
	    GenericObject go;

	    System.out.println("Show tree:");
	    System.out.println("Size: " + tree.size());
	    while(treeIterator.hasNext()) 
	    {
	    	go = treeIterator.next();
	    	if (go instanceof Wall)
	    		subClassing((Line)go);
	    	else if (go instanceof JumpingPlatform)
	    		subClassing((JumpingPlatform)go);
	    	else if (go instanceof GenericObject)
	    		subClassing((GenericObject)go);
	    	
	        go.show();
	    }
	}
	
	void subClassing (Line wall) {
		System.out.println("I'm an object and a wall!");
	}
	
	void subClassing (JumpingPlatform platform) {
		System.out.println("I'm an object and a platform!");
	}
	
	void subClassing (GenericObject object) {
		System.out.println("I'm a generic object!");
	}

	public int getnumCheckpoints() {
		return checkpoints.size();
	}

	public void setID(int mapID2) {
		mapID = mapID2;
	}

	public void resetCheckpoints() {
		for (CheckPoint check: checkpoints)
			check.setCrossed(false);
	}

}

/**
 * Comparator for use in the treeSet.
 *
 */
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