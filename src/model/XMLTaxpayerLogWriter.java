package model;

import java.io.IOException;
import java.io.PrintWriter;

public class XMLTaxpayerLogWriter implements TaxpayerLogWriter {

	@Override
	public void write(String[] data) {
		PrintWriter outputStream;
		try {
			outputStream = new PrintWriter(new java.io.FileWriter(data[1]  + "_LOG.xml"));
			outputStream.println("<Name> " 			+ data[0] + " </Name>");
			outputStream.println("<TIN> "   		+ data[1] + " </AFM>");
			outputStream.println("<Income> " 		+ data[2] + " </Income>");
			outputStream.println("<BasicTax> " 		+ data[3] + " </BasicTax>");
			if (Float.parseFloat(data[4]) > 0) {
				outputStream.println("<TaxIncrease> " + data[4] + " </TaxIncrease>");
			} else {
				outputStream.println("<TaxDecrease> " + data[5] + " </TaxDecrease>");
			}
			outputStream.println("<TotalTax> " 		+ data[5] + " </TotalTax>");
			outputStream.println("<Receipts> " 		+ data[6] + " </Receipts>");
			outputStream.println("<ENTERTAINMENT> " + data[7]+ " </ENTERTAINMENT>");
			outputStream.println("<BASIC> "  		+ data[8] + " </BASIC>");
			outputStream.println("<TRAVEL> " 		+ data[9] + " </TRAVEL>");
			outputStream.println("<HEALTH> " 		+ data[10] + " </HEALTH>");
			outputStream.println("<OTHER> "  		+ data[11] + " </OTHER>");
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
