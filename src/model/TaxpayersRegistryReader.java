package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

public class TaxpayersRegistryReader {
	String fileName;

	TaxpayersRegistryReader(String fileName) {
		this.fileName = fileName;
	}

	public Vector<String> read() {
		Vector<String> taxpayersRegistry = new Vector<String>();
		try {
			BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
			String line = inputStream.readLine();
			while (line != null) {
				taxpayersRegistry.add(line);
				line = inputStream.readLine();
			}
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taxpayersRegistry;
	}
}
