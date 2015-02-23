package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class regrouping taxis into a list
 * @author pgm1
 * @version 22/02/2015
 */
public class TaxiList {
	/** List of taxis **/
	private TreeSet<Taxi> taxiList = new TreeSet<Taxi>();
	/** Manager **/
	private Manager manager;
	/** Errors **/
	private static final String ERROR_READING = "Error during reading process: ";
	private static final String ERROR_NB_ARGUMENTS = "Input line should be 'driver name,registration number'";
	
	/**
	 * Constructor
	 * @param manager Manager
	 */
	public TaxiList(Manager manager){	
		this.taxiList = new TreeSet<Taxi>();
		this.manager = manager;
	}
	
	/**
	 * Get the taxi list
	 * @return the taxi list
	 */
	public TreeSet<Taxi> getTaxiList(){
		return this.taxiList;
	}
	
	/**
	 * Set the taxi list
	 * @param taxiList the taxi list to set
	 */
	public void setTaxiList(TreeSet<Taxi> taxiList){
		this.taxiList = taxiList;
	}
	
	/**
	 * Get the number of taxis in the list
	 * @return number of taxis
	 */
	public int getNbTaxi() {
		return this.taxiList.size();
	}
	
	/**
	 * Get the taxi from his registration number
	 * @param regNb the registration number of the taxi to get
	 * @return the taxi with the registration number corresponding
	 */
	public Taxi getTaxiByRegNb(String regNb){
		
		Taxi taxi = null;
		Iterator<Taxi> taxiIterator = taxiList.iterator();
		while(taxiIterator.hasNext()){
			Taxi currentTaxi = taxiIterator.next();
			if(regNb.equals(currentTaxi.getRegNb())){
				taxi = currentTaxi;
				break;
			}
		}
		return taxi;
	}
	
	/**
	 * Get the destinations for each driver name of the list of taxis
	 * @return all the destinations by driver name
	 */
	public String getDriverNameAndDest(){
		
		// instance a String to return at the end
		String driverNameDest = "";
		// for each taxi of the list
		Iterator<Taxi> i = taxiList.iterator();
		while (i.hasNext()) {
			Taxi t = i.next();
			// we get the driver name
			driverNameDest += t.getDriverName() + "\n";
			// we get all destinations for this taxi
			Set<String> journeys = manager.getJourneyList().getDestinationsForTaxi(t);
			// and we add each destination of this taxi in the String
			Iterator<String> i2 = journeys.iterator();
			while(i2.hasNext())
			{
				driverNameDest += "   " + i2.next() + "\n";	
			}
			driverNameDest += "\n";
		}
		//return the String of all destinations for each taxi
		return driverNameDest;
	}
	
	/**
	 * Add a new taxi
	 * @param t Taxi to add
	 * @return 1 success - 0 fail
	 */
	public boolean addTaxi(Taxi t) {
		return taxiList.add(t);	
	}
	
	/**
	 * Processes line, extracts data, creates taxi object
	 * and adds to list.
	 * @param line the line to be processed
	 */
	private void processLine(String line){

		String [] parts = line.split(",");
		if(parts.length == 2){
			String driverName = parts[0];
			String regNb = parts[1].trim();
	
			try {
				//create Taxi object and add to the list
				Taxi t = new Taxi(driverName, regNb);
				this.addTaxi(t); 
			} catch (RegNbFormatException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println(ERROR_READING + ERROR_NB_ARGUMENTS);
		}
	}
	
	/**
	 * Read a file
	 * @param fileName path of the file to read
	 */
	@SuppressWarnings("resource")
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
