package ir.maktab.pharmacy.GUI.mainFrame.drgInsuranse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DrugInsuranceController implements ActionListener{

	DrugInsuranceView jf;
	
	public DrugInsuranceController(DrugInsuranceView jf) {
		
		this.jf = jf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == jf.getLoadbtn()) {
			
			try {
				jf.getShare(jf.getDrugIdTf().getText(), jf.InsureNameSelectedRow);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == jf.getRemovebtn()) {
			
			try {
				jf.remove(jf.DrugSelectedRow, jf.InsureSelectedRow);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == jf.getAddbtn()) {
			
			try {
				System.out.println(jf.InsurShareSelectedRow);
				jf.add(jf.getDrugIdTf().getText(), jf.InsureNameSelectedRow,Integer.parseInt(jf.getInsureIdTf().getText()));
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == jf.getButtonUpdate()) {
			
			if (jf.getDrugIdTf().getText().equals("") || jf.getInsureIdTf().getText().equals("")) {
				try {
					jf.update(jf.DrugSelectedRow, jf.InsureSelectedRow, jf.InsurShareSelectedRow);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				try {
					jf.update(jf.getDrugIdTf().getText(), jf.InsureNameSelectedRow, 
							Integer.parseInt(jf.getInsureIdTf().getText()));
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
						
//				textField.setText("");
//				textField_1.setText("");
//				textField_2.setText("");
//				textField_3.setText("");
			}
		}
		
	}

}
