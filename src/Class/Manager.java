package Class;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Manager {
	/**
	 * Initialize constants
	 */
	// Path for input files
	private static final String PATH_READ_TAXI_DETAILS = "TaxiDetails.txt";
	private static final String PATH_READ_DEST_VALID = "ValidDestinations.txt";
	private static final String PATH_READ_DEST_2014 = "VisitedDestination.txt";
	private static final String PATH_READ_DEST_JOURNEY = "JourneyDetails.txt";
	
	// Path for output files
	private static final String PATH_WRITE_COST_BOUNDS = "CostBoundsReport.txt";
	private static final String PATH_WRITE_DRIVER_DEST= "DriverDestinationsReport.txt";
	private static final String PATH_WRITE_DEST_YEAR_REP = "DestYearRepReport.txt";
	
	// Strings constant
	private static final String DEST_NEW_PLACES = " NEW PLACES IN 2015";
	private static final String DEST_OLD_PLACES =  " PLACES VISITED IN 2014 ONLY";
	private static final String DEST_BOTH_PLACES = " PLACES VISITED IN BOTH 2014 AND 2015";
	private static final String HEADER_DRIVER_DEST = "Text file containing details of which places each driver has visited: \n";
	
	/** List of taxis **/
	private TaxiList taxiList;
	/** List of journeys **/
	private JourneyList journeyList;
	/** List of destinations visited **/
	private DestinationsVisited destinationsVisited;
	/** List of valid destinations **/
	private DestinationList validDestinations;
	
	/**
	 * Constructor
	 */
	public Manager(){
		taxiList = new TaxiList(this);
		journeyList = new JourneyList(this);
		destinationsVisited = new DestinationsVisited(this);
		validDestinations = new DestinationList();
	}
	
	/**
	 * Run the manager
	 */
	public void run(){
		try {
			// Import taxi details
			taxiList.readFile(PATH_READ_TAXI_DETAILS);
			
			// Import valid destinations
			validDestinations.readFile(PATH_READ_DEST_VALID);

			// Import JourneyList
			journeyList.readFile(PATH_READ_DEST_JOURNEY);
			
			// Import destinations of 2014
			destinationsVisited.readFile(PATH_READ_DEST_2014, FunctionalConstants.YEAR_2014);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		// Write report
		writeReport();
	}
	
	/**
	 * Write reports
	 */
	private void writeReport(){
		writeToFile(PATH_WRITE_COST_BOUNDS,getCostBounds());
		writeToFile(PATH_WRITE_DRIVER_DEST,getDriverDestinations());
		writeToFile(PATH_WRITE_DEST_YEAR_REP,getDestSortByYear());
	}	
	
	/**
	 * Get cost bounds
	 * @return cost bounds
	 */
	private String getCostBounds(){
		// Get all the journeys
		return journeyList.getAllJourneys();
	}

	/**
	 * Get destination sorted by driver
	 * @return string containing list of destinations sorted
	 */
	private String getDriverDestinations(){
		// Generate the DriverDestinations report
		String report = HEADER_DRIVER_DEST + FunctionalConstants.NEW_LINE;
		report += taxiList.getDriverNameAndDest();
		
		// Return the report
		return report;
	}
	
	/**
	 * Get destination sorted by year
	 * @return string containing list of destinations sorted
	 */
	private String getDestSortByYear(){
		// Create variables
		StringBuffer sb = new StringBuffer();
		
		// Get destination list sorted
		HashMap<String,DestinationList> destRep = destinationsVisited.getYearRepartition(FunctionalConstants.YEAR_2014, FunctionalConstants.YEAR_2015);		
		DestinationList onlyOld = destRep.get(FunctionalConstants.YEAR_2014);
		DestinationList onlyNew = destRep.get(FunctionalConstants.YEAR_2015);
		DestinationList shared = destRep.get(FunctionalConstants.YEAR_BOTH);
		
		// Only new year
		sb.append(onlyNew.getSize() + DEST_NEW_PLACES + FunctionalConstants.NEW_LINE);
		sb.append(onlyNew.getNameList() + FunctionalConstants.NEW_LINE);
		
		// Only first year
		sb.append(onlyOld.getSize() + DEST_OLD_PLACES + FunctionalConstants.NEW_LINE);
		sb.append(onlyOld.getNameList() + FunctionalConstants.NEW_LINE);
		
		// Both year
		sb.append(shared.getSize() + DEST_BOTH_PLACES + FunctionalConstants.NEW_LINE);
		sb.append(shared.getNameList());
		
		return sb.toString();
	}
	
	/**
	 * Get valid destinations
	 * @return valid destinations
	 */
	public DestinationList getValidDestinations() {
		return validDestinations;
	}

	/**
	 * Set valid destionations
	 * @param validDestinations valid destinations to set
	 */
	public void setValidDestinations(DestinationList validDestinations) {
		this.validDestinations = validDestinations;
	}
	
	/**
	 * Get taxi list
	 * @return taxi list to get
	 */
	public TaxiList getTaxiList() {
		return taxiList;
	}

	/**
	 * Set journey list
	 * @param journeyList journey list to set
	 */
	public void setTaxiList(TaxiList taxiList) {
		this.taxiList = taxiList;
	}
	
	/**
	 * Get journey list
	 * @return journey list to get
	 */
	public JourneyList getJourneyList() {
		return journeyList;
	}

	/**
	 * Set journey list
	 * @param journeyList journey list to set
	 */
	public void setJourneyList(JourneyList journeyList) {
		this.journeyList = journeyList;
	}
	
	/** 
	 * Get destinations visited
	 * @return visited destinations
	 */
	public DestinationsVisited getDestinationsVisited() {
		return destinationsVisited;
	}

	/**
	 * Set destinations visited
	 * @param destinationsVisited destinations visited to set
	 */
	public void setDestinationsVisited(DestinationsVisited destinationsVisited) {
		this.destinationsVisited = destinationsVisited;
	}
	
	/**
	 * Write a report into a file
	 * @param filename path of the file to write in
	 * @param report report to write in the file
	 */
	private void writeToFile(String filename, String report) {
		FileWriter fw;
		try {
		    fw = new FileWriter(filename);
		    fw.write("##### Report #####\n");
		    fw.write(report);
		 	fw.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filename + " not found ");
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}	
}

