package Class;


public class Destination {

	private String name;
	private double distance;
	
	public Destination(){
	}
	
	public Destination(String name, double distance){
		this.name = name;
		this.distance = distance;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public void setDistance(double dist){
		this.distance = dist;
	}
	
	public int compare(Destination d1, Destination d2){
		return 1;
	}
	
	public boolean equals(){
		return true;
	}
	
}
