package Class;

public class Destination {
	/** Name of the destination **/
	private String name;
	/** Distance **/
	private double distance;
	
	/** Basic constructor **/
	public Destination(){
	}
	
	/**
	 * Constructor
	 * @param name name of the destination
	 * @param distance distance from base
	 */
	public Destination(String name, double distance){
		this.name = name;
		this.distance = distance;
	}
	
	/**
	 * Get the name of the destination
	 * @return name of the destination
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set the name of the destination
	 * @param name name of the destination to set
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Get the distance
	 * @return the distance
	 */
	public double getDistance(){
		return this.distance;
	}
	
	/**
	 * Set the distance
	 * @param dist distance to set
	 */
	public void setDistance(double dist){
		this.distance = dist;
	}
	
	public int compare(Destination d1, Destination d2){
		return d1.getName().compareTo(d2.getName());
	}
	
	/**
	 * Equals method
	 */
	public boolean equals(Object obj){
		if(obj != null && obj instanceof Destination){
		    Destination d = (Destination) obj;			
			return name == d.getName();
		} else{
			return false;
		}
	}
	
	/**
	 * Hash code method
	 */
	public int hashCode(){
		return name.hashCode();
	}
	
}
