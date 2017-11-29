package burgers;

public class FreeClassicBurgerVoucher extends Voucher implements Voucherable{
	
	private double voucherValue;
	private Burger valueEquivalent;
	
	public FreeClassicBurgerVoucher(){
		this.voucherValue = 3.0;
		this.valueEquivalent = setValueEquivalent();
	}
	
	public double getValue(){
		return this.voucherValue;
	}
	
	public Burger setValueEquivalent(){
		Burger burger = new Burger("Classic Burger", MeatType.CHICKEN, BreadType.WHEAT);
		return burger;
	}
	
	public Burger getValueEquivalent(){
		return this.valueEquivalent;
	}

}
