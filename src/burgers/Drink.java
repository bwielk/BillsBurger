package burgers;

public class Drink {
	
	private String name;
	private double price;
	private DrinkType type;
	private DrinkSize size;
	
	public Drink(String name, double price, DrinkType type, DrinkSize size){
		this.name = name;
		this.price = price;
		this.type = type;
		this.size = size;
	}

	public String getName() {
		return name;
	}
	
	public double getPrice(){
		return price;
	}
	
	public DrinkType getType(){
		return type;
	}
	
	public DrinkSize getSize(){
		return size;
	}
}
