package Class;

import java.util.*;

public class JourneyComparator implements Comparator<Journey>{

	public int compare(Journey j1, Journey j2){
		if (j1.getJourneyFee() < j2.getJourneyFee()) return -1;
        if (j1.getJourneyFee() > j2.getJourneyFee()) return 1;
        return 0;
	}
}
