package Class;

import java.util.*;

public class JourneyComparator implements Comparator<Journey>{

	public int compare(Journey j1, Journey j2){
		return Double.compare(j1.getJourneyFee(), j2.getJourneyFee());
	}
}
