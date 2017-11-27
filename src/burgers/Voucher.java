package burgers;

public class Voucher {
	
	private boolean isValid;
	
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
