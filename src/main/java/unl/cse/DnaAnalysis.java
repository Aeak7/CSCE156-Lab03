package unl.cse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Processes DNA data and counts the number of instances that a particular DNA
 * subsequence appears.
 * 
 * @author akelly
 *
 */
public class DnaAnalysis {

	private static final String DNA_FILE = "data/H1N1nucleotide.txt";
	private static final String DNA;

	static {
		// load the contents of the file statically (when the class
		// is loaded)
		DNA = loadDnaFromFile();
	}

	/**
	 * Loads a DNA string from the {@link #DNA_FILE} and returns it as a string with
	 * all whitespace removed.
	 * 
	 * @return dna
	 */
	public static String loadDnaFromFile() {

		Scanner s = null;
		try {
			s = new Scanner(new File(DNA_FILE));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		String dna = "";

		while (s.hasNextLine()) {
			dna += s.nextLine();
		}
		// close the scanner
		s.close();
		// strip all whitespace
		dna = dna.replaceAll("\\s+", "");
		return dna;
	}

	/**
	 * Counts the number of times <code>subSequence</code> appears in the
	 * {@link #DNA} string
	 * 
	 * @return count - times of subSequence in DNA
	 */
	public static int countSubsequences(String subSequence) {
        int count = 0;
        int startIndex = 0;
        String lowerSub = subSequence.toLowerCase();
        if(DNA.contains(lowerSub) == true) {
            while((startIndex = DNA.indexOf(lowerSub, startIndex)) != -1 ) {
                count++;
                startIndex++;
            }
        }
        else {
            System.err.println("DNA does not contain the substring");
        }
        return count;
    }

	public static void main(String args[]) {
		if (args.length != 1) {
			System.err.println("ERROR: expecting a single subSequuence");
			System.exit(1);
		}
		
		String subSequence = args[0];
			
		int count = countSubsequences(subSequence);

		System.out.println(subSequence + " appears " + count + " times");

	}

}
