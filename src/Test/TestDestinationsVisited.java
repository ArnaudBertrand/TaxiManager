package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import Class.Destination;
import Class.DestinationList;
import Class.DestinationsVisited;

public class TestDestinationsVisited {
	// Destinations
	Destination london = new Destination("London",100);
	Destination paris = new Destination("Paris",100);
	Destination edinburgh = new Destination("Edinburgh",100);
	Destination dublin = new Destination("Dublin",100);
	Destination miami = new Destination("Miami",100);
	Destination larochelle = new Destination("La Rochelle",100);
	
	@Test 
	public void testAddForYear(){
		DestinationsVisited destVisited = new DestinationsVisited();
		
		// Null
		String msg = "Should return false for null destination or null string";
		assertFalse(msg,destVisited.addDestForYear(null, "2014"));
		assertFalse(destVisited.addDestForYear(paris, null));
		
		// Random values
		msg = "Should return true on accepted values";
		assertTrue(destVisited.addDestForYear(london, "2014"));
		assertTrue(destVisited.addDestForYear(paris, "2015"));
		assertTrue(destVisited.addDestForYear(edinburgh, "2016"));
		assertTrue(destVisited.addDestForYear(dublin, "2014"));
		
		// Dupplicates
		msg = "Fail to detect dupplicate during add";
		assertFalse(msg,destVisited.addDestForYear(london, "2014"));
	}
	
	@Test
	public void testGetYearRepartition(){
		DestinationsVisited destVisited = new DestinationsVisited();
				
		// EMPTY TEST ################################
		HashMap<String, DestinationList> res = destVisited.getYearRepartition("2014", "2015");
		
		// results
		DestinationList res14 = res.get("2014");
		DestinationList res15 = res.get("2015");
		DestinationList resShared = res.get("BOTH");
		
		// expected results
		DestinationList expected14 = new DestinationList();
		DestinationList expected15 = new DestinationList();
		DestinationList expectedShared = new DestinationList();
		
		// test
		String msg = "Not working for empty map";
		assertEquals(msg, expected14, res14);
		assertEquals(msg, expected15, res15);
		assertEquals(msg, expectedShared, resShared);
		
		// TEST ONLY 2014 #############################
		// fill in 2014
		destVisited.addDestForYear(london,"2014");
		destVisited.addDestForYear(paris,"2014");
		destVisited.addDestForYear(edinburgh,"2014");
		destVisited.addDestForYear(dublin,"2014");
		
		// results
		res = destVisited.getYearRepartition("2014", "2015");
		res14 = res.get("2014");
		res15 = res.get("2015");
		resShared = res.get("BOTH");
		
		// expected
		expected14.addDestination(london);
		expected14.addDestination(paris);
		expected14.addDestination(edinburgh);
		expected14.addDestination(dublin);
		
		// test
		msg = "Not working for only one year existing";
		assertEquals(msg, expected14, res14);
		assertEquals(msg, expected15, res15);
		assertEquals(msg, expectedShared, resShared);

		// COMPLETE TEST ###############################
		// fill in 2015
		destVisited.addDestForYear(paris,"2015");
		destVisited.addDestForYear(edinburgh,"2015");
		destVisited.addDestForYear(miami,"2015");
		destVisited.addDestForYear(larochelle,"2015");

		// results
		res = destVisited.getYearRepartition("2014", "2015");
		res14 = res.get("2014");
		res15 = res.get("2015");
		resShared = res.get("BOTH");
		
		// expected
		expected14 = new DestinationList();
		expected14.addDestination(london);
		expected14.addDestination(dublin);
		expected15.addDestination(miami);
		expected15.addDestination(larochelle);
		expectedShared.addDestination(paris);
		expectedShared.addDestination(edinburgh);
		
		// test
		msg = "Repartition not working properly";
		assertEquals(msg, expected14, res14);
		assertEquals(msg, expected15, res15);
		assertEquals(msg, expectedShared, resShared);
	}
}
