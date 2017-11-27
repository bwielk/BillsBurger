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
	
	@Test
	public void tillRecordsTheTotalIncome(){
		till1.sell(burger1);
		till1.sell(burger1);
		till1.sell(burger1);
		till1.sell(burger1);
		assertEquals(12.00, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillRecordsNumberOfSoldBurgers(){
		till1.sell(burger1);
		till1.sell(burger1);
		assertEquals(2, till1.getSoldBurgers());
		till1.sell(burger1);
		till1.sell(burger1);
		assertEquals(4, till1.getSoldBurgers());
	}
	
	@Test
	public void tillCanAddAdditionsToABurger(){
		till1.addAddition(burger1, Addition.CHEDDAR);
		till1.addAddition(burger1, Addition.SALAD);
		till1.addAddition(burger1, Addition.PEPPERS);
		till1.addAddition(burger1, Addition.CHEDDAR);
		assertEquals(4, burger1.getNumberOfAdditions());
	}
	
	@Test
	public void tillCanCalculateTheValueOfTheBurger(){
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.SALAD);//0.3
		assertEquals(3.80, till1.calculateBurgerPrice(burger1), 0.1);
		till1.addAddition(burger1, Addition.TOMATO);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(4.40, till1.calculateBurgerPrice(burger1), 0.1);
	}
	
	@Test
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions(){
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		assertEquals(3.80, till1.calculateBurgerPrice(burger1), 0.1);
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(4.60, till1.calculateBurgerPrice(burger1), 0.1);
	}
	
	@Test
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions2A(){
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		assertEquals(4.0, till1.calculateBurgerPrice(burger1), 0.1);
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(4.60, till1.calculateBurgerPrice(burger1), 0.1);
	}
	
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions2B(){
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		assertEquals(3.6, till1.calculateBurgerPrice(burger1), 0.1);
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(4.60, till1.calculateBurgerPrice(burger1), 0.1);
	}
	
	@Test
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions3(){
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.SALAD);//0.3
		assertEquals(3.80, till1.calculateBurgerPrice(burger1), 0.1);
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		System.out.println(burger1.getAdditions());
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(4.40, till1.calculateBurgerPrice(burger1), 0.1);
	}

}
