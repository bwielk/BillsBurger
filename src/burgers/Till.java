package burgers;

import java.math.BigDecimal;
import java.util.*;

public class Till {

	private double income;
	private int soldBurgers;
	private HashMap<Burger, Integer> transaction;
	private int processedTransactions;
	private ArrayList<Voucherable> usedVouchers;
	
	public Till(){
		this.income = 0;
		this.soldBurgers = 0;
		this.transaction = new HashMap<Burger, Integer>();
		this.processedTransactions = 0;
		this.usedVouchers = new ArrayList<Voucherable>();
	}
	
	public int getUsedVouchers() {
		return usedVouchers.size();
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
	
	public void useVoucher(Voucher voucher){
		voucher.validate();
	}
	
	public double calculateBurgerPrice(Burger burger){
		if(burger.getClass() == DeluxeBurger.class){
			DeluxeBurger deluxeBurger = (DeluxeBurger)burger;
			int numOfProducts = deluxeBurger.getDeluxeAdditions().size();
			switch(numOfProducts){
			case 0: return burger.getBasePrice();
			case 1: return 3.50;
			case 2: return 4.00;
			}
		}
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
	
	public String removeProduct(Burger burger){
		if(this.transaction.containsKey(burger)){
			if(this.transaction.get(burger) >1){
				this.transaction.put(burger, this.transaction.get(burger)-1);
			}else if(this.transaction.get(burger) == 1){
				this.transaction.remove(burger);
			}
		}else{
			return "No such product on the transaction list";
		}
		return burger.getName() + " has been removed";
	}
	
	public BigDecimal calculateTransaction(){
		BigDecimal value = new BigDecimal("0.0");
		for(Burger product : this.transaction.keySet()){
			for(int i=0; i<(int)(this.transaction.get(product)); i++){
				double price = calculateBurgerPrice(product);
				this.income += (double) price;
				value = value.add(new BigDecimal("" + price + ""));
				this.soldBurgers += 1;
			}
		}
		return value;
	}
	
	public String completeTransaction(){
		this.processedTransactions += 1;
		return "The total transaction is � " + (new BigDecimal(String.format("%.2f", calculateTransaction())));
	}
	
	public String completeTransactionWithVoucher(Voucherable voucher){
		for(Burger product : this.transaction.keySet()){
			if(calculateBurgerPrice(product) == calculateBurgerPrice(voucher.getValueEquivalent()) && product.getName() == voucher.getValueEquivalent().getName()){
				removeProduct(product);
				this.processedTransactions += 1;
				this.usedVouchers.add(voucher);
				return "The total transaction is � " + (new BigDecimal(String.format("%.2f", calculateTransaction())));
			}
		}
		return "Voucher is not valid for any of the products";
	}
	
	public double calculateChipsPrice(Chips chips){
		double result = chips.getPrice();
		return result += (chips.getPrice()*chips.getSize().getPriceProportion());
	}
	
	public String addDeluxeAddition(DeluxeBurger burger, Productable product){
		if(burger.getDeluxeAdditions().size() == 0){
			burger.acceptDeluxeAddition(product);
		}else{
			for(Productable item : burger.getDeluxeAdditions().keySet()){
				if(item.getClass() == Chips.class && burger.getDeluxeAdditions().get(item) == 1){
					if(product.getClass() == Drink.class){
						burger.acceptDeluxeAddition(product);
					}
				}
				else if(item.getClass() == Drink.class && burger.getDeluxeAdditions().get(item) == 1){
					if(product.getClass() == Chips.class){
						burger.acceptDeluxeAddition(product);
					}
				}
			}
		}
		return "The Deluxe Deal already contains this item";
	}
}
