package burgers;

public class Chips implements Productable{
	
	private String name;
	private ChipsSize size;
	private double basePrice;
	
	public Chips(String name, ChipsSize size, double basePrice){
		this.name = name;
		this.size = size;
		this.basePrice = basePrice;
	}
	
	public String getName(){
		return name;
	}
	
	public ChipsSize getSize(){
		return size;
	}
	
	public double getPrice(){
		return basePrice;
	}
	
}
