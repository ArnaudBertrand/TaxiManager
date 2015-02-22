package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Class.Destination;
import Class.Journey;
import Class.Taxi;

public class TestJourney {
	
	//For this test, we suppose that the taxis are valid ones
	Taxi t = new Taxi ("Bob", "LON-XFDERT");
	//Destinations
	Destination d1 = new Destination ("Manchester",35);
	Destination d2 = new Destination ("Edinburgh", 100);
	Destination d3 = new Destination ("Southampton", 48);
	Destination d4 = new Destination ("Liverpool",-9);
	Destination d5 = new Destination ("Liverpool",29);
	//Creation of journeys 
	Journey j1 = new Journey (t, d1, 6);
	Journey j2 = new Journey (t, d2, 4);
	Journey j3 = new Journey (t, d3, 7);
	Journey j4 = new Journey (t, d4, 5);
	Journey j5 = new Journey (t, d5, -1);
	
	@Test
	public void testGetJourneyFee1() {
		//The fee of journey 1 should be: 5+(35*0.99)+(1*6)=45.65
		assertEquals("error on j1" , 45.65, j1.getJourneyFee(), 2);
	}
	
	@Test
	public void testGetJourneyFee2() {
		//The fee of journey 2 should be: 5+(100*0.99)+(2*4)=112
		assertEquals("error on j2" , 112, j2.getJourneyFee(), 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetJourneyFee3() {	
		//Problem: number of passenger > 6
		j3.getJourneyFee();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetJourneyFee4() {
		//Problem: distance < 0
		j4.getJourneyFee();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetJourneyFee5() {
		//Problem: number of passenger < 0
		j5.getJourneyFee();
	}
	
}
