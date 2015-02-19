package Class;

import java.util.*;

public class Journey {

	private Taxi taxi;
	private Destination destination;
	private int nbPerson;
	
	//constructor
	public Journey(Taxi taxi, Destination destination, int nbPerson){
		this.taxi = taxi;
		this.destination = destination;
		this.nbPerson = nbPerson;
	}
	
	public Taxi getTaxi(){
		return this.taxi;
	}
	
	public void setTaxi(Taxi t){
		this.taxi = t;
	}
	
	public Destination getDestination(){
		return this.destination;
	}
	
	public void setDestination(Destination d){
		this.destination = d;
	} 
	
	public int getNbPerson(){
		return this.nbPerson;
	}
	
	public void setNbPerson(int nbPerson){
		this.nbPerson = nbPerson;
	}
	
	public double getJourneyFee(){
		//Instantiation of the variables, fee set to 5£
		double fee = 5.00;
		double distance;
		int nbPerson;
		
		//Get the distance of the journey
		distance = this.destination.getDistance();
		
		//Get the number of people of the journey
		nbPerson = this.getNbPerson();
		
		//Add the fee for the distance
		fee = fee + distance*0.99;
		
		//Add the fee for the number of people
		if (distance > 50) {
			fee = fee + 2.00*nbPerson;
		} else {
			fee = fee + 1.00*nbPerson;
		}

		return fee;
	}
	
	public int compare(Journey j1, Journey j2){
		return 1;
	}
	
}
