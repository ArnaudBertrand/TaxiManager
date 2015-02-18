package Class;

import java.util.*;

public class Taxi implements Comparable<Taxi> {
	
	private String regNb;
	private String driverName;
	private int nbOfSeats;
	
	//constructor
	public Taxi(String driverName, String regNb){	
		this.driverName = driverName;
		this.regNb = regNb;
	}
	
	public String getRegNb(){
		return this.regNb;
	}
	
	public void setRegNb(String regNb){
		this.regNb = regNb;
	}
	
	public String getDriverName(){
		return this.driverName;
	}
	
	public void setDriverName(String driverName){
		this.driverName = driverName;
	} 
	
	public int getNbOfSeats(){
		return this.nbOfSeats;
	}
	
	public void setNbOfSeats(int nbOfSeats){
		this.nbOfSeats = nbOfSeats;
	}
	
	public int compare(Taxi t1, Taxi t2){
		return t1.getDriverName().compareTo(t2.getDriverName());
	}
	
	public boolean equals(Taxi other){
		return (other.getRegNb().equals(this.getRegNb()));
	} 
	
	public int compareTo(Taxi other) {
		return driverName.compareTo(other.driverName);
	}
	
	public int hashCode() { 
		return driverName.hashCode();
	}



}
