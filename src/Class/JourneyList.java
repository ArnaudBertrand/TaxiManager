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
		String dest = parts[1];
		int nbPerson = Integer.parseInt(parts[2].trim());
		
		//Get the taxi object corresponding to the regNb
		TaxiList tl = new TaxiList();	
		Taxi taxi = tl.getTaxiByRegNb(regNb);
		
		//Get the destination object corresponding to the destination
		DestinationList dl = new DestinationList();
		Destination destination = dl.getDest(dest);
		
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
}
