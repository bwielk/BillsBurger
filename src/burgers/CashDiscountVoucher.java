package burgers;

public class CashDiscountVoucher extends Voucher implements Voucherable {

	private double voucherValue;
	
	public CashDiscountVoucher(double value){
		this.voucherValue = value;
	}
	
	public double getValue(){
		return this.voucherValue;
	}

	public Burger getValueEquivalent() {
		return null;
	}
}
