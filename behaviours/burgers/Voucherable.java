package burgers;

public interface Voucherable {
	
	void validate();
	boolean isValid();
	double getValue();
	Burger getValueEquivalent();

}
