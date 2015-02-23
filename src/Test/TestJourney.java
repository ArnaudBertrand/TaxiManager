package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import Class.Destination;
import Class.Journey;
import Class.Taxi;

public class TestJourney {
	//For this test, we suppose that the taxis are valid ones
	Taxi t = new Taxi ("Bob", "LON-XFDERT");
	//Destinations
	Destination d1 = new Destination ("Manchester",35);
	Destination d2 = new Destination ("Edinburgh", 100);
	Destination d3 = new Destination ("Southampton", 48);
	Destination d4 = new Destination ("Liverpool",-9);
	Destination d5 = new Destination ("Liverpool",29);
	//Creation of journeys 
	Journey j1 = new Journey (t, d1, 6);
	Journey j2 = new Journey (t, d2, 4);
	Journey j3 = new Journey (t, d3, 7);
	Journey j4 = new Journey (t, d4, 5);
	Journey j5 = new Journey (t, d5, -1);
	
	@Test
	public void testGetJourneyFee() {
		//The fee of journey 1 should be: 5+(35*0.99)+(1*6)=45.65
		assertEquals("error on j1" , 45.65, j1.getJourneyFee(), 2);
		
		//The fee of journey 2 should be: 5+(100*0.99)+(2*4)=112
		assertEquals("error on j2" , 112, j2.getJourneyFee(), 2);
		
		//Problem: number of passenger > 6
		try{
			j3.getJourneyFee();
			fail("error on j3");
		} catch(IllegalArgumentException e){
			assertTrue("error on j3", e.getMessage().contains("passenger"));
		}
		//Problem: distance < 0
		try{
			j4.getJourneyFee();
			fail("error on j4");
		} catch(IllegalArgumentException e){
			assertTrue("error on j3", e.getMessage().contains("distance"));
		}
		
		//Problem: number of passenger < 0
		try{
			j5.getJourneyFee();
			fail("error on j5");
		} catch(IllegalArgumentException e){
			assertTrue("error on j3", e.getMessage().contains("passenger"));
		}
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
