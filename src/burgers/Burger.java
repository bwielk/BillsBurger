package burgers;

import java.util.*;

public class Burger {
	
	private boolean canStoreAdditions;
	protected String name;
	protected MeatType meat;
	protected BreadType bread;
	private double basePrice;
	private EnumMap<Addition, Integer> additions;
	
	public Burger(String name, MeatType meat, BreadType bread){
		this.canStoreAdditions = true;
		this.name = name;
		this.meat = meat;
		this.bread = bread;
		this.basePrice = 3.00;
		this.additions = new EnumMap<Addition, Integer>(Addition.class);
	}

	public double getBasePrice() {
		return basePrice;
	}

	public boolean isCanStoreAdditions() {
		return canStoreAdditions;
	}
	
	public void disableStoringAdditions(){
		this.canStoreAdditions = false;
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

	public MeatType getMeat() {
		return meat;
	}

	public void setMeat(MeatType meat) {
		this.meat = meat;
	}
	
	public String acceptAddition(Addition addition){
		if(getNumberOfAdditions()<4 && isCanStoreAdditions()){
			if(additions.containsKey(addition)){
				additions.put(addition, additions.get(addition)+1);
			}else{
				this.additions.put(addition, 1);
			}
		}
		return "This burger can have only 4 additions";
	}
	
	public int getNumberOfAdditions(){
		int result = 0;
		for(Integer number : additions.values()){
			result += (int)number;
		}
		return result;
	}
	
	public EnumMap<Addition, Integer> getAdditions(){
		return this.additions;
	}
	
	public void removeAddition(Addition addition){
		if(additions.containsKey(addition)){
			additions.put(addition, additions.get(addition)-1);
		}else{
			additions.remove(addition);
		}
	}
}
