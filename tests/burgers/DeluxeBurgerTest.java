package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeluxeBurgerTest{
	
	private DeluxeBurger burger;
	private Till till;
	
	@Before
	public void before(){
		burger = new DeluxeBurger("Deluxe Beef", MeatType.BEEF, BreadType.WHEAT);
		till = new Till();
	}
	
	@Test
	public void DeluxeBurgerDoesntStoreStuff(){
		till.addAddition(burger, Addition.HALOUMI);
		till.addAddition(burger, Addition.CHEDDAR);
		assertEquals(0, burger.getAdditions().size());
		assertEquals(3.00, till.calculateBurgerPrice(burger), 0.1);
	}

}
