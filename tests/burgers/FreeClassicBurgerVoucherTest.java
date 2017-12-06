package burgers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FreeClassicBurgerVoucherTest{
	
	private FreeClassicBurgerVoucher voucher;
	
	@Before
	public void before(){
		voucher = new FreeClassicBurgerVoucher();
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
		assertEquals(result, burger.getPrice(), 0.1);
	}
}
