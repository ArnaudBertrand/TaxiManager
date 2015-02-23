package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Class.Destination;
import Class.Journey;
import Class.JourneyList;
import Class.Manager;
import Class.RegNbFormatException;
import Class.Taxi;
import Class.TaxiList;

public class TestTaxiList {

	@Test
	public void testAddAndRemove() {
		
		Manager man = new Manager();
		TaxiList taxiList = new TaxiList(man);
		
		// Test adding
		String msg = "Success to add taxi";
		
		try {
			assertTrue(msg,taxiList.addTaxi(new Taxi("Jean De Bourg", "LON-1649GF")));
			assertTrue(msg,taxiList.addTaxi(new Taxi("Nick Clarck", "LON-HHFFSS")));
			assertTrue(msg,taxiList.addTaxi(new Taxi("Johnny Bigor", "LON-990055")));
			assertTrue(msg,taxiList.addTaxi(new Taxi("Nina Swarowski", "MAN-NN1789")));
			
			// Test adding duplicates
			msg = "Failed to detect duplicates";
			assertFalse(msg,taxiList.addTaxi(new Taxi("Johnny Bigor", "LON-990055")));
			assertFalse(msg,taxiList.addTaxi(new Taxi("Nina Swarowski", "MAN-NN1789")));
			
			// Test removing
			msg = "Success to remove taxi";
			assertTrue(msg,taxiList.removeTaxi("LON-1649GF"));
			assertTrue(msg,taxiList.removeTaxi("LON-HHFFSS"));
			assertTrue(msg,taxiList.removeTaxi("LON-990055"));
			assertTrue(msg,taxiList.removeTaxi("MAN-NN1789"));
			
			// Test removing duplicates
			msg = "Failed to detect duplicates";
			assertFalse(msg,taxiList.removeTaxi("LON-990055"));
			assertFalse(msg,taxiList.removeTaxi("MAN-NN1789"));

		} catch (RegNbFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		
		Manager man = new Manager();
		TaxiList taxiList = new TaxiList(man);
		
		try{
			Taxi taxi = new Taxi("Jean De Bourg", "LON-1649GF");
			taxiList.addTaxi(taxi);
			taxiList.addTaxi(new Taxi("Nick Clarck", "LON-HHFFSS"));
			
			// Test getTaxiByRegNb
			String msg = "Success to get element from list";
			assertEquals(msg,taxi,taxiList.getTaxiByRegNb("LON-1649GF"));	
			msg = "Should return null for null element (not in the list)";
			assertEquals(msg,null,taxiList.getTaxiByRegNb(""));
			msg = "Should return null for non-existing element (not in the list)";
			assertEquals(msg,null,taxiList.getTaxiByRegNb("NotExisting"));					
		} catch (RegNbFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetDriverNameAndDest() {
		
		Manager man = new Manager();
		TaxiList taxiList = man.getTaxiList();
		JourneyList journeyList = man.getJourneyList();
		
		// Empty list
		String msg = "Fail on empty list return empty string";
		assertEquals(msg, "", taxiList.getDriverNameAndDest());
		
		// Create taxi list
		Taxi taxi1;
		Taxi taxi2;
		try {
			taxi1 = new Taxi("Nina Swarowski", "MAN-NN1789");
			taxi2 = new Taxi("Nick Clarck", "LON-HHFFSS");
			taxiList.addTaxi(taxi1);
			taxiList.addTaxi(taxi2);
			
			// Create destinations
			Destination dest1 = new Destination("Paris", 6.7);
			Destination dest2 = new Destination("Londres", 3.8);
			Destination dest3 = new Destination("NYC", 9.8);
			
			// Create journey list
			journeyList.addJourney(new Journey(taxi1, dest1, 1));
			journeyList.addJourney(new Journey(taxi1, dest2, 2));
			journeyList.addJourney(new Journey(taxi1, dest3, 3));
			journeyList.addJourney(new Journey(taxi2, dest2, 4));
			
			
			// Test list with good data
			msg = "Success on filled list";
			String expectedList = "Nick Clarck\n"
					+ "   " + "Londres\n\n"
					+ "Nina Swarowski\n"
					+ "   " + "Paris\n"
					+ "   " + "Londres\n"
					+ "   " + "NYC\n\n";
			
			String currentList = taxiList.getDriverNameAndDest();
			assertTrue(msg, expectedList.equals(currentList));
		} catch (RegNbFormatException e) {
			System.out.println(e.getMessage());
		}

	}
	
}
