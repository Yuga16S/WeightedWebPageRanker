/*
 * A comparable model to store file name with number of occurrence of a keyword(score).
 */
public class WeightedWebPage implements Comparable<WeightedWebPage> {
	
	private String filename;
	private int numberOfOccurrence;
	
	public WeightedWebPage(String filename, int numberOfOccurrence) {
		this.filename = filename;
		this.numberOfOccurrence = numberOfOccurrence;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getNumberOfOccurrence() {
		return numberOfOccurrence;
	}

	public void setNumberOfOccurrence(int numberOfOccurrence) {
		this.numberOfOccurrence = numberOfOccurrence;
	}

	/**
	 * returns -1 when o1 > o2
	 *         +1 when o1 < o2
	 *          0 when o1 = o2 
	 * NOTE: This is opposite to its natural ordering. 
	 *       This way the web pages get sorted in descending order of their score by default
	 */
	private int compare(WeightedWebPage o1, WeightedWebPage o2) {
		if (o1.getNumberOfOccurrence() < o2.getNumberOfOccurrence()) {
			return 1;
		} else if (o1.getNumberOfOccurrence() > o2.getNumberOfOccurrence()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int compareTo(WeightedWebPage o) {
		return compare(this, o);
	}
	
	
	
	
}
