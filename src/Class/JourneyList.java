package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class regrouping journeys into an ArrayList
 * 
 * @author pg5
 * @version 22/02/2015
 * */
public class JourneyList {
	
	/** Instanciate variables **/
	// Journey List
	private ArrayList<Journey> journeyList = new ArrayList<Journey>();
	// Manager class
	private Manager manager;
	
	/** Errors **/
	private static final String ERROR_READING = "Error during reading process: ";
	private static final String ERROR_NB_ARGUMENTS = "Input line should be 'reg nb,destination,nb person'";
	
	// Constructors
	public JourneyList(Manager manager) {
		this.journeyList = new ArrayList<Journey>();
		this.manager = manager;
	}

	/**
	 * Get the journey list
	 * 
	 * @return journey list
	 */
	public ArrayList<Journey> getJourneyList() {
		return this.journeyList;
	}

	/**
	 * Set the journey list
	 * 
	 * @param the
	 *            journey list
	 * */
	public void setJourneyList(ArrayList<Journey> journeyList) {
		this.journeyList = journeyList;
	}

	/**
	 * Read a file
	 * 
	 * @param fileName
	 *            path of the file to read
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public void readFile(String fileName) throws FileNotFoundException {
		File f = new File(fileName);
		Scanner scanner = new Scanner(f);
		// Process each line
		while (scanner.hasNextLine()) {
			// read first line and process it
			String inputLine = scanner.nextLine();
			if (inputLine.length() != 0) {// ignored if blank line
				processLine(inputLine);
			}
		}
	}

	/**
	 * Processes line, extracts data, creates journey object and adds it to
	 * list.
	 * 
	 * @param line
	 *            the line to be processed
	 */
	private void processLine(String line) {

		String[] parts = line.split(",");
		if(parts.length == 3){
			String regNb = parts[0].trim();
			String dest = parts[1].trim();
			
			try{
				int nbPerson = Integer.parseInt(parts[2].trim());

				// Create a new object taxi with the correct regNb
				Taxi taxi = manager.getTaxiList().getTaxiByRegNb(regNb);

				// Get the destination object corresponding to the destination
				Destination destination = manager.getValidDestinations().getDest(dest);

				// create a Journey object and add it to the list
				this.addJourney(new Journey(taxi, destination, nbPerson));
				manager.getDestinationsVisited().addDestForYear(destination,
						FunctionalConstants.YEAR_2015);			
			} catch(NumberFormatException e){
				System.out.println(ERROR_READING + e.getMessage());
			} catch(IllegalArgumentException e){
				System.out.println(ERROR_READING + e.getMessage());
			} catch(NullPointerException e){
				System.out.println(ERROR_READING + e.getMessage());
			}			
		} else {
			System.out.println(ERROR_READING + ERROR_NB_ARGUMENTS);
		}
	}

	/**
	 * Add a new journey
	 * 
	 * @param j
	 *            journey to add
	 */
	public boolean addJourney(Journey j) {
		return journeyList.add(j);
	}

	/**
	 * Get the top 5 journeys and cheapest 5 journeys
	 * 
	 * @return string of journeys
	 */
	public String getAllJourneys() {
		// Sort the JourneyList by cost
		Collections.sort(journeyList, new JourneyComparator());
		Iterator<Journey> j = journeyList.iterator();
		// String builder for the 5 most expensive journeys
		StringBuilder sb1 = new StringBuilder(
				"CHARGES FOR THE TOP 5 JOURNEYS \n");
		// String builder for the 5 cheapest journeys
		StringBuilder sb2 = new StringBuilder(
				"CHARGES FOR THE CHEAPEST 5 JOURNEYS\n");
		int counter = 0;
		int min = journeyList.size() < 5 ? 0 : (journeyList.size() - 5);
		// Go through the journeyList
		while (j.hasNext()) {
			String allJourneys = "";
			// For each journey
			Journey currentJourney = j.next();
			// Get the wanted fields
			// Taxi regNb
			allJourneys += String.format("%-12s", currentJourney.getTaxi()
					.getRegNb());

			// Destination name
			allJourneys += String.format("%-25s", currentJourney
					.getDestination().getName());

			// Destination distance
			allJourneys += String.format("%7.1f", currentJourney
					.getDestination().getDistance());
			allJourneys += String.format("%-4s", "km");

			// Number of people
			String nbPeople = "";
			if (currentJourney.getNbPerson() == 1) {
				nbPeople = currentJourney.getNbPerson() + " person";
			} else {
				nbPeople = currentJourney.getNbPerson() + " people";
			}
			allJourneys += String.format("%10s", nbPeople);

			// Cost
			allJourneys += String.format("%11s", "Cost ") +FunctionalConstants.POUNDS;
			allJourneys += String.format("%6.2f",
					currentJourney.getJourneyFee());
			allJourneys += '\n';

			// Get the top 5
			if (counter < 5) {
				sb1.append(allJourneys);
			}
			// Get the 5 cheapest
			if (counter >= min) {
				sb2.append(allJourneys);
			}
			counter++;
		}

		sb1.append("\n");

		return sb1.toString() + sb2.toString();
	}

	/**
	 * Get all journeys for a specific taxi
	 * 
	 * @return string of journeys
	 */
	public ArrayList<String> getDestinationsForTaxi(Taxi t) {

		ArrayList<String> journeys = new ArrayList<String>();
		String regNb = t.getRegNb();
		// Go through the JourneyList
		Iterator<Journey> j = journeyList.iterator();
		while (j.hasNext()) {
			// For each journey
			Journey currentJourney = j.next();
			String taxiNb = currentJourney.getTaxi().getRegNb();
			if (taxiNb.equals(regNb)) {
				String destination = currentJourney.getDestination().getName();
				journeys.add(destination);
			}
		}
		return journeys;
	}

}
