package burgers;

public class Burger {
	
	protected String name;
	protected MeatType meat;
	protected BreadType bread;		
	
	public Burger(String name, MeatType meat, BreadType bread){
		this.name = name;
		this.meat = meat;
		this.bread = bread;
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
	
}
