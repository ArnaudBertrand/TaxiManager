package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DestinationsVisited {

	/** Destinations visited **/
	private HashMap<String, DestinationList> destinationsVisited;
	/** Manager **/
	private Manager manager;
	
	/** Empty constructor **/
	public DestinationsVisited (){
		destinationsVisited = new HashMap<String, DestinationList>();
	}
	
	/** Constructor **/
	public DestinationsVisited(Manager manager){
		// Instanciate variables
		destinationsVisited = new HashMap<String, DestinationList>();
		
		// Set valid destinations
		this.manager = manager;		
	}
		
	/**
	 * Read a file
	 * @param fileName path of the file to read
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("resource")
	public void readFile(String fileName, String year) throws FileNotFoundException{
		File f = new File(fileName);
		Scanner scanner = new Scanner(f);
		// Process each line
		while (scanner.hasNextLine()) {
			processLineDestVisited(scanner.nextLine());
		}
	}
	
	/**
	 * Process line of visited destinations for a year
	 * @param destName name of the destination
	 * @param destinationList the destination list to fill
	 */
	private void processLineDestVisited(String destName){
		Destination dest = manager.getValidDestinations().getDest(destName);
		if(dest != null){
			addDestForYear(dest,FunctionalConstants.YEAR_2014);			
		} else {
			System.out.println("Error during reading proces, destination \"" + destName + "\" not existing");
		}
	}
	
	/**
	 * Get the repartition of destinations visited by year
	 * @param year1 first year to check
	 * @param year2 second year to check
	 * @return map containing set of destination for 1st only, 2nd only or shared.
	 */
	public HashMap<String, DestinationList> getYearRepartition(String year1, String year2) {
		// Create result map
		HashMap<String,DestinationList> result = new HashMap<String,DestinationList>();
		DestinationList year1Only = new DestinationList();
		DestinationList year2Only = new DestinationList();
		DestinationList shared = new DestinationList();
		
		// Handle null values
		DestinationList dl1 = new DestinationList();
		DestinationList dl2 = new DestinationList();
		if(destinationsVisited.get(year1) != null ){
			dl1 = destinationsVisited.get(year1);
		}
		if(destinationsVisited.get(year2) != null){
			dl2 = destinationsVisited.get(year2);
		}
		
		shared = dl1.getSameDestinations(dl2);
		year1Only = dl1.getDifferentDestinations(shared);
		year2Only = dl2.getDifferentDestinations(shared);
		
		// Set the result map
		result.put(year1, year1Only);
		result.put(year2, year2Only);
		result.put(FunctionalConstants.YEAR_BOTH, shared);
		
		return result;			
	}
	
	/**
	 * Add destination for a specific year
	 * @param dest destination to add
	 * @param year year where you want to add the destination
	 * @return true if insert false if it already exist
	 */
	public boolean addDestForYear(Destination dest, String year){
		DestinationList dstList = destinationsVisited.get(year);
		// Handle year not existing
		if(dstList == null){
			dstList = new DestinationList();
			destinationsVisited.put(year, dstList);
		}
		return dstList.addDestination(dest);
	}
}
