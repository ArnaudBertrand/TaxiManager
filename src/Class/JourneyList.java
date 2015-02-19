package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Class regrouping journey into a taxi list
 * @author pg5
 * @version 19/02/2015
 * */
public class JourneyList {

	// List of journey
	private ArrayList<Journey> journeyList = new ArrayList<Journey>();
	// Reading errors
	String readingErrors;
	
	//constructor
	public JourneyList(){
			this.journeyList = new ArrayList<Journey>();
			this.readingErrors = "";
	}
	
	/**
	 * Get the journey list
	 * @return journey list
	 */
	public ArrayList<Journey> getJourneyList(){
		return this.journeyList;
	}
	
	/**
	 * Set the journey list
	 * */
	public void setJourneyList(ArrayList<Journey> journeyList){
		this.journeyList = journeyList;
	}
	
	/**
	 * Read a file
	 * @param fileName path of the file to read
	 */
	public void readFile(String fileName) throws FileNotFoundException{
		File f = new File(fileName);
		Scanner scanner = new Scanner(f);
		// Process each line
		while (scanner.hasNextLine()) {
			//read first line and process it
			String inputLine = scanner.nextLine(); 
			if (inputLine.length() != 0) {//ignored if blank line
				processLine(inputLine);	
			}
		}
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
	
	/**
	 * Get errors of the reading process
	 * @return list of errors that happened during reading process
	 */
	public String getReadingErrors(){
		String readFileErrors = "";
		if(this.readingErrors != null){
			readFileErrors = ">> Errors detected during reading process\n";
			readFileErrors += this.readingErrors;
		}
		return readFileErrors;
	}
	
	/**
	 * Processes line, extracts data, creates journey object
	 * and adds to list.
	 * @param line the line to be processed
	 */
	private void processLine(String line){
		
		String [] parts = line.split(",");
		String regNb = parts[0].trim();
		String dest = parts[1].trim();
		int nbPerson = Integer.parseInt(parts[2].trim());
		
		//Get the taxi object corresponding to the regNb
		Taxi taxi = new Taxi("", regNb);
		
		//Get the destination object corresponding to the destination
		Destination destination =new Destination(dest,-1);
		
		//create Journey object and add it to the list
		Journey j = new Journey(taxi, destination, nbPerson);
		this.addJourney(j);
	}
	
	/**
	 * Add a new journey
	 * @param j journey to add
	 * @return 1 success - 0 fail
	 */
	public boolean addJourney(Journey j){
		boolean isNotExisting = false;
		if (journeyList.add(j)){
			isNotExisting = true;
		}
		return isNotExisting;
	}
	
	/**
	 * Get all journeys
	 * @return string of journeys
	 */
	public String getAllJourneys(){
		
		String allJourneys = "";		
		// Go through the JourneyList
		Iterator<Journey> j = journeyList.iterator();
		while(j.hasNext())
		{
			//For each journey
		    Journey currentJourney = j.next();
			// Get the wanted fields
		    String TaxiNB =   currentJourney.getTaxi().getRegNb();
			String Destination = currentJourney.getDestination().getName();
			String Distance = currentJourney.getDestination().getDistance() + " miles ";
			//Be careful to people/person
			String NbPeople = "";
			if (currentJourney.getNbPerson() == 1){
			NbPeople = currentJourney.getNbPerson() + " person";
			}
			else {
			NbPeople = currentJourney.getNbPerson() + " people";	
			}
			String Cost = "Cost £" + currentJourney.getJourneyFee() ;
			// Add the fields and set the format
			allJourneys += String.format("%-15s%-25s%-15s%-12s%-15s\n", TaxiNB, Destination, Distance, NbPeople, Cost);
		}
	
		return allJourneys;
	}
	
}
