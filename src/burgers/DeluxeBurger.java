package burgers;

import java.util.HashMap;

public class DeluxeBurger extends Burger{
	
	private HashMap<Productable, Integer> deluxeAdditions;
	
	public DeluxeBurger(String name, MeatType meat, BreadType bread){
		super(name, meat, bread);
		this.deluxeAdditions = new HashMap<Productable, Integer>();
		disableStoringAdditions();
	}
	
	public HashMap<Productable, Integer> getDeluxeAdditions(){
		return this.deluxeAdditions;
	}
	
	public String acceptDeluxeAddition(Productable product){
		if(product.getClass() == Chips.class){
			Chips chips = (Chips)product;
			if(chips.getSize()== ChipsSize.SMALL){
				getDeluxeAdditions().put(chips, 1);
			}
		}else if(product.getClass() == Drink.class){
			Drink drink = (Drink)product;
			if(drink.getSize()== DrinkSize.SMALL){
				getDeluxeAdditions().put(drink, 1);
			}
		}
		return "The product cannot be added";
	}
}
