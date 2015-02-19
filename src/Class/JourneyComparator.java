package Class;

import java.util.*;

public class JourneyComparator implements Comparator<Journey>{

	public int compare(Journey j1, Journey j2){
		return Double.compare(j2.getJourneyFee(),j1.getJourneyFee());
	}
}
