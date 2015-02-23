package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Class.Destination;

public class TestDestination {
	@Test
	public void testDestinationCompareAndEquals(){
		Destination dest1 = new Destination();
		dest1.setName("Liverpool");
		dest1.setDistance(15);
		
		Destination dest2 = new Destination("Liverpool", -1);

		Destination dest3 = new Destination("Manchester",35);
		
		//Test equals
		String mess = "Destination should be identical";
		assertTrue(mess,dest1.equals(dest2));
		dest2 = new Destination("Liverpool", 15);
		assertTrue(mess,dest1.equals(dest2));
		mess = "Destination should not be identical";
		assertFalse(mess,dest1.equals(dest3));
		
		// Test compare
		mess = "Expect to be 0 for identical";
		assertEquals(mess,0,dest1.compare(dest1, dest2));
		mess = "L before M result should be -1";
		assertEquals(mess,-1,dest1.compare(dest1, dest3));
		mess = "M after L result should be 1";
		assertEquals(mess,1,dest1.compare(dest3, dest1));
	}
}
