package burgers;

import static org.junit.Assert.*;

import org.junit.*;

import org.junit.Test;

public class VoucherTest extends Voucher {
	
	private Till till1;
	private FreeClassicBurgerVoucher voucher;
	private Burger burger1;
	private Burger burger2;
	
	@Before
	public void before(){
		till1 = new Till();
		voucher = new FreeClassicBurgerVoucher();
		burger1 = new Burger("Classic Burger", MeatType.BEEF, BreadType.WHOLEMEAL);//3.0
		burger2 = new Burger("Beef brgr", MeatType.BEEF, BreadType.RYE);//4.6
	}
	
	@Test
	public void voucherHasBeenUsed(){
		till1.newTransaction();
		till1.addBurger(burger1);
		till1.addBurger(burger2);
		till1.completeTransactionWithVoucher(voucher);
		assertEquals(false, voucher.isValid());
	}

}
