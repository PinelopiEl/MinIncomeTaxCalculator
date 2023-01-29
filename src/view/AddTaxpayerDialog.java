package view;

import java.awt.ComponentOrientation;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import controller.Controller;
import model.MaritalStatus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Window.Type;

public class AddTaxpayerDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtTIN;
	private JTextField txtIncome;
	private JComboBox<MaritalStatus> cboMaritalStatus;
	private JButton btnOK;
	private JButton btnCancel;
	
	private JFrame parentFrame;
	private Controller controller;
	
	public AddTaxpayerDialog(JFrame parentFrame, Controller controller) {
		super(parentFrame, true);
		this.controller = controller;
		this.parentFrame = parentFrame;
		initComponents();
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	private void initComponents() {
		setTitle("Add Taxpayer");
		setName("frmAddTaxpayer");		
		setResizable(false);
		setLocationRelativeTo(parentFrame);		
		setBounds(100, 100, 340, 180);
		
		contentPane = new JPanel();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblName = new JLabel("Full Name");						
		lblName.setBounds(10, 11, 120, 14);contentPane.add(lblName);
		JLabel lblTRN = new JLabel("Tax Identification No");		
		lblTRN.setBounds(10, 36, 120, 14);contentPane.add(lblTRN);				
		JLabel lblIncome = new JLabel("Income");					
		lblIncome.setBounds(10, 61, 120, 14);contentPane.add(lblIncome);
		JLabel lblMaritalStatus = new JLabel("Marital Status");	
		lblMaritalStatus.setBounds(10, 86, 120, 14);contentPane.add(lblMaritalStatus);
		
		txtName = new JTextField();							
		txtName.setText("FamilyName, FirstName");
		txtName.setBounds(130, 8, 180, 20);contentPane.add(txtName);
		txtTIN = new JTextField();							
		txtTIN.setToolTipText("TIN shall be a 9-digits integer.");
		txtTIN.setText("999999999");
		txtTIN.setBounds(130, 33, 180, 20);contentPane.add(txtTIN);
		txtIncome = new JTextField();						
		txtIncome.setToolTipText("Income shall be a numeric value");
		txtIncome.setText("65000.00");
		txtIncome.setBounds(130, 58, 180, 20);contentPane.add(txtIncome);
		cboMaritalStatus = new JComboBox<MaritalStatus>();	
		cboMaritalStatus.setToolTipText("Pick one of the predefined values");
		cboMaritalStatus.setBounds(130, 83, 180, 20);contentPane.add(cboMaritalStatus);		
		cboMaritalStatus.addItem(MaritalStatus.SINGLE);
		cboMaritalStatus.addItem(MaritalStatus.HEAD_OF_HOUSEHOLD);
		cboMaritalStatus.addItem(MaritalStatus.MARRIED_FILING_SEPARATELY);
		cboMaritalStatus.addItem(MaritalStatus.MARRIED_FILING_JOINTLY);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(70, 114, 80, 23);
		contentPane.add(btnOK);
		btnCancel = new JButton("Cancel");	
		btnCancel.setBounds(160, 114, 80, 23);
		contentPane.add(btnCancel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOK){
			controller.handleAddTaxpayerOKButton();
		}else if (e.getSource()==btnCancel) {
			controller.handleAddTaxpayerCancelButton();
		}
	}
	
	public String[] getEnteredValues() {
		String[] taxPayer = {txtName.getText(), cboMaritalStatus.getSelectedItem().toString(), txtTIN.getText(), txtIncome.getText()};
		return taxPayer;
	}
}