package Class;

public class Journey {

	/** Instanciate variables **/
	private Taxi taxi;
	private Destination destination;
	private int nbPerson;
	
	/** Errors **/
	private final static String ERROR_NULL_TAXI_DEST = "Taxi or destination cannot be null";
	private final static String ERROR_NB_PERSON_IN_TAXI = "Number of person in taxi out of bounds: ";

	/**
	 * Constructor
	 * 
	 * @param taxi
	 * @param destination
	 * @param number
	 *            of passenger
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 * */
	public Journey(Taxi taxi, Destination destination, int nbPerson) throws NullPointerException, IllegalArgumentException {
		if(taxi == null || destination == null){
			throw new NullPointerException(ERROR_NULL_TAXI_DEST);
		}
		if(nbPerson < 1 || taxi.getNbOfSeats() < nbPerson){
			throw new IllegalArgumentException(ERROR_NB_PERSON_IN_TAXI + nbPerson);
		}
		this.taxi = taxi;
		this.destination = destination;
		this.nbPerson = nbPerson;
	}

	/**
	 * Get the taxi object
	 * 
	 * @return the taxi that made the journey
	 * */
	public Taxi getTaxi() {
		return this.taxi;
	}

	/**
	 * Set the taxi object
	 * 
	 * @param the
	 *            taxi object to set
	 * */
	public void setTaxi(Taxi t) {
		this.taxi = t;
	}

	/**
	 * Get the destination
	 * 
	 * @return the destination object for the journey
	 * */
	public Destination getDestination() {
		return this.destination;
	}

	/**
	 * Set the destination
	 * 
	 * @param the
	 *            destination object to set
	 * */
	public void setDestination(Destination d) {
		this.destination = d;
	}

	/**
	 * Get the number of people
	 * 
	 * @return the number of people that made the journey
	 * */
	public int getNbPerson() {
		return this.nbPerson;
	}

	/**
	 * Set the number of people
	 * 
	 * @param the
	 *            number of people to set
	 * */
	public void setNbPerson(int nbPerson) {
		this.nbPerson = nbPerson;
	}

	/**
	 * Calculate the fee for a journey
	 * 
	 * @return fee
	 * */
	public double getJourneyFee() {
		// Instantiation of the variables, fee set to 5 pounds at first
		double fee = 5.00;
		double distance;
		int nbPerson;

		// Get the distance of the journey
		distance = this.destination.getDistance();
		// Get the number of people of the journey
		nbPerson = this.getNbPerson();

		// Add the fee for the distance
		fee = fee + distance * 0.99;

		// Add the fee for the number of people
		if (distance > 50) {
			fee = fee + 2.00 * nbPerson;
		} else {
			fee = fee + 1.00 * nbPerson;
		}

		return fee;
	}

	/**
	 * Method to compare the destinations
	 * 
	 * @param the
	 *            journeys to compare
	 * */
	public int compare(Journey j1, Journey j2) {
		return j1.getDestination().getName()
				.compareTo(j2.getDestination().getName());
	}

}
