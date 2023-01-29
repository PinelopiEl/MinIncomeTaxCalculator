package model;

public class TaxpayerLogWriterFactory {
	public TaxpayerLogWriter getTaxpayerLogWriter(IOFormat ioFormat) {
		switch(ioFormat) {
		case TXT:
			return new TXTTaxpayerLogWriter();
		case XML:
			return new XMLTaxpayerLogWriter();
		default:
			return null;
		}
	}
}
