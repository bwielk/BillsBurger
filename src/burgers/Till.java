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
	private boolean voucherCanBeUsed;
	
	public Till(){
		this.income = 0;
		this.soldBurgers = 0;
		this.burgers = new HashMap<Burger, Integer>();
		this.processedTransactions = 0;
		this.usedVouchers = new ArrayList<Voucherable>();
		this.products = new HashMap<Productable, Integer>();
		this.voucherCanBeUsed = false;
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
		this.usedVouchers.add(voucher);
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
	
	public void newTransaction(){
		this.burgers.clear();
		this.products.clear();
		this.voucherCanBeUsed = false;
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
	
	private BigDecimal calculateBurgers(){
		BigDecimal value = new BigDecimal("0.0");
		for(Burger burger : this.burgers.keySet()){
			for(int i=0; i<(int)(this.burgers.get(burger)); i++){
				double price = calculateBurgerPrice(burger);
				this.income += (double) price;
				value = value.add(new BigDecimal("" + price + ""));
				this.soldBurgers += 1;
			}
		}
		return value;
	}
	
	private BigDecimal calculateProducts(){
		BigDecimal value = new BigDecimal("0.0");
		for(Productable product : this.products.keySet()){
			for(int i=0; i<(int)(this.products.get(product)); i++){
				if(product.getClass() == Chips.class){
					Chips chips = (Chips)product;
					double chipsPrice = calculateChipsPrice(chips);
					this.income += (double) chipsPrice;
					value = value.add(new BigDecimal("" + chipsPrice + ""));
				}else if(product.getClass() == Drink.class){
					Drink drink = (Drink)product;
					double drinkPrice = drink.getPrice();
					this.income += (double) drinkPrice;
					value = value.add(new BigDecimal("" + drinkPrice + ""));
				}
			}
		}
		return value;
	}
	
	private BigDecimal calculateTransaction(){
		BigDecimal value = new BigDecimal("0.0");
		value = value.add(new BigDecimal("" + calculateBurgers() + ""));
		value = value.add(new BigDecimal("" + calculateProducts() + ""));
		return value;
	}
	
	private double calculateChipsPrice(Chips chips){
		double result = chips.getPrice();
		return result += (chips.getPrice()*(chips.getSize().getPriceProportion()));
	}
	
	private void runVoucherForBurger(Voucherable voucher){
		HashMap<Burger, Integer> temp = new HashMap<Burger, Integer>();
		for(Burger burger : this.burgers.keySet()){
			if(calculateBurgerPrice(burger) == calculateBurgerPrice(voucher.getValueEquivalent()) && burger.getName() == voucher.getValueEquivalent().getName() && voucher.isValid()){
				this.voucherCanBeUsed = true;
				temp.put(burger, this.burgers.get(burger));
				if(temp.get(temp.keySet().toArray()[0]) >1){
					this.burgers.put((Burger)temp.keySet().toArray()[0], this.burgers.get(temp.keySet().toArray()[0])-1);
				}else if(this.burgers.get(temp.keySet().toArray()[0]) == 1){
					this.burgers.put((Burger)temp.keySet().toArray()[0], 0);
				}
			}
		}
	}
	
	public Receipt completeTransaction(){
		this.processedTransactions += 1;
		Receipt receipt = new Receipt();
		receipt.setTotal("The total transaction is £ " + (new BigDecimal(String.format("%.2f", calculateTransaction()))));
		receipt.setPaid();
		return receipt;
	}
	
	public Receipt completeTransactionWithVoucher(Voucherable voucher){
		if(voucher.getValueEquivalent().getClass() == Burger.class){
			runVoucherForBurger(voucher);
			if(this.voucherCanBeUsed){
				useVoucher(voucher);
			}
		}
		return completeTransaction();
	}
}
