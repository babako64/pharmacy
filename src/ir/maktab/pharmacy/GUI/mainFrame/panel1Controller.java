package ir.maktab.pharmacy.GUI.mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class panel1Controller implements ActionListener {

	panel1 jf;

	public panel1Controller(panel1 jf) {

		this.jf = jf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jf.getLoadbtn()) {

			jf.getDrug(jf.getNameTf().getText());
			System.out.println("add");
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
						Integer.parseInt(jf.getStockTf().getText()), jf.getExpireTf().getText(),
						Double.parseDouble(jf.getpriceTf().getText()));
			} else {

				jf.add(0, jf.getNameTf().getText(), Integer.parseInt(jf.getStockTf().getText()),
						jf.getExpireTf().getText(), Double.parseDouble(jf.getpriceTf().getText()));
			}
		}

		if (e.getSource() == jf.getButtonUpdate()) {

			if (jf.getNameTf().getText().equals("") || jf.getStockTf().getText().equals("")
					|| jf.getExpireTf().getText().equals("")) {
				System.out.println("upd");
				jf.update(jf.IdSelectedRow, jf.NameSelectedRow, jf.StocktSelectedRow, jf.ExpireDateSelectedRow,
						jf.PricaSelectedRow);

			} else {
				jf.update(Integer.parseInt(jf.getIdTf().getText()), jf.getNameTf().getText(),
						Integer.parseInt(jf.getStockTf().getText()), jf.getExpireTf().getText(),
						Double.parseDouble(jf.getpriceTf().getText()));

		
			}
		}

	}

}
