package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CashDiscountVoucherTest {

	private CashDiscountVoucher voucher1;
	private Till till;
	private Burger burger1;
	private Burger burger2;
	private FitBurger fitBurger1;
	private DeluxeBurger deluxeBurger1;
	private Chips chips1;
	private Drink drink1;
	
	@Before
	public void before(){
		till = new Till();
		burger1 = new Burger("Classic Burger", MeatType.BEEF, BreadType.WHOLEMEAL);//3.0
		burger1 = new Burger("Beef Burger", MeatType.BEEF, BreadType.WHOLEMEAL);//3.0
		fitBurger1 = new FitBurger("Fit beef", MeatType.BEEF);//3.6
		deluxeBurger1 = new DeluxeBurger("Deluxe beef", MeatType.BEEF, BreadType.RYE);
		drink1 = new Drink("Coca Cola", 1.00, DrinkType.SOFT, DrinkSize.SMALL);
		chips1 = new Chips("French chips", ChipsSize.SMALL, 1.20);
		till.addAddition(burger2, Addition.HALOUMI);
		till.addAddition(burger2, Addition.CHEDDAR);
		till.addAddition(burger2, Addition.SALAD);
		till.addAddition(burger2, Addition.SALAD);
		till.addDeluxeAddition(deluxeBurger1, drink1);
		till.addDeluxeAddition(deluxeBurger1, chips1);
		voucher1 = new CashDiscountVoucher(10.00);
	}

}
