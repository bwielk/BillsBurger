package burgers;

public class Voucher{
	
	protected boolean isValid;
	
	public Voucher(){
		this.isValid = true;
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void validate(){
		this.isValid = false;
	}

}
