package Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DestinationList {

	private Set<Destination> destinationList = new HashSet<Destination>();
	
	//constructor
	public DestinationList(){
	}
		
	public Set<Destination> getDestinationList(){
		return this.destinationList;
	}
	
	public void setDestinationList(Set<Destination> destinationList){
		this.destinationList = destinationList;
	}
	
	public boolean addDestination(Destination dest){
		return destinationList.add(dest);
	}

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
}
