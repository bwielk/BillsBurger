package burgers;

import java.math.BigDecimal;
import java.util.*;

public class Till {

	private double income;
	private int soldBurgers;
	private HashMap<Burger, Integer> burgers;
	private int processedTransactions;
	private ArrayList<Voucherable> usedVouchers;
	private HashMap<Productable, Integer> products;
	
	public Till(){
		this.income = 0;
		this.soldBurgers = 0;
		this.burgers = new HashMap<Burger, Integer>();
		this.processedTransactions = 0;
		this.usedVouchers = new ArrayList<Voucherable>();
		this.products = new HashMap<Productable, Integer>();
	}
	
	public int getUsedVouchers() {
		return usedVouchers.size();
	}

	public double getIncome() {
		return income;
	}
	
	public HashMap<Burger, Integer> getBurgers() {
		return burgers;
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
	
	private void useVoucher(Voucherable voucher){
		voucher.validate();
	}
	
	private double calculateBurgerPrice(Burger burger){
		if(burger.getClass() == DeluxeBurger.class){
			DeluxeBurger deluxeBurger = (DeluxeBurger)burger;
			int numOfProducts = deluxeBurger.getDeluxeAdditions().size();
			switch(numOfProducts){
			case 0: return burger.getPrice();
			case 1: return 3.50;
			case 2: return 4.00;
			}
		}
		double totalPrice = 0.0;
		for(Addition addition : burger.getAdditions().keySet()){
			totalPrice += addition.getPrice()*((burger.getAdditions().get(addition)));
		}
		return totalPrice += burger.getPrice();
	}
	
	public void addBurger(Burger burger){
		if(this.burgers.containsKey(burger)){
			this.burgers.put(burger, this.burgers.get(burger)+1);
		}else{
			this.burgers.put(burger, 1);
		}
	}
	
	public void addProduct(Productable product){
		if(this.products.containsKey(product)){
			this.products.put(product, this.products.get(product)+1);
		}else{
			this.products.put(product, 1);
		}
	}
	
	public void newTransaction(){
		this.burgers.clear();
		this.products.clear();
	}
	
	public int getNumberOfItems(){
		int total = 0;
		for(Burger product : this.burgers.keySet()){
			total += (int)this.burgers.get(product);
		}
		return total;
	}
	
	public String removeBurger(Burger burger){
		if(this.burgers.containsKey(burger)){
			if(this.burgers.get(burger) >1){
				this.burgers.put(burger, this.burgers.get(burger)-1);
			}else if(this.burgers.get(burger) == 1){
				this.burgers.remove(burger);
			}
		}else{
			return "No such product on the transaction list";
		}
		return burger.getName() + " has been removed";
	}
	
	public String removeProduct(Productable product){
		if(this.products.containsKey(product)){
			if(this.products.get(product) >1){
				this.products.put(product, this.products.get(product)-1);
			}else if(this.products.get(product) == 1){
				this.products.remove(product);
			}
		}else{
			return "No such product on the transaction list";
		}
		return product.getName() + " has been removed";
	}
	
	public BigDecimal calculateTransaction(){
		BigDecimal value = new BigDecimal("0.0");
		for(Burger burger : this.burgers.keySet()){
			for(int i=0; i<(int)(this.burgers.get(burger)); i++){
				double price = calculateBurgerPrice(burger);
				this.income += (double) price;
				value = value.add(new BigDecimal("" + price + ""));
				this.soldBurgers += 1;
			}
		}
		for(Productable product : this.products.keySet()){
			for(int i=0; i<(int)(this.products.get(product)); i++){
				if(product.getClass() == Chips.class){
					Chips chips = (Chips)product;
					double chipsPrice = calculateChipsPrice(chips);
					this.income += (double) chipsPrice;
					value = value.add(new BigDecimal("" + chipsPrice + ""));
				}else if(product.getClass() == Drink.class){
					double drinkPrice = product.getPrice();
					this.income += (double) drinkPrice;
				}
			}
		}
		return value;
	}
	
	public Receipt completeTransaction(){
		this.processedTransactions += 1;
		Receipt receipt = new Receipt();
		receipt.setTotal("The total transaction is £ " + (new BigDecimal(String.format("%.2f", calculateTransaction()))));
		receipt.setPaid();
		return receipt;
	}
	
	public String completeTransactionWithVoucher(Voucherable voucher){
		for(Burger product : this.burgers.keySet()){
			if(calculateBurgerPrice(product) == calculateBurgerPrice(voucher.getValueEquivalent()) && product.getName() == voucher.getValueEquivalent().getName() && voucher.isValid()){
				removeBurger(product);
				this.processedTransactions += 1;
				useVoucher(voucher);
				this.usedVouchers.add(voucher);
				return "The total transaction is £ " + (new BigDecimal(String.format("%.2f", calculateTransaction())));
			}
		}
		return "Voucher is not valid for any of the products";
	}
	
	private double calculateChipsPrice(Chips chips){
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
