package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReceiptTest {
	
	private Receipt receipt;
	private Till till1;

	@Before
	public void before(){
		receipt = new Receipt();
		till1 = new Till();
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
}
