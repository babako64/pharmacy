package ir.maktab.pharmacy.GUI.mainFrame.prescription;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ir.maktab.pharmacy.GUI.mainFrame.panel2Controller;
import ir.maktab.pharmacy.drugDAO.DrugDAOImp;
import ir.maktab.pharmacy.drugInsuranceDAO.DrugInsuranceDAOImp;
import ir.maktab.pharmacy.drugInsuranceModel.DrugInsurance;
import ir.maktab.pharmacy.drugModel.Drug;
import ir.maktab.pharmacy.drugPrescriptionModel.DrugPrescription;
import ir.maktab.pharmacy.drupPrescriptionDAO.DrugPrescriptionDAOImp;
import ir.maktab.pharmacy.patientModel.Patient;

import javax.swing.JPanel;

public class PrescriptionView extends JFrame{

	
	private JTable table;
	private JTextField textField;
	DefaultTableModel tableModel;
	
	 int IdSelectedRow;
	 String NameSelectedRow;
	 int  InsurCodeSelectedRow;
	private JTextField textField_1;
	private JTextField textField_2;
	
	JButton btnAdd;
	JButton btnLoad;
	JButton btnRemove;
	JButton btnUpdate;
	
	int prescriptionID;
	int insuranceId;
	
	public PrescriptionView(int prescriptionID, int insuranceId) {
		
		
		this.prescriptionID = prescriptionID;
		this.insuranceId = insuranceId;
		
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		//String drugs[][] = getInsurances();
		String column[] = { "DRUG", "PRESCRIPTION ID", "COUNT", "PAYMENT"};
		
	
		textField = new JTextField();
		textField.setBounds(104, 64, 140, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(104, 104, 140, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(104, 141, 140, 26);
		getContentPane().add(textField_2);
		
		
	    btnAdd = new JButton("Add");
		btnAdd.setBounds(34, 331, 89, 23);
		btnAdd.addActionListener(new PrescriptionController(this));
		getContentPane().add(btnAdd);
		
		 btnLoad = new JButton("Load");
		btnLoad.setBounds(143, 331, 89, 23);
		btnLoad.addActionListener(new PrescriptionController(this));
		getContentPane().add(btnLoad);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(34, 380, 89, 23);
		btnRemove.addActionListener(new PrescriptionController(this));
		getContentPane().add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(143, 380, 89, 23);
		btnUpdate.addActionListener(new PrescriptionController(this));
		getContentPane().add(btnUpdate);
		
		JLabel lblId = new JLabel("drug name");
		lblId.setBounds(22, 70, 72, 14);
		getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("count");
		lblName.setBounds(22, 110, 46, 14);
		getContentPane().add(lblName);
		
		JLabel lblInsureCode = new JLabel("payment");
		lblInsureCode.setBounds(22, 147, 72, 14);
		getContentPane().add(lblInsureCode);
		
		JPanel panel = new JPanel();
		panel.setBounds(278, 11, 656, 540);
		getContentPane().add(panel);
		
		//tableModel = new DefaultTableModel(drugs, column);
		table = new JTable(tableModel){
			public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
				Component comp = super.prepareRenderer(renderer, Index_row, Index_col);

				if (isRowSelected(Index_row)) {
					comp.setBackground(Color.yellow);
					IdSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 0));
					NameSelectedRow = (String) table.getValueAt(Index_row, 1);
					InsurCodeSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 2));
					

				} else {
					if (Index_row % 2 == 0 && !isCellSelected(Index_row, Index_col)) {
						comp.setBackground(Color.lightGray);
					} else {
						comp.setBackground(Color.white);
					}
				}
				return comp;
			}
		};
		//	table.setBounds(277, 29, 590, 424);
			table.setRowSelectionAllowed(true);
			JScrollPane sp = new JScrollPane(table);
			panel.add(sp);
		
		setSize(960,600);
        setVisible(true);
	}
	
	public void addDrug(String drugName, int prescriptionID, int count, double payment) throws ClassNotFoundException, SQLException {
		
		//System.out.println(drugName);
		
		DrugPrescriptionDAOImp dPrescrip = new DrugPrescriptionDAOImp();
		
		Drug  drug = new DrugDAOImp().getDrug(drugName);
		
		double newPayment=0;
		//System.out.println(payment);
		if(count <= drug.getStock() ) {
		if(payment == 0) {
		DrugInsuranceDAOImp dInsu = new DrugInsuranceDAOImp();
		int share =  dInsu.getShare(drug.getId(), insuranceId);
		//System.out.println(drug.getPrice() +" " + count + " " + share);
		double price = drug.getPrice();
			double p = price;
			price = price * (double)count;
			price = price * (double)share;
			price = price / (double)100;
		//	System.out.println(price);
			newPayment = (p * (double)count) - price;
		//	System.out.println(newPayment);
		}
		}
		
		dPrescrip.addDrupPrescription(drug.getId(), prescriptionID, count, newPayment);
		getPrescription(drug, prescriptionID);
	}
	
	public void getPrescription(Drug drug, int presripId) throws ClassNotFoundException, SQLException {
		
		DrugPrescriptionDAOImp dp = new DrugPrescriptionDAOImp();
		ArrayList<DrugPrescription> d = dp.getDrug(drug.getId(), presripId);
		
		//ArrayList<Patient> pList = patientDAO.getAll();
		String[][] list = new String[d.size()][4];
		for (int i = 0; i < d.size(); i++) {
		String dName = new DrugDAOImp().getDrugByID(d.get(i).getdId());
			list[i][0] = dName;
			list[i][1] = Integer.toString(d.get(i).getpID());
			list[i][2] = Integer.toString(d.get(i).getCount());
			list[i][3] = Double.toString(d.get(i).getPayment());
			
		}

		refresh(list);
	}
	
	public void refresh(String[][] list ) {

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		String column[] = { "DRUG", "PRESCRIPTION ID", "COUNT", "PAYMENT"};
		tableModel.setDataVector(list, column);
		table.setModel(tableModel);

	}
	
	public JButton getAddbtn() {
		
		return btnAdd;
	}
	public JButton getLoadbtn() {
	
	return btnLoad;
	}
	public JButton getRemovebtn() {
		
		return btnRemove;
		}
	public JButton getButtonUpdate() {
		
		return btnUpdate;
	}
	
	public JTextField getNameTf() {
		
		return textField;
	}
	
	public JTextField getCountTf() {
		
		return textField_1;
	}
	
	public JTextField getPaymenntTf() {
		
		return textField_2;
	}
	
}
