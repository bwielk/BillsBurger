package burgers;

import java.util.*;

public class Burger {
	
	protected String name;
	protected MeatType meat;
	protected BreadType bread;
	protected double basePrice;
	protected HashMap<Addition, Double> additions;
	
	public Burger(String name, MeatType meat, BreadType bread){
		this.name = name;
		this.meat = meat;
		this.bread = bread;
		this.basePrice = 3.00;
		this.additions = new HashMap<Addition, Double>();
	}

	public double getBasePrice() {
		return basePrice;
	}

	public BreadType getBread() {
		return bread;
	}

	public void setBread(BreadType bread) {
		this.bread = bread;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MeatType getMeat() {
		return meat;
	}

	public void setMeat(MeatType meat) {
		this.meat = meat;
	}
	
	public void acceptAddition(Addition addition){
		this.additions.put(addition, addition.getPrice());
	}
	
	public HashMap<Addition, Double> getAdditions(){
		return this.additions;
	}
	
}
