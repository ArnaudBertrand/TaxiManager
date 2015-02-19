package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Class.Taxi;

public class TestTaxi {

	@Test
	public void testTaxiCompareToAndEquals() {
		
		Taxi taxi1 = new Taxi("Samantha Andrew","LON-000YUU");
		Taxi taxi2 = new Taxi("Jean Charles De Quercusson", "LON-000YUU");
		Taxi taxi3 = new Taxi("Pierre-Paul Dupont","LON-KIL234");
		
		//Test equals method
		String mess = "Taxi should be identical";
		assertTrue(mess,taxi1.equals(taxi2));
		taxi2 = new Taxi("Samantha Andrew", "LON-000YUU");
		assertTrue(mess,taxi1.equals(taxi2));
		mess = "Taxi should not be identical";
		assertFalse(mess,taxi1.equals(taxi3));
		
		Taxi taxi4 = new Taxi("Antoine Barret","LON-000YUU");
		Taxi taxi5 = new Taxi("Antoine Barret", "MAN-ERT657");
		Taxi taxi6 = new Taxi("Paul Dupont","LON-KIL234");
		
		//Test compareTo method
		mess = "Expect to be 0 for identical";
		assertEquals(mess,0,taxi4.compareTo(taxi5));
		mess = "A > P result should be inferior to 0";
		assertTrue(mess,taxi4.compareTo(taxi6) < 0);
		mess = "Expect to be 0 for identical";
		assertEquals(mess,0,taxi4.compareTo(taxi4));
		mess = "P > A result should be superior to 0";
		assertTrue(mess,taxi6.compareTo(taxi4) > 0);
	}

}
