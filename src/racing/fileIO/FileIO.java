package racing.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import racing.logic.CheckPoint;
import racing.logic.CollisionObject;
import racing.logic.Conversions;
import racing.logic.DelayObject;
import racing.logic.GenericObject;
import racing.logic.JumpingPlatform;
import racing.logic.Map;
import racing.logic.OutOfBounds;
import racing.logic.Wall;

/**
 * Handles import and export of maps, saved in a plaintext.
 * @version 1.0
 * @created 16-Mai-2011 10:42:30
 */
public class FileIO {

	int mapID;
	TreeSet<GenericObject> tree;
	Vector<CheckPoint> checkpoints;
	Map map = null;
	Conversions conv = new Conversions();
	
	public void finalize() throws Throwable {

	}

	/**
	 * To be used in map editor import.
	 * @param mapID
	 * @param tree
	 * @param checkpoints
	 */
	public FileIO(int mapID, TreeSet<GenericObject> tree, Vector<CheckPoint> checkpoints){
		this.mapID = mapID;
		this.tree = tree;
		this.checkpoints = checkpoints;
	}
	
	/**
	 * To be used in map import.
	 * @param map
	 */
	public FileIO(Map map, int id){
		this.mapID = id;
		this.map = map;
		this.tree = map.getTree();
		this.checkpoints = map.getCheckpoints();
		//this.map = map;
	}
	
	public void exportMap() {
		exportThread.start();
		try {
		    exportThread.join();
		    // Wait for thread to finish
		} catch (InterruptedException e) {
		    // Thread was interrupted
		}
	}
	
	
	/**
	 * We use a thread to guarantee the program won't block and continues responsive.
	 */
	Thread exportThread = new Thread() {
		public void run() {
			try{
				
				BufferedWriter out = new BufferedWriter(new FileWriter("Resources/Maps/Saves/" + conv.idToFilenames(mapID, 0), false));
				//BufferedWriter out = new BufferedWriter(new FileWriter("save.txt", false));
				
			
				out.write(Integer.toString(mapID) + "\n");
				
				for (CheckPoint check: checkpoints) {
			    	out.write(check.write() + "\n");
			    }
				
			    for (GenericObject go: tree) {
			    	out.write(go.write() + "\n");
			    }
			    out.close();
			}
			catch (IOException e){
				System.err.println("Error reading file: " + e.getMessage());
			}
		}
	};
	
	/**
	 * Imports the data in the file save identified by id to
	 * the checkpoint / tree referenced.
	 * @param id
	 * @param exportTomap
	 */
	public void importMap() {
		
		readThread.start();
		try {
		    readThread.join();
		    // Wait for thread to finish
		} catch (InterruptedException e) {
		  // Thread was interrupted
		}
	}
	
	Thread readThread = new Thread() {
		public void run() {
			try{
				
				String strLine;
				int type;
				double x1;
				double y1;
				double x2;
				double y2;
				
				Scanner in = new Scanner(new FileInputStream("Resources/Maps/Saves/" + conv.idToFilenames(mapID, 0)));

				strLine = in.next();
				mapID = Integer.parseInt(strLine);
				if (map != null)
					map.setID(mapID);
				
				Matcher matcher = Pattern.compile("\\d+").matcher(strLine);
				while (in.hasNext())   {

					type = Integer.valueOf(in.next());
					//x1 = Double.valueOf(in.next());
					x1 = Double.valueOf(in.next()) - 20;
					y1 = Double.valueOf(in.next()) - 23;
					x2 = Double.valueOf(in.next()) - 20;
					//x2 = Double.valueOf(in.next()) ;
					y2 = Double.valueOf(in.next()) - 23 ;
										
					//System.out.println("Importing : " + type + " " + x1 + " " + y1 + " " + x2 + " " + y2 );

					if (type == 0) { // Checkpoint
						checkpoints.add(new CheckPoint(x1,y1,x2,y2));
					}
					else if (type == 1) { // Wall
						tree.add(new Wall(x1,y1,x2,y2));
					}
					else if (type == 2) {
						tree.add(new DelayObject(x1,y1,(int)x2,(int)y2));
					}
					else if (type == 3) {
						tree.add(new JumpingPlatform(x1,y1,(int)x2,(int)y2));
					}
					else if (type == 4) {
						tree.add(new OutOfBounds(x1,y1,(int)x2,(int)y2));
					}
					else if (type == 6) {
						tree.add(new CollisionObject(x1,y1,(int)x2,(int)y2));
					}
					
					
				}
				
				System.out.println("Imported");
				in.close();
			}
			catch (IOException e){
				System.err.println("Error reading file: " + e.getMessage());
			}
		}
	};

}