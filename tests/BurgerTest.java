import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
}
