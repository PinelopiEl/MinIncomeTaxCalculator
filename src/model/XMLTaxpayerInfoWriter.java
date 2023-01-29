package model;

import java.io.FileWriter;
import java.io.PrintWriter;

public class XMLTaxpayerInfoWriter implements TaxpayerInfoWriter {

	@Override
	public void write(Taxpayer taxpayer) {
		try {
			String fileName = taxpayer.getTaxIdentificationNumber() + "_INFO.xml";
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
			printWriter.println("<Name> "+ taxpayer.getFullname()+ " </Name>");
			printWriter.println("<TIN> " + taxpayer.getTaxIdentificationNumber()+" </TIN>");
			printWriter.println("<Status> " + taxpayer.getMaritalStatus()+" </Status>");
			printWriter.println("<Income> " + taxpayer.getIncome()+" </Income>");
			printWriter.println("");
			printWriter.println("<Receipts>");
			printWriter.println("");
			for (Receipt receipt : taxpayer.getReceipts().values()) {
				printWriter.println("<ReceiptID> "+ receipt.getId()+ " </ReceiptID>");
				printWriter.println("<Date> " + receipt.getIssueDate().toString() + " </Date>");
				printWriter.println("<Kind> " + receipt.getKind() +" </Kind>");
				printWriter.println("<Amount> " + receipt.getAmount()+ " </Amount>");
				printWriter.println("<Company> "  + receipt.getCompany().getName()+ " </Company>");
				printWriter.println("<Country> " + receipt.getCompany().getAddress().getCountry()+ " </Country>");
				printWriter.println("<City> " + receipt.getCompany().getAddress().getCity()+ " </City>");
				printWriter.println("<Street> "+ receipt.getCompany().getAddress().getStreet()+ " </Street>");
				printWriter.println("<Number> " + receipt.getCompany().getAddress().getNumber()+ " </Number>");
				printWriter.println("");
			}
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
