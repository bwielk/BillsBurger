package burgers;

public enum Addition {
	
	TOMATO(0.30),
	SALAD(0.30),
	CHEDDAR(0.50),
	MOZZARELLA(0.50),
	PICKLES(0.30),
	PEPPERS(0.30),
	HALOUMI(0.50);
	
	private Double price;
	
	Addition(Double price){
		this.price = price;
	}
	
	public Double getPrice(){
		return this.price;
	}

}
