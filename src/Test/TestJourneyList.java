package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Class.Destination;
import Class.FunctionalConstants;
import Class.Journey;
import Class.JourneyList;
import Class.Manager;
import Class.RegNbFormatException;
import Class.Taxi;

public class TestJourneyList {

	Manager M1 = new Manager();
	JourneyList JL = new JourneyList(M1);
	ArrayList<Journey> journeyList = new ArrayList<Journey>();
	// For this test, we suppose that the taxis are valid ones
	
	Destination d1,d2,d3,d4;
	Taxi t;
	Journey j1,j2,j3,j4,j11,j12,j13,j14,j15,j16,j17,j18,j19,j20,j21;
	
	@Before public void initialize() {
		try {
			t = new Taxi("Bob", "LON-XFDERT");
			// Destinations
			d1 = new Destination("Manchester", 35);
			d2 = null;
			d3 = new Destination("Edinburgh", 100);
			d4 = new Destination("Liverpool", 78);
			// Journeys
			j1 = new Journey(t, d1, 6);
			j2 = new Journey(t, d2, 4);
			j3 = new Journey(t, d1, 7);
			j4 = new Journey(t, d1, -1);
			j11 = new Journey(t, d1, 1);
			j12 = new Journey(t, d1, 2);
			j13 = new Journey(t, d1, 3);
			j14 = new Journey(t, d1, 4);
			j15 = new Journey(t, d4, 1);
			j16 = new Journey(t, d4, 2);
			j17 = new Journey(t, d4, 3);
			j18 = new Journey(t, d4, 4);
			j19 = new Journey(t, d3, 1);
			j20 = new Journey(t, d3, 2);
			j21 = new Journey(t, d3, 3);
		} catch (RegNbFormatException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddJourneyToJourneyList() {
		// Should add j1
		assertTrue("error to add j1", JL.addJourney(j1));
		
		// Should not add j2 because destination is null
		assertFalse("should not add j2", JL.addJourney(j2));
		
		// Should not add j3 because number of passenger > 6
		assertFalse("should not add j3", JL.addJourney(j3));
		
		// Should not add j4 because number of passenger < 0
		assertFalse("should not add j4", JL.addJourney(j4));
	}

	@Test
	public void testGetAllJourneys(){
		// Test nb journey inferior at 5
		JL.addJourney(j11); //fee 40.65
		JL.addJourney(j12); //fee 41.65
		JL.addJourney(j13); //fee 42.65
		JL.addJourney(j14); //fee 43.65
		
		String ExceptedList = "CHARGES FOR THE TOP 5 JOURNEYS \n"
				+ "LON-XFDERT  Manchester                  35.0km    4 people      Cost "+FunctionalConstants.POUNDS+" 43.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    3 people      Cost "+FunctionalConstants.POUNDS+" 42.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    2 people      Cost "+FunctionalConstants.POUNDS+" 41.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    1 person      Cost "+FunctionalConstants.POUNDS+" 40.65\n"
				+ "\nCHARGES FOR THE CHEAPEST 5 JOURNEYS\n"
				+ "LON-XFDERT  Manchester                  35.0km    4 people      Cost "+FunctionalConstants.POUNDS+" 43.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    3 people      Cost "+FunctionalConstants.POUNDS+" 42.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    2 people      Cost "+FunctionalConstants.POUNDS+" 41.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    1 person      Cost "+FunctionalConstants.POUNDS+" 40.65\n";
		
		assertEquals("should be equal",ExceptedList, JL.getAllJourneys());

		// Add eleven journeys (10 would have been enough)
		JL.addJourney(j15); //fee 84.22
		JL.addJourney(j16); //fee 86.22
		JL.addJourney(j17); //fee 88.22
		JL.addJourney(j18); //fee 90.22
		JL.addJourney(j19); //fee 106.00
		JL.addJourney(j20); //fee 108.00
		JL.addJourney(j21); //fee 110.00
		
		//Excepted result
		ExceptedList = "CHARGES FOR THE TOP 5 JOURNEYS \n"
				+ "LON-XFDERT  Edinburgh                  100.0km    3 people      Cost "+FunctionalConstants.POUNDS+"110.00\n"
				+ "LON-XFDERT  Edinburgh                  100.0km    2 people      Cost "+FunctionalConstants.POUNDS+"108.00\n"
				+ "LON-XFDERT  Edinburgh                  100.0km    1 person      Cost "+FunctionalConstants.POUNDS+"106.00\n"
				+ "LON-XFDERT  Liverpool                   78.0km    4 people      Cost "+FunctionalConstants.POUNDS+" 90.22\n"
				+ "LON-XFDERT  Liverpool                   78.0km    3 people      Cost "+FunctionalConstants.POUNDS+" 88.22\n\n"
				+ "CHARGES FOR THE CHEAPEST 5 JOURNEYS\n"
				+ "LON-XFDERT  Liverpool                   78.0km    1 person      Cost "+FunctionalConstants.POUNDS+" 84.22\n"
				+ "LON-XFDERT  Manchester                  35.0km    4 people      Cost "+FunctionalConstants.POUNDS+" 43.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    3 people      Cost "+FunctionalConstants.POUNDS+" 42.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    2 people      Cost "+FunctionalConstants.POUNDS+" 41.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    1 person      Cost "+FunctionalConstants.POUNDS+" 40.65\n";
		
		//Test
		assertEquals("should be equal",ExceptedList, JL.getAllJourneys());
}
}
