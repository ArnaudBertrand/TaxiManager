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

}
