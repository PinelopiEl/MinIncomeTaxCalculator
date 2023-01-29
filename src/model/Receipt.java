package model;

import exceptions.WrongReceiptDateException;
import java.time.LocalDate;

public class Receipt {

	private final int id;
	private final LocalDate issueDate;
	private final float amount;
	private final ReceiptCategory kind;
	private final Company company;

	public Receipt(int id, LocalDate issueDate, float amount, ReceiptCategory kind, Company company){
		this.id = id;
		this.issueDate = issueDate;
		this.amount = amount;
		this.kind = kind;
		this.company = company;
	}
	
	public int getId() {
		return id;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public float getAmount() {
		return amount;
	}

	public ReceiptCategory getKind() {
		return kind;
	}

	public Company getCompany() {
		return company;
	}
}
