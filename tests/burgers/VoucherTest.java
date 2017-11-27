package burgers;

import static org.junit.Assert.*;

import org.junit.*;

import org.junit.Test;

public class VoucherTest extends Voucher {
	
	private Till till1;
	private Till till2;
	private Voucher voucher1;
	private Voucher voucher2;
	
	@Before
	public void before(){
		till1 = new Till();
		till2 = new Till();
		voucher1 = new Voucher();
		voucher2 = new Voucher();
	}
	
	@Test
	public void voucherHasBeenUsed(){
		till1.useVoucher(voucher1);
		assertEquals(false, voucher1.isValid());
	}

}
