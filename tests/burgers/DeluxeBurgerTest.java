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
		assertEquals(0, burger.getAdditions().size());
		assertEquals(3.00, till.calculateBurgerPrice(burger), 0.1);
	}
	
	@Test
	public void DeluxeBurgerWithNoDeluxeAdditions(){
		assertEquals(3.00, till.calculateBurgerPrice(burger), 0.1);
	}
	
	@Test
	public void DeluxeBurgerCanAcceptDrink(){
		burger.acceptDeluxeAddition(drink1);
		assertEquals(3.50, till.calculateBurgerPrice(burger), 0.1);
	}
	
	@Test
	public void DeluxeBurgerCanOnlyAcceptSmallDrink(){
		assertEquals("The product cannot be added", burger.acceptDeluxeAddition(drink2));
		burger.acceptDeluxeAddition(drink2);
		assertEquals(3.00, till.calculateBurgerPrice(burger), 0.1);
		burger.acceptDeluxeAddition(drink1);
		assertEquals(3.50, till.calculateBurgerPrice(burger), 0.1);
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
	public void DeluxeBurgerCanStoreChipsAndADrink(){
		till.addDeluxeAddition(burger, drink1);
		till.addDeluxeAddition(burger, chips1);
		assertEquals(2, burger.getDeluxeAdditions().size());
	}
	
	@Test
	public void TillTransafersOnlySmallDrinkToTheDeluxeDeal(){
		till.addDeluxeAddition(burger, drink1);
		till.addDeluxeAddition(burger, drink2);
		assertEquals(1, burger.getDeluxeAdditions().size());
	}
	
	@Test
	public void TillTransafersOnlySmallChipsToTheDeluxeDeal(){
		till.addDeluxeAddition(burger, chips1);
		till.addDeluxeAddition(burger, chips2);
		assertEquals(1, burger.getDeluxeAdditions().size());
	}
}
