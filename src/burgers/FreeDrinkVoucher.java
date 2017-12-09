package burgers;

public class FreeDrinkVoucher extends Voucher implements Voucherable{
	
	private double voucherValue;
	private Drink drink;
	
	public FreeDrinkVoucher(){
		this.voucherValue = setValue();
	}
	
	private double setValue(){
		return 1.00;
	}

	public double getValue() {
		return this.voucherValue;
	}

	public Burger getValueEquivalent() {
		return null;
	}

}
