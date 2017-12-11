package burgers;

public class Receipt {
	
	private boolean paid;
	private String total;
	private double totalInDouble;
	
	public Receipt(){
		this.paid = false;
		this.total = "";
		this.totalInDouble = 0.0;
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
	
	public void setTotalInDouble(double number){
		this.totalInDouble = number;
	}
	
	public double getTotalInDouble(){
		return this.totalInDouble;
	}

}
