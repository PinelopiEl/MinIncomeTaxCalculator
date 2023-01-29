package model;

import java.util.HashMap;

import exceptions.WrongReceiptKindException;

public class Taxpayer {
	private String fullName;
	private MaritalStatus status;
	private int taxIdentificationNumber;
	private float income;
	private HashMap<Integer, Receipt> receipts = new HashMap<Integer, Receipt>(0); /// Name changed to receipts

	// Constructors
	protected Taxpayer(String fullname, MaritalStatus status, int taxIdentificationNumber, float income) {
		this.fullName = fullname;
		this.status = status;
		this.taxIdentificationNumber = taxIdentificationNumber;
		this.income = income;
	}

	public void addReceipt(Receipt receipt) {
		receipts.put(receipt.getId(), receipt);
	}

	public void removeReceipt(int receiptId) {
		receipts.remove(receiptId);
	}

	public String getFullname() {
		return fullName;
	}

	public int getTaxIdentificationNumber() {
		return taxIdentificationNumber;
	}

	public float getIncome() {
		return income;
	}

	public MaritalStatus getMaritalStatus() {
		return status;
	}
	
	public HashMap<Integer, Receipt>  getReceipts() {
		return receipts;
	}

}
