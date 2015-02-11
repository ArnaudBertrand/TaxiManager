package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TaxiList {

	private Set<Taxi> taxiList = new HashSet<Taxi>();
	
	//constructor
	public TaxiList(){				
	}
	
	public Set<Taxi> getTaxiList(){
		return this.taxiList;
	}
	
	public void setTaxiList(Set<Taxi> taxiList){
		this.taxiList = taxiList;
	}
	
	public Taxi getTaxiByRegNb(String regNb){
		
		Taxi taxi = null;
		Iterator<Taxi> taxiIterator = taxiList.iterator();
		while(taxiIterator.hasNext()){
			Taxi currentTaxi = taxiIterator.next();
			if(currentTaxi.getRegNb() != null && currentTaxi.getRegNb() == regNb){
				taxi = currentTaxi;
				break;
			}
		}
		return taxi;
	}
	
	public boolean addTaxi(Taxi t) {
		
		boolean isNotExisting = false;
		String regNb = t.getRegNb();
		Taxi inList = getTaxiByRegNb(regNb); 
		if (inList == null) {
			taxiList.add(t);
			isNotExisting = true;
		}
		return isNotExisting;
	}
	
	private void processLine(String line){

		String parts [] = line.split(",");
		String driverName = parts[0];
		String regNb = parts[1];

		//create Taxi object and add to the list
		Taxi t = new Taxi(driverName, regNb);
		this.addTaxi(t);

	}
	
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
	
	
}
