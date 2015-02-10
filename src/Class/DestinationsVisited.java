package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DestinationsVisited {

	private HashMap<String, DestinationList> destinationsVisited;
	private DestinationList validDestinations;
	
	//constructor
	public DestinationsVisited(){
		// Instanciate variables
		destinationsVisited = new HashMap<String, DestinationList>();
		validDestinations = new DestinationList();
		
		// Add year 2014 and 2015
		destinationsVisited.put("2014", new DestinationList());
		destinationsVisited.put("2015", new DestinationList());
	}
	
	public HashMap<String, DestinationList> getDestinationsVisited(){
		return destinationsVisited;
	}
	
	public void setDestinationsVisited(HashMap<String, DestinationList> destVisited){
		this.destinationsVisited = destVisited;
	}
	
	/**
	 * Read a file
	 * @param fileName path of the file to read
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings("resource")
	public void readFile(String fileName, String type, String year) throws FileNotFoundException{
		File f = new File(fileName);
		Scanner scanner = new Scanner(f);
		// Process each line
		while (scanner.hasNextLine()) {
			if((FunctionalConstants.DEST_VALID).equals(type)){
				processLineDestValid(scanner.nextLine());
			}
			if(FunctionalConstants.DEST_VISITED.equals(type)){
				processLineDestVisited(scanner.nextLine(), this.destinationsVisited.get(year));				
			}
		}
	}
	
	private void processLineDestValid(String inputLine){
		String [] parts = inputLine.split(",");
		Destination dest = new Destination(parts[1],Double.parseDouble(parts[2]));
		validDestinations.addDestination(dest);
	}
	
	private void processLineDestVisited(String destName, DestinationList destinationList){
		Destination dest = validDestinations.getDest(destName);
		if(dest != null){
			destinationList.addDestination(dest);			
		}
	}

	public DestinationList getFromYear(String year) {
		return destinationsVisited.get(year);
	}
	
	public HashMap<String, Set<Destination>> getYearRepartition(String year1, String year2) {
		Set<Destination> dl1 = destinationsVisited.get(year1).getDestinationList();
		Set<Destination> dl2 = destinationsVisited.get(year2).getDestinationList();
		
		// Create result map
		HashMap<String,Set<Destination>> result = new HashMap<String,Set<Destination>>();
		Set<Destination> year1Only = new HashSet<Destination>();
		Set<Destination> year2Only = new HashSet<Destination>();
		Set<Destination> shared = new HashSet<Destination>();
		
		for(Destination dest : dl1){
			if(dl2.contains(dest)){
				// Handle shared
				shared.add(dest);
			} else {
				// Handle year 1 only
				year1Only.add(dest);
			}
		}
		
		// Handle year 2 only
		for(Destination dest : dl2){
			if(shared.contains(dest)){
				year2Only.add(dest);
			}
		}
		
		result.put(year1, year1Only);
		result.put(year2, year2Only);
		result.put(FunctionalConstants.YEAR_BOTH, shared);
		return result;
	}

}
