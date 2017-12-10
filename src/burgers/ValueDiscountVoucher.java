package burgers;

public class ValueDiscountVoucher extends Voucher implements Voucherable {

	private double voucherValue;
	
	public ValueDiscountVoucher(double value){
		this.voucherValue = value;
	}
	
	public double getValue(){
		return this.voucherValue;
	}
	
	public Burger getValueEquivalent() {
		return null;
	}		
}
