package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

public class XMLTaxpayerInfoReader implements TaxpayerInfoReader {
	Taxpayer taxpayer;
	String line;
	String fullName;
	BufferedReader inputStream;

	@Override
	public Taxpayer read(String fileName) {
		try {
			inputStream = new BufferedReader(new FileReader(fileName));
			fullName = getValueOfField(inputStream.readLine());
			int taxIdenditicationNumber = Integer.parseInt(getValueOfField(inputStream.readLine()));
			MaritalStatus maritalStatus = MaritalStatus.valueOf(getValueOfField(inputStream.readLine()));
			float income = Float.parseFloat(getValueOfField(inputStream.readLine()));

			taxpayer = new Taxpayer(fullName, maritalStatus, taxIdenditicationNumber, income);
			for (int i = 0; i <= 3; i++) {
				line = inputStream.readLine();
			}
			while (line != null) {
				String values[] = line.split(" ", 3);
				int receiptId = getReceiptId(values);
				LocalDate issueDate = LocalDate.parse(getValueOfField(inputStream.readLine()));
				ReceiptCategory receiptCategory = ReceiptCategory.valueOf(getValueOfField(inputStream.readLine()));
				float amount = Float.parseFloat(getValueOfField(inputStream.readLine()));
				String companyName = getValueOfField(inputStream.readLine());
				String country = getValueOfField(inputStream.readLine());
				String city = getValueOfField(inputStream.readLine());
				String street = getValueOfField(inputStream.readLine());
				String number = getValueOfField(inputStream.readLine());

				Address aAddress = new Address(country, city, street, number);
				Company aCompany = new Company(companyName, aAddress);
				Receipt aReceipt = new Receipt(receiptId, issueDate, amount, receiptCategory, aCompany);
				taxpayer.addReceipt(aReceipt);
				
				line = inputStream.readLine();
				line = inputStream.readLine();
			}
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taxpayer;
	}

	private int getReceiptId(String[] values) {
		 if (values[0].equals("<ReceiptID>")) {
		      int receiptId = Integer.parseInt(values[1].trim());
		      return receiptId;
		    }
		    return -1;
	}

	protected String getValueOfField(String fieldsLine) {
		
		String valueWithTail[] = fieldsLine.split(" ", 2);
	    String valueReversed[] = new StringBuilder(valueWithTail[1]).reverse().toString().trim()
	        .split(" ", 2);
	    return new StringBuilder(valueReversed[1]).reverse().toString();
	}
}