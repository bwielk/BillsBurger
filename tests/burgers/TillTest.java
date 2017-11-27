package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TillTest{
	
	private Till till1;
	private Burger burger1;
	
	@Before
	public void before(){
		till1 = new Till();
		burger1 = new Burger("Beef cheese", MeatType.BEEF, BreadType.WHOLEMEAL);
	}
	

	@Test
	public void tillCanSellBurgers(){
		till1.sell(burger1);
		assertEquals(3.00, till1.getIncome(), 0.1);
	}

}
