package model;

public enum MaritalStatus {
	SINGLE(0), 
	HEAD_OF_HOUSEHOLD(1), 
	MARRIED_FILING_SEPARATELY(2), 
	MARRIED_FILING_JOINTLY(3);
  
	private int value;
  
	MaritalStatus(int value){ 
		this.value = value;  
	}
  
	public int getValue(){ 
		return value;
	}
}
