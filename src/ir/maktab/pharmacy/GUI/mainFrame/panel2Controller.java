package ir.maktab.pharmacy.GUI.mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class panel2Controller implements ActionListener {

	panel2 jf;

	public panel2Controller(panel2 jf) {
		this.jf = jf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jf.getLoadbtn()) {

			jf.getInsurance(jf.getNameTf().getText());

		}

		if (e.getSource() == jf.getRemovebtn()) {

			if (MainFrameView.userCheck) {
				jf.remove(jf.IdSelectedRow);
			} else {
				JOptionPane.showMessageDialog(null, "Access denied \n", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource() == jf.getAddbtn()) {

			if (!jf.getIdTf().getText().equals("")) {

				jf.add(Integer.parseInt(jf.getIdTf().getText()), jf.getNameTf().getText(),
						Integer.parseInt(jf.getInsureCodeTf().getText()));
			} else {
				jf.add(0, jf.getNameTf().getText(), Integer.parseInt(jf.getInsureCodeTf().getText()));
			}
		}

		if (e.getSource() == jf.getButtonUpdate()) {

			if (MainFrameView.userCheck) {
				if (jf.getNameTf().getText().equals("") || jf.getInsureCodeTf().getText().equals("")) {
					jf.update(jf.IdSelectedRow, jf.NameSelectedRow, jf.InsurCodeSelectedRow);

				} else {
					jf.update(Integer.parseInt(jf.getIdTf().getText()), jf.getNameTf().getText(),
							Integer.parseInt(jf.getInsureCodeTf().getText()));

				}

			} else {
				JOptionPane.showMessageDialog(null, "Access denied \n", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}

	}

}
