package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Class.Destination;
import Class.Journey;
import Class.JourneyList;
import Class.Manager;
import Class.Taxi;

public class TestJourneyList {

	Manager M1 = new Manager();
	JourneyList JL = new JourneyList(M1);
	ArrayList<Journey> journeyList = new ArrayList<Journey>();
	// For this test, we suppose that the taxis are valid ones
	Taxi t = new Taxi("Bob", "LON-XFDERT");
	// Destinations
	Destination d1 = new Destination("Manchester", 35);
	Destination d2 = null;
	Destination d3 = new Destination("Edinburgh", 100);
	Destination d4 = new Destination("Liverpool", 78);

	// Creation of journeys
	Journey j1 = new Journey(t, d1, 6);
	Journey j2 = new Journey(t, d2, 4);
	Journey j3 = new Journey(t, d1, 7);
	Journey j4 = new Journey(t, d1, -1);

	Journey j11 = new Journey(t, d1, 1);
	Journey j12 = new Journey(t, d1, 2);
	Journey j13 = new Journey(t, d1, 3);
	Journey j14 = new Journey(t, d1, 4);
	Journey j15 = new Journey(t, d4, 1);
	Journey j16 = new Journey(t, d4, 2);
	Journey j17 = new Journey(t, d4, 3);
	Journey j18 = new Journey(t, d4, 4);
	Journey j19 = new Journey(t, d3, 1);
	Journey j20 = new Journey(t, d3, 2);
	Journey j21 = new Journey(t, d3, 3);

	@Test
	public void testAddJourneyToJourneyList1() {
		// Should add j1
		assertTrue("error to add j1", JL.addJourney(j1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddJourneyToJourneyList2() {
		// Should not add j2 because destination is null
		assertFalse("should not add j2", JL.addJourney(j2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddJourneyToJourneyList3() {
		// Should not add j3 because number of passenger > 6
		assertFalse("should not add j3", JL.addJourney(j3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddJourneyToJourneyList4() {
		// Should not add j4 because number of passenger < 0
		assertFalse("should not add j4", JL.addJourney(j4));
	}

	@Test (expected=IllegalArgumentException.class)
	public void testGetAllJourneys1(){
		//Add only 6 journeys, therefore the method getAllJourneys should not work
		JL.addJourney(j11);
		JL.addJourney(j12);
		JL.addJourney(j13);
		JL.addJourney(j14);
		JL.addJourney(j15);
		JL.addJourney(j16);
		
		JL.getAllJourneys();	
	}
	
	@Test
	public void testGetAllJourneys2(){
		//Add eleven journeys (10 would have been enough)
		JL.addJourney(j11); //fee 40.65
		JL.addJourney(j12); //fee 41.65
		JL.addJourney(j13); //fee 42.65
		JL.addJourney(j14); //fee 43.65
		JL.addJourney(j15); //fee 84.22
		JL.addJourney(j16); //fee 86.22
		JL.addJourney(j17); //fee 88.22
		JL.addJourney(j18); //fee 90.22
		JL.addJourney(j19); //fee 106.00
		JL.addJourney(j20); //fee 108.00
		JL.addJourney(j21); //fee 110.00
		
		//Excepted result
		String ExceptedList = "CHARGES FOR THE TOP 5 JOURNEYS \n"
				+ "LON-XFDERT  Edinburgh                  100,0km    3 people    Cost £110,00\n"
				+ "LON-XFDERT  Edinburgh                  100,0km    2 people    Cost £108,00\n"
				+ "LON-XFDERT  Edinburgh                  100,0km    1 person    Cost £106,00\n"
				+ "LON-XFDERT  Liverpool                   78,0km    4 people    Cost £ 90,22\n"
				+ "LON-XFDERT  Liverpool                   78,0km    3 people    Cost £ 88,22\n\n"
				+ "CHARGES FOR THE CHEAPEST 5 JOURNEYS\n"
				+ "LON-XFDERT  Liverpool                   78,0km    1 person    Cost £ 84,22\n"
				+ "LON-XFDERT  Manchester                  35,0km    4 people    Cost £ 43,65\n"
				+ "LON-XFDERT  Manchester                  35,0km    3 people    Cost £ 42,65\n"
				+ "LON-XFDERT  Manchester                  35,0km    2 people    Cost £ 41,65\n"
				+ "LON-XFDERT  Manchester                  35,0km    1 person    Cost £ 40,65\n";
		
		//Test
		assertEquals("should be equal",ExceptedList, JL.getAllJourneys());
}
}
