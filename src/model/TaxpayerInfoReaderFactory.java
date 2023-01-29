package model;

public class TaxpayerInfoReaderFactory {
	public TaxpayerInfoReader getTaxpayerInfoReader(IOFormat readerType) {
		switch(readerType) {
		case TXT:
			return new TXTTaxpayerInfoReader();
		case XML:
			return new XMLTaxpayerInfoReader();
		default:
			return null;
		}
	}
}
