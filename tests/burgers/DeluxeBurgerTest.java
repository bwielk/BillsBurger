package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeluxeBurgerTest{
	
	private DeluxeBurger burger;
	private Till till;
	private Drink drink1;
	
	@Before
	public void before(){
		burger = new DeluxeBurger("Deluxe Beef", MeatType.BEEF, BreadType.WHEAT);
		till = new Till();
		drink1 = new Drink("Coca Cola", 1.00, DrinkType.SOFT, DrinkSize.SMALL);
	}
	
	@Test
	public void DeluxeBurgerDoesntStoreStuff(){
		till.addAddition(burger, Addition.HALOUMI);
		till.addAddition(burger, Addition.CHEDDAR);
		assertEquals(0, burger.getAdditions().size());
		assertEquals(3.00, till.calculateBurgerPrice(burger), 0.1);
	}
	
	@Test
	public void DeluxeBurgerWithNoDeluxeAdditions(){
		assertEquals(3.00, till.calculateBurgerPrice(burger), 0.1);
	}
	
	@Test
	public void DeluxeBurgerCanAcceptDrink(){
		burger.acceptAddition(drink1);
		assertEquals(3.50, till.calculateBurgerPrice(burger), 0.1);
	}
}
