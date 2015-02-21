package Class;

import java.util.HashSet;
import java.util.Set;

public class DestinationList {
	/** Destination list **/
	private Set<Destination> destinationList = new HashSet<Destination>();
	
	/**
	 * Set a list of destination 
	 * @param destinationList list of destination to set
	 */
	public void setDestinationList(Set<Destination> destinationList){
		this.destinationList = destinationList;
	}
	
	/**
	 * Add a destination to the set
	 * @param dest destination to set
	 * @return true if inserted false if already exists
	 */
	public boolean addDestination(Destination dest){
		return destinationList.add(dest);
	}

	/**
	 * Get a destination from name
	 * @param destName name of the destination
	 * @return destination or null if not found
	 */
	public Destination getDest(String destName) {
		Destination dest = null;
		for(Destination destValid : destinationList){
			if(destName != null && destName.equals(destValid.getName())){
				dest = destValid;
				break;
			}
		}
		return dest;
	}
	
	/**
	 * Get same destinations
	 * @param other the other destination list to compare
	 * @return a destination list composed of shared destinations
	 */
	public DestinationList getSameDestinations(DestinationList other){
		DestinationList shared = new DestinationList();
		for(Destination dst : destinationList){
			if(other.contains(dst)){
				shared.addDestination(dst);
			}
		}
		return shared;
	}
	
	/**
	 * Get different destinations
	 * @param other the other destination list to compare
	 * @return a destination list composed of destinations not shared
	 */
	public DestinationList getDifferentDestinations(DestinationList other){
		DestinationList dstList = new DestinationList();
		for(Destination dst : destinationList){
			if(!other.contains(dst)){
				dstList.addDestination(dst);
			}
		}
		return dstList;
	}
	
	/**
	 * Check if the list contains the destination
	 * @param dest destination to check
	 * @return true if contains otherwise false
	 */
	public boolean contains(Destination dest){
		return destinationList.contains(dest);
	}

	/**
	 * Get size of the destination list
	 * @return the size
	 */
	public int getSize() {
		return destinationList.size();
	}

	/**
	 * Get the list of names of destinations
	 * @return list of name of destinations
	 */
	public Object getNameList() {
		String destList = "";
		for(Destination dest : destinationList){
			destList += dest.getName() + FunctionalConstants.NEW_LINE;
		}
		return destList;
	}
}
