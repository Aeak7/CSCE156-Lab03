package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Processes a comma-separated value (CSV) file of win/loss data from the 2011
 * National League MLB season. It sorts the teams (best record to worst) and
 * prints a report to the standard output.
 * 
 * @author cbourke
 *
 */
public class Baseball {

	private static final String FILE_NAME = "data/mlb_nl_2011.csv";

	/**
	 * This method loads MLB team data from the CSV file
	 * specified by {@link #FILE_NAME} and instantiates 
	 * and returns a list of {@link Team}s.
	 * 
	 * @return
	 */
	public static List<Team> loadData() {

		List<Team> teams = new ArrayList<>();

		// TODO: write code to open the file, process it line-by-line
		// to create team instances and add them to the list.
		//
		// Be sure to close the scanner
		
		Scanner s = null;
		try {
			s = new Scanner(new File(FILE_NAME));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		while (s.hasNextLine()) {
			//s.nextLine();
			System.out.println(s.nextLine()); //HERE
			String line = s.nextLine();
			teams.add(null);
		}
		
		return teams;
	}
	
	public static void persistData(List<Team> teams, String outputFileName) {
		//TODO: implement the file output method
		//takes teams, outputs to a file
	}
	
	public static void main(String args[]) {

		List<Team> teams = loadData();

		System.out.println("Teams: ");
		for (Team t : teams) {
			System.out.println(t);
		}

		Collections.sort(teams, Team.teamByWinPercentage);

		System.out.println("\n\nSorted Teams: ");
		for (Team t : teams) {
			System.out.println(t);
		}
		
		//TODO: call your file output method with the sorted teams

	}

}
