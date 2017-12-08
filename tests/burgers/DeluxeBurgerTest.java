package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeluxeBurgerTest{
	
	private DeluxeBurger burger;
	private Till till;
	private Drink drink1;
	private Drink drink2;
	private Chips chips1;
	private Chips chips2;
	
	@Before
	public void before(){
		burger = new DeluxeBurger("Deluxe Beef", MeatType.BEEF, BreadType.WHEAT);
		till = new Till();
		drink1 = new Drink("Coca Cola", 1.00, DrinkType.SOFT, DrinkSize.SMALL);
		drink2 = new Drink("Coca Cola Zero", 1.50, DrinkType.SOFT, DrinkSize.MEDIUM);
		chips1 = new Chips("French chips", ChipsSize.SMALL, 1.20);
		chips2 = new Chips("American chips", ChipsSize.MEDIUM, 1.30);
		}
	
	@Test
	public void DeluxeBurgerDoesntStoreStuff(){
		till.addAddition(burger, Addition.HALOUMI);
		till.addAddition(burger, Addition.CHEDDAR);
		till.addDeluxeAddition(burger, chips1);
		assertEquals(0, burger.getAdditions().size());
		till.addBurger(burger);
		till.completeTransaction();
		assertEquals(3.50, till.getIncome(), 0.1);
	}
	
	@Test
	public void DeluxeBurgerWithNoDeluxeAdditions(){
		till.newTransaction();
		till.addBurger(burger);
		till.completeTransaction();
		assertEquals(3.00, till.getIncome(), 0.1);
	}
	
	@Test
	public void DeluxeBurgerCanAcceptDrink(){
		till.newTransaction();
		burger.acceptDeluxeAddition(drink1);
		till.addBurger(burger);
		assertEquals("The total transaction is £ 3.50", till.completeTransaction().getTotal());
	}
	
	@Test
	public void DeluxeBurgerCanOnlyAcceptSmallDrink(){
		till.newTransaction();
		assertEquals("The product cannot be added", burger.acceptDeluxeAddition(drink2));
		burger.acceptDeluxeAddition(drink2);
		burger.acceptDeluxeAddition(drink1);
		till.addBurger(burger);
		assertEquals("The total transaction is £ 3.50", till.completeTransaction().getTotal());
	}
	
	@Test
	public void DeluxeBurgerCanStoreOnly1Drink(){
		till.addDeluxeAddition(burger, drink1);
		assertEquals("The Deluxe Deal already contains this item", till.addDeluxeAddition(burger, drink1));
	}
	
	@Test
	public void DeluxeBurgerCanStoreOnly1PortionOfChips(){
		till.addDeluxeAddition(burger, chips1);
		assertEquals("The Deluxe Deal already contains this item", till.addDeluxeAddition(burger, chips2));
	}
	
	@Test
	public void DeluxeBurgerCanStoreChipsAndADrinkTestA(){
		till.newTransaction();
		till.addDeluxeAddition(burger, drink1);
		till.addDeluxeAddition(burger, chips1);
		till.addBurger(burger);
		assertEquals("The total transaction is £ 4.00", till.completeTransaction().getTotal());
		assertEquals(2, burger.getDeluxeAdditions().size());
	}
	
	@Test
	public void DeluxeBurgerCanStoreChipsAndADrinkTestB(){
		till.newTransaction();
		till.addDeluxeAddition(burger, chips1);
		till.addDeluxeAddition(burger, drink1);
		till.addBurger(burger);
		till.completeTransaction();
		assertEquals(4.00, till.getIncome(), 0.1);
		assertEquals(1, till.getSoldBurgers());
		assertEquals(2, burger.getDeluxeAdditions().size());
	}
	
	
	@Test
	public void TillTransafersOnlySmallDrinkToTheDeluxeDeal(){
		till.newTransaction();
		till.addDeluxeAddition(burger, drink1);
		till.addDeluxeAddition(burger, drink2);
		till.addBurger(burger);
		assertEquals("The total transaction is £ 3.50", till.completeTransaction().getTotal());
		assertEquals(3.50, till.getIncome(), 0.1);
		assertEquals(1, burger.getDeluxeAdditions().size());
	}
	
	@Test
	public void TillTransafersOnlySmallChipsToTheDeluxeDeal(){
		till.newTransaction();
		till.addDeluxeAddition(burger, chips1);
		till.addDeluxeAddition(burger, chips2);
		till.addBurger(burger);
		till.completeTransaction();
		assertEquals(3.50, till.getIncome(), 0.1);
		assertEquals(1, burger.getDeluxeAdditions().size());
	}
}
