package ir.maktab.pharmacy.GUI.mainFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ir.maktab.pharmacy.drugDAO.DrugDAOImp;
import ir.maktab.pharmacy.drugModel.Drug;
import ir.maktab.pharmacy.resource.HintTextField;
import javax.swing.JLabel;

public class panel1 extends JPanel {
	private JTable table;
	private JTextField textField;
	DefaultTableModel tableModel;

	int IdSelectedRow;
	String NameSelectedRow;
	int StocktSelectedRow;
	String ExpireDateSelectedRow;
	double PricaSelectedRow;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	JButton btnAdd;
	JButton btnLoad;
	JButton btnRemove;
	JButton btnUpdate;

	public panel1() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		String drugs[][] = getDrugs();
		String column[] = { "ID", "NAME", "STOCK", "EXPIRE DATE", "PRICE" };
		tableModel = new DefaultTableModel(drugs, column);
		table = new JTable(tableModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
				Component comp = super.prepareRenderer(renderer, Index_row, Index_col);

				if (isRowSelected(Index_row)) {
					comp.setBackground(Color.yellow);
					IdSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 0));
					NameSelectedRow = (String) table.getValueAt(Index_row, 1);
					StocktSelectedRow = Integer.parseInt((String) table.getValueAt(Index_row, 2));
					ExpireDateSelectedRow = (String) table.getValueAt(Index_row, 3);
					PricaSelectedRow = Double.parseDouble((String) table.getValueAt(Index_row, 4));

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
		// table.setBounds(277, 29, 590, 424);
		table.setRowSelectionAllowed(true);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(278, 27, 590, 495);
		add(sp);

		textField = new JTextField();
		textField.setBounds(104, 64, 140, 26);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(104, 104, 140, 26);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(104, 141, 140, 26);
		add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(104, 178, 140, 26);
		add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(104, 215, 140, 26);
		add(textField_4);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(34, 331, 89, 23);
		btnAdd.addActionListener(new panel1Controller(this));
		add(btnAdd);

		btnLoad = new JButton("Load");
		btnLoad.setBounds(143, 331, 89, 23);
		btnLoad.addActionListener(new panel1Controller(this));
		add(btnLoad);

		btnRemove = new JButton("Remove");
		btnRemove.setBounds(34, 380, 89, 23);
		btnRemove.addActionListener(new panel1Controller(this));
		add(btnRemove);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(143, 380, 89, 23);
		btnUpdate.addActionListener(new panel1Controller(this));
		add(btnUpdate);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(22, 70, 46, 14);
		add(lblId);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(22, 116, 46, 14);
		add(lblName);

		JLabel lblExpireDate = new JLabel("Expire Date");
		lblExpireDate.setBounds(22, 184, 72, 14);
		add(lblExpireDate);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(22, 147, 46, 14);
		add(lblStock);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(22, 221, 46, 14);
		add(lblPrice);
	}

	public String[][] getDrugs() {

		DrugDAOImp drugDAO = new DrugDAOImp();
		ArrayList<Drug> drugList = drugDAO.getAll();
		String[][] list = new String[drugList.size()][5];
		Drug drug;
		for (int i = 0; i < drugList.size(); i++) {
			drug = drugList.get(i);
			list[i][0] = Integer.toString(drug.getId());
			list[i][1] = drug.getName();
			list[i][2] = Integer.toString(drug.getStock());
			list[i][3] = drug.getExpireDate();
			list[i][4] = Double.toString(drug.getPrice());
		}

		return list;

	}

	public void getDrug(String name) {

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		String column[] = { "ID", "NAME", "STOCK", "EXPIRE DATE", "PRICE" };
		tableModel.setDataVector(findDrug(name), column);
		table.setModel(tableModel);

	}

	public String[][] findDrug(String name) {

		DrugDAOImp drugDAO = new DrugDAOImp();
		ArrayList<Drug> drugList = new ArrayList<>();
		drugList.add(drugDAO.getDrug(name));
		String[][] list = new String[drugList.size()][5];
		Drug drug = null;
		for (int i = 0; i < drugList.size(); i++) {
			drug = drugList.get(i);
			list[i][0] = Integer.toString(drug.getId());
			list[i][1] = drug.getName();
			list[i][2] = Integer.toString(drug.getStock());
			list[i][3] = drug.getExpireDate();
			list[i][4] = Double.toString(drug.getPrice());
		}

		return list;

	}

	public void update(int id, String name, int stock, String expireDate, double price) {

		Drug drug = new Drug(id, name, stock, expireDate, price);

		new DrugDAOImp().updateDrug(drug);

		refresh();

	}

	public void updatePrice(int id, double price) {

		new DrugDAOImp().updatePrice(id, price);

		refresh();

	}

	public void refresh() {

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		String column[] = { "ID", "NAME", "STOCK", "EXPIRE DATE", "PRICE" };
		tableModel.setDataVector(getDrugs(), column);
		table.setModel(tableModel);

	}

	public void add(int id, String name, int stock, String expireDate, double price) {

		Drug drug = new Drug(id, name, stock, expireDate, price);
		new DrugDAOImp().addDrug(drug);
		refresh();

	}

	public void remove(int id) {

		new DrugDAOImp().removeDrugById(id);
		;
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

	public JTextField getIdTf() {

		return textField;
	}

	public JTextField getNameTf() {

		return textField_1;
	}

	public JTextField getStockTf() {

		return textField_2;
	}

	public JTextField getExpireTf() {

		return textField_3;
	}

	public JTextField getpriceTf() {

		return textField_4;
	}
}
