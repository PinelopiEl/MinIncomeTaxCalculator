package model;

public enum ReceiptCategory {
	ENTERTAINMENT(0), 
	BASIC(1), 
	TRAVEL(2), 
	HEALTH(3), 
	OTHER(4);
  
	private int value;
  
	ReceiptCategory(int value){ 
		this.value = value;  
	}
  
	public int getValue(){ 
		return value;
	}
}
