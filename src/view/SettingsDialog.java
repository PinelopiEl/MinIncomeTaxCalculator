package view;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.JSeparator;

public class SettingsDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblBasicRatesOfSingle;
	private JTable tblBasicRatesOfHeadOfHouseHold;
	private JTable tblBasicRatesOfMarriedFilingSeparately;
	private JTable tblBasicRatesOfMarriedFilingJointly;
	private JTable tblVariationTaxRates;
	private JTextField txtConfigurationFileName;
	private JTextField txtDataStoragePath;
	private JButton btnSelectConfigurationFileName;
	private JButton btnSelectDataStoragePath;
	private JCheckBox chbAutoCalculate;
	private JButton btnOK;
	private JButton btnCancel;	
	
	private JFrame parentFrame;
	private Controller controller;
			
	
	public SettingsDialog(JFrame parentFrame, Controller controller) {
		super(parentFrame, true);
		this.controller = controller;
		this.parentFrame = parentFrame;
		initComponents();
		
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
		btnSelectConfigurationFileName.addActionListener(this);
		btnSelectDataStoragePath.addActionListener(this);
	}

	@SuppressWarnings("serial")
	private void initComponents() {
		setTitle("Settings");
		setName("frmASettings");	
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		setBounds(100, 100, 610, 620);
		
		contentPane = new JPanel();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		//Basic tax rates for a taxpayer with Single marital status		
		JLabel lblBasicTaxRates = new JLabel("Basic Tax Rates per Marital Status and Income Band");
		lblBasicTaxRates.setBounds(10, 11, 569, 14);
		contentPane.add(lblBasicTaxRates);
		JSeparator sprBasicTax = new JSeparator();
		sprBasicTax.setBounds(10, 30, 567, 2);
		contentPane.add(sprBasicTax);

		JLabel lblSingle = new JLabel("Single");
		lblSingle.setBackground(new Color(0, 255, 255));
		lblSingle.setBounds(10, 36, 270, 14);
		contentPane.add(lblSingle);
		
		tblBasicRatesOfSingle = new JTable();
		tblBasicRatesOfSingle.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblBasicRatesOfSingle.setIntercellSpacing(new Dimension(5, 5));
		tblBasicRatesOfSingle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBasicRatesOfSingle.setModel(new DefaultTableModel(
			new Object[][] {
				{"Low Rate", 		Integer.valueOf(0), 	Integer.valueOf(24680),  Float.valueOf(5.35f)},
				{"Basic Rate",  	Integer.valueOf(24680), Integer.valueOf(81080),  Float.valueOf(7.05f)},
				{"Medium Rate", 	Integer.valueOf(81080), Integer.valueOf(90000),  Float.valueOf(7.85f)},
				{"High Rate",  		Integer.valueOf(90000), Integer.valueOf(152540), Float.valueOf(7.85f)},
				{"Additional Rate", Integer.valueOf(152540),null,  					 Float.valueOf(9.85f)},
			},
			new String[] { "Band", ">=", "<", "Tax Rate %"}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblBasicRatesOfSingle.getColumnModel().getColumn(0).setPreferredWidth(85);
		tblBasicRatesOfSingle.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblBasicRatesOfSingle.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblBasicRatesOfSingle.getColumnModel().getColumn(3).setPreferredWidth(65);
		tblBasicRatesOfSingle.setBounds(10, 60, 280, 145);
		JScrollPane scpBasicRatesOfSingle = new JScrollPane(tblBasicRatesOfSingle);
		scpBasicRatesOfSingle.setLocation(10, 50);
		scpBasicRatesOfSingle.setSize(275, 107);
		contentPane.add(scpBasicRatesOfSingle);
		
		//Basic tax rates for a taxpayer with Head of Household marital status
		JLabel lblHeadOfHousehold = new JLabel("Head of Houshold");
		lblHeadOfHousehold.setBounds(304, 36, 270, 14);
		contentPane.add(lblHeadOfHousehold);

		tblBasicRatesOfHeadOfHouseHold = new JTable();
		tblBasicRatesOfHeadOfHouseHold.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblBasicRatesOfHeadOfHouseHold.setIntercellSpacing(new Dimension(5, 5));
		tblBasicRatesOfHeadOfHouseHold.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBasicRatesOfHeadOfHouseHold.setModel(new DefaultTableModel(
			new Object[][] {
				{"Low Rate", 		Integer.valueOf(0), 		Integer.valueOf(30390), 	Double.valueOf(5.35)},
				{"Basic Rate", 		Integer.valueOf(30390), 	Integer.valueOf(90000), 	Double.valueOf(7.05)},
				{"Medium Rate", 	Integer.valueOf(90000), 	Integer.valueOf(122110), 	Double.valueOf(7.05)},
				{"High Rate", 		Integer.valueOf(122110), 	Integer.valueOf(203390), 	Double.valueOf(7.85)},
				{"Additional Rate", Integer.valueOf(203390), 	null, 						Double.valueOf(9.85)},
			},
			new String[] { "Band", ">=", "<", "Tax Rate %"	}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Float.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tblBasicRatesOfHeadOfHouseHold.getColumnModel().getColumn(0).setPreferredWidth(85);
		tblBasicRatesOfHeadOfHouseHold.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblBasicRatesOfHeadOfHouseHold.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblBasicRatesOfHeadOfHouseHold.getColumnModel().getColumn(3).setPreferredWidth(65);
		tblBasicRatesOfHeadOfHouseHold.setBounds(10, 60, 280, 145);
		JScrollPane scpBasicRatesOfHeadOfHouseHold= new JScrollPane(tblBasicRatesOfHeadOfHouseHold);
		scpBasicRatesOfHeadOfHouseHold.setLocation(304, 50);
		scpBasicRatesOfHeadOfHouseHold.setSize(275, 107);
		contentPane.add(scpBasicRatesOfHeadOfHouseHold);
		
		//Basic tax rates for a taxpayer with Married Filing Separately marital status
		JLabel lblNMarriedFilingSeparately = new JLabel("Married Filing Separately");
		lblNMarriedFilingSeparately.setBounds(304, 166, 270, 14);
		contentPane.add(lblNMarriedFilingSeparately);
		
		tblBasicRatesOfMarriedFilingSeparately = new JTable();
		tblBasicRatesOfMarriedFilingSeparately.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblBasicRatesOfMarriedFilingSeparately.setIntercellSpacing(new Dimension(5, 5));
		tblBasicRatesOfMarriedFilingSeparately.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBasicRatesOfMarriedFilingSeparately.setModel(new DefaultTableModel(
			new Object[][] {
				{"Low Rate", 		Integer.valueOf(0), 		Integer.valueOf(18040), 	Double.valueOf(5.35)},
				{"Basic Rate", 		Integer.valueOf(18040), 	Integer.valueOf(71680), 	Double.valueOf(7.05)},
				{"Medium Rate", 	Integer.valueOf(71680), 	Integer.valueOf(90000), 	Double.valueOf(7.85)},
				{"High Rate", 		Integer.valueOf(90000), 	Integer.valueOf(127120), 	Double.valueOf(7.85)},
				{"Additional Rate", Integer.valueOf(127120), 	null, 						Double.valueOf(9.85)},
			},
			new String[] { "Band", ">=", "<", "Tax Rate %"	}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Float.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tblBasicRatesOfMarriedFilingSeparately.getColumnModel().getColumn(0).setPreferredWidth(85);
		tblBasicRatesOfMarriedFilingSeparately.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblBasicRatesOfMarriedFilingSeparately.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblBasicRatesOfMarriedFilingSeparately.getColumnModel().getColumn(3).setPreferredWidth(65);
		tblBasicRatesOfMarriedFilingSeparately.setBounds(10, 60, 280, 145);
		JScrollPane scpBasicRatesOfMarriedFillingSeparately= new JScrollPane(tblBasicRatesOfMarriedFilingSeparately);
		scpBasicRatesOfMarriedFillingSeparately.setLocation(304, 180);
		scpBasicRatesOfMarriedFillingSeparately.setSize(275, 107);
		contentPane.add(scpBasicRatesOfMarriedFillingSeparately);
		
		//Basic tax rates for a taxpayer with Married Filing Jointly marital status
		JLabel lblMarriedFilingJoinly = new JLabel("Married Filing Joinly");
		lblMarriedFilingJoinly.setBounds(10, 166, 270, 14);
		contentPane.add(lblMarriedFilingJoinly);
		
		tblBasicRatesOfMarriedFilingJointly = new JTable();
		tblBasicRatesOfMarriedFilingJointly.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblBasicRatesOfMarriedFilingJointly.setIntercellSpacing(new Dimension(5, 5));
		tblBasicRatesOfMarriedFilingJointly.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblBasicRatesOfMarriedFilingJointly.setModel(new DefaultTableModel(
			new Object[][] {
				{"Low Rate", 		Integer.valueOf(0), 		Integer.valueOf(36080), 	Double.valueOf(5.35)},
				{"Basic Rate", 		Integer.valueOf(36080), 	Integer.valueOf(90000), 	Double.valueOf(7.05)},
				{"Medium Rate", 	Integer.valueOf(90000), 	Integer.valueOf(143350), 	Double.valueOf(7.85)},
				{"High Rate", 		Integer.valueOf(143350), 	Integer.valueOf(254240), 	Double.valueOf(7.85)},
				{"Additional Rate", Integer.valueOf(254240),	null, 						Double.valueOf(9.85)},
			},
			new String[] { "Band", ">=", "<", "Tax Rate %"	}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Float.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tblBasicRatesOfMarriedFilingJointly.getColumnModel().getColumn(0).setPreferredWidth(85);
		tblBasicRatesOfMarriedFilingJointly.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblBasicRatesOfMarriedFilingJointly.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblBasicRatesOfMarriedFilingJointly.getColumnModel().getColumn(3).setPreferredWidth(65);
		tblBasicRatesOfMarriedFilingJointly.setBounds(10, 60, 280, 145);
		JScrollPane scpBasicRatesOfMarriedFilingJointly= new JScrollPane(tblBasicRatesOfMarriedFilingJointly);
		scpBasicRatesOfMarriedFilingJointly.setBorder(new LineBorder(new Color(130, 135, 144)));
		scpBasicRatesOfMarriedFilingJointly.setLocation(12, 180);
		scpBasicRatesOfMarriedFilingJointly.setSize(275, 107);
		contentPane.add(scpBasicRatesOfMarriedFilingJointly);
		
		//Variation tax rates for a taxpayer 
		JLabel lblVariationTaxRates = new JLabel("VariationTax Rates per Receipts-Value/Income Band");
		lblVariationTaxRates.setBounds(10, 300, 564, 14);
		contentPane.add(lblVariationTaxRates);
		JSeparator sprVariationTax = new JSeparator();
		sprVariationTax.setBounds(10, 320, 567, 2);
		contentPane.add(sprVariationTax);
		
		tblVariationTaxRates= new JTable();
		tblVariationTaxRates.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblVariationTaxRates.setIntercellSpacing(new Dimension(5, 5));
		tblVariationTaxRates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblVariationTaxRates.setModel(new DefaultTableModel(
			new Object[][] {
				{"Low Rate", 		Integer.valueOf(0), 	Integer.valueOf(20), 	Double.valueOf(8)},
				{"Basic Rate", 		Integer.valueOf(20), 	Integer.valueOf(40), 	Double.valueOf(4)},
				{"Medium Rate", 	Integer.valueOf(40), 	Integer.valueOf(60), 	Double.valueOf(-15)},
				{"High Rate", 		Integer.valueOf(60), 	null,					Double.valueOf(-30)}
			},
			new String[] { "Band", ">= %", "< %", "Tax Rate %"	}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Float.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tblVariationTaxRates.getColumnModel().getColumn(0).setPreferredWidth(85);
		tblVariationTaxRates.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblVariationTaxRates.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblVariationTaxRates.getColumnModel().getColumn(3).setPreferredWidth(65);
		tblVariationTaxRates.setBounds(10, 60, 280, 145);
		JScrollPane scpVariationTaxRates= new JScrollPane(tblVariationTaxRates);
		scpVariationTaxRates.setBorder(new LineBorder(new Color(130, 135, 144)));
		scpVariationTaxRates.setLocation(10, 333);
		scpVariationTaxRates.setSize(275, 91);
		contentPane.add(scpVariationTaxRates);contentPane.add(scpVariationTaxRates);
		
		//Files and Paths	
		JLabel lblFilesAndFolders = new JLabel("Configuration Files and Folders");
		lblFilesAndFolders.setBounds(10, 435, 564, 14);
		contentPane.add(lblFilesAndFolders);
		JSeparator sprConfigurationFilesAndFolders = new JSeparator();
		sprConfigurationFilesAndFolders.setBounds(12, 455, 567, 2);
		contentPane.add(sprConfigurationFilesAndFolders);
		JLabel lblConfigurationFileName = new JLabel("Configuration File Name");
		lblConfigurationFileName.setBounds(10, 469, 149, 14);
		contentPane.add(lblConfigurationFileName);
		
		txtConfigurationFileName = new JTextField();
		txtConfigurationFileName.setBounds(153, 466, 396, 20);
		contentPane.add(txtConfigurationFileName);
		txtConfigurationFileName.setColumns(10);
		
		JLabel lblDataStoragePath = new JLabel("Data Storage Path");
		lblDataStoragePath.setBackground(Color.CYAN);
		lblDataStoragePath.setBounds(10, 497, 149, 14);
		contentPane.add(lblDataStoragePath);
		
		txtDataStoragePath = new JTextField();
		txtDataStoragePath.setColumns(10);
		txtDataStoragePath.setBounds(153, 494, 396, 20);
		contentPane.add(txtDataStoragePath);
		
		//Buttons
		btnSelectConfigurationFileName = new JButton("...");
		btnSelectConfigurationFileName.setBounds(559, 464, 20, 20);
		contentPane.add(btnSelectConfigurationFileName);
		
		btnSelectDataStoragePath = new JButton("...");
		btnSelectDataStoragePath.setBounds(559, 493, 20, 23);
		contentPane.add(btnSelectDataStoragePath);
		
		chbAutoCalculate = new JCheckBox("Auto-Calculate");
		chbAutoCalculate.setBounds(10, 518, 134, 23);
		contentPane.add(chbAutoCalculate);
		
		btnOK = new JButton("OK");
		btnOK.setMnemonic('O');
		btnOK.setBounds(409, 548, 80, 23);
		contentPane.add(btnOK);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setMnemonic('C');
		btnCancel.setBounds(499, 548, 80, 23);
		contentPane.add(btnCancel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==btnOK){
			controller.handleSettingsOKButton();
		}else if (source==btnCancel) {
			dispose();
		}else if (source==btnSelectConfigurationFileName) {
			controller.handleSettingsConfigurationFileNameButton();
		}else if (source==btnSelectDataStoragePath) {
			controller.handleSettingsSelectDataStoragePathButton();
		}
		
	}

	public void setConfigurationFileName(String name) {
		txtConfigurationFileName.setText(name);
	}

	public void setDataStoragePath(String path) {
		txtDataStoragePath.setText(path);
	}
}
