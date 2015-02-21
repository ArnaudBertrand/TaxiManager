package Class;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class Manager {
	/**
	 * Initialize constants
	 */
	public static final String PATH_READ_TAXI_DETAILS = "TaxiDetails.txt";
	public static final String PATH_READ_DEST_VALID = "ValidDestinations.txt";
	public static final String PATH_READ_DEST_2014 = "VisitedDestination.txt";
	public static final String PATH_READ_DEST_JOURNEY = "JourneyDetails.txt";
	
	public static final String PATH_WRITE_COST_BOUNDS = "CostBoundsReport.txt";
	public static final String PATH_WRITE_DRIVER_DEST= "DriverDestinationsReport.txt";
	public static final String PATH_WRITE_DEST_YEAR_REP = "DestYearRepReport.txt";
	
	public static final String DEST_NEW_PLACES = " NEW PLACES IN 2015";
	public static final String DEST_OLD_PLACES =  " PLACES VISITED IN 2014 ONLY";
	public static final String DEST_BOTH_PLACES = " PLACES VISITED IN BOTH 2014 AND 2015";
	
	/** List of taxis **/
	private TaxiList taxiList;
	/** List of journeys **/
	private JourneyList journeyList;
	/** List of destinations visited **/
	private DestinationsVisited destinationsVisited;
	/** List of valid destinations **/
	private DestinationList validDestinations;
	
	//constructor
	public Manager(){
		taxiList = new TaxiList(this);
		journeyList = new JourneyList(this);
		destinationsVisited = new DestinationsVisited(this);
		validDestinations = new DestinationList();
	}
	
	public void run(){
		try {
			// Import taxi details
			taxiList.readFile(PATH_READ_TAXI_DETAILS);
			
			// Import valid destinations
			destinationsVisited.readFile(PATH_READ_DEST_VALID, FunctionalConstants.DEST_VALID, null);

			// Import JourneyList
			journeyList.readFile(PATH_READ_DEST_JOURNEY);
			
			
			// Import destinations of 2014
			destinationsVisited.readFile(PATH_READ_DEST_2014, FunctionalConstants.DEST_VISITED, FunctionalConstants.YEAR_2014);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		// Write report
		writeReport();
	}
	

	private void writeReport(){
		writeToFile(PATH_WRITE_COST_BOUNDS,getCostBounds());
		writeToFile(PATH_WRITE_DRIVER_DEST,getDriverDestinations());
		writeToFile(PATH_WRITE_DEST_YEAR_REP,getDestSortByYear());
		System.out.println(getDestSortByYear());
	}	

	private String getCostBounds(){
		// Get all the journeys
		return journeyList.getAllJourneys();
	}

	/**
	 * Get destination sorted by driver
	 * @return string containing list of destinations sorted
	 */
	private String getDriverDestinations(){
		String report ="";
		//report += taxiList.getReadingErrors();

		// Generate the DriverDestinations report
		report += "\n" + "Text file containing details of which places each driver"
				+ " has visited : \n\n";
		
		//report += "Number of taxis in the list : ";
		//report += taxiList.getTaxiNb() + "\n";
		//report += "Driver name for the taxi MAN-24PM24 : ";
		//report += taxiList.getDriverNameByRegNb("MAN-24PM24") + "\n\n";
		//report += taxiList.getAllDriverName() + "\n";
		report += taxiList.getDriverNameAndDest();
		
		// Print report in console and send it into a file
		//System.out.print(report);
		//taxiList.writeToFile("DriverDestinationsReport.txt", report);
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

	public DestinationList getValidDestinations() {
		return validDestinations;
	}

	public void setValidDestinations(DestinationList validDestinations) {
		this.validDestinations = validDestinations;
	}
	
	public JourneyList getJourneyList() {
		return journeyList;
	}

	public void setJourneyList(JourneyList journeyList) {
		this.journeyList = journeyList;
	}
	
	public DestinationsVisited getDestinationsVisited() {
		return destinationsVisited;
	}

	public void setDestinationsVisited(DestinationsVisited destinationsVisited) {
		this.destinationsVisited = destinationsVisited;
	}
	
	/**
	 * Write a report into a file
	 * @param filename path of the file to write in
	 * @param report report to write in the file
	 */
	public void writeToFile(String filename, String report) {
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
			 System.exit(0);
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}
}

