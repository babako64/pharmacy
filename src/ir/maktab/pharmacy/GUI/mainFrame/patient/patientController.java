package ir.maktab.pharmacy.GUI.mainFrame.patient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ir.maktab.pharmacy.GUI.mainFrame.MainFrameView;

public class patientController implements ActionListener {

	patientView jf;

	public patientController(patientView jf) {
		this.jf = jf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jf.getLoadbtn()) {

			try {
				jf.getPatient(jf.getNameTf().getText());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (e.getSource() == jf.getRemovebtn()) {

			try {
				if (MainFrameView.userCheck) {
					jf.remove(jf.NameSelectedRow);
				} else {
					JOptionPane.showMessageDialog(null, "Access denied \n", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (e.getSource() == jf.getAddbtn()) {

			try {
				jf.addPatient(jf.getNameTf().getText(), Integer.parseInt(jf.getInsureNumberTf().getText()),
						jf.insuranceName, jf.getDoctorNameTf().getText(), jf.getReferralDateTf().getText());
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

			try {
				jf.update(jf.IdSelectedRow, jf.NameSelectedRow, jf.InsurNumberSelectedRow, jf.InsurIdSelectedRow);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}

	}

}
