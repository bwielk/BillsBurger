package burgers;

public class DeluxeBurger extends Burger{
	
	public DeluxeBurger(String name, MeatType meat, BreadType bread){
		super(name, meat, bread);
		disableStoringAdditions();
	}
	
	@Override
	public String acceptAddition(Addition addition){
		return "This burger cannot have additions";
	}

}
