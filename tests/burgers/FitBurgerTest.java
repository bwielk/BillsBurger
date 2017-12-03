package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FitBurgerTest{
	
	FitBurger burger;
	FitBurger burger2;
	Till till;
	
	@Before
	public void before(){
		burger = new FitBurger("Fit Chicken", MeatType.CHICKEN);
		burger2 = new FitBurger("Fit Beef", MeatType.BEEF);
		till = new Till();
	}

	@Test
	public void fitBurgerInheritsBasePrice(){
		assertEquals(3.00, burger.getPrice(), 0.1);
	}
	
	@Test
	public void fitBurgerAlwaysHasRyeBread(){
		assertEquals(BreadType.RYE, burger.getBread());
		assertEquals(BreadType.RYE, burger2.getBread());
	}
	
	@Test
	public void fitBurgerCanStore2Additions(){
		till.addAddition(burger2, Addition.PICKLES);
		till.addAddition(burger2, Addition.TOMATO);
		till.addAddition(burger, Addition.HALOUMI);
		till.addAddition(burger, Addition.TOMATO);
		assertEquals(2, burger.getAdditions().size());
		assertEquals(2, burger2.getAdditions().size());
	}
	
	@Test
	public void tillUpdatesThePRiceOFAFitBurgerProperly(){
		till.addAddition(burger2, Addition.TOMATO);
		till.addAddition(burger2, Addition.TOMATO);
		till.addAddition(burger, Addition.HALOUMI);
		till.addAddition(burger, Addition.HALOUMI);
		assertEquals(3.60, till.calculateBurgerPrice(burger2), 0.1);
		assertEquals(4.00, till.calculateBurgerPrice(burger), 0.1);
	}
	
	@Test
	public void fitBurgerCanStoreOnly2Additions(){
		burger.acceptAddition(Addition.TOMATO);
		burger.acceptAddition(Addition.MOZZARELLA);
		assertEquals("This burger can have only 2 additions", burger.acceptAddition(Addition.MOZZARELLA));
		assertEquals(2, burger.getAdditions().size());
	}
	
	

}
