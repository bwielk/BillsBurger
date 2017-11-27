package burgers;

import java.util.*;

public class Till {

	private double income;
	private int soldBurgers;
	private HashMap<Burger, Integer> transaction;
	private int processedTransactions;
	
	public Till(){
		this.income = income;
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

	public int getProcessedTransactions() {
		return processedTransactions;
	}

	
	public int getSoldBurgers() {
		return soldBurgers;
	}
	
	public void sell(Burger burger){
		this.income += calculateBurgerPrice(burger);
		this.soldBurgers += 1;
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
		if(transaction.containsKey(burger)){
			transaction.put(burger, transaction.get(burger)+1);
		}else{
			transaction.put(burger, 1);
		}
	}
	
	public void newTransaction(){
		transaction.clear();
	}
	
	public int getNumberOfItems(){
		int total = 0;
		for(Burger product : transaction.keySet()){
			total += (int)transaction.get(product);
		}
		return total;
	}

}
