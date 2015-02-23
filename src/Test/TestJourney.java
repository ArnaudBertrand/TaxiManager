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
	Destination d1,d2,d3,d4,d5;
	Journey j1,j2,j3;
	
	@Before public void initialize() {
		//For this test, we suppose that the taxis are valid ones
		try {
			t = new Taxi ("Bob", "LON-XFDERT");
			//Destinations
			d1 = new Destination ("Manchester",35);
			d2 = new Destination ("Edinburgh", 100);
			d3 = new Destination ("Southampton", 48);
			d4 = new Destination ("Liverpool",-9);
			d5 = new Destination ("Liverpool",29);
			//Creation of journeys 
			j1 = new Journey (t, d1, 6);
			j2 = new Journey (t, d2, 4);
		} catch (RegNbFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testConstructor() {
		// Test fails with null objects
		try{
			new Journey(null, d1, 3);
			fail("Fail detect null taxi");
		} catch(NullPointerException e){
			assertTrue("Wrong message for taxi error", e.getMessage().contains("Taxi"));
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		try{
			new Journey(t, null, 3);
			fail("Fail detect null destination");
		} catch(NullPointerException e){
			assertTrue("Wrong message for destination error", e.getMessage().contains("destination"));
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		try{
			new Journey(t, d1, 0);
			fail("Fail detect invalid nb of sits");
		} catch(NullPointerException e){
			System.out.println(e.getMessage());
		} catch(IllegalArgumentException e){
			assertTrue("Wrong message for destination error", e.getMessage().contains("0"));
		}
	}

	@Test
	public void testGetJourneyFee() {
		//The fee of journey 1 should be: 5+(35*0.99)+(1*6)=45.65
		assertEquals("error on j1" , 45.65, j1.getJourneyFee(), 2);
		
		//The fee of journey 2 should be: 5+(100*0.99)+(2*4)=112
		assertEquals("error on j2" , 112, j2.getJourneyFee(), 2);		
	}
	
	@Test
	public void testCompare(){
		String mess = "Expect to be 0 for identical";
		assertEquals(mess,0,j1.compare(j1, j1));
		mess = "M after E result should be > 0";
		assertTrue(mess,j1.compare(j1, j2) > 0);
		mess = "E before M result should be < 0";
		assertTrue(mess,j1.compare(j2, j1) < 0);
	}
}
