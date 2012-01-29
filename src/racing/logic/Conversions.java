package racing.logic;

import java.util.concurrent.TimeUnit;

public class Conversions {

	public static void lapTime (int i, long l) {
		System.out.println("Lap " + i + ": " + String.format("%d min and %d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(l),
			    TimeUnit.MILLISECONDS.toSeconds(l) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
	}
	
	public static String finishTime (long l, boolean show) {
		String toReturn = null;
		toReturn = "Finish time: " + String.format("%d min and %d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(l),
			    TimeUnit.MILLISECONDS.toSeconds(l) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
		if (show)
			System.out.println(toReturn);
		return toReturn;
	}
	
	/**
	 * For each map unique id.
	 * @param id
	 * @param mapBG
	 * @return mapbackground image or textfile associated with map
	 */
	public String idToFilenames(int id, int info) {
		String str = null;
		
		if (id == 0) {
			if (info == 0)
				str = "generated.txt";
			else if (info == 1)
				str = "map2.png";
		}
		if (id == 1) {
			if (info == 0)
				str = "generated1.txt";
			else if (info == 1)
				str = "map1.png";
		}
		if (id == 5) {
			if (info == 0)
				str = "map2.txt";
			else if (info == 1)
				str = "map2.png";
		}
		if (id == 4) {
			if (info == 0)
				str = "map1.txt";
			else if (info == 1)
				str = "map1.png";
		}
		
		
		return str;
	}
	
	/**
	 * 
	 * @param id
	 * @param info
	 * @return corresponding witdh or height of the map id "id"
	 */
	public int idToDimensions(int id, int info) {
		int dimension = 0;
		
		if (id == 0 || id == 5) {
			if (info == 0) // Width
				dimension = 1440;
			else if (info == 1) // Height
				dimension = 2880;
		}
		if (id == 4 || id ==1) {
			if (info == 0) // Width
				dimension = 1536;
			else if (info == 1) // Height
				dimension = 1056;
		}
		return dimension;
	}

}
