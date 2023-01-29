package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.IOFormat;
import model.ReceiptCategory;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import java.awt.Color;

public class MainFrame extends JFrame implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel contentPane;
	private JList<String> lstTaxpayers;
	private JButton btnAddTaxpayer;
	private JButton btnRemoveTaxpayer;
	private JButton btnLoadTaxpayer;	
	private JComboBox<IOFormat> cboIOFormat;	
	private JList<String> lstReceipts;
	private JButton btnAddReceipt;
	private JButton btnRemoveReceipt;
	private JTextField txtMaritalStatus;
	private JTextField txtIncome;
	private JTextField txtReceipts;
	private JTextField txtTotalTax;
	private JTextField txtVariationTax;
	private JTextField txtBasicTax;
	private JButton btnCalculate;
	private JButton btnSaveLog;
	private JButton btnSettings;
	private DefaultCategoryDataset barChartDataset;
	private JFreeChart barChart;
	private ChartPanel barChartPanel;
	private DefaultPieDataset pieChartDataset;
	private JFreeChart pieChart;
	private ChartPanel pieChartPanel;
	private JButton btnSaveChanges;
	private JButton btnClose;

	public MainFrame(Controller controller) {
		this.controller = controller;
		initComponents();
		
		btnAddTaxpayer.addActionListener(this);
		btnRemoveTaxpayer.addActionListener(this);
		btnLoadTaxpayer.addActionListener(this);
		cboIOFormat.addActionListener(this);
		btnAddReceipt.addActionListener(this);
		btnRemoveReceipt.addActionListener(this);
		btnCalculate.addActionListener(this);
		btnSaveLog.addActionListener(this);
		btnSettings.addActionListener(this);
		btnSaveChanges.addActionListener(this);		
		btnClose.addActionListener(this);
		lstTaxpayers.addListSelectionListener(this);
		lstReceipts.addListSelectionListener(this);
	}

	private void initComponents() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/resources/TaxCalculator.ico")));
		setTitle("Income Tax Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 789);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTaxpayers = new JLabel("Taxpayers");
		lblTaxpayers.setBounds(10, 11, 615, 14);
		contentPane.add(lblTaxpayers);

		JSeparator sepTaxpayers = new JSeparator();
		sepTaxpayers.setBounds(10, 30, 615, 1);
		contentPane.add(sepTaxpayers);

		JLabel lblTaxpayersHeader = new JLabel(
				String.format("%-9s %-30s %-25s", "TIN", "Taxpayer Name", "Marital Status"));
		lblTaxpayersHeader.setFont(new Font("Monospac821 BT", Font.PLAIN, 11));
		lblTaxpayersHeader.setBounds(10, 36, 515, 14);
		contentPane.add(lblTaxpayersHeader);

		lstTaxpayers = new JList<String>();
		lstTaxpayers.setFont(new Font("Monospac821 BT", Font.PLAIN, 11));
		lstTaxpayers.setBounds(10, 35, 515, 300);
		JScrollPane scpTaxpayers = new JScrollPane(lstTaxpayers);
		scpTaxpayers.setBounds(10, 50, 615, 285);
		contentPane.add(scpTaxpayers);

		btnAddTaxpayer = new JButton("Add");
		btnAddTaxpayer.setEnabled(false);
		btnAddTaxpayer.setBounds(10, 347, 80, 23);
		contentPane.add(btnAddTaxpayer);

		btnRemoveTaxpayer = new JButton("Remove");
		btnRemoveTaxpayer.setEnabled(false);
		btnRemoveTaxpayer.setBounds(100, 347, 80, 23);
		contentPane.add(btnRemoveTaxpayer);
		
		btnLoadTaxpayer = new JButton("Load");
		btnLoadTaxpayer.setBounds(190, 347, 80, 23);
		contentPane.add(btnLoadTaxpayer);
		
		JLabel lblIOFormat = new JLabel("IO Format");
		lblIOFormat.setBounds(488, 351, 68, 14);
		contentPane.add(lblIOFormat);
		
		cboIOFormat = new JComboBox<IOFormat>();
		cboIOFormat.setToolTipText("Pick one of the available formats");
		cboIOFormat.setBounds(557, 346, 68, 23);
		cboIOFormat.addItem(IOFormat.TXT);
		cboIOFormat.addItem(IOFormat.XML);
		contentPane.add(cboIOFormat);	
		
		JLabel lblReceipts = new JLabel("Receipts");
		lblReceipts.setBounds(10, 381, 518, 14);
		contentPane.add(lblReceipts);		
		
		JSeparator sepReceipts = new JSeparator();
		sepReceipts.setBounds(10, 400, 1064, 1);
		contentPane.add(sepReceipts);

		JLabel lblReceiptsHeader = new JLabel(String.format("%-6s %-13s %-10s %8s %-20s %-20s", "ID", "Category",
				"Issued On", "Amount", "Company Name", "Company Address"));
		lblReceiptsHeader.setFont(new Font("Monospac821 BT", Font.PLAIN, 11));
		lblReceiptsHeader.setBackground(Color.LIGHT_GRAY);
		lblReceiptsHeader.setBounds(10, 406, 615, 14);
		contentPane.add(lblReceiptsHeader);
		
		lstReceipts = new JList<String>();
		lstReceipts.setFont(new Font("Monospac821 BT", Font.PLAIN, 11));
		lstReceipts.setBounds(10, 405, 515, 300);
		JScrollPane scpReceipts = new JScrollPane(lstReceipts);
		scpReceipts.setBounds(10, 420, 615, 285);
		contentPane.add(scpReceipts);

		btnAddReceipt = new JButton("Add");
		btnAddReceipt.setEnabled(false);
		btnAddReceipt.setBounds(10, 717, 80, 23);
		contentPane.add(btnAddReceipt);

		btnRemoveReceipt = new JButton("Remove");
		btnRemoveReceipt.setEnabled(false);
		btnRemoveReceipt.setBounds(100, 717, 80, 23);
		contentPane.add(btnRemoveReceipt);

		JLabel lblTaxCalculator = new JLabel("Tax Calculator");
		lblTaxCalculator.setBounds(638, 11, 440, 14);
		contentPane.add(lblTaxCalculator);

		JSeparator sepTaxCalculator = new JSeparator();
		sepTaxCalculator.setBounds(634, 30, 440, 1);
		contentPane.add(sepTaxCalculator);
		JLabel lblMaritalStatus = new JLabel("Status");
		lblMaritalStatus.setBounds(634, 36, 68, 14);
		contentPane.add(lblMaritalStatus);

		JLabel lblReceiptsAmount = new JLabel("Receipts ");
		lblReceiptsAmount.setBounds(634, 98, 68, 14);
		contentPane.add(lblReceiptsAmount);

		txtMaritalStatus = new JTextField();
		txtMaritalStatus.setEditable(false);
		txtMaritalStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		txtMaritalStatus.setBounds(700, 33, 110, 20);
		contentPane.add(txtMaritalStatus);
		txtMaritalStatus.setColumns(10);

		txtIncome = new JTextField();
		txtIncome.setEditable(false);
		txtIncome.setHorizontalAlignment(SwingConstants.TRAILING);
		txtIncome.setColumns(10);
		txtIncome.setBounds(700, 64, 110, 20);
		contentPane.add(txtIncome);

		txtReceipts = new JTextField();
		txtReceipts.setEditable(false);
		txtReceipts.setHorizontalAlignment(SwingConstants.TRAILING);
		txtReceipts.setColumns(10);
		txtReceipts.setBounds(700, 95, 110, 20);
		contentPane.add(txtReceipts);

		txtTotalTax = new JTextField();
		txtTotalTax.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtTotalTax.setEditable(false);
		txtTotalTax.setHorizontalAlignment(SwingConstants.TRAILING);
		txtTotalTax.setColumns(10);
		txtTotalTax.setBounds(700, 316, 110, 20);
		contentPane.add(txtTotalTax);

		txtVariationTax = new JTextField();
		txtVariationTax.setEditable(false);
		txtVariationTax.setHorizontalAlignment(SwingConstants.TRAILING);
		txtVariationTax.setColumns(10);
		txtVariationTax.setBounds(700, 285, 110, 20);
		contentPane.add(txtVariationTax);

		txtBasicTax = new JTextField();
		txtBasicTax.setEditable(false);
		txtBasicTax.setHorizontalAlignment(SwingConstants.TRAILING);
		txtBasicTax.setColumns(10);
		txtBasicTax.setBounds(700, 254, 110, 20);
		contentPane.add(txtBasicTax);

		JLabel lblIncome = new JLabel("Income");
		lblIncome.setBounds(634, 67, 68, 14);
		contentPane.add(lblIncome);

		JLabel lblBasicTax = new JLabel("Basic Tax");
		lblBasicTax.setBounds(634, 257, 68, 14);
		contentPane.add(lblBasicTax);

		JLabel lblVariationTax = new JLabel("Variant Tax");
		lblVariationTax.setBounds(634, 288, 68, 14);
		contentPane.add(lblVariationTax);

		JLabel lblTotalTax = new JLabel("Total Tax");
		lblTotalTax.setBounds(634, 319, 68, 14);
		contentPane.add(lblTotalTax);

		btnCalculate = new JButton("Calculate");
		btnCalculate.setEnabled(false);
		btnCalculate.setMnemonic('l');
		btnCalculate.setBounds(634, 347, 90, 23);
		contentPane.add(btnCalculate);

		btnSaveLog = new JButton("Save Log");
		btnSaveLog.setEnabled(false);
		btnSaveLog.setMnemonic('l');
		btnSaveLog.setBounds(730, 347, 90, 23);
		contentPane.add(btnSaveLog);
		
		btnSettings = new JButton("Settings");
		btnSettings.setMnemonic('S');
		btnSettings.setBounds(984, 347, 90, 23);
		contentPane.add(btnSettings);



		// Initialize Bar Chart
		barChartDataset = new DefaultCategoryDataset();
		barChartDataset.addValue(0, "Tax", "Basic");
		barChartDataset.addValue(0, "Tax", "Variation");
		barChartDataset.addValue(0, "Tax", "Total");
		barChart = ChartFactory.createBarChart("Tax Analysis", "Tax type", "Amount[$]", barChartDataset);
		barChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 15));
		barChart.getCategoryPlot().getDomainAxis().setLabelFont(new Font("Tahoma", Font.PLAIN, 15));
		barChart.getCategoryPlot().getRangeAxis().setLabelFont(new Font("Tahoma", Font.PLAIN, 15));
		barChartPanel = new ChartPanel(barChart);
		barChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		barChartPanel.setBackground(Color.white);
		barChartPanel.setBounds(820, 33, 255, 300);
		contentPane.add(barChartPanel);

		// Initialize Pie Chart
		pieChartDataset = new DefaultPieDataset();
		pieChartDataset.setValue("Entertainment", 0);
		pieChartDataset.setValue("Basic", 0);
		pieChartDataset.setValue("Travel", 0);
		pieChartDataset.setValue("Health", 0);
		pieChartDataset.setValue("Other", 0);
		pieChart = ChartFactory.createPieChart("Profile of Receipts.", pieChartDataset);
		pieChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 15));
		pieChartPanel = new ChartPanel(pieChart);
		pieChartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		pieChartPanel.setBackground(Color.white);
		pieChartPanel.setBounds(634, 405, 440, 300);
		contentPane.add(pieChartPanel);

		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setEnabled(false);
		btnSaveChanges.setMnemonic('h');
		btnSaveChanges.setBounds(864, 717, 120, 23);
		contentPane.add(btnSaveChanges);
		
		btnClose = new JButton("Close");
		btnClose.setMnemonic('C');
		btnClose.setBounds(994, 717, 80, 23);
		contentPane.add(btnClose);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnLoadTaxpayer) {
			controller.handleLoadTaxpayers();
		} else if (source == btnAddTaxpayer) {
			controller.handleShowTaxpayerDialog();
		} else if (source == btnRemoveTaxpayer) {
			controller.handleRemoveTaxpayerButton();
		} else if (source == btnAddReceipt) {
			controller.handleShowReceiptDialog();
		} else if (source == btnRemoveReceipt) {
			controller.handleRemoveReceipt();
		} else if (source == cboIOFormat) {
			controller.handleIOFormatComboBox((IOFormat) cboIOFormat.getSelectedItem());	
		} else if (source == btnCalculate) {
				controller.handleCalculateButton();
		} else if (source == btnSaveLog) {
			controller.handleSaveLogButton();		
		} else if (source == btnSettings) {
			controller.handleShowSettingsDialog();
		} else if (source == btnSaveChanges) {
			controller.handleSaveChangesButton();	
		} else if (source == btnClose) {
			controller.handleMainFrameCloseButton();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object source = e.getSource();
		if (source == lstTaxpayers) {
			controller.handleTaxpayersListValueChanged(lstTaxpayers.getSelectedIndex());
		} else if (source == lstReceipts) {
			controller.handleReceiptsListValueChanged(lstReceipts.getSelectedIndex());
		}
	}

	// ------Functions to be called by the Controller--------
	public void updateTaxPayersList(Vector<String> items) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addAll(items);
		lstTaxpayers.setModel(listModel);
	}

	public void updateReceiptsList(Vector<String> items) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addAll(items);
		lstReceipts.setModel(listModel);
	}

	public void updateTaxFields(String[] results) {
		txtMaritalStatus.setText(results[0]);
		txtIncome.setText(results[1]);
		txtReceipts.setText(results[2]);
		txtBasicTax.setText(results[3]);
		txtVariationTax.setText(results[4]);
		txtTotalTax.setText(results[5]);
	}

	public void enableAddTaxpayerButton(boolean b) {
		btnAddTaxpayer.setEnabled(b);
	}

	public void enableRemoveTaxpayerButton(boolean b) {
		btnRemoveTaxpayer.setEnabled(b);
	}

	public void enableAddReceiptButton(boolean b) {
		btnAddReceipt.setEnabled(b);
	}

	public void enableRemoveReceiptButton(boolean b) {
		btnRemoveReceipt.setEnabled(b);
	}

	public void enableCalculateButton(boolean b) {
		btnCalculate.setEnabled(b);
	}
	public void enableSaveLogButton(boolean b) {
		btnSaveLog.setEnabled(b);
	}
	public void enableSaveChangesButton(boolean b) {
		btnSaveChanges.setEnabled(b);
	}

	public void clearTaxCalculatorFields() {
		txtMaritalStatus.setText("");
		txtIncome.setText("");
		txtReceipts.setText("");
		txtBasicTax.setText("");
		txtVariationTax.setText("");
		txtTotalTax.setText("");
		barChartDataset.clear();
		pieChartDataset.clear();
	}

	public void updateBarChart(String[] calculationResults) {
		barChartDataset.clear();
		barChartDataset.addValue(Double.parseDouble(calculationResults[3]), "Tax", "Basic");
		barChartDataset.addValue(Double.parseDouble(calculationResults[4]), "Tax", "Variation");
		barChartDataset.addValue(Double.parseDouble(calculationResults[5]), "Tax", "Total");

		barChart = ChartFactory.createBarChart("Tax Analysis", "Tax type", "Amount[$]", barChartDataset);
		barChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 15));
	}

	public void updatePieChart(Float[] receiptsProfile) {
		pieChartDataset.clear();
		for (ReceiptCategory e : ReceiptCategory.values()) {
			float amount =receiptsProfile[e.getValue()];
			if(amount>0) {
				pieChartDataset.setValue(e, amount);
			}
		}
		pieChart = ChartFactory.createPieChart("Profile of Receipts.", pieChartDataset);
		pieChart.getTitle().setFont(new Font("Tahoma", Font.PLAIN, 15));
	}

}
