package controller;

import java.awt.EventQueue;
import java.io.File;
import java.time.format.DateTimeParseException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.IOFormat;
import model.MaritalStatus;
import model.Model;
import view.AddReceiptDialog;
import view.AddTaxpayerDialog;
import view.MainFrame;
import view.SettingsDialog;

public class Controller {
	private Model model;
	private MainFrame mainFrame;
	private AddTaxpayerDialog addTaxpayerDialog;
	private AddReceiptDialog addReceiptDialog;
	private SettingsDialog settingsDialog;
	
	public Controller(){
		model = new Model();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame = new MainFrame(Controller.this); 					
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};
	
	//----------------------Event Handlers----------------------------------------
	//Handling of MainFrame Events
	public void handleLoadTaxpayers() {
		mainFrame.updateTaxPayersList(model.readTaxpayersRegistry());
		mainFrame.enableAddTaxpayerButton(true);
	}
	
	public void handleTaxpayersListValueChanged(int index) {
		model.setCurrentTaxpayerIndex(index);
		mainFrame.updateReceiptsList(model.getCurrentTaxpayerReceipts());
		mainFrame.clearTaxCalculatorFields();
		if(index >= 0) {
			mainFrame.enableRemoveTaxpayerButton(true);
			mainFrame.enableAddReceiptButton(true);
			mainFrame.enableCalculateButton(true);
			mainFrame.enableSaveChangesButton(true);
			mainFrame.enableSaveLogButton(true);
		}else {
			mainFrame.enableRemoveTaxpayerButton(false);
			mainFrame.enableAddReceiptButton(false);
			mainFrame.enableRemoveReceiptButton(false);
			mainFrame.enableCalculateButton(false);
			mainFrame.enableSaveChangesButton(false);
			mainFrame.enableSaveLogButton(false);
		}
	}
	
	public void handleReceiptsListValueChanged(int index) {
		model.setCurrentReceiptIndex(index);
		if(index >= 0) {
			mainFrame.enableRemoveReceiptButton(true);
		}else {
			mainFrame.enableRemoveReceiptButton(false);
		}
	}
	
	public void handleShowTaxpayerDialog() {
		addTaxpayerDialog = new AddTaxpayerDialog(mainFrame, Controller.this);	
		addTaxpayerDialog.setLocationRelativeTo(mainFrame);
		addTaxpayerDialog.setVisible(true);
	}

	public void handleRemoveTaxpayerButton() {
		int answer = JOptionPane.showConfirmDialog(mainFrame, "The selected taxpayer is going to be deleted!\nPress OK to confirm.", "Removal of Taxpayer", JOptionPane.OK_CANCEL_OPTION);
		if(answer==JOptionPane.OK_OPTION) {
			model.removeCurrentTaxpayer();
			mainFrame.updateTaxPayersList(model.getTaxpayersRegistry());
		}
	}
	
	public void handleShowReceiptDialog() {
		System.out.println("[MainForm]>[Add Receipt] button pressed");
		addReceiptDialog = new AddReceiptDialog(mainFrame, Controller.this);
		addReceiptDialog.setLocationRelativeTo(mainFrame);
		addReceiptDialog.setVisible(true);	
	}
	public void handleRemoveReceipt() {
		int answer = JOptionPane.showConfirmDialog(mainFrame, "The selected receipt is going to be deleted!\nPress OK to confirm.", "Removal of Receipt", JOptionPane.OK_CANCEL_OPTION);
		if(answer==JOptionPane.OK_OPTION) {
			model.removeCurrentReceipt();
			// TODO: Remove receipt from the collection of receipts of the relevant taxpayer collection and recreate the array of strings String[] items
			mainFrame.updateReceiptsList(model.getCurrentTaxpayerReceipts());
			mainFrame.clearTaxCalculatorFields();
		}
	}

	public void handleCalculateButton() {
		String[] calculationResults =model.CalculateTax();
		mainFrame.updateTaxFields(calculationResults);
		mainFrame.updateBarChart(calculationResults);
		mainFrame.updatePieChart(model.getReceiptsProfile());
		mainFrame.enableSaveChangesButton(true);
	}

	public void handleSaveChangesButton() {
		model.storeTaxpayerInfo();
	}

	public void handleShowSettingsDialog() {
		settingsDialog = new SettingsDialog(mainFrame, Controller.this);	
		settingsDialog.setLocationRelativeTo(mainFrame);
		settingsDialog.setVisible(true);
	}
	
	public void handleMainFrameCloseButton() {
		mainFrame.dispose();
	}
	
	//Handling of Add Taxpayer Dialog Events	
	public void handleAddTaxpayerOKButton() {
		String[] tP = addTaxpayerDialog.getEnteredValues();
		
		try {
			model.addTaxPayer(tP[0], MaritalStatus.valueOf(tP[1]), Integer.parseInt(tP[2]), Float.parseFloat(tP[3]));
			mainFrame.updateTaxPayersList(model.getTaxpayersRegistry());
			addTaxpayerDialog.dispose();
		} catch (Exception e) {
			if(e instanceof NumberFormatException){
				JOptionPane.showInternalMessageDialog(null, "The field [TIN] has to be a 9-digits Integer and [Income] a number\nPlease correct and try again.","Numeric Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void handleAddTaxpayerCancelButton() {
		addTaxpayerDialog.dispose();
	}
		
	//Handling of Add Receipt Dialog Events	
	public void handleAddReceiptDialogOKButton() {
		String[] rec = addReceiptDialog.getEnteredValues();
		try {
			model.addReceipt(rec);
			mainFrame.updateReceiptsList(model.getCurrentTaxpayerReceipts());
			mainFrame.clearTaxCalculatorFields();
			addReceiptDialog.dispose();
		} catch (Exception e) {
			if(e instanceof NumberFormatException){
				JOptionPane.showInternalMessageDialog(null, "[ID] has to be an Integer and [Amount] a number\nPlease correct and try again.","Numeric Error Message", JOptionPane.ERROR_MESSAGE);
			}else if (e instanceof DateTimeParseException){
				JOptionPane.showInternalMessageDialog(null, "The field [Issue Date] shall have the format YYYY-MM-DD\nPlease correct and try again.","Numeric Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	public void handleAddReceiptDialogCancelButton() {
		addReceiptDialog.dispose();
	}

	
	//Handling of Settings Dialog Events
	public void handleSettingsOKButton() {
		// TODO Auto-generated method stub
		
	}
	public void handleSettingsCancelButton() {
		settingsDialog.dispose();
	}
	public void handleSettingsConfigurationFileNameButton() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Configuration File");
		int returnValue = fileChooser.showOpenDialog(settingsDialog);
		if(returnValue==JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			model.setConfigurationFileName(file.getAbsolutePath());
			settingsDialog.setConfigurationFileName(file.getAbsolutePath());
		}
	}
	public void handleSettingsSelectDataStoragePathButton() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Data Storage Path");
		fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
		int returnValue = fileChooser.showOpenDialog(settingsDialog);
		if(returnValue==JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			model.setDataStoragePath(file.getPath());
			settingsDialog.setDataStoragePath(file.getPath());
		}
		
	}

	public void handleSaveLogButton() {
		model.SaveLogFile();
	}

	public void handleIOFormatComboBox(IOFormat format) {
		model.setIOFormat(format);
	}

}

