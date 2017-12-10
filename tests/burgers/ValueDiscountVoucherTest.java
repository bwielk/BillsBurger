package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValueDiscountVoucherTest {

	private ValueDiscountVoucher voucher1;
	private Till till;
	
	@Before
	public void before(){
		till = new Till();
		voucher1 = new ValueDiscountVoucher(10.00);
	}
	
	@Test
	public void voucherHasValue(){
		assertEquals(10.00, voucher1.getValue(), 0.1);
	}
	
	@Test
	public void vocherCanBeValidated(){
		assertEquals(true, voucher1.isValid());
		till.newTransaction();
		till.addBurger(new Burger("Beef burger", MeatType.BEEF, BreadType.SESAME));
		till.addBurger(new Burger("Beef burger", MeatType.BEEF, BreadType.SESAME));
		till.addBurger(new Burger("Beef burger", MeatType.BEEF, BreadType.SESAME));
		till.addBurger(new Burger("Beef burger", MeatType.BEEF, BreadType.SESAME));
		till.completeTransactionWithVoucher(voucher1);
		assertEquals(false, voucher1.isValid());
	}

}
