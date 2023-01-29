package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Vector;

public class Model {
	private boolean isDirty;
	private IOFormat ioFormat;
	private int currentTaxpayerIndex;
	private int currentReceiptIndex;
	private Vector<String> taxpayersRegistry;

	private Taxpayer currentTaxpayer;
	private Vector<String> currentTaxpayerReceipts;

	private Settings settings;

	public Model() {
		taxpayersRegistry = new Vector<String>();
		settings = new Settings();
		ioFormat = IOFormat.TXT;
	}

	// Getter and Setters
	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}

	public void setIOFormat(IOFormat ioFormat) {
		this.ioFormat = ioFormat;
	}

	public void setCurrentTaxpayerIndex(int index) {
		currentTaxpayerIndex = index;
		currentTaxpayerReceipts = new Vector<String>();
		if (currentTaxpayerIndex > -1) {
			// Find TIN = taxIdNo
			String taxpayer = taxpayersRegistry.get(currentTaxpayerIndex);
			String[] temp = taxpayer.split(" ");
			int taxIdNo = Integer.parseInt(temp[0]);

			String fileName = taxIdNo + "_INFO." + ioFormat;
			File f = new File(fileName);
			if (f.exists()) {
				TaxpayerInfoReaderFactory taxpayerInfoReaderFactory = new TaxpayerInfoReaderFactory();
				TaxpayerInfoReader txtTaxpayerInfoReader = taxpayerInfoReaderFactory.getTaxpayerInfoReader(ioFormat);
				currentTaxpayer = txtTaxpayerInfoReader.read(fileName);
				for (Receipt aReceipt : currentTaxpayer.getReceipts().values()) {
					currentTaxpayerReceipts.add(String.format("%-6d %-13.13s %-10.10s %8.2f %-20.20s %-20.20s",
							aReceipt.getId(), aReceipt.getKind(), aReceipt.getIssueDate(), aReceipt.getAmount(),
							aReceipt.getCompany().getName(), aReceipt.getCompany().getAddress().toString()));
				}
			}

		} else {
			currentTaxpayerReceipts.add("");
		}
	}

	public int getCurrentTaxpayerIndex() {
		return currentTaxpayerIndex;
	}

	public void setCurrentReceiptIndex(int index) {
		currentReceiptIndex = index;
	}

	public Vector<String> getTaxpayersRegistry() {
		return taxpayersRegistry;
	}

	public Vector<String> getCurrentTaxpayerReceipts() {
		return currentTaxpayerReceipts;
	}

	public void setConfigurationFileName(String configurationFileName) {
		settings.setConfigurationFileName(configurationFileName);
	}

	public String getConfigurationFileName() {
		return settings.getConfigurationFileName();
	}

	public void setDataStoragePath(String dataStoragePath) {
		settings.setDataStoragePath(dataStoragePath);
	}

	public void getDataStoragePath() {
		settings.getDataStoragePath();
	}

	// Functions relevant to Taxpayer Registry
	public Vector<String> readTaxpayersRegistry() {
		TaxpayersRegistryReader taxpayerRegistryReader = new TaxpayersRegistryReader("TaxpayersRegistry.txt");
		taxpayersRegistry = taxpayerRegistryReader.read();
		return taxpayersRegistry;
	}

	public void writeTaxpayersRegistry() {
		TaxpayersRegistryWriter taxpayerRegistryWriter = new TaxpayersRegistryWriter("TaxpayersRegistry.txt");
		taxpayerRegistryWriter.write(taxpayersRegistry);
	}

	// Functions relevant to Taxpayer
	public void addTaxPayer(String name, MaritalStatus maritalStatus, int tIN, float income) {
		currentTaxpayer = new Taxpayer(name, maritalStatus, tIN, income);
		taxpayersRegistry.add(String.format("%-9.9s %-30.30s %-25.25s", tIN, name, maritalStatus.toString()));
		this.setDirty(true);
		writeTaxpayersRegistry();
		// Create INFO file
		storeTaxpayerInfo();
	}

	public void removeCurrentTaxpayer() {
		if (currentTaxpayerIndex > -1) {
			taxpayersRegistry.remove(currentTaxpayerIndex);
			// Re-rite the file TaxpayersRegistry.txt
			writeTaxpayersRegistry();
			// Delete relevant INFO file
			File taxpayersRegistryFile = new File(currentTaxpayer.getTaxIdentificationNumber() + "_INFO.txt");
			taxpayersRegistryFile.delete();
		}
	}

	// Functions relevant to Receipt
	public void addReceipt(String[] input) {
		Address address = new Address(input[5], input[6], input[7], input[8]);
		Company company = new Company(input[4], address);

		try {
			Receipt aReceipt = new Receipt(Integer.parseInt(input[0]), LocalDate.parse(input[1]),
					Float.parseFloat(input[2]), ReceiptCategory.valueOf(input[3]), company);
			int receiptsCount = currentTaxpayer.getReceipts().size();
			currentTaxpayer.addReceipt(aReceipt);
			if (receiptsCount < currentTaxpayer.getReceipts().size()) { // DOuble Receipt
				currentTaxpayerReceipts.add(String.format("%-6.6s %-13.13s %-10.10s %9.2f %-20.20s %-20.20s",
						Integer.toString(aReceipt.getId()), aReceipt.getKind().toString(), aReceipt.getIssueDate(),
						aReceipt.getAmount(), aReceipt.getCompany().getName(),
						aReceipt.getCompany().getAddress().toString()));
				// Save to the relevant file
				storeTaxpayerInfo();
			}
			this.setDirty(true);
		} catch (Exception e) {
			throw e;
		}
	}

	public void removeCurrentReceipt() {
		if (currentReceiptIndex > -1) {
			String currentReceipt = currentTaxpayerReceipts.get(currentReceiptIndex);
			String[] receiptElements = currentReceipt.split(" ", 2);
			int receiptID = Integer.parseInt(receiptElements[0].trim());
			currentTaxpayer.removeReceipt(receiptID);
			currentTaxpayerReceipts.remove(currentReceiptIndex);
			// Save to the relevant file
			storeTaxpayerInfo();
		}
		this.setDirty(true);
	}

	// Functions relevant to storage of Data
	public void storeTaxpayerInfo() {
		TaxpayerInfoWriterFactory taxpayerInfoWriterFactory = new TaxpayerInfoWriterFactory();
		TaxpayerInfoWriter taxpayerInfoWriter = taxpayerInfoWriterFactory.getTaxpayerInfoWriter(ioFormat);
		taxpayerInfoWriter.write(currentTaxpayer);
	}

	public void SaveLogFile() {
		TaxpayerLogWriterFactory taxpayerLogWriterFactory = new TaxpayerLogWriterFactory();
		TaxpayerLogWriter taxpayerLogWriter = taxpayerLogWriterFactory.getTaxpayerLogWriter(ioFormat);
		taxpayerLogWriter.write(packData());
	}

	// Business Logic Functions
	// -------Tax Calculation Functions-----
	private float[][] getBasicTaxCoefficients(MaritalStatus maritalStatus) {
		float[][] basicTaxCoefficients = new float[5][2];
		String fileName = maritalStatus + ".tcf";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			for (int i = 0; i <= 4; i++) {
				String[] s = reader.readLine().split(" ");
				basicTaxCoefficients[i][0] = Float.parseFloat(s[0]);
				basicTaxCoefficients[i][1] = Float.parseFloat(s[1]);
			}
			reader.close();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return basicTaxCoefficients;
	}

	private float[][] getVariantTaxCoefficients() {
		float[][] variantTaxCoefficients = new float[4][2];
		String fileName = "Variant.tcf";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			for (int i = 0; i <= 3; i++) {
				String[] s = reader.readLine().split(" ");
				variantTaxCoefficients[i][0] = Float.parseFloat(s[0]);
				variantTaxCoefficients[i][1] = Float.parseFloat(s[1]);
			}
			reader.close();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		return variantTaxCoefficients;
	}

	public float getBasicTax() {
		float basicTax = 0;

		float income = currentTaxpayer.getIncome();
		float[][] f = getBasicTaxCoefficients(currentTaxpayer.getMaritalStatus());

		for (int i = 0; i <= 3; i++) {
			if (income > f[i + 1][0]) {
				basicTax += (f[i + 1][0] - f[i][0]) * f[i][1];
			} else {
				basicTax += (income - f[i][0]) * f[i][1];
				break;
			}
		}
		if (income > f[4][0]) {
			basicTax += (income - f[4][0]) * f[4][1];
		}
		return basicTax;
	}

	public float getVariationTaxPercentageOnReceipts() {
		float variationTaxPercentage = 0;
		float[][] f = getVariantTaxCoefficients();
		float totalAmountOfReceipts = getTotalAmountOfReceipts();
		float income = currentTaxpayer.getIncome();
		float percentageOfIncome = totalAmountOfReceipts / income;
		for (int i = 0; i <= 3; i++) {
			if (percentageOfIncome >= f[i][0]) {
				variationTaxPercentage = f[i][1];
			} else {
				break;
			}
		}
		return variationTaxPercentage;
	}

	public float getTotalAmountOfReceipts() {
		float sum = 0;
		for (Receipt receipt : currentTaxpayer.getReceipts().values()) {
			sum += receipt.getAmount();
		}
		return sum;
	}

	public float getTotalTax() {
		return (1 + getVariationTaxPercentageOnReceipts()) * getBasicTax();
	}

	public String[] CalculateTax() {
		float basicTax = getBasicTax();
		float variationTax = getVariationTaxPercentageOnReceipts() * basicTax;
		float totalTax = basicTax + variationTax;
		String[] calculationResults = { currentTaxpayer.getMaritalStatus().toString(),
				String.format("%.2f", currentTaxpayer.getIncome()), String.format("%.2f", getTotalAmountOfReceipts()),
				String.format("%.2f", basicTax), String.format("%.2f", variationTax),
				String.format("%.2f", totalTax), };
		return calculationResults;
	}

	public Float[] getReceiptsProfile() {
		Float[] receiptsProfile = { 0f, 0f, 0f, 0f, 0f };
		for (Receipt receipt : currentTaxpayer.getReceipts().values()) {
			receiptsProfile[receipt.getKind().getValue()] += receipt.getAmount();
		}
		return receiptsProfile;
	}

	public String[] packData() {
		Float[] profile = getReceiptsProfile();
		String[] pack = { currentTaxpayer.getFullname(), Integer.toString(currentTaxpayer.getTaxIdentificationNumber()),
				Float.toString(currentTaxpayer.getIncome()), Float.toString(getBasicTax()),
				Float.toString(getVariationTaxPercentageOnReceipts()), Float.toString(getTotalTax()),
				Float.toString(getTotalAmountOfReceipts()), Float.toString(profile[0]), Float.toString(profile[1]),
				Float.toString(profile[2]), Float.toString(profile[3]), Float.toString(profile[4]) };
		return pack;
	}
}
