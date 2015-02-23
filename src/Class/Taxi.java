package Class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class representing the object Taxi
 * @author pgm1
 * @version 22/02/2015
 */
public class Taxi implements Comparable<Taxi> {
	
	/** Registration number of the taxi **/
	private String regNb;
	/** Driver name **/
	private String driverName;
	/** Number of seat in the taxi **/
	private int nbOfSeats;
	/** Regex **/
	private static final String REGEX_REG_NB = "[A-Z]{3}-[A-Z0-9]{6}";
	/** Errors **/
	private static final String ERROR_NULL_ARGUMENT = "Driver name and reg number should not be null";
	
	/**
	 * Constructor
	 * @param driverName name of the driver of the taxi
	 * @param regNb registration number or the taxi
	 * @throws RegNbFormatException 
	 */
	public Taxi(String driverName, String regNb) throws RegNbFormatException{	
		if(driverName == null || regNb == null){
			throw new NullPointerException(ERROR_NULL_ARGUMENT);
		}
		Pattern regex = Pattern.compile(REGEX_REG_NB);
		Matcher match = regex.matcher(regNb);
		if(regNb.length() != 10 || !match.find()){
			throw new RegNbFormatException(regNb);
		}
		this.driverName = driverName;
		this.regNb = regNb;			
		
		//Set default value for nbOfSeats can be handled later in the input file
		nbOfSeats = 6;
	}
	
	/**
	 * Get the registration number of the taxi
	 * @return registration number of the taxi
	 */
	public String getRegNb(){
		return this.regNb;
	}
	
	/**
	 * Set the registration number of the taxi
	 * @param regNb registration number of the taxi to set
	 */
	public void setRegNb(String regNb){
		this.regNb = regNb;
	}
	
	/**
	 * Get the name of the driver
	 * @return name of the driver
	 */
	public String getDriverName(){
		return this.driverName;
	}
	
	/**
	 * Set the name of the driver
	 * @param driverName name of the driver to set
	 */
	public void setDriverName(String driverName){
		this.driverName = driverName;
	} 

	/**
	 * Get the number of seat in the taxi
	 * @return number of seat in the taxi
	 */
	public int getNbOfSeats(){
		return this.nbOfSeats;
	}
	
	/**
	 * Set the number of seat in the taxi
	 * @param nbOfSeats number of seat in the taxi to set
	 */
	public void setNbOfSeats(int nbOfSeats){
		this.nbOfSeats = nbOfSeats;
	}
	
	/**
	 * Equals method
	 */
	public boolean equals(Object other){
		boolean eq = false;
		if(other != null && other instanceof Taxi){
		    Taxi t = (Taxi) other;
			eq = this.getRegNb().equals(t.getRegNb());
		}
		return eq;
	}
	
	/**
	 * CompareTo method
	 */
	public int compareTo(Taxi other) {
		return driverName.compareTo(other.driverName);
	}
}
