package burgers;

public interface Voucherable {
	
	void validate();
	boolean isValid();
	double getValue();
	Object getValueEquivalent();

}
