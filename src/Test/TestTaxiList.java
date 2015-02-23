package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Class.Destination;
import Class.Journey;
import Class.JourneyList;
import Class.Manager;
import Class.RegNbFormatException;
import Class.Taxi;
import Class.TaxiList;

public class TestTaxiList {
	Manager man = new Manager();
	TaxiList taxiList = new TaxiList(man);
	Taxi t1,t2,t3,t4;
	Journey j1,j2,j3,j4;
	
	// Destinations
	Destination paris = new Destination("Paris", 6.7);
	Destination londres = new Destination("Londres", 3.8);
	Destination nyc = new Destination("NYC", 9.8);
	
	
	@Before public void initialize(){
		try {
			// Taxis
			t1 = new Taxi("Jean De Bourg", "LON-1649GF");
			t2 = new Taxi("Nick Clarck", "LON-HHFFSS");
			t3 = new Taxi("Johnny Bigor", "LON-990055");
			t4 = new Taxi("Nina Swarowski", "MAN-NN1789");
			
			// Journeys
			j1 = new Journey(t4, paris, 1);
			j2 = new Journey(t4, londres, 2);
			j3 = new Journey(t4, nyc, 3);
			j4 = new Journey(t2, londres, 4);
		} catch (RegNbFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testAddAndRemoveByRegNb() {
		taxiList = new TaxiList(man);
		
		// Add null
		String msg = "Should return false on null taxi";
		assertFalse("msg",taxiList.addTaxi(null));
		
		// Random values
		msg = "Should return true on valid values";
		assertTrue(msg,taxiList.addTaxi(t1));
		assertTrue(msg,taxiList.addTaxi(t2));
		assertTrue(msg,taxiList.addTaxi(t3));
		assertTrue(msg,taxiList.addTaxi(t4));
		
		// Dupplicates
		msg = "Should return false on dupplicate values";
		assertFalse(msg,taxiList.addTaxi(t1));
		assertFalse(msg,taxiList.addTaxi(t2));
				
		// Remove null
		msg = "Should return false on null value";
		assertFalse(msg,taxiList.removeTaxiByRegNb(null));
		
		// Remove valid value
		msg = "Should return true on valid taxi";
		assertTrue(msg,taxiList.removeTaxiByRegNb("LON-1649GF"));
		assertTrue(msg,taxiList.removeTaxiByRegNb("LON-HHFFSS"));
		assertTrue(msg,taxiList.removeTaxiByRegNb("LON-990055"));
		assertTrue(msg,taxiList.removeTaxiByRegNb("MAN-NN1789"));
		
		// Remove not existing
		msg = "Should remove false on not existing";
		assertFalse(msg,taxiList.removeTaxiByRegNb("LON-990055"));
		assertFalse(msg,taxiList.removeTaxiByRegNb("MAN-NN1789"));
	}
	
	@Test
	public void testGetTaxiByRegNb() {
		taxiList = new TaxiList(man);
		taxiList.addTaxi(t1);
		taxiList.addTaxi(t2);
		
		// Null
		String msg = "Should return null on null value";
		assertNull(msg,taxiList.getTaxiByRegNb(null));
		
		// Random value
		assertEquals(msg,t1,taxiList.getTaxiByRegNb("LON-1649GF"));	
		msg = "Should return null for null element (not in the list)";
		
		// Not existing
		msg = "Should return null for non-existing element (not in the list)";
		assertEquals(msg,null,taxiList.getTaxiByRegNb("NotExisting"));					
	}
	
	@Test
	public void testGetDriverNameAndDest() {
		taxiList = new TaxiList(man);
		JourneyList journeyList = man.getJourneyList();
		
		// Empty list
		String msg = "Fail on empty list return empty string";
		assertEquals(msg, "", taxiList.getDriverNameAndDest());
		
		// Create taxi list
		taxiList.addTaxi(t4);
		taxiList.addTaxi(t2);
		
		// Create journey list
		journeyList.addJourney(j1);
		journeyList.addJourney(j2);
		journeyList.addJourney(j3);
		journeyList.addJourney(j4);
		
		// Expected result
		String expectedList = "Nick Clarck\n"
				+ "   " + "Londres\n\n"
				+ "Nina Swarowski\n"
				+ "   " + "Londres\n"
				+ "   " + "NYC\n"
				+ "   " + "Paris\n\n";
		
		// Test
		msg = "Fail to give the correct expected output";
		assertTrue(msg, expectedList.equals(taxiList.getDriverNameAndDest()));
	}
	
}
