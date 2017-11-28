package burgers;

public class FreeClassicBurgerVoucher extends Voucher {
	
	private double voucherValue;
	
	public FreeClassicBurgerVoucher(){
		this.voucherValue = 3.0;
	}
	
	public double getValue(){
		return this.voucherValue;
	}

}
