package burgers;

import java.util.EnumMap;

public class Till {

	private double income;
	private int soldBurgers;
	
	public Till(){
		this.income = income;
		this.soldBurgers = 0;
	}
	
	public double getIncome() {
		return income;
	}

	public void sell(Burger burger){
		this.income += burger.getBasePrice();
		this.soldBurgers += 1;
	}
	
	public int getSoldBurgers() {
		return soldBurgers;
	}
	
	public void addAddition(Burger burger, Addition addition){
		burger.acceptAddition(addition);
	}
	
	public double calculateBurgerPrice(Burger burger){
		double totalPrice = 0.0;
		for(Addition addition : burger.getAdditions().keySet()){
			totalPrice += addition.getPrice()*((burger.getAdditions().get(addition)));
		}
		return totalPrice += burger.getBasePrice();
	}
}
