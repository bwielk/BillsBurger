package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TillTest{
	
	private Till till1;
	private Burger burger1;
	private Burger burger2;
	private Burger burger3;
	private FreeClassicBurgerVoucher voucher1;
	
	@Before
	public void before(){
		till1 = new Till();
		burger1 = new Burger("Classic Burger", MeatType.BEEF, BreadType.WHOLEMEAL);//3.0
		burger2 = new Burger("Beef brgr", MeatType.BEEF, BreadType.RYE);//4.6
		burger3 = new Burger("Chick brgr", MeatType.CHICKEN, BreadType.WHEAT);//4.3
		till1.addAddition(burger2, Addition.HALOUMI);
		till1.addAddition(burger2, Addition.CHEDDAR);
		till1.addAddition(burger2, Addition.SALAD);
		till1.addAddition(burger2, Addition.SALAD);
		till1.addAddition(burger3, Addition.TOMATO);
		till1.addAddition(burger3, Addition.MOZZARELLA);
		till1.addAddition(burger3, Addition.MOZZARELLA);
		voucher1 = new FreeClassicBurgerVoucher();
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
		till1.newTransaction();
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
		till1.newTransaction();
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
		assertEquals(2, till1.getBurgers().size());
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
	
	@Test
	public void canReturnTotalTransactionValue(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger2);//4.6
		till1.addProduct(burger3);//4.3
		assertEquals("The total transaction is £ 11.90", till1.completeTransaction());
	}
	
	@Test
	public void canSubstractTotalTransactionValue(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger1);//3
		till1.addProduct(burger2);//4.6
		till1.addProduct(burger3);//4.3
		till1.addProduct(burger3);//4.3
		assertEquals("The total transaction is £ 19.20", till1.completeTransaction());
		till1.removeBurger(burger3);
		till1.removeBurger(burger1);
		assertEquals("The total transaction is £ 11.90", till1.completeTransaction());
	}
	
	@Test
	public void tillCompletesTransactionWithVoucher1(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger2);//4.6
		till1.addProduct(burger3);//4.3
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(1, till1.getUsedVouchers());
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(8.9, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillCompletesTransactionWithVoucher2(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.addProduct(burger1);//3
		till1.addProduct(burger1);//3
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(6.0, till1.getIncome(), 0.1);
		assertEquals(1, till1.getUsedVouchers());
	}
	
	@Test
	public void tillDoesntCompleteTransactionWithVoucherIfNoValidBurgerInTransaction(){
		till1.newTransaction();
		till1.addProduct(burger2);//4.6
		till1.addProduct(burger3);//4.3
		assertEquals("Voucher is not valid for any of the products", till1.completeTransactionWithVoucher(voucher1));
		assertEquals(0, till1.getUsedVouchers());
	}
	
	@Test
	public void tillCompletesTransactionWithVoucherIfBuyingOnlyOneBurger(){
		till1.newTransaction();
		till1.addProduct(burger1);//3
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(0.0, till1.getIncome(), 0.1);
		assertEquals(1, till1.getUsedVouchers());
	}
}
