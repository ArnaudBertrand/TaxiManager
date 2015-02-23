package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Class.RegNbFormatException;
import Class.Taxi;

public class TestTaxi {
	Taxi taxi1,taxi2,taxi3,taxi4,taxi5,taxi6;
	
	@Before public void initialize(){
		try {
			taxi1 = new Taxi("Samantha Andrew","LON-000YUU");
			taxi2 = new Taxi("Jean Charles De Quercusson", "LON-000YUU");
			taxi3 = new Taxi("Pierre-Paul Dupont","LON-KIL234");	
			taxi4 = new Taxi("Antoine Barret","LON-000YUU");
			taxi5 = new Taxi("Antoine Barret", "MAN-ERT657");
			taxi6 = new Taxi("Paul Dupont","LON-KIL234");			

		} catch (RegNbFormatException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testConstructor() {
		// Null driver name
		try{
			new Taxi(null,"LON-000YUU");
			fail("Driver null should create null pointer exception");
		} catch(RegNbFormatException e){
			fail(e.getMessage());
		} catch(NullPointerException e){
			assertTrue("Wrong error for null pointer",e.getMessage().contains("Driver name"));
		}
		
		// Null reg number
		try{
			new Taxi("Georgio Mancheto",null);
			fail("Reg nb null should create null pointer exception");
		} catch(RegNbFormatException e){
			fail(e.getMessage());
		} catch(NullPointerException e){
			assertTrue("Wrong error for null pointer",e.getMessage().contains("reg number"));
		}
		
		// Random values
		try{
			new Taxi("Samantha Andrew","LON-000YUU");
			new Taxi("Jean Charles De Quercusson", "LON-000YUU");
			new Taxi("Pierre-Paul Dupont","LON-KIL234");
		} catch(RegNbFormatException e){
			fail(e.getMessage());
		} catch(NullPointerException e){
			fail(e.getMessage());
		}
		
		// Wrong typo reg nb
		try{
			new Taxi("Samantha Andrew","LO9-000YUU");
			fail("Not passing number in 3 first chars");
		} catch(RegNbFormatException e){
			assertTrue("Fail getting correct message", e.getMessage().contains("LO9-000YUU"));
		} catch(NullPointerException e){
			fail(e.getMessage());
		}
		// Wrong typo reg nb
		try{
			new Taxi("Jean Charles De Quercusson", "LONA000YUU");
			fail("Not passing without dash");
		} catch(RegNbFormatException e){
			assertTrue("Fail getting correct message", e.getMessage().contains("LONA000YUU"));
		} catch(NullPointerException e){
			fail(e.getMessage());
		}
		// Wrong typo reg nb
		try{
			new Taxi("Pierre-Paul Dupont","LON-KIL?34");
			fail("Not passing not number or capitalletter");
		} catch(RegNbFormatException e){
			assertTrue("Fail getting correct message", e.getMessage().contains("LON-KIL?34"));
		} catch(NullPointerException e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testTaxiEquals() {
		try{
			// Null
			String mess= "Equals with null should return false";
			assertFalse(mess,taxi1.equals(null));
			
			// Equal
			mess = "Taxi should be identical";
			assertTrue(mess,taxi1.equals(taxi2));
			taxi2 = new Taxi("Samantha Andrew", "LON-000YUU");
			assertTrue(mess,taxi1.equals(taxi2));
			
			// Not equal
			mess = "Taxi should not be identical";
			assertFalse(mess,taxi1.equals(taxi3));
			
		} catch(RegNbFormatException e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testCompareTo(){
		// Equal
		String mess = "Expect to be 0 for identical";
		assertEquals(mess,0,taxi4.compareTo(taxi5));
		mess = "Expect to be 0 for identical";
		assertEquals(mess,0,taxi4.compareTo(taxi4));
		
		// Before
		mess = "A > P result should be inferior to 0";
		assertTrue(mess,taxi4.compareTo(taxi6) < 0);
		
		// After
		mess = "P > A result should be superior to 0";
		assertTrue(mess,taxi6.compareTo(taxi4) > 0);		
	}

}
