package ir.maktab.pharmacy.GUI.mainFrame.prescription;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.GUI.mainFrame.MainFrameView;

public class PrescriptionController implements ActionListener {

	PrescriptionView jf;

	public PrescriptionController(PrescriptionView jf) {

		this.jf = jf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jf.getLoadbtn()) {

			// jf.getDrug(jf.getNameTf().getText());

		}

		if (e.getSource() == jf.getRemovebtn()) {

			if (MainFrameView.userCheck) {
				// jf.remove(jf.IdSelectedRow);
			} else {
				JOptionPane.showMessageDialog(null, "Access denied \n", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == jf.getAddbtn()) {

			try {
				if (jf.getPaymenntTf().getText().equals("")) {
					jf.addDrug(jf.getNameTf().getText(), jf.prescriptionID, Integer.parseInt(jf.getCountTf().getText()),
							0);
				} else {
					jf.addDrug(jf.getNameTf().getText(), jf.prescriptionID, Integer.parseInt(jf.getCountTf().getText()),
							Double.parseDouble(jf.getPaymenntTf().getText()));
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getSource() == jf.getButtonUpdate()) {

			// if (jf.getNameTf().getText().equals("") ||
			// jf.getStockTf().getText().equals("")
			// || jf.getExpireTf().getText().equals("")) {
			// System.out.println("upd");
			// jf.update(jf.IdSelectedRow, jf.NameSelectedRow, jf.StocktSelectedRow,
			// jf.ExpireDateSelectedRow, jf.PricaSelectedRow);
			//
			// } else {
			// jf.update(Integer.parseInt(jf.getIdTf().getText()), jf.getNameTf().getText(),
			// Integer.parseInt(jf.getStockTf().getText()),
			// jf.getExpireTf().getText(), Double.parseDouble(jf.getpriceTf().getText()));
			//
			//// textField.setText("");
			//// textField_1.setText("");
			//// textField_2.setText("");
			//// textField_3.setText("");
			// }
		}
	}

}
