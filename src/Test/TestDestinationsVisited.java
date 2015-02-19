package Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import Class.Destination;
import Class.DestinationList;
import Class.DestinationsVisited;

public class TestDestinationsVisited {
	@Test
	public void testProcessLine(){
		DestinationsVisited destVisited = new DestinationsVisited();
		
		// Empty destinations
		HashMap<String,Set<Destination>> res = new HashMap<String,Set<Destination>>();
		Set<Destination> res14 = new HashSet<Destination>();
		Set<Destination> res15 = new HashSet<Destination>();
		Set<Destination> both = new HashSet<Destination>();
		res.put("2014", res14);
		res.put("2015", res15);
		res.put("BOTH", both);
		String mess = "Empty array not working";
		assertEquals(mess, res, destVisited.getYearRepartition("2014", "2015"));
		
		HashMap<String,DestinationList> dsListPerYear = new HashMap<String,DestinationList>();
				
		// Only 2014
		DestinationList dsList = new DestinationList();
		Destination london = new Destination("London",100);
		Destination paris = new Destination("Paris",100);
		Destination edinburgh = new Destination("Edinburgh",100);
		Destination dublin = new Destination("Dublin",100);
		dsList.addDestination(london);
		dsList.addDestination(paris);
		dsList.addDestination(edinburgh);
		dsList.addDestination(dublin);
		dsListPerYear.put("2014",dsList);
		destVisited.setDestinationsVisited(dsListPerYear);
		res14.add(london);
		res14.add(paris);
		res14.add(edinburgh);
		res14.add(dublin);
		mess = "Only 2014 not working";
		assertEquals(mess, res, destVisited.getYearRepartition("2014", "2015"));

		// 2014 and 2015
		dsList = new DestinationList();
		Destination miami = new Destination("Miami",100);
		Destination larochelle = new Destination("La Rochelle",100);
		dsList.addDestination(miami);
		dsList.addDestination(paris);
		dsList.addDestination(edinburgh);
		dsList.addDestination(larochelle);
		dsListPerYear.put("2015",dsList);
		res15.add(miami);
		res15.add(paris);
		res15.add(edinburgh);
		res15.add(larochelle);
		mess = "2014 and 2015 not working";
		assertEquals(mess, res, destVisited.getYearRepartition("2014", "2015"));
		

	}
	
	@Test
	public void testGetYearRepartition(){
		
	}
}
