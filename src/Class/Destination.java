package Class;


public class Destination {

	private String name;
	private double distance;
	
	//constructor
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
		return d1.getName().compareTo(d2.getName());
	}
	
	public boolean equals(Object obj){
		Destination d = (Destination) obj;
		return this.getName() == d.getName();
	}
	
}
