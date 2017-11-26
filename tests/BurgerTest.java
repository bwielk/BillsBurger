import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import burgers.Burger;

public class BurgerTest {
	
	private Burger burger;
	
	@Before
	public void before(){
		burger = new Burger("Classic Burger");
	}
	
	@Test
	public void burgerHasName(){
		assertEquals("Classic Burger", burger.getName());
	}
}
