package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Class.RegNbFormatException;
import Class.Taxi;

public class TestTaxi {
	@Test
	public void testConstructor() {
		// Success
		try{
			new Taxi("Samantha Andrew","LON-000YUU");
			new Taxi("Jean Charles De Quercusson", "LON-000YUU");
			new Taxi("Pierre-Paul Dupont","LON-KIL234");
		} catch(RegNbFormatException e){
			System.out.println(e.getMessage());
		}
		
		// Fails
		try{
			new Taxi("Samantha Andrew","LO9-000YUU");
			fail("Not passing number in 3 first chars");
		} catch(RegNbFormatException e){
			assertTrue("Fail getting correct message", e.getMessage().contains("LO9-000YUU"));
		}
		try{
			new Taxi("Jean Charles De Quercusson", "LONA000YUU");
			fail("Not passing without dash");
		} catch(RegNbFormatException e){
			assertTrue("Fail getting correct message", e.getMessage().contains("LONA000YUU"));
		}
		try{
			new Taxi("Pierre-Paul Dupont","LON-KIL?34");
			fail("Not passing not number or capitalletter");
		} catch(RegNbFormatException e){
			assertTrue("Fail getting correct message", e.getMessage().contains("LON-KIL?34"));
		}
	}
	
	@Test
	public void testTaxiCompareToAndEquals() {
		try{
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
		} catch(RegNbFormatException e){
			System.out.println(e.getMessage());
		}
	}

}
