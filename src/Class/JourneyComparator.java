package Class;

import java.util.*;

public class JourneyComparator implements Comparator<Journey> {
	/**
	 * Method to compare journeys according to the fees
	 * 
	 * @param the
	 *            two journeys to compare
	 * */
	public int compare(Journey j1, Journey j2) {
		return Double.compare(j2.getJourneyFee(), j1.getJourneyFee());
	}
}
