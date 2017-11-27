package burgers;

public class Till {
	
	private double income;
	
	public Till(){
		this.income = income;
	}
	
	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public void sell(Burger burger){
		this.income += burger.getBasePrice();
	}
}
