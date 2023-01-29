package model;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;

public class TaxpayersRegistryWriter {
	String fileName;

	public TaxpayersRegistryWriter(String fileName) {
		this.fileName = fileName;
	}

	public void write(Vector<String> taxpayersRegistry) {
		try {
			PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
			for(String line: taxpayersRegistry) {
				printWriter.println(line);
			}
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
