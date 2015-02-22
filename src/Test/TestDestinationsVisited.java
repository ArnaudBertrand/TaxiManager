package Test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import Class.Destination;
import Class.DestinationList;
import Class.DestinationsVisited;

public class TestDestinationsVisited {
	@Test
	public void testAddForYearAndGetYearRepartition(){
		DestinationsVisited destVisited = new DestinationsVisited();
		
		// Create destinations
		Destination london = new Destination("London",100);
		Destination paris = new Destination("Paris",100);
		Destination edinburgh = new Destination("Edinburgh",100);
		Destination dublin = new Destination("Dublin",100);
		Destination miami = new Destination("Miami",100);
		Destination larochelle = new Destination("La Rochelle",100);
		
		// Empty test
		HashMap<String, DestinationList> res = destVisited.getYearRepartition("2014", "2015");
		DestinationList res14 = res.get("2014");
		DestinationList expected14 = new DestinationList();
		DestinationList res15 = res.get("2015");
		DestinationList expected15 = new DestinationList();
		DestinationList resShared = res.get("BOTH");
		DestinationList expectedShared = new DestinationList();
		String msg = "Not working for empty map";
		assertEquals(msg, expected14, res14);
		assertEquals(msg, expected15, res15);
		assertEquals(msg, expectedShared, resShared);
		
		// Set 2014
		destVisited.addDestForYear(london,"2014");
		destVisited.addDestForYear(paris,"2014");
		destVisited.addDestForYear(edinburgh,"2014");
		destVisited.addDestForYear(dublin,"2014");
		
		// Test half only 2014 have been created
		res = destVisited.getYearRepartition("2014", "2015");
		res14 = res.get("2014");
		res15 = res.get("2015");
		resShared = res.get("BOTH");
		expected14.addDestination(london);
		expected14.addDestination(paris);
		expected14.addDestination(edinburgh);
		expected14.addDestination(dublin);
		msg = "Not working for only one year existing";
		assertEquals(msg, expected14, res14);
		assertEquals(msg, expected15, res15);
		assertEquals(msg, expectedShared, resShared);

		// Complete test
		destVisited.addDestForYear(paris,"2015");
		destVisited.addDestForYear(edinburgh,"2015");
		destVisited.addDestForYear(miami,"2015");
		destVisited.addDestForYear(larochelle,"2015");

		res = destVisited.getYearRepartition("2014", "2015");
		res14 = res.get("2014");
		res15 = res.get("2015");
		resShared = res.get("BOTH");
		
		expected14 = new DestinationList();
		expected14.addDestination(london);
		expected14.addDestination(dublin);
		expected15.addDestination(miami);
		expected15.addDestination(larochelle);
		expectedShared.addDestination(paris);
		expectedShared.addDestination(edinburgh);
		
		msg = "Repartition not working properly";
		assertEquals(msg, expected14, res14);
		assertEquals(msg, expected15, res15);
		assertEquals(msg, expectedShared, resShared);
	}
}
