package burgers;

public enum Addition {
	
	TOMATO(0.3),
	SALAD(0.3),
	CHEDDAR(0.5),
	MOZZARELLA(0.5),
	PICKLES(0.3),
	PEPPERS(0.3),
	HALOUMI(0.5);
	
	private Double price;
	
	Addition(Double price){
		this.price = price;
	}
	
	public Double getPrice(){
		return this.price;
	}

}
