package ir.maktab.pharmacy.GUI.mainFrame.drgInsuranse;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ir.maktab.pharmacy.GUI.mainFrame.panel2Controller;
import ir.maktab.pharmacy.drugInsuranceDAO.DrugInsuranceDAOImp;
import ir.maktab.pharmacy.drugInsuranceModel.DrugInsurance;
import ir.maktab.pharmacy.insuranceDAO.InsuranceDAOImp;
import ir.maktab.pharmacy.insuranceModel.Insurance;

import javax.swing.JComboBox;

public class DrugInsuranceView extends JPanel{

	private JTable table;
	private JTextField textField;
	DefaultTableModel tableModel;
	
	 String DrugSelectedRow;
	 String InsureSelectedRow;
	 int  InsurShareSelectedRow;
	 String InsureNameSelectedRow;
	 private JTextField textField_2;
	
	JButton btnAdd;
	JButton btnLoad;
	JButton btnRemove;
	JButton btnUpdate;
	
	public DrugInsuranceView() throws ClassNotFoundException, SQLException {


		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		String drugs[][] = getDrugInsurances();
		String column[] = { "DRUG NAME", "INSURANCE NAME", "INSURANCE SHARE"};
		tableModel = new DefaultTableModel(drugs, column);
		table = new JTable(tableModel){
			public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
				Component comp = super.prepareRenderer(renderer, Index_row, Index_col);

				if (isRowSelected(Index_row)) {
					comp.setBackground(Color.yellow);
					DrugSelectedRow = (String) table.getValueAt(Index_row, 0);
					InsureSelectedRow = (String) table.getValueAt(Index_row, 1);
					InsurShareSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 2));
					

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
		sp.setBounds(278, 27, 590, 495);
		add(sp);
		
	
		textField = new JTextField();
		textField.setBounds(128, 64, 140, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(128, 164, 140, 26);
		add(textField_2);
		
		
	    btnAdd = new JButton("Add");
		btnAdd.setBounds(34, 331, 89, 23);
		btnAdd.addActionListener(new DrugInsuranceController(this));
		add(btnAdd);
		
		 btnLoad = new JButton("Load");
		btnLoad.setBounds(143, 331, 89, 23);
		btnLoad.addActionListener(new DrugInsuranceController(this));
		add(btnLoad);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(34, 380, 89, 23);
		btnRemove.addActionListener(new DrugInsuranceController(this));
		add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(143, 380, 89, 23);
		btnUpdate.addActionListener(new DrugInsuranceController(this));
		add(btnUpdate);
		
		JLabel lblId = new JLabel("Drug name");
		lblId.setBounds(22, 70, 59, 14);
		add(lblId);
		
		JLabel lblName = new JLabel("Insurance");
		lblName.setBounds(22, 116, 59, 14);
		add(lblName);
		
		JLabel lblInsureCode = new JLabel("Insurance Share");
		lblInsureCode.setBounds(22, 170, 96, 14);
		add(lblInsureCode);
		
		String[] description = getInsuranse();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(128, 113, 140, 20);
		
		int count = 0;
		 for (int i = 0; i < description.length; i++)
			 comboBox.addItem(description[count++]);
		 
		 comboBox.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        
		        System.out.println(((JComboBox)e.getSource()).getSelectedItem());
		        InsureNameSelectedRow = (String) (((JComboBox)e.getSource()).getSelectedItem());
		      }
		    });
		 
		add(comboBox);
	}
	
	public String[][] getDrugInsurances() throws ClassNotFoundException, SQLException{

		DrugInsuranceDAOImp insurDAO = new DrugInsuranceDAOImp();
		ArrayList<DrugInsurance> insurList = insurDAO.getAll();
		String[][] list = new String[insurList.size()][3];
		DrugInsurance DrugInsurance;
		for (int i = 0; i < insurList.size(); i++) {
			DrugInsurance = insurList.get(i);
			list[i][0] = DrugInsurance.getDrugId();
			list[i][1] = DrugInsurance.getInsuranceId();
			list[i][2] = Integer.toString(DrugInsurance.getInsuranceShare());
			
		}

		return list;
		
	}
	
	public void getShare(String dName, String insurName) throws ClassNotFoundException, SQLException {

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		String column[] = { "DRUG NAME", "INSURANCE NAME", "INSURANCE SHARE" };
		tableModel.setDataVector(getDrugInsurance(dName, insurName), column);
		table.setModel(tableModel);

	}
	public String[][] getDrugInsurance(String dName, String insurName) throws ClassNotFoundException, SQLException{
		
		DrugInsuranceDAOImp insurDAO = new DrugInsuranceDAOImp();
		ArrayList<DrugInsurance> insurList = insurDAO.getInsuranceShare(dName, insurName);
		String[][] list = new String[insurList.size()][3];
		DrugInsurance DrugInsurance;
		for (int i = 0; i < insurList.size(); i++) {
			DrugInsurance = insurList.get(i);
			list[i][0] = DrugInsurance.getDrugId();
			list[i][1] = DrugInsurance.getInsuranceId();
			list[i][2] = Integer.toString(DrugInsurance.getInsuranceShare());
			
		}

		return list;
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
	
	public void add(String drugName, String insureName, int insureShare) throws ClassNotFoundException, SQLException {
		
		DrugInsuranceDAOImp insurDAO = new DrugInsuranceDAOImp();
		System.out.println(insureShare);
		insurDAO.addInsuranceShare(drugName, insureName, insureShare);
		refresh();
	}
	
	public void refresh() throws ClassNotFoundException, SQLException {

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		String column[] = { "DRUG NAME", "INSURANCE NAME", "INSURANCE SHARE"};
		tableModel.setDataVector(getDrugInsurances(), column);
		table.setModel(tableModel);

	}
	
	public void remove(String dName, String insuName) throws ClassNotFoundException, SQLException {
		
		DrugInsuranceDAOImp insurDAO = new DrugInsuranceDAOImp();
		insurDAO.remove(dName, insuName);
		refresh();
	}
	
	public void update(String drugName, String insureName, int insureShare) throws ClassNotFoundException, SQLException {
		DrugInsuranceDAOImp insurDAO = new DrugInsuranceDAOImp();
		insurDAO.update(drugName, insureName, insureShare);
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
	
	public JTextField getDrugIdTf() {
		
		return textField;
	}
	
	
	public JTextField getInsureIdTf() {
		
		return textField_2;
	}
}
