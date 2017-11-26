import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import burgers.Addition;
import burgers.BreadType;
import burgers.Burger;
import burgers.MeatType;

public class BurgerTest {
	
	private Burger burger;
	
	@Before
	public void before(){
		burger = new Burger("Classic Burger", MeatType.BEEF, BreadType.WHEAT);
	}
	
	@Test
	public void burgerHasName(){
		assertEquals("Classic Burger", burger.getName());
	}
	
	@Test 
	public void burgerHasMeatType(){
		assertEquals(MeatType.BEEF, burger.getMeat());
	}
	
	@Test
	public void burgerHasBreadType(){
		assertEquals(BreadType.WHEAT, burger.getBread());
	}
	
	@Test
	public void burgerHasBasePrice(){
		double result = burger.getBasePrice();
		assertEquals(3.00, result, 0.1);
	}
	
	@Test
	public void burgerCanHaveAnAddition(){
		burger.acceptAddition(Addition.SALAD);
		assertEquals(1, burger.getNumberOfAdditions());
	}
	
	@Test
	public void burgerCanHave4AdditionsOnly(){
		burger.acceptAddition(Addition.SALAD);
		assertEquals(1, burger.getAdditions().size());
		burger.acceptAddition(Addition.CHEDDAR);
		assertEquals(2, burger.getAdditions().size());
		burger.acceptAddition(Addition.PEPPERS);
		assertEquals(3, burger.getAdditions().size());
		burger.acceptAddition(Addition.PICKLES);
		assertEquals(4, burger.getAdditions().size());
		assertEquals("This burger can have only 4 additions", burger.acceptAddition(Addition.PICKLES));
	}
	
	@Test
	public void burgerCanRemoveAnAddition(){
		burger.acceptAddition(Addition.HALOUMI);
		burger.acceptAddition(Addition.CHEDDAR);
		burger.acceptAddition(Addition.SALAD);
		burger.acceptAddition(Addition.PEPPERS);
		burger.removeAddition(Addition.HALOUMI);
		assertEquals(3, burger.getNumberOfAdditions());
	}
	
	@Test 
	public void burgerCanStoreTheSameAdditions(){
		burger.acceptAddition(Addition.HALOUMI);
		burger.acceptAddition(Addition.HALOUMI);
		burger.acceptAddition(Addition.HALOUMI);
		burger.acceptAddition(Addition.PEPPERS);
		assertEquals(4, burger.getNumberOfAdditions());
	}
}
