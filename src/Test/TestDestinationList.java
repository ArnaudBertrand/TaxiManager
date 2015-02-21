package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Class.Destination;
import Class.DestinationList;

public class TestDestinationList {
	@Test
	public void testAddAndGet(){
		DestinationList dstList = new DestinationList();
		
		// Test adding
		String msg = "Failed to add destination";
		Destination paris = new Destination("Paris", 95);
		assertTrue(msg,dstList.addDestination(paris));
		assertTrue(msg,dstList.addDestination(new Destination("NYC", 78)));
		assertTrue(msg,dstList.addDestination(new Destination("JohnnyTown", 45)));
		assertTrue(msg,dstList.addDestination(new Destination("NinjaTurtleTown", 1789)));
		
		// Test adding duplicates
		msg = "Failed to detect duplicates";
		assertFalse(msg,dstList.addDestination(new Destination("JohnnyTown", 45)));
		assertFalse(msg,dstList.addDestination(new Destination("NinjaTurtleTown", -1)));
		
		// Test get
		msg = "Failed to get element from list";
		assertEquals(msg,paris,dstList.getDest("Paris"));		
		msg = "Should return null for non-existing element";
		assertEquals(msg,null,dstList.getDest("NotExisting"));		
	}
	
	@Test
	public void testSameAndDifferentDestinations(){
		DestinationList ds1 = new DestinationList();
		DestinationList ds2 = new DestinationList();
		
		// Create dest list
		Destination london = new Destination("London",100);
		Destination paris = new Destination("Paris",100);
		Destination edinburgh = new Destination("Edinburgh",100);
		Destination dublin = new Destination("Dublin",100);
		Destination miami = new Destination("Miami",100);
		Destination larochelle = new Destination("La Rochelle",100);
		
		// Test empty
		String msg = "Fail on empty same destinations, should return empty array";
		assertEquals(msg, new DestinationList(), ds1.getSameDestinations(ds2));
		msg = "Fail on empty different destinations, should return empty array";
		assertEquals(msg, new DestinationList(), ds1.getDifferentDestinations(ds2));
		
		// Test with values
		ds1.addDestination(london);
		ds1.addDestination(paris);
		ds1.addDestination(edinburgh);
		ds1.addDestination(dublin);
		
		ds2.addDestination(edinburgh);
		ds2.addDestination(dublin);
		ds2.addDestination(miami);
		ds2.addDestination(larochelle);
		
		// Test shared
		DestinationList expected = new DestinationList();
		expected.addDestination(edinburgh);
		expected.addDestination(dublin);
		msg = "Fail on test to detect shared destinations between populated destinations list";
		assertEquals(msg,expected, ds1.getSameDestinations(ds2));
		
		msg = "Fail on test to detect different destinations between populated destinations list";
		expected = new DestinationList();
		expected.addDestination(london);
		expected.addDestination(paris);
		assertEquals(msg,expected, ds1.getDifferentDestinations(ds2));	
	}
	
	@Test
	public void testContains(){
		DestinationList dsList = new DestinationList();
		
		// Empty list
		String msg = "Fail on empty list should return false";
		assertFalse(msg, dsList.contains(new Destination("Paris",100)));
		
		// Create dest list
		dsList.addDestination(new Destination("London",100));
		dsList.addDestination(new Destination("Paris",100));
		dsList.addDestination(new Destination("Edinburgh",100));
		
		// Test success
		msg = "Fail on success";
		assertTrue(msg, dsList.contains(new Destination("Paris",100)));
		
		// Test fail
		msg = "Fail on not finding";
		assertFalse(msg, dsList.contains(new Destination("Princess street",100)));
	}
	
	@Test
	public void testGetNameList(){
		DestinationList dsList = new DestinationList();
		
		// Empty list
		String msg = "Fail on empty list return empty string";
		assertEquals(msg, "", dsList.getNameList());
		
		// Create dest list
		dsList.addDestination(new Destination("London",100));
		dsList.addDestination(new Destination("Paris",100));
		dsList.addDestination(new Destination("Edinburgh",100));
		
		// Test list with data
		msg = "Fail on filled list";
		String [] expectedList = {"London\n" + "Paris\n" + "Edinburgh\n",
		                    "London\n" + "Edinburgh\n" + "Paris\n",
		                    "Paris\n" + "Edinburgh\n" + "London\n",
		                    "Paris\n" + "London\n" + "Edinburgh\n",
		                    "Edinburgh\n" + "Paris\n" + "London\n",
		                    "Edinburgh\n" + "London\n" + "Paris\n"};
		
		boolean inArray = false;
		String nameList = dsList.getNameList();
		for(int i = 0; i<6; i++){
			if(expectedList[i].equals(nameList)){
				inArray = true;
				break;
			}
		}
		assertTrue(msg, inArray);
	}
}