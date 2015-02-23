package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Class.Destination;
import Class.Journey;
import Class.RegNbFormatException;
import Class.Taxi;

public class TestJourney {
	Taxi t;
	Destination d1 = new Destination ("Manchester",35);
	Destination d2 = new Destination ("Edinburgh", 100);
	Destination d3 = new Destination ("Southampton", 48);
	Destination d4 = new Destination ("Liverpool", 7);
	Destination d5 = new Destination ("Liverpool",29);
	Journey j1,j2,j3;
	
	@Before public void initialize() {
		//For this test, we suppose that the taxis are valid ones
		try {
			t = new Taxi ("Bob", "LON-XFDERT");
			// Creation of journeys 
			j1 = new Journey (t, d1, 6);
			j2 = new Journey (t, d2, 4);
		} catch (RegNbFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testConstructor() {
		// Taxi null
		try{
			new Journey(null, d1, 3);
			fail("Fail detect null taxi");
		} catch(NullPointerException e){
			assertTrue("Wrong message for taxi error", e.getMessage().contains("Taxi"));
		} catch(IllegalArgumentException e){
			fail("Error should not appear on distance");
		}
		
		// Destination null
		try{
			new Journey(t, null, 3);
			fail("Fail detect null destination");
		} catch(NullPointerException e){
			assertTrue("Wrong message for destination error", e.getMessage().contains("destination"));
		} catch(IllegalArgumentException e){
			fail("Error should not appear on distance");
		}
		
		// Invalid nb of passengers
		try{
			new Journey(t, d1, 0);
			fail("Fail detect invalid nb of passengers");
		} catch(NullPointerException e){
			fail("Error should not appear for null values");
		} catch(IllegalArgumentException e){
			assertTrue("Wrong message for passengers error", e.getMessage().contains("0"));
		}
		try{
			new Journey(t, d1, 16);
			fail("Fail detect invalid nb of passengers");
		} catch(NullPointerException e){
			fail("Error should not appear for null values");
		} catch(IllegalArgumentException e){
			assertTrue("Wrong message for passengers error", e.getMessage().contains("16"));
		}
		
		// Valid journey
		try{
			new Journey(t, d1, 3);
		} catch(NullPointerException e){
			fail("Error should not appear for null values");
		}  catch(IllegalArgumentException e){
			fail("Error should not appear on distance");
		}

	}

	@Test
	public void testGetJourneyFee() {
		//The fee of journey 1 should be: 5+(35*0.99)+(1*6)=45.65
		assertEquals("Fee should be = 46.65" , 45.65, j1.getJourneyFee(), 2);
		
		//The fee of journey 2 should be: 5+(100*0.99)+(2*4)=112
		assertEquals("Fee should be = 112" , 112, j2.getJourneyFee(), 2);		
	}
	
	@Test
	public void testCompare(){
		// Identical
		String mess = "Expect to be 0 for identical";
		assertEquals(mess,0,j1.compare(j1, j1));
		// After
		mess = "M after E result should be > 0";
		assertTrue(mess,j1.compare(j1, j2) > 0);
		// Before
		mess = "E before M result should be < 0";
		assertTrue(mess,j1.compare(j2, j1) < 0);
	}
}
