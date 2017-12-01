package burgers;

public class FitBurger extends Burger {
	
	public FitBurger(String name, MeatType meat){
		super(name,meat,BreadType.RYE);
	}
	
	@Override
	public String acceptAddition(Addition addition){
		if(getNumberOfAdditions()<2){
			if(getAdditions().containsKey(addition)){
				getAdditions().put(addition, getAdditions().get(addition)+1);
			}else{
				getAdditions().put(addition, 1);
			}
		}
		return "This burger can have only 2 additions";
	}
}
