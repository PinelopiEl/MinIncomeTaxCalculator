package model;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTTaxpayerLogWriter implements TaxpayerLogWriter {

	@Override
	public void write(String[] data) {
		PrintWriter outputStream;
		try {
			outputStream = new PrintWriter(new java.io.FileWriter(data[1] + "_LOG.txt"));
			outputStream.println("Name: " 					+ data[0]);
			outputStream.println("TIN: " 					+ data[1]);
			outputStream.println("Income: " 				+ data[2]);
			outputStream.println("Basic Tax: " 				+ data[3]);
			if (Float.parseFloat(data[4]) > 0) {
				outputStream.println("Tax Increase: " 		+ data[4]);
			} else {
				outputStream.println("Tax Decrease: " 		+ data[4]);
			}
			outputStream.println("Total Tax: " 				+ data[5]);
			outputStream.println("TotalReceiptsGathered: " 	+ data[6]);
			outputStream.println("ENTERTAINMENT: " 			+ data[7]);
			outputStream.println("BASIC: " 					+ data[8]);
			outputStream.println("TRAVEL: " 				+ data[9]);
			outputStream.println("HEALTH: " 				+ data[10]);
			outputStream.println("OTHER: " 					+ data[11]);
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
