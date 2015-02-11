package Class;

import java.io.FileNotFoundException;
import java.util.*;

public class Manager {
	/**
	 * Initialise constants
	 */
	public static final String PATH_DEST_VALID = "ValidDestinations.txt";
	public static final String PATH_DEST_2014 = "VisitedDestination";
	public static final String DEST_NEW_PLACES = " NEW PLACES IN 2015";
	public static final String DEST_OLD_PLACES =  " PLACES VISITED IN 2014 ONLY";
	public static final String DEST_BOTH_PLACES = " PLACES VISITED IN BOTH 2014 AND 2015";
	
	/** List of taxis **/
	private TaxiList taxiList;
	/** List of journeys **/
	private JourneyList journeyList;
	/** List of destinations visited **/
	private DestinationsVisited destinationsVisited;
	
	//constructor
	public Manager(){
		taxiList = new TaxiList();
		journeyList = new JourneyList();
		destinationsVisited = new DestinationsVisited();
	}
	
	public void run(){
		try {
			// Import valid destinations
			destinationsVisited.readFile(PATH_DEST_VALID, FunctionalConstants.DEST_VALID, null);
			
			// Import destinations of 2014
			destinationsVisited.readFile(PATH_DEST_2014, FunctionalConstants.DEST_VISITED, FunctionalConstants.YEAR_2014);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		// Write report
		writeReport();
	}
	
	private void writeReport(){
		StringBuilder report = new StringBuilder();
		
		// Get output destination sorted by year
		report.append(getDestSortByYear());
		System.out.println(report);
	}
	
	private String getCostBounds(){
		return "haha";
	}
	
	private String getDriverDestinations(){
		return "hoho";
	}
	
	/**
	 * Get destination sorted by year
	 * @return string containing list of destinations sorted
	 */
	private String getDestSortByYear(){
		// Create variables
		StringBuffer sb = new StringBuffer();
		
		// Get destination list sorted
		HashMap<String,Set<Destination>> destRep = destinationsVisited.getYearRepartition(FunctionalConstants.YEAR_2014, FunctionalConstants.YEAR_2015);		
		Set<Destination> onlyOld = destRep.get(FunctionalConstants.YEAR_2014);
		Set<Destination> onlyNew = destRep.get(FunctionalConstants.YEAR_2015);
		Set<Destination> shared = destRep.get(FunctionalConstants.YEAR_BOTH);
		
		// Only new year
		sb.append(onlyNew.size() + DEST_NEW_PLACES + FunctionalConstants.NEW_LINE);
		for(Destination dest : onlyNew){
			sb.append(dest.getName() + FunctionalConstants.NEW_LINE);
		}
		
		// Only first year
		sb.append(onlyOld.size() + DEST_OLD_PLACES + FunctionalConstants.NEW_LINE);
		for(Destination dest : onlyOld){
			sb.append(dest.getName() + FunctionalConstants.NEW_LINE);
		}
		
		// Both year
		sb.append(shared.size() + DEST_BOTH_PLACES + FunctionalConstants.NEW_LINE);
		for(Destination dest : shared){
			sb.append(dest.getName() + FunctionalConstants.NEW_LINE);
		}
		
		return sb.toString();
	}
	
}

