package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TillTest{
	
	private Till till1;
	private Burger burger1;
	private Burger burger2;
	private Burger burger3;
	
	@Before
	public void before(){
		till1 = new Till();
		burger1 = new Burger("Beef cheese", MeatType.BEEF, BreadType.WHOLEMEAL);//3.0
		burger2 = new Burger("Beef brgr", MeatType.BEEF, BreadType.RYE);//4.6
		burger3 = new Burger("Chick brgr", MeatType.CHICKEN, BreadType.WHEAT);//4.3
		till1.addAddition(burger2, Addition.HALOUMI);
		till1.addAddition(burger2, Addition.CHEDDAR);
		till1.addAddition(burger2, Addition.SALAD);
		till1.addAddition(burger2, Addition.SALAD);
		till1.addAddition(burger3, Addition.TOMATO);
		till1.addAddition(burger3, Addition.MOZZARELLA);
		till1.addAddition(burger3, Addition.MOZZARELLA);
	}
	

	@Test
	public void tillCanSellBurgers(){
		till1.addProduct(burger1);
		till1.completeTransaction();
		assertEquals(3.00, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillRecordsTheTotalIncome(){
		
		till1.addProduct(burger1);
		till1.addProduct(burger1);
		till1.addProduct(burger1);
		till1.addProduct(burger1);
		till1.completeTransaction();
		assertEquals(12.00, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillRecordsNumberOfSoldBurgers(){
		till1.addProduct(burger1);
		till1.addProduct(burger1);
		till1.completeTransaction();
		assertEquals(2, till1.getSoldBurgers());
		till1.addProduct(burger1);
		till1.addProduct(burger1);
		till1.completeTransaction();
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
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(4.40, till1.calculateBurgerPrice(burger1), 0.1);
	}
	
	@Test
	public void canCalculateIncomeAfterSellingBurgersWithAdditions(){
		till1.newTransaction();
		till1.addProduct(burger1);
		till1.addProduct(burger2);
		till1.completeTransaction();
		assertEquals(7.6, till1.getIncome(), 0.1);
		till1.addProduct(burger3);
		till1.addProduct(burger1);
		till1.completeTransaction();
		assertEquals(14.9, till1.getIncome(),0.1);
	}
	
	@Test
	public void canAddItemsToTransaction(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger2);//4.6
		assertEquals(2, till1.getTransaction().size());
	}
	
	@Test
	public void canCalculateTotalItems(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger2);//4.6
		till1.addProduct(burger3);//4.6
		till1.addProduct(burger2);//4.6
		assertEquals(4, till1.getNumberOfItems());
		till1.addProduct(burger3);//4.6
		till1.addProduct(burger2);//4.6
		assertEquals(6, till1.getNumberOfItems());
	}
	
	@Test
	public void canGenerateAndCountProccessedTransactions1(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger2);//4.6
		till1.addProduct(burger2);//4.6
		till1.completeTransaction();
		assertEquals(12.2, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
	}
	
	@Test
	public void canGenerateAndCountProccessedTransactions2(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger2);//4.6
		till1.addProduct(burger3);//4.3
		till1.completeTransaction();
		assertEquals(11.9, till1.getIncome(), 0.1);
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger1);//3
		till1.addProduct(burger1);//3
		till1.completeTransaction();
		assertEquals(20.9, till1.getIncome(), 0.1);
		assertEquals(2, till1.numOfProcessedTransactions());
	}

}
