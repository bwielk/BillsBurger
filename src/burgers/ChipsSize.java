package burgers;

public enum ChipsSize {
	
	SMALL(0.3),
	MEDIUM(0.5),
	LARGE(0.8);
	
	private double priceProportion;
	
	ChipsSize(double proportion){
		this.priceProportion = proportion;
	}
	
	public double getPriceProportion(){
		return priceProportion;
	}

}
