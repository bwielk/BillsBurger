package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DrinkTest {
	
	private Drink drink1;
	
	@Before
	public void before(){
		drink1 = new Drink("Pepsi", 1.50, DrinkType.SOFT, DrinkSize.SMALL);
	}

	@Test
	public void drinkHasName(){
		assertEquals("Pepsi", drink1.getName());
	}
	
	@Test
	public void drinkHasType(){
		assertEquals(DrinkType.SOFT, drink1.getType());
	}
	
	@Test
	public void drinkHasPrice(){
		assertEquals(1.50, drink1.getPrice(), 0.1);
	}
	
	@Test
	public void drinkHasSize(){
		assertEquals(DrinkSize.SMALL, drink1.getSize());
	}
}
