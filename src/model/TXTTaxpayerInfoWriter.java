package model;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TXTTaxpayerInfoWriter implements TaxpayerInfoWriter {

	@Override
	public void write(Taxpayer taxpayer) {
		try {
			String fileName = taxpayer.getTaxIdentificationNumber() + "_INFO.txt";
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
			printWriter.println("Name: " + taxpayer.getFullname());
			printWriter.println("TIN: " + taxpayer.getTaxIdentificationNumber());
			printWriter.println("Status: " + taxpayer.getMaritalStatus());
			printWriter.println("Income: " + taxpayer.getIncome());
			printWriter.println("");
			printWriter.println("Receipts:");
			printWriter.println("");
			for (Receipt receipt : taxpayer.getReceipts().values()) {
				printWriter.println("ReceiptID: " + receipt.getId());
				printWriter.println("Date: " + receipt.getIssueDate().toString());
				printWriter.println("Kind: " + receipt.getKind());
				printWriter.println("Amount: " + receipt.getAmount());
				printWriter.println("Company: " + receipt.getCompany().getName());
				printWriter.println("Country: " + receipt.getCompany().getAddress().getCountry());
				printWriter.println("City: " + receipt.getCompany().getAddress().getCity());
				printWriter.println("Street: " + receipt.getCompany().getAddress().getStreet());
				printWriter.println("Number: " + receipt.getCompany().getAddress().getNumber());
				printWriter.println("");
			}
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
