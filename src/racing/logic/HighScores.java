package racing.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Read write operations in files to present / save the overall
 * highscores of players.
 *
 */
public class HighScores {

	private String filename;
	private List<String> readContent;
	
	/**
	 * The name of the file should correspond to the map name.
	 * @param file
	 */
	public HighScores(String file) {
		String temp = file;
		temp = "scores.txt";
		filename = "Resources/Scores/";
		filename = filename + temp;
		readContent = new ArrayList<String>();
	}
	
	/**
	 * Retrieves the data in file.
	 * @return Vector<String> line by line
	 */
	public List<String> readRecord() {
		readThread.start();
		return readContent;
	}
	
	public void writeRecord(String toWrite) {
		try {

			readContent = readRecord();

			try {
				readThread.join();
				// Wait for thread to finish
			} catch (InterruptedException e) {
				// Thread was interrupted
			}
			Matcher matcher = Pattern.compile("\\d+").matcher(toWrite);
			matcher.find();
			long points =   Long.valueOf(matcher.group());
			long pointsTemp = 0;

			boolean breakW = true;

			Iterator litr = readContent.iterator(); 
			while (litr.hasNext() && breakW) {
				String string = (String)litr.next();

				matcher = Pattern.compile("\\d+").matcher(string);
				matcher.find();
				pointsTemp = Long.valueOf(matcher.group());
				System.out.println("Parsing: " + pointsTemp);
				if (points >= pointsTemp) { 
					// Order the file by finish points and not times
					readContent.add(readContent.indexOf(string) , toWrite);
					breakW = false;
				}
			}

			// Rewrite file
			BufferedWriter out = new BufferedWriter(new FileWriter(filename));
			for (String string: readContent) {
				out.write(string + "\n");
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Error writing file: " + e.getMessage());
		}
	}
	
	public void writeHighScore(String playerName, long points, long time) {
		writeRecord(playerName + "      " + points + "    " + Conversions.finishTime(time, false));
	}
	
	/**
	 * We use a thread to guarantee the program won't block and continues responsive.
	 */
	Thread readThread = new Thread() {
		public void run() {
			try{
				FileInputStream fstream = new FileInputStream(filename);
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;

				while ((strLine = br.readLine()) != null)   {
					readContent.add(strLine);
				}
				in.close();
			}
			catch (IOException e){
				System.err.println("Error reading file: " + e.getMessage());
			}
		}
	};

	public void show() {
		System.out.println("File: " + filename);
		
		readThread.start();
	}
}
