package Class;

import java.util.*;

public class Journey {

	private Taxi taxi;
	private Destination destination;
	private int nbPerson;
	
	//constructor
	public Journey(){
					
	}
	
	public Taxi getTaxi(){
		return taxi;
	}
	
	public void setTaxi(Taxi t){
		
	}
	
	public Destination getDestination(){
		return destination;
	}
	
	public void setDestination(Destination d){
		
	} 
	
	public int getNbPerson(){
		return 24;
	}
	
	public void setNbPerson(int i){
		
	}
	
	public double getJourneyFee(){
		return 5.555;
	}
	
	public int compare(Journey j1, Journey j2){
		return 1;
	}
	
}
