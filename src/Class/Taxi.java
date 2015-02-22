package Class;

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
	
	/** Basic constructor **/
	public Taxi(){
	}
	
	/**
	 * Constructor
	 * @param driverName name of the driver of the taxi
	 * @param regNb registration number or the taxi
	 */
	public Taxi(String driverName, String regNb){	
		this.driverName = driverName;
		this.regNb = regNb;
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
	public boolean equals(Taxi other){
		return (other.getRegNb().equals(this.getRegNb()));
	} 
	
	/**
	 * CompareTo method
	 */
	public int compareTo(Taxi other) {
		return driverName.compareTo(other.driverName);
	}
}
