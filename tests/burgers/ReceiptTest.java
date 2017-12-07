package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReceiptTest {
	
	private Till till1;
	private Burger burger1;
	private Burger burger2;
	private Burger burger3;
	private FitBurger fitBurger1;
	private FitBurger fitBurger2;
	private DeluxeBurger deluxeBurger1;
	private DeluxeBurger deluxeBurger2;
	private Drink drink1;
	private Drink drink2;
	private Chips chips1;
	private Chips chips2;
	private FreeClassicBurgerVoucher voucher1;
	private Receipt receipt;

	@Before
	public void before(){
		receipt = new Receipt();
		till1 = new Till();
		burger1 = new Burger("Classic Burger", MeatType.BEEF, BreadType.WHOLEMEAL);//3.0
		burger2 = new Burger("Beef brgr", MeatType.BEEF, BreadType.RYE);//4.6
		burger3 = new Burger("Chick brgr", MeatType.CHICKEN, BreadType.WHEAT);//4.3
		fitBurger1 = new FitBurger("Fit beef", MeatType.BEEF);//3.6
		fitBurger2 = new FitBurger("Fit chicken", MeatType.CHICKEN);//3.8
		deluxeBurger1 = new DeluxeBurger("Deluxe beef", MeatType.BEEF, BreadType.RYE);//3.5
		deluxeBurger2 = new DeluxeBurger("Deluxe chicken", MeatType.CHICKEN, BreadType.WHEAT);//4
		drink1 = new Drink("Coca Cola", 1.00, DrinkType.SOFT, DrinkSize.SMALL);
		drink2 = new Drink("Coca Cola Zero", 1.50, DrinkType.SOFT, DrinkSize.MEDIUM);
		chips1 = new Chips("French chips", ChipsSize.SMALL, 1.20);
		chips2 = new Chips("American chips", ChipsSize.MEDIUM, 1.30);
		till1.addAddition(burger2, Addition.HALOUMI);
		till1.addAddition(burger2, Addition.CHEDDAR);
		till1.addAddition(burger2, Addition.SALAD);
		till1.addAddition(burger2, Addition.SALAD);
		till1.addAddition(burger3, Addition.TOMATO);
		till1.addAddition(burger3, Addition.MOZZARELLA);
		till1.addAddition(burger3, Addition.MOZZARELLA);
		till1.addAddition(fitBurger1, Addition.PEPPERS);
		till1.addAddition(fitBurger1, Addition.TOMATO);
		till1.addAddition(fitBurger2, Addition.MOZZARELLA);
		till1.addAddition(fitBurger2, Addition.PICKLES);
		till1.addDeluxeAddition(deluxeBurger1, drink1);
		till1.addDeluxeAddition(deluxeBurger2, drink1);
		till1.addDeluxeAddition(deluxeBurger2, chips1);
		voucher1 = new FreeClassicBurgerVoucher();
	}
	
	@Test
	public void transactionOnReceiptHasNOTBeenPaid(){
		assertEquals(false, receipt.isPaid());
	}
	
	@Test
	public void transactionHasBeenPaid(){
		till1.newTransaction();
		till1.addBurger(new Burger("Beef burger", MeatType.BEEF, BreadType.WHEAT));
		till1.addBurger(new Burger("Pig burger", MeatType.PORK, BreadType.WHEAT));
		till1.addProduct(new Drink("Pepsi", 1.00, DrinkType.SOFT, DrinkSize.SMALL));
		Receipt newReceipt = till1.completeTransaction();
		assertEquals(true, newReceipt.isPaid());
	}
	
	@Test
	public void receiptHoldsTheTotal(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(fitBurger1);//3.6
		till1.addBurger(deluxeBurger2);//4
		assertEquals("The total transaction is £ 10.60", till1.completeTransaction().getTotal());
	}
}
