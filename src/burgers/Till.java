package burgers;

import java.util.*;

public class Till {

	private double income;
	private int soldBurgers;
	private HashMap<Burger, Integer> transaction;
	private int processedTransactions;
	
	public Till(){
		this.income = 0;
		this.soldBurgers = 0;
		this.transaction = new HashMap<Burger, Integer>();
		this.processedTransactions = 0;
	}
	
	public double getIncome() {
		return income;
	}
	
	public HashMap<Burger, Integer> getTransaction() {
		return transaction;
	}

	public int numOfProcessedTransactions() {
		return processedTransactions;
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
	
	public void addProduct(Burger burger){
		if(this.transaction.containsKey(burger)){
			this.transaction.put(burger, this.transaction.get(burger)+1);
		}else{
			this.transaction.put(burger, 1);
		}
	}
	
	public void newTransaction(){
		this.transaction.clear();
	}
	
	public int getNumberOfItems(){
		int total = 0;
		for(Burger product : this.transaction.keySet()){
			total += (int)this.transaction.get(product);
		}
		return total;
	}
	
	public void completeTransaction(){
		for(Burger product : this.transaction.keySet()){
			for(int i=0; i<(int)(this.transaction.get(product)); i++){
				this.income += calculateBurgerPrice(product);
				this.soldBurgers += 1;
			}
		}
		this.processedTransactions += 1;
	}
	
}