package ir.maktab.pharmacy.GUI.mainFrame.patient;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ir.maktab.pharmacy.GUI.mainFrame.panel2Controller;
import ir.maktab.pharmacy.GUI.mainFrame.prescription.PrescriptionView;
import ir.maktab.pharmacy.drugInsuranceDAO.DrugInsuranceDAOImp;
import ir.maktab.pharmacy.drugInsuranceModel.DrugInsurance;
import ir.maktab.pharmacy.insuranceDAO.InsuranceDAOImp;
import ir.maktab.pharmacy.insuranceModel.Insurance;
import ir.maktab.pharmacy.patientDAO.PatientDAOImp;
import ir.maktab.pharmacy.patientModel.Patient;
import ir.maktab.pharmacy.prescriptionDAO.PrescriptionDAOImp;
import ir.maktab.pharmacy.prescriptionModel.Prescription;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class patientView extends JPanel{
	
	
	
	private JTable table;
	DefaultTableModel tableModel;
	
	 int IdSelectedRow;
	 String NameSelectedRow;
	 int  InsurNumberSelectedRow;
	 int  InsurIdSelectedRow;
	 
	 String insuranceName;
	private JTextField textField_1;
	private JTextField textField_2;
	
	JButton btnAdd;
	JButton btnLoad;
	JButton btnRemove;
	JButton btnUpdate;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public patientView() throws ClassNotFoundException, SQLException {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		String patients[][] = getPatient();
		String column[] = { "ID", "NAME", "INSURANCE NUMBER", "INSURANCE ID"};
		tableModel = new DefaultTableModel(patients, column);
		table = new JTable(tableModel){
			public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
				Component comp = super.prepareRenderer(renderer, Index_row, Index_col);

				if (isRowSelected(Index_row)) {
					comp.setBackground(Color.yellow);
					IdSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 0));
					NameSelectedRow = (String) table.getValueAt(Index_row, 1);
					InsurNumberSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 2));
					InsurIdSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 3));

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
		sp.setBounds(265, 172, 650, 357);
		add(sp);
		
		textField_1 = new JTextField();
		textField_1.setBounds(109, 37, 140, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(441, 37, 140, 26);
		add(textField_2);
		
		
	    btnAdd = new JButton("Add");
		btnAdd.setBounds(22, 221, 89, 23);
		btnAdd.addActionListener(new patientController(this));
		add(btnAdd);
		
		 btnLoad = new JButton("Load");
		btnLoad.setBounds(124, 221, 89, 23);
		btnLoad.addActionListener(new patientController(this));
		add(btnLoad);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(22, 272, 89, 23);
		btnRemove.addActionListener(new patientController(this));
		add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(124, 272, 89, 23);
		btnUpdate.addActionListener(new patientController(this));
		add(btnUpdate);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(53, 43, 46, 14);
		add(lblName);
		
		JLabel lblInsureCode = new JLabel("insurance number");
		lblInsureCode.setBounds(298, 43, 85, 14);
		add(lblInsureCode);
		
		JLabel lblInsurance = new JLabel("insurance");
		lblInsurance.setBounds(670, 43, 46, 14);
		add(lblInsurance);
		
		String[] description = getInsuranse();
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(743, 40, 140, 20);
		int count = 0;
		 for (int i = 0; i < description.length; i++)
			 comboBox.addItem(description[count++]);
		 
		 comboBox.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        
		        insuranceName = (String) (((JComboBox)e.getSource()).getSelectedItem());
		      }
		    });
		add(comboBox);
		
		JLabel lblDoctorName = new JLabel("doctor name");
		lblDoctorName.setBounds(39, 96, 60, 14);
		add(lblDoctorName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(109, 90, 140, 26);
		add(textField_3);
		
		JLabel lblReferralDate = new JLabel("referral date");
		lblReferralDate.setBounds(298, 96, 68, 14);
		add(lblReferralDate);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(441, 90, 140, 26);
		add(textField_4);
	}
	
	public String[][] getPatient() throws ClassNotFoundException, SQLException{

		PatientDAOImp patientDAO = new PatientDAOImp();
		ArrayList<Patient> pList = patientDAO.getAll();
		String[][] list = new String[pList.size()][4];
		Patient patient;
		for (int i = 0; i < pList.size(); i++) {
			patient = pList.get(i);
			list[i][0] = Integer.toString(patient.getId());
			list[i][1] = patient.getPationtName();
			list[i][2] = Integer.toString(patient.getInsuranceNumber());
			list[i][3] = Integer.toString(patient.getInsuranceID());
			
		}

		return list;
		
	}
	
	public void refresh() throws ClassNotFoundException, SQLException {

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		String column[] = { "ID", "NAME", "INSURANCE NUMBER", "INSURANCE ID"};
		tableModel.setDataVector(getPatient(), column);
		table.setModel(tableModel);

	}
	
	public void addPatient(String name, int insurNumber, String insurName, String docName, String referralDate) throws ClassNotFoundException, SQLException {
		
		int insurID = new DrugInsuranceDAOImp().getInsuId(insurName);
		PatientDAOImp paientDAO = new PatientDAOImp();
		Patient patient = new Patient(0, name, insurNumber, insurID);
		int pId=0;
		if(paientDAO.checkPatient(patient)) {
			pId = paientDAO.getPatientId(patient.getPationtName(), insurNumber);
			addPrescription(docName, referralDate, pId, insurID);
			
		}else {
			
			 pId = paientDAO.addPatient(patient);
			 addPrescription(docName, referralDate, pId, insurID);
			
		}
		
		
	}
	
	public void addPrescription(String doctorName, String referralDate, int pationtId,int insurID) throws ClassNotFoundException, SQLException {
		
		Prescription prescrip = new Prescription(0, doctorName, referralDate, pationtId);
		int id = new PrescriptionDAOImp().addPrescription(prescrip);
		getParent();
		new PrescriptionView(id, insurID);
	}
	
	public String[] getInsuranse() {
		
		InsuranceDAOImp insu = new InsuranceDAOImp();
		ArrayList<Insurance> insuList = new ArrayList<>();
		insuList = insu.getall();
		Insurance insurance;
		String[] list = new String[insuList.size()];
		for (int i = 0; i < insuList.size(); i++) {
			insurance = insuList.get(i);
			list[i] = insurance.getName();
			
		}
		
		return list;
	}
	
	public void remove(String name) throws ClassNotFoundException, SQLException {
		 new PatientDAOImp().remove(name);
		 new PatientDAOImp().remove2(name);
		 refresh();
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
		
		return textField_1;
	}
	
	public JTextField getInsureNumberTf() {
		
		return textField_2;
	}
	
	public JTextField getDoctorNameTf() {
		
		return textField_3;
	}
	
	public JTextField getReferralDateTf() {
		
		return textField_4;
	}
}
