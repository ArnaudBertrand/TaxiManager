package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Class regrouping taxis into a list
 * @author pgm1
 * @version 17/02/2015
 */
public class TaxiList {

	/** List of taxis **/
	private Set<Taxi> taxiList = new HashSet<Taxi>();
	/** Reading errors **/
	String readingErrors;
	
	/**
	 * Constructor of taxi list
	 */
	public TaxiList(){	
		this.taxiList = new HashSet<Taxi>();
		this.readingErrors = "";
	}
	
	/**
	 * Get the taxi list
	 * @return the taxi list
	 */
	public Set<Taxi> getTaxiList(){
		return this.taxiList;
	}
	
	/**
	 * Set the taxi list
	 */
	public void setTaxiList(Set<Taxi> taxiList){
		this.taxiList = taxiList;
	}
	
	/**
	 * Get the number of taxis in the list
	 * @return number of taxis
	 */
	public int getTaxiNb() {
		return this.taxiList.size();
	}
	
	/**
	 * Get the taxi from the registration number
	 * @param regNb the registration number of the taxi to get
	 * @return the taxi
	 */
	public Taxi getTaxiByRegNb(String regNb){
		
		Taxi taxi = null;
		/*Iterator<Taxi> taxiIterator = taxiList.iterator();
		while(taxiIterator.hasNext()){
			Taxi currentTaxi = taxiIterator.next();
			if(currentTaxi.getRegNb() != null && regNb.equals(currentTaxi.getRegNb())){
				taxi = currentTaxi;
				break;
			}
		}
		*/
		for (Taxi t : taxiList) {
			if(regNb.equals(t.getRegNb())){
				taxi = t;
				break;
			}
		}
		return taxi;
	}
	
	/**
	 * Get the driver name from the registration number of a taxi
	 * @param regNb the registration number of the taxi
	 * @return the driver name
	 */
	public String getDriverNameByRegNb(String regNb){
		
		String dv = "";
		for (Taxi t : taxiList) {
			if(regNb.equals(t.getRegNb())){
				dv = t.getDriverName();
				break;
			}
		}
		return dv;
	}
	
	/**
	 * Add a new taxi
	 * @param t Taxi to add
	 * @return 1 success - 0 fail
	 */
	public boolean addTaxi(Taxi t) {
		
		boolean isNotExisting = false;
		if (taxiList.add(t)) {
			isNotExisting = true;
		}
		return isNotExisting;
	}
	
	/**
	 * Processes line, extracts data, creates taxi object
	 * and adds to list.
	 * @param line the line to be processed
	 */
	private void processLine(String line){

		String parts [] = line.split(",");
		String driverName = parts[0];
		String regNb = parts[1];

		//create Taxi object and add to the list
		Taxi t = new Taxi(driverName, regNb);
		this.addTaxi(t);

	}
	
	/**
	 * Read a file
	 * @param fileName path of the file to read
	 */
	public void readFile(String fileName){
		try {
			File f = new File(fileName);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				//read first line and process it
				String inputLine = scanner.nextLine(); 
				if (inputLine.length() != 0) {//ignored if blank line
					processLine(inputLine);		
				}	
			}
		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf){
			 System.out.println( fileName + " not found ");
			 System.exit(0);
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
	 * Remove a taxi from the list
	 * @param regNb the registration number of the taxi to remove
	 * @return 1 success - 0 fail
	 */
	public boolean removeTaxi(String regNb){
		boolean success = false;
		Taxi taxiToRem = this.getTaxiByRegNb(regNb);
		if(taxiToRem != null){
			 success = taxiList.remove(taxiToRem);
		}
		return success;
	}
	
	
}
