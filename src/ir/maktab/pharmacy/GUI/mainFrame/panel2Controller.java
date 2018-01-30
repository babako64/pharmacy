package ir.maktab.pharmacy.GUI.mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class panel2Controller implements ActionListener{

	panel2 jf;
	
	public panel2Controller(panel2 jf) {
		this.jf = jf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource() == jf.getLoadbtn()) {
			
			jf.getDrug(jf.getNameTf().getText());
			System.out.println("add");
		}
		
		if(e.getSource() == jf.getRemovebtn()) {
			System.out.println("re 2");
			jf.remove(jf.IdSelectedRow);
		}
		
		if(e.getSource() == jf.getAddbtn()) {
			
			jf.add(Integer.parseInt(jf.getIdTf().getText()), jf.getNameTf().getText(), Integer.parseInt(jf.getInsureCodeTf().getText()));
		}
		
		if(e.getSource() == jf.getButtonUpdate()) {
			
			if (jf.getNameTf().getText().equals("") || jf.getInsureCodeTf().getText().equals("")) {
				System.out.println("upd");
				jf.update(jf.IdSelectedRow, jf.NameSelectedRow, jf.InsurCodeSelectedRow);

			} else {
				jf.update(Integer.parseInt(jf.getIdTf().getText()), jf.getNameTf().getText(),
						Integer.parseInt(jf.getInsureCodeTf().getText()));
						
//				textField.setText("");
//				textField_1.setText("");
//				textField_2.setText("");
//				textField_3.setText("");
			}
		}
		
	}

}
