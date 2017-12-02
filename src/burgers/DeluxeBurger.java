package burgers;

import java.util.HashMap;

public class DeluxeBurger extends Burger{
	
	private HashMap<Productable, Integer> deluxeAdditions;
	
	public DeluxeBurger(String name, MeatType meat, BreadType bread){
		super(name, meat, bread);
		this.deluxeAdditions = new HashMap<Productable, Integer>();
		disableStoringAdditions();
	}
	
	@Override
	public String acceptAddition(Addition addition){
		return "This burger cannot have additions";
	}
	
	public String acceptAddition(Productable product){
		deluxeAdditions.put(product, 1);
		return "The product has been added";
	}
	
	public HashMap<Productable, Integer> getDeluxeAdditions(){
		return this.deluxeAdditions;
	}
}
