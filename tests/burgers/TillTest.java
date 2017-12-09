package burgers;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TillTest{
	
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
	
	@Before
	public void before(){
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
	public void tillCanSellBurgers(){
		till1.addBurger(burger1);
		till1.completeTransaction();
		assertEquals(3.00, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillRecordsTheTotalIncome(){
		
		till1.addBurger(burger1);
		till1.addBurger(burger1);
		till1.addBurger(burger1);
		till1.addBurger(burger1);
		till1.completeTransaction();
		assertEquals(12.00, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillRecordsNumberOfSoldBurgers(){
		till1.addBurger(burger1);
		till1.addBurger(burger1);
		till1.completeTransaction();
		assertEquals(2, till1.getSoldBurgers());
		till1.newTransaction();
		till1.addBurger(burger1);
		till1.addBurger(burger1);
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
		//assertEquals("3.80", till1.calculateTransaction());
		till1.addAddition(burger1, Addition.TOMATO);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addBurger(burger1);
		assertEquals(4, burger1.getNumberOfAdditions());
		till1.completeTransaction();
		assertEquals(4.4, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions(){
		till1.newTransaction();
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addBurger(burger1);
		assertEquals("The total transaction is £ 3.80", till1.completeTransaction().getTotal());
		Burger burger1 = new ArrayList<Burger>(till1.getBurgers().keySet()).get(0);
		till1.getBurgers().remove(burger1);
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		assertEquals(4, burger1.getNumberOfAdditions());
		till1.addBurger(burger1);
		assertEquals("The total transaction is £ 4.60", till1.completeTransaction().getTotal());
		assertEquals(8.40, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions2A(){
		till1.newTransaction();
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addBurger(burger1);
		till1.completeTransaction();
		assertEquals(4.0, till1.getIncome(), 0.1);
		Burger burger1 = new ArrayList<Burger>(till1.getBurgers().keySet()).get(0);
		till1.getBurgers().remove(burger1);
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addBurger(burger1);
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(1, till1.getSoldBurgers());
		assertEquals("The total transaction is £ 4.60", till1.completeTransaction().getTotal());
		assertEquals(2, till1.getSoldBurgers());
		assertEquals(8.60, till1.getIncome(), 0.1);
	}
	
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions2B(){
		till1.newTransaction();
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		assertEquals("The total transaction is £ 3.60", till1.completeTransaction());
		Burger burger1 = new ArrayList<Burger>(till1.getBurgers().keySet()).get(0);
		till1.removeBurger(burger1);
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addBurger(burger1);
		till1.completeTransaction();
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals(8.20, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillCanCalculateTheValueOfTheBurgerWithTheSameAdditions3(){
		till1.newTransaction();
		till1.addAddition(burger1, Addition.HALOUMI);//0.5
		till1.addAddition(burger1, Addition.SALAD);//0.3
		till1.addBurger(burger1);
		assertEquals("The total transaction is £ 3.80", till1.completeTransaction().getTotal());
		Burger burger1 = new ArrayList<Burger>(till1.getBurgers().keySet()).get(0);
		till1.getBurgers().remove(burger1);
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addAddition(burger1, Addition.PEPPERS);//0.3
		till1.addBurger(burger1);
		assertEquals(4, burger1.getNumberOfAdditions());
		assertEquals("The total transaction is £ 4.40", till1.completeTransaction().getTotal());
		assertEquals(8.20, till1.getIncome(), 0.1);
	}
	
	@Test
	public void canCalculateIncomeAfterSellingBurgersWithAdditions(){
		till1.newTransaction();
		till1.addBurger(burger1);
		till1.addBurger(burger2);
		till1.completeTransaction();
		assertEquals(7.6, till1.getIncome(), 0.1);
		till1.newTransaction();
		till1.addBurger(burger3);
		till1.addBurger(burger1);
		till1.completeTransaction();
		assertEquals(14.9, till1.getIncome(),0.1);
	}
	
	@Test
	public void canAddItemsToTransaction(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger2);//4.6
		assertEquals(2, till1.getBurgers().size());
	}
	
	@Test
	public void canCalculateTotalItems(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger2);//4.6
		till1.addBurger(burger3);//4.6
		till1.addBurger(burger2);//4.6
		assertEquals(4, till1.getNumberOfItems());
		till1.addBurger(burger3);//4.6
		till1.addBurger(burger2);//4.6
		assertEquals(6, till1.getNumberOfItems());
	}
	
	@Test
	public void canGenerateAndCountProccessedTransactions1(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger2);//4.6
		till1.addBurger(burger2);//4.6
		till1.completeTransaction();
		assertEquals(12.2, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
	}
	
	@Test
	public void canGenerateAndCountProccessedTransactions2(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger2);//4.6
		till1.addBurger(burger3);//4.3
		till1.completeTransaction();
		assertEquals(11.9, till1.getIncome(), 0.1);
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger1);//3
		till1.addBurger(burger1);//3
		till1.completeTransaction();
		assertEquals(20.9, till1.getIncome(), 0.1);
		assertEquals(2, till1.numOfProcessedTransactions());
	}
	
	@Test
	public void canReturnTotalTransactionValue(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger2);//4.6
		till1.addBurger(burger3);//4.3
		assertEquals("The total transaction is £ 11.90", till1.completeTransaction().getTotal());
	}
	
	@Test
	public void canSubstractTotalTransactionValue(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger1);//3
		till1.addBurger(burger2);//4.6
		till1.addBurger(burger3);//4.3
		till1.addBurger(burger3);//4.3
		assertEquals("The total transaction is £ 19.20", till1.completeTransaction().getTotal());
		till1.removeBurger(burger3);
		till1.removeBurger(burger1);
		assertEquals("The total transaction is £ 11.90", till1.completeTransaction().getTotal());
	}
	
	@Test
	public void tillCompletesTransactionWithVoucher1(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger2);//4.6
		till1.addBurger(burger3);//4.3
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(1, till1.getUsedVouchers());
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(8.9, till1.getIncome(), 0.1);
	}
	
	@Test
	public void tillCompletesTransactionWithVoucher2(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.addBurger(burger1);//3
		till1.addBurger(burger1);//3
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(6.0, till1.getIncome(), 0.1);
		assertEquals(1, till1.getUsedVouchers());
	}
	
	@Test
	public void tillDoesntCompleteTransactionWithVoucherIfNoValidBurgerInTransaction(){
		till1.newTransaction();
		till1.addBurger(burger2);//4.6
		till1.addBurger(burger3);//4.3
		assertEquals("The total transaction is £ 8.90", till1.completeTransactionWithVoucher(voucher1).getTotal());
		assertEquals(0, till1.getUsedVouchers());
	}
	
	@Test
	public void tillCompletesTransactionWithVoucherIfBuyingOnlyOneBurger(){
		till1.newTransaction();
		till1.addBurger(burger1);//3
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(0.0, till1.getIncome(), 0.1);
		assertEquals(1, till1.getUsedVouchers());
	}
	
	@Test
	public void tillCanProcessMultipleTimesOfBurgersAtOnce(){
		till1.newTransaction();
		till1.addBurger(burger1);
		till1.addBurger(burger2);
		till1.addBurger(burger3);
		till1.addBurger(fitBurger1);
		till1.addBurger(fitBurger2);
		till1.addBurger(deluxeBurger1);
		till1.addBurger(deluxeBurger2);
		till1.completeTransaction();
		assertEquals(26.80, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(7, till1.getSoldBurgers());
	}
	
	@Test
	public void tillCanProcessMultipleTimesOfBurgersAtOnceWithVoucher(){
		till1.newTransaction();
		till1.addBurger(burger1);
		till1.addBurger(burger2);
		till1.addBurger(burger3);
		till1.addBurger(fitBurger1);
		till1.addBurger(fitBurger2);
		till1.addBurger(deluxeBurger1);
		till1.addBurger(deluxeBurger2);
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(23.80, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(6, till1.getSoldBurgers());
	}
	
	@Test
	public void tillCanSellADrinksAndChipsWithAClassicBurger(){
		till1.newTransaction();
		till1.addBurger(burger1);//3.00
		till1.addBurger(burger1);//3.00
		till1.addBurger(deluxeBurger1);//3.5
		till1.addProduct(drink1);//1
		till1.addProduct(drink2);//1.50
		till1.addProduct(chips2);//1.95
		till1.completeTransaction();
		assertEquals(13.95, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(3, till1.getSoldBurgers());
	}
	
	@Test
	public void tillCanSellADrinksAndChipsWithVariousBurgers(){
		till1.newTransaction();
		till1.addBurger(burger1);//3.00
		till1.addBurger(burger1);//3.00
		till1.addBurger(fitBurger1);//3.6
		till1.addBurger(fitBurger2);//3.8
		till1.addBurger(deluxeBurger1);//3.5
		till1.addBurger(deluxeBurger2);//4.0
		till1.addBurger(deluxeBurger2);//4.0
		till1.addBurger(deluxeBurger2);//4.0
		till1.addProduct(drink1);//1
		till1.addProduct(drink1);//1
		till1.addProduct(drink1);//1
		till1.addProduct(drink2);//1.5
		till1.addProduct(drink2);//1.5
		till1.addProduct(drink2);//1.5
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.completeTransaction();
		assertEquals(44.20, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(8, till1.getSoldBurgers());
	}
	
	@Test
	public void tillCanSellADrinksAndChipsWithVariousBurgersWithVoucher(){
		till1.newTransaction();
		till1.addBurger(burger1);//3.00
		till1.addBurger(burger1);//3.00
		till1.addBurger(fitBurger1);//3.6
		till1.addBurger(fitBurger2);//3.8
		till1.addBurger(deluxeBurger1);//3.5
		till1.addBurger(deluxeBurger2);//4.0
		till1.addBurger(deluxeBurger2);//4.0
		till1.addBurger(deluxeBurger2);//4.0
		till1.addProduct(drink1);//1
		till1.addProduct(drink1);//1
		till1.addProduct(drink1);//1
		till1.addProduct(drink2);//1.5
		till1.addProduct(drink2);//1.5
		till1.addProduct(drink2);//1.5
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(41.20, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(7, till1.getSoldBurgers());
	}
	
	@Test
	public void tillCanKeepUpWithTheActionsOnTheTill(){
		till1.newTransaction();
		till1.addBurger(burger1);//3.00
		till1.addBurger(burger1);//3.00
		till1.addBurger(fitBurger1);//3.6
		till1.addBurger(fitBurger2);//3.8
		till1.addBurger(deluxeBurger1);//3.5
		till1.addBurger(deluxeBurger2);//4.0
		till1.addBurger(deluxeBurger2);//4.0
		till1.addBurger(deluxeBurger2);//4.0
		till1.addProduct(drink1);//1
		till1.addProduct(drink1);//1
		till1.addProduct(drink1);//1
		till1.removeProduct(drink1);//-1
		till1.removeProduct(drink1);//-1
		till1.addProduct(drink2);//1.5
		till1.addProduct(drink2);//1.5
		till1.addProduct(drink2);//1.5
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.removeProduct(chips2);//-1.95
		till1.removeProduct(chips2);//-1.95
		till1.addProduct(chips2);//1.95
		till1.addProduct(chips2);//1.95
		till1.completeTransactionWithVoucher(voucher1);
		assertEquals(35.30, till1.getIncome(), 0.1);
		assertEquals(1, till1.numOfProcessedTransactions());
		assertEquals(7, till1.getSoldBurgers());
	}
	
	@Test
	public void canReleaseAReceipt(){
		Receipt receipt = till1.completeTransaction();
		assertEquals(Receipt.class, receipt.getClass());
	}
}
