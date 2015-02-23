package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Class.Destination;
import Class.DestinationList;

public class TestDestinationList {
	// DestinationLists
	DestinationList ds1 = new DestinationList();
	DestinationList ds2 = new DestinationList();
	
	// Destinations
	Destination london = new Destination("London",100);
	Destination paris = new Destination("Paris",100);
	Destination edinburgh = new Destination("Edinburgh",100);
	Destination dublin = new Destination("Dublin",100);
	Destination miami = new Destination("Miami",100);
	Destination larochelle = new Destination("La Rochelle",100);
	
	@Before public void initialize(){
		// Test with values
		ds1.addDestination(london);
		ds1.addDestination(paris);
		ds1.addDestination(edinburgh);
		ds1.addDestination(dublin);
		
		ds2.addDestination(edinburgh);
		ds2.addDestination(dublin);
		ds2.addDestination(miami);
		ds2.addDestination(larochelle);		
	}


	
	@Test
	public void testAddAndGet(){
		DestinationList dstList = new DestinationList();
		
		// Adding
		String msg = "Failed to add destination";
		assertTrue(msg,dstList.addDestination(paris));
		assertTrue(msg,dstList.addDestination(london));
		assertTrue(msg,dstList.addDestination(edinburgh));
		assertTrue(msg,dstList.addDestination(dublin));
		
		// Detect duplicates
		msg = "Failed to detect duplicates";
		assertFalse(msg,dstList.addDestination(paris));
		assertFalse(msg,dstList.addDestination(dublin));
		
		// Add null destination
		msg = "Failed to detect null";
		assertFalse(msg,dstList.addDestination(null));
		
		
		// Get existing element
		msg = "Failed to get element from list";
		assertEquals(msg,paris,dstList.getDest("Paris"));
		
		// Get not existing element
		msg = "Should return null for non-existing element";
		assertNull(msg,dstList.getDest("NotExisting"));
		
		// Get null element
		assertNull(msg,dstList.getDest(null));
	}
	

	@Test
	public void testSameDestinations(){
		DestinationList ds1 = new DestinationList();
		DestinationList ds2 = new DestinationList();
		
		// Null
		String msg = "Fail on null DestinationList, should return empty array";
		assertEquals(msg, new DestinationList(),ds1.getSameDestinations(null));
		
		// Empty
		msg = "Fail on empty same destinations, should return empty array";
		assertEquals(msg, new DestinationList(), ds1.getSameDestinations(ds2));		
		
		// Random values
		ds1 = this.ds1;
		ds2 = this.ds2;
		DestinationList expected = new DestinationList();
		expected.addDestination(edinburgh);
		expected.addDestination(dublin);
		msg = "Fail on test to detect shared destinations between populated destinations list";
		assertEquals(msg,expected, ds1.getSameDestinations(ds2));		
	}
	
	@Test
	public void testDifferentDestinations(){
		DestinationList ds1 = new DestinationList();
		DestinationList ds2 = new DestinationList();
		
		// Null
		String msg = "Fail on null DestinationList, should return empty array";
		assertEquals(msg, new DestinationList(), ds1.getDifferentDestinations(null));
		
		// Empty
		msg = "Fail on empty different destinations, should return empty array";
		assertEquals(msg, new DestinationList(), ds1.getDifferentDestinations(ds2));
		
		// Random values
		ds1 = this.ds1;
		ds2 = this.ds2;
		DestinationList expected = new DestinationList();
		expected = new DestinationList();
		expected.addDestination(london);
		expected.addDestination(paris);
		msg = "Fail on test to detect different destinations between populated destinations list";
		assertEquals(msg,expected, ds1.getDifferentDestinations(ds2));	
	}
	
	@Test
	public void testContains(){
		DestinationList dsList = this.ds1;
		
		// Null
		String msg = "Fail on null dstination should return false";
		assertFalse(msg, dsList.contains(null));
		
		// Find
		msg = "Fail to find a match, should return true";
		assertTrue(msg, dsList.contains(paris));
		
		// Test fail
		msg = "Fail to not find, should return false";
		assertFalse(msg, dsList.contains(new Destination("Princess street",100)));
	}
	
	@Test
	public void testGetNameList(){
		DestinationList dsList = new DestinationList();
		
		// Empty list
		String msg = "Fail on empty list return empty string";
		assertEquals(msg, "", dsList.getNameList());
		
		// Ramdom list
		dsList = this.ds1;
		
		// Test list with data
		msg = "Fail on filled list";
		String expected = "London\n" + "Dublin\n" + "Edinburgh\n" + "Paris\n";
		
		String nameList = dsList.getNameList();
		assertEquals(msg, expected,dsList.getNameList());
	}
}