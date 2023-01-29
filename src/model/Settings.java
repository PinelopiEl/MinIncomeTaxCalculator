package model;

public class Settings {
	private boolean autoCalculation;
	private String dataStoragePath;
	private String configurationFileName;
	
	Settings(){
		//TODO: Read the file Settings.xml
	}

	public String getConfigurationFileName() {
		return configurationFileName;
	}

	public void setConfigurationFileName(String configurationFileName) {
		this.configurationFileName = configurationFileName;
	}

	public String getDataStoragePath() {
		return dataStoragePath;
	}

	public void setDataStoragePath(String dataStoragePath) {
		this.dataStoragePath = dataStoragePath;
	}

	public boolean isAutoCalculation() {
		return autoCalculation;
	}

	public void setAutoCalculation(boolean autoCalculation) {
		this.autoCalculation = autoCalculation;
	}
}
