package burgers;

public class Receipt {
	
	private boolean paid;
	private String total;
	
	public Receipt(){
		this.paid = false;
		this.total = "";
	}
	
	public boolean isPaid(){
		return this.paid;
	}
	
	public void setPaid(){
		this.paid = true;
	}
	
	public void setTotal(String total){
		this.total = total;
	}
	
	public String getTotal(){
		return this.total;
	}

}
