package unl.cse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Processes a comma-separated value (CSV) file of win/loss data from the 2011
 * National League MLB season. It sorts the teams (best record to worst) and
 * prints a report to the standard output.
 * 
 * @author akelly
 *
 */
public class Baseball {

	private static final String FILE_NAME = "data/mlb_nl_2011.csv";

	/**
	 * This method loads MLB team data from the CSV file
	 * specified by {@link #FILE_NAME} and instantiates 
	 * and returns a list of {@link Team}s.
	 * 
	 * @return teams - ArrayList of teams from csv file
	 */
	public static List<Team> loadData() {
		
		List<Team> teams = new ArrayList<>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
			while ((line = br.readLine()) != null) {
				String [] values = line.split(",");
				int wins = Integer.parseInt(values[1]);
				int losses = Integer.parseInt(values[2]);
				Team t = new Team(values[0], wins, losses);
				teams.add(t);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return teams;
	}
	
	/**
	 * Writes the given list of teams into the given 
	 * file {@link outputFileName} to each line
	 * 
	 * @param teams
	 * @param outputFileName
	 */
	public static void persistData(List<Team> teams, String outputFileName) {
		try {
			PrintWriter pw = new PrintWriter(outputFileName);
			for(Team t : teams) {
				pw.println(t);
			}
			pw.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		
		String outputFile = "data/output.txt";
		persistData(teams, outputFile);
	}

}
