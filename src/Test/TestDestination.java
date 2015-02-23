package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Class.Destination;

public class TestDestination {
	Destination d1,d2,d3;
	
	@Before public void initialize(){
		d1 = new Destination("Liverpool",15);
		d2 = new Destination("Liverpool", 12);
		d3 = new Destination("Manchester",35);
	}
	
	@Test
	public void testConstructor(){	
		// Destination name null
		try{
			new Destination(null, 6);
			fail("Fail name null in constructor");
		} catch(IllegalArgumentException e){
			fail("Distance > 0 is allowed");
		} catch(NullPointerException e){
			assertTrue("Wrong message destination name null", e.getMessage().contains("name"));
		}
		
		// Destination distance <0
		try{
			new Destination("London", -1);
			fail("Fail negative distance in constructor");
		} catch(IllegalArgumentException e){
			assertTrue("Wrong message for distance negative", e.getMessage().contains("-1"));
		}  catch(NullPointerException e){
			fail("Name is not null = London");
		}
		
		//Destination distance = 0
		try{
			new Destination("London", -0);
		} catch(NullPointerException e){
			fail("Name is not null = London");
		}  catch(IllegalArgumentException e){
			fail("Distance = 0 is allowed");
		}
		
		// Destination distance > 0
		try{
			new Destination("London", 15);
		} catch(NullPointerException e){
			fail("Name is not null = London");
		}  catch(IllegalArgumentException e){
			fail("Distance > 0 is allowed");
		}
		
	}

	@Test
	public void testEquals(){		
		// Null
		String msg= "Destination should be not identical, argument is null";
		assertFalse(msg,d1.equals(null));
		// Equals
		msg = "Destination should be identical, names are identical";
		assertTrue(msg,d1.equals(d2));
		// Equals
		d2 = new Destination("Liverpool", 15);
		assertTrue(msg,d1.equals(d2));
		// Not equals
		msg = "Destination should not be identical";
		assertFalse(msg,d1.equals(d3));
	}
	
	@Test
	public void testCompare(){
		// Identical
		String msg = "Expect to be 0 for identical";
		assertEquals(msg,0,d1.compare(d1, d2));
		// Before
		msg = "L before M result should be < 0";
		assertTrue(msg,d1.compare(d1, d3) < 0);
		// After
		msg = "M after L result should be > 0";
		assertTrue(msg,d1.compare(d3, d1) > 0);
	}
}
