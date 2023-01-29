package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import controller.Controller;
import model.ReceiptCategory;

public class AddReceiptDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtIssueDate;
	private JComboBox<ReceiptCategory> cboCategory;
	private JTextField txtAmount;
	private JTextField txtCompany;
	private JTextField txtCountry;
	private JTextField txtCity;
	private JTextField txtStreet;
	private JTextField txtNumber;
	private JButton btnOK;
	private JButton btnCancel;
	private JFrame parentFrame;
	private Controller controller;

	public AddReceiptDialog(JFrame parentFrame, Controller controller) {
		super(parentFrame, true);
		this.controller = controller;
		this.parentFrame = parentFrame;
		initComponents();
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	private void initComponents() {
		setTitle("Add Receipt");
		setName("frmAddReceipt");
		setResizable(false);
		setLocationRelativeTo(parentFrame);
		setBounds(100, 100, 270, 350);

		contentPane = new JPanel();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTRN = new JLabel("Identification No");
		lblTRN.setBounds(10, 10, 100, 14);
		contentPane.add(lblTRN);

		JLabel lblIssueDate = new JLabel("Issue Date");
		lblIssueDate.setBounds(10, 35, 100, 14);
		contentPane.add(lblIssueDate);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 60, 100, 14);
		contentPane.add(lblCategory);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(10, 85, 100, 14);
		contentPane.add(lblAmount);

		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(10, 110, 100, 14);
		contentPane.add(lblCompany);

		JLabel lblCompanyAddressHeader = new JLabel("Company Address");
		lblCompanyAddressHeader.setBounds(10, 146, 234, 14);
		contentPane.add(lblCompanyAddressHeader);

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(10, 171, 100, 14);
		contentPane.add(lblCountry);

		JLabel lblCity = new JLabel("City ");
		lblCity.setBounds(10, 196, 100, 14);
		contentPane.add(lblCity);

		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(10, 221, 100, 14);
		contentPane.add(lblStreet);

		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(10, 246, 100, 14);
		contentPane.add(lblNumber);

		txtID = new JTextField();
		txtID.setText("55555");
		txtID.setColumns(10);
		txtID.setBounds(108, 7, 136, 20);
		contentPane.add(txtID);

		txtIssueDate = new JTextField();
		txtIssueDate.setText("2021-11-11");
		txtIssueDate.setColumns(10);
		txtIssueDate.setBounds(108, 32, 136, 20);
		contentPane.add(txtIssueDate);

		cboCategory = new JComboBox<ReceiptCategory>();
		cboCategory.setBounds(108, 57, 136, 20);
		cboCategory.addItem(ReceiptCategory.BASIC);
		cboCategory.addItem(ReceiptCategory.ENTERTAINMENT);
		cboCategory.addItem(ReceiptCategory.HEALTH);
		cboCategory.addItem(ReceiptCategory.OTHER);
		cboCategory.addItem(ReceiptCategory.TRAVEL);
		contentPane.add(cboCategory);

		txtAmount = new JTextField();
		txtAmount.setText("1000.01");
		txtAmount.setColumns(10);
		txtAmount.setBounds(108, 82, 136, 20);
		contentPane.add(txtAmount);

		txtCompany = new JTextField();
		txtCompany.setText("IBM");
		txtCompany.setColumns(10);
		txtCompany.setBounds(108, 107, 136, 20);
		contentPane.add(txtCompany);

		txtCountry = new JTextField();
		txtCountry.setText("Greece");
		txtCountry.setColumns(10);
		txtCountry.setBounds(108, 165, 136, 20);
		contentPane.add(txtCountry);

		txtCity = new JTextField();
		txtCity.setText("Athens");
		txtCity.setColumns(10);
		txtCity.setBounds(108, 190, 136, 20);
		contentPane.add(txtCity);

		txtStreet = new JTextField();
		txtStreet.setText("My Street");
		txtStreet.setColumns(10);
		txtStreet.setBounds(108, 215, 136, 20);
		contentPane.add(txtStreet);

		txtNumber = new JTextField();
		txtNumber.setText("12A");
		txtNumber.setColumns(10);
		txtNumber.setBounds(108, 240, 136, 20);
		contentPane.add(txtNumber);

		btnOK = new JButton("OK");
		btnOK.setBounds(35, 277, 80, 23);
		contentPane.add(btnOK);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(125, 277, 80, 23);
		contentPane.add(btnCancel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOK) {
			controller.handleAddReceiptDialogOKButton();
		} else if (e.getSource() == btnCancel) {
			controller.handleAddReceiptDialogCancelButton();
		}

	}

	public String[] getEnteredValues() {
		String[] receipt = { txtID.getText(), txtIssueDate.getText(), txtAmount.getText(),
				cboCategory.getSelectedItem().toString(), txtCompany.getText(), txtCountry.getText(), txtCity.getText(),
				txtStreet.getText(), txtNumber.getText() };
		return receipt;
	}
}
