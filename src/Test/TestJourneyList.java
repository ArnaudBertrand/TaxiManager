package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

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

	Manager manager = new Manager();
	JourneyList jL = new JourneyList(manager);
	
	// For this test, we suppose that the taxis are valid ones
	
	Destination d1 = new Destination("Manchester", 35),
			d3 = new Destination("Edinburgh", 100),
			d4 = new Destination("Liverpool", 78);
	
	Taxi t1,t2;
	Journey j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12;
	
	@Before public void initialize() {
		try {
			t1 = new Taxi("Bob", "LON-XFDERT");
			t2 = new Taxi("bobi","LON-XFDEEE");
			// Destinations
			// Journeys
			j1 = new Journey(t1, d1, 3);
			j2 = new Journey(t1, d1, 1);
			j3 = new Journey(t1, d1, 2);
			j4 = new Journey(t1, d1, 3);
			j5 = new Journey(t1, d1, 4);
			j6 = new Journey(t1, d4, 1);
			j7 = new Journey(t1, d4, 2);
			j8 = new Journey(t1, d4, 3);
			j9 = new Journey(t1, d4, 4);
			j10 = new Journey(t1, d3, 1);
			j11 = new Journey(t1, d3, 2);
			j12 = new Journey(t1, d3, 3);
			
			jL.addJourney(j2); //fee 40.65
			jL.addJourney(j3); //fee 41.65
			jL.addJourney(j4); //fee 42.65
			jL.addJourney(j5); //fee 43.65

		} catch (RegNbFormatException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddJourneyToJourneyList() {
		// Null
		assertFalse("Should not add null", jL.addJourney(null));
		
		// Random value
		assertTrue("Should allow valid entry", jL.addJourney(j1));
		
		// Dupplicate
		assertTrue("Should allow dupplicate", jL.addJourney(j1));
	}

	@Test
	public void testGetAllJourneys(){
		// Test nb journey inferior at 5
		String expectedList = "CHARGES FOR THE TOP 5 JOURNEYS \n"
				+ "LON-XFDERT  Manchester                  35.0km    4 people      Cost "+FunctionalConstants.POUNDS+" 43.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    3 people      Cost "+FunctionalConstants.POUNDS+" 42.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    2 people      Cost "+FunctionalConstants.POUNDS+" 41.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    1 person      Cost "+FunctionalConstants.POUNDS+" 40.65\n"
				+ "\nCHARGES FOR THE CHEAPEST 5 JOURNEYS\n"
				+ "LON-XFDERT  Manchester                  35.0km    4 people      Cost "+FunctionalConstants.POUNDS+" 43.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    3 people      Cost "+FunctionalConstants.POUNDS+" 42.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    2 people      Cost "+FunctionalConstants.POUNDS+" 41.65\n"
				+ "LON-XFDERT  Manchester                  35.0km    1 person      Cost "+FunctionalConstants.POUNDS+" 40.65\n";
		
		assertEquals("Strings should be equal, take a look in debug mode",expectedList, jL.getAllJourneys());

		// Test with > 5
		jL.addJourney(j6); //fee 84.22
		jL.addJourney(j7); //fee 86.22
		jL.addJourney(j8); //fee 88.22
		jL.addJourney(j9); //fee 90.22
		jL.addJourney(j10); //fee 106.00
		jL.addJourney(j11); //fee 108.00
		jL.addJourney(j12); //fee 110.00
		
		// Expected result
		expectedList = "CHARGES FOR THE TOP 5 JOURNEYS \n"
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
		assertEquals("Strings should be equal, take a look in debug mode",expectedList, jL.getAllJourneys());
	}
	
	@Test
	public void testGetDestination(){
		jL = new JourneyList(manager);
		Set<String> expected = new TreeSet<String>();
		
		// Null
		String msg = "Should return an empty array list";
		assertEquals(msg,expected,jL.getDestinationsForTaxi(null));
		
		// Not existing
		assertEquals(msg,expected,jL.getDestinationsForTaxi(t2));
		
		// Existing
		msg = "Should return an array list equals to expected";
		jL.addJourney(j2);
		jL.addJourney(j7);
		jL.addJourney(j8);
		jL.addJourney(j10);
		jL.addJourney(j11);
		expected.add("Manchester");
		expected.add("Edinburgh");
		expected.add("Liverpool");
		assertEquals(msg,expected,jL.getDestinationsForTaxi(t1));
	}
}
