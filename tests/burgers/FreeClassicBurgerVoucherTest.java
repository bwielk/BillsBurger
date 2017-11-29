package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FreeClassicBurgerVoucherTest{
	
	private FreeClassicBurgerVoucher voucher;
	private Till till;
	
	@Before
	public void before(){
		voucher = new FreeClassicBurgerVoucher();
		till = new Till();
	}

	@Test
	public void canHaveValue(){
		double result = voucher.getValue();
		assertEquals(3.00, result, 0.1);
	}
	
	@Test
	public void canShowWhetherIsStillValid(){
		boolean result = voucher.isValid();
		assertEquals(true, result);
	}
	
	@Test
	public void canGetInvalid(){
		voucher.validate();
		assertEquals(false, voucher.isValid());
	}
	
	@Test
	public void canGetValueEquivalent(){
		double result = voucher.getValue();
		Burger burger = voucher.getValueEquivalent();
		assertEquals(till.calculateBurgerPrice(burger), result, 0.1);
	}
}
