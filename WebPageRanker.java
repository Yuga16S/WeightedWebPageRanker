import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

/**
 * Program to rank web pages based on their score (summation of occurrences of all the keywords in the web page) 
 * and prints the top 10 results.
 * @author yugapriya
 */
public class WebPageRanker {

	private static final String PROJECT_PATH = System.getProperty("user.dir"); // Points to the project's directory
	private static final String HTML_FOLDER_PATH = PROJECT_PATH + "/resources/html_test_files/";
	
	public static void main(String[] args) {
		String[] keysToFind = {"WebInnovationX", "Digital Transformation", "businesses"}; 
		File htmlFolder = new File(HTML_FOLDER_PATH);
		
		//NOTE: Since we are making the heap hold comparable objects, we are not passing a comparator in the constructor below
		PriorityQueue<WeightedWebPage> heap = new PriorityQueue<>(100); //setting the initial size of the heap as 100
		
		for(File htmlFile : htmlFolder.listFiles()) { // Iterating over each file in the HTML_FOLDER_PATH
			int occurence = 0;
			for (String keyToFind : keysToFind) {
				try {
					occurence += findMatches(htmlFile, keyToFind);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return; // Exiting the program when the file gets abruptly deleted during program execution.
				}
			}
			
			WeightedWebPage weightedWebPage = new WeightedWebPage(htmlFile.getName(), occurence);
			heap.add(weightedWebPage);
		}
		
		// Printing the top 10 html filenames
		for(int i = 0; i<10; i++) {
			if(heap.size() == 0) {
				// Heap is empty
				System.out.println("No more webpages.");
				break;
			}
			
			System.out.println(heap.poll().getFilename());
		}

	}
	
	/*
	 * Given a file and keyword, returns the number of occurrences of the keyword in the file.
	 */
	private static int findMatches(File file, String keyword) throws FileNotFoundException {
		int count = 0;
		Scanner sc = new Scanner(file); 
		try {
			while(sc.hasNext()) {
				String line = sc.nextLine();
				count += StringUtils.countMatches(line, keyword); //case sensitive
			}
		} catch (Exception e) {
			// we simply return 0 in case of any error
			return 0;
		} finally {
			sc.close();
		}
		return count;
	}
	
}
