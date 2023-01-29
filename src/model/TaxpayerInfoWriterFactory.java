package model;

public class TaxpayerInfoWriterFactory {
	public TaxpayerInfoWriter getTaxpayerInfoWriter(IOFormat writerType) {
		switch(writerType) {
		case TXT:
			return new TXTTaxpayerInfoWriter();
		case XML:
			return new XMLTaxpayerInfoWriter();
		default:
			return null;
		}
	}
}
