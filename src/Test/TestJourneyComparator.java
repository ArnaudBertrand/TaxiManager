package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Class.Destination;
import Class.Journey;
import Class.JourneyComparator;
import Class.RegNbFormatException;
import Class.Taxi;

public class TestJourneyComparator {
	
	JourneyComparator JC = new JourneyComparator();
	Taxi t;
	Destination d1,d2,d3;
	Journey j1,j2,j3;
	
	@Before public void initialize() {
		try {
			//For this test, we suppose that the taxis are valid ones
			t = new Taxi ("Bob", "LON-XFDERT");
			//Destinations
			d1 = new Destination ("Manchester",35);
			d2 = new Destination ("Edinburgh", 100);
			d3 = new Destination ("Southampton", 35);
		
			//Creation of journeys 
			//Fee for j1: 5+(35*0.99)+(1*6)=45.65
			j1 = new Journey (t, d1, 6);
			//Fee for j2: 5+(100*0.99)+(2*4)=112 
			j2 = new Journey (t, d2, 4);
			//Fee for j3: 5+(35*0.99)+(1*6)=45.65
			j3 = new Journey (t, d3, 6);
		} catch (RegNbFormatException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCompareJourneysFee1() {
		String mess = "Expect to be 0 for identical";
		assertEquals(mess, 0, JC.compare(j1, j3));
	}
	
	@Test
	public void testCompareJourneysFee2() {
		String mess = "fee j1 < fee j2 => result should be 1";
		assertEquals(mess, 1, JC.compare(j1, j2));
	}
	
	@Test
	public void testCompareJourneysFee3() {
		String mess = "fee j2 > fee j3 => result should be -1";
		assertEquals(mess, -1, JC.compare(j2, j3));
	}

}
