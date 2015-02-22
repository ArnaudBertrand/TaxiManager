package Class;

public class Journey {

	private Taxi taxi;
	private Destination destination;
	private int nbPerson;

	/**
	 * Constructor
	 * 
	 * @param taxi
	 * @param destination
	 * @param number
	 *            of passenger
	 * */
	public Journey(Taxi taxi, Destination destination, int nbPerson) {
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
		// Instantiation of the variables, fee set to 5£ at first
		double fee = 5.00;
		double distance;
		int nbPerson;

		// Get the distance of the journey
		distance = this.destination.getDistance();
		if (distance <= 0)
			throw new IllegalArgumentException(
					"Error during fee calculation, distance should be positive");

		// Get the number of people of the journey
		nbPerson = this.getNbPerson();
		if (nbPerson < 0 || nbPerson > 6)
			throw new IllegalArgumentException(
					"Error during fee calculation, number of passenger should be between 1 and 6");

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
